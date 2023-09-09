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
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ResultSet rs = ps.executeQuery();
            
            int message_id = rs.getInt("message_id");
            int posted_by = rs.getInt("posted_by");
            String message_text = rs.getString("message_text");
            long time_posted_epoch = rs.getLong("time_posted_epoch");

            newMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);

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
            
            int message_id = rs.getInt("message_id");
            int posted_by = rs.getInt("posted_by");
            String message_text = rs.getString("message_text");
            long time_posted_epoch = rs.getInt("time_posted_epoch");
            
            message = new Message(message_id, posted_by, message_text, time_posted_epoch);

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

    public Message updateMessageById(Message message, int id) {
        Connection conn = ConnectionUtil.getConnection();
        Message updatedMessage = null;
        try {
            String sql = "UPDATE message SET posted_by=?, message_text=?, time_posted_epoch=? WHERE message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.setInt(4, message.getPosted_by());
            ResultSet rs = ps.executeQuery();
            
            int message_id = rs.getInt("message_id");
            int posted_by = rs.getInt("posted_by");
            String message_text = rs.getString("message_text");
            long time_posted_epoch = rs.getInt("time_posted_epoch");

            updatedMessage = new Message(message_id, posted_by, message_text, time_posted_epoch);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return updatedMessage;
    }  

    public List<Message> getMessagesByAccountId(Long account_id) {
        Connection conn = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, account_id);
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
