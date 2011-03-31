package db.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class Connector {
	
	private String driver = "";
	private String hostname = "";
	private String username;
	private String password;
	private String dbname;
	private Statement stmt;
	
	public Connector()
	{
		Properties properties = new Properties();
		File f=new File("conf/db.properties");
		try 
		{
			properties.load(new FileInputStream(f));
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDriver(properties.getProperty("driver"));
		this.setHostname(properties.getProperty("hostname"));
		this.setUsername(properties.getProperty("username"));
		this.setPassword(properties.getProperty("password"));
		this.setDBname(properties.getProperty("dbname"));
//		this.setDriver("mysql");
//		this.setHostname("localhost");
//		this.setUsername("root");
//		this.setPassword("serhat");
//		this.setDBname("ahbap_db");
		
	}

	public Boolean connect()
	{
		String s="mysql";
		if (driver.equals(s) == true)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String query_string = "jdbc:mysql://" + this.getHostname() + "/" + this.getDBname() + "?" + "user=" + this.getUsername() + "&password=" + this.getPassword(); 
				System.out.println(query_string);
				Connection conn=DriverManager.getConnection(query_string);
				this.stmt = conn.createStatement();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		}
		return false;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}
	
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getHostname() {
		return hostname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public void setDBname(String dbname) {
		this.dbname = dbname;
	}

	public String getDBname() {
		return dbname;
	}
	
	public void setStatement(Statement statement) {
		this.stmt = statement;
	}

	public Statement getStatement() {
		return stmt;
	}

}
