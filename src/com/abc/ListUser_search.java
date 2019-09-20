package com.abc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newPage.User;
import newPage.UserDao0;

/**
 * Servlet implementation class ListUser_search
 */
@WebServlet("/ListUser_search")
public class ListUser_search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String dd=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUser_search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		int pageNo = 1;
		UserDao0 userdao=new UserDao0();
		List<User> lists=new ArrayList<User>();
		String pageno=request.getParameter("pageNos");
		
		int recordCount=0;
		
		if(pageno != null){
			pageNo=Integer.parseInt(pageno);
		}
		
		else if(pageno ==null) {
			pageNo=1;
		}
		
		if(request.getParameter("dd")!=null) {
			dd=request.getParameter("dd");
		}
		lists=userdao.listUser_search(pageNo,dd);
		recordCount=userdao.getPage_search(dd);

//		System.out.print(recordCount);
		request.setAttribute("recordCount", recordCount);
		request.setAttribute("listss", lists);
//		System.out.println(pageno);
//		System.out.println(pageNo);
		request.setAttribute("pageNos", pageNo);
		request.getRequestDispatcher("userlist_search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
