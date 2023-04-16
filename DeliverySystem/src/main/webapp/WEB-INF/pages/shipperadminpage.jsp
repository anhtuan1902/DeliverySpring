<%-- 
    Document   : shipperadminpage
    Created on : Apr 12, 2023, 11:46:40 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:choose>
    <c:when test="${userinfo.userRole == 'ADMIN_ROLE'}">
        <h1 class="text-info text-center">QUẢN TRỊ NHÂN VIÊN GIAO HÀNG</h1>
        <c:url value="/api/shipper/verified" var="endpoint" />
        <div class="bg-white p-4 rounded shadow mt-3" style="width: 80%">
            <h3 class="text-center">Danh sách tất cả nhân viên</h3>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Tên nhân viên</th>
                    <th>Hình ảnh</th>
                    <th>CMND</th>
                    <th>Verify</th>
                    <th></th>
                </tr>
                <c:forEach items="${shippers}" var="s">
                    <tr id="shipper${s.id}">
                        <td>${s.id}</td>
                        <td>${s.userId.lastName} ${s.userId.firstName}</td>
                        <td><img src="${s.avatar}" alt="avatar" 
                                 class="rounded-circle me-2" 
                                 style="width: 50px; height: 50px; object-fit: cover" /></td>
                        <td>${s.cmnd}</td>
                        <td>
                            <c:choose>
                                <c:when test="${s.alreadyVerify}">
                                    <i class="fa-solid fa-check"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="fa-solid fa-xmark"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${s.alreadyVerify}">
                                    <div id="spinner${s.id}" style="display:none" class="spinner-border text-primary"></div>                          
                                    <input  type="button" onclick="updateVerified('${endpoint}', ${s.id})" value="Bỏ xác nhận" class="btn btn-danger" />
                                </c:when>
                                <c:otherwise>
                                    <div id="spinner${s.id}" style="display:none" class="spinner-border text-primary"></div>
                                    <input  type="button" onclick="updateVerified('${endpoint}', ${s.id})" value="Xác nhận" class="btn btn-danger" />
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="bg-white p-4 rounded shadow mt-3" style="width: 80%">
            <h3 class="text-center">Nhân viên chưa được xác nhận</h3>
            <table class="table">
                <tr>
                    <th>Id</th>
                    <th>Tên nhân viên</th>
                    <th>Hình ảnh</th>
                    <th>CMND</th>
                    <th>Verify</th>
                    <th></th>
                </tr>
                <c:forEach items="${shippers}" var="s">
                    <c:if test="${!s.alreadyVerify}">
                        <tr id="shipper${s.id}">
                            <td>${s.id}</td>
                            <td>${s.userId.lastName} ${s.userId.firstName}</td>
                            <td><img src="${s.avatar}" alt="avatar" 
                                     class="rounded-circle me-2" 
                                     style="width: 50px; height: 50px; object-fit: cover" /></td>
                            <td>${s.cmnd}</td>
                            <td>
                                <i class="fa-solid fa-xmark"></i>
                            </td>
                            <td>
                                <div id="spinner${s.id}" style="display:none" class="spinner-border text-primary"></div>
                                <input  type="button" onclick="updateVerified('${endpoint}', ${s.id})" value="Xác nhận" class="btn btn-danger" />
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <script src="<c:url value="/js/action.js" />"></script>
    </c:when>
    <c:otherwise>
        <h1>Bạn không có quyền truy cập</h1>
    </c:otherwise>
</c:choose>