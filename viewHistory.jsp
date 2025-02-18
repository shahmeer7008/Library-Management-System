
<!DOCTYPE html>
<html lang="en">
<head>
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Borrowing History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080'); 
        }
        h1 {
            color: #ffffff;
            text-align: center;
            align-items: center;
        }
        form {
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background: orange;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color:#ffffff;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: gold;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #ff8c00;
        }
    </style>
</head>
<body>
    <h1>View Borrowing History</h1>
    <form action="viewHistory" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        <button type="submit">View History</button>
    </form>
</body>
</html>
