package model;

import java.sql.Connection;

public interface DAO {
	String[] colId = {
			"code", "pname", "cost", "pnum", "jnum", "sale", "gcode"
	};
	String[] col = {
				"제품코드", "제품이름", "제품원가", "목표수량", "재고수량", "출고가", "그룹코드"
	};
	
	String[] groupColId = {
			"gcode", "gname"
	};
	String[] groupCol = {
			"그룹코드", "그룹이름"
	};
	
	String[] otherColId = {
			"gname", "total", "necessary"
	};
	String[] otherCol = {
			"그룹이름", "총이익금액", "생산해야할 수량"
	};
	
	
	Connection getConnnection() throws Exception;
	void close();
}
