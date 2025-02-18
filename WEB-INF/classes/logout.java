
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Date;

public class logout extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String message = "Session not found";

        if (session != null) {
            String sessionId = session.getId();
            Date creationTime = new Date(session.getCreationTime());
            Date lastAccessedTime = new Date(session.getLastAccessedTime());

            message = "Session has expired. Details:\\n" +
                      "Session ID: " + sessionId + "\\n" +
                      "Creation Time: " + creationTime + "\\n" +
                      "Last Accessed Time: " + lastAccessedTime;

            session.invalidate();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<script>");
        out.println("alert('" + escapeForJavaScript(message) + "');");
        out.println("window.location.href = 'index.jsp';");
        out.println("</script>");
        out.println("</head>");
        out.println("<body></body>");
        out.println("</html>");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private String escapeForJavaScript(String message) {
        return message.replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r");
    }
}
