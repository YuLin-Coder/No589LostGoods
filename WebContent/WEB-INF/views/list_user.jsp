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
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/login.css"> 
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>用户列表</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
       
          <div class="layui-card-body"> 
<a href="UserServlet?method=userListAct2" class="layui-btn layui-btn-primary" >返回列表</a>
           
             <span style="color:red">${msg}</span>
            <table class="layui-table">
              <colgroup>
                <col width="150">
                <col width="200">
                  <col width="200">
                <col width="200">
                 <col width="200">
                 <col width="200">
                  <col width="150">
                <col>
              </colgroup>
              <thead>
               <tr>
			      <th>序号</th>
			      <th>用户名</th>
			      <th>真实姓名</th>
			      <th>密码</th>
			      <th>性别</th>
			      <th>用户类型</th>
			        <c:if test="${type==2}">
			       <th>操作</th>
			       </c:if>
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${list}" var="user" >
    <tr>
       <td>${user.id }</td>
      <td>${user.username }</td>
         <td>${user.realname }</td>
      <td>${user.password }</td>
      <td>
     <c:choose>
                   <c:when test="${user.sex==1}">
                 	男
                   </c:when>
                   <c:otherwise>
                	女
                   </c:otherwise></c:choose>
 
      <td><c:choose>
                   <c:when test="${user.type==1}">
                 	学生
                   </c:when>
                   <c:otherwise>
                	管理员
                   </c:otherwise></c:choose>
 
      </td>
       <c:if test="${type==2}">
       <td>
      
             <a href="UserServlet?method=queryUserAct&id=${user.id}" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon">&#xe642;</i></a>
    		
    		 <a href="UserServlet?method=deleteUserAct&id=${user.id}" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">&#xe640;</i></a></td>
    		</c:if>
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
  
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
</script>
      
</body>
</html>