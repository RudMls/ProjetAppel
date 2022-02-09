<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.ArrayList" %>

<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0"> Cours a remplir </h3>
    <a class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button" href="#"><i class="fas fa-download fa-sm text-white-50"></i>Importer un justificatif</a>
</div>


<div class="table-responsive" id="no-more-tables">

    <table class="table col-sm-12 table-bordered table-striped table-condensed cf">
        <thead class="thead-dark cf" >
        <tr>
            <th scope="col">Photo</th>
            <th scope="col">Nom</th>
            <th scope="col">Présence</th>
            <th scope="col">Absence</th>
            <th scope="col">Retard</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.listEtudiant}" var="etudiant" >
            <tr>

                <td><c:out value="${etudiant.getImageUrl()}" /></td>
                <td>
                    <c:out  value="${etudiant.getNom()}"/>
                    <c:out  value="${etudiant.getPrenom()}"/>
                </td>
                <th scope="row"><input type="radio"  value="<c:out value="${ absences.getId() }"/>"
                                       <c:if test="${absences.getJustificatif() ==! null}">disabled</c:if>
                /></th>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>






