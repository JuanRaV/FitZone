package fit_zone.connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    public static Connection getConnection(){
        Connection conn = null;
        var databaseName = "fit_zone_db";
        var url = "jdbc:mysql://localhost:3306/" + databaseName;
        var user = "root";
        var password = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("ERROR CONNECTING TO DB: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        var conn = ConnectionDB.getConnection();
        if(conn!=null){
            System.out.println("Connected successfully");
        }else{
            System.out.println("Something went wrong");
        }
    }
}
