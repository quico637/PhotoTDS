package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.persistence.jpa.jpql.parser.DatabaseTypeFactory;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.Album;
import umu.tds.app.PhotoTDS.model.Comentario;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.JTextField;

public class PanelAlbumFotos {

	/**
	 * 
	 */
	private final static int PROFILE_PIC_SIZE = 40;
	private final static int X = 150;
	private final static int Y = 150;
	private JFrame frame;

	private Album publicacion;
	private String profilePath;
	private JPanel panel;
	private static List<Foto> l;

	private String userLogged;

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

	public PanelAlbumFotos(Album publicacion, String userLogged) {
		super();
		this.userLogged = userLogged;
		this.publicacion = publicacion;
		System.out.println("titulo album: " + this.publicacion.getTitulo());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 625);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		GridBagLayout gbl_panelFoto = new GridBagLayout();
		gbl_panelFoto.columnWidths = new int[] { 40, 0, 0, 0, 0, 40, 40, 0 };
		gbl_panelFoto.rowHeights = new int[] { 40, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelFoto.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelFoto.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panelFoto);

		JLabel userName = new JLabel(publicacion.getCreator() + " : " + publicacion.getTitulo());
		GridBagConstraints gbc_userName = new GridBagConstraints();
		gbc_userName.gridwidth = 5;
		gbc_userName.insets = new Insets(0, 0, 5, 5);
		gbc_userName.gridx = 1;
		gbc_userName.gridy = 1;
		panel.add(userName, gbc_userName);

		JLabel profPicEdit = new JLabel();
		GridBagConstraints gbc_profPicEdit = new GridBagConstraints();
		gbc_profPicEdit.gridwidth = 5;
		gbc_profPicEdit.gridheight = 3;
		gbc_profPicEdit.insets = new Insets(0, 0, 5, 5);
		gbc_profPicEdit.gridx = 1;
		gbc_profPicEdit.gridy = 2;
		panel.add(profPicEdit, gbc_profPicEdit);
		profPicEdit.setBounds(0, 0, 40, 40);
		Image imageEdit;
		imageEdit = createImageIcon(((Album) publicacion).getFotos().get(0).getPath()).getImage().getScaledInstance(200,
				200, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(imageEdit);
		profPicEdit.setIcon(icon);
				
						JButton btnNewButton = new JButton("");
						btnNewButton.addActionListener(e -> {
							// Hay que cambiarlo a AlbumAddWindow - Crealo yulari mentiendono
							AlbumWindowFotos pw = new AlbumWindowFotos(userLogged, publicacion);
							pw.showWindow(frame);
						});
								
										JLabel lblNewLabel = new JLabel("");
										GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
										gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
										gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
										gbc_lblNewLabel.gridx = 1;
										gbc_lblNewLabel.gridy = 5;
										panel.add(lblNewLabel, gbc_lblNewLabel);
										lblNewLabel.setText(Integer.toString(publicacion.getLikes()));
						
								JButton like = new JButton("");
								like.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
								like.setIcon(new ImageIcon(PanelAlbumFotos.class.getResource("/umu/tds/app/PhotoTDS/images/love.png")));
								GridBagConstraints gbc_like = new GridBagConstraints();
								gbc_like.fill = GridBagConstraints.HORIZONTAL;
								gbc_like.insets = new Insets(0, 0, 5, 5);
								gbc_like.gridx = 2;
								gbc_like.gridy = 5;
								panel.add(like, gbc_like);
								like.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										Controller.getInstancia().meGusta(publicacion, userLogged);
									}
								});
						btnNewButton.setIcon(
								new ImageIcon(PanelAlbumFotos.class.getResource("/umu/tds/app/PhotoTDS/images/anadir-imagen.png")));
						GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
						gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
						gbc_btnNewButton.gridx = 4;
						gbc_btnNewButton.gridy = 5;
						panel.add(btnNewButton, gbc_btnNewButton);
		
				JButton deleteBtn = new JButton("");
				deleteBtn.addActionListener(e -> {
					if (!Controller.getInstancia().removePublication(userLogged, publicacion))
						System.out.println("User Not logged in PanelFoto.");
				});
				deleteBtn.setIcon(new ImageIcon(PanelAlbumFotos.class.getResource("/umu/tds/app/PhotoTDS/images/delete.png")));
				GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
				gbc_deleteBtn.insets = new Insets(0, 0, 5, 5);
				gbc_deleteBtn.gridx = 5;
				gbc_deleteBtn.gridy = 5;
				panel.add(deleteBtn, gbc_deleteBtn);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 7;
		panel.add(scrollPane, gbc_scrollPane);

		List<JLabel> labels = new LinkedList<>();
		l = ((Album) publicacion).getFotos();
		for (Foto p : l) {
			JLabel etiqueta = new JLabel();
			Image imagen = createImageIcon(((Foto) p).getPath()).getImage().getScaledInstance(X, Y, Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(imagen);
			etiqueta.setIcon(icono);
			labels.add(etiqueta);
			System.out.println("Publicacion: " + p);
		}

		DefaultListModel<Component> demoList = new DefaultListModel<>();
		demoList.addAll(labels);

		JList<Component> jList = new JList<>(demoList);
		jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		jList.setVisibleRowCount(-1);
		jList.ensureIndexIsVisible(jList.getHeight());
		jList.setCellRenderer(createListRenderer());
		scrollPane.setViewportView(jList);

	}

	private static ListCellRenderer<? super Component> createListRenderer() {
		return new DefaultListCellRenderer() {
			/**
			 * º
			 */
			private static final long serialVersionUID = 1L;
			private Color background = new Color(0, 100, 255, 15);
			private Color defaultBackground = (Color) UIManager.get("List.background");

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				Component renderer = (Component) value;
				if (renderer instanceof JLabel) {
					if (isSelected) {
						JPanel panelFoto = new PanelFoto(l.get(index), l.get(index).getCreator()).getPanel();
						JPanel panelCentralCardLayout = VentanaInicio.getPanelCentralCardLayout();
						panelCentralCardLayout.add(panelFoto, "panelFoto");
						CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
						cl.show(panelCentralCardLayout, "panelFoto");
						System.out.println("Fotico golfa");
					}

					((JLabel) renderer).setBackground(index % 2 == 0 ? background : defaultBackground);
				}

				return renderer;
			}
		};
	}

}
