
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ApproveRejectRequestServlet extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = null;
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null|| session.getAttribute("role") == null) {
            response.setContentType("text/html");
            out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("alert('Please log in to proceed.');");
            out.println("window.location.href = 'index.jsp';");
            out.println("</script>");
            return;
        }
        String role = (String) session.getAttribute("role");
        if (!"librarian".equals(role)) {
            response.setContentType("text/html");
            PrintWriter o = response.getWriter();
            o.println("<script type='text/javascript'>");
            o.println("alert('Access denied. You do not have the required permissions.');");
            o.println("window.location.href = 'index.jsp';");
            o.println("</script>");
            return;
        }
        response.setContentType("text/html");
        out = response.getWriter();

        out.println("<html><body>");
        Dao dao = new Dao();
        ArrayList<requests> arr = dao.ApproveRejectRequest();
        if (!arr.isEmpty()){
        out.println("<h3>Updated Requests:</h3>");
        out.println("<table border='1'>");
        out.println("<tr><th>Username</th><th>Book Name</th><th>Status</th></tr>");
            for (requests r : arr) {

                out.println("<tr>");
                out.println("<td>" + r.username + "</td>");
                out.println("<td>" + r.BookName + "</td>");
                out.println("<td>" + r.status + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
        else  out.println("<h3>No pending Request</h3>");
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
