<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.projetappel.model.Absence" %>
<%@ page import="java.util.ArrayList" %>
<div class="d-sm-flex justify-content-between align-items-center mb-4">
  <h3 class="text-dark mb-0">Absences de ${requestScope.utilisateur.prenom} </h3>


    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Cours</th>
            <th scope="col">Date</th>
            <th scope="col">Justificatif</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
        </tr>
        <tr>
            <th scope="row">3</th>
            <td>Larry</td>
            <td>the Bird</td>
            <td>@twitter</td>
        </tr>
        </tbody>
    </table>


</div>
