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
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPerfil {

	/**
	 * 
	 */
	private final static int PROFILE_PIC_SIZE = 40;
	private final static int X = 150;
	private final static int Y = 150;
	private JFrame frame;
		
	private String user;
	private String profilePath;
	private JPanel panelPerfil;
	
	private String userLogged;

	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JPanel getPanelPerfil() {
		return panelPerfil;
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




	public PanelPerfil(String user, String userLogged) {
		super();
		this.user = user;
		this.userLogged = userLogged;
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
		
		panelPerfil = new JPanel();
		GridBagLayout gbl_panelPerfil = new GridBagLayout();
		gbl_panelPerfil.columnWidths = new int[]{50, 51, 0, 0, 55, 65, 50, 0};
		gbl_panelPerfil.rowHeights = new int[]{50, 23, 0, 0, 0, 0};
		gbl_panelPerfil.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPerfil.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPerfil.setLayout(gbl_panelPerfil);
		
		JLabel profPic = new JLabel();
		GridBagConstraints gbc_profPic = new GridBagConstraints();
		gbc_profPic.gridheight = 2;
		gbc_profPic.fill = GridBagConstraints.HORIZONTAL;
		gbc_profPic.insets = new Insets(0, 0, 5, 5);
		gbc_profPic.gridx = 1;
		gbc_profPic.gridy = 1;
		panelPerfil.add(profPic, gbc_profPic);
		profPic.setFont(new Font("Segoe Script", Font.BOLD, 16));
		profPic.setBounds(0, 0, PROFILE_PIC_SIZE, PROFILE_PIC_SIZE);
		Optional<User> us = Controller.getInstancia().getUser(user);
		User use = us.get();
		Image img = createImageIcon(use.getProfilePic()).getImage().getScaledInstance(X, Y, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		profPic.setIcon(icon);
		
		
		JLabel userName = new JLabel(user);
		GridBagConstraints gbc_userName = new GridBagConstraints();
		gbc_userName.fill = GridBagConstraints.HORIZONTAL;
		gbc_userName.insets = new Insets(0, 0, 5, 5);
		gbc_userName.gridx = 3;
		gbc_userName.gridy = 1;
		panelPerfil.add(userName, gbc_userName);
		
		JButton editButton = new JButton("Edit Profile");
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 5;
		gbc_editButton.gridy = 1;
		panelPerfil.add(editButton, gbc_editButton);
		
		editButton.addActionListener(e -> {
			CardLayout cl = (CardLayout) VentanaInicio.getPanelCentralCardLayout().getLayout();
			cl.show(VentanaInicio.getPanelCentralCardLayout(), "panelEdit");
		});
		

		JButton logout = new JButton("Logout");
		GridBagConstraints gbc_logout = new GridBagConstraints();
		gbc_logout.insets = new Insets(0, 0, 0, 5);
		gbc_logout.anchor = GridBagConstraints.NORTHWEST;
		gbc_logout.gridx = 5;
		gbc_logout.gridy = 4;
		
		if(user.equals(userLogged))
			panelPerfil.add(logout, gbc_logout);
		
//		JPanel panelEdit = new JPanel();
//		GridBagLayout gbl_panel = new GridBagLayout();
//		gbl_panel.columnWidths = new int[] { 25, 51, 0, 0, 55, 65, 50, 0 };
//		gbl_panel.rowHeights = new int[] { 25, 23, 0, 0, 0, 0 };
//		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
//		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
//		panelEdit.setLayout(gbl_panel);
//
//		JLabel profPicEdit = new JLabel();
//		GridBagConstraints gbc_profPic_Edit = new GridBagConstraints();
//		gbc_profPic_Edit.gridheight = 2;
//		gbc_profPic_Edit.fill = GridBagConstraints.HORIZONTAL;
//		gbc_profPic_Edit.insets = new Insets(0, 0, 5, 5);
//		gbc_profPic_Edit.gridx = 1;
//		gbc_profPic_Edit.gridy = 1;
//		panelEdit.add(profPicEdit, gbc_profPic_Edit);
//		profPic.setBounds(0, 0, 40, 40);
//		Optional<User> usEdit = Controller.getInstancia().getUser(user);
//		if (!usEdit.isPresent())
//			System.out.println("user null");
//		User useEdit = usEdit.get();
//		Image imgEdit = createImageIcon(useEdit.getProfilePic()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
//		ImageIcon iconEdit = new ImageIcon(imgEdit);
//		profPicEdit.setIcon(iconEdit);
//
//		JLabel userNameEdit = new JLabel(user);
//		GridBagConstraints gbc_userName_Edit = new GridBagConstraints();
//		gbc_userName_Edit.fill = GridBagConstraints.HORIZONTAL;
//		gbc_userName_Edit.insets = new Insets(0, 0, 5, 5);
//		gbc_userName_Edit.gridx = 2;
//		gbc_userName_Edit.gridy = 1;
//		panelEdit.add(userNameEdit, gbc_userName_Edit);
//
//		JButton changeButton = new JButton("Change");
//		GridBagConstraints gbc_changeButton = new GridBagConstraints();
//		gbc_changeButton.insets = new Insets(0, 0, 5, 5);
//		gbc_changeButton.gridx = 1;
//		gbc_changeButton.gridy = 3;
//		panelEdit.add(changeButton, gbc_changeButton);
//		changeButton.addActionListener(e -> {
//		JFileChooser chooser = new JFileChooser();
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
//		chooser.setFileFilter(filter);
//		int returnVal = chooser.showOpenDialog(null);
//		if (returnVal == JFileChooser.APPROVE_OPTION) {
//			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
//			profilePath = chooser.getSelectedFile().getPath();
//			Controller.getInstancia().changeProfilePicture(user, profilePath);
//		}
//	});
//		
//		JButton btnNewButton = new JButton("Save Changes");
//		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
//		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
//		gbc_btnNewButton.gridx = 1;
//		gbc_btnNewButton.gridy = 4;
//		panelEdit.add(btnNewButton, gbc_btnNewButton);
		
//		JButton createPdf = new JButton("PDF");
//		createPdf.addActionListener(e -> {
//			
//			JFileChooser chooser = new JFileChooser();
//			chooser.showSaveDialog(null);
//
//			if (chooser.getSelectedFile() != null) {
//
//				String fichero = chooser.getSelectedFile().getAbsolutePath();
//				Controller.getInstancia().createPdf(user, fichero);
//
//			}
//		});
//		panelPerfil.add(createPdf);
		
		
		
		logout.addActionListener(e -> {
			if(!Controller.getInstancia().logout(user)) System.out.println("Cagaste en LOGOUT");
			VentanaInicio.hideWindow();
			VentanaLogin.getInstancia().showWindow();
		});
		
	}

}
