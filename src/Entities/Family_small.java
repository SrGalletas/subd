package Entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Family_small {
	private int id_name;
	private int id_small;

	public Family_small(int id_name, int id_small) {
		this.id_name = id_name;
		this.id_small = id_small;

	}

	public Family_small() {

	}

	public int getAccessoriesId() {
		return id_name;
	}

	public void setAccessoriesId(int id_name) {
		this.id_name = id_name;
	}

	public int getDeviceId() {
		return id_small;
	}

	public void setDeviceId(int id_small) {
		this.id_small = id_small;
	}

	public Vector<Object> setData(Connection connection) throws SQLException {
		Vector<Object> data = new Vector<Object>();
		data.add(id_name);
		data.add(id_small);
		Statement statement = connection.createStatement();
		ResultSet rs = statement
				.executeQuery("select id_name from family where id_name = "
						+ id_name + ";");
		while (rs.next()) {
			data.add(rs.getString("id_name"));
		}
		rs = statement
				.executeQuery("select id_small from small_expenses where id_small = "
						+ id_small + ";");
		while (rs.next()) {
			data.add(rs.getString("id_small"));
		}

		return data;
	}

	public void addElement(int id_name, int id_small, Connection connection)
			throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("insert into family_small values(" + id_name
				+ ", " + id_small + ");");
	}

	public void removeElement(int id_name, Connection connection)
			throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("delete from family_small where idname = "
				+ id_name + ";");
	}

	public void refreshElement(int id_name, int id_small, Connection connection)
			throws SQLException {
		Statement stmt = null;
		stmt = connection.createStatement();
		stmt.executeUpdate("update family_small set idname = " + id_name
				+ ", idsmall = " + id_small + " where idsmall = "
				+ id_small + ";");
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
		columnNames.add("idname");
		columnNames.add("idsmall");
		return columnNames;
	}

	public ArrayList<Family_small> getTable(Connection connection)
			throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select * from family_small");
		ArrayList<Family_small> res = new ArrayList<>();
		while (rs.next()) {
			res.add(new Family_small((int) rs.getObject(1), (int) rs
					.getObject(2)));
		}
		return res;
	}
}