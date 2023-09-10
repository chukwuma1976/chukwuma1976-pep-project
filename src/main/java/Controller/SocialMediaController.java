package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    //service objects
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();

        //user registration controller route
        app.post("/register", this::registerHandler);

        //login controller route
        app.post("/login", this::loginHandler);

        //message controllers routes
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::addMessageHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIdHandler);

        return app;
    }

    /**
     * These are my handlers, the names speak for themselves
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account user = mapper.readValue(context.body(), Account.class);
        Account newUser = accountService.addUser(user);
        if(newUser!=null){
            context.json(newUser);
        }else{
            context.status(400);
        }

    }

    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account user = mapper.readValue(context.body(), Account.class);
        Account loginUser = accountService.loginUser(user);
        if(loginUser !=null){
            context.json(loginUser);
        }else{
            context.status(401);
        }
    }

    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    private void addMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.addMessage(message);
        if (newMessage != null){
            context.json(newMessage);
        } else {
            context.status(400);
        };
    }

    private void getMessageByIdHandler(Context context) {
        int id = Integer.parseInt(context.pathParam("message_id"));
        Message message = messageService.getMessageById(id);
        if (message != null){
            context.json(message);
        } else {
            context.json("");
            context.status(200);
        }
    }

    private void deleteMessageByIdHandler(Context context) {
        int id = Integer.parseInt(context.pathParam("message_id"));
        if (messageService.getMessageById(id)!=null){
            context.json(messageService.getMessageById(id));
            messageService.deleteMessageById(id);
        } else {
            context.json("");
        }
    }    

    private void updateMessageByIdHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int id = Integer.parseInt(context.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageById(message, id);
        if (updatedMessage!=null){           
            context.json(updatedMessage);
        } else {
            context.status(400);
        }
    }  

    private void getMessagesByAccountIdHandler(Context context) {
        int id = Integer.parseInt(context.pathParam("account_id"));
        List<Message> messages = messageService.getMessagesByAccountId(id);
        context.json(messages);
    }  

}