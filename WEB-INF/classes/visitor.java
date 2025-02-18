
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class visitor extends HttpServlet {
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
        String action = request.getParameter("action");

        switch (action) {
            case "viewBooks":
                request.getRequestDispatcher("viewBooks").forward(request, response);
                break;
            case "requestBorrow":
                request.getRequestDispatcher("RequestBorrow.jsp").forward(request, response);
                break;
            case "viewHistory":
                request.getRequestDispatcher("ViewHistory.jsp").forward(request, response);
                break;
            case "addReview":
                request.getRequestDispatcher("AddReview.jsp").forward(request, response);
                break;
            case "viewReviews":
                request.getRequestDispatcher("ViewReviews.jsp").forward(request, response);
                break;
            case "viewStatus":
                request.getRequestDispatcher("ViewStatus.jsp").forward(request, response);
                break;
            case "logout":
                request.getRequestDispatcher("logout").forward(request, response);
                break;
            default:
                response.sendRedirect("visitor.jsp");
                break;
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
