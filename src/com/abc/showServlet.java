package com.abc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import newPage.User;
import newPage.UserDao0;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/showServlet")
public class showServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDao0 userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showServlet() {
        super();
        userDao = new UserDao0();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html ; charset=utf-8");
		
//		request.getRequestDispatcher("fileName.jsp").include(request, response);
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		User showImg = userDao.selectUser(id);
		
		String imageFileName = showImg.getName();
		System.out.print(imageFileName);
		
		String contentType = this.getServletContext().getMimeType(imageFileName);
		
		if(contentType == null) {
			contentType = "image/png" ;
		}
        
        System.out.println("Content Type: "+ contentType);
        
        response.setHeader("Content-Type", contentType);
        
        response.setHeader("Content-Length", String.valueOf(showImg.getImageData().length));
    
        response.setHeader("Content-Disposition", "inline; filename=\"" + showImg.getName() + "\"");

		OutputStream out = response.getOutputStream();
		
		out.write(showImg.getImageData());
		
        out.flush(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
