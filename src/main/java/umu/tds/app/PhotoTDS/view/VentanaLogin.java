package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.filechooser.FileNameExtensionFilter;

import umu.tds.app.PhotoTDS.model.Utils;

// import controller.AppPhotoController;

import java.awt.FlowLayout;
import javax.swing.UIManager;

import java.awt.CardLayout;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import umu.tds.app.PhotoTDS.controller.Controller;

import java.awt.Font;

/**
 * 
 * @author quico y JC
 *
 */
public class VentanaLogin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtEmail;
	private JPasswordField passwordField_1;
	private JTextField txtNombreCom;
	private JTextField txtNombreDeUsuario;
	private JLabel lblNewLabel_7;
	private static VentanaLogin unicaInstancia = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private VentanaLogin() {
		initialize();
	}
	
	public void hideWindow() {
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);
	}
	
	public static VentanaLogin getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new VentanaLogin();
		}

		return unicaInstancia;
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
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/instagram.png")));
		frame.setBounds(200, 200, 655, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(5, 5));

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel photoApp = new JLabel("PhotoApp");
		photoApp.setFont(new Font("Segoe Script", Font.PLAIN, 32));
		photoApp.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/instagram.png")));
		panel.add(photoApp);

		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panelBottom, BorderLayout.SOUTH);

		JPanel panelCenterCardLayout = new JPanel();
		frame.getContentPane().add(panelCenterCardLayout, BorderLayout.CENTER);
		panelCenterCardLayout.setLayout(new CardLayout(0, 0));

		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegister.setIcon(
						new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/user-3.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegister.setIcon(
						new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/user-2.png")));
			}
		});
		
		JLabel lblNewLabel_8 = new JLabel("Not registered?");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		panelBottom.add(lblNewLabel_8);
		btnRegister.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/user-2.png")));
		panelBottom.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			boolean login = true;
			public void actionPerformed(ActionEvent e) {
				if (login == true) {
					CardLayout cl = (CardLayout) (panelCenterCardLayout.getLayout());
					cl.show(panelCenterCardLayout, "panelRegister");
					btnRegister.setText("Login");
					lblNewLabel_8.setText("Do you have an account?");
					login = false;
				} else {
					CardLayout cl = (CardLayout) (panelCenterCardLayout.getLayout());
					cl.show(panelCenterCardLayout, "panelLogin");
					btnRegister.setText("Register");
					lblNewLabel_8.setText("Not registered?");
					login = true;
				}
			}
		});

		JPanel panelLogin = new JPanel();
		panelCenterCardLayout.add(panelLogin, "panelLogin");
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[] { 80, 15, 0, 207, 0, 120, 0 };
		gbl_panelLogin.rowHeights = new int[] { 50, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelLogin.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelLogin.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelLogin.setLayout(gbl_panelLogin);

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelLogin.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panelLogin.add(textField, gbc_textField);

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panelLogin.add(lblNewLabel_2, gbc_lblNewLabel_2);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		panelLogin.add(passwordField, gbc_passwordField);

		JButton botonLogin = new JButton("Login");
		botonLogin.addActionListener(e -> {
			if(Controller.getInstancia().login(textField.getText(), passwordField.getText()) == true) {
				VentanaInicio.getInstancia().showWindow();
				this.hideWindow();
			}
			else {
				System.out.println("moscas");
			}
		});
		// btnNewButton_1.addActionListener(event ->
		// AppPhotoController.getInstancia().login());
		botonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				botonLogin.setIcon(
						new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				botonLogin.setIcon(
						new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter-2.png")));
			}

		});
		botonLogin.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter-2.png")));
		GridBagConstraints gbc_botonLogin = new GridBagConstraints();
		gbc_botonLogin.gridwidth = 2;
		gbc_botonLogin.insets = new Insets(0, 0, 5, 5);
		gbc_botonLogin.gridx = 2;
		gbc_botonLogin.gridy = 3;
		panelLogin.add(botonLogin, gbc_botonLogin);
//		
//		
//		textField_1 = new JTextField();
//		textField_1.setColumns(10);
//		
//		textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		
//		JButton btnNewButton_2 = new JButton("send");
//		btnNewButton_2.addActionListener(e -> Controller.getInstancia().createUser(textField_1.getText(), textField_2.getText()));
//		
//		JButton btnNewButton_2_1 = new JButton("update");
//		btnNewButton_2_1.addActionListener(e -> Controller.getInstancia().getAllusers());
//		GridBagConstraints gbc_btnNewButton_2_1 = new GridBagConstraints();
//		gbc_btnNewButton_2_1.insets = new Insets(0, 0, 5, 5);
//		gbc_btnNewButton_2_1.gridx = 1;
//		gbc_btnNewButton_2_1.gridy = 4;
//		panelLogin.add(btnNewButton_2_1, gbc_btnNewButton_2_1);
//		
//		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
//		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
//		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_2.gridx = 3;
//		gbc_textField_2.gridy = 4;
//		panelLogin.add(textField_2, gbc_textField_2);
//		
//
//		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
//		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
//		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_1.gridx = 3;
//		gbc_textField_1.gridy = 5;
//		panelLogin.add(textField_1, gbc_textField_1);
//		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
//		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
//		gbc_btnNewButton_2.gridx = 3;
//		gbc_btnNewButton_2.gridy = 6;
//		panelLogin.add(btnNewButton_2, gbc_btnNewButton_2);

		JPanel panelRegister = new JPanel();
		panelRegister.setForeground(new Color(255, 255, 255));
		panelCenterCardLayout.add(panelRegister, "panelRegister");
		GridBagLayout gbl_panelRegister = new GridBagLayout();
		gbl_panelRegister.columnWidths = new int[] { 70, 15, 0, 80, 0 };
		gbl_panelRegister.rowHeights = new int[] { 50, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelRegister.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelRegister.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelRegister.setLayout(gbl_panelRegister);

		JLabel lblNewLabel = new JLabel("e-mail:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panelRegister.add(lblNewLabel, gbc_lblNewLabel);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 1;
		panelRegister.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 2;
		panelRegister.add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtNombreCom = new JTextField();
		GridBagConstraints gbc_txtNombreCom = new GridBagConstraints();
		gbc_txtNombreCom.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreCom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreCom.gridx = 2;
		gbc_txtNombreCom.gridy = 2;
		panelRegister.add(txtNombreCom, gbc_txtNombreCom);
		txtNombreCom.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Username:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 3;
		panelRegister.add(lblNewLabel_4, gbc_lblNewLabel_4);

		txtNombreDeUsuario = new JTextField();
		GridBagConstraints gbc_txtNombreDeUsuario = new GridBagConstraints();
		gbc_txtNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreDeUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreDeUsuario.gridx = 2;
		gbc_txtNombreDeUsuario.gridy = 3;
		panelRegister.add(txtNombreDeUsuario, gbc_txtNombreDeUsuario);
		txtNombreDeUsuario.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Password:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 4;
		panelRegister.add(lblNewLabel_5, gbc_lblNewLabel_5);

		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("Contraseña");
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.gridx = 2;
		gbc_passwordField_1.gridy = 4;
		panelRegister.add(passwordField_1, gbc_passwordField_1);
		
		JDateChooser calendar = new JDateChooser();
		IDateEditor dateEditor = calendar.getDateEditor();
		JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dateEditor;
		txtFld.addPropertyChangeListener(e -> txtFld.setForeground(Color.WHITE));
		calendar.getCalendarButton().setForeground(new Color(0, 0, 0));
		JLabel calendarLabel = new JLabel("Date:");
		calendarLabel.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_calendarLabel = new GridBagConstraints();
		gbc_calendarLabel.anchor = GridBagConstraints.EAST;
		gbc_calendarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_calendarLabel.gridx = 1;
		gbc_calendarLabel.gridy = 5;
		panelRegister.add(calendarLabel, gbc_calendarLabel);
		calendar.getCalendarButton()
				.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/calendario.png")));
		calendar.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.anchor = GridBagConstraints.NORTH;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.HORIZONTAL;
		gbc_calendar.gridx = 2;
		gbc_calendar.gridy = 5;
		panelRegister.add(calendar, gbc_calendar);

		JLabel lblNewLabel_6 = new JLabel("Description:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 6;
		panelRegister.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DescriptionWindow.getInstancia().showWindow();
			}
		});
		
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		panelRegister.add(btnNewButton, gbc_btnNewButton);

		JButton btnSubmitReg = new JButton("Register");
		btnSubmitReg.addActionListener(e -> {
			Controller.getInstancia().createUser(txtNombreDeUsuario.getText(), txtEmail.getText(), txtNombreCom.getText(), Utils.StringToDate(txtFld.getText()) , DescriptionWindow.getInstancia().getDescripcion() , passwordField_1.getText());
		});
		btnSubmitReg.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter-2.png")));
		btnSubmitReg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSubmitReg.setIcon(
						new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSubmitReg.setIcon(
						new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter-2.png")));
			}

		});

		lblNewLabel_7 = new JLabel("Profile Picture:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 7;
		panelRegister.add(lblNewLabel_7, gbc_lblNewLabel_7);

		JButton btnNewButton_1 = new JButton("Select");
		btnNewButton_1.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 7;
		panelRegister.add(btnNewButton_1, gbc_btnNewButton_1);
		btnSubmitReg.setActionCommand("Register");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 8;
		panelRegister.add(btnSubmitReg, gbc_btnLogin);
	}

}
