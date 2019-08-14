package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetStatus extends AsyncRequestHandler{


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");

        // OF   Person person = (Person) request.getSesstion().getAttribute("user");
        String status = person.getStatus();

        return status;
    }
}

