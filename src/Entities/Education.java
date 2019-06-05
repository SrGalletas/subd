package Entities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Education {
	private int id_education;
	private String ed_exp;
	private int ed_m;
	private String date;

	
	public Education(int id_education, String ed_exp, int ed_m, String date){
		this.id_education = id_education;
		this.ed_exp = ed_exp;
		this.ed_m = ed_m;
		this.date = date;
	}
	
	public Education(String ed_exp, int ed_m, String date ){
		this.ed_exp = ed_exp;
		this.ed_m = ed_m;
		this.date = date;

	}
	
	public Education(){
		
	}
	
	public int getId(){
		return id_education;
	}
	
	public void setId(int id_education){
		this.id_education = id_education;
	}
	
	public String getType(){
		return ed_exp;
	}
	
	public void setType(String ed_exp){
		this.ed_exp = ed_exp;
	}
	
	public int getManufacturer(){
		return ed_m;
	}
	
	public void setManufacturer(int ed_m){
		this.ed_m = ed_m;
	}
	
	public String getModel(){
		return date;
	}
	
	public void setModel(String date){
		this.date = date;
	}
	
	public Vector<Object> setData(Connection conection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_education);
		data.add(ed_exp);
		data.add(ed_m);
		data.add(date);
		return data;
	}

	public void addElement(String ed_exp, int ed_m, String date, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into education values(nextval('seq_education'),'" + ed_exp + "', " + ed_m + ", '" + date + "');");
	}

	public void removeElement(int id_education, Connection connection) throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from education where id_education = " + id_education + ";");
	}

	public void refreshElement(int id_education, String ed_exp, int ed_m, String date, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update education set education_expenses = '" + ed_exp + "', education_money = '" + ed_m + "', date = '" + date + "' where id_education = " + id_education + ";");
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
		columnNames.add("id_education");
		columnNames.add("Type");
		columnNames.add("Money");
		columnNames.add("Date");
		return columnNames;
	}

	public ArrayList<Education> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from education;");
		ArrayList<Education> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Education((int) rs.getObject(1), rs.getObject(2).toString(), (int) rs.getObject(3), rs.getObject(4).toString()));
		}
		return res;
	}
	
}
