package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Family_expenses {
	private int id_name;
	private int id_ex;
	
	public Family_expenses(int id_name, int id_ex){
		this.id_name = id_name;
		this.id_ex = id_ex;

	}
		
	
	
	public Family_expenses(){
		
	}
	
	
	
	public int getAccessoriesId(){
		return id_name;
	}
	
	public void setAccessoriesId(int id_name){
		this.id_name = id_name;
	}
	
	public int getDeviceId(){
		return id_ex;
	}
	
	public void setDeviceId(int id_ex){
		this.id_ex = id_ex;
	}
	
	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_name);	
		data.add(id_ex);	
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select id_name from family where id_name = " + id_name + ";");
		while (rs.next()) {
			data.add(rs.getString("id_name"));
		}
		rs = statement.executeQuery("select id_ex from expenses where id_ex = " + id_ex + ";");
		while (rs.next()) {
			data.add(rs.getString("id_ex"));
		}
		
		return data;
	}

	public void addElement( int id_name, int id_ex, Connection connection)
			throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into family_expenses values(" + id_name + ", " +  id_ex + ");");
	}

	public void removeElement(int id_name, Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from family_expenses where idname = " + id_name + ";");
	}

	public void refreshElement(int id_name, int id_ex,
			Connection connection) throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update family_expenses set idname = " + id_name +
				", idex = " + id_ex + " where idex = " + id_ex + ";");
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
		columnNames.add("idex");
		return columnNames;
	}

	public ArrayList<Family_expenses> getTable(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from family_expenses");
		ArrayList<Family_expenses> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Family_expenses((int) rs.getObject(1),(int) rs.getObject(2)));
		}
		return res;
	}
}