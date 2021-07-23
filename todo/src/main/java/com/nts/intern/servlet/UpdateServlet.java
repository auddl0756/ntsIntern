package com.nts.intern.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.intern.dao.TodoDao;
import com.nts.intern.security.Security;
import com.nts.intern.type.TodoType;

@WebServlet(urlPatterns = {"/update"}, initParams = @WebInitParam(name = "id", value = "-1"))
public class UpdateServlet extends HttpServlet {
	private static final TodoDao DAO = new TodoDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String updateInfo = br.readLine();

		HashMap<String, Object> map = new ObjectMapper().readValue(updateInfo, HashMap.class);

		long id = Long.parseLong((String)map.getOrDefault("id", "0"));
		String type = (String)map.get("type");

		if (type.equals(TodoType.TODO.getType())) {
			DAO.updateTodo(id, TodoType.DOING.getType());
		} else if (type.equals(TodoType.DOING.getType())) {
			DAO.updateTodo(id, TodoType.DONE.getType());
		}

		response.sendRedirect(Security.MAIN_URL);
	}
}
