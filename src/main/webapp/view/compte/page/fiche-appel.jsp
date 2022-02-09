<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.ArrayList" %>
<form action="/compte/cours-instance" method="post">
<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Appel pour le cours ${requestScope.coursInstance.getCours().getLibelle()} ${requestScope.coursInstance.getParseDateDebut()}</h3>
    <button class="btn btn-primary btn-sm d-none d-sm-inline-block" type="submit">Valider</button>
</div>


<div class="table-responsive" id="no-more-tables">

        <table class="table col-sm-12 table-bordered table-striped table-condensed cf">
            <thead class="thead-dark cf" >
            <tr>
                <th scope="col">Photo</th>
                <th scope="col">Nom et prénom</th>
                <th scope="col">Présent</th>
                <th scope="col">Absent</th>
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
                    <td>
                        <input type="hidden" name="etudiantId" value="<c:out  value="${etudiant.getId()}"/>" >

                        <input type="radio"  name="<c:out  value="${etudiant.getId()}"/>" value="present"/>
                    </td>
                    <td>
                        <input type="radio" name="<c:out  value="${etudiant.getId()}"/>" value="absent"/>
                    </td>
                    <td>
                        <input type="radio" name="<c:out  value="${etudiant.getId()}"/>" value="retard"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>






