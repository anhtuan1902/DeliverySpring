<%-- 
    Document   : home
    Created on : Mar 31, 2023, 11:55:41 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="bg-white p-3 mt-3 rounded border shadow" style="width: 70%">
    <div class="d-flex" type="button">
        <div class="p-1">
            <img src="${extrainfo.avatar}" alt="avatar" 
                 class="rounded-circle me-2" 
                 style="width: 38px; height: 38px; object-fit: cover" />
        </div>
        <input type="text" class="form-control rounded-pill border-0 bg-gray d-flex"
               placeholder="What do you want to post?"
               disabled
               data-bs-toggle="modal"
               data-bs-target="#createModal"/>
    </div>

    <div class="modal fade"
         id="createModal"
         tabindex="-1"
         aria-labelledby="createModalLabel"
         aria-hidden="true"
         data-bs-backdrop="false"
         >
        <c:if test="${errMsg != null}">
            <div class="alert alert-danger">${errMsg}</div>
        </c:if>
        <c:url value="/home/posts" var="action" />
        <form:form method="POST" action="${action}" modelAttribute="poster"  enctype="multipart/form-data">

            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <!-- head -->
                    <div class="modal-header align-items-center">
                        <h5
                            class="text-dark text-center w-100 m-0"
                            id="exampleModalLabel"
                            >
                            Create Post
                        </h5>
                        <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                            ></button>
                    </div>
                    <!-- body -->
                    <div class="modal-body">
                        <div class="my-1 p-1">
                            <div class="d-flex flex-column">
                                <!-- name -->
                                <div class="d-flex align-items-center">
                                    <div class="p-2">
                                        <img src="${extrainfo.avatar}"
                                             alt="avatar"
                                             class="rounded-circle"
                                             style="width: 38px; height: 38px; object-fit: cover;"/>
                                    </div>
                                    <div>
                                        <p class="m-0 fw-bold">${userinfo.firstName} ${userinfo.lastName} </p>
                                    </div>
                                </div>
                                <!-- text -->
                                <div class="form-floating my-3">
                                    <form:input path="productName" class="form-control" id="productName" placeholder="Tên sản phẩm" name="productName" />
                                    <label for="productName">Tên sản phẩm</label>
                                    <form:errors path="productName" cssClass="text-danger" />
                                </div>
                                <div class="form-floating my-3">
                                    <form:input path="fromAddress" class="form-control" id="fromAddress" placeholder="Địa chỉ nhận" name="fromAddress" />
                                    <label for="fromAddress">Địa chỉ nhận</label>
                                    <form:errors path="fromAddress" cssClass="text-danger" />
                                </div>
                                <div class="form-floating my-3">
                                    <form:input path="toAddress" class="form-control" id="toAddress" placeholder="Địa chỉ đến" name="toAddress" />
                                    <label for="toAddress">Địa chỉ đến</label>
                                    <form:errors path="toAddress" cssClass="text-danger" />
                                </div>
                                <div class="form-floating my-3">
                                    <form:textarea path="description" class="form-control" id="description" placeholder="Chi tiết sản phẩm" name="description" />
                                    <label for="description">Chi tiết sản phẩm</label>
                                </div>
                                <div class="form-floating">
                                    <form:select class="form-select" id="disId" name="disId" path="disId">
                                        <c:forEach items="${discounts}" var="d">
                                            <form:option value="${d.id}" label="${d.discountTitle}" />
                                        </c:forEach>
                                    </form:select>
                                    <label for="disId" class="form-label">Discount:</label>
                                </div>
                                <div class="form-floating mb-3 mt-3">
                                    <form:input type="file" class="form-control" id="file" path="file" name="file" />
                                    <label for="file">Ảnh sản phẩm</label>
                                </div>
                            </div>
                        </div>

                        <!-- end -->
                    </div>
                    <!-- footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary w-100">
                            Đăng bài
                        </button>
                    </div>

                </div>
            </div>
        </form:form>
    </div>
</div>

<!-- posts -->

<c:forEach items="${posts}" var="p">
    <div class="bg-white p-4 rounded shadow mt-3" id="post${p.id}" style="width: 70%">
        <!-- author -->
        <div class="d-flex justify-content-between">
            <!-- avatar -->
            <div class="d-flex">
                <img src="${p.customerId.avatar}"
                     alt="avatar"
                     class="rounded-circle me-2"
                     style="width: 38px; height: 38px; object-fit: cover"/>
                <div>
                    <p class="m-0 fw-bold">${p.customerId.userId.lastName} ${p.customerId.userId.firstName}</p>
                    <span class="text-muted fs-7">${p.createdDate}</span>
                </div>
            </div>
            <!-- edit -->
            <i class="fas fa-ellipsis-h" type="button" id="post1Menu" data-bs-toggle="dropdown"
               aria-expanded="false"></i>
            <!-- edit menu -->
            <ul class="dropdown-menu border-0 shadow" aria-labelledby="post1Menu">
                <li class="d-flex align-items-center">
                    <a class="dropdown-item d-flex justify-content-around align-items-center fs-7" href="#">
                        Edit Post
                    </a>
                </li>
                <li class="d-flex align-items-center">
                    <c:url value="/api/posts/${p.id}" var="endpoint" />
                    <input  type="button" onclick="deletePost('${endpoint}')" value="Xóa bài viết" class="d-flex fs-7 btn dropdown-item justify-content-around align-items-center" />
                </li>
            </ul>
        </div>
        <!-- post content -->
        <div class="mt-3">
            <!-- content -->
            <div>
                <p>${p.productName}</p>
                <p>Vận chuyển từ: ${p.fromAddress}</p> <p><i class="fa-solid fa-arrow-right"></i> ${p.toAddress}</p> 
                <p>Sẽ giảm giá: ${p.discountId.discountPercent}%</p>
                <c:if test="${p.description != null}">
                    <p>${p.description}</p>
                </c:if>
                <p>Các bạn hãy ra giá $$$ </p>
                <img src="${p.productImg}" alt="${p.productName}"
                     class="img-fluid rounded align-items-center" style="height: 500px; width: 100%"/>
            </div>
            <!-- comments -->
            <div class="post__comment mt-3 position-relative">
                <!-- comments start-->
                <div class="accordion" id="accordionExample">
                    <div class="accordion-item border-0">
                        <!-- comment collapse -->
                        <hr />
                        <!-- comment -->
                        <div class="d-flex justify-content-around">
                            <div
                                class="
                                dropdown-item
                                rounded
                                d-flex
                                justify-content-center
                                align-items-center
                                pointer
                                text-muted
                                p-1
                                "
                                data-bs-toggle="collapse"
                                data-bs-target="#collapsePost1"
                                aria-expanded="false"
                                aria-controls="collapsePost1"
                                >
                                <i class="fas fa-comment-alt me-3"></i>
                                <p class="m-0">Đấu giá</p>
                            </div>
                        </div>
                        <!-- comment expand -->
                        <div id="collapsePost1" class="accordion-collapse collapse"
                             aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                            <hr />
                            <div class="accordion-body">
                                <c:forEach items="${auctions}" var="a">
                                    <c:if test="${a.postId.id == p.id}">
                                        <div class="d-flex align-items-center my-1">
                                            <!-- avatar -->
                                            <img src="${a.shipperId.avatar}"
                                                 alt="avatar" class="rounded-circle me-2"
                                                 style="width: 38px; height: 38px; object-fit: cover;"/>
                                            <!-- comment text -->
                                            <div class="p-3 rounded comment__input w-100">
                                                <!-- comment menu of author -->
                                                <div class="d-flex justify-content-end">
                                                    <!-- icon -->
                                                    <i class="fas fa-ellipsis-h text-blue pointer"
                                                       id="post1CommentMenuButton"
                                                       data-bs-toggle="dropdown"
                                                       aria-expanded="false"></i>
                                                    <!-- menu -->
                                                    <ul class="dropdown-menu border-0 shadow"
                                                        aria-labelledby="post1CommentMenuButton">
                                                        <li class="d-flex align-items-center">
                                                            <a class="dropdown-item d-flex
                                                               justify-content-around
                                                               align-items-center fs-7"
                                                               href="#">
                                                                Edit Comment
                                                            </a>
                                                        </li>
                                                        <li class="d-flex align-items-center">
                                                            <a class="dropdown-item d-flex
                                                               justify-content-around
                                                               align-items-center fs-7"
                                                               href="#">
                                                                Delete Comment
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <p class="fw-bold m-0">${a.shipperId.userId.lastName} ${a.shipperId.userId.firstName}</p>
                                                <div class="row">
                                                    <div class="col-8">
                                                        <p class="m-0 fs-7 bg-gray p-2 rounded">
                                                            ${a.content}
                                                        </p>
                                                        <p class="m-0 fs-7 bg-gray p-2 rounded">
                                                            Gia: ${a.price}
                                                        </p>
                                                        <p class="card-text"><small className="text-muted">${a.createdDate}</small></p>
                                                    </div>
                                                    <div class="col-4 justify-content-md-end">
                                                        <button class="btn btn-primary" type="button">Chap Nhan</button>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                                <!-- create comment -->

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

                                    <!-- input -->
                                    <c:url value="/api/posts/addAuction" var="endpoint" />
                                    <input id="contentAuction" type="text" class="form-control" placeholder="Nhập nội dung"/>
                                    <input id="priceAuction" type="number" class="form-control ms-2" placeholder="Nhập giá"/>
                                    <input type="button" onclick="addAuction('${endpoint}', ${p.id})" value="Đấu giá" class="btn btn-outline-secondary ms-2" />
                                </div>
                                <!-- end -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end -->
            </div>
        </div>
    </div>
</c:forEach>

<script src="<c:url value="/js/action.js" />"></script>
