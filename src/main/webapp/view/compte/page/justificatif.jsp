<%--
  Created by IntelliJ IDEA.
  User: rmonl
  Date: 07/02/2022
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Justificatif</h1>


<form method="post" enctype="multipart/form-data">
    <div>
        <label>Libelle</label>
        <input type="text" name="libelle">
    </div>
    <div>
        <label>Fichier</label>
        <input type="file" name="fichier">
    </div>
    <div>
        <input type="submit" value="Envoyer">
    </div>
</form>

</body>
</html>
