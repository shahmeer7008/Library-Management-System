<!DOCTYPE html>
<html>
<head>
    <title>Librarian Dashboard</title>
    <%@ page language="java" import="jakarta.servlet.*,jakarta.servlet.http.*,java.sql.*" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%
    HttpSession ss = request.getSession(false);
    if (ss == null || ss.getAttribute("username") == null) {
    %>
    <script>
        alert('Session not found. Please log in.');
        window.location.href = "index.jsp";
    </script>
    <%
        return;
    }
    %>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        h1 {
            color: #ffffff;
            text-align: center;
            margin-top: 140px;
        }

        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        a {
            display: inline-block;
            margin: 5px 0;
            padding: 10px 20px;
            font-size: 16px;
            color: white;
            background-color: orange;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            text-align: center;
            width: 200px;
        }

        a:hover {
            background-color: darkgoldenrod;
        }
    </style>
</head>
<body>
    <h1><b>Librarian Dashboard</b></h1>
    <div class="button-container">
        <a href="Insert.jsp">Add Book</a>
        <a href="update.jsp">Update Book</a>
        <a href="delete.jsp">Delete Book</a>
        <a href="viewBooks">View Books</a>
        <a href="ApproveRejectRequestServlet">Approve/Reject Borrowing Requests</a>
        <a href="logout">Logout</a>
    </div>
</body>
</html>
