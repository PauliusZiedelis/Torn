package Torn.Program.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import Torn.Program.Constant.Constant;
import Torn.Program.DTO.MembersListDTO;
import Torn.Program.Model.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class GetMembersList {
	static CRUD crud = new CRUD();
	public static MembersListDTO getMembersList() {
		try {
			// Creating url
			String api = Constant.getApi();
			String membersListUrl = "https://api.torn.com/faction/937?selections=basic&key=" + api;

			// making connection
			URLConnection urlConnection;
			urlConnection = new URL(membersListUrl).openConnection();
			urlConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			urlConnection.connect();

			// reading from web and parsing Json to string
			JsonReader reader = new JsonReader(new InputStreamReader(urlConnection.getInputStream()));
			JsonParser parser = new JsonParser();
			String initialStrngParsed = parser.parse(reader).toString();

			// Checking if API is correct
			if (initialStrngParsed.contains("{\"error\":{\"code\":2,\"error\":\"Incorrect key\"}}")) {
				System.out.println("Wrong api code");
				return takeMembersListFromFile();
			} else {
				// Making members list
				return makeListOfMembers(initialStrngParsed);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return takeMembersListFromFile();
		}
	}

	public static MembersListDTO makeListOfMembers(String stringOfMembers) {
		List<Member> membersList = new ArrayList<Member>();
		// Pattern
		// Grup1(\d+)Grup2(\"\:\{\"name\"\:\")Grup3(.+?\")Grup4(\,\"days_in_faction\"\:)Grup5(\d+)Grup6(\,\"last_action\"\:\")Grup7(.+?\")Grup8(\,\"status\"\:\[")Grup9(.+?\"\]\})
		// Grup1(38465)Grup2(":{"name":")Grup3(JZZ")Grup4(,"days_in_faction":)Grup5(3713)Grup6(,"last_action":")Grup7(1
		// hour ago")Grup8(,"status":[")Grup9(Okay",""]})
		Pattern userPattern = Pattern.compile(
				"(\\d+)(\\\"\\:\\{\\\"name\\\"\\:\\\")(.+?\\\")(\\,\\\"days_in_faction\\\"\\:)(\\d+)(\\,\\\"last_action\\\"\\:\\\")(.+?\\\")(\\,\\\"status\\\"\\:\\[\")(.+?\\\"\\]\\})");
		// Creating member and adding to the list
		Matcher m = userPattern.matcher(stringOfMembers);
		while (m.find()) {
			Member member = new Member();
			member.id = m.group(1);
			member.name = m.group(3).replaceAll("\"", "");
			member.days_in_faction = Integer.parseInt(m.group(5));
			if (m.group(7).contains("minute")) {
				String[] lastAction = m.group(7).split(" ");
				member.last_action = Integer.parseInt(lastAction[0]);
			}
			if (m.group(7).contains("hour")) {
				String[] lastAction = m.group(7).split(" ");
				member.last_action = Integer.parseInt(lastAction[0]) * 60;
			}
			if (m.group(7).contains("day")) {
				String[] days = m.group(7).split(" ");
				member.last_action = Integer.parseInt(days[0]) * 24 * 60;
			}
			member.status = m.group(9).replaceAll("\",\"\"]}", "");
			membersList.add(member);
		}
		if (!membersList.isEmpty()) {
			updateFCMemebersList(membersList);
			MembersListDTO membersListDTO = new MembersListDTO();
			membersListDTO.isSucses = true;
			membersListDTO.membersList = membersList;
			return membersListDTO;
		} else {
			return takeMembersListFromFile();
		}
	}

	public static void updateFCMemebersList(List<Member> membersList) {
		String location = Constant.membersListLocation;
		String fileName = Constant.membersListFileName;
		ArrayList<String> fileContent = new ArrayList<String>();
		for (int i = 0; i < membersList.size(); i++) {
			StringBuilder lineB = new StringBuilder();
			lineB.append("Name: ").append(membersList.get(i).name);
			if (membersList.get(i).name.length() < 10) {
				lineB.append("	");
			}
			lineB.append("	").append("ID: ").append(membersList.get(i).id);
			if (membersList.get(i).days_in_faction == 1) {
				lineB.append("	").append("Days in FC: ").append(membersList.get(i).days_in_faction).append(" day");
			}
			if (membersList.get(i).days_in_faction != 1) {
				lineB.append("	").append("Days in FC: ").append(membersList.get(i).days_in_faction).append(" days");
			}
			if (membersList.get(i).last_action == 1) {
				lineB.append("	").append("Last action: ").append(membersList.get(i).last_action).append(" minete ago");
			}
			if (membersList.get(i).last_action != 1) {
				lineB.append("	").append("Last action: ").append(membersList.get(i).last_action)
						.append(" minutes ago");
			}
			lineB.append("	").append("Satatus: ").append(membersList.get(i).status);
			String line = lineB.toString();
			fileContent.add(line);
		}
		crud.write(fileContent, location, fileName);
	}

	public static MembersListDTO takeMembersListFromFile() {
		List<Member> membersList = new ArrayList<Member>();
		String location = Constant.membersListLocation;
		String fileName = Constant.membersListFileName;
		ArrayList<String> fileContent = crud.read(location, fileName);
		if (!fileContent.isEmpty()) {
			for (int i = 0; i < fileContent.size(); i++) {
				String line = fileContent.get(i).replaceAll("		", "	").replaceAll("", "");
				String[] element = line.split("	");
				Member member = new Member();
				member.name = element[0].replaceAll("Name: ", "");
				member.id = element[1].replaceAll("ID: ", "");
				member.days_in_faction = Integer.parseInt(element[2].split(" ")[3]);
				member.last_action = Integer.parseInt(element[3].split(" ")[2]);
				member.status = element[4].replaceAll("Satatus: ", "");
				membersList.add(member);
			}
			MembersListDTO membersListDTO = new MembersListDTO();
			membersListDTO.isSucses = true;
			membersListDTO.membersList = membersList;
			return membersListDTO;
		} else {
			System.out.println("API connection went wrong and backup file is empty");
			MembersListDTO membersListDTO = new MembersListDTO();
			membersListDTO.isSucses = false;
			return membersListDTO;
		}
	}
}
