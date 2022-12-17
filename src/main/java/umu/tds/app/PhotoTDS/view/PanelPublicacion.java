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
	private final static int MAX_ALTO = 625;
	private final static int MAX_ANCHO = 275;
	private String perfil;
	private String comentario;
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					PanelPublicacion window = new PanelPublicacion();
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
	public PanelPublicacion(String perfil, String comentario) {
		this.perfil = perfil;
		this.comentario = comentario;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 625);
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
		ImageIcon imageIcon = new ImageIcon(PanelPublicacion.class.getResource("/umu/tds/app/PhotoTDS/images/maxresdefault.jpg"));
		float redimension = 1;
//		if(imageIcon.getIconHeight() > imageIcon.getIconWidth()) {
//			redimension = (100 * MAX_ALTO) / imageIcon.getIconHeight();
//		}else {
//			redimension = (100 * MAX_ANCHO) / imageIcon.getIconWidth();
//		}
//		
//		
//		if(imageIcon.getIconWidth() > MAX_ANCHO) {
//			redimension = (100 * MAX_ANCHO) / imageIcon.getIconHeight();
//		} else if (imageIcon.getIconHeight() > MAX_ALTO) {
//			redimension = (100 * MAX_ALTO) / imageIcon.getIconWidth();
//		}
//		redimension /=  100;
//		System.out.println(redimension);
//
//		Image image = imageIcon.getImage().getScaledInstance(Math.round(imageIcon.getIconWidth()*redimension), Math.round(imageIcon.getIconHeight()*redimension), Image.SCALE_SMOOTH);
//		imageIcon = new ImageIcon(image);
		lblNewLabel.setIcon(new ImageIcon(PanelPublicacion.class.getResource("/umu/tds/app/PhotoTDS/images/take-a-photo (2).png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		seccionFoto.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel seccionUsuario = new JPanel();
		frame.getContentPane().add(seccionUsuario, BorderLayout.NORTH);
		GridBagLayout gbl_seccionUsuario = new GridBagLayout();
		gbl_seccionUsuario.columnWidths = new int[]{0, 0};
		gbl_seccionUsuario.rowHeights = new int[]{0, 0};
		gbl_seccionUsuario.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_seccionUsuario.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		seccionUsuario.setLayout(gbl_seccionUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("PERFIL: " + this.perfil);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		seccionUsuario.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
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
