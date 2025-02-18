

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class viewBooks extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null|| session.getAttribute("role") == null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Please login to proceed.</h3>");
            out.println("<a href='index.jsp'>Login</a>");
            out.println("</body></html>");
            return;
        }
        String role = (String) session.getAttribute("role");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

            out.println("<html><body>");
            out.println("<h1>Available Books</h1>");
    
            Dao dao=new Dao();
            ArrayList<books>a=dao.viewBooks();
            
        
            if (a.isEmpty()) {
                out.println("<p>No book records available.</p>");
            } else {
                out.println("<table border='1'><tr><th>Book Name</th><th>Author</th><th>Status</th></tr>");
                for (books b : a) {
                    out.println("<tr><td>" + b.BookName + "</td><td>" + b.Author + "</td><td>" + b.BookStatus + "</td></tr>");
                }
                out.println("</table>");
            }
    
            out.println("<br><a href='logout'>Logout</a>");
            out.println("</body></html>");


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
