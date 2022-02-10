<%@ page import="com.example.projetappel.model.Justificatif" %>
<%@ page import="java.util.ArrayList" %>

<%
    ArrayList<Justificatif> justificatifs = request.getAttribute("justificatifs") == null ? null : (ArrayList<Justificatif>) request.getAttribute("justificatifs");
    String realPath = request.getServletContext().getRealPath("");
%>

<%!
    String getResult(ArrayList<Justificatif> justificatifs) {
        StringBuilder result = new StringBuilder();
        try {
            if (justificatifs != null) {
                for  (Justificatif justificatif : justificatifs) {

                    result.append(String.format(
                            "<tr>" +
                                    "<td></td>" +
                                    "<td></td>" +
                                    "<td></td>" +
                            "</tr>"
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
    <h3 class="text-dark mb-0">Justificatifs</h3>
</div>

<form method="post">
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
                <%= getResult(justificatifs) %>
            </tbody>
        </table>
    </div>
</form>