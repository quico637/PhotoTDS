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

public class PanelEdit {

	/**
	 * 
	 */
	
	private JFrame frame;
		
	private String user;
	private JPanel panel;
	
	private String profilePath;

	
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




	public PanelEdit(String user) {
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{25, 51, 0, 0, 55, 65, 50, 0};
		gridBagLayout.rowHeights = new int[]{25, 23, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel = new JPanel();
		panel.setLayout(gridBagLayout);
		
		JLabel profPicEdit = new JLabel();
		GridBagConstraints gbc_profPicEdit = new GridBagConstraints();
		gbc_profPicEdit.gridheight = 2;
		gbc_profPicEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_profPicEdit.insets = new Insets(0, 0, 5, 5);
		gbc_profPicEdit.gridx = 1;
		gbc_profPicEdit.gridy = 1;
		panel.add(profPicEdit, gbc_profPicEdit);
		profPicEdit.setBounds(0, 0, 40, 40);
		Optional<User> userEdit = Controller.getInstancia().getUser(user);
		if(!userEdit.isPresent())
			System.out.println("user null");
		User useEdit = userEdit.get();
		Image imageEdit = createImageIcon(useEdit.getProfilePic()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(imageEdit);
		profPicEdit.setIcon(icon);

		JLabel userNameEdit = new JLabel(user);
		GridBagConstraints gbc_userName_Edit = new GridBagConstraints();
		gbc_userName_Edit.fill = GridBagConstraints.HORIZONTAL;
		gbc_userName_Edit.insets = new Insets(0, 0, 5, 5);
		gbc_userName_Edit.gridx = 2;
		gbc_userName_Edit.gridy = 1;
		panel.add(userNameEdit, gbc_userName_Edit);

		JButton changeButton = new JButton("Change");
		GridBagConstraints gbc_changeButton = new GridBagConstraints();
		gbc_changeButton.insets = new Insets(0, 0, 5, 5);
		gbc_changeButton.gridx = 1;
		gbc_changeButton.gridy = 3;
		panel.add(changeButton, gbc_changeButton);
		changeButton.addActionListener(e -> {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile());
			profilePath = chooser.getSelectedFile().getPath();
			Controller.getInstancia().changeProfilePicture(user, profilePath);
		}
	});
		
		JButton btnNewButton = new JButton("Save Changes");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
		
	}

}
