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
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KALP
 */
public class InsertCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet InsertCategoryServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            String catid = request.getParameter("txtcatid");
            String catcode = request.getParameter("txtcatcode");
            String catname = request.getParameter("txtcatname");
            
            Connection connection = null;
            
           
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "root");

                if(catid==null)
                {
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into category(CategoryCode,CategoryName) values(?,?)");
                    preparedStatement.setString(1, catcode);
                    preparedStatement.setString(2, catname);
                    int execRow = preparedStatement.executeUpdate();

                    if (execRow > 0) 
                    {
                        response.sendRedirect("CategoryServlet");
                    } 
                    else 
                    {
                        response.sendRedirect("CategoryInsertServlet");
                        out.println("Something goes to wrong !!");
                    }
                }
                else
                {
                    PreparedStatement preparedStatement1 = connection.prepareStatement("update category set categoryCode=?, categoryName=? where categoryId=?");
                     preparedStatement1.setString(1, catcode);
                    preparedStatement1.setString(2, catname);
                    preparedStatement1.setString(3, catid);
                    int execRow = preparedStatement1.executeUpdate();

                    if (execRow > 0) 
                    {
                        response.sendRedirect("CategoryServlet");
                    } 
                    else 
                    {
                        response.sendRedirect("CategoryInsertServlet");
                        out.println("Something goes to wrong !!");
                    }
                }
                
            }
            catch(Exception e)
            {
                out.println(e);
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
