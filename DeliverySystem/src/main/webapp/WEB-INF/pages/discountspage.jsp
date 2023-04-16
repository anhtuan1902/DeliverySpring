<%-- 
    Document   : discountspage
    Created on : Apr 6, 2023, 11:40:53 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:choose>
    <c:when test="${userinfo.userRole == 'ADMIN_ROLE'}">
        <h1 class="text-info text-center">QUẢN TRỊ MÃ GIẢM GIÁ</h1>

        <div class="bg-white p-3 mt-3 rounded border shadow" style="width: 70%">
            <c:if test="${errMsg != null}">
                <div class="alert alert-danger">${errMsg}</div>
            </c:if>
            <c:url value="/admin/discounts" var="action" />
            <form:form method="POST" action="${action}"
                       modelAttribute="discount">

                <form:errors path="*" element="div" cssClass="alert alert-danger" />
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" id="name" placeholder="Tên mã giảm giá" path="discountTitle" name="discountTitle" />
                    <label for="name">Tên mã giảm giá</label>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input type="number" class="form-control" id="price" placeholder="Giá sản phẩm" path="discountPercent" name="discountPercent" />
                    <label for="price">Phần trăm giảm giá</label>
                </div>
                <div class="form-floating mt-2">
                    <input type="submit" value="Thêm sản phẩm" class="btn btn-success" />
                </div>
            </form:form>
        </div>


        <div class="bg-white p-4 rounded shadow mt-3" style="width: 70%">
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Tên mã giảm giá</th>
                    <th>Phần trăm giảm giá</th>
                    <th></th>
                </tr>
                <c:forEach items="${discounts}" var="d">
                    <tr id="discount${d.id}">
                        <td>${d.id}</td>
                        <td>${d.discountTitle}</td>
                        <td>${d.discountPercent}%</td>
                        <td>
                            <div id="spinner${d.id}" style="display:none" class="spinner-border text-primary"></div>
                            <c:url value="/api/discounts/${d.id}" var="endpoint" />
                            <input  type="button" onclick="deleteDiscount('${endpoint}', ${d.id})" value="Xóa" class="btn btn-danger" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script src="<c:url value="/js/action.js" />"></script>
    </c:when>
    <c:otherwise>
        <h1>Bạn không có quyền truy cập</h1>
    </c:otherwise>   
</c:choose>
