package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;

import umu.tds.app.PhotoTDS.controller.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class VentanaInicio {

	private JFrame frame;
	
	private final static int X_BORDER = 500;
	private final static int Y_BORDER = 700;
	private static VentanaInicio unicaInstancia = null;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio window = new VentanaInicio();
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
	private VentanaInicio() {
		initialize();
	}
	
	public void showWindow() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public static VentanaInicio getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new VentanaInicio();
		}

		return unicaInstancia;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/instagram.png")));
		frame.setBounds(100, 100, X_BORDER, Y_BORDER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralCardLayout = new JPanel();
		frame.getContentPane().add(panelCentralCardLayout, BorderLayout.CENTER);
		panelCentralCardLayout.setLayout(new CardLayout(0, 0));

		JLabel photoApp = new JLabel("PhotoApp");
		photoApp.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		photoApp.setIcon(
				new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/iconoPequeno.png")));
		panelNorte.add(photoApp, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Profile");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
				cl.show(panelCentralCardLayout, "panelPerfil");
			}
		});
		lblNewLabel.setIcon(
				new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/profileInicio.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 16));
		panelNorte.add(lblNewLabel, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panelNorte.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/buscar.png")));
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JPanel panelPrincipal = new JPanel();
		panelCentralCardLayout.add(panelPrincipal, "panelPrincipal");
		Controller.getInstancia().getAllusers();

		JPanel panelPerfil = new JPanel();
		panelCentralCardLayout.add(panelPerfil, "panelPerfil");
		panelPerfil.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
