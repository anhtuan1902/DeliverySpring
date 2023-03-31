<%-- 
    Document   : shipperpage
    Created on : Mar 24, 2023, 10:55:08 AM
    Author     : trant
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="text-center mb-3">
    <h1>Danh Sách Shipper</h1>
</div>

<div style="width: 80%" >
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
        <c:forEach items="${shippers}" var="shipper">
            <c:url value="/shipper/${shipper.id}" var="detail"/>
            <div class="col">
                <a href="${detail}" class="card h-100 rounded-4 shadow">
                    <img src="${shipper.avatar}" class="card-img-top p-3" alt="${shipper.id}">
                    <div class="card-body text-center">
                        <h5 class="card-title">${shipper.userId.lastName} ${shipper.userId.firstName}</h5>
                        <p class="card-text">
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                        </p>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
