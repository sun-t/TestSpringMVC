<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/contacts.js"></script>
	<script type="text/javascript">
		function log(){
			if(document.form.name.value==""){
				window.alert("please input name!");
		     	document.form.name.focus();
		     	return false;
			}
			if(document.form.password.value==""){
				window.alert("please input password!");
		     	document.form.password.focus();
		     	return false;
			}
			return true;
		}
	</script>
  </head>
  <body style="font-family: Arial; font-size:smaller;" bgcolor="black">
  <form action="loginContacts.do" method="post" name="form">
    <table bgcolor="lightblue" align="center" style="border-collapse: collapse;" border="0" bordercolor="#006699">
    <tr><td colspan="2" align="center">定时测试任务发起页</td></tr>
    <tr>
    <td align="center">测试发起人：</td><td ><input type="text" name="name" style="width:150; height:20"></td>
    </tr>
    <tr>
    <td align="center">测试用例列表：</td><td><input type="address" name="address" style="width:150; height:20"></td>
    </tr>
    <tr>
    <td colspan="2" align="center"><input type="submit" value="Login" > 
     <input type="button" value="New" onclick="javascript:go('saveContact.do');"/>
    </tr>
    </table>
    </form>
  </body>
</html>
