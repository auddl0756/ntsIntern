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
import com.nts.intern.type.TodoType;

@WebServlet(urlPatterns = {"/update"}, initParams = @WebInitParam(name = "id", value = "-1"))
public class UpdateServlet extends HttpServlet {
	private static final TodoDao DAO = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");

		if (type.equals(TodoType.TODO.getType())) {
			DAO.updateTodo(id, TodoType.DOING.getType());
		} else if (type.equals(TodoType.DOING.getType())) {
			DAO.updateTodo(id, TodoType.DONE.getType());
		}

		response.sendRedirect(Security.MAIN_URL);
	}
}
