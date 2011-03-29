package db.operations;

import java.sql.SQLException;
import java.sql.Statement;

import string.operations.*;

public class MySqlConnector extends Connector {
	
	public Boolean insert (String tblname, String[] field_names, String[] values ) {
		
		StringOperations string_operations = new StringOperations();
		
		String field = string_operations.joinField(field_names, ",");
		String str_fields = "(" + field + ")";
		
		String value = string_operations.joinValue(values, ",");
		String str_values = "(" + value + ")";
		
		String[] query_array = {"INSERT INTO", tblname, str_fields, "VALUES", str_values};
		
		String query = string_operations.joinField(query_array, " ");
		System.out.println(query);
		Statement stmt = getStatement();
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

}
