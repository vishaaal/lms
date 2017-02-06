
package processing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;


    public class LogInQuerry implements Callable<Boolean> {
        private String tableName, id, pass;
        public LogInQuerry(String tn,String i,String p){
            tableName =tn ;
            id = i;
            pass = p;
        }
        @Override
        public Boolean call() {
           boolean f=false;
           String sql = "SELECT userId, password FROM " + tableName + " WHERE userId = \"" + id+"\"";
        // create a new connection from SqlConnect
            try (Connection conn = processing.SqlConnect.getConnection()) {    
//             print out a message
                System.out.println(String.format("Connected to database %s "+ "successfully.", conn.getCatalog()));

                Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql);   
                rs.next();
                if(id.compareTo(rs.getString("userId"))==0 && pass.compareTo(rs.getString("password"))==0) f=true;
                else f=false;
//                    System.out.println(id.compareTo(rs.getString("userId"))+"  "+pass.compareTo(rs.getString("password"))+f);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return f;
        }
    }

