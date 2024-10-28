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
  <legend>失物信息</legend>
</fieldset>  
 <div class="layui-col-md12">
        <div class="layui-card">
        
          <div class="layui-card-body">
        
           
          
            <table class="layui-table">
              <colgroup>
                <col width="150">
                <col width="200">
                  <col width="100">
                <col width="200">
                <col width="400">
                <col width="150">
                 <col width="150">
                 <col width="200">
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
			       
			      
			     
    			</tr> 
              </thead>
              <tbody>
                <c:forEach items="${pageBean.data}" var="g" >
    <tr>
       <td>${g.id }</td>
      <td>${g.gname }</td>
      	<td style="width:80px;height:80px;"><a href="upload/${g.imgUrl }" target="_blank"><img src="upload/${g.imgUrl }" alt=""  border="0"/></a></td>
      <td>${g.lostPlace }</td>
        <td>${g.info }</td>
      <td>
     <c:choose>
                   <c:when test="${g.status==1}">
                 	已经丢失
                   </c:when>
                   <c:otherwise>
                	已经找到
                   </c:otherwise></c:choose>
 
      </td>
        <td>${g.user.realname }</td>
    <td>${g.createTime }</td>
     <td>${g.phone }</td>
     
       
<%--        <td><a onclick="return del();" href="GoodsServlet?method=deleteGoodsAct&id=${g.id}&type=${type}" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">取消挂失</i></a></td>
 --%>    		
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
               <a href="${pageContext.request.contextPath}/GoodsServlet?method=goodsListAct&currentPage=${pageBean.firstPage}&pageSize=${pageBean.pageSize}">首页&nbsp;</a>
               <a href="${pageContext.request.contextPath}/GoodsServlet?method=goodsListAct&currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}">上一页&nbsp;</a>
           </c:otherwise>
       </c:choose>
       <c:choose>
           <c:when test="${pageBean.currentPage==pageBean.totalPage}">
              下一页&nbsp;
               尾页
           </c:when>
           <c:otherwise>
               <a href="${pageContext.request.contextPath}/GoodsServlet?method=goodsListAct&currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}">
                   下一页&nbsp;
               </a>
               <a href="${pageContext.request.contextPath}/GoodsServlet?method=goodsListAct&currentPage=${pageBean.totalPage}&pageSize=${pageBean.pageSize}">               尾页&nbsp;</a>
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
    var url="${pageContext.request.contextPath}/GoodsServlet?method=goodsListAct&pageSize="+pageSize;
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
    var url="${pageContext.request.contextPath}/GoodsServlet?method=goodsListAct&currentPage="+currentPage+"&pageSize=${pageBean.pageSize}";
    window.location.href=url;
}

</script>
</body>
</html>