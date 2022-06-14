package model;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class DAOBase implements DAO {
	private static String dbDriver = "oracle.jdbc.driver.OracleDriver";
	private static String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static String dbUser = "system";
	private static String dbPassword = "hrdkorea";
	
	private static Connection conn = null;
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	
	private static final String table1 = "product";
	private static final String table2 = "groupcode";
	
	@Override
	public Connection getConnnection() throws Exception {
		Class.forName(dbDriver);
		
		conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		
		return conn;
	};
	
	@Override
	public void close() {
		if(rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace();} }
		if(stmt != null) { try { stmt.close(); }  catch (Exception e) { e.printStackTrace(); } }
		if(conn != null) { try { conn.close(); }  catch (Exception e) { e.printStackTrace(); } }
	}
	
	private <T> ArrayList<T> searchTable(T t, String table, String select, String condition, String...param) {
		ArrayList<T> list = new ArrayList<T>();
		
		try {
			int count = countQM(condition);
			
			conn = this.getConnnection();
			
			if(count != param.length) {
				condition = "1=1";
			}
			
			String sql = "SELECT " + select + " FROM " + table + " WHERE " + condition;
			
			stmt = conn.prepareStatement(sql);
			
			for(int i = 0 ; i < count; i++) {
				stmt.setString(i + 1, param[i]);
			}
			
			rs = stmt.executeQuery();
			
			ResultSetMetaData md = rs.getMetaData();
			
			while(rs.next()) {
				@SuppressWarnings({ "unchecked", "deprecation" })
				T dto = (T) t.getClass().newInstance();
				
				for(int i = 0; i < md.getColumnCount(); i++) {
					String colName = md.getColumnName(i + 1);
					
					String colEng = null;
					
					if(t instanceof SearchDTO) {
						for(int j = 0; j < colId.length; j++) {
							if(colName.equals(col[j])) {
								colEng = colId[j];
								break;
							}
						}
						for(int j = 0; j < otherCol.length; j++) {
							if(colName.equals(otherCol[j])) {
								colEng = otherColId[j];
								break;
							}
						}
					} else if(t instanceof GroupDTO) {
						for(int j = 0; j < groupColId.length; j++) {
							if(colName.equals(groupCol[j])) {
								colEng = groupColId[j];
								break;
							}
						}
					}
					
					if(colEng != null) {
						Class<?> cls = Class.forName(dto.getClass().getName());
						
						Method m = cls.getDeclaredMethod("set" + colEng.replaceAll("^[a-z]", Character.toString((char)(colEng.charAt(0) - 32))), String.class);
						
						m.invoke(dto, rs.getString(colName));
					}
				}
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return list;
	}
	
	public ArrayList<SearchDTO> getSearch(String select, String condition, String...param) {
		ArrayList<Object> list = searchTable(new SearchDTO(), table1 + " p", select, condition, param);
		
		ArrayList<SearchDTO> result = new ArrayList<SearchDTO>();
		
		for(int i = 0; i < list.size(); i++) {
			result.add((SearchDTO)list.get(i));
		}
		
		return result;
	}
	
	public ArrayList<GroupDTO> searchGroup(String condition, String...param) {
		ArrayList<Object> list = searchTable(new GroupDTO(), table2, "*", condition, param);
		
		ArrayList<GroupDTO> result = new ArrayList<GroupDTO>();
		
		for(int i = 0; i < list.size(); i++) {
			result.add((GroupDTO)list.get(i));
		}
		
		return result;
	}
	
	public int executeSQL(String sql, String... str) {
		int result = -1;
		try {
			conn = this.getConnnection();
			
			stmt = makeSQL(sql, str);
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return result;
	}
	
	private PreparedStatement makeSQL(String sql, String... str) throws Exception {
		int count = countQM(sql);
		
		if(count != str.length) {
			return stmt;
		}
		
		stmt = conn.prepareStatement(sql);
		
		for(int i = 0; i < count; i++) {
			stmt.setString(i + 1, str[i]);
		}
		
		return stmt;
	}
	
	private int countQM(String str) {
		int count = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '?') {
				count++;
			}
		}
		
		return count;
	}
}
