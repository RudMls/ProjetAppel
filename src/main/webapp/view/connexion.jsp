<%@ page import="javax.swing.text.Style" %><%--
  Created by IntelliJ IDEA.
  User: rmonl
  Date: 04/02/2022
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>
<body>
    <%
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String emailErreur = request.getAttribute("email_erreur") == null ? "" : (String) request.getAttribute("email_erreur");
        String passwordErreur = request.getAttribute("password_erreur") == null ? "" : (String) request.getAttribute("password_erreur");
        String generaleErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
    %>
    <section class="login-dark">
        <form method="post">
            <h2 class="visually-hidden">Login Form</h2>
            <div class="illustration">
                <i class="icon ion-ios-locked-outline"></i>
            </div>
            <div class="mb-3">
                <input class="form-control" type="email" name="email" value="<%= email %>" placeholder="Email">
                <small class="text-danger"><%= emailErreur %></small>
            </div>
            <div class="mb-3">
                <input class="form-control" type="password" name="password" placeholder="Mot de passe">
                <small class="text-danger"><%= passwordErreur %></small>
            </div>
            <div class="mb-3">
                <small class="text-danger"><%= generaleErreur %></small>
                <button class="btn btn-primary d-block w-100" type="submit">Se connecter</button>
            </div>
        </form>
    </section>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
