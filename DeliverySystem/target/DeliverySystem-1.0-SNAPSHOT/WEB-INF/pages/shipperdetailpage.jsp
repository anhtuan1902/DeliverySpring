<%-- 
    Document   : shipperdetailpage
    Created on : Mar 25, 2023, 1:16:36 PM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center m-4">Chi tiết người giao hàng</h1>
<div class="card shadow" style="width: 80%">
    <div class="row m-3">
        <div class="col">
            <img class="rounded-circle" src="${shipper.avatar}" alt="${shipper.userId.username}" style="height: 400px; width: 400px"/>
        </div>
        <div class="col">
            <h4 class="text-uppercase text-black-50 my-3">
                Shipper
            </h4>
            <h1 class='display-5'>
                ${shipper.userId.lastName} ${shipper.userId.firstName}
            </h1>
            <h4><span class="badge bg-secondary me-2">CCCD: ${shipper.cmnd}</span></h4>
            <h4><span class="badge bg-secondary me-2">Email: ${shipper.userId.email}</span></h4>
            <p class='lead mt-3'></p>
        </div>
    </div>
    <hr className='my-5' />
    <nav class="mx-2">
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Đánh giá</button>
        </div>
    </nav>
    <div class="m-4 tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">...</div>
    </div>
</div>


