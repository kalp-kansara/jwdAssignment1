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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KALP
 */
public class InsertingCategory extends HttpServlet {

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
            out.println("<title>Servlet InsertingCategory</title>");
            out.println("</head>");
            out.println("<body>");

            String catId = request.getParameter("catId");
//            String catcode = request.getParameter("txtcatcode");
//            String catname = request.getParameter("txtcatname");

            int CategoryId = 0;
            String CategoryName = "";
            String CategoryCode = "";

            Connection connection = null;
            
            if(catId != null)
            {
                try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost/computershop", "root", "root");
                Statement statement = connection.createStatement();
                
                ResultSet resultSet = statement.executeQuery("select * from category where categoryid = "+catId+"");

                while (resultSet.next()) {
                    CategoryId = resultSet.getInt("CategoryId");
                    CategoryCode = resultSet.getString("CategoryCode");
                    CategoryName = resultSet.getString("CategoryName");
                }
                
                }
                catch(Exception e)
                {
                    out.println(e);
                }
            }
            
            out.println("<form action='InsertCategoryServlet'  method='post'>");
            out.println("<table align='center'>");
            
            if(catId==null)
            {
                out.println("<tr>");
                out.println("<td>CATEGORY CODE : </td>");
                out.println("<td><input type='text' name='txtcatcode'></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>CATEGORY NAME : </td>");
                out.println("<td><input type='text' name='txtcatname'></td>");
                out.println("</tr>");
            }
            else
            {
                out.println("<tr>");
                out.println("<td><input type='text' name='txtcatid' value=' "+CategoryId+" ' hidden=''></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>CATEGORY CODE : </td>");
                out.println("<td><input type='text' name='txtcatcode' value=' "+CategoryCode+" '></td>");
                out.println("</tr>");
                
                out.println("<tr>");
                out.println("<td>CATEGORY NAME : </td>");
                out.println("<td><input type='text' name='txtcatname' value=' "+CategoryName+" '></td>");
                out.println("</tr>");
            }
            
            out.println("<tr>");
            out.println("<td>");
            out.println("<input type='submit' value='submit' name='submit'>");
            out.println("</td>");
            out.println("</tr>");
            
            out.println("</table>");
            out.println("</form>");
            

//                if (catId == null) 
//                {

//                    if (CategoryName.equals(catname) || CategoryCode.equals(catcode)) 
//                    {
//                        RequestDispatcher rd  = request.getRequestDispatcher("CategoryInsertServlet");
//                        rd.forward(request, response);
//                         out.println("Category Is already In It !!");
//                    }
//                    else
//                    {
//                        PreparedStatement preparedStatement1 = connection.prepareStatement("insert into category(CategoryCode,CategoryName) values(?,?)");
//                        preparedStatement1.setString(1, catcode);
//                        preparedStatement1.setString(2, catname);
//                        int execRow = preparedStatement1.executeUpdate();
//
//                        if (execRow > 0) 
//                        {
//                            response.sendRedirect("CategoryServlet");
//                        }
//                        else
//                        {
//                            out.println("Something goes to wrong !!");
//                            response.sendRedirect("CategoryInsertServlet");
//                        }
//                    }
//                }
//                else
//                {
//                    
//                }
//                }
//            } 
//            catch (Exception e) 
//            {
//                out.println(e);
//            }

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
