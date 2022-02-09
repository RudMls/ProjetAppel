<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.projetappel.model.Utilisateur" %>
<%@ page import="com.example.projetappel.model.Enseignant" %>
<%@ page import="com.example.projetappel.model.Scolarite" %>
<%@ page import="com.example.projetappel.model.Etudiant" %>
<%@ page import="com.example.projetappel.enumtype.Role" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <div class="container-fluid">
        <h3 class="text-dark mb-0">Profil</h3>

        <div class="row mb-3">
            <div class="col-lg-4">
                <div class="card mb-3">
                    <form method="POST" enctype="multipart/form-data">
                        <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4"
                                                                       src="/dogs/image2.jpeg" width="160" height="160">
                            <div class="mb-3">
                                <label class="form-label" for="customFile">Default file input example</label>
                                <input type="file" class="form-control" id="customFile" />
                                <button class="btn btn-primary btn-sm" type="submit">Change Photo</button>
                            </div>
                        </div>
                    </form>

                </div>
                    <c:choose>
                        <c:when test = "${requestScope.role== Role.ETUDIANT}">
                            <%
                                float txAbsGen = (float) request.getAttribute("txAbsGen");
                                float txAbsCours = (float) request.getAttribute("txAbsCours");
                                float txRetGen = (float) request.getAttribute("txRetGen");
                                float txRetCours = (float) request.getAttribute("txRetCours");
                            %>
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="text-primary font-weight-bold m-0">Statistiques de l'Étudiant</h6>
                                </div>
                                <div class="card-body">
                                    <h4 class="small font-weight-bold">Taux général d'absences
                                        <span class="float-right">
                                            <%= txAbsGen%>%
                                        </span></h4>
                                    <div class="progress progress-sm mb-3">
                                        <div class="progress-bar bg-danger" aria-valuenow="<%= txAbsGen%>%" aria-valuemin="0" aria-valuemax="100"
                                             style="width: <%= txAbsGen%>%;"><span class="sr-only"><%= txAbsGen%>%</span></div>
                                    </div>

                                    <h4 class="small font-weight-bold">Taux d'absences de Nom Matière
                                        <span class="float-right">
                                            <%= txAbsCours%>%
                                        </span></h4>
                                    <div class="progress progress-sm mb-3">
                                        <div class="progress-bar bg-warning" aria-valuenow="<%= txAbsCours%>%" aria-valuemin="0"
                                             aria-valuemax="100" style="width: <%= txAbsCours%>%;"><span class="sr-only"><%= txAbsCours%>%</span></div>
                                    </div>

                                    <h4 class="small font-weight-bold">Taux général de retards
                                        <span class="float-right">
                                            <%= txRetGen%>%
                                        </span></h4>
                                    <div class="progress progress-sm mb-3">
                                        <div class="progress-bar bg-primary" aria-valuenow="<%= txRetGen%>" aria-valuemin="0"
                                             aria-valuemax="100" style="width: <%= txRetGen%>%;"><span class="sr-only"><%= txRetGen%>%</span></div>
                                    </div>

                                    <h4 class="small font-weight-bold">Taux de retards de Nom Matière
                                        <span class="float-right">
                                            <%= txRetCours%>%
                                        </span></h4>
                                    <div class="progress progress-sm mb-3">
                                        <div class="progress-bar bg-info" aria-valuenow="<%= txRetCours%>" aria-valuemin="0"
                                             aria-valuemax="100" style="width: <%= txRetCours%>%;"><span class="sr-only"><%= txRetCours%>%</span></div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>

            </div>
            <div class="col-lg-8">
                <div class="row">
                    <div class="col">
                        <div class="card shadow mb-3">
                            <div class="card-header py-3">
                                <p class="text-primary m-0 font-weight-bold">Mes informations</p>
                            </div>

                            <div class="card-body">
                                <form>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label
                                                    for="username"><strong>Statut</strong></label><input
                                                    class="form-control" type="text" id="username"
                                                    placeholder=${requestScope.role} name="username"></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label for="email"><strong>Adresse E-mail</strong></label><input
                                                    class="form-control" type="email" id="email"
                                                    placeholder=${requestScope.utilisateur.email} name="email"></div>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label for="first_name"><strong>Prénom</strong></label><input
                                                    class="form-control" type="text" id="first_name" placeholder=${requestScope.utilisateur.prenom}
                                                    name="first_name"></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label for="last_name"><strong>Nom</strong></label><input
                                                    class="form-control" type="text" id="last_name"
                                                    placeholder=${requestScope.utilisateur.nom} name="last_name"></div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/profile.js"></script>