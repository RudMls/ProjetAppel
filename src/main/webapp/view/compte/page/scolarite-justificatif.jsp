<%@ page import="com.example.projetappel.model.Justificatif" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.projetappel.util.FileManager" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    ArrayList<Justificatif> justificatifs = request.getAttribute("justificatifs") == null ? null : (ArrayList<Justificatif>) request.getAttribute("justificatifs");
    String realPath = request.getServletContext().getRealPath("");
%>

<%!
    String getResult(ArrayList<Justificatif> justificatifs) {
        StringBuilder result = new StringBuilder();
        String disabled = "";
        String urlFile;
        String fileName;
        try {
            if (justificatifs != null) {
                for  (Justificatif justificatif : justificatifs) {
                    if (justificatif.getFichier() != null) {
                        fileName = justificatif.getFichier().getNom();
                        urlFile = FileManager.getFichier(justificatif.getFichier());
                    } else {
                        urlFile = "";
                        fileName = "";
                    }
                    result.append(String.format(
                            "<tr>" +
                                    "<th scope='row'>" +
                                    "<input type='checkbox' name='abscence_ids' value='%1$s'/>" +
                                    "</th>" +
                                    "<td>%2$s %3$s</td>" +
                                    "<td>%4$s</td>" +
                                    "<td>%5$s</td>" +
                                    "<td><a href='%6$s' download>%7$s</a></td>" +
                            "</tr>",
                            justificatif.getId(),
                            justificatif.getAbsences().get(0).getEtudiant().getNom(),
                            justificatif.getAbsences().get(0).getEtudiant().getPrenom(),
                            justificatif.getAbsences().get(0).getFicheAppel().getCoursInstance().getCours().getLibelle(),
                            justificatif.getDate(),
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
    <h3 class="text-dark mb-0">Justificatifs</h3>
    <button type="submit" class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button" href="#">Valider</button>
</div>

    <div class="table-responsive" id="no-more-tables">
        <table class="table col-sm-12 table-bordered table-striped table-condensed cf">
            <thead class="thead-dark cf" >
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nom et Prénom</th>
                    <th scope="col">Cours</th>
                    <th scope="col">Date</th>
                    <th scope="col">Justificatif</th>
                </tr>
            </thead>
            <tbody>
                <%= getResult(justificatifs) %>
            </tbody>
        </table>
    </div>
</form>