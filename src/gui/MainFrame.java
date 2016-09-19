package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MainFrame extends JFrame {
	private Toolbar toolbar;
	private TextPanel textPanel;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private static PrefsDialog prefsDialog;
	private static Preferences prefs;

	public MainFrame() {
		super("App");
		setLayout (new BorderLayout());
		
		// Instantiate all components
		toolbar = new Toolbar();
		textPanel = new TextPanel("This is my new text area!\n");
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		fileChooser = new JFileChooser();
		controller = new Controller();
		prefsDialog = new PrefsDialog(MainFrame.this);

		prefs = Preferences.userRoot().node("AppPref");
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		setJMenuBar(createMenuBar());
		tablePanel.setData(controller.getPeople());

		// TOOLBAR listener: handle save & refresh buttons
		toolbar.setToolbarListener(new ToolbarListener() {
			public void saveToFile() {
				if (fileChooser.getSelectedFile() != null) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(MainFrame.this, "Must first import/export database to set filename");
			}
			
			public void refreshFromFile() {
				if (fileChooser.getSelectedFile() != null) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(MainFrame.this, "Must first import/export database to set filename");
			}
		});

		// FORM listener
		formPanel.setFormListener(new FormListener() {
			public void submitForm(FormEvent ev) {
				controller.addPerson(ev);
				tablePanel.refresh();
			}
		});

		// TABLE listener
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				controller.removePerson(row);
			}
		});

		// Save and restore preferences
		MainFrame.prefsDialog.setPrefsListener(new PrefsListener() {
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});

		MainFrame.prefsDialog.setDefaults(prefs.get("user", ""), prefs.get("password", ""), prefs.getInt("port", 1357));

		// Make form visible
		setSize (600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// ADD all components
		add(toolbar, BorderLayout.PAGE_START);
		// frame.add(textPanel, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		pack();
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		// Create FILE menu items
		JMenuItem exportDataItem = new JMenuItem("Export...");
		JMenuItem importDataItem = new JMenuItem("Import...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		// Add mnemonics and accelerators to file menu items
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		// Add EXIT listener
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure you want to exit?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});

		// Add IMPORT Data listener
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// Add EXPORT data listener
		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
						tablePanel.refresh();

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		// Create WINDOW menu
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);

		// Add SHOW sub-menu
		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);

		// Add Preferences sub-menu
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		windowMenu.add(prefsItem);
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
		});

		return menuBar;
	}
}
