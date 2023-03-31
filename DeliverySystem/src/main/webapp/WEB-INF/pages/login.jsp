<%-- 
    Document   : login
    Created on : Mar 29, 2023, 6:10:58 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="login-box">
    <h2>Login</h2>

    <c:if test="${param.error != null}">
        <div class="alert alert-danger">
            <spring:message code="user.login.error1" />
        </div> 
    </c:if>
    <c:if test="${param.accessDenied != null}">
        <div class="alert alert-info">
            <spring:message code="user.login.error2" />
        </div> 
    </c:if>

    <spring:url value="/login" var="action" />
    <form action="${action}" method="POST">
        <div class="user-box">
            <input name="username" id="usernameId" required="true" >
            <label for="usernameId"><spring:message code="user.username" /></label>
        </div>
        <div class="user-box">
            <input type="password" name="password" id="passwordId" required="true">
            <label for="usernameId"><spring:message code="user.password" /></label>
        </div>
        <button class="btn btn-info w-100" type="submit">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Submit
        </button>

    </form>
</div>
