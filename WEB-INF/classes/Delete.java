



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class Delete extends HttpServlet {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null|| session.getAttribute("role") == null) {
            out.println("<html>");
            out.println("<head><title>Session Expired</title></head>");
            out.println("<body bgcolor=\"#ffffff\">");
            out.println("<script type='text/javascript'>");
            out.println("alert('Please log in to proceed.');");
            out.println("window.location.href = 'index.jsp';");
            out.println("</script>");
            out.println("</body></html>");
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
        String bname = request.getParameter("bname");

        out.println("<html>");
        out.println("<head><title>Response</title></head>");
        out.println("<body bgcolor=\"#ffffff\">");
        out.println("<h3>Book Name: " + bname + "</h3>");
        out.println("<body bgcolor=\"#ffffff\">");

       Dao dao=new Dao();
       int ans=dao.delete(bname);
       if(ans>0)
       {
        out.println("<h3>Deleted Successfully</h3>");
       }
       else  out.println("<h3>Couldn't deleted</h3>");

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        process(request, response);
    }
}
