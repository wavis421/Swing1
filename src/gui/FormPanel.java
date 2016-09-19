package gui;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	private JLabel lblName, lblPhone, lblAnimal, lblFood, lblCitizen, lblSsn;
	private JTextField textName, textPhone, textSSN;
	private JList<selectItem> animalList;
	private JComboBox<selectItem> foodCombo;
	private JCheckBox americanCitizenCheck;
	private JButton btnSubmit;
	private JRadioButton maleRadio, femaleRadio;
	private ButtonGroup genderGroup;
	private FormListener formListener;

	public FormPanel() {
		// Create borders
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// Create labels, text fields, animal list, food combo box and submit button
		lblName = new JLabel("Name:");
		lblPhone = new JLabel("Ph #:");
		lblAnimal = new JLabel("Fav Animal:");
		lblFood = new JLabel("Fav Food:");
		lblCitizen = new JLabel ("American Citizen: ");
		lblSsn = new JLabel ("SSN: ");
		textName = new JTextField(10);
		textPhone = new JTextField(10);
		americanCitizenCheck = new JCheckBox();
		textSSN = new JTextField(10);
		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		genderGroup = new ButtonGroup();

		createAnimalList();
		createFoodComboBox();

		americanCitizenCheck.setSelected(false);
		textSSN.setEnabled(false);
		lblSsn.setEnabled(false);
		
		americanCitizenCheck.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = americanCitizenCheck.isSelected();
				lblSsn.setEnabled(isTicked);
				textSSN.setEnabled(isTicked);
			}
		});
		
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText();
				String phoneNum = textPhone.getText();
				selectItem animal = (selectItem) animalList.getSelectedValue();
				selectItem food = (selectItem) foodCombo.getSelectedItem();
				boolean citizen = americanCitizenCheck.isSelected();
				String ssn = "";
				String gender = genderGroup.getSelection().getActionCommand();
				
				if (citizen)
					ssn = textSSN.getText();
				
				FormEvent ev = new FormEvent(this, name, phoneNum, animal, food, citizen, ssn, gender);
				if (formListener != null)
					formListener.submitForm(ev);
			}
		});
		
		setFormLayout();
	}

	private void addFormRow (GridBagConstraints gcon, JLabel lbl, Component value, int gridY) {
		gcon.gridx = 0;
		gcon.gridy = gridY;
		gcon.anchor = GridBagConstraints.LINE_END;
		add (lbl, gcon);
		gcon.gridx++;
		gcon.anchor = GridBagConstraints.LINE_START;
		add (value, gcon);
	}
	
	private void setFormLayout () {
		int gridY = 0;
		setLayout (new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets (0,5,0,5);
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		
		addFormRow (gc, lblName, textName, gridY++);
		addFormRow (gc, lblPhone, textPhone, gridY++);
		addFormRow (gc, lblAnimal, animalList, gridY++);
		addFormRow (gc, lblFood, foodCombo, gridY++);
		addFormRow (gc, lblCitizen, americanCitizenCheck, gridY++);
		addFormRow (gc, lblSsn, textSSN, gridY++);
		
		/* GENDER row (special handling) */
		gc.gridx = 1;
		gc.gridy = gridY++;
		gc.anchor = GridBagConstraints.LINE_START;
		add(maleRadio, gc);
		gc.gridy = gridY++;
		add(femaleRadio, gc);
		
		/* SUBMIT row (special handling) */
		gc.weighty = 1;
		gc.gridx = 1;
		gc.gridy++;
		gc.insets = new Insets(15,-30,0,0);
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(btnSubmit, gc);
	}

	private void createAnimalList() {
		DefaultListModel<selectItem> animalModel = new DefaultListModel<selectItem>();
		animalModel.addElement(new selectItem(1, "Rabbit"));
		animalModel.addElement(new selectItem(2, "Lion"));
		animalModel.addElement(new selectItem(3, "Kangaroo"));
		animalList = new JList<selectItem>(animalModel);
		animalList.setSelectedIndex(1);
		animalList.setBorder(BorderFactory.createEtchedBorder());
		animalList.setMinimumSize(new Dimension(90, animalList.getPreferredSize().height));
	}

	private void createFoodComboBox() {
		DefaultComboBoxModel<selectItem> foodModel = new DefaultComboBoxModel<selectItem>();
		foodModel.addElement(new selectItem(1, "Pizza"));
		foodModel.addElement(new selectItem(2, "Ice Cream"));
		foodModel.addElement(new selectItem(3, "Chili"));
		foodCombo = new JComboBox<selectItem>(foodModel);
		foodCombo.setSelectedIndex(1);
		foodCombo.setBorder(BorderFactory.createEtchedBorder());
		foodCombo.setMaximumSize(new Dimension(foodCombo.getMaximumSize().width, lblName.getPreferredSize().height));
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

class selectItem {
	private int id;
	private String type;

	public String toString() {
		return type;
	}

	public selectItem(int id, String type) {
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}
}
