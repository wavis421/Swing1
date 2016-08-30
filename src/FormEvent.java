import java.util.EventObject;

public class FormEvent extends EventObject {
	private String name;
	private String phoneNum;
	private selectItem animal;
	private selectItem food;
	private boolean isCitizen;
	private String ssn;
	private String gender;
	
	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source, String name, String phoneNum, selectItem animal, selectItem food, boolean isCitizen, String ssn, String gender) {
		super(source);
		
		this.name = name;
		this.phoneNum = phoneNum;
		this.animal = animal;
		this.food = food;
		this.isCitizen = isCitizen;
		this.ssn = ssn;
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public selectItem getAnimal() {
		return animal;
	}

	public selectItem getFood() {
		return food;
	}

	public boolean isCitizen() {
		return isCitizen;
	}

	public String getSsn() {
		return ssn;
	}

	public String getGender() {
		return gender;
	}
}
