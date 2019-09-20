<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<link rel="stylesheet" href="css/style.css">
<style>
	body {
        
        width: 100%;
	    height: 100%;
	    padding: 0;
        background:url(111.jpg);
        background-size:cover;
        font-family: DFKai-sb;
        font-size: 30px;
    }

</style>
</head>
<body>

<form action = "Servlet" method = "get" class="login">      
    <font style="color:#fff" size="30" face="DFKai-sb"><b>智能機房辨識系統</b></font>
    <br>
        	<i class="fa fa-user-circle-o"></i>
        	<br>
        	<div>
        	<% if (request.getAttribute("ff")!=null){
        		out.print(request.getAttribute("ff"));
        		} %>
        	</div>
        	<div class = "st1"><b>帳戶  : </b>
        	<input type = "text" name = "name" placeholder = "" required autocomplete = "off">
        	<br>
            </div>
            <div class = "st1"><b>密碼 : </b>
            <input type = "password" name = "pwd" required placeholder = "">
            <br>
            </div>
            <div class = "st1"><b>驗證碼 :</b>
            <img   src="provecode" style="border-width:0px;" />
            <input type="text" name="securityCode" maxlength="5"  style="color:Blue;background-color:#E2F3FF;font-family:Arial;font-size:x-large;height:28px;width:79px;" />

			
            <br />
            <br />
            </div>
            <div class="st2">
            <button  style="font-size:30px" type="submit" value="login"><b>登入</b></button>         
            </div>
  

</form>
</body>
</html>