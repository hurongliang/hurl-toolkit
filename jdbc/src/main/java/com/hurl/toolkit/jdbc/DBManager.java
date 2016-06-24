package com.hurl.toolkit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {
	private DBConfig config;
	
	public static void main(String[] args){
		DBConfig config = new DBConfig();
		config.setConnectUrl("jdbc:mysql://localhost:3306/twooneo1_waguji?useUnicode=true&amp;characterEncoding=UTF-8");
		config.setDriverClass("com.mysql.jdbc.Driver");
		config.setUser("twooneo1_waguji");
		config.setPassword("waguji");
		
		DBManager manager = DBManager.newInstance(config);
		List<Map<String,Object>>list = manager.query("select * from t_dm_gp");
		System.out.println(list);
	}
	public static DBManager newInstance(DBConfig config){
		return new DBManager(config);
	}
	private DBManager(DBConfig config){
		this.config = config;
	}
	public List<Map<String,Object>> query(String sql){
		try{
			Class.forName(config.getDriverClass());
		}catch(ClassNotFoundException e){
			throw new DBRuntimeException("找不到驱动："+config.getDriverClass(),e);
		}
		
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		
		try{
			try{
				conn = DriverManager.getConnection(config.getConnectUrl(), config.getUser(), config.getPassword());
			}catch(SQLException e){
				throw new DBRuntimeException("创建数据库连接时出错！",e);
			}
			
			try{
				statement = conn.createStatement();
			}catch(SQLException e){
				throw new DBRuntimeException("创建Statement时出错！",e);
			}
			
			try{
				rs = statement.executeQuery(sql);
			}catch(SQLException e){
				throw new DBRuntimeException("执行查询时出错！",e);
			}
			
			try{
				int columnCount = rs.getMetaData().getColumnCount();
				String[] columnNames = new String[columnCount];
				for(int i=0;i<columnCount;i++){
					columnNames[i]=rs.getMetaData().getColumnName(i+1);
				}
				while(rs.next()){
					Map<String,Object> map = new HashMap<String,Object>();
					for(int i=0;i<columnNames.length;i++){
						map.put(columnNames[i], rs.getObject(columnNames[i]));
					}
					result.add(map);
				}
				return result;
			}catch(SQLException e){
				throw new DBRuntimeException("读取查询结果时出错！",e);
			}
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				rs=null;
			}
			if(statement!=null){
				try{
					statement.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				statement=null;
			}
			if(conn!=null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				conn=null;
			}
		}
		
	}
}
