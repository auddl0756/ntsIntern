package com.nts.intern.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.intern.dao.TodoDao;
import com.nts.intern.dto.TodoDto;
import com.nts.intern.security.Security;
import com.nts.intern.type.TodoType;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final TodoDao DAO = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		TodoDto dto = new TodoDto();

		dto.setTitle(request.getParameter("title"));
		dto.setName(request.getParameter("name"));
		dto.setSequence(Integer.parseInt(request.getParameter("sequence")));
		dto.setType(TodoType.TODO.getType());

		DAO.addTodo(dto);

		response.sendRedirect(Security.MAIN_URL);
	}

}
