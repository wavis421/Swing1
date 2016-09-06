package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.FormEvent;
import model.Animals;
import model.Database;
import model.Foods;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	
	public void addPerson (FormEvent ev)
	{
		String animalString = ev.getAnimal();
		String foodString = ev.getFood();
		String genderString = ev.getGender();
		
		Animals animal = null;
		Foods food = null;
		Gender gender = null;
		
		if (animalString.equals("Rabbit"))
			animal = Animals.RABBIT;
		else if (animalString.equals("Lion"))
			animal = Animals.LION;
		else if (animalString.equals("Kangaroo"))
			animal = Animals.KANGAROO;
		
		if (foodString.equals("Pizza"))
			food = Foods.PIZZA;
		else if (foodString.equals("Ice Cream"))
			food = Foods.ICE_CREAM;
		else if (foodString.equals("Chili"))
			food = Foods.CHILI;
			
		if (genderString.equals("male"))
			gender = Gender.MALE;
		else if (genderString.equals("female"))
			gender = Gender.FEMALE;
		
		Person person = new Person (ev.getName(), ev.getPhoneNum(), animal, food,
			ev.isCitizen(), ev.getSsn(), gender);
		db.addPerson(person);
	}
	
	public List<Person> getPeople () {
		return db.getPeople();
	}
	
	public void saveToFile (File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile (File file) throws IOException {
		db.loadFromFile (file);
	}
}
