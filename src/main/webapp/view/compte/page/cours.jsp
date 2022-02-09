<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<div class="d-sm-flex justify-content-between align-items-center mb-4">
    <div class=""container-fluid>
        <h3 class="text-dark mb-0">Cours</h3>

        <style>
            body {
                background: #4527A0
            }

            .list-group {
                width: 600px !important
            }

            .list-group-item {
                margin-top: 10px;
                border-radius: none;
                background: #5E35B1;
                cursor: pointer;
                transition: all 0.3s ease-in-out
            }

            .list-group-item:hover {
                transform: scaleX(1.1)
            }

            .check {
                opacity: 0;
                transition: all 0.6s ease-in-out
            }

            .list-group-item:hover .check {
                opacity: 1
            }

            .about span {
                font-size: 12px;
                margin-right: 10px
            }

            input[type=checkbox] {
                position: relative;
                cursor: pointer
            }

            input[type=checkbox]:before {
                content: "";
                display: block;
                position: absolute;
                width: 20px;
                height: 20px;
                top: 0px;
                left: 0;
                border: 1px solid #10a3f9;
                border-radius: 3px;
                background-color: white
            }

            input[type=checkbox]:checked:after {
                content: "";
                display: block;
                width: 7px;
                height: 12px;
                border: solid #007bff;
                border-width: 0 2px 2px 0;
                -webkit-transform: rotate(45deg);
                -ms-transform: rotate(45deg);
                transform: rotate(45deg);
                position: absolute;
                top: 2px;
                left: 6px
            }

            input[type="checkbox"]:checked+.check {
                opacity: 1
            }
        </style>
        <div class="container d-flex justify-content-center">
            <ul class="list-group mt-5 text-white">
                <c:forEach items="${requestScope.listCours}" var="cours" >
                <li class="list-group-item d-flex justify-content-between align-content-center">
                    <div class="d-flex flex-row"> <img src="https://img.icons8.com/color/100/000000/folder-invoices.png" width="40" />
                        <div class="ml-2">
                            <h6 class="mb-0"><c:out  value="${cours.getCours().getLibelle()}"/></h6>
                        </div>
                    </div>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>