package Torn.Program.Service;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Torn.Program.Constant.Constant;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class JFrameGymMain {
	static CRUD crud = new CRUD();
	static String selectedSSLUser;

	public static void mainStatsJFrame() {
		final JFrame mainWindow = new JFrame("Torn Gym Add");
		mainWindow.setSize(500, 250);
		mainWindow.setResizable(true);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainWindow.add(panel);

		JButton addGymInfo = new JButton("Add Gym Info");
		addGymInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameAddGymInfo gymInfo = new JFrameAddGymInfo();
				gymInfo.addStatsJFrame();
				mainWindow.dispose();
			}
		});
		panel.add(addGymInfo, gbc);

		JButton gymResult = new JButton("Gym Results");
		gymResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameGymResults gymR = new JFrameGymResults();
				gymR.gymResultsInfo();
				mainWindow.dispose();
			}
		});
		panel.add(gymResult, gbc);

		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameMain main = new JFrameMain();
				main.mainJFrame();
				mainWindow.dispose();

			}
		});
		panel.add(back, gbc);

		mainWindow.setVisible(true);

	}

}
