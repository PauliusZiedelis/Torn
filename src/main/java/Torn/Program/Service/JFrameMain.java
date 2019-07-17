package Torn.Program.Service;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

import javafx.scene.control.DatePicker;

public class JFrameMain {
	public static void mainJFrame() {
		final JFrame mainWindow = new JFrame("Torn Program");
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

		JButton gymJFrame = new JButton("Gym panel");
		gymJFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameGymMain gym = new JFrameGymMain();
				gym.mainStatsJFrame();
				mainWindow.dispose();
			}
		});
		panel.add(gymJFrame, gbc);

		JButton sSLUser = new JButton("SSL user panel");
		sSLUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameSSLUser sslJFrame = new JFrameSSLUser();
				sslJFrame.sslUserJFrame();
				mainWindow.dispose();
			}
		});
		panel.add(sSLUser, gbc);

		JButton vacationB = new JButton("Vacation panel");
		vacationB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameVacation vacation = new JFrameVacation();
				vacation.vacation();
				mainWindow.dispose();
			}
		});
		panel.add(vacationB, gbc);

		mainWindow.setVisible(true);

	}

}
