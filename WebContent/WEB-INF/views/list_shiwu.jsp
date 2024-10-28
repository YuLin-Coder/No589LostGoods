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
  <legend>拾物信息</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
      
          <div class="layui-card-body">
          <a href="ShiwuServlet?method=ShiwuListAct" class="layui-btn layui-btn-primary" >返回列表</a>
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
                  <col>
              </colgroup>
              <thead>
               <tr>
			     <th>序号</th>
			      <th>拾物人</th>
			      <th>物品名称</th>
			      <th>物品图片</th>
			      <th>物品描述</th>
			      <th>拾到地点</th>
			      <th>拾到时间</th>
			      <th>联系电话</th>
			     
			        <th>操作</th>
			       
			      
			     
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${list}" var="s" >
    <tr>
       <td>${s.id }</td>
      <td>${s.user.realname }</td>
      <td>${s.wpname }</td>

       	<td style="width:80px;height:80px;" ><a href="upload/${s.imgUrl }" target="_blank"><img src="upload/${s.imgUrl }" alt=""  border="0"/></a></td>
    
        <td>${s.detail }</td>
    <td>${s.place }</td>
     <td>${s.createTime }</td>
      <td>${s.phone }</td>
       <c:if test="${type==1 }">
         <td>
        <a href="MeetServlet?method=toAddMeetAct&wpname=${s.wpname}&u_id=${s.u_id}" class="layui-btn layui-btn-sm layui-btn-normal">认领</a>
        </td>
        </c:if>
       <c:if test="${type==2 }">
       
       <td>
        <a href="ShiwuServlet?method=queryShiwuAct&id=${s.id}" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon">&#xe642;</i></a>
       <a onclick="return del();" href="ShiwuServlet?method=deleteShiwuAct&id=${s.id}&type=${type}&u_id=${s.u_id}" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">&#xe640;</i></a></td>
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


function del(){
	if(confirm("确定取消删除吗")){
		return  true;
		//alert("删除成功");
	}else{
		return false;
	};
} 


</script>
</body>
</html>