package model;

public class SearchDTO {
	private String code;
	private String pname;
	private String cost;
	private String pnum;
	private String jnum;
	private String sale;
	private String gcode;
	
	// product의 컬럼 이외의 다른 결과값 저장을 위한 변수 (otherColId)
	private String gname;
	private String total;
	private String necessary;

	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	
	public String getJnum() {
		return jnum;
	}
	public void setJnum(String jnum) {
		this.jnum = jnum;
	}
	
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	
	public String getGcode() {
		return gcode;
	}
	public void setGcode(String gcode) {
		this.gcode = gcode;
	}
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getNecessary() {
		return necessary;
	}
	public void setNecessary(String necessary) {
		this.necessary = necessary;
	}
}
