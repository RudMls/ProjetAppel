<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.projetappel.model.Appartenir" %>
<%@ page import="com.example.projetappel.model.Etudiant" %>
<%@ page import="com.example.projetappel.util.FileManager" %>
<%@ page import ="com.example.projetappel.util.Constants" %>

<%
    ArrayList<Etudiant> etudiants = request.getAttribute("listInscription") == null ? null : (ArrayList<Etudiant>) request.getAttribute("listInscription");
    String realPath = request.getServletContext().getRealPath("");
%>

<%!
    String getSource(ArrayList<Etudiant> etudiants) {
        StringBuilder result = new StringBuilder();
        String urlFile;
        try {
            if (etudiants != null) {
                for (Etudiant etudiant : etudiants) {
                    if (etudiant.getFichier() != null) {
                        urlFile = FileManager.getFichier(etudiant.getFichier());
                    } else {
                        urlFile = Constants.DEFAULT_IMAGE_URL;
                    }
                    result.append(String.format(
                            "<tr>" +
                                "<td><img class='rounded-circle mb-3 mt-4 img-profile' src='%1$s' width='160' height='160'></td>" +
                                "<td>%2$s</td>" +
                                "<td>%3$s</td>" +
                                "<td>%4$s</td>" +
                            "</tr>"
                            , urlFile
                            , etudiant.getPrenom() + " " +etudiant.getNom()
                            , etudiant.getAppartenir().getFormation().getLibelle()
                            , etudiant.getAppartenir().getGroupe().getLibelle()
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
%>

<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Liste des étudiants</h3>
</div>


<div class="table-responsive" id="no-more-tables">

    <table class="table col-sm-12 table-bordered table-striped table-condensed cf">
        <thead class="thead-dark cf" >
        <tr>
            <th scope="col">Photo</th>
            <th scope="col">Nom</th>
            <th scope="col">Formation</th>
            <th scope="col">Groupe</th>
            <th scope="col"> </th>
        </tr>
        </thead>
        <tbody>
            <%= getSource(etudiants) %>
        </tbody>
    </table>
</div>
</script>
