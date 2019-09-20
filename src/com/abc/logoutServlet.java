package com.abc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logoutServlet
 */
@WebServlet("/logoutServlet")
public class logoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logoutServlet() {
        super();
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
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		pw.print("<script type=\"text/javascript\">");
		pw.print("alert('Logout Successfully')");
		pw.print("</script>");
		
		request.getRequestDispatcher("index.jsp").include(request, response);
		
//		if(session.getAttribute("account") == null) {
//			pw.print("<script type=\"text/javascript\">");
//			pw.print("alert('You are not login yet!!')");
//			pw.print("</script>");
//		}else {
//			session.invalidate();
//			pw.print("<script type=\"text/javascript\">");
//			pw.print("alert('Logout Successfully')");
//			pw.print("</script>");
//		
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
