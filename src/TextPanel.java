import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class TextPanel extends JPanel {
	private JTextArea textArea;
	
	public TextPanel (int width, int height, String str) {
		textArea = new JTextArea(str);
		
		JScrollPane scrollPane = new JScrollPane (textArea);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		scrollPane.setPreferredSize(new Dimension(width,height));
		add (scrollPane);

	}
	
	public void appendText (String str) {
		textArea.append(str);
	}
}
