package com.nts.intern.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.nts.intern.dto.TodoDto;

public class TodoDao {
	private static final String URL = "jdbc:mysql://10.113.116.52:13306/user07";
	private static final String USER = "user07";
	private static final String PASSWD = "user07";
	//private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public List<TodoDto> getAll() {
		ResultSet resultSet = null;
		List<TodoDto> result = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String query = "select * from todo";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String title = resultSet.getString("title");
				String name = resultSet.getString("name");
				Integer sequence = resultSet.getInt("sequence");
				String type = resultSet.getString("type");
				String tmpLocalDateTime = resultSet.getString("regdate");
				
				StringTokenizer st = new StringTokenizer(tmpLocalDateTime, " ");
				String dateStr = st.nextToken();
				st = new StringTokenizer(dateStr,"-");
				
				int year = Integer.parseInt(st.nextToken());
				int month = Integer.parseInt(st.nextToken());
				int date = Integer.parseInt(st.nextToken());
				
				LocalDateTime regDate = LocalDateTime.of(year, month, date,0,0,0);
				
				TodoDto dto = new TodoDto();
				dto.setId(id);
				dto.setTitle(title);
				dto.setName(name);
				dto.setSequence(sequence);
				dto.setType(type);
				dto.setRegDateTime(regDate);
				
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(result);	
		return result;
	}
	
	
	public int addTodo(TodoDto dto) {
		int insertCount = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String query = "insert into todo values(?,?,?,?,?,?)";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setLong(1, dto.getId());
			preparedStatement.setString(2, dto.getTitle());
			preparedStatement.setString(3, dto.getName());
			preparedStatement.setInt(4, dto.getSequence());
			preparedStatement.setString(5,dto.getType());
			preparedStatement.setString(6, dto.getRegDate().toString());
			
			System.out.println(dto.getRegDate().toString());
			
			insertCount = preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(result);	
		return insertCount;
	}
	
	
}
