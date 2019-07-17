package Torn.Program.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Torn.Program.Constant.Constant;
import Torn.Program.DTO.MembersListDTO;
import Torn.Program.Model.Member;
import Torn.Program.Model.SSLUser;

public class EnergySpentInGym {
	static List<Member> memberList;
	static CRUD crud = new CRUD();
	static String date1;
	static String date2;
	static ArrayList<String> gymInfo1;
	static ArrayList<String> gymInfo2;
	static String strengh1;
	static String speed1;
	static String defense1;
	static String dexterity1;
	static String strengh2;
	static String speed2;
	static String defense2;
	static String dexterity2;

	public static List<Member> energySpent(String date1, String date2) {

		MembersListDTO membersListDTO = GetMembersList.getMembersList();
		if (membersListDTO.isSucses) {
			memberList = membersListDTO.membersList;
		}
		gymInfo1 = crud.read(Constant.gymInfoLocation, date1 + ".txt");
		gymInfo2 = crud.read(Constant.gymInfoLocation, date2 + ".txt");
		if (gymInfo1.size() == 7) {
			strengh1 = gymInfo1.get(0);
			speed1 = gymInfo1.get(2);
			defense1 = gymInfo1.get(4);
			dexterity1 = gymInfo1.get(6);
		}
		if (gymInfo2.size() == 7) {
			strengh2 = gymInfo2.get(0);
			speed2 = gymInfo2.get(2);
			defense2 = gymInfo2.get(4);
			dexterity2 = gymInfo2.get(6);
		}
		// https://www.torn.com/profiles.php?XID=143259 616600
		// (https://www.torn.com\/profiles.php\?XID=)(\d+)( )(\d+)
		Pattern memberPattern = Pattern.compile("(https://www.torn.com\\/profiles.php\\?XID=)(\\d+)(	)(\\d+)");
		Matcher mStrengh1 = memberPattern.matcher(strengh1);
		while (mStrengh1.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mStrengh1.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).strength1 = Integer.parseInt(mStrengh1.group(4));
				}
			}
		}
		Matcher mSpeed1 = memberPattern.matcher(speed1);
		while (mSpeed1.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mSpeed1.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).speed1 = Integer.parseInt(mSpeed1.group(4));
				}
			}
		}
		Matcher mDefense1 = memberPattern.matcher(defense1);
		while (mDefense1.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mDefense1.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).defense1 = Integer.parseInt(mDefense1.group(4));
				}
			}
		}
		Matcher mdexterity1 = memberPattern.matcher(dexterity1);
		while (mdexterity1.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mdexterity1.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).dexterity1 = Integer.parseInt(mdexterity1.group(4));
				}
			}
		}
		Matcher mStrengh2 = memberPattern.matcher(strengh2);
		while (mStrengh2.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mStrengh2.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).strength2 = Integer.parseInt(mStrengh2.group(4));
				}
			}
		}
		Matcher mSpeed2 = memberPattern.matcher(speed2);
		while (mSpeed2.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mSpeed2.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).speed2 = Integer.parseInt(mSpeed2.group(4));
				}
			}
		}
		Matcher mDefense2 = memberPattern.matcher(defense2);
		while (mDefense2.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mDefense2.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).defense2 = Integer.parseInt(mDefense2.group(4));
				}
			}
		}
		Matcher mdexterity2 = memberPattern.matcher(dexterity2);
		while (mdexterity2.find()) {
			for (int i = 0; i < memberList.size(); i++) {
				if (mdexterity2.group(2).equals(memberList.get(i).id)) {
					memberList.get(i).dexterity2 = Integer.parseInt(mdexterity2.group(4));
				}
			}
		}
		for (int i = 0; i < memberList.size(); i++) {
			memberList.get(i).strengthTotal = memberList.get(i).strength2 - memberList.get(i).strength1;
			memberList.get(i).speedTotal = memberList.get(i).speed2 - memberList.get(i).speed1;
			memberList.get(i).defenseTotal = memberList.get(i).defense2 - memberList.get(i).defense1;
			memberList.get(i).dexterityTotal = memberList.get(i).dexterity2 - memberList.get(i).dexterity1;
			memberList.get(i).total = memberList.get(i).strengthTotal + memberList.get(i).speedTotal
					+ memberList.get(i).defenseTotal + memberList.get(i).dexterityTotal;
		}
		for (int i = 0; i < memberList.size(); i++) {
			for (int j = i + 1; j < memberList.size(); j++) {
				if (memberList.get(i).total < memberList.get(j).total) {
					Member temporary = memberList.get(i);
					memberList.set(i, memberList.get(j));
					memberList.set(j, temporary);
				}
			}
		}
		SSLUserList sslUsers=new SSLUserList();
		ArrayList<SSLUser> sslUser=sslUsers.getSSLUsersList();
		for (int i = 0; i < memberList.size(); i++) {
			for (int j = 0; j < sslUser.size(); j++) {
				if (memberList.get(i).id.equals(sslUser.get(j).id)) {
					memberList.get(i).sslUser=true;
				}
			}
		}
		return memberList;
	}
}
