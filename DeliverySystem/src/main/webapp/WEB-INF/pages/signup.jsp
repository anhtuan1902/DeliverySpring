<%-- 
    Document   : signupcustomer
    Created on : Mar 29, 2023, 11:32:44 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="login-box mx-4">
    <h2>Đăng kí</h2>
    <c:if test="${errMsg != null}">
        <div class="alert alert-danger">${errMsg}</div>
    </c:if>
    <c:url value="/signup" var="action" />
    <form:form method="POST" action="${action}" modelAttribute="user">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <div class="user-box">
            <form:input path="firstName" class="form-control" id="firstName" placeholder="Tên" name="firstName" />
            <label for="firstName">Tên</label>
        </div>
        <div class="user-box">
            <form:input path="lastName" class="form-control" id="lastName" placeholder="Họ & Tên đệm" name="lastName" />
            <label for="lastName">Họ và tên đệm</label>
        </div>
        <div class="user-box">
            <form:input path="username" class="form-control" id="username" placeholder="Tên đăng nhập" name="username" />
            <label for="username">Tên đăng nhập</label>
        </div>
        <div class="user-box">
            <form:password path="password" class="form-control" id="password" placeholder="Mật khẩu" name="password" />
            <label for="password">Mật khẩu</label>
        </div>
        <div class="user-box">
            <form:password path="confirmPassword" class="form-control" id="confirmPassword" placeholder="Nhập lại mật khẩu" name="confirmPassword" />
            <label for="confirmPassword">Nhập lại mật khẩu</label>
        </div>
        <div class="user-box">
            <form:input path="email" class="form-control" id="email" placeholder="Email" name="email" />
            <label for="email">Email</label>
        </div>
        <div class="user-box">
            <form:select path="userRole" class="form-control">  
                <form:option value="CUSTOMER_ROLE" label="CUSTOMER"/>  
                <form:option value="SHIPPER_ROLE" label="SHIPPER"/>  
            </form:select>  
        </div>
        <button class="btn btn-info w-100" type="submit"">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Đăng kí
        </button>
    </form:form>
</div>
