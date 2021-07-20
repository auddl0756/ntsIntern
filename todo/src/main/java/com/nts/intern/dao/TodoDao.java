package com.nts.intern.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nts.intern.dto.TodoDto;
import com.nts.intern.dto.TodoResponseDto;
import com.nts.intern.security.Security;

public class TodoDao {
	private static final String URL = Security.URL;
	private static final String USER = Security.USER;
	private static final String PASSWD = Security.PASSWD;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println(classNotFoundException);
		}
	}

	public List<TodoResponseDto> getAll() {
		List<TodoResponseDto> result = new ArrayList<>();

		String query = "select * from todo";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				LocalDateTime regDateTime = resultSet.getObject("regdate", LocalDateTime.class);

				StringBuilder regDate = new StringBuilder();

				regDate.append(regDateTime.getYear());
				regDate.append(".");
				regDate.append(regDateTime.getMonth());
				regDate.append(".");
				regDate.append(regDateTime.getDayOfMonth());

				TodoResponseDto dto = new TodoResponseDto();
				dto.setId(resultSet.getLong("id"));
				dto.setTitle(resultSet.getString("title"));
				dto.setName(resultSet.getString("name"));
				dto.setSequence(resultSet.getInt("sequence"));
				dto.setType(resultSet.getString("type"));
				dto.setRegDate(regDate.toString());

				result.add(dto);
			}
		} catch (Exception exception) {
			System.err.println("getAll()"+" "+exception);
		}

		return result;
	}

	public int addTodo(TodoDto dto) {
		int insertedCount = 0;

		String query = "insert into todo values(?,?,?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setLong(1, dto.getId());
			preparedStatement.setString(2, dto.getTitle());
			preparedStatement.setString(3, dto.getName());
			preparedStatement.setInt(4, dto.getSequence());
			preparedStatement.setString(5, dto.getType());
			preparedStatement.setString(6, dto.getRegDate().toString());

			insertedCount = preparedStatement.executeUpdate();
		} catch (Exception exception) {
			System.err.println("insert "+dto+" "+exception);
		}
		return insertedCount;
	}

	public int updateTodo(TodoDto dto) {
		int updatedCount = 0;

		String query = "update todo set type=? where id =?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setString(1, dto.getType());
			preparedStatement.setLong(2, dto.getId());

			updatedCount = preparedStatement.executeUpdate();
		} catch (Exception exception) {
			System.err.println("update "+ dto+" "+exception);
		}
		return updatedCount;
	}
}
