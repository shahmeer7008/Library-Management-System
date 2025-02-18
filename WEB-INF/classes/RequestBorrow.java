
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RequestBorrow extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null || session.getAttribute("role") == null) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please login to proceed.');");
            out.println("window.location.href = 'index.jsp';");
            out.println("</script>");
            return;
        }
        String role = (String) session.getAttribute("role");
        if (!"visitor".equals(role)) {
            response.setContentType("text/html");
            PrintWriter o = response.getWriter();
            o.println("<script type='text/javascript'>");
            o.println("alert('Access denied. You do not have the required permissions.');");
            o.println("window.location.href = 'index.jsp';");
            o.println("</script>");
            return;
        }
        String username = request.getParameter("username");
        String BookName = request.getParameter("BookName");
        String status = "pending";
        requests r = new requests(username, BookName, status);
        Dao dao = new Dao();
        int ans = dao.RequestBorrow(r);
        if (ans == 1) {

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Your request has been sent.');");
            out.println("window.location.href = 'visitor.jsp';");
            out.println("</script>");
        } else {
            out.println("<h1>Request couldn't sent</h1>");
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
