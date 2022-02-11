<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<style>
    <%@include file="/css/cours.css"%>
</style>
<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <div class=""container-fluid>
        <h3 class="text-dark mb-0">Cours</h3>
        <div class="container d-flex justify-content-center">
            <ul class="list-group mt-5 text-white">
                <c:forEach items="${requestScope.listeCours}" var="cours" >
                    <a href="/compte/cours-statistiques?coursId=<c:out value="${cours.getId()}"/>">
                        <li class="list-group-item d-flex justify-content-between align-content-center">
                                <div class="d-flex flex-row"><i class="fa fa-book"></i>
                                <div class="ml-2">
                                        <h6 class="mb-0"><c:out value="${cours.getLibelle()}"/></h6>
                                </div>
                            </div>
                        </li>
                    </a>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>