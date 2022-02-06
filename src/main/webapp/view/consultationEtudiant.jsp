<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: liely
  Date: 06/02/2022
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulter mes absences</title>
</head>
<body>
  <%
    List<Absence> absences =(List<Absence>)request.getAttribute("listAbsences");
    for( Absence absence:absences){


    }
  %>
</body>
</html>
