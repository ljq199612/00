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
		<script type="text/javascript">
			$(document).ready(function(){
				$("input[name='delete']").hover(
					function(){ $(this).css("color", "red");},
					function(){ $(this).css("color", ""); }
				);
				
				$('#repairTable tr:even').css("backgroundColor","skyblue");
				$('#repairTable tr:odd').css("backgroundColor","aliceblue");
				$('#repairTable tr th').css("backgroundColor","lightgreen");
				$(':button').css("backgroundColor","gold");
				$(':submit').css("backgroundColor","gold");
				
				$("input[name='update']").each(function(i){
					if($(this).val() == "未受理"){
						$(this).css("color", "green");
					}else{
						$(this).parent().html($(this).val());
					}
				});

				$('#repairTable input').click(
					function(){ 
						var id = $(this).siblings().last().val();
						if($(this).attr("name")=="delete"){
							if(window.confirm("确定删除？")){
								$.ajax({ 
										type: 'POST', //提交方法是
										url: '<%=path%>/repair/delete.html', //请求的路径 
										data: { "checkedId": id }, 
										timeout: 1000, //请求超时时间，毫秒 
										error:function(){ 
											//请求失败的回调方法
											alert("删除失败!");
										}, 
										success:function(result){
											//请求成功的回调方法 
											if(result == "success"){ 
												var url = "<%=path%>/repair/manage.html";
												window.location.href = url;
											}
										}
								});
							}
						}
						
						if($(this).attr("name")=="update"){
							if(window.confirm("确定受理？")){
								$.ajax({ 
										type: 'POST', //提交方法是
										url: '<%=path%>/repair/toUpdate.html', //请求的路径 
										data: { "checkedId": id }, 
										timeout: 1000, //请求超时时间，毫秒 
										error:function(){}, 
										success:function(result){
											if(result == "success"){ 
												var url = "<%=path%>/repair/manage.html";
												window.location.href = url;
											}
										}
								});
							}
						}
					
					}
				);
				
				
				
			});
			
		</script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
		<h1> 
		&nbsp;&nbsp;&nbsp;报修管理
		</h1>
			<table id="repairTable" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<th width="20%">住户姓名</th>
					<th width="20%">报修时间</th>
					<th width="20%">事由</th>
					<th width="20%">状态</th>
					<th width="20%">操作</th>
				</tr>	
				<c:forEach items="${repairList}" var="repair">
				<tr align="center" bgcolor="#FAFAF1" height="30">
				    <td width="20%"> ${repair.household_name} </td>
				    <td width="20%"> ${repair.date} </td>
				    <td width="20%"> ${repair.cause} </td>
				    <td width="20%"> 
					    <input type="button" name="update" value="${repair.state}"/>	
				    	<input type="hidden" value="${repair.id}"/>
				    </td>
				    <td width="20%">
				    	<input type="button" name="delete" value="删除"/>	
				    	<input type="hidden" value="${repair.id}"/>
				    </td>
				</tr>
			</c:forEach>

			</table>
	</body>
</html>
