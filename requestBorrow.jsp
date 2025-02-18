
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request a Book</title>
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
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080'); 
        }
        h1 {
            color: #ffffff;
            align-items: center;
            text-align: center;
        }
        form {
            background-color: orange;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 20px auto;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #ffffff;
        }
        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            background-color: gold;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #e69500;
        }
    </style>
</head>
<body>
    <h1>Request a Book</h1>
    <form action="RequestBorrow" method="post">
        <label for="username">User Name:</label>
        <input type="text" id="username" name="username" required>
        <label for="BookName">Book Name:</label>
        <input type="text" id="BookName" name="BookName" required>
        <button type="submit">Request Book</button>
    </form>
</body>
</html>
