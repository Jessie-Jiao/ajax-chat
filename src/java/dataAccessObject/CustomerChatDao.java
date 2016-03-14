/*
 * This class contains methods to manage chat for customer
 * @author Siwei Jiao
 */
package dataAccessObject;

import DbConnect.DbConnection;
import java.sql.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.ChatMsgBean;
import bean.CustomerBean;

public class CustomerChatDao {
    
    private Connection connection;
        Statement st = null;
        ResultSet rs = null;
               
        String sql = "";
        
         public CustomerChatDao() {
            //connect to database and select the record
            connection = DbConnection.getConnection();	
        }
    
    /**
     * count the number of online customers
     * @return 
     */
    public int countOnlineCustomer(){
		
        try{

                sql = "SELECT COUNT(*) AS ROWCOUNT FROM IS2730.onlineChatCustomers WHERE online=?" ;
                PreparedStatement ps = connection.prepareStatement(sql) ;
                ps.setBoolean(1, true) ;
                rs = ps.executeQuery() ;
                if(rs.next())
                        return rs.getInt("ROWCOUNT") ;
                else
                        return 0 ;
        }catch (Exception e) {
                System.out.println(e.getMessage()) ;
                return -1 ;
        }
	}
    
    /**
     * check if customer exists in the onlineChatCustomers table
     * @param userName
     * @return 
     */
    public int checkOnlineExist(String userName) {
           
            //prepare and execute search query
        try{   
            sql = "SELECT COUNT(*) AS ROWCOUNT FROM IS2730.onlineChatCustomers WHERE user_name = ?";
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
     * add record of customer to onlineChatCustomers table
     * @param userId
     * @param userName 
     */
    public void addOnlineCustomer(int userId, String userName) {
        try {
                    //prepare and execute search query              
                sql = "INSERT INTO IS2730.onlineChatCustomers (user_id, user_name, online) values (?, ?, ?)";
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
     * get online customer list
     * @return 
     */
    public List<CustomerBean> getOnlineCustlist() {
        List<CustomerBean> customers = new ArrayList<CustomerBean>();                    

        try{
            //prepare and execute search query
            sql = "Select user_id, user_name FROM IS2730.onlineChatCustomers where online = ?";

            PreparedStatement ps = connection.prepareStatement(sql) ;
                    ps.setBoolean(1,true) ;                
                    rs = ps.executeQuery() ;

            while (rs.next()) {

                CustomerBean cust = new CustomerBean();
                cust.setCustomer_id(rs.getInt("user_id"));
                cust.setUser_name(rs.getString("user_name"));         
                customers.add(cust);                
            }

            rs.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }

        return customers;
    } 
      
    /**
     * log message that sent from customer
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
    * set customer as offline when logout
    * @param userName 
    */
    public void offlineCustomer(String userName) {
        try { 
            sql = "UPDATE IS2730.onlineChatCustomers SET online = ? where user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setBoolean(1, false);
            ps.setString(2, userName);
            ps.executeUpdate();  

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    /**
     * set customer as online when logged in
     * @param userName 
     */
    public void setCustomerOnline(String userName) {
        try { 
            sql = "UPDATE IS2730.onlineChatCustomers SET online = ? where user_name = ?";
            PreparedStatement ps = connection.prepareStatement(sql);  
            ps.setBoolean(1, true);
            ps.setString(2, userName);
            ps.executeUpdate();  

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
        
}
