package Torn.Program.Service;

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

public class JFrameSSLUser {
	static CRUD crud = new CRUD();
	static String selectedSSLUser;

	public static void sslUserJFrame() {
		final JFrame mainWindow = new JFrame("SSL users panel");
		mainWindow.setSize(500, 250);
		mainWindow.setResizable(true);
		mainWindow.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainWindow.add(panel);

		JButton addSSLUser = new JButton("Add SSL user");
		addSSLUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sslUser = (JOptionPane.showInputDialog("Add SSL user", "name [id]"));
				SSLUserList addSSLUser = new SSLUserList();
				addSSLUser.addSSLUser(sslUser);
			}
		});
		panel.add(addSSLUser, gbc);

		JButton removeSSLUser = new JButton("Remove SSL user");
		removeSSLUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeJframe();
			}
		});
		panel.add(removeSSLUser, gbc);

		final JComboBox sslUsersBox = new JComboBox(sslUserList());
		sslUsersBox.setSelectedIndex(0);
		sslUsersBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				selectedSSLUser = sslUsersBox.getSelectedItem().toString();

			}
		});
		sslUsersBox.setVisible(false);
		panel.add(sslUsersBox, gbc);

		final JButton buttonShow = new JButton("Show SSL users list");
		buttonShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (sslUsersBox.isVisible() == false) {
					sslUsersBox.setVisible(true);
					buttonShow.setText("Hide SSL users list");
				}
				else {
					sslUsersBox.setVisible(false);
					buttonShow.setText("Show SSL users list");
				}
			}
		});
		panel.add(buttonShow, gbc);

		JButton buttonBack = new JButton("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrameMain main = new JFrameMain();
				main.mainJFrame();
				mainWindow.dispose();
			}
		});
		panel.add(buttonBack, gbc);

		mainWindow.setVisible(true);

	}

	public static String[] sslUserList() {
		List<String> sslUsersL = crud.read(Constant.sslListFileLocation, Constant.sslUsersListFileName);
		String[] sslUsers = new String[sslUsersL.size()];
		for (int i = 0; i < sslUsersL.size(); i++) {
			sslUsers[i] = sslUsersL.get(i);
		}
		return sslUsers;
	}

	public static void removeJframe() {
		final JFrame removeSSLUserWindow = new JFrame("Remove SSL User");
		removeSSLUserWindow.setSize(500, 250);
		removeSSLUserWindow.setResizable(true);
		removeSSLUserWindow.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		removeSSLUserWindow.add(panel);

		final JComboBox sslUsersBox = new JComboBox(sslUserList());
		sslUsersBox.setSelectedIndex(0);
		sslUsersBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				selectedSSLUser = sslUsersBox.getSelectedItem().toString();

			}
		});
		panel.add(sslUsersBox, gbc);

		// Confirm button if ssl user selected removes user and close removeJframe
		JButton removeSSLUserConfirm = new JButton("Confirm");
		removeSSLUserConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedSSLUser != null) {
					System.out.println(selectedSSLUser);
					SSLUserList remove = new SSLUserList();
					remove.removeSSLUser(selectedSSLUser);
					selectedSSLUser = null;
					removeSSLUserWindow.dispose();
				}
			}
		});
		panel.add(removeSSLUserConfirm, gbc);

		// Cancel button close removeJframe
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeSSLUserWindow.dispose();
			}
		});

		panel.add(cancel, gbc);

		removeSSLUserWindow.setVisible(true);
	}

}
