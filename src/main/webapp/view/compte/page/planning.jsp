<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.example.projetappel.util.DatePlanning" %>
<%@ page import="java.util.Date" %>
<style>
    <%@include file="/css/planning.css"%>
</style>

<%
    String date = DatePlanning.getStrFormat(new Date(), "yyyy-MM-dd");
    if (request.getAttribute("date") != null) {
        date = (String) request.getAttribute("date");
    }
%>


<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <h3 class="text-dark mb-0">Planning</h3>
</div>

<div class="container">
    <div class="p-3" style="background: #f5f5f5;">
        <form method="post">
            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 d-sm-flex justify-content-sm-center justify-content-lg-center justify-content-xl-center">
                    <div class="row">
                        <div class="col-4 col-sm-4 col-md-4 d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center"><label for="date" class="col-form-label d-md-flex justify-content-md-center align-items-md-center">Date</label></div>
                        <div class="col-6 col-sm-8 col-md-8 d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center justify-content-md-center align-items-md-center"><input id="date" name="date" type="date" value="<%= date %>"></div>
                    </div>
                </div>
                <button type="submit" name="planning_action" value="previous">Précédent</button>
                <button type="submit" name="planning_action" value="next">Après</button>
                <div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 d-flex d-sm-flex justify-content-center align-items-center justify-content-sm-center align-items-sm-center justify-content-lg-center justify-content-xl-center"><button id="rechercher" class="btn btn-primary d-xl-flex" type="submit" name="planning_action" value="search" >Rechercher</button></div>
            </div>
        </form>
    </div>
</div>


<div class="cd-schedule loading">
    <div class="timeline">
        <ul>
            <li><span>09:00</span></li>
            <li><span>09:30</span></li>
            <li><span>10:00</span></li>
            <li><span>10:30</span></li>
            <li><span>11:00</span></li>
            <li><span>11:30</span></li>
            <li><span>12:00</span></li>
            <li><span>12:30</span></li>
            <li><span>13:00</span></li>
            <li><span>13:30</span></li>
            <li><span>14:00</span></li>
            <li><span>14:30</span></li>
            <li><span>15:00</span></li>
            <li><span>15:30</span></li>
            <li><span>16:00</span></li>
            <li><span>16:30</span></li>
            <li><span>17:00</span></li>
            <li><span>17:30</span></li>
            <li><span>18:00</span></li>
        </ul>
    </div> <!-- .timeline -->

    <div class="events">
        <ul class="wrap">
            <c:forEach var="coursInstances" items="${requestScope.coursInstanceFilter}">
                <li class="events-group">
                    <div class="top-info">
                        <span>${DatePlanning.getDayOfWeek(DatePlanning.getWeektoMillis(coursInstances.key))}</span>
                        <small>${DatePlanning.getStringToLong(coursInstances.key, "dd/MM")}</small>
                    </div>
                    <ul>
                        <c:forEach var="coursInstance" items="${coursInstances.value}">
                            <li class="single-event" data-start="${DatePlanning.getStrFormat(coursInstance.dateDebut, "HH:mm")}" data-end="${DatePlanning.getStrFormat(coursInstance.dateFin, "HH:mm")}" data-content="event-rowing-workout" data-event="event-2">
                                <a href="/compte/cours-instance?id=${coursInstance.id}" >
                                    <em class="event-name">${coursInstance.cours.libelle}</em>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>

<%--            <li class="events-group">--%>
<%--                <div class="top-info"><span>Tuesday</span></div>--%>

<%--                <ul>--%>
<%--                    <li class="single-event" data-start="10:00" data-end="11:00"  data-content="event-rowing-workout" data-event="event-2">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Rowing Workout</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="11:30" data-end="13:00"  data-content="event-restorative-yoga" data-event="event-4">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Restorative Yoga</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="13:30" data-end="15:00" data-content="event-abs-circuit" data-event="event-1">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Abs Circuit</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="15:45" data-end="16:45"  data-content="event-yoga-1" data-event="event-3">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Yoga Level 1</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>

<%--            <li class="events-group">--%>
<%--                <div class="top-info"><span>Wednesday</span></div>--%>

<%--                <ul>--%>
<%--                    <li class="single-event" data-start="09:00" data-end="10:15" data-content="event-restorative-yoga" data-event="event-4">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Restorative Yoga</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="10:45" data-end="11:45" data-content="event-yoga-1" data-event="event-3">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Yoga Level 1</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="12:00" data-end="13:45"  data-content="event-rowing-workout" data-event="event-2">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Rowing Workout</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="13:45" data-end="15:00" data-content="event-yoga-1" data-event="event-3">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Yoga Level 1</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>

<%--            <li class="events-group">--%>
<%--                <div class="top-info"><span>Thursday</span></div>--%>

<%--                <ul>--%>
<%--                    <li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Abs Circuit</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="12:00" data-end="13:45" data-content="event-restorative-yoga" data-event="event-4">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Restorative Yoga</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="15:30" data-end="16:30" data-content="event-abs-circuit" data-event="event-1">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Abs Circuit</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="17:00" data-end="18:30"  data-content="event-rowing-workout" data-event="event-2">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Rowing Workout</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>

<%--            <li class="events-group">--%>
<%--                <div class="top-info"><span>Friday</span></div>--%>

<%--                <ul>--%>
<%--                    <li class="single-event" data-start="10:00" data-end="11:00"  data-content="event-rowing-workout" data-event="event-2">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Rowing Workout</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="12:30" data-end="14:00" data-content="event-abs-circuit" data-event="event-1">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Abs Circuit</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="15:45" data-end="16:45"  data-content="event-yoga-1" data-event="event-3">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Yoga Level 1</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
<%--            <!--        -->--%>
<%--            <li class="events-group">--%>
<%--                <div class="top-info"><span>Saturday</span></div>--%>
<%--                <ul>--%>
<%--                    <li class="single-event" data-start="09:30" data-end="10:30" data-content="event-abs-circuit" data-event="event-1">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Abs Circuit</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="11:00" data-end="12:30" data-content="event-rowing-workout" data-event="event-2">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Rowing Workout</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>

<%--                    <li class="single-event" data-start="14:00" data-end="15:15"  data-content="event-yoga-1" data-event="event-3">--%>
<%--                        <a href="#0">--%>
<%--                            <em class="event-name">Yoga Level 1</em>--%>
<%--                        </a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </li>--%>
            <!--        -->
            <!--        -->
            <!--        -->
        </ul>
    </div>

    <div class="event-modal">
        <header class="header">
            <div class="content">
                <span class="event-date"></span>
                <h3 class="event-name"></h3>
            </div>

            <div class="header-bg"></div>
        </header>

        <div class="body">
            <div class="event-info"></div>
            <div class="body-bg"></div>
            <a href="">Fiche d'appel</a>
        </div>

        <a href="#0" class="close">Close</a>
    </div>

    <div class="cover-layer"></div>
</div> <!-- .cd-schedule -->


<script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-1b93190375e9ccc259df3a57c1abc0e64599724ae30d7ea4c6877eb615f89387.js"></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js'></script>
<script src="/js/planning.js"></script>
