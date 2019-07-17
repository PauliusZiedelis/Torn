package Torn.Program.Service;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Torn.Program.Constant.Constant;
import Torn.Program.Model.Member;

public class JFrameVacation {
	static String date1;
	static String date2;
	static String selectedMember;

	public static void vacation() {
		final JFrame vacationWindow = new JFrame("Vacation Panel");
		vacationWindow.setSize(500, 250);
		vacationWindow.setResizable(true);
		vacationWindow.setLocationRelativeTo(null);
		vacationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		vacationWindow.add(panel);

		JLabel labelUser = new JLabel("Select User:");
		panel.add(labelUser, gbc);
		final JComboBox membersListBox = new JComboBox(getMembersList());
		membersListBox.setSelectedIndex(0);
		membersListBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				selectedMember = membersListBox.getSelectedItem().toString();

			}
		});
		panel.add(membersListBox, gbc);

		JLabel label = new JLabel("Select Dates:");
		panel.add(label, gbc);
		final JTextField text = new JTextField(30);
		JButton bFrom = new JButton("Select Date from");
		vacationWindow.getContentPane().add(panel);
		bFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				date1 = new DatePicker(vacationWindow).setPickedDate();
				System.out.println(date1);
				text.setText(selectedMember + " " + date1 + " - " + date2);
			}
		});
		panel.add(bFrom, gbc);
		JButton bTo = new JButton("Select Date to");

		vacationWindow.getContentPane().add(panel);
		bTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				date2 = new DatePicker(vacationWindow).setPickedDate();
				System.out.println(date2);
				text.setText(selectedMember + " " + date1 + " - " + date2);
			}
		});
		panel.add(bTo, gbc);
		JLabel label2 = new JLabel("Selected Dates:");
		panel.add(label2, gbc);
		panel.add(text, gbc);

		JButton confirmB = new JButton("Confirm");
		confirmB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedMember != null && date1 != null && date2 != null) {
					Vacation vacation = new Vacation();
					vacation.addToVacationList(selectedMember, date1, date2);
				}
			}
		});
		panel.add(confirmB, gbc);
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameMain main = new JFrameMain();
				main.mainJFrame();
				vacationWindow.dispose();

			}
		});
		panel.add(back, gbc);
		vacationWindow.setVisible(true);
	}

	public static String[] getMembersList() {
		List<Member> membersList = GetMembersList.getMembersList().membersList;

		String[] membersListString = new String[membersList.size()];
		for (int i = 0; i < membersList.size(); i++) {
			membersListString[i] = membersList.get(i).name + " [" + membersList.get(i).id + "]";
		}
		return membersListString;
	}
}
