<%-- 
    Document   : signupcustomer
    Created on : Mar 29, 2023, 11:32:44 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="login-box">
    <h2>Sign Up Customer</h2>
    <c:url value="/signup" var="action" />
    <form:form method="POST" action="${action}" modelAttribute="user">
        <div class="user-box">
            <form:input path="firstName" class="form-control" id="firstName" placeholder="Tên" name="firstName" />
            <label for="firstName">First Name</label>
            <form:errors path="firstName" cssClass="text-danger" />
        </div>
        <div class="user-box">
            <form:input path="lastName" class="form-control" id="lastName" placeholder="Họ & Tên đệm" name="lastName" />
            <label for="lastName">Last Name</label>
            <form:errors path="lastName" cssClass="text-danger" />
        </div>
        <div class="user-box">
            <form:input path="username" class="form-control" id="username" placeholder="Tên đăng nhập" name="username" />
            <label for="username">Username</label>
            <form:errors path="username" cssClass="text-danger" />
        </div>
        <div class="user-box">
            <label for="password">
                <spring:message code="user.password" />
            </label>
            <form:password path="password" class="form-control" id="password" placeholder="Mật khẩu" name="password" />
            <label for="password">Password</label>
            <form:errors path="password" cssClass="text-danger" />
        </div>
        <div class="user-box">
            <label for="confirmPassword">
                <spring:message code="user.confirmPassword" />
            </label>
            <form:password path="confirmPassword" class="form-control" id="confirmPassword" placeholder="Nhập lại mật khẩu" name="confirmPassword" />
            <label for="confirmPassword">Repeat Password</label>
            <form:errors path="confirmPassword" cssClass="text-danger" />
        </div>
        <div class="user-box">
            <form:input path="email" class="form-control" id="email" placeholder="Nhập lại mật khẩu" name="email" />
            <label for="email">Email</label>
            <form:errors path="email" cssClass="text-danger" />
        </div>
        <form:form modelAttribute="customer" enctype="multipart/form-data">
            <div class="user-box">
                <form:input type="file" class="form-control" id="avatar" path="avatar" name="avatar" />
                <label for="avatar" class="pb-4">Ảnh đại diện</label>
            </div>
        </form:form>
        <div>
            <button type="submit" class="btn btn-info w-100">
                Post
            </button>
        </div>  
    </form:form>
</div>
