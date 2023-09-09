// import java.util.List;

import Controller.SocialMediaController;
// import Model.Message;
// import Service.MessageService;
import io.javalin.Javalin;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        SocialMediaController controller = new SocialMediaController();
        Javalin app = controller.startAPI();
        app.start(8080);

        // MessageService message = new MessageService();
        // List<Message> messages = message.getAllMessages();
        // Message newMess = new Message(3,"This is another message", 124294202);
        // message.addMessage(newMess);
        // System.out.println("New message: " + newMess.toString());
        // for (Message mess: messages){
        //     System.out.println(mess.toString());
        // }
        // Message edited = new Message(5,"This is an edited message", 124294202);
        // message.updateMessageById(edited, 2);
        // System.out.println("updated message: " + newMess.toString());
    }
}
