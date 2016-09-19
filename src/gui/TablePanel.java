package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Person;

public class TablePanel extends JPanel {
	
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
	private JMenuItem removeItem;
	private PersonTableListener personTableListener;
	
	public TablePanel () {
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		popup = new JPopupMenu();
		removeItem = new JMenuItem("Delete row");
		
		popup.add(removeItem);
		
		// Detect right mouse click on table, then popup "Delete row" and select row
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
					int row = table.rowAtPoint(e.getPoint());
					table.getSelectionModel().setSelectionInterval(row, row);
				}
			}
		});
		
		// When "Delete row" selected, then trigger PersonTableListener action for this row
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personTableListener != null)
				{
					personTableListener.rowDeleted (row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}		
		});
		
		setLayout (new BorderLayout());
		add (new JScrollPane(table), BorderLayout.CENTER);
	}

	public void setData (List<Person> db) 
	{
		tableModel.setData(db);
	}
	
	public void setPersonTableListener (PersonTableListener listener) {
		this.personTableListener = listener;
	}
	
	public void refresh ()
	{
		tableModel.fireTableDataChanged();
	}
}
