package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Family {
	private int id_name;
	private String name;
//
//	 private ArrayList<Family_education> dev_acc;

	public Family(int id_name, String name) {
		this.id_name = id_name;
		this.name = name;
//		 this.dev_acc = new ArrayList<Family_education>();
	}

	public Family(String name ) {
		this.name = name;
//		 this.dev_acc = new ArrayList<Family_education>();
	}

	public Family() {

	}

	public int getId() {
		return id_name;
	}

	public void setId(int id_name) {
		this.id_name = id_name;
	}

	public String getFullName() {
		return name;
	}

	public void setFullName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_name);
		data.add(name);

		return data;
	}

	public void addElement(String name, Connection connection)
			throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("insert into family values( nextval('seq_family'), '" + name + "');");
	}

	public void removeElement(int id_name, Connection connection)
			throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("delete from family where id_name = " + id_name + ";");
	}

	public void refreshElement(int id_name, String name, Connection connection)
			throws SQLException {
		Statement statement = null;
		statement = connection.createStatement();
		statement.executeUpdate("update family set name = '" + name + "' "
				+ " where id_name = " + id_name + ";");
	}

	public DefaultTableModel TableModel(Connection connection)
			throws SQLException {
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
		columnNames.add("id_name");
		columnNames.add("name");
		return columnNames;
	}

	public ArrayList<Family> getTable(Connection connection)
			throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from family;");
		ArrayList<Family> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Family((int) rs.getObject(1), rs.getObject(2).toString()));
		}
		return res;
	}

}
