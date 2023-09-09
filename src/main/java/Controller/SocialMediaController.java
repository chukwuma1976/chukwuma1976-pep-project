package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;

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

    // future service objects
    // AccountService accountService;
    // MessageService messageService;

    public SocialMediaController(){
        // accountService = new AccountService();
        // messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);

        //user registration controller route
        app.post("/register", this::registerHandler);

        //login controller route
        app.post("/login", this::loginHandler);

        //message controllers routes
        app.get("/messages", this::getAllMessagesHandler);
        app.post("/messages", this::addMessageHandler);
        app.get("/messages{message_id}", this::getMessageByIdHandler);
        app.delete("/messages{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages{message_id}", this::updateMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIDHandler);

        return app;
    }

    /**
     * These are my handlers, the names speak for themselves
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context context) {
        // ObjectMapper mapper = new ObjectMapper();
        // Account user = mapper.readValue(context.body(), Account.class);
        // Account newUser = accountService.addUser(user);
        // if(newUser!=null){
        //     context.json(mapper.writeValueAsString(newUser));
        // }else{
        //     context.status(400);
        // }

    }

    private void loginHandler(Context context) {
        // Account user = new Account(context.body());
        // Account loginUser = accountService.loginUser(user);
        // if(loginUser){
        //     context.status(200);
        // }else{
        //     context.status(400);
        // }
    }

    private void getAllMessagesHandler(Context context) {
        // List<Messages> messages = messageService.getAllMessages()
        // context.json(messages);
    }

    private void addMessageHandler(Context context) {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message newMessage = messageService.addMessage(context.body(), Message.class);
        if (newMessage != null){
            context.json(newMessage);
        } else {
            context.status(400);
        };
    }

    private void getMessageByIdHandler(Context context) {
        // Message message = messageService.getMessageById(context.pathParam("message_id"));
        // context.json(message);
    }

    private void deleteMessageByIdHandler(Context context) {
        // if (messageService.getMessageById(context.pathParam("message_id"))!=null){
        //     messageService.deleteById(context.pathParam("message_id"));
        //     context.status(200);
        // } else {
            // context.result();
        // }
    }    

    private void updateMessageByIdHandler(Context context) {
        // if (messageService.getMessageById(context.pathParam("message_id"))!=null){
                // int id = context.pathParam("message_id");
        //     messageService.updateMessage(id);
        //     context.json(messageService.getMessageById(id));
        // } else {
        //     context.status(400);
        // }
    }  

    private void getMessagesByAccountIDHandler(Context context) {
        // List<Messages> messages = messageService.getMessagesByAccountID();
        // context.json(messages);
    }  

}