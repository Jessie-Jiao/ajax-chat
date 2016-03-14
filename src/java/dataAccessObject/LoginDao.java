/*
 * This class contains methods  to check user credentials and get user data
 * @author Siwei Jiao
 */
package dataAccessObject;

import java.sql.*;

import DbConnect.DbConnection;

import bean.CustomerBean;
import bean.CustServBean;

public class LoginDao {
        private Connection connection;
        Statement st = null;
        ResultSet rs = null;
        
       
        String sql = "";
        
        public LoginDao() {
            //connect to database and select the record
            connection = DbConnection.getConnection();	
        }
        
        /**
         * verify customer credential
         * @param userName
         * @param password
         * @return
         * @throws SQLException 
         */
        public boolean validateCustomer(String userName, String password) throws SQLException{
            int count;
            boolean valid = false;
            
            //prepare and execute search query
            sql = "SELECT COUNT(*) AS ROWCOUNT FROM IS2730.customers WHERE USER_NAME=? AND PASSWORD=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();

                
            rs.next();
            count = rs.getInt("ROWCOUNT");
            rs.close();

            if(count == 1)
                valid = true;
            
            return valid; 
        }
        
        /**
         * get customer data by name
         * @param userName
         * @return 
         */
        public CustomerBean getCustomerByName(String userName) {
            CustomerBean customer = new CustomerBean();
            try {
                    //prepare and execute search query
                sql = "SELECT * FROM IS2730.customers WHERE USER_NAME=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,userName);              
                rs = ps.executeQuery();
                
                //check if query returns one valid result            
                while(rs.next()) {
                    customer.setCustomer_id(rs.getInt("customer_id"));
                    customer.setUser_name(rs.getString("user_name"));
                    customer.setFirst_name(rs.getString("first_name"));
                    customer.setLast_name(rs.getString("last_name"));               
                }
           
            rs.close();
            ps.close();
             
                              
            } catch (SQLException e) {
                    e.printStackTrace();
            }

                return customer;
        }
        
        /**
         * verify customer service credential
         * @param userName
         * @param password
         * @return
         * @throws SQLException 
         */
        public boolean validateCustServ(String userName, String password) throws SQLException{
            int count;
            boolean valid = false;
            
            //prepare and execute search query
            sql = "SELECT COUNT(*) AS ROWCOUNT FROM IS2730.customerServices WHERE USER_NAME=? AND PASSWORD=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();

                
            rs.next();
            count = rs.getInt("ROWCOUNT");
            rs.close();

            if(count == 1)
                valid = true;
            
            return valid; 
        }
        
        /**
         * get customer service data by name
         * @param userName
         * @return 
         */
        public CustServBean getCustServByName(String userName) {
            CustServBean custServ = new CustServBean();
            try {
                    //prepare and execute search query
                sql = "SELECT * FROM IS2730.customerServices WHERE USER_NAME=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,userName);              
                rs = ps.executeQuery();
                
                //check if query returns one valid result            
                while(rs.next()) {
                    custServ.setCustServ_id(rs.getInt("custServ_id"));
                    custServ.setUser_name(rs.getString("user_name"));
                    custServ.setFirst_name(rs.getString("first_name"));
                    custServ.setLast_name(rs.getString("last_name"));               
                }
           
            rs.close();
            ps.close();
             
                             
            } catch (SQLException e) {
                    e.printStackTrace();
            }

                return custServ;
        }
}
