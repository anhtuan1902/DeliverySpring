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
            <c:if test="${shipper.alreadyVerify == true}">
                <c:url value="/home/shipper/${shipper.id}" var="detail"/>
                <div class="col">
                    <div  class="card h-100 rounded-4 shadow">
                        <a href="${detail}">
                            <img src="${shipper.avatar}" class="card-img-top p-3" alt="${shipper.id}" style="height: 300px">
                        </a>
                        <div class="card-body text-center">
                            <h5 class="card-title">${shipper.userId.lastName} ${shipper.userId.firstName}</h5>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
