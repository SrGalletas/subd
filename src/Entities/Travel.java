package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Travel {
	private int id_travel;
	private String t_exp;
	private int t_m;
	
	public Travel(int id_travel, String t_exp, int t_m){
		this.id_travel = id_travel;
		this.t_exp = t_exp;
		this.t_m = t_m;
	}
	
	public Travel(String t_exp, int t_m){
		this.t_exp = t_exp;
		this.t_m = t_m;
	}
	
	public Travel(){
		
	}
	
	public int getId(){
		return id_travel;
	}
	
	public void setId(int id_travel){
		this.id_travel = id_travel;
	}
	
	public String getBreakdown(){
		return t_exp;
	}
	
	public void setBreakdown(String t_exp){
		this.t_exp = t_exp;
	}
	
	
	public int getSum(){
		return t_m;
	}
	
	public void setSum(int t_m){
		this.t_m = t_m;
	}
	
	
	
	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_travel);
		data.add(t_exp);
		data.add(t_m);
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

	public void addElement(String t_exp, int t_m, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into travels values(nextval('seq_travels'),'" + t_exp + "', '" +  t_m + ");");
	}

	public void removeElement(int id_travel, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from travels where id_travel = " + id_travel + ";");
	}

	public void refreshElement(int id_travel, String t_exp, int t_m, Connection connection) throws SQLException {		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("update travels set travel_expenses = '" + t_exp + "', travel_money = " + t_m + " where id_travel = " + id_travel + ";");
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
		columnNames.add("id_travel");
		columnNames.add("Type");
		columnNames.add("Money");
		return columnNames;
	}

	public ArrayList<Travel> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from travels");
		ArrayList<Travel> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Travel((int) rs.getObject(1), rs.getObject(2).toString(),(int) rs.getObject(3)));
		}
		return res;
	}


	
}
