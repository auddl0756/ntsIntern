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

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final TodoDao dao = new TodoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		TodoDto dto = new TodoDto();
		dto.setTitle(request.getParameterValues("title")[0]);
		dto.setName(request.getParameterValues("name")[0]);
		dto.setSequence(Integer.parseInt(request.getParameterValues("sequence")[0]));
		dto.setType("TODO");

		dao.addTodo(dto);

		response.sendRedirect(Security.MAIN_URL);
	}

}
