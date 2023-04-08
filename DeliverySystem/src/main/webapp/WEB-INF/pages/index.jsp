<%-- 
    Document   : index
    Created on : Mar 19, 2023, 3:22:29 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div class="login-form">
    <h2>Welcome to TAT Delivery</h2>
    <a href="<c:url value="/login" />" class="social-button" id="facebook-connect"> <span>Sign in</span></a>
    <a href="<c:url value="/signup" />" class="social-button" id="google-connect"> <span>Sign up</span></a>
</div>


