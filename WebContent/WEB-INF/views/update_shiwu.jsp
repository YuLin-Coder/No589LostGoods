
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
<title>校园失物管理</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="<%=path %>/layui/css/login.css">  
</head>
<body>

 <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>编辑拾物信息</legend>
</fieldset>  
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
      
          <div class="layui-card-body" pad15>
             <form  action="ShiwuServlet?method=updateShiwuAct" method="post" >
         
            
           
           <input type="hidden" name="id" value="${list.id}"/>
           
          
              
          
              <div class="layui-form-item">
                <label class="layui-form-label">物品名称：</label>
                <div class="layui-input-inline">
                  <input type="text" name="wpname" id="wpname" value="${list.wpname}"  lay-verify="required" lay-verType="tips" class="layui-input">
                </div>
              </div>
              
              
             
              
              
               <div class="layui-form-item ">
            <label class="layui-form-label">物品详情：</label>
            <div class="layui-input-inline">
              <textarea type="text" name="detail" id="detail" placeholder="请输入物品详情" class="layui-textarea">${list.detail}</textarea>
            </div>
          </div>   
          
           <div class="layui-form-item ">
            <label class="layui-form-label">拾物地点：</label>
            <div class="layui-input-inline">
              <textarea type="text" name="place" id="place" placeholder="请输入拾物地点" class="layui-textarea">${list.place}</textarea>
            </div>
          </div>        
              
             
            
              
            <div class="layui-inline">
                  <label class="layui-form-label">拾物时间：</label>
                  <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="createTime" value="${list.createTime}" id="createTime" placeholder="yyyy-MM-dd HH:mm:ss">
                  </div>
              </div>
             
           
              
               <br>
            
           
          
              
               <div class="layui-form-item">
                <label class="layui-form-label">联系电话：</label>
                <div class="layui-input-inline">
                  <input type="text" name="phone" id="phone"  value=" ${list.phone}"  lay-verify="required" lay-verType="tips" class="layui-input">
                </div>
              </div>
              <br>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="updateShiwu">确认修改</button>
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

layui.use([ 'form','jquery','layer','laydate' ], function() {
	var form = layui.form,
	 layer = layui.layer,
	 laydate=layui.laydate,
	 $= layui.jquery;
	 form.render();//这句一定要加，占坑
	 
	  laydate.render({
	      elem: '#createTime'
	      ,type: 'datetime'
	    });
	  $("#imgUrl").change(function () {
		    //创建blob对象，浏览器将文件放入内存中，并生成标识
		    var img_src = URL.createObjectURL($(this)[0].files[0]);
		    //给img标检的src赋值
		    document.getElementById("preview_img").src=img_src;
		    //URL.revokeObjectURL(img_src);// 手动 回收，
		});
	 $('#updateShiwu').click(function(){  
		  var $1 = $.trim($('#sname').val());
		  var $2 = $.trim($("#wpname").val());
		
		  var $4 = $.trim($("#detail").val());
		  var $5 = $.trim($("#phone").val());
		  var $6 = $.trim($("#createTime").val());
		  var $7 = $.trim($("#place").val());
		   
		     if($2 == ''){  
		         layer.msg('物品名称不能为空',function() {time:2000}); 
		         return false;  
		     }
		    
		     if($4 == ''){  
		         layer.msg('物品详细信息不能为空',function() {time:2000}); 
		         return false;  
		     }
		     if($5 == ''){  
		         layer.msg('联系人不能为空',function() {time:2000}); 
		         return false;  
		     }
		    
		     if($6== ''){  
		         layer.msg('拾物日期不能为空',function() {time:2000}); 
		         return false;  
		     }
		     
		     if($7== ''){  
		         layer.msg('拾物地点不能为空',function() {time:2000}); 
		         return false;  
		     }
		   
	})
	 
	 
   

});
</script>
</body>
</html>