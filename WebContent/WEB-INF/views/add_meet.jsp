
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
  <legend>我要认领</legend>
</fieldset>  
 
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
       
          <div class="layui-card-body" pad15>
             <form action="MeetServlet?method=addMeetAct" method="post" >
         
            
           <input type="hidden" name="u_id" value="${id}" />
    
           <input type="hidden" name="type" value="${type}" />
         
              <div class="layui-form-item">
                <label class="layui-form-label">认领物品：</label>
                <div class="layui-input-inline">
                  <input type="text" name="wpname" readonly value="${wpname }"  lay-verify="required" lay-verType="tips" class="layui-input">
                </div>
              </div>
              
            
              <div class="layui-form-item">
                <label class="layui-form-label">认领人：</label>
                <div class="layui-input-inline">
                  <input type="text" name="name" id="name"  value="${user.realname} "  lay-verify="required" lay-verType="tips" class="layui-input">
                </div>
              </div>
              
               <div class="layui-form-item ">
            <label class="layui-form-label">备&nbsp;&emsp;注：</label>
            <div class="layui-input-inline">
              <textarea type="text" name="bz" id="bz" placeholder="请输入备注" class="layui-textarea"></textarea>
            </div>
          </div>        
              
           
             
              
               <div class="layui-form-item">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-inline">
                  <input type="text" name="phone" id="phone"  value=" "  lay-verify="required" lay-verType="tips" class="layui-input">
                </div>
              </div>
              <br>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="addMeet">确认认领</button>
                  <a href="ShiwuServlet?method=ShiwuListAct" class="layui-btn layui-btn-primary" >返回</a>
                </div>
              </div>
              </form>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
		
	


<script src="<%=path %>/layui/layui.js"></script>
<script>

layui.use([ 'form','jquery','layer' ], function() {
	var form = layui.form,
	 layer = layui.layer,
	 $= layui.jquery;
	 form.render();//这句一定要加，占坑
	  
	 $('#addMeet').click(function(){  
		  var $1 = $.trim($('#name').val());
		 
		  var $3 = $.trim($("#bz").val());
		  var $4 = $.trim($("#phone").val());
		     if($1 == ''){  
		         layer.msg('认领人不能为空',function() {time:2000}); 
		         return false;  
		     }
		    
		     if($3 == ''){  
		         layer.msg('备注不能为空',function() {time:2000}); 
		         return false;  
		     }
		   
		    
		     if($4== ''){  
		         layer.msg('联系人电话不能为空',function() {time:2000}); 
		         return false;  
		     }
		   
	})
	 
	 
   

});
</script>
</body>
</html>