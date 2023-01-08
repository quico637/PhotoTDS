package umu.tds.app.PhotoTDS.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import javax.swing.JPasswordField;

public class PanelEdit {

	/**
	 * 
	 */

	private JFrame frame;

	private String user;
	private JPanel panel;

	private String profilePath;
	private JPasswordField passwordField;

	DescriptionWindow dw;

	public JFrame getFrame() {
		return this.frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	private ImageIcon createImageIcon(String path) {
		if (path == null) {
			System.err.println("Path is null!!!.");
			return null;
		}
		java.net.URL imgURL = getClass().getResource(path);

		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		ImageIcon img = new ImageIcon(path);
		if (img != null)
			return img;

		System.err.println("Couldn't find file: " + path);
		return null;

	}

	public PanelEdit(String user) {
		super();
		this.user = user;
		this.dw = new DescriptionWindow();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 625);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 25, 51, 30, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 25, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
		if (!userEdit.isPresent())
			System.out.println("user null");
		User useEdit = userEdit.get();
		Image imageEdit = createImageIcon(useEdit.getProfilePic()).getImage().getScaledInstance(150, 150,
				Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(imageEdit);
		profPicEdit.setIcon(icon);

		JButton btnNewButton_1 = new JButton("Change Description");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 3;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dw.showWindow(frame);
			}
		});
		
		JLabel lblNewLabel = new JLabel("New Password:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 4;
		gbc_passwordField.gridy = 2;
		panel.add(passwordField, gbc_passwordField);

		JButton changeButton = new JButton("Change Photo");
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
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 5;
		panel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(e -> {
			
			String description = dw.getDescripcion();
			String passwd = String.valueOf(passwordField.getPassword());
			
			if (description != null) {
				Controller.getInstancia().changeDescription(user, description);
			}
				
			if(passwd != null) {
				if(!Controller.getInstancia().changePassword(user, passwd)) {
					ErrorWindow ew = new ErrorWindow("Password is too short.");
					ew.showWindow(frame);
				};
			}

			if(profilePath != null) {
				Controller.getInstancia().changeProfilePicture(user, profilePath);
			}

		});

	}
}
