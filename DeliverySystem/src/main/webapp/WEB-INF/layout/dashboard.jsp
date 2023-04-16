<%-- 
    Document   : dashboard
    Created on : Mar 19, 2023, 9:09:46 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="bg-white shadow" id="sidebar-wrapper">
    <div class="sidebar-heading text-center py-4 primary-text fs-4 fw-bold text-uppercase border-bottom"><i
            class="fas fa-user-secret me-2"></i>Giao hàng TAT</div>
    <div class="list-group list-group-flush my-3">
        <a href="<c:url value="/home/posts" />" class="list-group-item list-group-item-action bg-transparent second-text"><i
                class="fas fa-tachometer-alt me-2"></i>Trang chủ</a>
        <a href="<c:url value="/home/shipper" />" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                class="fas fa-project-diagram me-2"></i>Danh sách người giao hàng</a>
        <a href="<c:url value="/admin/discounts" />" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                class="fas fa-chart-line me-2"></i>Quản lí mã giảm giá</a>
        <a href="<c:url value="/home/shipper/orders" />" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                class="fas fa-paperclip me-2"></i>Danh sách đơn hàng</a>
        <a href="<c:url value="/home/shipper/orders" />" class="list-group-item list-group-item-action bg-transparent second-text fw-bold"><i
                class="fas fa-shopping-cart me-2"></i>Quản lí đơn hàng</a>
        <a href="<c:url value="/logout" />" class="list-group-item list-group-item-action bg-transparent text-danger fw-bold"><i
                class="fas fa-power-off me-2"></i>Logout</a>
    </div>
</div>
