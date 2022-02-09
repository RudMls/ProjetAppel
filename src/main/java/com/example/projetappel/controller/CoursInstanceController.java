package com.example.projetappel.controller;

import com.example.projetappel.dao.CoursInstanceDao;
import com.example.projetappel.model.CoursInstance;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CoursInstanceController", value = "/compte/cours-instance")
public class CoursInstanceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            Integer coursIntanceId = stringToInteger(id);
            if (coursIntanceId != null) {
                CoursInstanceDao coursInstanceDao = new CoursInstanceDao();
                CoursInstance coursInstance = coursInstanceDao.find(coursIntanceId);
                request.setAttribute("coursInstance", coursInstance);
                request.setAttribute("page", "cours-instance");
                request.getRequestDispatcher("/view/compte/index.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public Integer stringToInteger(String toParse) {
        Integer result;
        try {
            result = Integer.parseInt(toParse);
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }
}
