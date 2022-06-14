<%@page import="java.util.Enumeration"%>
<%@page import="model.GroupDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="style.css">
	<%
		@SuppressWarnings("unchecked")
		ArrayList<GroupDTO> group = (ArrayList<GroupDTO>)request.getAttribute("group");
		String[] colId = (String[])request.getAttribute("colId");
		String[] col = (String[])request.getAttribute("col");
		String[] groupColId = (String[])request.getAttribute("groupColId");
	%>
<title>생산관리 등록화면</title>
</head>
<body>
	<h1>생산관리 등록화면</h1>
	<div class="title">생산관리 등록화면</div>
	<div class="content">
		<form action='' method='POST' name='productForm'>
			<ul>
				<%
					for(int i = 0; i < col.length - 1; i++) {
						String type = "text";
						if(i >= 2) {
							type = "number";
						}
				%>
						<li><span class="input"><%= col[i] %></span>
						<input type='<%= type %>' name='<%= colId[i] %>'></li>
				<%
					}
				%>
				<li>
					<span class="input">그룹이름</span>
					<select name='<%= colId[6] %>'>
					<%
						for(int i = 0; i < group.size(); i++) {
							GroupDTO groupDto = group.get(i);
					%>
							<option value='<%= groupDto.getGcode() %>'><%= groupDto.getGname() %></option>
					<%
						}
					%>
					</select>
				</li>
			</ul>
		</form>
		<div class="buttonArea">
			<button class='btn' onclick="submitForm(document.productForm, 'insert.do')">등록</button>
			<button class='btn' onclick="location.href='index.html';">메인화면</button>
		</div>
	</div>
	
	<script type="text/javascript" src="function.js"></script>
</body>
</html>