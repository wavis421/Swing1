package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileFilter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;

public class MainFrame {
	private JFrame frame;
	private Toolbar toolbar;
	private TextPanel textPanel;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;

	public MainFrame() {

		frame = new JFrame("App");
		toolbar = new Toolbar();
		textPanel = new TextPanel ("This is my new text area!\n");
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		fileChooser = new JFileChooser();
		controller = new Controller();
		
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		frame.setJMenuBar (createMenuBar());
		tablePanel.setData(controller.getPeople());
		
		toolbar.setToolbarListener(new ToolbarListener() {
			public void outputName() {
				textPanel.appendText("Name is " + formPanel.getName() + "\n");
			}
			public void outputPhone() {
				textPanel.appendText("Phone # is " + formPanel.getPhoneNum() + "\n");
			}
			public void outputText(String text) {
				textPanel.appendText(text);
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			public void submitForm(FormEvent ev) {
				controller.addPerson(ev);
				tablePanel.refresh();
			}
		});

		//frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.add(toolbar, BorderLayout.SOUTH);
		//frame.add(textPanel, BorderLayout.EAST);
		frame.add(tablePanel, BorderLayout.CENTER);
		frame.add(formPanel, BorderLayout.WEST);

		frame.pack();
	}
	
	private JMenuBar createMenuBar () {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu ("File");
		menuBar.add(fileMenu);
		
		JMenuItem exportDataItem = new JMenuItem ("Export...");
		JMenuItem importDataItem = new JMenuItem ("Import...");
		JMenuItem exitItem = new JMenuItem ("Exit");
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
		
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
		
		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
					System.out.println("Import filename = " + fileChooser.getSelectedFile());
			}
		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
					System.out.println("Export filename = " + fileChooser.getSelectedFile());
			}
		});
		
		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);
		JMenu showMenu = new JMenu ("Show");
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
		
		return menuBar;
	}
}
