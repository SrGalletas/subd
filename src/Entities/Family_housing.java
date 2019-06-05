package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Family_housing {
	private int id_name;
	private int id_housing;
	
	public Family_housing(int id_name, int id_housing){
		this.id_name = id_name;
		this.id_housing = id_housing;

	}
		
	
	
	public Family_housing(){
		
	}
	
	
	
	public int getAccessoriesId(){
		return id_name;
	}
	
	public void setAccessoriesId(int id_name){
		this.id_name = id_name;
	}
	
	public int getDeviceId(){
		return id_housing;
	}
	
	public void setDeviceId(int id_housing){
		this.id_housing = id_housing;
	}
	
	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_name);	
		data.add(id_housing);	
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select id_name from family where id_name = " + id_name + ";");
		while (rs.next()) {
			data.add(rs.getString("id_name"));
		}
		rs = statement.executeQuery("select id_housing from housing where id_housing = " + id_housing + ";");
		while (rs.next()) {
			data.add(rs.getString("id_housing"));
		}
		
		return data;
	}

	public void addElement( int id_name, int id_housing, Connection connection)
			throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into family_housing values(" + id_name + ", " +  id_housing + ");");
	}

	public void removeElement(int id_name, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from family_housing where idname = " + id_name + ";");
	}

	public void refreshElement(int id_name, int id_housing,
			Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update family_housing set idname = " + id_name +
				", idhousing = " + id_housing + " where idhousing = " + id_housing + ";");
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
		columnNames.add("idname");
		columnNames.add("idhousing");
		return columnNames;
	}

	public ArrayList<Family_housing> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from family_housing");
		ArrayList<Family_housing> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Family_housing((int) rs.getObject(1),(int) rs.getObject(2)));
		}
		return res;
	}
}