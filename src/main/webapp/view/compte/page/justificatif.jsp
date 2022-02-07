<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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