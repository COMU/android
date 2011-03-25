package db.operations;

import string.operations.*;

public class MySqlConnector extends Connector {
	
	public Boolean insert (String tblname, String[] field_names, String[] values ) {
		
		StringOperations string_operations = new StringOperations();
		
		String field = string_operations.join(field_names, ",");
		String str_fields = "(" + field + ")";
		
		String value = string_operations.join(values, ",");
		String str_values = "(" + value + ")";
		
		String[] query_array = {"INSERT INTO", tblname, str_fields, "VALUES", str_values};
		
		String query = string_operations.join(query_array, " ");
		
		Statement stmt = this.getStatement();
		
	}

}
