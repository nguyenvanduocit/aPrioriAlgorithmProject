/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nckh_apriori;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thuytienvy
 */
public class MySQL {
    private static String server;
    private static String userName;
    private static String password;
    private static String databaseName;
    private static Connection myCon;
    
    public MySQL(String server, String databaseName, String userName, String password) {
        MySQL.server = server;
        MySQL.databaseName = databaseName;
        MySQL.userName = userName;
        MySQL.password = password;
    }
    public ResultSet select(String query) throws SQLException{
        Statement stmt = myCon.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    public void disConnect(){
        try {
            myCon.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myCon = DriverManager.getConnection("jdbc:mysql://"+server+"/"+databaseName,userName,password);
            return true;
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException sqlEx){
            return false;
        }
    }
}
