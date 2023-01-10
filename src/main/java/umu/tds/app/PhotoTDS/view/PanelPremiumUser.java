package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.persistence.jpa.jpql.parser.DatabaseTypeFactory;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.User;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class PanelPremiumUser {

	/**
	 * 
	 */

	private JFrame frame;

	private String user;
	private JPanel panel;

	private boolean logged;

	public JFrame getFrame() {
		return this.frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public PanelPremiumUser(String user) {
		super();
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 625);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();

		Optional<User> us = Controller.getInstancia().getUser(user);

		if (us.isEmpty())
			return;

		User u = us.get();

		JLabel excel = new JLabel("Generate Excel file with your followers");

		panel.add(excel);

		JButton excelButton = new JButton("Generate Excel");
		panel.add(excelButton);

		excelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancia().goPremium(user);
			}
		});

		JLabel pdf = new JLabel("Generate Excel file with your followers");
		panel.add(pdf);

		JButton pdfButton = new JButton("Generate PDF");
		panel.add(pdfButton);

		pdfButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(null);

				if (chooser.getSelectedFile() != null) {

					String fichero = chooser.getSelectedFile().getAbsolutePath();
					Controller.getInstancia().createPdf(user, fichero);
				}
			}
		});

	}

}
