package ventanas;

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
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

public class VentanaLogin {
	
	// estoy en login-window

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JPasswordField passwordField_1;

	// prueba para ver si se cambia de rama
	
	//Al yotaeisei no le piden pass
	
	
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
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/imagenes/picture.png")));
		frame.setBounds(100, 100, 516, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(5, 5));
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel photoApp = new JLabel("PhotoApp");
		photoApp.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/instagram.png")));
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
		gbl_panelLogin.columnWidths = new int[]{15, 0, 0, 0, 15, 0};
		gbl_panelLogin.rowHeights = new int[]{50, 0, 0, 0, 0, 0};
		gbl_panelLogin.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
	
		
		JLabel textRegistro = new JLabel("¿No estás registrado?");
		GridBagConstraints gbc_textRegistro = new GridBagConstraints();
		gbc_textRegistro.anchor = GridBagConstraints.NORTH;
		gbc_textRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_textRegistro.gridx = 3;
		gbc_textRegistro.gridy = 3;
		panelLogin.add(textRegistro, gbc_textRegistro);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("PhotoTDS");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registro");
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Salir");
		mntmNewMenuItem_1.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/salida-de-emergencia.png")));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JButton btnNewButton_1 = new JButton("Generar clave");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 2;
		panelLogin.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panelRegister = new JPanel();
		panelCenterCardLayout.add(panelRegister, "panelRegister");
		GridBagLayout gbl_panelRegister = new GridBagLayout();
		gbl_panelRegister.columnWidths = new int[]{15, 0, 0, 0, 15, 0};
		gbl_panelRegister.rowHeights = new int[]{50, 0, 0, 0, 0, 0};
		gbl_panelRegister.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelRegister.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelRegister.setLayout(gbl_panelRegister);
		
		JLabel lblNewLabel_1_1 = new JLabel("Registerrrr");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 1;
		panelRegister.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panelRegister.add(textField_1, gbc_textField_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Clave:");
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2_1.gridx = 1;
		gbc_lblNewLabel_2_1.gridy = 2;
		panelRegister.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
		
		passwordField_1 = new JPasswordField();
		GridBagConstraints gbc_passwordField_1 = new GridBagConstraints();
		gbc_passwordField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField_1.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField_1.gridx = 2;
		gbc_passwordField_1.gridy = 2;
		panelRegister.add(passwordField_1, gbc_passwordField_1);
		
		JButton btnNewButton_1_1 = new JButton("Generar clave");
		GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
		gbc_btnNewButton_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1_1.gridx = 3;
		gbc_btnNewButton_1_1.gridy = 2;
		panelRegister.add(btnNewButton_1_1, gbc_btnNewButton_1_1);
		
		JLabel textRegistro_1 = new JLabel("¿No estás registrado?");
		GridBagConstraints gbc_textRegistro_1 = new GridBagConstraints();
		gbc_textRegistro_1.anchor = GridBagConstraints.NORTH;
		gbc_textRegistro_1.insets = new Insets(0, 0, 5, 5);
		gbc_textRegistro_1.gridx = 3;
		gbc_textRegistro_1.gridy = 3;
		panelRegister.add(textRegistro_1, gbc_textRegistro_1);
		
		
		/* BOTONES */
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/enter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/enter-2.png")));
			}
		});
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panelCenterCardLayout.getLayout());
				cl.show(panelCenterCardLayout, "panelLogin");
			}
		});
		btnLogin.setToolTipText("");
		btnLogin.setIcon(new ImageIcon(VentanaLogin.class.getResource("/imagenes/enter-2.png")));
		panelBottom.add(btnLogin);

		
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panelCenterCardLayout.getLayout());
				cl.show(panelCenterCardLayout, "panelRegister");
			}
		});
		panelBottom.add(btnRegister);
	}

}