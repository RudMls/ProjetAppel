<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.ArrayList" %>

<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Absences de ${requestScope.utilisateur.prenom} </h3>
    <a class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button" href="#"><i class="fas fa-download fa-sm text-white-50"></i>Importer un justificatif</a>
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
               <c:forEach items="${ requestScope.listAbsences}" var="absences" >
               <tr>
                       <th scope="row"><input type="checkbox"  value="<c:out value="${ absences.getId() }"/>"
                          <c:if test="${absences.getJustificatif() ==! null}">disabled</c:if>
                            /></th>
                       <td><c:out value="${absences.getFicheAppel().getCoursInstance().getCours().getLibelle() }" /></td>
                       <td>
                           <c:out  value="${absences.getFicheAppel().getCoursInstance().getParseDateDebut()}"/>
                           <c:out  value="${absences.getFicheAppel().getCoursInstance().getParseDateFin()}"/>
                       </td>
                       <td> Justificatif </td>

               </tr>
               </c:forEach>
               </tbody>
           </table>
   </div>






