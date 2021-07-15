package com.nts.intern;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head><title>form</title>");
		out.println("<link rel=\"stylesheet\" href=\"css/todayServlet.css\">");
		out.println("</head>");
		out.println("<body>");

		LocalDateTime nowTime = LocalDateTime.now();

		String formattedTime = nowTime.format(FORMAT);

		out.println("<h2>현재 시간 <br/> " + formattedTime + "</h2>");

		out.println("<a href=\"index.html\">메인 화면</a>");

		out.println("</body>");
		out.println("</html>");
	}

}
