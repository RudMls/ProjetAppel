<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.projetappel.util.FileManager" %>
<jsp:useBean id="test" class="com.example.projetappel.util.FileManager"/>

<%
    ArrayList<Absence> absences = request.getAttribute("listAbsences") == null ? null : (ArrayList<Absence>) request.getAttribute("listAbsences");
    String realPath = request.getServletContext().getRealPath("");
%>

<%!
    String getResult(ArrayList<Absence> absences, String realPath) {
        StringBuilder result = new StringBuilder();
        String disabled = "";
        String urlFile;
        String fileName;
        try {
            if (absences != null) {
                for (Absence absence : absences) {
                    disabled = absence.getJustificatif() != null ? "disabled" : "";
                    if (absence.getJustificatif() != null) {
                        disabled = "disabled";
                        fileName = absence.getJustificatif().getFichier().getNom();
                        urlFile = FileManager.getFichier(absence.getJustificatif().getFichier());
                    } else {
                        disabled = "";
                        urlFile = "";
                        fileName = "";
                    }
                    result.append(String.format(
                        "<tr>" +
                            "<th scope='row'>" +
                                "<input type='checkbox' %1$s name='abscence_ids' value='%2$s'/>" +
                            "</th>" +
                                "<td>%3$s</td>" +
                                "<td>%4$s %5$s</td>" +
                            "<td><a href='%6$s' download>%7$s</a></td>" +
                        "</tr>",
                            disabled,
                            absence.getId(),
                            absence.getFicheAppel().getCoursInstance().getCours().getLibelle(),
                            absence.getFicheAppel().getCoursInstance().getParseDateDebut(),
                            absence.getFicheAppel().getCoursInstance().getParseDateFin(),
                            urlFile,
                            fileName

                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
%>

<form method="post">
    <div class="d-sm-flex justify-content-between align-items-center mb-4">
        <h3 class="text-dark mb-0">Absences de ${requestScope.utilisateur.prenom} </h3>
        <button type="submit" class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button" href="#"><i class="fas fa-download fa-sm text-white-50"></i>Importer un justificatif</button>
    </div>

    <div class="table-responsive" id="no-more-tables">

        <table class="table col-sm-12 table-bordered table-striped table-condensed cf">
            <thead class="thead-dark cf" >
            <tr>
                <th scope="col">#</th>
                <th scope="col">Cours</th>
                <th scope="col">Date</th>
                <th scope="col">Justificatif</th>
            </tr>
            </thead>
            <tbody>
            <%= getResult(absences, realPath) %>
            </tbody>
        </table>
    </div>
</form>







