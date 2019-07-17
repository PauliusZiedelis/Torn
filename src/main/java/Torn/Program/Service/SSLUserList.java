package Torn.Program.Service;

import java.util.ArrayList;
import java.util.List;

import Torn.Program.Constant.Constant;
import Torn.Program.Model.SSLUser;

public class SSLUserList {
	static String fileLocation = Constant.sslListFileLocation;
	static String fileName = Constant.sslUsersListFileName;
	static CRUD crud = new CRUD();

	public static void addSSLUser(String nameID) {
		ArrayList<String> sslUsersList = crud.read(fileLocation, fileName);
		if (nameID != null && !nameID.contains("name [id]")) {
			sslUsersList.add(nameID);
			crud.write(sslUsersList, fileLocation, fileName);
		}
	}

	public static ArrayList<SSLUser> getSSLUsersList() {
		ArrayList<String> sslUsersList = crud.read(fileLocation, fileName);
		ArrayList<SSLUser> sslUsersListfilled = new ArrayList<SSLUser>();
		for (int i = 0; i < sslUsersList.size(); i++) {
			String[] line = sslUsersList.get(i).split(" ");
			SSLUser sslUser = new SSLUser();
			sslUser.name = line[0];
			sslUser.id = line[1].replaceAll("\\[", "").replaceAll("\\]", "");
			sslUsersListfilled.add(sslUser);
		}
		if (!sslUsersListfilled.isEmpty()) {
			return sslUsersListfilled;
		} else {
			System.out.println("fucked");
		}
		return sslUsersListfilled;
	}

	public static void removeSSLUser(String sslUser) {
		List<String> sslUsersList = crud.read(fileLocation, fileName);
		for (int i = 0; i < sslUsersList.size(); i++) {
			if (sslUsersList.get(i).equals(sslUser)) {
				sslUsersList.remove(i);
				crud.write(sslUsersList, fileLocation, fileName);
			}
		}
	}
}
