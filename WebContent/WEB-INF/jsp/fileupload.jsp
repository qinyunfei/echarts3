<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>qwer</title>

 <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>


<script>

    $(function () {
        $("#saveImg").click(function () {
                ajaxFileUpload();
                return true;
        })
        
        
        

    })
/**
 * ajaxFileUpload    JQuery异步上传插件
 */
    function ajaxFileUpload() {
        $.ajaxFileUpload
        (
            {
                url: "${pageContext.request.contextPath }/reports/upload", //用于文件上传的服务器端请求地址
                type: 'post',
                data: { gameId: 'asa' },
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'fileImg', //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data)  //服务器成功响应处理函数
                {
                	 $("#tpxs").empty();
                     if(data!=""){
		                 for(var i=0;i<data.length;i++){
		                	 tpxs(data[i].lists);
		                	 
		           		  }
		                 }
	                 
                }
            }
        )
        return false;
}
    
	  function tpxs(obj){
		  var data="";
		   for(var i=0;i<obj.length;i++){
			   data=data+"<td>"+obj[i]+"</td>"
     		  }
  		$("#tpxs").append('<tr>'+data+"</tr>");
  	              }
</script>
</head>
<body>
		 <input id="fileImg" type="file" name="fileImg" size="80" />
         <input id="saveImg" type="button" value="上传"  />
          
          
         <table border="1"  width="500"  >
		 <tbody id="tpxs">
		 </tbody>
		 </table>
</body>
</html>