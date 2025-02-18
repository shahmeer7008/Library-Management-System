import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Insert extends HttpServlet {

    
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null|| session.getAttribute("role") == null) {
            response.sendRedirect("index.jsp"); 
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
        PrintWriter out = response.getWriter();

        String bname = request.getParameter("bname");
        String author = request.getParameter("aname");
        Dao d = new Dao();
        books b = new books(bname, author, "A");
        int ans = d.insert(b);

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor='#ffffff'>");
        if (ans == 1) {
            out.println("<h1>Insertion successful</h1>");
        } else {
            out.println("<h1>Record could not be inserted.</h1>");
        }
        out.println("</body></html>");
    }

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       process( request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       process( request, response);
    }
}
