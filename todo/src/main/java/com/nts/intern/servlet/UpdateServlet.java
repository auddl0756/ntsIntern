package com.nts.intern.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.intern.dao.TodoDao;
import com.nts.intern.dto.TodoDto;
import com.nts.intern.security.Security;

@WebServlet(urlPatterns = { "/update" }, initParams = @WebInitParam(name = "id", value = "-1"))
public class UpdateServlet extends HttpServlet {
	private static final TodoDao dao = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameterValues("id")[0];
		String type= request.getParameterValues("type")[0];
		
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		long id = Long.parseLong((String) request.getAttribute("id"));
		String type= (String)request.getAttribute("type");

		dao.updateTodo(id,type);
		
		response.sendRedirect(Security.MAIN_URL);
	}
}
