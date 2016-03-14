/*
 * This class contains methods to get chat messages
 * @author Siwei Jiao
 */
package dataAccessObject;

import DbConnect.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.ChatMsgBean;

public class ChatMsgDao {
    private Connection connection;
        Statement st = null;
               
        String sql = "";
        
         public ChatMsgDao() {
            //connect to database and select the record
            connection = DbConnection.getConnection();	
        }
    
         /**
          * get recent 20 messages between customer and customer service
          * @param userName1
          * @param userName2
          * @return 
          */
    public List<ChatMsgBean> getMsgByUserNames(String userName1, String userName2) {
        List<ChatMsgBean> chatMsgs = new ArrayList<ChatMsgBean>();                    

        try{
            //prepare and execute search query
            sql = "select * from chatLog where (msgFrom = ? and msgTo = ?) or (msgFrom = ? and msgTo = ?) order by dateTime DESC FETCH FIRST 20 ROWS ONLY";
            PreparedStatement ps = connection.prepareStatement(sql) ;
                    ps.setString(1,userName1) ;
                    ps.setString(2, userName2);
                    ps.setString(3, userName2);
                    ps.setString(4, userName1);
                    ResultSet rs = ps.executeQuery() ;

            while (rs.next()) {

                ChatMsgBean chatMsg = new ChatMsgBean();
                chatMsg.setChatLog_id(rs.getInt("chatLog_id"));
                chatMsg.setMsgFrom(rs.getString("msgFrom"));
                chatMsg.setMsgTo(rs.getString("msgTo"));
                chatMsg.setDateTime(rs.getTimestamp("dateTime"));
                chatMsg.setMsgContent(rs.getString("msgContent"));
                
                chatMsgs.add(chatMsg);                
            }

        } catch (SQLException e) {
                e.printStackTrace();
        }

        return chatMsgs;
    } 
    
    /**
     * get all chat messages between customer and customer service
     * @param userName1
     * @param userName2
     * @return 
     */
    public List<ChatMsgBean> getChatHistory(String userName1, String userName2) {
        List<ChatMsgBean> chatHistory = new ArrayList<ChatMsgBean>();                    

        try{
            //prepare and execute search query
            sql = "select * from chatLog where (msgFrom = ? and msgTo = ?) or (msgFrom = ? and msgTo = ?) order by dateTime DESC";
            PreparedStatement ps = connection.prepareStatement(sql) ;
                    ps.setString(1,userName1) ;
                    ps.setString(2, userName2);
                    ps.setString(3, userName2);
                    ps.setString(4, userName1);
                    ResultSet rs = ps.executeQuery() ;

            while (rs.next()) {

                ChatMsgBean chatMsg = new ChatMsgBean();
                chatMsg.setChatLog_id(rs.getInt("chatLog_id"));
                chatMsg.setMsgFrom(rs.getString("msgFrom"));
                chatMsg.setMsgTo(rs.getString("msgTo"));
                chatMsg.setDateTime(rs.getTimestamp("dateTime"));
                chatMsg.setMsgContent(rs.getString("msgContent"));
                
                chatHistory.add(chatMsg);                
            }

        } catch (SQLException e) {
                e.printStackTrace();
        }

        return chatHistory;
    } 
}
