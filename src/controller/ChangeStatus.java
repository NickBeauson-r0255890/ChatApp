package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeStatus extends AsyncRequestHandler {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String status = request.getParameter("status");
        HttpSession session = request.getSession(); //Ophalen user

        Person person = (Person) session.getAttribute("user");
        person.setStatus(status);

        return status;
    }
}
