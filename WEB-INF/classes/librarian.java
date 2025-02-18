


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class librarian extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null|| session.getAttribute("role") == null) {
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
        String action = request.getParameter("action");

        switch (action) {
            case "Insert":
                request.getRequestDispatcher("Insert.jsp").forward(request, response);
                break;
            case "delete":
                request.getRequestDispatcher("delete.jsp").forward(request, response);
                break;
            case "update":
                request.getRequestDispatcher("update.jsp").forward(request, response);
                break;
            case "ApproveRejectRequestServlet":
                request.getRequestDispatcher("ApproveRejectRequestServlet").forward(request, response);
                break;
            case "viewBooks":
                request.getRequestDispatcher("viewBooks").forward(request, response);
                break;
            case "logout":
                request.getRequestDispatcher("logout").forward(request, response);
                break;
            default:
                response.sendRedirect("librarian.jsp");
        }

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

}
