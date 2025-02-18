<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080'); 
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f0f0f0;
        }
        .signup-container {
            width: 300px;
            padding: 20px;
            background-color: orange;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .signup-container h2 {
            margin-bottom: 20px;
            color: #ffffff;
        }
        .signup-container form {
            display: flex;
            flex-direction: column;
        }
        .signup-container input {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }
        .signup-container button {
            padding: 10px;
            background-color: gold;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        .signup-container button:hover {
            background-color: #e65c00;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
            font-size: 14px;
        }
    </style>
     <script>
        function validateForm() {
            const email = document.forms["signupForm"]["username"].value;
            const adminEmails = ["bsef22m501@pucit.edu.pk", "raza@gmail.com"];

            if (adminEmails.includes(email)) {
                alert("Admin accounts cannot register as visitors.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="signup-container">
        <h2>Sign Up</h2>
        <% if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <form name="signUpForm"action="SignUp" method="post" onsubmit="return validateForm()">
            <input type="email" name="username" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit">Sign Up</button>
        </form>
        <a href="index.jsp">Already have an account? Login</a>
    </div>
</body>
</html>
