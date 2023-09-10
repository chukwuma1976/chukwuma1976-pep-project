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
        if (messageDAO.getMessageById(id)!=null 
        && message.getMessage_text()!="" && message.getMessage_text().length()<255){
            messageDAO.updateMessageById(message, id);
            return messageDAO.getMessageById(id);
        } else return null;
    }  

    public List<Message> getMessagesByAccountId(int account_id) {
        return messageDAO.getMessagesByAccountId(account_id);
    }  
    
}
