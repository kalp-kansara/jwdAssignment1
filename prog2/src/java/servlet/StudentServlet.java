/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KALP
 */
public class StudentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            String errors = "<ul>";
            
            Enumeration e = request.getParameterNames();
            while(e.hasMoreElements()) 
            {
                String ParamName = (String)e.nextElement();
                String ParamValue = request.getParameter(ParamName);
                if(ParamValue.isEmpty()) 
                {
                    errors += "<li>" + ParamName+" is required.</li>";
                }
            }
            
            if(request.getParameter("txtsname") == null)
            {
                errors +="<li>Enter Student Name</li>";
            }
            
            if(request.getParameter("rdb") == null) 
            {
                errors += "<li>You have to select Gender</li>";
            }
            
            if(request.getParameter("languages") == null) {
                errors += "<li>Please select atleast one Language you know</li>";
            }
            
            if(request.getParameter("txtphno") == null)
            {
                errors += "<li>Enter Phone Number</li>";
            }
            
            
            if(errors=="<ul>") {
                String name = request.getParameter("txtsname");
                String phno = request.getParameter("txtphno");
               
                if(name.length() <= 8) 
                {
                    errors += "<li>Full name must be more than 8 characters</li>";
                }
            }
            if(errors=="<ul>") 
            {
                out.println("Values are:");
                out.println("<dl>");
                e = request.getParameterNames();
                while(e.hasMoreElements()) {
                    String ParamName = (String)e.nextElement();
                    String ParamValue = request.getParameter(ParamName);
                    out.println("<dt>"+ParamName+"</dt>");
                    out.println("<dd>"+ParamValue+"</dd>");
                }
                out.println("</dl>");
            } 
            else 
            {
                errors = errors+"</ul>";
                out.println("<span style='color:red'>"+errors+"</span>");
                RequestDispatcher rd = request.getRequestDispatcher("StudentRegistration.html");
                rd.include(request,response);   
            }
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
