<%@page import="java.util.Enumeration"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="model.SearchDTO"%>
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
	
		ArrayList<SearchDTO> result = null;
		
		Enumeration<String> attrNames = request.getAttributeNames();
		
		while(attrNames.hasMoreElements()) {
			String attrName = attrNames.nextElement();
			
			if(attrName.equals("list")) {
				@SuppressWarnings("unchecked")
				ArrayList<SearchDTO> temp = (ArrayList<SearchDTO>)request.getAttribute("list");
				result = temp;
			}
		}
		
	%>
	
	<%!
	 	<T> String getter(T t, String name) {
			String result = "";
		
			try {
				Class<?> cls = Class.forName(t.getClass().getName());
				
				Method m = cls.getDeclaredMethod("get" + name.replaceAll("^[a-z]", Character.toString((char)(name.charAt(0) - 32))));
				
				result = (String)m.invoke(t);
			} catch(Exception e) {
				System.out.println(e);
			}
			
			return result;
		}
	%>
<title>생산관리 조회 &amp; 수정 화면</title>
</head>
<body>
	<h1>생산관리 조회 &amp; 수정 화면</h1>
	<div class="title">생산관리 조회화면</div>
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
						<li>
							<span class="input"><%= col[i] %></span>
							<input type='<%= type %>' name='<%= colId[i] %>'<% if(request.getMethod().equals("POST") && result != null && result.size() != 0) { %><%= "value=" + getter(result.get(0), colId[i]) %><% } %>>
						</li>
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
							<option value='<%= groupDto.getGcode() %>'<% if(request.getMethod().equals("POST") && result != null && result.size() != 0 && result.get(0).getGcode().equals(groupDto.getGcode())) {%> selected <% } %>>
								<%= groupDto.getGname() %>
							</option>
					<%
						}
					%>
					</select>
				</li>
			</ul>
		</form>
		<div class="buttonArea">
			<button class='btn' onclick="searchForm(document.productForm, 'search.do')">조회</button>
			<button class='submitBtn' id='update' onclick="searchForm(document.productForm, 'update.do')">수정</button>
			<button class='submitBtn' id='delete' onclick="searchForm(document.productForm, 'delete.do')">삭제</button>
			<button class='btn' onclick="location.href='index.html';">메인화면</button>
		</div>
	</div>
	<%
		if(request.getMethod().equals("POST") && result != null && result.size() == 0) {
	%>
			<div class='notice' id='failed'>결과가 없습니다.</div>
	<%
		}
	%>

	
	<script type="text/javascript" src="function.js"></script>
	<script>
	window.onload = function() {
		<%
			/* if(request.getMethod().equals("GET")) {
		%>
				disabled();
		<%
			} else if(result != null && result.size() != 0) {
		%>
				activateBtn();
		<%
			}*/ if(result != null && result.size() == 0) {
		%>
				// disabled();
				notify(document.getElementById('failed'));
		<%
			}
		%>
	}
	</script>
	
</body>
</html>