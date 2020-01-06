/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.course.hibernate.servlets;

import java.io.*;

import java.util.List;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;

import org.course.hibernate.ejb3.persistencia.simple.User;
import org.course.hibernate.ejb3.sesion.stateless.PersonaFacadeLocal;

/**
 *
 * @author user
 */
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -2071061085534715156L;
	
	@EJB
    private PersonaFacadeLocal personaFacade;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            for (int i = 0; i < 10; i++) {
                User p = new User("User " + i);
                personaFacade.create(p);
            }
            List<User> personas = personaFacade.findAll();
            for (User persona : personas) {
                out.println(persona);
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
