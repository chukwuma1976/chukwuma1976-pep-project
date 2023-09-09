package DAO;

import Model.Account;
import java.sql.*;
import Util.ConnectionUtil;

public class AccountDAO {
    
    public Account addUser(Account user) {
        Connection conn = ConnectionUtil.getConnection();
        Account newUser = null;

        try{
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            String username = user.getUsername();
            String password = user.getPassword();
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            ResultSet pkeyResultSet = ps.getGeneratedKeys();

            if (pkeyResultSet.next()){
                int account_id = pkeyResultSet.getInt(1);
                newUser = new Account(account_id, username, password);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return newUser;
    }

    public Account loginUser(Account user) {
        Connection conn = ConnectionUtil.getConnection();
        Account loggedUser = null;

        try{
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
           
           
            loggedUser = new Account(rs.getInt("account_id"), 
                                    rs.getString("username"), 
                                    rs.getString("password"));
            System.out.println("logged user"+loggedUser.toString());

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return loggedUser;
    }
}
