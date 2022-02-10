<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.projetappel.util.FileManager" %>
<%@ page import="com.example.projetappel.util.Constants" %>
<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.ArrayList" %>
<%
    String generaleErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
%>
<form action="/compte/cours-instance" method="post">
<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Appel pour le cours du ${requestScope.coursInstance.getCours().getLibelle()}</h3>
    <button class="btn btn-success btn-sm d-none d-sm-inline-block" type="submit"
        <c:if test="${requestScope.coursInstance.getFicheAppel().isValidee()}">disabled</c:if>
        name="submit" value="Enregistrer">Enregistrer
    </button>
    <button class="btn btn-danger btn-sm d-none d-sm-inline-block" type="submit"
        <c:if test="${requestScope.coursInstance.getFicheAppel().isValidee()}">disabled</c:if>
        name="submit" value="Valider">Valider
    </button>
    <div class="d-sm-flex justify-content-between align-items-center mb-4">
        <button type="submit" name="submit" value="export_pdf" class="btn btn-dark btn-sm d-none d-sm-inline-block" role="button" href="#"><i class="fas fa-download fa-sm text-white-50"></i>Export PDF</button>
    </div>
</div>
<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h5>${requestScope.coursInstance.getCours().getFormation().getLibelle()} </br>
        Groupe ${requestScope.coursInstance.getGroupe().getLibelle()} </h5>
    <h5>${requestScope.coursInstance.getParseDateDebut()}</h5>
</div>
<input type="hidden" name="coursInstance" value="<c:out  value="${requestScope.coursInstance.getId()}"/>" >
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
                    <td><img class="border rounded-circle img-profile" src="${etudiant.getFichier() != null ? FileManager.getFichier(etudiant.getFichier) : Constants.DEFAULT_IMAGE_URL}"></td>
                    <td>
                        <c:out  value="${etudiant.getNom()}"/>
                        <c:out  value="${etudiant.getPrenom()}"/>
                    </td>
                    <td>
                        <input type="hidden" name="etudiantId" value="<c:out  value="${etudiant.getId()}"/>" >

                        <input type="radio"  name="<c:out  value="${etudiant.getId()}"/>" value="present"
                               <c:if test="${requestScope.listEtudiantPresence.get(etudiant) eq 'present' }">checked</c:if>
                         />
                    </td>
                    <td>
                        <input type="radio" name="<c:out  value="${etudiant.getId()}"/>" value="absent"
                            <c:if test="${requestScope.listEtudiantPresence.get(etudiant) eq 'absent' }">checked</c:if>
                       />
                    </td>
                    <td>
                        <input type="radio" name="<c:out  value="${etudiant.getId()}"/>" value="retard"
                               <c:if test="${requestScope.listEtudiantPresence.get(etudiant) eq 'retard' }">checked</c:if>
                        />
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</form>






