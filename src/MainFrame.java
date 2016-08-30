import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

public class MainFrame {
	private JFrame frame;
	private Toolbar toolbar;
	private TextPanel textPanel;
	private FormPanel formPanel;
	private JMenuBar menuBar;
	
	private final int FRAME_WIDTH = 600;
	private final int FRAME_HEIGHT = 600;
	private final int TOOLBAR_HEIGHT = 40;
	private final int FORM_WIDTH = 200;

	public MainFrame() {

		frame = new JFrame("App");
		toolbar = new Toolbar();
		toolbar.setPreferredSize(new Dimension (FRAME_WIDTH, TOOLBAR_HEIGHT));
		textPanel = new TextPanel(FRAME_WIDTH - FORM_WIDTH, FRAME_HEIGHT - TOOLBAR_HEIGHT,
				"This is my new text area!\n");
		formPanel = new FormPanel(FORM_WIDTH, FRAME_HEIGHT - TOOLBAR_HEIGHT);
		
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
				textPanel.appendText("\nForm submitted: \n");
				textPanel.appendText("     Name     = " + ev.getName() + "\n");
				textPanel.appendText("     Phone #  = " + ev.getPhoneNum() + "\n");
				textPanel.appendText("     Animal   = " + ev.getAnimal().toString() + 
						", ID = " + ev.getAnimal().getId() + "\n");
				textPanel.appendText("     Fav Food = " + ev.getFood().toString() + 
						", ID = " + ev.getFood().getId() + "\n");
				textPanel.appendText("     Is American Citizen = " + ev.isCitizen() + "\n");
				textPanel.appendText("     SSN = " + ev.getSsn() + "\n");
				textPanel.appendText("     Gender = " + ev.getGender() + "\n");
			}
		});

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.add(createMenuBar(), BorderLayout.NORTH);
		frame.add(toolbar, BorderLayout.SOUTH);
		frame.add(textPanel, BorderLayout.EAST);
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
				System.out.println("Exit action listener: event = " + e.getSource());
				int action = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", 
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION)
					System.exit(0);
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
