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
		String[] otherCol = (String[])request.getAttribute("otherCol");
		String[] otherColId = (String[])request.getAttribute("otherColId");
	
		Enumeration<String> attrNames = request.getAttributeNames();
		
		ArrayList<SearchDTO> result = null;
		
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
<title>이익 순위 제품 화면</title>
</head>
<body>
	<h1>이익 순위 제품 화면</h1>
	<div class="title">이익 순위 제품 화면</div>
	<div class="content">
		<table class="result">
			<tr>
				<th><%= col[1] %></th>
				<th><%= otherCol[1] %></th>
			</tr>
			<%
				for(int i = 0; i < result.size(); i++) {
			%>
					<tr>
						<td><%= getter(result.get(i), colId[1]) %></td>
						<td><%= getter(result.get(i), otherColId[1]) %></td>
					</tr>
			<%
				}
			%>
		</table>
		<div class="buttonArea" id="btnArea">
			<button class='btn' onclick="location.href='index.html';">메인화면</button>
		</div>	
	</div>

	<script type="text/javascript" src="function.js"></script>
</body>
</html>