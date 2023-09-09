package Controller;

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
    // AccountService accountService = new AccountService();
    // MessageService messageService = new MessageService();

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

        // app.start(8080);
        return app;
    }

    /**
     * These are my handlers, the names speak for themselves
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void registerHandler(Context context) {
        context.json("sample text");
    }

    private void loginHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesHandler(Context context) {
        context.json("sample text");
    }

    private void addMessageHandler(Context context) {
        context.json("sample text");
    }

    private void getMessageByIdHandler(Context context) {
        context.json("sample text");
    }

    private void deleteMessageByIdHandler(Context context) {
        context.json("sample text");
    }    

    private void updateMessageByIdHandler(Context context) {
        context.json("sample text");
    }  

    private void getMessagesByAccountIDHandler(Context context) {
        context.json("sample text");
    }  

}