package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {
	private List<Person> db;
	private String colNames [] = {"ID", "Name", "Phone #", "Gender", "Is Citizen", "SSN", "Favorite Animal", "Favorite Food" };
	
	public void setData (List<Person> db)
	{
		this.db = db;
	}
	
	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public String getColumnName(int column) {
		return colNames [column];
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get (row);
		switch (col)
		{
		case 0: return person.getId();
		case 1: return person.getName(); 
		case 2: return person.getPhoneNum(); 
		case 3: return person.getGender();
		case 4: return person.isCitizen();
		case 5: return person.getSsn(); 
		case 6: return person.getAnimal(); 
		case 7: return person.getFood(); 
		}
		return null;
	}
}
