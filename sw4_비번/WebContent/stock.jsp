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
		String[] groupCol = (String[])request.getAttribute("groupCol"); 
		String[] groupColId = (String[])request.getAttribute("groupColId");
	
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
<title>그룹별 재고 수량 화면</title>
</head>
<body>
	<h1>그룹별 재고 수량 화면</h1>
	<div class="title">그룹별 재고 수량 화면</div>
	<div class="content">
		<table class="result">
			<tr>
				<th><%= groupCol[1] %></th>
				<th><%= col[4] %></th>
			</tr>
			<%
				for(int i = 0; i < result.size(); i++) {
			%>
					<tr>
						<td><%= getter(result.get(i), groupColId[1]) %></td>
						<td><%= getter(result.get(i), colId[4]) %></td>
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