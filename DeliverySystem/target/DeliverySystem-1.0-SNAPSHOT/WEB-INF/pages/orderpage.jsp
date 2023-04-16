<%-- 
    Document   : orderpage
    Created on : Apr 16, 2023, 3:03:06 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:choose>
    <c:when test="${userinfo.userRole == 'SHIPPER_ROLE'}">
        <h1 class="text-info text-center">Đơn hàng</h1>

        <div class="bg-white p-4 rounded shadow mt-3" style="width: 70%">
            <h3 class="text-center">Đơn hàng đang thực hiện</h3>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Tên sản phẩm</th>
                    <th>Trạng thái sản phẩm</th>
                    <th>Địa chỉ nhận hàng</th>
                    <th>Địa chỉ giao hàng</th>
                    <th>Ngày cập nhật</th>
                    <th></th>
                </tr>
                <c:url value="/api/updateorder" var="endpoint" />
                <c:forEach items="${orders}" var="o">
                    <c:if test="${o.statusOrder != 'ĐÃ GIAO HÀNG'}">
                        <tr id="discount${o.id}">
                            <td>${o.id}</td>
                            <td>${o.auctionId.postId.productName}</td>
                            <td><select class="form-select" id="status${o.id}" aria-label="Default select example">
                                    <option selected>${o.statusOrder}</option>
                                    <option value="CHƯA NHẬN HÀNG">CHƯA NHẬN HÀNG</option>
                                    <option value="ĐÃ NHẬN HÀNG">ĐÃ NHẬN HÀNG</option>
                                    <option value="ĐÃ GIAO HÀNG">ĐÃ GIAO HÀNG</option>
                                </select></td>
                            <td>${o.auctionId.postId.fromAddress}</td>
                            <td>${o.auctionId.postId.toAddress}</td>
                            <td>${o.updatedDate}</td>
                            <td>                   
                                <div id="spinner${o.id}" style="display:none" class="spinner-border text-primary"></div>
                                <input  type="button" onclick="updateOrder('${endpoint}', ${o.id})" value="Cập nhật" class="btn btn-danger" />
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>

        <div class="bg-white p-4 rounded shadow mt-3" style="width: 70%">
            <h3 class="text-center">Đơn hàng đã thực hiện</h3>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Tên sản phẩm</th>
                    <th>Trạng thái sản phẩm</th>
                    <th>Địa chỉ nhận hàng</th>
                    <th>Địa chỉ giao hàng</th>
                    <th>Ngày cập nhật</th>
                </tr>

                <c:forEach items="${orders}" var="o">
                    <c:if test="${o.statusOrder == 'ĐÃ GIAO HÀNG'}">
                        <tr id="discount${o.id}">
                            <td>${o.id}</td>
                            <td>${o.auctionId.postId.productName}</td>
                            <td><select class="form-select" aria-label="Default select example">
                                    <option selected>${o.statusOrder}</option>
                                    <option value="CHƯA NHẬN HÀNG">CHƯA NHẬN HÀNG</option>
                                    <option value="ĐÃ NHẬN HÀNG">ĐÃ NHẬN HÀNG</option>
                                    <option value="ĐÃ GIAO HÀNG">ĐÃ GIAO HÀNG</option>
                                </select></td>
                            <td>${o.auctionId.postId.fromAddress}</td>
                            <td>${o.auctionId.postId.toAddress}</td>
                            <td>${o.updatedDate}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>

    </c:when>
    <c:when test="${userinfo.userRole == 'ADMIN_ROLE'}">
        <h1 class="text-info text-center">Đơn hàng</h1>

        <div class="bg-white p-4 rounded shadow mt-3" style="width: 70%">
            <h3 class="text-center">Các đơn hàng của nhân viên giao hàng</h3>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Tên sản phẩm</th>
                    <th>Trạng thái sản phẩm</th>
                    <th>Địa chỉ nhận hàng</th>
                    <th>Địa chỉ giao hàng</th>
                    <th>Nhân viên giao hàng</th>
                    <th>Người yêu cầu</th>
                    <th>Ngày cập nhật</th>
                    <th></th>
                </tr>
                <c:url value="/api/updateorder" var="endpoint" />
                <c:forEach items="${orders}" var="o">
                    <tr id="discount${o.id}">
                        <td>${o.id}</td>
                        <td>${o.auctionId.postId.productName}</td>
                        <td><select class="form-select" id="status${o.id}" aria-label="Default select example">
                                <option selected>${o.statusOrder}</option>
                                <option value="CHƯA NHẬN HÀNG">CHƯA NHẬN HÀNG</option>
                                <option value="ĐÃ NHẬN HÀNG">ĐÃ NHẬN HÀNG</option>
                                <option value="ĐÃ GIAO HÀNG">ĐÃ GIAO HÀNG</option>
                            </select></td>
                        <td>${o.auctionId.postId.fromAddress}</td>
                        <td>${o.auctionId.postId.toAddress}</td>
                        <td>${o.shipperId.userId.lastName} ${o.shipperId.userId.firstName}</td>
                        <td>${o.auctionId.postId.customerId.userId.lastName} ${o.auctionId.postId.customerId.userId.firstName}</td>
                        <td>${o.updatedDate}</td>
                        <td>                   
                            <div id="spinner${o.id}" style="display:none" class="spinner-border text-primary"></div>
                            <input  type="button" onclick="updateOrder('${endpoint}', ${o.id})" value="Cập nhật" class="btn btn-danger" />
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <h1>Bạn không có quyền truy cập</h1>
    </c:otherwise>
</c:choose>

<script src="<c:url value="/js/action.js" />"></script>