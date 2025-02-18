


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SignUp extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Dao dao = new Dao();
        boolean userExists = dao.checkUserExists(username);

        if (userExists) {
            request.setAttribute("errorMessage", "An account with this email already exists. Please log in or use a different email.");
            request.getRequestDispatcher("SignUp.jsp").forward(request, response);
        } else {
            Persons p = new Persons(username, password, "visitor");
            int ans = dao.SignUp(p);
            if (ans > 0) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("errorMessage", "Account creation failed. Please try again.");
                request.getRequestDispatcher("SignUp.jsp").forward(request, response);
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

}
