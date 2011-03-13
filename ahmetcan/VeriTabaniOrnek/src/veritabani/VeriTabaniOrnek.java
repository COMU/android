package veritabani;
import java.sql.*;

public class VeriTabaniOrnek {

	public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            System.out.println("surucu yukleme basarili");
        } catch (Exception ex) {
        	
        }
        try {
            //Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/misafir?","root","123456");
    		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/misafir?" +"user=root&password=123456");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}