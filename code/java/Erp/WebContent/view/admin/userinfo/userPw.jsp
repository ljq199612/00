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
		
		
        <script language="javascript">
            function check(){
            	 var userPwReal="${sessionScope.admin.password}";
               if(document.thisForm.userPw1.value ==""){
                  alert("新密码不能空");
                  return ;
               }else if(document.thisForm.userPw1.value != document.thisForm.userPw2.value){
                  alert("两次输入的密码不一致");
                  return ;
               }else{ 
            	    var userName = "${sessionScope.admin.userName}";
            	    var newPassword = $('#userPw1').val();
     			 	 $.ajax({ 
     			 		type: 'POST', //提交方法是
     			 		url: '<%=path%>/changePassword.html', //请求的路径 
     				 	data: {
     				 			"userName": userName,
     				 			"newPassword": newPassword 
     				 			}, 
     				 	timeout: 1000, //请求超时时间，毫秒 
     				 	error:function(){ 
     				 		//请求失败的回调方法 
     				 	}, 
     			 		success:function(result){ 
     			 			if(result == "success"){ 
								alert("密码修改成功");     			 				
     			 				var url = "<%=path%>/welcome.jsp";
     			 				window.location.href = url;
     			 			}else if(result == "failed"){ 
     			 				alert("修改失败");  
     			 			}
     			 		} 
     				 }); 
           		 }
            }  
        </script>
	</head>

	<body>
			<form method="post" action="" name="thisForm">
			<table id="mainTable" >	
				<tr bgcolor="#EEF4EA">
				    <td colspan="2" background="<%=path %>/images/wbg.gif" class='title'><h1>密码修改</h1></td>
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         登录名：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF">
				       &nbsp; ${sessionScope.admin.userName}
				    </td>
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         新密码：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF">
				        <input type="password" id="userPw1" name="userPw1" size="22"/>
				    </td>
				</tr>
				<tr bgcolor="#FFFFFF">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				        确认密码：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF">
				           <input type="password" name="userPw2" id="userPw2" size="22"/>
				    </td>
				</tr>
			</table>
			
			<table  width="60%" border="0" cellpadding="2" cellspacing="0" align="center" style="margin-top:8px">
					<tr align="center">
						<td colspan="2"> 
							<input type="button" value="修改" onclick="check();"/>&nbsp;&nbsp; 
							<input id="reset" type="reset" value="重置"/>
						</td>
					</tr>
				</table>
			</form>
   </body>
</html>
