
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddReview extends HttpServlet {
    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        
        if (session == null || session.getAttribute("username") == null|| session.getAttribute("role") == null) {
            response.setContentType("text/html");
            out.println("<script type='text/javascript'>");
            out.println("alert('Please log in to proceed.');");
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
        String userName = request.getParameter("username");
        String bookName = request.getParameter("BookName");
        String review = request.getParameter("review");

        if (userName == null || bookName == null || review == null || userName.isEmpty() || bookName.isEmpty() || review.isEmpty()) {
            response.sendRedirect("visitor.jsp?error=Invalid Input");
            return;
        }

        Dao dao=new Dao();
        reviews r=new reviews(userName, bookName, review);
        int ans=dao.AddReview(r);
        if(ans==1)response.sendRedirect("visitor.jsp?message=Review Submitted");
        else {
            out.println("<h1>Review could not be inserted.</h1>");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
