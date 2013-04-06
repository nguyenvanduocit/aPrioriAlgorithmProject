/*
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transactions` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
)
 */
package demomysql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.StringTokenizer;

/**
 *
 * @author thuytienvy
 */
public class DemoMySQL {
    static Connection myCon;
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
       
        MySQL inStream = new MySQL("localhost","nckh_apriori","root","");
        inStream.connect();
        ResultSet rs = inStream.select("select transactions from transactions limit 2");
        String str;
        while (rs.next()) {
            str = rs.getString("transactions");
            StringTokenizer stToken = new StringTokenizer(str, "\t");
            while (stToken.hasMoreTokens()) {
                System.out.print(stToken.nextToken());
            }
            System.out.println();
        }
        inStream.disConnect();
    }
    
    public static ResultSet select(String query) throws SQLException{
        Statement stmt = myCon.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
    
    public static void importToDatabase()throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Statement myStmt;
        try (BufferedReader inList = new BufferedReader(new FileReader("census_LineNo.txt"))) {
            myStmt = myCon.createStatement();

            String str;

            while ((str = inList.readLine()) != null) {
                myStmt.executeUpdate("INSERT into transactions (transactions) values ('"+str+"')");
            }
            inList.close();
        }
    }
}