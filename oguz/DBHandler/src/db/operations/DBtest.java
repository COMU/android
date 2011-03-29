package db.operations;

public class DBtest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySqlConnector myConnector = new MySqlConnector();
		if(myConnector.connect()){
			String field[]={"ad","soyad","dogum_tarihi","email","parola","konum_id"};
			String values[]={"gokhan","sahin","1991-08-18","gokhan@gmail.com","111111","1"};
			myConnector.insert("kullanici",field, values);
		}
	}

}
