package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddFriend extends AsyncRequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String newFriendEmail = request.getParameter("newFriend");

        PersonService personService = getPersonService();
        Person newFriend = personService.getPerson(newFriendEmail);

        HttpSession session = request.getSession(); //Ophalen user

        Person user = (Person) session.getAttribute("user"); // Eigen user


        user.addFriend(newFriend);
        newFriend.addFriend(user);
        return null;
    }
}
