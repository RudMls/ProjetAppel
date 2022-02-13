<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String libelle = request.getParameter("libelle") == null ? "" : request.getParameter("libelle");
    String fichierErreur = request.getAttribute("fichier_erreur") == null ? "" : (String) request.getAttribute("fichier_erreur");
    String generalErreur = request.getAttribute("generale_erreur") == null ? "" : (String) request.getAttribute("generale_erreur");
%>

<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Justificatif</h3>
    <a href="/compte/consultation-etudiant" class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button" >Retour</a>
</div>

<div class="container">
    <div class="row justify-content-center" style="padding-top: 35px;">
        <div class="col-md-12 col-lg-10">
            <div class="bg-white">
                <div class="card-header bg-white">&nbsp;</div>
                <div class="card-body">
                    <form method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Libelle</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="libelle" value="<%= libelle %>" class="form-control" placeholder="Name">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Fichier</label>
                                    <div class="col-sm-10">
                                        <input type="file" name="fichier" id="fileDemo">
                                        <small class="d-flex d-sm-flex justify-content-center justify-content-sm-center text-danger erreur"><%= fichierErreur %></small>
                                    </div>
                                </div>
                            </div>

                            <div class="col-sm-12">
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label"></label>
                                    <div class="col-sm-10">
                                        <button class="btn-primary btn-sm">Envoyer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>