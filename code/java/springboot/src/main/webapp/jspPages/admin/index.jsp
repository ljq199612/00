<%@ include file="../_main.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<title>index</title>

<h1>Spring Boot 集成Mybatis  + Pagehelper 测试例子</h1>
当前页共 <b>${fn:length(pageInfo.list)}</b> 条记录，总共 <b>${pageInfo.total}</b> 条记录

<table id = "adminsTable">
    <tr>
        <th width="25%">姓名</th>
		<th width="25%">密码</th>
		<th width="25%">注册时间</th>
		<th width="25%">操作</th>
	</tr>
	<c:forEach items="${admin}" var="admin">
	    <tr>
	        <td width="25%"> ${admin.userName} </td>
		    <td width="25%"> ${admin.password} </td>
            <td width="25%"> ${admin.registDate} </td>
		    <td width="25%">
		       	<input type="button" name="update" value="修改"/>
		    	<input type="button" name="delete" value="删除"/>
		   	    <input type="hidden" value="${admin.id}"/>
		    </td>
		</tr>
	</c:forEach>
</table>

</body>

</html>



<script type="text/javascript">

$(document).ready(function(){
    $(document).ready(function(){
        $('#adminsTable input').hover(
	        function(){ $(this).css("color", "red");},
			function(){ $(this).css("color", ""); }
		);
		$('#adminsTable tr:even').css("backgroundColor","skyblue");
		$('#adminsTable tr:odd').css("backgroundColor","aliceblue");
		$('#adminsTable tr th').css("backgroundColor","lightgreen");
		$(':button').css("backgroundColor","gold");
		$(':submit').css("backgroundColor","gold");
	});
});

</script>
