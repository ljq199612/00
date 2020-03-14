<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				$('#householdTable input').hover(
					function(){ $(this).css("color", "red");},
					function(){ $(this).css("color", ""); }
				);
				
				$('#householdTable tr:even').css("backgroundColor","skyblue");
				$('#householdTable tr:odd').css("backgroundColor","aliceblue");
				$('#householdTable tr th').css("backgroundColor","lightgreen");
				$(':button').css("backgroundColor","gold");
				$(':submit').css("backgroundColor","gold");
				
				$('#householdTable input').click(
					function(){ 
						var id = $(this).siblings().last().val();
						if($(this).attr("name")=="delete"){
							if(window.confirm("确定删除？")){
								$.ajax({ 
										type: 'POST', //提交方法是
										url: '<%=path%>/household/delete.html', //请求的路径/
										data: { "checkedId": id }, 
										timeout: 1000, //请求超时时间，毫秒 
										error:function(){ 
											//请求失败的回调方法
											alert("删除失败!");
										}, 
										success:function(result){
											//请求成功的回调方法 
											if(result == "success"){ 
												var url = "<%=path%>/household/manage.html";
												window.location.href = url;
											}
										}
								});
							}
						}
						
						if($(this).attr("name")=="update"){
							$.ajax({ 
									type: 'POST', //提交方法是
									url: '<%=path%>/household/toUpdate.html', //请求的路径 
									data: { "checkedId": id }, 
									timeout: 1000, //请求超时时间，毫秒 
									error:function(){}, 
									success:function(result){
										if(result == "success"){ 
											var url = "<%=path%>/view/admin/household/updateHousehold.jsp";
											window.location.href = url;
										}
									}
							});
						}
					
					}
				);
			});

			function addHousehold(){
				var url = "<%=path%>/view/admin/household/addHousehold.jsp";
				window.location.href = url;
			}
			
			
		</script>

	</head>

	<body>
		<form action="<%=path%>/household/select.html"  method="post">
			<table  width="98%" border="0" cellpadding="2" cellspacing="0" align="center" style="margin-top:8px">
				<tr>
					<td align="left" width="18%"><h1>业主信息</h1></td>
					<td align="center" width="64%">
						姓名：<input type="text" name="name" size="10" value="${household.name}"/>
						<input type="submit"  value="查询"/>
					</td>
					<td align="center" width="18%">
						<input type="button" value="添加"  style="width:80px" onclick="addHousehold()" />
					</td>
				</tr>
			</table>
		</form>
			
			<table id = "householdTable" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr align="center">
					<th width="10%">姓名</th>
					<th width="7%">性别</th>
					<th width="8%">年龄</th>
					<th width="17%">联系方式</th>
					<th width="15%">住房信息</th>
					<th width="10%">注册时间</th>
					<th width="15%">备注</th>
					<th width="18%">操作</th>
		       </tr>	
				<c:forEach items="${householdList}" var="household">
				<tr align="center" bgcolor="#FAFAF1" height="30">
				    <td width="10%">	${household.name} </td>
				    <td width="7%"> ${household.sex} </td>
				    <td width="8%"> ${household.age} </td>
				    <td width="17%"> ${household.telephone} </td>
				    <td width="15%"> ${household.address} </td>
				    <td width="10%"> ${household.regdate} </td>
				    <td width="15%"> ${household.remarks} </td>
				    <td width="18%"> 
				    	<input type="button" name="update" value="修改"/>
				    	<input type="button" name="delete" value="删除"/>	
				    	<input type="hidden" value="${household.id}"/>
				    </td>
				</tr>
			   </c:forEach>
			</table>
	</body>
</html>
