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
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" type="text/css" href="layui/css/layui.css">
  <link rel="stylesheet" href="layui/css/admin.css" media="all"> 
  
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>系统简介</legend>
</fieldset>  
   
 
<div class="layui-carousel" id="test10">
  <div carousel-item="">
    <div><img src="img/1.jpg"></div>
    <div><img src="img/2.jpg"></div>
    <div><img src="img/3.jpg"></div>
    <div><img src="img/4.jpg"></div>
    
    
  </div>
</div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>关于我们</legend>
</fieldset>   
 
<div class="layui-bg-gray" style="padding: 10px;">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md6">
      <div class="layui-card">
        <div class="layui-card-header">系统介绍</div>
        <div class="layui-card-body">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了解决失物招领的问题，代替传统的失物招领处，开发一款基于PC端的校园失物招领系统，方便学生找回失物，避免过多的麻烦。双方及时和系统管理员联系，在线下或者线上沟通进行物品信息的确认，最后归还物品。<br>
        </div>
      </div>
    </div>
    <div class="layui-col-md6">
      <div class="layui-card">
        <div class="layui-card-header">联系方式</div>
        <div class="layui-card-body">
        联系地址：河南省商丘市梁园区睢阳大道中段236号商丘工学院<br>
         微信/电话：15090621305<br>QQ：2764362338
         
        </div>
      </div>
    </div>
  </div>
</div> 
 
          
<script src="layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['dropdown', 'util', 'layer'], function(){
  var element = layui.element;
  var layer = layui.layer;
  
  //监听折叠
  element.on('collapse(test)', function(data){
    layer.msg('展开状态：'+ data.show);
  });
});
</script>
<script>
layui.use(['carousel', 'form'], function(){
  var carousel = layui.carousel
  ,form = layui.form;
  
  //常规轮播
  
  carousel.render({
    elem: '#test10'
    ,width: '1486px'
    ,height: '450px'
    ,interval: 1500
  });
  
  //事件
  carousel.on('change(test4)', function(res){
    console.log(res)
  });
  
  var $ = layui.$, active = {
    set: function(othis){
      var THIS = 'layui-bg-normal'
      ,key = othis.data('key')
      ,options = {};
      
      othis.css('background-color', '#5FB878').siblings().removeAttr('style'); 
      options[key] = othis.data('value');
      ins3.reload(options);
    }
  };
  
  //监听开关
  form.on('switch(autoplay)', function(){
    ins3.reload({
      autoplay: this.checked
    });
  });
  
  $('.demoSet').on('keyup', function(){
    var value = this.value
    ,options = {};
    if(!/^\d+$/.test(value)) return;
    
    options[this.name] = value;
    ins3.reload(options);
  });
  
  //其它示例
  $('.demoTest .layui-btn').on('click', function(){
    var othis = $(this), type = othis.data('type');
    active[type] ? active[type].call(this, othis) : '';
  });
});
</script>

</body>
</html>