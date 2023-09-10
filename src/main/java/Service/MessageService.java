package Service;

import DAO.MessageDAO;
import Model.Message;
import java.util.*;

public class MessageService {

    MessageDAO messageDAO;

    public MessageService (){
        messageDAO = new MessageDAO();
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    public Message addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    public Message getMessageById(int id) {
        return messageDAO.getMessageById(id);
    }

    public void deleteMessageById(int id) {
        if (messageDAO.getMessageById(id)!=null){
            messageDAO.deleteMessageById(id);
        } else return;
    }    

    public Message updateMessageById(Message message, int id) {
        if (messageDAO.getMessageById(id)!=null){
            return messageDAO.updateMessageById(message, id);
        } else return message;
    }  

    public List<Message> getMessagesByAccountId(int account_id) {
        return messageDAO.getMessagesByAccountId(account_id);
    }  
    
}
