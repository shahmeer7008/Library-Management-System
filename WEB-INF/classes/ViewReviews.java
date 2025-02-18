
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class ViewReviews extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || session.getAttribute("role") == null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h3>Please login to proceed.</h3>");
            out.println("<a href='index.jsp'>Login</a>");
            out.println("</body></html>");
            return;
        }
        String role = (String) session.getAttribute("role");
        if (!"visitor".equals(role)) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("alert('Access denied. You do not have the required permissions.');");
            out.println("window.location.href = 'index.jsp';");
            out.println("</script>");
            return;
        }
        String BookName = request.getParameter("BookName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Reviews for Book: " + BookName + "</h1>");
        out.println("<table border='1'><tr><th>Username</th><th>Review</th></tr>");
        HashMap<String, List<String>> h = new Dao().viewReviews(BookName);
        if (h.isEmpty()) {
            out.println("No Reviews");
        } else {
            int i = 1;
            for (String key : h.keySet()) {

                out.println("<tr><td>" + key + "</td><td>");

                List<String> reviews = h.get(key);
                for (String review : reviews) {
                    out.println(i + ". " + review + ".....<br>");
                    i++;
                }

                out.println("</td></tr>");
            }
            out.println("</table>");
            out.println("<br><a href='logout'>Logout</a>");
            out.println("</body></html>");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }
}
