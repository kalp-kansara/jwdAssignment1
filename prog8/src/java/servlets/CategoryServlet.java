/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KALP
 */
public class CategoryServlet extends HttpServlet {

    Connection connection;
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
            out.println("<title>Servlet CategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
                try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from category");
                
                
                out.println("<table align='center' border=1 cellpadding='5px'>");
                out.println("<tr>");
                out.println("<td colspan = 4><a href='InsertingCategory' align='center'>ADD CATEGORY</a></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<th>CATEGORY CODE</th>");
                out.println("<th>CATEGORY NAME</th>");
                out.println("<th>UPDATE</th>");
                out.println("<th>DELETE</th>");
                out.println("</th>");
                out.println("</tr>");
                
                while (resultSet.next()) 
                {
                    
                    out.println("<tr>");
                    out.println("<td>" + resultSet.getString(2) + "</td>");
                    out.println("<td>" + resultSet.getString(3) + "</td>");
                    out.println("<td><a href='InsertingCategory?catId="+resultSet.getString(1)+" '>UPDATE</a></td>");
                    out.println("<td><a href='DeleteCategory?catId="+resultSet.getString(1)+"'>DELETE</a></td>");
                    out.println("</tr>");
                    
                    
                }

            } catch (Exception e) {
            }

            out.println("</table>");
            out.println("</form>");

            
            
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
