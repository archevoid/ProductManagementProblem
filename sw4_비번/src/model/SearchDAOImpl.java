package model;

import java.util.ArrayList;

public class SearchDAOImpl extends DAOBase {
	public ArrayList<SearchDTO> getByCode(String code) {
		return this.getSearch("*", col[0] + " = ?", code);
	}
	
	public ArrayList<SearchDTO> getPriority() {
		String select = col[1] + ", (" + col[3] + " - " + col[4] + ") AS \"" + otherCol[2] + "\"";
		
		String condition = col[4] + " / " + col[3] +" < 0.2";
		
		return this.getSearch(select, condition);
	}
	
	public ArrayList<SearchDTO> getRank() {
		String select = col[1] + ", " + col[4] + " * (" + col[5] + " - " + col[2] + ") AS " + otherCol[1];

		String condition = "1 = 1 ORDER BY " + otherCol[1] + " DESC ";
		
		return this.getSearch(select, condition);
	}
	
	public ArrayList<SearchDTO> getStock() {
		String select = "(SELECT " + groupCol[1] + " FROM groupcode g WHERE p." + col[6] + " = g." + groupCol[0] + ") AS " + otherCol[0] + ", sum(" + col[4] + ") AS " + col[4];
		String condition = "1=1 GROUP BY " + col[6];
		
		return this.getSearch(select, condition);
	}
}
