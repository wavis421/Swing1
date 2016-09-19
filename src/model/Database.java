package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private List<Person> people;
	
	public Database () {
		people = new LinkedList<Person>(); 
	}

	public void addPerson (Person person)
	{
		people.add(person);
	}
	
	public void removePerson (int row)
	{
		people.remove(row);
	}
	
	public List<Person> getPeople ()
	{
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile (File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		// Convert to array
		Person[] persons = people.toArray(new Person[people.size()]);
		
		oos.writeObject(persons);
		oos.close();
	}
	
	public void loadFromFile (File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			// Convert from array to people 
			Person[] persons = (Person[]) ois.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}
}
