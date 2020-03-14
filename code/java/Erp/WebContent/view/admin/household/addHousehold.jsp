<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<script language="javascript">
			$(document).ready(function(){
				$('#mainTable').css(
						{	"margin": "auto",
							"margin-top": "30px",
							"background-color": "#CBD8AC",
							"width": "60%",
							"border": "0"
						});
				
				$('body').css( {"background-color": "#FFFFFF" });
				$('#mainTable tr').css( {"align": "center", "background-color": "#FFFFFF" ,"height": "40px"});
				$('#mainTable tr td:odd').css({	"text-align": "left", "width": "15%" });
				$('#mainTable tr td:even').css(	{"text-align": "left" });
				$('#mainTable tr td:even input').css( {"border": "0" , "size": "30%"});
				
				$(':submit').css("backgroundColor","gold");
				$(':reset').css("backgroundColor","gold");
				
				$("#submit").click(function(){
					var url = "<%=path%>/household/insert.html";
	 				document.thisForm.action = url;
				});
				
			});
		</script>
	</head>

	<body>
			<form  id="thisForm" name="thisForm" method="post">
				<table id="mainTable" >
					<tr bgcolor="#EEF4EA">
						<td colspan="2" background="<%=path%>/images/wbg.gif" class='title'><h1>&nbsp;住户添加</h1></td>
					</tr>
					<tr>
						<td>&nbsp; 姓名：</td>
						<td>&nbsp;&nbsp; <input type="text" name="name"/> </td>
					</tr>
					<tr>
						<td>&nbsp; 性别：</td>
						<td> &nbsp;&nbsp;
							<label>男</label><input type="radio" name="sex" value="男" checked="checked" /> 
							<label>女</label><input type="radio" name="sex" value="女"/>
						</td>
					</tr>
					<tr>
						<td>&nbsp; 年龄：</td>
						<td>&nbsp;&nbsp; <input type="text" name="age"/> </td>
					</tr>
					<tr>
						<td>&nbsp; 联系方式：</td>
						<td>&nbsp;&nbsp; <input type="text" name="telephone"/> </td>
					</tr>
					<tr>
						<td>&nbsp; 住房信息：</td>
						<td>&nbsp;&nbsp; <input type="text" name="address"/> </td>
					</tr>
					<tr>
						<td>&nbsp; 注册日期：</td>
						<td> 
							&nbsp;&nbsp;<input type="text" name="regdate" onfocus="WdatePicker({isShowWeek:true})"/> 
						</td>
					</tr>
					<tr>
						<td>&nbsp; 密码：</td>
						<td>&nbsp;&nbsp; <input type="text" name="password"/> </td>
					</tr>
					<tr>
						<td>&nbsp; 备注：</td>
						<td>&nbsp;&nbsp; <input type="text" name="remarks"/> </td>
					</tr>
				</table>
				<table  width="60%" border="0" cellpadding="2" cellspacing="0" align="center" style="margin-top:8px">
					<tr align="center">
						<td colspan="2"> 
							<input id="submit" type="submit" value="提交"/>&nbsp;&nbsp; 
							<input id="reset" type="reset" value="重置"/>
						</td>
					</tr>
				</table>
				 
			</form>
   </body>
</html>
