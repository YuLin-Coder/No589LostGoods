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
<script src="layui/layui.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>修改感谢信内容</legend>
</fieldset>  
 <div class="layui-fluid larry-wrapper">

    <section class="panel panel-padding">
		
        <form class="layui-form layui-form-pane form-conmon" action="LetterServlet?method=updateLetter" method="post">
        <input type="hidden" name="id" value="${thLetter.id}"/>      
 			<div class="layui-form-item">
 			
                
              </div>
               <label class="layui-form-label">标题</label>
                <div class="layui-input-inline" style="width:20%">
                  <input type="text" name="title" id="title" value="${thLetter.title}"  lay-verify="required"  class="layui-input">
                </div>
            <div class="layui-form-item layui-form-text layui-form-item-sm" style="margin: 10px;">

               

                           <!-- 跟那些过往的编辑器一样，你需要放置一个标签（一般为textarea文本域）作为编辑器的目标元素，然后调用 layedit.build('id') 即可，如下所示：-->

                <div >

                    <textarea id="text" name=text  value="">${thLetter.text }</textarea>

                </div>

            </div>
				<div class="layui-input-block" style="float:right">
                  <button class="layui-btn" type="submit" id="updateLetter"  >确认修改</button>
                </div>
        </form>

    </section>

</div>

<script>
//Demo

</script>
<script>
layui.use('layedit', function(){
	  var layedit = layui.layedit;
	  layedit.build('text', {
		  tool: [
			  'strong' //加粗
			  ,'italic' //斜体
			  ,'underline' //下划线
			  ,'del' //删除线
			  
			  ,'|' //分割线
			  
			  ,'left' //左对齐
			  ,'center' //居中对齐
			  ,'right' //右对齐
			
			]
		});  
	});
</script>
  </body>
</html>