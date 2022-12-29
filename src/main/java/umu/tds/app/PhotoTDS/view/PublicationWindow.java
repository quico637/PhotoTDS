package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import java.awt.Component;

import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import umu.tds.app.PhotoTDS.controller.Controller;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicationWindow {

	private JFrame frame;
	private static PublicationWindow unicaInstancia = null;
	private String path;
	private JTextField textField;
	private JTextField textField_1;
	
	private String user;
	

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PublicationWindow window = new PublicationWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	private PublicationWindow(String u) {
		this.user = u;
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

	public static PublicationWindow getInstancia(String u) {
		if (unicaInstancia == null) {
			unicaInstancia = new PublicationWindow(u);
		}

		return unicaInstancia;
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
		btnNewButton_2.addActionListener(e -> Controller.getInstancia().getAllPublications().stream().forEach(r -> System.out.println(r)));
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo:");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.addActionListener(e -> {
			this.hideWindow();
			Controller.getInstancia().createFoto(this.user, textField_1.getText(), textField.getText(), path);
		});
		panel.add(btnNewButton_1);

		
	}
}
