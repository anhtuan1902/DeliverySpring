<%-- 
    Document   : customerforminfo
    Created on : Apr 2, 2023, 8:05:35 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="login-box mx-4">
    <h2>Thông tin người dùng</h2>
    <c:if test="${errMsg != null}">
        <div class="alert alert-danger">${errMsg}</div>
    </c:if>
    <c:url value="/customerform" var="action" />
    <form:form method="POST" action="${action}" modelAttribute="customer" enctype="multipart/form-data">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <div class="user-box">
            <form:input type="file" class="form-control" id="avatar" path="file" name="file" />
            <label for="avatar" class="pb-4">Ảnh đại diện</label>
        </div>
        <button class="btn btn-info w-100" style="margin-top: 2px;" type="submit">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Đăng kí
        </button>
    </form:form>
</div>
