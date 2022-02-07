<%@ page import="com.example.projetappel.model.Utilisateur" %>
<%@ page import="com.example.projetappel.model.Enseignant" %>
<%@ page import="com.example.projetappel.model.Scolarite" %>
<%@ page import="com.example.projetappel.model.Etudiant" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <div class="container-fluid">
        <h3 class="text-dark mb-0">Profil</h3>

        <div class="row mb-3">
            <div class="col-lg-4">
                <div class="card mb-3">
                    <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4"
                                                                   src="/dogs/image2.jpeg" width="160" height="160">
                        <div class="mb-3">
                            <button class="btn btn-primary btn-sm" type="button">Change Photo</button>
                        </div>
                    </div>
                </div>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="text-primary font-weight-bold m-0">Taux d'Absence par Matières</h6>
                    </div>
                    <div class="card-body">
                        <h4 class="small font-weight-bold">Server migration<span class="float-right">20%</span></h4>
                        <div class="progress progress-sm mb-3">
                            <div class="progress-bar bg-danger" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 20%;"><span class="sr-only">20%</span></div>
                        </div>
                        <h4 class="small font-weight-bold">Sales tracking<span class="float-right">40%</span></h4>
                        <div class="progress progress-sm mb-3">
                            <div class="progress-bar bg-warning" aria-valuenow="40" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 40%;"><span class="sr-only">40%</span></div>
                        </div>
                        <h4 class="small font-weight-bold">Customer Database<span class="float-right">60%</span></h4>
                        <div class="progress progress-sm mb-3">
                            <div class="progress-bar bg-primary" aria-valuenow="60" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 60%;"><span class="sr-only">60%</span></div>
                        </div>
                        <h4 class="small font-weight-bold">Payout Details<span class="float-right">80%</span></h4>
                        <div class="progress progress-sm mb-3">
                            <div class="progress-bar bg-info" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
                                 style="width: 80%;"><span class="sr-only">80%</span></div>
                        </div>
                        <h4 class="small font-weight-bold">Account setup<span class="float-right">Complete!</span></h4>
                        <div class="progress progress-sm mb-3">
                            <div class="progress-bar bg-success" aria-valuenow="100" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 100%;"><span class="sr-only">100%</span></div>
                        </div>
                    </div>
                </div>
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
                                                    placeholder=${requestScope.status} name="username"></div>
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
                        <div class="card shadow">
                            <div class="card-header py-3">
                                <p class="text-primary m-0 font-weight-bold">Contact Settings</p>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class="form-group"><label for="address"><strong>Address</strong></label><input
                                            class="form-control" type="text" id="address" placeholder="Sunset Blvd, 38"
                                            name="address"></div>
                                    <div class="form-row">
                                        <div class="col">
                                            <div class="form-group"><label
                                                    for="city"><strong>City</strong></label><input class="form-control"
                                                                                                   type="text" id="city"
                                                                                                   placeholder="Los Angeles"
                                                                                                   name="city"></div>
                                        </div>
                                        <div class="col">
                                            <div class="form-group"><label
                                                    for="country"><strong>Country</strong></label><input
                                                    class="form-control" type="text" id="country" placeholder="USA"
                                                    name="country"></div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button class="btn btn-primary btn-sm" type="submit">Save&nbsp;Settings</button>
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