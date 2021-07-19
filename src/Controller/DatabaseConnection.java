package Controller;
import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "enter name of database";
        String databaseUser = "enter name of username";
        String databasePassword = "enter password";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
