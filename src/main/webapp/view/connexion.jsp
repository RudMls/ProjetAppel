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
    <title>Title</title>
</head>
<body>
    <%
        String email = request.getParameter("email") == null ? "" : request.getParameter("email");
        String emailErreur = request.getAttribute("email_erreur") == null ? "" : (String) request.getAttribute("email_erreur");
        String passwordErreur = request.getAttribute("password_erreur") == null ? "" : (String) request.getAttribute("password_erreur");
        String generaleErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
    %>
    <h1>Connexion</h1>
    <form method="post">
        <div>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" value="<%= email %>" spellcheck="false" autocomplete="off">
            <span><%= emailErreur %></span>
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" spellcheck="false" autocomplete="off">
            <span><%= passwordErreur %></span>
        </div>
        <div>
            <input type="submit" value="Se connecter">
            <span><%= generaleErreur %></span>
        </div>
    </form>
</body>
</html>
