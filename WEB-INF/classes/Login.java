


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Login extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Dao dao = new Dao();
            String actualRole = dao.Login(new Persons(username, password, role));

            if (actualRole != null) {
                if (role.equals(actualRole)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    session.setAttribute("role", actualRole);
                    if ("librarian".equals(actualRole)) {
                        if ("admin@gmail.com".equals(username) || "raza@gmail.com".equals(username)){
                        response.sendRedirect("librarian.jsp");
                        }
                    } else if ("visitor".equals(actualRole)) {
                        response.sendRedirect("visitor.jsp");
                    }
                } else {
                    response.sendRedirect("index.jsp?error=Invalid Role Selection");
                }
            } else {
                response.sendRedirect("index.jsp?error=Invalid Credentials");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
