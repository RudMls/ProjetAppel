<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Planning</h3>
</div>

<div class="container">
    <div class="p-3" style="background: #f5f5f5;">
        <div class="row">
            <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 d-sm-flex justify-content-sm-center justify-content-lg-center justify-content-xl-center">
                <div class="row">
                    <div class="col-4 col-sm-4 col-md-4 d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center"><label for="date" class="col-form-label d-md-flex justify-content-md-center align-items-md-center">Date</label></div>
                    <div class="col-6 col-sm-8 col-md-8 d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center justify-content-md-center align-items-md-center"><input id="date" type="date"></div>
                </div>
            </div>
            <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center justify-content-lg-center justify-content-xl-center"><button id="rechercher" class="btn btn-primary d-xl-flex" type="button">Rechercher</button></div>
        </div>
    </div>
</div>
<div class="schedule content-block">
    <div class="container">
        <div class="timetable">
            <ul class="nav nav-tabs text-left d-flex d-sm-flex d-md-flex d-lg-flex justify-content-center align-content-end justify-content-sm-center justify-content-md-center justify-content-lg-center" style="margin-top: 10px;margin-bottom: 10px;">
                <li class="nav-item" id="tab_lundi"><a class="nav-link active" href="#">Lundi</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Mardi</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Mercredi</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Jeudi</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Vendredi</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Samedi</a></li>
                <li class="nav-item"><a class="nav-link" href="#">dimanche</a></li>
            </ul>
            <div class="tab-content">
                <div class="row">
                    <div class="col-sm-12 col-md-6 col-lg-4">
                        <div style="margin: 0;background: #f5f5f5;padding: 10px;margin-top: 5px;">
                            <p style="margin: 5px;"><strong>9:30 - 12:30</strong></p>
                            <p style="margin: 5px;">Programmation Objet</p><button class="btn btn-primary" type="button" style="margin: 5px;">Fiche d'appel</button>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4">
                        <div style="margin: 0;background: #f5f5f5;padding: 10px;margin-top: 5px;">
                            <p style="margin: 5px;"><strong>9:30 - 12:30</strong></p>
                            <p style="margin: 5px;">Programmation Objet</p><button class="btn btn-primary" type="button" style="margin: 5px;">Fiche d'appel</button>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-6 col-lg-4">
                        <div style="margin: 0;background: #f5f5f5;padding: 10px;margin-top: 5px;">
                            <p style="margin: 5px;"><strong>9:30 - 12:30</strong></p>
                            <p style="margin: 5px;">Programmation Objet</p><button class="btn btn-primary" type="button" style="margin: 5px;">Fiche d'appel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/planning.js"></script>
