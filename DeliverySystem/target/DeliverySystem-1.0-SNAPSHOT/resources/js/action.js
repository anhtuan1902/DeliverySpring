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
    if (confirm("Bạn chắc chắn xóa?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204){
                location.reload();
                alert("Đã xóa thành công");
            }else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
        });
    }
}

function addAuction(endpoint, postId) {
    fetch(endpoint, {
        method: "post",
        body: JSON.stringify({
            "content": document.getElementById("contentAuction").value,
            "price": document.getElementById("priceAuction").value,
            "postId": postId
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => {
        if (res.status === 204){
                location.reload();
            }else
                alert("Hệ thống đang có lỗi, vui lòng quay lại sau!");
    });
}