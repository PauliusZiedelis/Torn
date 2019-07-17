package Torn.Program.Service;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Torn.Program.Constant.Constant;

public class JFrameGymResults {
	static CRUD crud = new CRUD();
	static String date1;
	static String date2;
	static String minEnergyStr;
	static String minEnergySSLStr;
	static int minEnergy;
	static int minEnergySSL;

	public static void gymResultsInfo() {
		final JFrame gymResultsInfoWindow = new JFrame("Select Dates");
		gymResultsInfoWindow.setSize(250, 250);
		gymResultsInfoWindow.setResizable(true);
		gymResultsInfoWindow.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gymResultsInfoWindow.add(panel);

		List<String> dates = crud.read(Constant.gymInfoLocation, Constant.gymDatesFileName);
		final String[] datesString = new String[dates.size()];
		for (int i = 0; i < dates.size(); i++) {
			datesString[i] = dates.get(i);
		}

		final JComboBox date1Box = new JComboBox(datesString);
		date1Box.setSelectedIndex(datesString.length - 2);
		date1Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				date1 = date1Box.getSelectedItem().toString();

			}
		});
		panel.add(date1Box, gbc);

		final JComboBox date2Box = new JComboBox(datesString);
		date2Box.setSelectedIndex(datesString.length - 1);
		date2Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				date2 = date2Box.getSelectedItem().toString();
			}
		});
		panel.add(date2Box, gbc);
		
		JButton buttonMinEnergy = new JButton("Add min energy need");
		buttonMinEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				minEnergyStr = (JOptionPane.showInputDialog("Add min energy need", Constant.minGymRequirement));
				Pattern p = Pattern.compile("[^0-9]");
				Matcher m = p.matcher(minEnergyStr);
				if (!m.find()) {
					minEnergy = Integer.parseInt(minEnergyStr);
					if (minEnergy == 0) {
						minEnergy = 1;
					}
				}
			}
		});
		panel.add(buttonMinEnergy, gbc);

		JButton buttonMinEnergySSL = new JButton("Add min energy to SSL user");
		buttonMinEnergySSL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				minEnergySSLStr = (JOptionPane.showInputDialog("Add min energy need", Constant.minGymRequirementSSL));
				Pattern p = Pattern.compile("[^0-9]");
				Matcher m = p.matcher(minEnergySSLStr);
				if (!m.find()) {
					minEnergySSL = Integer.parseInt(minEnergySSLStr);
					if (minEnergySSL == 0) {
						minEnergySSL = 1;
					}
				}
			}
		});
		panel.add(buttonMinEnergySSL, gbc);

		JButton buttonConfirm = new JButton("Confirm");
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (date1 == null) {
					date1=datesString[datesString.length-2];
				}
				if (date2 == null) {
					date2=datesString[datesString.length-1];
				}
				if (minEnergy == 0) {
					minEnergy = Constant.minGymRequirement;
				}
				if (minEnergySSL == 0) {
					minEnergySSL = Constant.minGymRequirementSSL;
				}
				gymResults();
				gymResultsInfoWindow.dispose();
			}
		});
		panel.add(buttonConfirm, gbc);
		
		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gymResultsInfoWindow.dispose();
					JFrameGymMain back = new JFrameGymMain();
					back.mainStatsJFrame();
			}
		});
		panel.add(buttonBack, gbc);
		
		gymResultsInfoWindow.setVisible(true);
	}

	public static void gymResults() {
		final GymResults gymR=new GymResults(date1, date2, minEnergy, minEnergySSL);
		final JFrame gymResultsWindow = new JFrame("Gym Results");
		gymResultsWindow.setSize(250, 250);
		gymResultsWindow.setResizable(true);
		gymResultsWindow.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gymResultsWindow.add(panel);

		JButton buttonHTML = new JButton("Coppy html");
		buttonHTML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StringSelection stringSelection = new StringSelection(gymR.gymResultsInHtml());
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		panel.add(buttonHTML, gbc);

		JButton buttonBBCode = new JButton("Coppy BB code");
		buttonBBCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				StringSelection stringSelection = new StringSelection("");
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
			}
		});
		panel.add(buttonBBCode, gbc);
		
		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				gymResultsWindow.dispose();
				gymResultsInfo();
			}
		});
		panel.add(buttonBack, gbc);
		
		
		gymResultsWindow.setVisible(true);
	}
}
