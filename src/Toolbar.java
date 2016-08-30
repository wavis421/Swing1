import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Toolbar extends JPanel {
	private JButton btnGetName;
	private JButton btnGetPhone;
	private ToolbarListener toolbarListener;

	public Toolbar() {
		setLayout (new FlowLayout(FlowLayout.CENTER));
		
		JToolBar toolbar = new JToolBar();
		btnGetName = new JButton("Get Name");
		btnGetPhone = new JButton("Get Phone #");

		add(toolbar);
		toolbar.add(btnGetName);
		toolbar.add(btnGetPhone);

		btnGetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolbarListener != null) {
					toolbarListener.outputText("\nGet Name button was pressed. \n");
					toolbarListener.outputName();
				}
			}
		});

		btnGetPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toolbarListener != null) {
					toolbarListener.outputText("\nGet Phone # button was pressed. \n");
					toolbarListener.outputPhone();
				}
			}
		});
	}

	public void setToolbarListener(ToolbarListener listener) {
		this.toolbarListener = listener;
	}

}
