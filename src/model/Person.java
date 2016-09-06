package model;

import java.io.Serializable;

public class Person implements Serializable {
	private static int count = 0;
	
	private String name;
	private String phoneNum;
	private Animals animal;
	private Foods food;
	private boolean isCitizen;
	private String ssn;
	private Gender gender;
	private int id;
	
	public Person (String name, String phoneNum, Animals animal, Foods food,
			boolean citizen, String ssn, Gender gender)
	{
		this.name = name;
		this.phoneNum = phoneNum;
		this.animal = animal;
		this.food = food;
		this.isCitizen = citizen;
		this.ssn = ssn;
		this.gender = gender;
		
		this.id = count;
		count++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Animals getAnimal() {
		return animal;
	}

	public void setAnimal(Animals animal) {
		this.animal = animal;
	}

	public Foods getFood() {
		return food;
	}

	public void setFood(Foods food) {
		this.food = food;
	}

	public boolean isCitizen() {
		return isCitizen;
	}

	public void setCitizen(boolean isCitizen) {
		this.isCitizen = isCitizen;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
