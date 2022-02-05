<%--
  Created by IntelliJ IDEA.
  User: rmonl
  Date: 04/02/2022
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        <%@include file="/css/planning.css"%>
    </style>
</head>
<body>



<h1>Planning</h1>

<form id="filter" method="post">
    <div>
        <label for="date">Date</label>
        <input type="date" id="date" name="date">
    </div>

    <div>
        <input type="submit" value="Rechercher">
    </div>
</form>

<div class="idance">
    <div class="schedule content-block">
        <div class="container">
            <h2 data-aos="zoom-in-up" class="aos-init aos-animate">Schedule</h2>

            <div class="timetable">

                <!-- Schedule Top Navigation -->
                <nav class="nav nav-tabs">
                    <a class="nav-link active">Mon</a>
                    <a class="nav-link">Tue</a>
                    <a class="nav-link">Wed</a>
                    <a class="nav-link">Thu</a>
                    <a class="nav-link">Fri</a>
                    <a class="nav-link">Sat</a>
                    <a class="nav-link">Sun</a>
                </nav>

                <div class="tab-content">
                    <div class="tab-pane show active">
                        <div class="row">

                            <!-- Schedule Item 1 -->
                            <div class="col-md-6">
                                <div class="timetable-item">
                                    <div class="timetable-item-img">
                                        <img src="https://via.placeholder.com/100x80/FFB6C1/000000" alt="Contemporary Dance">
                                    </div>
                                    <div class="timetable-item-main">
                                        <div class="timetable-item-time">4:00pm - 5:00pm</div>
                                        <div class="timetable-item-name">Contemporary Dance</div>
                                        <a href="#" class="btn btn-primary btn-book">Book</a>
                                        <div class="timetable-item-like">
                                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                                            <i class="fa fa-heart" aria-hidden="true"></i>
                                            <div class="timetable-item-like-count">11</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Schedule Item 2 -->
                            <div class="col-md-6">
                                <div class="timetable-item">
                                    <div class="timetable-item-img">
                                        <img src="https://via.placeholder.com/100x80/00FFFF/000000" alt="Break Dance">
                                    </div>
                                    <div class="timetable-item-main">
                                        <div class="timetable-item-time">5:00pm - 6:00pm</div>
                                        <div class="timetable-item-name">Break Dance</div>
                                        <a href="#" class="btn btn-primary btn-book">Book</a>
                                        <div class="timetable-item-like">
                                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                                            <i class="fa fa-heart" aria-hidden="true"></i>
                                            <div class="timetable-item-like-count">28</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Schedule Item 3 -->
                            <div class="col-md-6">
                                <div class="timetable-item">
                                    <div class="timetable-item-img">
                                        <img src="https://via.placeholder.com/100x80/8A2BE2/000000" alt="Street Dance">
                                    </div>
                                    <div class="timetable-item-main">
                                        <div class="timetable-item-time">5:00pm - 6:00pm</div>
                                        <div class="timetable-item-name">Street Dance</div>
                                        <a href="#" class="btn btn-primary btn-book">Book</a>
                                        <div class="timetable-item-like">
                                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                                            <i class="fa fa-heart" aria-hidden="true"></i>
                                            <div class="timetable-item-like-count">28</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Schedule Item 4 -->
                            <div class="col-md-6">
                                <div class="timetable-item">
                                    <div class="timetable-item-img">
                                        <img src="https://via.placeholder.com/100x80/6495ED/000000" alt="Yoga">
                                    </div>
                                    <div class="timetable-item-main">
                                        <div class="timetable-item-time">7:00pm - 8:00pm</div>
                                        <div class="timetable-item-name">Yoga</div>
                                        <a href="#" class="btn btn-primary btn-book">Book</a>
                                        <div class="timetable-item-like">
                                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                                            <i class="fa fa-heart" aria-hidden="true"></i>
                                            <div class="timetable-item-like-count">23</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Schedule Item 5 -->
                            <div class="col-md-6">
                                <div class="timetable-item">
                                    <div class="timetable-item-img">
                                        <img src="https://via.placeholder.com/100x80/00FFFF/000000" alt="Stretching">
                                    </div>
                                    <div class="timetable-item-main">
                                        <div class="timetable-item-time">6:00pm - 7:00pm</div>
                                        <div class="timetable-item-name">Stretching</div>
                                        <a href="#" class="btn btn-primary btn-book">Book</a>
                                        <div class="timetable-item-like">
                                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                                            <i class="fa fa-heart" aria-hidden="true"></i>
                                            <div class="timetable-item-like-count">14</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Schedule Item 6 -->
                            <div class="col-md-6">
                                <div class="timetable-item">
                                    <div class="timetable-item-img">
                                        <img src="https://via.placeholder.com/100x80/008B8B/000000" alt="Street Dance">
                                    </div>
                                    <div class="timetable-item-main">
                                        <div class="timetable-item-time">8:00pm - 9:00pm</div>
                                        <div class="timetable-item-name">Street Dance</div>
                                        <a href="#" class="btn btn-primary btn-book">Book</a>
                                        <div class="timetable-item-like">
                                            <i class="fa fa-heart-o" aria-hidden="true"></i>
                                            <i class="fa fa-heart" aria-hidden="true"></i>
                                            <div class="timetable-item-like-count">9</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    <script type="text/css">
        <%@include file="/js/planning.js"%>
    </script>
</body>
</html>
