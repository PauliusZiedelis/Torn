package Torn.Program.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Torn.Program.Constant.Constant;

public class AddGymInfo {
	static CRUD crud = new CRUD();
	static LocalDate today = LocalDate.now();
	
	public static void addGymInfo(String strength, String speed, String defense, String dexterity) {
		String fileLocation = Constant.gymInfoLocation;
		String breakPoint = Constant.breakPoint;
		StringBuilder strengthBuilder = new StringBuilder();
		StringBuilder speedBuilder = new StringBuilder();
		StringBuilder defenseBuilder = new StringBuilder();
		StringBuilder dexterityBuilder = new StringBuilder();
		strengthBuilder.append("Strengh info: ").append(strength);
		speedBuilder.append("Speed info: ").append(speed);
		defenseBuilder.append("Defense info: ").append(defense);
		dexterityBuilder.append("Dexterity info: ").append(dexterity);

		ArrayList<String> fileContent = new ArrayList<String>();
		fileContent.add(strengthBuilder.toString());
		fileContent.add(breakPoint);
		fileContent.add(speedBuilder.toString());
		fileContent.add(breakPoint);
		fileContent.add(defenseBuilder.toString());
		fileContent.add(breakPoint);
		fileContent.add(dexterityBuilder.toString());
		crud.write(fileContent, fileLocation, today.toString() + ".txt");
		addToDates();
	}
	public static void addToDates() {
		List<String> dates=crud.read(Constant.gymInfoLocation, "Dates.txt");
		if(!dates.get(dates.size()-1).equals(today.toString())) {
			dates.add(today.toString());
		}
		crud.write(dates, Constant.gymInfoLocation, "Dates.txt");
	}

}
