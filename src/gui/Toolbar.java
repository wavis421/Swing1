package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {
	private JButton btnSave;
	private JButton btnRefresh;
	private ToolbarListener toolbarListener;

	public Toolbar() {
		setBorder (BorderFactory.createEtchedBorder());
		
		btnSave = new JButton();
		btnRefresh = new JButton();
		
		btnSave.setToolTipText("Save");
		btnRefresh.setToolTipText("Refresh");
		
		btnSave.setIcon(createIcon("/images/save_icon_16.png"));
		btnRefresh.setIcon(createIcon("/images/refresh_icon_16.png"));
		
		add(btnSave);
		add(btnRefresh);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolbarListener != null) {
					toolbarListener.saveToFile();
				}
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolbarListener != null) {
					toolbarListener.refreshFromFile();
				}
			}
		});
	}

	public void setToolbarListener(ToolbarListener listener) {
		this.toolbarListener = listener;
	}
	
	private ImageIcon createIcon (String path) {
		URL url = getClass().getResource(path);
		ImageIcon icon = new ImageIcon (url);
		return icon;
	}
}
