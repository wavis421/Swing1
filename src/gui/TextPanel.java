package gui;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class TextPanel extends JPanel {
	private JTextArea textArea;
	
	public TextPanel (String str) {
		textArea = new JTextArea(str);
		
		JScrollPane scrollPane = new JScrollPane (textArea);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add (scrollPane);
	}
	
	public void appendText (String str) {
		textArea.append(str);
	}
}
