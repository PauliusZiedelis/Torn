package Torn.Program.Service;

import java.awt.Color;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JFrameAddGymInfo {
	static String strength = "";
	static String speed = "";
	static String defense = "";
	static String dexterity = "";

	public static void addStatsJFrame() {
		final JFrame gymWindow = new JFrame("Torn Gym Add");
		gymWindow.setSize(350, 250);
		gymWindow.setResizable(true);
		gymWindow.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		// panel.setBackground(Color.darkGray);
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gymWindow.add(panel);

		JButton buttonStrength = new JButton("Add Strength");
		buttonStrength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				strength = (JOptionPane.showInputDialog("Add energy spend on Strength", "Add info"));

			}
		});
		panel.add(buttonStrength, gbc);

		JButton buttonSpeed = new JButton("Add Speed");
		buttonSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				speed = (JOptionPane.showInputDialog("Add energy spend on Speed", "Add info"));
			}
		});
		panel.add(buttonSpeed, gbc);

		JButton buttonDefense = new JButton("Add Defense");
		buttonDefense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				defense = (JOptionPane.showInputDialog("Add energy spend on Defense", "Add info"));
			}
		});
		panel.add(buttonDefense, gbc);

		JButton buttonDexterity = new JButton("Add Dexterity");
		buttonDexterity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dexterity = (JOptionPane.showInputDialog("Add energy spend on Dexterity", "Add info"));
			}
		});
		panel.add(buttonDexterity, gbc);

		JButton buttonConfirm = new JButton("Confirm");
		buttonConfirm.setForeground(Color.BLUE);
		buttonConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (!strength.isEmpty() && !speed.isEmpty() && !defense.isEmpty() && !dexterity.isEmpty()) {
					AddGymInfo addInfo = new AddGymInfo();
					addInfo.addGymInfo(strength, speed, defense, dexterity);
					System.out.println("Gym info added");
					gymWindow.dispose();
					JFrameGymMain back = new JFrameGymMain();
					back.mainStatsJFrame();
				} else {
					JOptionPane.showMessageDialog(null, "Not all b. Stats added", "Warning",
							JOptionPane.WARNING_MESSAGE);
					System.out.println("something wrong");
				}
			}
		});
		panel.add(buttonConfirm, gbc);
		
		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					gymWindow.dispose();
					JFrameGymMain back = new JFrameGymMain();
					back.mainStatsJFrame();
			}
		});
		panel.add(buttonBack, gbc);

		gymWindow.setVisible(true);

	}

}
