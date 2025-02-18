<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <%@ page language="java" import="jakarta.servlet.*,jakarta.servlet.http.*,java.sql.*" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&q=80&w=1080');
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: white;
        }

        .login-container {
            background-color: rgba(255, 165, 0, 0.9);
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 300px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        label {
            display: block;
            font-size: 14px;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: none;
            border-radius: 4px;
        }

        .radio-group label {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        .radio-group input[type="radio"] {
            margin-right: 8px;
        }

        .login-button {
            width: 100%;
            padding: 10px;
            background-color: #ffcc00;
            border: none;
            border-radius: 4px;
            color: #333;
            font-size: 16px;
            cursor: pointer;
        }

        .login-button:hover {
            background-color: #e6b800;
        }

        .error-message {
            margin-top: 15px;
            text-align: center;
            color: red;
        }

        a {
            color: white;
            text-decoration: none;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        <form action="Login" method="POST">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group radio-group">
                <label>
                    <input type="radio" name="role" value="librarian" required>
                    Librarian
                </label>
                <label>
                    <input type="radio" name="role" value="visitor" required>
                    Visitor
                </label>
            </div>
            <div class="form-group">
                <button type="submit" class="login-button">Login</button>
            </div>
        </form>
        <a href="SignUp.jsp">Don't have an account? Sign up</a>
        <div class="error-message">
            <script>
                const urlParams = new URLSearchParams(window.location.search);
                const errorMessage = urlParams.get('error');
                if (errorMessage) {
                    document.querySelector('.error-message').innerText = errorMessage;
                }
            </script>
        </div>
    </div>
</body>
</html>
