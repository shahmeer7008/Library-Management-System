
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Update extends HttpServlet {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        if (!"librarian".equals(role)) {
            response.setContentType("text/html");
            PrintWriter o = response.getWriter();
            o.println("<script type='text/javascript'>");
            o.println("alert('Access denied. You do not have the required permissions.');");
            o.println("window.location.href = 'index.jsp';");
            o.println("</script>");
            return;
        }
        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");

        String oldname = request.getParameter("oldname");
        String newname = request.getParameter("newname");
        String nauthname = request.getParameter("nauthname");
        Dao dao = new Dao();
        int ans = dao.update(oldname, newname, nauthname);
        if (ans > 0) {
            out.println("<h3>Updated Successfully</h3>");
        } else
            out.println("<h3>Couldn't update</h3>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
