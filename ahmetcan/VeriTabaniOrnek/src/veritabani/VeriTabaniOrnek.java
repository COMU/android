package veritabani;
import java.sql.*;

public class VeriTabaniOrnek {

	public static void main(String[] args) {
		
		Statement stmt;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            System.out.println("surucu yukleme basarili");
        } catch (Exception ex) {
        	
        }
        try {
            //Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/misafir?","root","123456");
    		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/veri_tabani_adi?" +"user=kullanici_adi&password=parola");
    	    //Get a Statement object
    	    stmt = conn.createStatement();
    	    String sql = "SELECT * FROM tablo_adi";
    	    ResultSet result = stmt.executeQuery(sql);
    	    while (result.next())
    	    {
    	    	System.out.println(result.getString(1));
    	    }
    		conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}