package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.TextArea;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import javax.swing.JList;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ErrorWindow {

	private JFrame frame;
	private String msg;

	/**
	 * Create the application.
	 */
	public ErrorWindow(String msg) {
		this.msg = msg;
		initialize();
	}

	public void showWindow(Component c) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(c);
	}

	public void hideWindow() {
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);
	}

	// GETTERS AND SETTERS

	public String getMsg() {
		return msg;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme");
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 200);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("ERROR!");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblNewLabel);

		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hideWindow();
			}
		});
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 106, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel(this.msg);
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 0;

	}
	


}
