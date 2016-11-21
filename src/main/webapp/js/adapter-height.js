// 计算页面的实际高度，iframe自适应会用到
function calcPageHeight(doc) {
    var cHeight = Math.max(doc.body.clientHeight, doc.documentElement.clientHeight);
    var sHeight = Math.max(doc.body.scrollHeight, doc.documentElement.scrollHeight);
    return Math.max(cHeight, sHeight);
}

window.onload = function() {
    var iframe = parent.document.getElementById('mainFrame');
    if(iframe){
        var height = calcPageHeight(document);
        if(height < 680){
            height = 680;
        }
        iframe.style.height = height + 'px'
    }
}