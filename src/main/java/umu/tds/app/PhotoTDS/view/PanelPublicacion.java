package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import org.eclipse.persistence.jpa.jpql.parser.DatabaseTypeFactory;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;

public class PanelPublicacion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private final int MAX_ALTO;
	private final int MAX_ANCHO;
		
	private String perfil;
	private String comentario;
	private String path;
	
	private String pathProfilePic;
	
	public JFrame getFrame() {
		return this.frame;
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


	/**
	 * Create the application.
	 */
	public PanelPublicacion(String perfil, String comentario, String path, String pathProfilePic, int x, int y, int maxAlto, int maxAncho) {
		this.perfil = perfil;
		this.comentario = comentario;
		this.path = path;
		this.pathProfilePic = pathProfilePic;
		this.MAX_ALTO = maxAlto;
		this.MAX_ANCHO = maxAncho;
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
		
		JPanel seccionFoto = new JPanel();
		frame.getContentPane().add(seccionFoto, BorderLayout.CENTER);
		GridBagLayout gbl_seccionFoto = new GridBagLayout();
		gbl_seccionFoto.columnWidths = new int[]{0, 0, 0, 0};
		gbl_seccionFoto.rowHeights = new int[]{0, 0, 0, 0};
		gbl_seccionFoto.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_seccionFoto.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		seccionFoto.setLayout(gbl_seccionFoto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, MAX_ANCHO, MAX_ALTO);
		ImageIcon imageIcon = new ImageIcon(PanelPublicacion.class.getResource("/umu/tds/app/PhotoTDS/images/maxresdefault.jpg"));

		Image img = createImageIcon(pathProfilePic).getImage().getScaledInstance(lblNewLabel.getHeight(), lblNewLabel.getWidth(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		lblNewLabel.setIcon(icon);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		seccionFoto.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel seccionUsuario = new JPanel();
		frame.getContentPane().add(seccionUsuario, BorderLayout.NORTH);
		GridBagLayout gbl_seccionUsuario = new GridBagLayout();
		gbl_seccionUsuario.columnWidths = new int[]{0, 0, 0};
		gbl_seccionUsuario.rowHeights = new int[]{0, 0};
		gbl_seccionUsuario.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_seccionUsuario.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		seccionUsuario.setLayout(gbl_seccionUsuario);
		
		JLabel profilePic = new JLabel("");
		
		profilePic.setBounds(0, 0, 25, 25);

		img = createImageIcon(path).getImage().getScaledInstance(profilePic.getHeight(), profilePic.getWidth(), Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		profilePic.setIcon(icon);
		
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		seccionUsuario.add(profilePic, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("PERFIL: " + this.perfil);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 0;
		seccionUsuario.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JPanel seccionComentario = new JPanel();
		frame.getContentPane().add(seccionComentario, BorderLayout.SOUTH);
		GridBagLayout gbl_seccionComentario = new GridBagLayout();
		gbl_seccionComentario.columnWidths = new int[]{0, 0};
		gbl_seccionComentario.rowHeights = new int[]{0, 0};
		gbl_seccionComentario.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_seccionComentario.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		seccionComentario.setLayout(gbl_seccionComentario);
		
		JLabel lblNewLabel_2 = new JLabel("Comentario: " + this.comentario);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		seccionComentario.add(lblNewLabel_2, gbc_lblNewLabel_2);
	}

}
