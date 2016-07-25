package com.hurl.toolkit.jdbc;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DBManagerTest {
	
	@Test
	public void test(){
		DBConfig config = new DBConfig();
		config.setConnectUrl("jdbc:mysql://localhost:3307/twooneo1_waguji?useUnicode=true&amp;characterEncoding=UTF-8");
		config.setDriverClass("com.mysql.jdbc.Driver");
		config.setUser("twooneo1_waguji");
		config.setPassword("waguji");
		
		DBManager manager = DBManager.newInstance(config);
		List<Map<String,Object>>list = manager.query("select * from sj_gpdm limit 10");
		System.out.println(list);
	}
}
