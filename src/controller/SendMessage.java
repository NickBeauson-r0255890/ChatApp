package controller;

import domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendMessage extends AsyncRequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response){

        ConversationService conversationModel = getConversationService();
        PersonService model = getPersonService();

        //friend
        String friendName = request.getParameter("userName");
        Person friend = model.getPerson(friendName.toLowerCase() + "@ucll.be");
        //idealiter zou de id (mail) van de friend worden doorgestuurd aangezien dezelfde
        //naam meerdere keren kan voorkomen

        //user
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");

        //Message
        String messageString = request.getParameter("message");
        Message message = new Message(user,messageString);

        //GetConversation
        Conversation conversation = null;
        boolean test = conversationModel.checkConversationAlreadyExists(user,friend);
        if(!conversationModel.checkConversationAlreadyExists(user,friend)){
            System.out.println("Conversation for " + user.getFirstName() + " & " + friend.getFirstName() + " added");
            conversationModel.addConversation(user,friend);
        }
        conversation = conversationModel.getConversation(user,friend);

        //add Message to conversation
        conversation.addMessageToConversation(message);
        System.out.println("Message added to conversation from " + user.getFirstName() + " & " + friend.getFirstName());

        System.out.println(message.getPerson().getFirstName() + ": " + message.getMessage());
        return null;
    }
}
