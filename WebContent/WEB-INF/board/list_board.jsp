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
<title>校园失物管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/login.css"> 
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>公告列表</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
        
          <div class="layui-card-body">
              <a href="BoardServlet?method=BoardListAct" class="layui-btn layui-btn-primary" >返回列表</a>
           
          
            <table class="layui-table">
              <colgroup>
                <col width="150">
                <col width="200">
           
                <col width="400">
                <col width="100">
                 <col width="150">
                 <col width="150">
               
               
              </colgroup>
              <thead>
               <tr>
			      <th>序号</th>
			      <th>公告标题</th>
			      <th>公告内容</th>
			      <th>编辑者</th>
			      <th>发布时间</th>
			     
			           <c:if test="${type==2}">
			       <th>操作</th>
			     </c:if>
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${list}" var="b" >
    <tr>
       <td>${b.id }</td>
      <td>${b.title }</td>
      <td>${b.content }</td>
        <td>${b.editor }</td>
     
        <td>${b.fbsj }</td>
    
     
       <c:if test="${type==2}">
       <td>
      
             <a href="BoardServlet?method=queryBoardAct&id=${b.id}" class="layui-btn layui-btn-sm layui-btn-normal"><i class="layui-icon">&#xe642;</i></a>
    		
    		 <a onclick="return del();" href="BoardServlet?method=deleteBoardAct&id=${b.id}" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">&#xe640;</i></a></td>
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