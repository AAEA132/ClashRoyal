package Controller;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The type Database connection class.
 */
public class DatabaseConnection {
    /**
     * The Database link.
     */
    public Connection databaseLink;

    /**
     * Get connection method.
     *
     * @return the connection to database
     */
    public Connection getConnection(){
        String databaseName = "clashroyaluserdb";
        String databaseUser = "M";
        String databasePassword = "72p4";
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
