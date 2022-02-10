<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>

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
        <c:forEach items="${requestScope.listInscription}" var="inscrit" >
            <tr>
                <td>
                    <img class="rounded-circle mb-3 mt-4" src="<% if (inscrit.getEtudiant().getImageUrl() != null) : inscrit.getEtudiant().getImageUrl() ? src="/assets/compte/img/avatars/avatar1.jpeg"}" width="160" height="160">
                </td>
                <td>
                    <c:out  value="${inscrit.getEtudiant().getNom()}"/>
                    <c:out  value="${inscrit.getEtudiant().getPrenom()}"/>
                </td>
                <td> <c:out  value="${inscrit.getFormation().getLibelle()}"/></td>
                <td> <c:out  value="${inscrit.getGroupe().getLibelle()}"/> </td>
                <th scope="row">
                   <a href="/compte/profile-etudiant-cherche?etudiantId=<c:out value="${inscrit.getEtudiant().getId()}"/>">
                   <button type="button" class="btn btn-outline-primary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                            <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                            <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                        </svg>
                    </button>
                    </a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</script>
