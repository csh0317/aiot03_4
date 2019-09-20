package com.abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.UserDAO;
import net.javaguides.usermanagement.model.User;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        userDAO = new UserDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		boolean username = false , userpwd = false;
		
		
		if(name != "" ){
			if(password != "") {
				if(password2 != "") {
					List<User> listuser = userDAO.selectAllUser();
					String dataname = null;
					for(User i : listuser) {
						if(name.equals(i.getUser())) {
							pw.print("<script type=\"text/javascript\">");
							pw.print("alert('This name is used')");
							pw.print("</script>");
							request.getRequestDispatcher("regist.jsp").include(request, response);
							username = false;
							break;
						}else{
							username = true;
							}
					}
					
					if(password.equals(password2)) {
						userpwd = true;
					}else {
						pw.print("<script type=\"text/javascript\">");
						pw.print("alert('Error')");
						pw.print("</script>");
						request.getRequestDispatcher("regist.jsp").include(request, response);
						userpwd = false;
					}
				}else {
//					System.out.print("N PWD2");
					pw.print("<script type=\"text/javascript\">");
					pw.print("alert('Please enter password2')");
					pw.print("</script>");
					request.getRequestDispatcher("regist.jsp").include(request, response);
				}
				}else {
//					System.out.print("N PWD");
					pw.print("<script type=\"text/javascript\">");
					pw.print("alert('Please enter password')");
					pw.print("</script>");
					request.getRequestDispatcher("regist.jsp").include(request, response);
				}
			}else {
//				System.out.print("N NAME");
				pw.print("<script type=\"text/javascript\">");
				pw.print("alert('Please enter name')");
				pw.print("</script>");
				request.getRequestDispatcher("regist.jsp").include(request, response);
				}
		
		if(username & userpwd) {
			User newUser = new User(name,password);
			try {
				userDAO.insertUser(newUser);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			pw.print("<script type=\"text/javascript\">");
			pw.print("alert('使用者新增成功')");
			pw.print("</script>");
			request.getRequestDispatcher("index.jsp").include(request, response);
//			response.sendRedirect("index.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
