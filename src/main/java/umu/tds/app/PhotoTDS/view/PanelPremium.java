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

public class PanelPremium {

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
	
	private ImageIcon createImageIcon(String path) {
		if(path == null) {
			System.err.println("Path is null!!!.");
			return null;
		}
		java.net.URL imgURL = getClass().getResource(path);
		
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		ImageIcon img = new ImageIcon(path);
		if(img != null)
			return img;
			
		System.err.println("Couldn't find file: " + path);
		return null;
		
	}




	public PanelPremium(String user) {
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
		
		if(us.isEmpty())
			return;
					
		User u = us.get();
			
		JLabel prem = new JLabel("Get premium! Just for " + u.getTotalPremiumPrice() + "$");
		
		prem.setFont(new Font("Segoe Script", Font.BOLD, 16));
		panel.add(prem);
		
		JButton suscribe = new JButton("Suscribe!");
		panel.add(suscribe);
		
		suscribe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancia().goPremium(user);
			}
		});
		
	}

}
