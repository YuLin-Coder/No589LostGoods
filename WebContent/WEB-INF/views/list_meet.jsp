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
<title>校园失物招领系统</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/login.css"> 
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>认领信息</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
      
          <div class="layui-card-body">
          <a href="MeetServlet?method=meetListAct2" class="layui-btn layui-btn-primary" >返回列表</a>
           <span style="color:red">${msg}</span>
           
           
          
            <table class="layui-table">
              <colgroup>
                <col width="70">
                <col width="120">
                <col width="130">
                <col width="150">
                <col width="280">
                 <col width="120">
                 <col width="200">
                  <col width="200">
                
              </colgroup>
              <thead>
               <tr>
			      <th>序号</th>
			      <th>认领物品</th>
			       
			      <th>认领人</th>
			      <th>备注</th>
			      <th>联系方式</th>
			       <th>状态</th>
			       <th>认领时间</th>
			      
			       
			       <th>操作</th>
			     
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${list}" var="m" >
    <tr>
       <td>${m.id }</td>
      <td>${m.wpname}</td>
      <td>${m.user.realname }</td>
        <td>${m.bz }</td>
     
    
        <td>${m.phone }</td>
         <td>
         
       <c:if test="${m.status==0 }">
       <button  class="layui-btn layui-btn-xs layui-btn-danger">未通过</button>
       </c:if>
       <c:if test="${m.status==1 }">
       <button  class="layui-btn layui-btn-xs layui-btn-normal">审核中</button>
       </c:if>
        <c:if test="${m.status==2 }">
       <button  class="layui-btn layui-btn-xs layui-btn">审核通过</button>
       </c:if>
         </td>
    <td>${m.rlsj }</td>
 
       <c:if test="${type==2}">
       <td>
       <a href="MeetServlet?method=passAct&id=${m.id}&type=${type}" class="layui-btn layui-btn-xs layui-btn">通过</a>
              <a href="MeetServlet?method=disAct&id=${m.id}&type=${type}" class="layui-btn layui-btn-xs layui-btn-danger">拒绝</a>
             <a href="MeetServlet?method=queryMeetAct&id=${m.id}" class="layui-btn layui-btn-xs layui-btn-normal"><i class="layui-icon">编辑</i></a>
    		
    		 <a onclick="return del();" href="MeetServlet?method=deleteMeetAct&id=${m.id}&type=${type}" class="layui-btn layui-btn-xs layui-btn-danger"><i class="layui-icon">删除</i></a></td>
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