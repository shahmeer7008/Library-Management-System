



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class viewStatus extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String BookName = request.getParameter("BookName");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (username == null || BookName == null || username.trim().isEmpty() || BookName.trim().isEmpty()) {
            out.println("<html><body>");
            out.println("<h3>Invalid input. Please check your username and book name.</h3>");
            out.println("</body></html>");
            return;
        }


        
           Dao dao=new Dao();
           String s=dao.viewStatus(username,BookName);
           out.println("<html><body>");
           if(s.equals("")){
            out.println("<h3>No record found for the request of user " + username + ".</h3>");
        }
        else {
            
                 out.println("<h3>Status of request for user " + username + ":</h3>");
                out.println("<p>" + s + "</p>");

        }

            out.println("<br><a href='logout'>Logout</a>");
            out.println("</body></html>");
            
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
            {
                process(request, response);
            }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
            {
                process(request, response);
            }
}
