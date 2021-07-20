package com.nts.intern.test;

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

		response.sendRedirect(Security.MAIN_URL);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		for (int i = 1; i <= 10; i++) {
			TodoDto dto = new TodoDto();
			dto.setId(10L + i);
			dto.setTitle("title1");
			dto.setName("name1");
			dto.setSequence(1);
			dto.setType("doing");
			dto.setRegDateTime(LocalDateTime.now());

			dao.addTodo(dto);
		}
	}
}
