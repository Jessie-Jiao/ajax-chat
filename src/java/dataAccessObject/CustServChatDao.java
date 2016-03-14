/*
 * This class contains methods to manage chat for customer service
 * @author Siwei Jiao
 */
package dataAccessObject;

import DbConnect.DbConnection;
import java.sql.*;
import java.sql.Timestamp;

import bean.ChatMsgBean;

public class CustServChatDao {
    private Connection connection;
        Statement st = null;
        ResultSet rs = null;
               
        String sql = "";
        
         public CustServChatDao() {
            //connect to database and select the record
            connection = DbConnection.getConnection();	
        }
    
     
    /**
     * check if customer service exists in the onlineChatCustServs table
     * @param userName
     * @return 
     */
    public int checkOnlineExist(String userName) {
           
            //prepare and execute search query
        try{   
            sql = "SELECT COUNT(*) AS ROWCOUNT FROM IS2730.onlineChatCustServs WHERE user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setString(1, userName);          

            rs = ps.executeQuery();

            //check if query returns one valid result 
            if(rs.next())
		return rs.getInt("ROWCOUNT");
            else
                return 0;
            
        }catch (Exception e) {
                System.out.println(e.getMessage()) ;
                return -1;
        } 
    }  
    
    /**
     * check if customer service online is true
     * @param userName
     * @return 
     */
    public int checkIfCustServOnline(String userName) {
           
            //prepare and execute search query
        try{   
            sql = "SELECT COUNT(*) AS ROWCOUNT FROM IS2730.onlineChatCustServs WHERE user_name = ? and online = ?";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setString(1, userName); 
            ps.setBoolean(2, true);

            rs = ps.executeQuery();

            //check if query returns one valid result 
            if(rs.next())
		return rs.getInt("ROWCOUNT");
            else
                return 0;
            
        }catch (Exception e) {
                System.out.println(e.getMessage()) ;
                return -1;
        } 
    }  
    
    /**
     * add record of customer service to onlineChatCustServs table
     * @param userId
     * @param userName 
     */
    public void addOnlineCustServ(int userId, String userName) {
        try {
                    //prepare and execute search query              
                sql = "INSERT INTO IS2730.onlineChatCustServs (user_id, user_name, online) values (?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(sql);                                       
                
                ps.setInt(1, userId); 
                ps.setString(2, userName);
                ps.setBoolean(3, true);
                ps.executeUpdate();            
              
                } catch (SQLException e) {
                        e.printStackTrace();
                }		
    }
    
    
    /**
     * log message that sent from customer service
     * @param newMsg 
     */
    public void logMessage(ChatMsgBean newMsg) {
            
        try{
            //prepare and execute search query
            sql = "INSERT INTO IS2730.chatLog(msgFrom, msgTo, dateTime, msgContent) values (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setString(1, newMsg.getMsgFrom()); 
            ps.setString(2, newMsg.getMsgTo()); 
            ps.setTimestamp(3, new Timestamp(newMsg.getDateTime().getTime()));
            ps.setString(4, newMsg.getMsgContent()); 
            ps.executeUpdate();  
        } catch (SQLException e) {
                e.printStackTrace();
        }

            
    }  
    
    
   /**
    * set customer service as offline when logout
    * @param userName 
    */
    public void offlineCustServ(String userName) {
        try { 
            sql = "UPDATE IS2730.onlineChatCustServs SET online = ? where user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setBoolean(1, false);
            ps.setString(2, userName);
            ps.executeUpdate();  

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    /**
     * set customer service as online when logged in
     * @param userName 
     */
    public void setCustServOnline(String userName) {
        try { 
            sql = "UPDATE IS2730.onlineChatCustServs SET online = ? where user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setBoolean(1, true);
            ps.setString(2, userName);
            ps.executeUpdate();  

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
