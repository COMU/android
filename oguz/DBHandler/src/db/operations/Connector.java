package db.operations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class Connector {
	
	private String driver = "mysql";
	private String hostname = "localhost";
	private String username;
	private String password;
	private String dbname;
	private Statement stmt;
	
	public void connector()
	{
		Properties properties = new Properties();
		try 
		{
			properties.load(new FileInputStream("db.conf"));
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
		
	}

	public Boolean connect()
	{
		
		if (this.driver == "mysql")
		{
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				String query_string = "jdbc:mysql://" + this.getHostname() + "/" + this.getDBname() + "?" + "user=" + this.getUsername() + "&=" + this.getPassword(); 
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
