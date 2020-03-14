<%@ page language="java" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<title>login.jsp</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css">
	<style type="text/css">
	    body 
	    {
		   margin-left: 0px;
		   background-color: #25676d;
	    }
	    .style2 {color: #990000}
	    .input2 
	    {
	 	   font-size: 12px;
		   border: 3px double #A8D0EE;
		   color: #344898;
	    }
	    .submit1 
	    {
		   border: 3px double #416C9C;
		   height: 22px;
		   width: 45px;
		   background-color: #F2F2F2;
		   font-size: 12px;
		   padding-top: 1px;
		   background-image: url(bt.gif);
		   cursor: hand;
	    }
	    .STYLE12 {font-family: Georgia, "Times New Roman", Times, serif}
	    .STYLE13 {color: #316BD6; }
	    .STYLE15 {color: #fdsere; font-size: 9pt; }
	 </style>

	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/public.js"></script>

	<script type="text/javascript">
		 function reg()
		 {
		         var url="<%=path%>/reg.jsp";
		         var n="";
		         var w="480px";
		         var h="500px";
		         var s="resizable:no;help:no;status:no;scroll:yes";
		         openWin(url,n,w,h,s);
		 }
	</script>
</head>
<body>
	<br>
	<br>
	<table id="userTable" style="margin-top:unset; background-color:#97c5ab">
		<tr>
			<td>
				<div align="center" style="FONT-WEIGHT: bold; FONT-SIZE: 40pt;">
					小区物业管理系统
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<form  id = "thisForm" name="thisForm" method="get" action="">
					<table  class="mainTable2" style="background-color:#a5ccb6">
						<tr><td id="msg"></td></tr>
						<tr>
							<td width="356" valign="bottom">
								<span class="STYLE15">用户名：</span>
								<input id="userName" name="userName" type="text" class="input2"
									onMouseOver="this.style.background='#F0DAF3';"
									onMouseOut="this.style.background='#FFFFFF'">
							</td>
						</tr>
						<tr>
							<td height="10" colspan="2" valign="bottom"></td>
						</tr>
						<tr>
							<td height="31" colspan="" valign="top" class="STYLE15">
								密&nbsp;&nbsp;&nbsp;&nbsp;码：
								<input id="password" name="password" type="password" size="" class="input2"
									align="bottom" onMouseOver="this.style.background='#F0DAF3';"
									onMouseOut="this.style.background='#FFFFFF'">
							</td>
						</tr>
						<tr>
							<td height="10" colspan="2" valign="bottom"></td>
						</tr>
						<tr style="display: block">
							<td height="23" colspan="2" valign="top" class="STYLE15">
								身&nbsp;&nbsp;&nbsp;&nbsp;份：
								<select class="INPUT_text" name="userType">
									<option value="-1" selected="selected">
										请选择登陆身份
									</option>
									<option value="0">
										管理员
									</option>
									<option value="1">
										业&nbsp;&nbsp;&nbsp;户
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
								&nbsp; &nbsp; &nbsp; &nbsp;
								<input id="loginBtn" name="button" type="button" class="submit1" value="登录"/>
								&nbsp;
								<input name="Submit2" type="reset" class="submit1" value="重置"/>
								&nbsp;
								<input name="button" type="button" class="submit1" value="注册"	onclick="reg()"/>
								&nbsp;
								<img id="indicator" src="<%=path%>/images/loading.gif" style="display:none" />
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>

<script type='text/javascript'>
		 $('#userTable').addClass("mainTable");
		 $("#loginBtn").click(function(){
			 //前台的非空验证 
			 var userName = $("#userName").val();
			 var password = $("#password").val();
			 
			 if(userName == ""){ 
				 alert("用户名不能为空");
			 }else if(	password == ""){
				 alert("密码不能为空");
			 }else{ 
			 	//如果账号和密码都不为空，就进行 ajax 异步提交 
			 	 $.ajax({ 
			 		type: 'POST', //提交方法是
			 		url: '<%=path%>/login.html', //请求的路径 
				 	data: {
				 			"userName": userName,
				 			"password": password 
				 			}, 
				 	timeout: 1000, //请求超时时间，毫秒 
				 	error:function(){ 
				 		//请求失败的回调方法 
				 		$("#msg").css("color","red"); 
				 		$("#msg").html("登录失败！请重试。"); 
				 	}, 
			 		success:function(result){ 
			 			//请求成功的回调方法 
			 			if(result == "success"){ 
			 				$("#msg").html(""); 
			 				var url = "<%=path%>/index.html";
			 				document.thisForm.action = url;
			 				document.thisForm.submit();
			 			}else if(result == "failed"){ 
			 				$("#msg").css("color","red"); 
			 				$("#msg").html("登录失败！请重试。"); 
			 			}else if(result == "pwderror"){ 
			 				$("#msg").css("color","red"); 
			 				$("#msg").html("帐号或密码错误！请重试。"); 
			 			}
			 		} 
				 }); 
			   } 
			 
			});
</script>

