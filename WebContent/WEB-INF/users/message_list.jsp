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
<title>留言管理</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/login.css"> 
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>留言信息</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
       
          <div class="layui-card-body">
         
          
            <table class="layui-table">
              <colgroup>
                <col width="150">
                <col width="150">
                
                <col width="400">
                <col width="200">
                 <col width="200">
               
              </colgroup>
              <thead>
               <tr>
			      <th>序号</th>
			      <th>留言人</th>
			      <th>留言内容</th>
			      <th>留言时间</th>
			      
			       
			       <th>操作</th>
			     
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${list}" var="m" >
    <tr>
       <td>${m.id }</td>
      <td>${m.user.realname }</td>
      <td>${m.content }</td>
       
    <td>${m.createTime }</td>
   
     
       
       <td>
      
             <a href="MessageServlet?method=queryMessageAct&id=${m.id}" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon">&#xe642;</i></a>
    		
    		 <a onclick="return del();" href="MessageServlet?method=deleteMessageAct&id=${m.id}&type=${type}&mname=${username}" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">&#xe640;</i></a>
    		 
    		 </td>
    		
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
	if(confirm("确定删除吗")){
		return  true;
		//alert("删除成功");
	}else{
		return false;
	};
} 

</script>
</body>
</html>