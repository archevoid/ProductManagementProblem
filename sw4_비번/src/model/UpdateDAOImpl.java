package model;

import javax.servlet.http.HttpServletRequest;

public class UpdateDAOImpl extends DAOBase {
	public int executeUpdate(HttpServletRequest request) {
		String sql = "UPDATE product SET ";
		
		int count = 0;
		boolean[] existance = new boolean[colId.length - 1];
		
		for(int i = 1; i < colId.length; i++) {
			if(!request.getParameter(colId[i]).isBlank()) {
				existance[i - 1] = true;
				count++;
			}
		}
		
		String[] param = new String[count + 1];
		
		for(int i = 1, num = 0; i < colId.length; i++) {
			if(existance[i - 1]) {
				sql += col[i] + " = ? ";
				if(num != count - 1) {
					 sql += ", ";
				}
				param[num] = request.getParameter(colId[i]);
				num++;
			}
		}
		
		sql += " WHERE " + col[0] + "= ? ";
		
		param[count] = request.getParameter(colId[0]);
		
		return this.executeSQL(sql, param);
	}
}
