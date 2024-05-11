// 绑定按钮点击事件
$(document).ready(function () {
    // 分享单击事件
    $("#convertButton").click(function (e) { 
        share(e);
    });
});

function share() {
    // 使用 HTML2Canvas 将当前网页内容转换为 canvas 元素
    html2canvas(document.documentElement).then(function(canvas) {
        // 将 canvas 转换为图片
        var imgData = canvas.toDataURL('image/png');
        
        $.ajax({
            type: 'POST',
            url: SERVER_PATH + "/uploadImage",
            data: {
                imageData: imgData
            },
            success: function(result) {
                var fileName = result.data;
                // 在新建标签页中打开 getShare 页面并传递文件名作为参数
                window.open('http://127.0.0.1:5500/getShare.html?fileName=' + fileName, '_blank');
            }
        });
    });
}


