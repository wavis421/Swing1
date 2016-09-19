package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog {
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private JTextField userField;
	private JPasswordField pwField;
	private PrefsListener prefsListener;

	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences...", true);
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		portSpinner = new JSpinner(new SpinnerNumberModel(3305, 0, 9999, 1));
		userField = new JTextField(10);
		pwField = new JPasswordField(10);
		pwField.setEchoChar('*');
		
		setPrefsLayout();

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = userField.getText();
				char[] password = pwField.getPassword();
				Integer port = (Integer) portSpinner.getValue();
				
				if (prefsListener != null)
					prefsListener.preferencesSet(user, new String(password), port);
				
				setVisible(false);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		setSize(265, 200);
		setLocationRelativeTo(parent);
	}

	private void setPrefsLayout() {
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		controlsPanel.setLayout(new GridBagLayout());
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//controlsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Border titleBorder = BorderFactory.createTitledBorder("Databases Preferences");
		Border spaceBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		// First row
		gc.gridx = gc.gridy = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets (0,0,0,15);
		controlsPanel.add(new JLabel("Username: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets (0,0,0,0);
		controlsPanel.add(userField, gc);

		// Next row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets (0,0,0,15);
		controlsPanel.add(new JLabel("Password: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets (0,0,0,0);
		controlsPanel.add(pwField, gc);

		// Next row
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = new Insets (0,0,0,15);
		controlsPanel.add(new JLabel("Port: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = new Insets (0,0,0,0);
		controlsPanel.add(portSpinner, gc);

		// Buttons row
		gc.gridy++;
		gc.gridx = 0;
		buttonsPanel.add(okButton);
		gc.gridx++;
		buttonsPanel.add(cancelButton);
		
		// Add to panel
		setLayout (new BorderLayout());
		add (controlsPanel, BorderLayout.CENTER);
		add (buttonsPanel, BorderLayout.SOUTH);
		
		// make OK & cancel buttons the same size
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
	}

	public void setPrefsListener(PrefsListener listener) {
		this.prefsListener = listener;
	}
	
	public void setDefaults (String user, String password, int port) {
		userField.setText(user);
		pwField.setText(password);
		portSpinner.setValue(port);
	}
}
