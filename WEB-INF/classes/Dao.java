import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

public class Dao {
    private Connection con = null;
    private Statement st = null;

    public Dao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "coder");
            st = con.createStatement();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public String Login(Persons p) {
        String actualRole = "";
        try {
            String username = p.username;
            String password = p.password;
            String query = "SELECT role FROM persons WHERE username='" + username + "' AND password='" + password + "'";
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                actualRole = rs.getString("role");
            }

            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actualRole;
    }

    // public String Logout() {

    // }

    public int insert(books b) {
        try {
            String BookName = b.BookName;
            String Author = b.Author;
            String BookStatus = b.BookStatus;

            String query = "INSERT INTO books(BookName, Author, BookStatus) VALUES('" + BookName + "', '" + Author
                    + "', '" + BookStatus + "')";
            int rs = st.executeUpdate(query);

            if (rs == 1) {
                st.close();
                con.close();
                return 1;
            }
            st.close();
            con.close();
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int delete(String BookName) {
        try {
            String bname = BookName;
            String query = "DELETE FROM books WHERE BookName = '" + bname + "'";
            System.out.println(query);

            int rs = st.executeUpdate(query);

            if (rs==1) {
                st.close();
                con.close();
                return 1;
            }
            st.close();
            con.close();
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(String oldname, String newname,String nauthname) {
        try {
            String query = "UPDATE books SET BookName = '" + newname + "', Author = '" + nauthname + "' WHERE BookName = '" + oldname + "'";

            int rs = st.executeUpdate(query);

            if (rs == 1) {
                st.close();
                con.close();
                return 1;
            }
            st.close();
            con.close();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int AddReview(reviews r) {
        String username = r.username;
        String BookName = r.BookName;
        String review = r.review;
        try {

            String query = "INSERT INTO reviews (username, BookName, review) VALUES ('" + username + "', '" + BookName
                    + "', '" + review + "')";
            int rs = st.executeUpdate(query);
            if (rs == 1) {
                st.close();
                con.close();
                return 1;
            }
            st.close();
            con.close();
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int RequestBorrow(requests r) {
        String username = r.username;
        String BookName = r.BookName;
        String status = r.status;
        try {
            String query = "INSERT INTO requests (username, BookName, status) VALUES ('" + username + "', '" + BookName
                    + "', '" + status + "')";
            int rs = st.executeUpdate(query);
            if (rs == 1) {
                st.close();
                con.close();
                return 1;
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return 0;
    }

    public int SignUp(Persons p) {
        String username = p.username;
        String password = p.password;
        String role = p.role;
        try {
            String query = "INSERT INTO persons (username, password, role) VALUES ('" + username + "', '" + password
                    + "', '" + role + "')";
            int rs = st.executeUpdate(query);

            if (rs == 1) {
                st.close();
                con.close();
                return 1;
            }
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return 0;
    }

    public ArrayList<books> viewBooks() {
        ArrayList<books> arr = new ArrayList<>();
        try {

            ResultSet rs = st.executeQuery("SELECT * FROM books");

            while (rs.next()) {
                books book = new books(rs.getString("BookName"), rs.getString("Author"), rs.getString("BookStatus"));

                arr.add(book);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException("SQL error occurred", e);
        }
        return arr;
    }

    public ArrayList<borrower> viewHistory(String username) {
        ArrayList<borrower> a = new ArrayList<>();
        try {
            String query = "SELECT * FROM borrower WHERE username = '" + username + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                borrower b = new borrower(rs.getString("username"), rs.getString("BookName"),
                        rs.getString("BorrowDate"),
                        rs.getString("DueDate"));

                a.add(b);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }

    public String viewStatus(String username, String BookName) {
        try {
            String query = "SELECT status FROM requests WHERE username = '" + username + "' AND BookName = '" + BookName
                    + "'";
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String status = rs.getString("status");
                rs.close();
                st.close();
                con.close();
                return status;

            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return "";
    }

    public HashMap<String, List<String>> viewReviews(String BookName) {
        HashMap<String, List<String>> h = new HashMap<>();
        try {
            String query = "SELECT * FROM reviews WHERE BookName = '" + BookName + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String book = rs.getString("BookName");
                String review = rs.getString("review");

                h.putIfAbsent(book, new ArrayList<>());
                h.get(book).add(review);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return h;
    }

    public ArrayList<requests> ApproveRejectRequest()
    {
        
        ArrayList<requests>ar=new ArrayList<>();
        try{
        String fetchPendingRequestsQuery = "SELECT username, BookName FROM requests WHERE status = 'pending'";
        ResultSet pendingRequests = st.executeQuery(fetchPendingRequestsQuery);
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String borrowDate = date.format(currentDate);

            long duedate = currentDate.getTime() + (14L * 24 * 60 * 60 * 1000);
            String duedateFormat = date.format(new Date(duedate));
            while (pendingRequests.next()) {
                String username = pendingRequests.getString("username");
                String bookName = pendingRequests.getString("BookName");

                Statement bookStatement = con.createStatement();
                String q = "SELECT BookStatus FROM books WHERE BookName = '" + bookName + "'";
                ResultSet bookStatusRs = bookStatement.executeQuery(q);

                String bookStatus = "NA";
                if (bookStatusRs.next()) {
                    bookStatus = bookStatusRs.getString("BookStatus");
                }
                bookStatusRs.close();
                bookStatement.close();

                String status;
                if (bookStatus.equals("NA")) {
                    status = "rejected";
                } else {
                    Statement pendingStatement = con.createStatement();
                    String Query = "SELECT COUNT(*) FROM borrower WHERE username = '" + username + "' AND DueDate < CURDATE()";
                    ResultSet pendingReturnRs = pendingStatement.executeQuery(Query);

                    boolean hasPendingReturn = false;
                    if (pendingReturnRs.next() && pendingReturnRs.getInt(1) > 0) {
                        hasPendingReturn = true;
                    }
                    pendingReturnRs.close();
                    pendingStatement.close();

                    if (hasPendingReturn) {
                        status = "rejected";
                    } else {
                        status = "approved";

                        Statement updateBookStatement = con.createStatement();
                        String k = "UPDATE books SET BookStatus = 'NA' WHERE BookName = '" + bookName + "'";
                        updateBookStatement.executeUpdate(k);
                        updateBookStatement.close();

                        Statement updateborrowerStatement = con.createStatement();
                        String x = "INSERT INTO borrower (username, BookName, BorrowDate, DueDate) " +
                                "VALUES ('" + username + "', '" + bookName + "', '" + borrowDate + "', '" + duedateFormat + "')";
                        updateborrowerStatement.executeUpdate(x);
                        updateborrowerStatement.close();

                    }
                }

                Statement updateRequestStatement = con.createStatement();
                String rs = "UPDATE requests SET status = '" + status + "' WHERE username = '" + username + "' AND BookName = '" + bookName + "'";
                updateRequestStatement.executeUpdate(rs);
                updateRequestStatement.close();
                ar.add(new requests(username,bookName,status));
            }
        }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return ar;


    }

    public boolean checkUserExists(String username) {
        boolean exists = false;
        ResultSet rs = null;
        try {
            
            String query = "SELECT 1 FROM persons WHERE username = '" + username + "'";
            rs = st.executeQuery(query);
            exists = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exists;
    }
    
   

}