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
            PreparedStatement ps = conn.prepareStatement(sql);

            String username = user.getUsername();
            String password = user.getPassword();
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (ps.execute() && password.length() > 4 && username.length() > 0){
                int account_id = rs.getInt("account_id");
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

            String username = user.getUsername();
            String password = user.getPassword();
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            int account_id = rs.getInt("account_id");
            loggedUser = new Account(account_id, username, password);

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return loggedUser;
    }
}
