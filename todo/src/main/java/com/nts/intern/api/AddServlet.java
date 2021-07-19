package com.nts.intern.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.intern.dao.TodoDao;
import com.nts.intern.dto.TodoDto;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

		response.sendRedirect("http://localhost:8080/todo/main");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		TodoDao dao = new TodoDao();

		TodoDto dto = new TodoDto();
		dto.setId(5L);
		dto.setTitle("title1");
		dto.setName("name1");
		dto.setSequence(1);
		dto.setType("doing");
		dto.setRegDateTime(LocalDateTime.now());

		// int cnt =dao.addTodo(dto);
		// System.out.println(cnt);
	}

}
