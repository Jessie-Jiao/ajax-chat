/*
 * set up database connection
 */
package DbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.LogIn;

/**
 *
 * @author Siwei Jiao
 */
public class DbConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                //connect db
                String driver = "org.apache.derby.jdbc.ClientDriver";
                Class.forName(driver);
                String connectionURL = "jdbc:derby://localhost:1527/ajax_chat";
                connection = DriverManager.getConnection(connectionURL, "username", "password");

              
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }

    }
}
