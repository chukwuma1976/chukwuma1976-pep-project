package DAO;
import java.sql.*;
import java.util.*;
import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public List<Message> getAllMessages() {
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getInt("time_posted_epoch");
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);

                messages.add(message);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return messages;
    }

    public Message addMessage(Message message) {
        Connection conn = ConnectionUtil.getConnection();
        Message newMessage = null;
        try {
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet rspkResultSet = ps.getGeneratedKeys();
            
            if (rspkResultSet.next() 
                && message.getMessage_text()!="" 
                && message.getMessage_text().length()<255){

                int message_id = rspkResultSet.getInt("message_id");
                newMessage = new Message(message_id, 
                                    message.getPosted_by(), 
                                    message.getMessage_text(), 
                                    message.getTime_posted_epoch());
                }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return newMessage;
    }

    public Message getMessageById(int id) {
        Connection conn = ConnectionUtil.getConnection();
        Message message = null;
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
                
                message = new Message(message_id, posted_by, message_text, time_posted_epoch);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return message;
    }

    public void deleteMessageById(int id) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        } 
    }    

    public void updateMessageById(Message message, int id) {
        Connection conn = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, message.getMessage_text());
            ps.setInt(2, id);

            ps.executeUpdate();


        }catch(SQLException e){
            e.printStackTrace();
        }
    }  

    public List<Message> getMessagesByAccountId(int account_id) {
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, account_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getInt("time_posted_epoch");
                Message message = new Message(message_id, posted_by, message_text, time_posted_epoch);

                messages.add(message);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return messages;
    }  
}
