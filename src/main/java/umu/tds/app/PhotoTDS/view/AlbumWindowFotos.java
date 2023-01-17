package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.Album;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlbumWindowFotos {

	private JFrame frame;
	private static AlbumWindowFotos unicaInstancia = null;
	private String path;
	private JTextField textField;
	private JTextField textField_1;
	private Album album;
	
	
	private String user;

	/**
	 * Create the application.
	 */
	public AlbumWindowFotos(String u, Album album) {
		this.user = u;
		this.album = album;
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


	public String getPath() {
		return this.path;
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
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Description: ");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile());
				path = chooser.getSelectedFile().getPath();
			}
		});
		panel.add(btnNewButton);
		
		
		JButton btnNewButton_2 = new JButton("Check");
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo:");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("SendFotoToAlbum");
		btnNewButton_1.addActionListener(e -> {
			this.hideWindow();
			if(Controller.getInstancia().addNewPicture(this.user, album, textField_1.getText(), textField.getText(), path))
				System.out.println("NOOO");
		});
		panel.add(btnNewButton_1);

		
	}
}
