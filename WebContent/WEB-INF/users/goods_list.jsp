<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>失物管理</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/login.css"> 
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>我的失物信息</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
         
          <div class="layui-card-body">
        
           
          
            <table class="layui-table">
              <colgroup>
               <col width="100">
                <col width="100">
                <col width="200">
                <col width="120">
                <col width="200">
                <col width="120">
                 <col width="120">
                 <col width="150">
                 
                 
                  
                <col>
              </colgroup>
              <thead>
               <tr>
			      <th>序号</th>
			      <th>物品名称</th>
			        <th>物品图片</th>
			      <th>丢失地点</th>
			      <th>物品特征信息</th>
			      <th>丢失状态</th>
			       <th>丢失人</th>
			       <th>丢失时间</th>
			         <th>联系电话</th>
			         <th>操作</th>
			       
			    
			     
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${list}" var="g" >
    <tr>
       <td>${g.id }</td>
      <td>${g.gname }</td>
      	<td style="width:80px;height:80px;"><a href="upload/${g.imgUrl }" target="_blank"><img src="upload/${g.imgUrl }" alt=""  border="0"/></a></td>
      <td>${g.lostPlace }</td>
        <td>${g.info }</td>
      <td>
     <c:choose>
                   <c:when test="${g.status==1}">
                 	已丢失
                   </c:when>
                   <c:otherwise>
                	已找到
                   </c:otherwise></c:choose>
 
      </td>
        <td>${g.user.realname }</td>
    <td>${g.createTime }</td>
     <td>${g.phone }</td>
     
       
       <td>
      
           
    		
    		 <a onclick="return del();" href="GoodsServlet?method=deleteGoodsAct&id=${g.id}&type=${type}" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">取消挂失</i></a></td>
    		
    </tr>
   </c:forEach>
              </tbody>
            </table>
          
          </div>
        </div>
      </div>
      
      
<script src="layui/layui.js"></script>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form;
  
 

});
</script>
<script>
function del(){
	if(confirm("确定取消挂失吗？")){
		return  true;
		//alert("删除成功");
	}else{
		return false;
	};
} 

</script>
</body>
</html>