package model;

public class DeleteDAOImpl extends DAOBase {
	public int deleteByCode(String code) {
		String sql = "DELETE FROM product WHERE " + col[0] + " = ?";
		
		return this.executeSQL(sql, code);
	}
}
