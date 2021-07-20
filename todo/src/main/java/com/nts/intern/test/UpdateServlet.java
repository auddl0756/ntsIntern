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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.sendRedirect(Security.MAIN_URL);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		TodoDao dao = new TodoDao();

		TodoDto dto = new TodoDto();
		
		dto.setId(5L);
		dto.setTitle("updated_title1");
		dto.setName("updated_name1");
		dto.setSequence(1);
		dto.setType("updated_doing");
		dto.setRegDateTime(LocalDateTime.now());
		
		dao.updateTodo(dto);
	}

}
