package Torn.Program.Service;

import java.util.List;

import Torn.Program.Model.Member;

public class GymResults {
	static List<Member> membersList;
	static int _minEnergy;
	static int _minEnergySSL;
	static int warningPoints = 30;

	public GymResults(String date1, String date2, int minEnergy, int minEnergySSL) {
		EnergySpentInGym gymResults = new EnergySpentInGym();
		membersList = gymResults.energySpent(date1, date2);
		_minEnergy = minEnergy;
		_minEnergySSL = minEnergySSL;
	}

	public static String gymResultsInHtml() {
		String TableBegining = "<table>\r\n<tbody>\r\n";
		String TableEnd = "</table>\r\n</tbody>\r\n";
		String rowBegining = "<tr>\r\n";
		String rowEnd = "</tr>\r\n";
		String columB = "<td>";
		String columE = "</td>\r\n";
		String boldBegining = "<strong>";
		String boldEnd = "</strong>";
		String linkBegining = "<a href=\"https://www.torn.com/profiles.php?XID=";
		String linkMidle = "#/\" target=\"_blank\" rel=\"noreferrer\">";
		String linkEnd = "</a></td>";
		int strengthTotal = 0;
		int speedTotal = 0;
		int defenseTotal = 0;
		int dexterityTotal = 0;

		StringBuilder str = new StringBuilder();
		str.append(TableBegining);
		str.append(rowBegining);
		str.append(columB);
		str.append(boldBegining);
		str.append("Place");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append("Name");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append("ID");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Strength ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Speed ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Defense ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Dexterity ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Total ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" SSL? ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Warning Points ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" Comments ");
		str.append(boldEnd);
		str.append(columE);
		str.append(rowEnd);

		for (int i = 0; i < membersList.size(); i++) {
			strengthTotal += membersList.get(i).strengthTotal;
			speedTotal += membersList.get(i).speedTotal;
			defenseTotal += membersList.get(i).defenseTotal;
			dexterityTotal += membersList.get(i).dexterityTotal;

			str.append(rowBegining);
			str.append(columB);
			str.append(i + 1);
			str.append(columE);
			str.append(columB);
			str.append(linkBegining + membersList.get(i).id + linkMidle + membersList.get(i).name + linkEnd);
			str.append(columE);
			str.append(columB);
			str.append(membersList.get(i).id);
			str.append(columE);
			str.append(columB);
			str.append(membersList.get(i).strengthTotal);
			str.append(columE);
			str.append(columB);
			str.append(membersList.get(i).speedTotal);
			str.append(columE);
			str.append(columB);
			str.append(membersList.get(i).defenseTotal);
			str.append(columE);
			str.append(columB);
			str.append(membersList.get(i).dexterityTotal);
			str.append(columE);
			str.append(columB);
			str.append(membersList.get(i).total);
			str.append(columE);
			str.append(columB);
			if (membersList.get(i).sslUser == true) {
				str.append("SSL user");
			}
			if (membersList.get(i).sslUser == false) {
				str.append(" ");
			}
			str.append(columE);

			str.append(columB);
			if (membersList.get(i).total > 0) {
				if (membersList.get(i).sslUser == true) {
					int e = _minEnergySSL - membersList.get(i).total;
					if (e > 0) {
						double wP = ((e * 1.0) / _minEnergySSL) * warningPoints;
						str.append(wP);
					}
				}
				if (membersList.get(i).sslUser == false) {
					int e = _minEnergy - membersList.get(i).total;
					if (e > 0) {
						double wP = ((e * 1.0) / _minEnergy) * warningPoints;
						str.append(wP);
					}
				}
			}
			if (membersList.get(i).total == 0) {
				str.append("30");
			}
			str.append(columE);
			str.append(columB);
			str.append("");
			str.append(columE);
			str.append(rowEnd);

		}

		int total = strengthTotal + speedTotal + defenseTotal + dexterityTotal;

		str.append(TableBegining);
		str.append(rowBegining);
		str.append(columB);
		str.append(boldBegining);
		str.append(" ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(" ");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(strengthTotal);
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(speedTotal);
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(defenseTotal);
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(dexterityTotal);
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append(total);
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append("");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append("");
		str.append(boldEnd);
		str.append(columE);
		str.append(columB);
		str.append(boldBegining);
		str.append("");
		str.append(boldEnd);
		str.append(columE);
		str.append(rowEnd);
		str.append(TableEnd);
		return str.toString();
	}

	public static void gymResultsInBB() {

	}

}
