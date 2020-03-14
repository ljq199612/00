<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"
					+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'message_include.jsp' starting page</title>
    

 
<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.0.js"></script> 
<script type="text/javascript">

</script>
</head>
<body>
	<jsp:include page="jsp/property/include_property.jsp"></jsp:include>
	
</body>
</html>