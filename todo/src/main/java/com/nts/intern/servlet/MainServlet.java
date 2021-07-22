package com.nts.intern.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.intern.dao.TodoDao;
import com.nts.intern.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final TodoDao DAO = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		List<TodoDto> list = DAO.findAll();

		request.setAttribute("todoList", list);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
	}
}
