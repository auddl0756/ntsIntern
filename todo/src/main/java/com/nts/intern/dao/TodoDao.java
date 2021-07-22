package com.nts.intern.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.nts.intern.dto.TodoDto;
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

	public TodoDto findById(long id) {
		String query = "select * from todo where id=?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setLong(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				TodoDto dto = new TodoDto();
				dto.setId(resultSet.getLong("id"));
				dto.setTitle(resultSet.getString("title"));
				dto.setName(resultSet.getString("name"));
				dto.setSequence(resultSet.getInt("sequence"));
				dto.setType(resultSet.getString("type"));

				LocalDateTime regDateTime = resultSet.getObject("regdate", LocalDateTime.class);
				dto.setRegDateTime(regDateTime);

				return dto;
			} else {
				return null;
			}
		} catch (Exception exception) {
			System.err.println("getAll()" + " " + exception);
		}

		return null;
	}

	public List<TodoDto> findAll() {
		List<TodoDto> result = new ArrayList<>();

		String query = "select * from todo";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				LocalDateTime regDateTime = resultSet.getObject("regdate", LocalDateTime.class);

				TodoDto dto = new TodoDto();
				dto.setId(resultSet.getLong("id"));
				dto.setTitle(resultSet.getString("title"));
				dto.setName(resultSet.getString("name"));
				dto.setSequence(resultSet.getInt("sequence"));
				dto.setType(resultSet.getString("type"));
				dto.setRegDateTime(regDateTime);

				result.add(dto);
			}
		} catch (Exception exception) {
			System.err.println("getAll()" + " " + exception);
		}

		return result;
	}

	public int addTodo(TodoDto dto) {
		int insertedCount = 0;

		String query = "insert into todo(title,name,sequence,type) values(?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setString(1, dto.getTitle());
			preparedStatement.setString(2, dto.getName());
			preparedStatement.setInt(3, dto.getSequence());
			preparedStatement.setString(4, dto.getType());

			insertedCount = preparedStatement.executeUpdate();
		} catch (Exception exception) {
			System.err.println("while inserting... " + dto + " " + exception);
		}
		return insertedCount;
	}

	public int updateTodo(long id,String type) {
		int updatedCount = 0;

		String query = "update todo set type=? where id =?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			if (type.equals("TODO")) {
				preparedStatement.setString(1, "DOING");
			} else if (type.equals("DOING")) {
				preparedStatement.setString(1, "DONE");
			}

			preparedStatement.setLong(2, id);
			updatedCount = preparedStatement.executeUpdate();

		} catch (Exception exception) {
			System.err.println("while updating... " + "id="+id+"type="+type+ " " + exception);
		}
		return updatedCount;
	}
	
	public int deleteTodo(long id) {
		int deletedCount = 0;
		
		String query = "delete from todo where id=?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setLong(1, id);
			deletedCount= preparedStatement.executeUpdate();

		} catch (Exception exception) {
			System.err.println("while deleting... " + "id="+id+" " + exception);
		}
		
		return deletedCount;
	}
}
