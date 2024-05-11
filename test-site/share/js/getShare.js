$(document).ready(function () {
    var fileName=$.getUrlParam("fileName");
    if(!fileName){
       false;
    }
    getShare(fileName);
});

function getShare(fileName){
    
  
    var imageUrl = SERVER_PATH + "/share/image/"+fileName;

    console.log(imageUrl);
            
    // 显示长图
    var imgElement = document.createElement('img');
    imgElement.style.width = '100%';
    imgElement.style.height = '100%';

    imgElement.src = imageUrl;
    document.body.appendChild(imgElement);
            
    // 提供下载链接给用户
    // var downloadLink = document.createElement('a');
    // downloadLink.href = imageUrl;
    // downloadLink.download = 'image_file.jpg';
    // downloadLink.innerHTML = '下载图片';
    // document.body.appendChild(downloadLink);
    
       
}
