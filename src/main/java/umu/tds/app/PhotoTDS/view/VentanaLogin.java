package umu.tds.app.PhotoTDS.view;


import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

// import controller.AppPhotoController;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DropMode;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class VentanaLogin {
	

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtEmail;
	private JPasswordField passwordField_1;
	private JTextField txtNombreCom;
	private JTextField txtNombreDeUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin window = new VentanaLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		    UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme");
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/picture.png")));
		frame.setBounds(100, 100, 516, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(5, 5));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel photoApp = new JLabel("PhotoApp");
		photoApp.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/instagram.png")));
		panel.add(photoApp);
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBorder(null);
		frame.getContentPane().add(panelBottom, BorderLayout.SOUTH);
		
	
	
		
		JPanel panelCenterCardLayout = new JPanel();
		frame.getContentPane().add(panelCenterCardLayout, BorderLayout.CENTER);
		panelCenterCardLayout.setLayout(new CardLayout(0, 0));
		
		
		JPanel panelLogin = new JPanel();
		panelCenterCardLayout.add(panelLogin, "panelLogin");
		GridBagLayout gbl_panelLogin = new GridBagLayout();
		gbl_panelLogin.columnWidths = new int[]{15, 0, 207, 0, 15, 0};
		gbl_panelLogin.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelLogin.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelLogin.setLayout(gbl_panelLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelLogin.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panelLogin.add(textField, gbc_textField);
		
		JLabel lblNewLabel_2 = new JLabel("Clave:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panelLogin.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		panelLogin.add(passwordField, gbc_passwordField);
		
		JButton btnNewButton_1 = new JButton("Login");
		// btnNewButton_1.addActionListener(event -> AppPhotoController.getInstancia().login());
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter.png")));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter-2.png")));
			}
			
		});
		btnNewButton_1.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/app/PhotoTDS/images/enter-2.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 2;
		panelLogin.add(btnNewButton_1, gbc_btnNewButton_1);
		
				
				
				JButton btnRegister = new JButton("Register");
				GridBagConstraints gbc_btnRegister = new GridBagConstraints();
				gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
				gbc_btnRegister.gridx = 2;
				gbc_btnRegister.gridy = 6;
				panelLogin.add(btnRegister, gbc_btnRegister);
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CardLayout cl = (CardLayout) (panelCenterCardLayout.getLayout());
						cl.show(panelCenterCardLayout, "panelRegister");
					}
				});
		
		JPanel panelRegister = new JPanel();
		panelCenterCardLayout.add(panelRegister, "panelRegister");
		GridBagLayout gbl_panelRegister = new GridBagLayout();
		gbl_panelRegister.columnWidths = new int[]{15, 0, 0, 0, 15, 0};
		gbl_panelRegister.rowHeights = new int[]{50, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelRegister.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelRegister.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelRegister.setLayout(gbl_panelRegister);
		
		JLabel lblNewLabel = new JLabel("Email:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
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
		
		JLabel lblNewLabel_3 = new JLabel("Nombre Completo:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
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
		
		JLabel lblNewLabel_4 = new JLabel("Nombre de usuario:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
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
		
		JLabel lblNewLabel_5 = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
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
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) (panelCenterCardLayout.getLayout());
				cl.show(panelCenterCardLayout, "panelLogin");
			}
		});
		
		JLabel calendarLabel = new JLabel("Fecha:");
		GridBagConstraints gbc_calendarLabel = new GridBagConstraints();
		gbc_calendarLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_calendarLabel.insets = new Insets(0, 0, 5, 5);
		gbc_calendarLabel.gridx = 1;
		gbc_calendarLabel.gridy = 5;
		panelRegister.add(calendarLabel, gbc_calendarLabel);
		
		JDateChooser calendar = new JDateChooser();
		calendar.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.anchor = GridBagConstraints.NORTH;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.HORIZONTAL;
		gbc_calendar.gridx = 2;
		gbc_calendar.gridy = 5;
		panelRegister.add(calendar, gbc_calendar);
		btnLogin.setActionCommand("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 2;
		gbc_btnLogin.gridy = 6;
		panelRegister.add(btnLogin, gbc_btnLogin);
	}

}