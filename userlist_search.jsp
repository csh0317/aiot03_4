<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>My JSP 'userlist.jsp' starting page</title>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0"> 
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <style type="text/css">
	th,td{width: 150px;border: 2px solid gray;text-align: center;}
	body{text-align: center;}
	a{text-decoration: none;}
	table {border-collapse: collapse;}
</style> -->
<!-- Font Awesome -->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <!-- Bootstrap core CSS -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="css/mdb.min.css" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="css/style.css" rel="stylesheet">
</head>
<body>
	<a href='index.jsp'><h2 align="center" class='title'>機房監控系統</h2></a>
		
<div class="card card-cascade narrower" align="center">
	<div
	    class="view view-cascade gradient-card-header blue-gradient narrower py-2 mx-4 mb-3 d-flex justify-content-between align-items-center">
		
		 <!--<div>
	      <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
	        <i class="fas fa-th-large mt-0"></i>
	      </button>
	      <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
	        <i class="fas fa-columns mt-0"></i>
	      </button>
	    </div> -->

        <div>
			<form action="ListUser_search">
				<i class="fa fa-search"></i>
				<input type="text" name="dd" placeholder='請輸入日期..'>
				<input type="submit" value="搜尋"></input>
			</form>
		</div>
	    <b style="color:#fff;padding-right:80px">資料列表</b>
	
<c:if test= "${account == 'admin' }">
	    <div>
	      <!-- <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
	        <i class="fas fa-pencil-alt mt-0"></i>
	      </button>
	      <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
	        <i class="far fa-trash-alt mt-0"></i>
	      </button>
	      <button type="button" class="btn btn-outline-white btn-rounded btn-sm px-2">
	        <i class="fas fa-info-circle mt-0"></i>
	      </button> -->
	      <a href='index.jsp'><b style="color:#fff">回首頁｜</b></a>
	      <a href="regist.jsp"><b style="color:#fff">新增使用者｜</b></a>
	      <a href="logoutServlet"><b style="color:#fff">登出｜</b></a>
	      <a href="ListUser"><b style="color:#fff">回清單</b></a>
	    </div>
</c:if>
<c:if test= "${account != 'admin' }">
	    <div>
	    	<a href="index.jsp"><b style="color:#fff">回首頁｜</b></a>
	    	<a href="logoutServlet"><b style="color:#fff">登出｜</b></a>
	    	<a href="ListUser"><b style="color:#fff">回清單</b></a>
	    </div>
</c:if>	    
	  </div>
 <div >
	
	    <div class="table-wrapper">
	    
	<table class="table table-striped table-hover mb-0" style='text-align:center'>
		<tr>
			<td class='ttd'>ID</td>
			<td class='ttd'>機房</td>
			<td class='ttd'>日期</td>
			<td class='ttd'>圖片</td>
		</tr>
		<c:forEach items="${listss}" var="person">
			<tr>
				<td >${person.id}</td>
				<td >${person.name}</td>
				<td >${person.number}</td>
				<td>
	                <a class='showimage' href="showServlet?id=<c:out value='${person.id}'/>">ShowImage</a>                  	
	            </td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<c:if test="${pageNos>1}">
		<a class='page' href="ListUser?pageNos=1" ><b>第一頁</b></a>&nbsp &nbsp
		<a class='page' href="ListUser?pageNos=${pageNos-1}"><b>上一頁</b></a>&nbsp &nbsp
	</c:if>
	<c:if test="${pageNos <recordCount}">
		<a class='page' href="ListUser?pageNos=${pageNos+1}"><b>下一頁</b></a>&nbsp &nbsp
		<a class='page' href="ListUser?pageNos=${recordCount}"><b>最後頁</b></a>&nbsp &nbsp
	</c:if>
	<form action="ListUser">
		<h5 align="center">共${recordCount}頁 &nbsp;
			<input type="text" value="${pageNos}" name="pageNos" size="1">頁
			<input type="submit" value="搜尋頁">
		</h5>
	</form>
</div>	
</div>
</div>	
<!-- JQuery -->
  <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="js/popper.min.js"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="js/mdb.min.js"></script>
</body>
</html>