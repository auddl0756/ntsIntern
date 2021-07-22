package com.nts.intern.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.intern.dao.TodoDao;
import com.nts.intern.security.Security;

@WebServlet("/delete")
public class DelteServlet extends HttpServlet {
	private static final TodoDao dao = new TodoDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameterValues("id")[0];
		request.setAttribute("id", id);
		
		doDelete(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		long id = Long.parseLong((String) request.getAttribute("id"));
		
		dao.deleteTodo(id);
		
		response.sendRedirect(Security.MAIN_URL);
	}
	

}
