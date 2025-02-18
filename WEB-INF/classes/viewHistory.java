
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class viewHistory extends HttpServlet {
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
        String username = request.getParameter("username");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Your Borrow History</h1>");
        out.println("<table border='1'><tr><th>Book Name</th><th>Borrow Date</th><th>Due Date</th></tr>");
        Dao dao = new Dao();
        ArrayList<borrower> a = dao.viewHistory(username);
        if (a.isEmpty()) {
            out.println("<p>No records .</p>");
        } else {
            for (borrower b : a) {
                out.println("<tr><td>" 
                        + b.BookName + "</td><td>"
                        + b.BorrowDate + "</td><td>"
                        + b.DueDate + "</td></tr>");
            }

            out.println("</table>");
        }
        out.println("<br><a href='logout'>Logout</a>");
        out.println("</body></html>");

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
