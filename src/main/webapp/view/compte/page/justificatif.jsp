<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String libelle = request.getParameter("libelle") == null ? "" : request.getParameter("libelle");
    String fichierErreur = request.getAttribute("fichier_erreur") == null ? "" : (String) request.getAttribute("fichier_erreur");
    String generalErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
%>

<h1>Justificatif</h1>

<form method="post" enctype="multipart/form-data">
    <div>
        <label>Libelle</label>
        <input type="text" name="libelle" value="<%= libelle %>">
    </div>
    <div>
        <label>Fichier</label>
        <input type="file" name="fichier" multiple>
        <span><%= fichierErreur %></span>
    </div>
    <div>
        <span><%= generalErreur %></span>
    </div>
    <div>
        <input type="submit" value="Envoyer">
    </div>
</form>