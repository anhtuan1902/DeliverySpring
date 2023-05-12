<%-- 
    Document   : shipperdetailpage
    Created on : Mar 25, 2023, 1:16:36 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="/api/shipper/${shipper.id}/rating" var="endpoint1" />

<h1 class="text-center m-4">Chi tiết người giao hàng</h1>
<div class="card shadow my-4" style="width: 80%">
    <div class="row m-3">
        <div class="col">
            <img class="rounded-circle" src="${shipper.avatar}" alt="${shipper.userId.username}" style="height: 400px; width: 90%"/>
        </div>
        <div class="col">
            <h4 class="text-uppercase text-black-50 my-3">
                Shipper
            </h4>
            <h1 class='display-5'>
                ${shipper.userId.lastName} ${shipper.userId.firstName}
            </h1>
            <h4><span class="badge bg-secondary my-4">CCCD: ${shipper.cmnd}</span></h4>
            <h4><span class="badge bg-secondary me-2">Email: ${shipper.userId.email}</span></h4>
            <h4 class="mt-4">${statisRate} <i class="fa-solid fa-star"></i></h4>
                <c:if test="${userinfo.userRole == 'CUSTOMER_ROLE'}">
                    <div id="rating">
                        <input type="radio" id="star5" name="rating" value="5" onclick="addRate('${endpoint1}', '5')"/>
                        <label class = "full" for="star5" title="Awesome - 5 stars"></label>

                        <input type="radio" id="star4" name="rating" value="4" onclick="addRate('${endpoint1}', '4')"/>
                        <label class = "full" for="star4" title="Pretty good - 4 stars"></label>


                        <input type="radio" id="star3" name="rating" value="3" onclick="addRate('${endpoint1}', '3')"/>
                        <label class = "full" for="star3" title="Meh - 3 stars"></label>


                        <input type="radio" id="star2" name="rating" value="2" onclick="addRate('${endpoint1}', '2')" />
                        <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>


                        <input type="radio" id="star1" name="rating" value="1" onclick="addRate('${endpoint1}', '1')"/>
                        <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                    </div>
            </c:if>

        </div>
    </div>
    <hr className='my-5' />
    <nav class="mx-2">
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Đánh giá</button>
        </div>
    </nav>
    <div class="m-4 tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">
            <c:if test="${userinfo.userRole == 'CUSTOMER_ROLE'}">
                <div class="d-flex my-1">
                    <!-- avatar -->
                    <div>
                        <img
                            src="${extrainfo.avatar}"
                            alt="avatar"
                            class="rounded-circle me-2"
                            style="
                            width: 38px;
                            height: 38px;
                            object-fit: cover;
                            "
                            />
                    </div>
                    <c:url value="/api/shipper/${shipper.id}/comments" var="endpoint2" />
                    <!-- input -->
                    <input id="contentId" type="text" class="form-control" placeholder="Nhập nội dung"/>
                    <input type="button" onclick="addComment('${endpoint2}', ${shipper.id})" value="Bình luận" class="btn btn-outline-secondary ms-2" />
                </div>
            </c:if>
            <!-- comments -->
            <hr />
            <div class="accordion-body">
                <c:forEach items="${comments}" var="c">
                    <div class="d-flex align-items-center my-1">
                        <!-- avatar -->
                        <img src="${c.creatorId.avatar}"
                             alt="avatar" class="rounded-circle me-2"
                             style="width: 38px; height: 38px; object-fit: cover;"/>
                        <!-- Auction text -->
                        <div class="p-3 rounded comment__input w-100">
                            <!-- Auction menu of author -->
                            <div class="d-flex justify-content-end">
                                <!-- icon -->
                                <i class="fas fa-ellipsis-h text-blue pointer"
                                   id="post1CommentMenuButton"
                                   data-bs-toggle="dropdown"
                                   aria-expanded="false"></i>
                                <!-- menu -->
                                <c:url value="/api/shipper/${c.id}/comments" var="endpoint3" />
                                <ul class="dropdown-menu border-0 shadow"
                                    aria-labelledby="post1CommentMenuButton">
                                    <li class="d-flex align-items-center">
                                        <input onclick="deleteComment('${endpoint3}')" type="button" class="dropdown-item d-flex
                                               justify-content-around
                                               align-items-center fs-7"
                                               value="Xóa bình luận"/>
                                    </li>
                                </ul>
                            </div>
                            <p class="fw-bold m-0">${c.creatorId.userId.lastName} ${c.creatorId.userId.firstName}</p>
                            <div class="row">
                                <div class="col-8">
                                    <p class="m-0 fs-7 bg-gray p-2 rounded">
                                        ${c.content}
                                    </p>
                                    <p class="card-text"><small className="text-muted">${c.createdDate}</small></p>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>



<script>
    window.onload = function () {
        loadRate('${rate.rate}');
    };
</script>

<script src="<c:url value="/js/action.js" />"></script>