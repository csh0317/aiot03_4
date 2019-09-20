package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane; 


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		String account = req.getParameter("name");
		
		HttpSession session = req.getSession();
	
		try 
		{ 	ServletContext sc = this.getServletContext();
			Connection con = (Connection) sc.getAttribute("mycon");
			PreparedStatement ps = con.prepareStatement("select * from password where user='"+account+"'",ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			String validateCode = (String) req.getSession().getAttribute("validateCode");
			String securityCode = req.getParameter("securityCode");
			boolean a=false;
			while(rs.next()) {
					
					String pwd = req.getParameter("pwd");
					String user =rs.getString("user");
					String psd = rs.getString("password");
					System.out.println("------USER-----"+user);
					System.out.println("------PASSWORD----"+psd);
					if(account.equals(user)&pwd.equals(psd)) {a=true;break;}
					}
					
					if(a==true) {
						if(securityCode==null||"".equals(securityCode)){ 
							req.setAttribute("ff", "驗證碼為空"); 
							req.getRequestDispatcher("index.jsp").include(req, res);
						}
						else{   
							if(validateCode.equals(securityCode)){  
								session.setAttribute("account", account);
					            res.sendRedirect("ListUser");
					        }
							else{   
								req.setAttribute("ff", "驗證碼錯誤");
								req.getRequestDispatcher("index.jsp").include(req, res);
						    } 
						}
					}
					else {
						req.setAttribute("ff", "密碼錯誤");
						RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
						rd.include(req, res);
					}		
		}catch(Exception e) {e.printStackTrace();;}
				out.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
