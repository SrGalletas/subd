package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Tipe {
	private int id_type;
	private String Type;
	
	public Tipe(int id_type, String Type){
		this.id_type = id_type;
		this.Type = Type;
	}
	public Tipe(String Type){
		this.Type = Type;
	}
	
	
	public Tipe(){
		
	}
	
	public int getId(){
		return id_type;
	}
	
	public void setId(int id_type){
		this.id_type = id_type;
	}
	
	public String getType(){
		return Type;
	}
	
	public void setType(String Type){
		this.Type = Type;
	}
	
	
	
	
	
	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_type);
		data.add(Type);
//		Statement statement = connection.createStatement();
//		ResultSet rs = statement.executeQuery("select fullname from customer where customerid = " + customerid + ";");
//		while (rs.next()) {
//			data.add(rs.getString("fullname"));		
//		}
//		rs = statement.executeQuery("select fullname from master where masterid = " + masterid + ";");
//		while (rs.next()) {
//			data.add(rs.getString("fullname"));
//		}
//		rs = statement.executeQuery("select type from device where deviceid = " + deviceid + ";");
//		while (rs.next()) {
//			data.add(rs.getString("type"));
//		}
		return data;

	}

	public void addElement(String Type, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into type values(nextval('seq_type'),'" + Type + "');");
	}

	public void removeElement(int id_type, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from type where id_type = " + id_type + ";");
	}

	public void refreshElement(int id_type, String Type, Connection connection) throws SQLException {		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("update type set Type = '" + Type + "' where id_type = " + id_type + ";");
	}

	public DefaultTableModel TableModel(Connection connection) throws SQLException {
		Vector<Object> columnNames = null;
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		DefaultTableModel tableModel = new DefaultTableModel();
		columnNames = getTitles();
		data.add(columnNames);
		for (int i = 0; i <= getTable(connection).size() - 1; i++) {
			data.add(getTable(connection).get(i).setData(connection));
		}
		tableModel.setDataVector(data, columnNames);
		return tableModel;
	}

	public Vector<Object> getTitles() {
		Vector<Object> columnNames = new Vector<Object>();
		columnNames.add("id_type");
		columnNames.add("Type");
		return columnNames;
	}

	public ArrayList<Tipe> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from type");
		ArrayList<Tipe> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Tipe((int) rs.getObject(1), rs.getObject(2).toString()));
		}
		return res;
	}
//	public ArrayList<Family_education> getTable(Connection connection) throws SQLException {
//		Statement statement = connection.createStatement();
//		ResultSet rs = statement.executeQuery("select * from family_education");
//		ArrayList<Family_education> res = new ArrayList<>();
//		while (rs.next()) {
//			res.add(new Family_education((int) rs.getObject(1),(int) rs.getObject(2)));
//		}
//		return res;
//	}
	
}
