/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function deleteDiscount(endpoint, id) {
    if (confirm("Bạn chắc chắn xóa?") === true) {
        let s = document.getElementById(`spinner${id}`);
        s.style.display = "block";

        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            s.style.display = "none";
            console.info(res);
            if (res.status === 204) {
                document.getElementById(`discount${id}`).style.display = "none";
            } else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
        });
    }
}

function deletePost(endpoint) {
    if (confirm("Bạn chắc chắn xác nhận?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                location.reload();
                alert("Đã xóa thành công");
            } else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
        });
    }
}

function addAuction(endpoint, postId) {
    fetch(endpoint, {
        method: "POST",
        body: JSON.stringify({
            content: document.getElementById(`content${postId}`).value,
            price: document.getElementById(`price${postId}`).value,
            postId: postId
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => {

        location.reload();

    });
}

function deleteAuction(endpoint) {
    if (confirm("Bạn chắc chắn xác nhận?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                location.reload();
                alert("Đã xóa thành công");
            } else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
        });
    }
}

function chooseRole() {
    var role = document.getElementById("role").value;

    if (role === 'SHIPPER_ROLE') {
        document.getElementById("CMND").hidden = false;
    } else {
        document.getElementById("CMND").hidden = true;
    }
}

function loadRate(rate) {
    if (rate > 0) {
        var r = rate.toString();
        document.getElementById(`star${r}`).checked = true;
    }
}


function addRate(endpoint, rate) {
    if (confirm("Bạn chắc chắn đánh giá?") === true) {
        fetch(endpoint, {
            method: "POST",
            body: JSON.stringify({
                rate: document.getElementById(`star${rate}`).value
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            location.reload();
        });
    }

}

function addComment(endpoint, shipperId) {
    fetch(endpoint, {
        method: "post",
        body: JSON.stringify({
            content: document.getElementById("contentId").value,
            id: shipperId
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => {
        location.reload();
    });
}

function deleteComment(endpoint) {
    if (confirm("Bạn chắc chắn xóa?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                location.reload();
                alert("Đã xóa thành công");
            } else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
        });
    }
}

function updateVerified(endpoint, shipperId) {
    if (confirm("Bạn chắc chắn xác nhận?") === true) {
        fetch(endpoint, {
            method: "PATCH",
            body: JSON.stringify({
                id: shipperId
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            location.reload();
        });
    }
}

function addOrder(endpoint, auctionId, shipperId, postId) {
    if (confirm("Bạn chắc chắn xác nhận?") === true) {
        fetch(endpoint, {
            method: "POST",
            body: JSON.stringify({
                auctionId: auctionId,
                shipperId: shipperId
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            deletePost(`http://localhost:8080/DeliverySystem/api/posts/${postId}`);
            location.reload();
        });
    }
}

function updateOrder(endpoint, orderId) {
    if (confirm("Bạn chắc chắn xác nhận?") === true) {
        fetch(endpoint, {
            method: "PATCH",
            body: JSON.stringify({
                orderId: orderId,
                status: document.getElementById(`status${orderId}`).value
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            location.reload();
        });
    }
}
