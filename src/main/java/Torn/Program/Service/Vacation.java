package Torn.Program.Service;

import java.util.List;

import Torn.Program.Constant.Constant;

public class Vacation {
	static CRUD crud = new CRUD();
	static String vacationFileLocation=Constant.vacationListFileLocation;
	static String vacationFileName=Constant.vacationListFileName;
	
	public static void addToVacationList(String nameAndID, String dateFrom, String dateTo ) {
		String addInfo=nameAndID+" "+dateFrom+" - "+dateTo;
		System.out.println(addInfo);
		List<String> vacationList=crud.read(vacationFileLocation, vacationFileName);
		vacationList.add(addInfo);
		crud.write(vacationList, vacationFileLocation, vacationFileName);
	}
}
