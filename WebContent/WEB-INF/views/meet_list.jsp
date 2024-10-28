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
           <form action="MeetServlet?method=findByRlwpAct" method="post">
           <input type="text" placeholder="根据认领物品" name="wpname" style="height:26px;" />
           <input type="submit" class="layui-btn layui-btn-primary layui-btn-sm" value="查询">
           
           </form>
           
          
            <table class="layui-table">
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
                <c:forEach items="${pageBean.meetData}" var="m" >
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
    		 <a href="MeetServlet?method=queryMeetAct&id=${m.id}" class="layui-btn layui-btn-xs layui-btn-normal">编辑</a>
    		
    		 <a onclick="return del();" href="MeetServlet?method=deleteMeetAct&id=${m.id}&type=${type}" class="layui-btn layui-btn-xs layui-btn-danger">删除</a></td>
    		</c:if>
    </tr>
   </c:forEach>
              </tbody>
            </table>
          
          </div>
        </div>
      </div>
      <div style="text-align:center;font-size:15px;">
      <c:choose>
          <c:when test="${pageBean.currentPage==pageBean.firstPage}">
              首页&nbsp;
              上一页
          </c:when>
           <c:otherwise>
               <a href="${pageContext.request.contextPath}/MeetServlet?method=meetListAct2&currentPage=${pageBean.firstPage}&pageSize=${pageBean.pageSize}">首页&nbsp;</a>
               <a href="${pageContext.request.contextPath}/MeetServlet?method=meetListAct2&currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}">上一页&nbsp;</a>
           </c:otherwise>
       </c:choose>
       <c:choose>
           <c:when test="${pageBean.currentPage==pageBean.totalPage}">
              下一页&nbsp;
               尾页
           </c:when>
           <c:otherwise>
               <a href="${pageContext.request.contextPath}/MeetServlet?method=meetListAct2&currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}">
                   下一页&nbsp;
               </a>
               <a href="${pageContext.request.contextPath}/MeetServlet?method=meetListAct2&currentPage=${pageBean.totalPage}&pageSize=${pageBean.pageSize}">               尾页&nbsp;</a>
           </c:otherwise>
       </c:choose>
            当前页为第${pageBean.currentPage}页/共${pageBean.totalPage}页
            总记录数是${pageBean.totalCount}条/
            <%--
            //todo 需求2
             用户输入每页显示数据的大小
            当输入完,焦点一释放就获取它的值使用js的onblur实现
            --%>
            每页显示<input type="text" id="pageSize" style="width: 25px" value="${pageBean.pageSize}" onblur="changePageSize()" >条
        <%--
         //todo 需求3:
          跳转到第几页
          分析:将它设计成一个下拉的列表供选择第几页
        --%>
        跳转到第<input type="text" id="currentPage" size="2"
                   value="${pageBean.currentPage}" onblur="changePage()">页
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
/* 改变每页显示的记录数*/
function changePageSize(){
    //1.得到用户输入的数据
    var pageSize = document.getElementById("pageSize").value;
    //2.处理用户输出的数据
    //判断规则:只能输入1-2位的数字
    var reg=/^[1-9][0-9]?$/; //?表示0-1个
    if (!reg.test(pageSize)){
       alert("只能输入1-2位的数字!");
       return;
    }
    //2.请求ListEmpServlet类,同时发送参数pageSize
    var url="${pageContext.request.contextPath}/MeetServlet?method=meetListAct2&pageSize="+pageSize;
    //请求一个地址
    window.location.href=url;
}

/* 跳转指定的页数的页面 */
function changePage() {
    /*获取用户输入的数据*/
    var currentPage = document.getElementById("currentPage").value;
    var reg=/^[1-9][0-9]?$/;
    if(!reg.test(currentPage)){
        alert("只能输入1-2位数字!")
        return;
    }
    /* 如果输入的数字大于了最大页数,那么提示*/
    var totalPage="${pageBean.totalPage}";
    if(currentPage>totalPage){
        alert("已经超过了总页数!");
        return;
    }
    var url="${pageContext.request.contextPath}/MeetServlet?method=meetListAct2&currentPage="+currentPage+"&pageSize=${pageBean.pageSize}";
    window.location.href=url;
}

</script>
</body>
</html>