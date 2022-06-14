package model;


import javax.servlet.http.HttpServletRequest;

public class InsertDAOImpl extends DAOBase {
	
	
	public int executeInsert(HttpServletRequest request) {
		String[] param = new String[colId.length];
		
		String sql = "INSERT INTO product VALUES (";
		
		for(int i = 0; i < colId.length; i++) {
			sql += "?";
			
			if(i != colId.length - 1) {
				sql += ", ";
			}
			
			param[i] = (String)request.getParameter(colId[i]);
		}
		
		
		sql += ")";
		
		return this.executeSQL(sql, param); 
	}
}
