import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class FormPanel extends JPanel {

	private JPanel formPanel;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblAnimal;
	private JLabel lblFood;
	private JLabel lblCitizen;
	private JLabel lblSsn;
	private JTextField textName;
	private JTextField textPhone;
	private JList<selectItem> animalList;
	private JComboBox<selectItem> foodCombo;
	private JCheckBox americanCitizenCheck;
	private JTextField textSSN;
	private JButton btnSubmit;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	private FormListener formListener;

	public FormPanel(int width, int height) {
		// Create form panel size and borders
		formPanel = new JPanel();
		formPanel.setPreferredSize(new Dimension(width, height));
		Border innerBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		formPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// Set up layout
		GroupLayout layout = new GroupLayout(formPanel);
		formPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		add(formPanel);

		// Create labels, text fields, animal list, food combo box and submit
		// button
		lblName = new JLabel("Name:");
		lblPhone = new JLabel("Ph #:");
		lblAnimal = new JLabel("Fav Animal:");
		lblFood = new JLabel("Fav Food:");
		lblCitizen = new JLabel ("American Citizen: ");
		lblSsn = new JLabel ("SSN: ");
		textName = new JTextField();
		textPhone = new JTextField();
		americanCitizenCheck = new JCheckBox();
		textSSN = new JTextField();
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
				String ssn = textSSN.getText();
				String gender = genderGroup.getSelection().getActionCommand();
				
				if (citizen)
					ssn = textSSN.getText();
				else
					ssn = "";
				
				FormEvent ev = new FormEvent(this, name, phoneNum, animal, food, citizen, ssn, gender);
				if (formListener != null)
					formListener.submitForm(ev);
			}
		});
		
		setLayout(layout);
	}

	private void setLayout(GroupLayout layout) {
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()   // label & text 
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblPhone))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(textName)
								.addComponent(textPhone)))
				.addGroup(layout.createSequentialGroup()   // animal
						.addComponent(lblAnimal)
						.addComponent(animalList))
				.addGroup(layout.createSequentialGroup()   // food
						.addComponent(lblFood)
						.addComponent(foodCombo))
				.addGroup(layout.createSequentialGroup()   // citizen check
						.addComponent(lblCitizen)
						.addComponent(americanCitizenCheck))
				.addGroup(layout.createSequentialGroup()   // SSN
						.addComponent(lblSsn)
						.addComponent(textSSN))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(maleRadio)
						.addComponent(femaleRadio))
				.addGroup(layout.createSequentialGroup()   // submit
						.addComponent(btnSubmit)));

		layout.linkSize(SwingConstants.HORIZONTAL, lblName, lblPhone);

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textName))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblPhone)
						.addComponent(textPhone))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblAnimal)
						.addComponent(animalList))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblFood)
						.addComponent(foodCombo))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblCitizen)
						.addComponent(americanCitizenCheck))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblSsn)
						.addComponent(textSSN))
				.addGroup(layout.createSequentialGroup()
						.addComponent(maleRadio)
						.addComponent(femaleRadio))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGap(60)
						.addComponent(btnSubmit)));
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

	public String getName() {
		return textName.getText();
	}

	public String getPhoneNum() {
		return textPhone.getText();
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
