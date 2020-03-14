<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$("input[name='delete']").hover(
					function(){ $(this).css("color", "red");},
					function(){ $(this).css("color", ""); }
				);
				
				$('#environmentTable tr:even').css("backgroundColor","skyblue");
				$('#environmentTable tr:odd').css("backgroundColor","aliceblue");
				$('#environmentTable tr th').css("backgroundColor","lightgreen");
				$(':button').css("backgroundColor","gold");
				$(':submit').css("backgroundColor","gold");
					
				$("input[name $= '_update']")
					.css( {"border": "0" , "text-align": "center"} )
					.change(function(){ $(this).css("color", "red"); }); 
				
				$("select").each(function(){
    				var cause = $(this).siblings().last().val(); 
					$(this).children().each(function(){
						if( $(this).val() == cause){ $(this).attr("selected", "selected"); }
					});
					
					$(this).change(function(){
						if( $(this).children('option:selected').val() != cause ){
							$(this).css("color", "red");
						}else{
							$(this).css("color", "");
						}
					});
					
  				});
				
				$(':button').click(
					function(){ 
						var id  =  $(this).siblings().last().val();
						var cost =  $(this).parent().prev().children().val();
						var cause =  $(this).parent().prev().prev().children().val();
						var date =  $(this).parent().prev().prev().prev().children().val();
						if($(this).attr("name")=="delete"){
							if(window.confirm("确定删除？")){
								$.ajax({ 
									type: 'POST', //提交方法是
									url: '<%=path%>/environmentTable/delete.html', //请求的路径 
									data: { "checkedId": id }, 
									timeout: 1000, //请求超时时间，毫秒 
									error:function(){ 
										//请求失败的回调方法
										alert("删除失败!");
									}, 
									success:function(result){
										//请求成功的回调方法 
										if(result == "success"){ 
											var url = "<%=path%>/environmentTable/manage.html";
											window.location.href = url;
										}
									}
								});
							}
						}
						
						if($(this).attr("name")=="update"){
							if(window.confirm("确定修改？")){
								$.ajax({ 
									type: 'POST', //提交方法是
									url: '<%=path%>/environmentTable/update.html', //请求的路径 
									data: { 
											"checkedId" : id,
											"cost"      : cost,
											"cause"     : cause,
											"date"      : date
											}, 
									timeout: 1000, //请求超时时间，毫秒 
									error:function(){ 
										//请求失败的回调方法
										alert("修改失败!");
									}, 
									success:function(result){
										//请求成功的回调方法 
										if(result == "success"){ 
											var url = "<%=path%>/environmentTable/manage.html";
											window.location.href = url;
										}
									}
								});
							}
						}
					}
				);
			});
			
			function addEnvironment(){
				var url = "<%=path%>/view/admin/environment/addEnvironment.jsp";
				window.location.href = url;
			}
			
			</script>
	</head>

	<body>
		<table  width="98%" border="0" cellpadding="2" cellspacing="0" align="center" style="margin-top:8px">
				<tr>
					<td align="left" width="80%"><h1>小区环境信息</h1></td>
					<td align="center" width="20%">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="添加"  style="width:80px" onclick="addEnvironment()" />
					</td>
				</tr>
		</table>
		
		<table id = "environmentTable" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
			<tr align="center" bgcolor="#FAFAF1" height="22">
				<th width="10%">编号</th>
				<th width="15%">名称</th>
				<th width="15%">位置</th>
				<th width="10%">占地面积</th>
				<th width="10%">修建时间</th>
				<th width="20%">纪念意义</th>
				<th width="20%">操作</th>
			</tr>	
			
			
			<c:forEach items="${environmentList}" var="environment">
				<tr align="center" bgcolor="#FAFAF1" height="30">
					<td width="10%">	${environment.number} </td>
					<td width="15%">	<input type="text" size="15%" name="name_update" value="${environment.name}"/> </td>
					<td width="15%">	<input type="text" size="15%" name="location_update" value="${environment.location}"/> </td>
					<td width="10%">	<input type="text" size="6%" name="area_update" value="${environment.area}"/> </td>
					<td width="10%">
						<input type="text" name="date_update" size="10%" readonly="readonly" value="${environment.date}" onfocus="WdatePicker({isShowWeek:true})"/>
					</td>
					<td width="20%"><input type="text" size="10%" name="memorable_update" value="${environment.memorable}"/></td>
					<td width="20%"> 
						<input type="button" name="update" value="更新"/>
						<input type="button" name="delete" value="删除"/>	
						<input type="hidden" value="${environment.id}"/>
					</td>
				</tr>
			</c:forEach>
			</table>
	</body>
</html>
