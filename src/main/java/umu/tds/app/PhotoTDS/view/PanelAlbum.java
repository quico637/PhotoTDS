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

public class PanelAlbum {

	/**
	 * 
	 */
	private final static int PROFILE_PIC_SIZE = 40;
	private final static int X = 150;
	private final static int Y = 150;
	private JFrame frame;
		
	private String user;
	private String profilePath;
	private JPanel panelPerfil;
	
	private String userLogged;

	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JPanel getPanelPerfil() {
		return panelPerfil;
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




	public PanelAlbum(String user, String userLogged) {
		super();
		this.user = user;
		this.userLogged = userLogged;
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
		
		panelPerfil = new JPanel();
		GridBagLayout gbl_panelPerfil = new GridBagLayout();
		gbl_panelPerfil.columnWidths = new int[]{40, 0, 0, 50, 0, 55, 65, 50, 0};
		gbl_panelPerfil.rowHeights = new int[]{40, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelPerfil.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPerfil.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelPerfil.setLayout(gbl_panelPerfil);
		
		JLabel profPic = new JLabel();
		GridBagConstraints gbc_profPic = new GridBagConstraints();
		gbc_profPic.gridwidth = 2;
		gbc_profPic.gridheight = 3;
		gbc_profPic.fill = GridBagConstraints.HORIZONTAL;
		gbc_profPic.insets = new Insets(0, 0, 5, 5);
		gbc_profPic.gridx = 1;
		gbc_profPic.gridy = 1;
		panelPerfil.add(profPic, gbc_profPic);
		profPic.setFont(new Font("Segoe Script", Font.BOLD, 16));
		profPic.setBounds(0, 0, PROFILE_PIC_SIZE, PROFILE_PIC_SIZE);
		Optional<User> us = Controller.getInstancia().getUser(user);
		User use = us.get();
		Image img = createImageIcon(use.getProfilePic()).getImage().getScaledInstance(X, Y, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(img);
		profPic.setIcon(icon);
		
		
		JLabel userName = new JLabel(user);
		GridBagConstraints gbc_userName = new GridBagConstraints();
		gbc_userName.anchor = GridBagConstraints.WEST;
		gbc_userName.insets = new Insets(0, 0, 5, 5);
		gbc_userName.gridx = 4;
		gbc_userName.gridy = 1;
		panelPerfil.add(userName, gbc_userName);
		
		JLabel lblNewLabel = new JLabel("Seguidores");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 2;
		panelPerfil.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel completeName = new JLabel(use.getNombreCompleto());
		GridBagConstraints gbc_completeName = new GridBagConstraints();
		gbc_completeName.anchor = GridBagConstraints.WEST;
		gbc_completeName.gridwidth = 2;
		gbc_completeName.insets = new Insets(0, 0, 5, 5);
		gbc_completeName.gridx = 4;
		gbc_completeName.gridy = 3;
		panelPerfil.add(completeName, gbc_completeName);
		
		JButton editButton = new JButton("Edit Profile");
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.gridwidth = 2;
		gbc_editButton.insets = new Insets(0, 0, 5, 5);
		gbc_editButton.gridx = 5;
		gbc_editButton.gridy = 1;
		
		if(isUserLogged())
		panelPerfil.add(editButton, gbc_editButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		panelPerfil.add(scrollPane, gbc_scrollPane);
		
		List<JLabel> labels = new LinkedList<>();
//		List<Publication> l = Controller.getInstancia().getUser(user).get().getPublications();
		List<Publication> l = Controller.getInstancia().getAllPublications();
		for (Publication p : l) {
			if (p instanceof Album) {
				JLabel etiqueta = new JLabel();
				Image icono = new ImageIcon(((Album)p).getPathProfileFoto()).getImage().getScaledInstance(X, Y, Image.SCALE_SMOOTH);
				etiqueta.setIcon(new ImageIcon(icono));
				labels.add(etiqueta);
				System.out.println("Publicacion: " + p);
			}
		}
		
		DefaultListModel<Component> demoList = new DefaultListModel<>();
		demoList.addAll(labels);
		
		JList<Component> jList = new JList<>(demoList);
		jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		jList.setVisibleRowCount(-1);
		jList.ensureIndexIsVisible(jList.getHeight());
		jList.setCellRenderer(createListRenderer());
		scrollPane.setViewportView(jList);
		
		JButton btnNewButton = new JButton("Fotos");
		btnNewButton.addActionListener(e -> {
			CardLayout cl = (CardLayout) VentanaInicio.getPanelCentralCardLayout().getLayout();
			cl.show(VentanaInicio.getPanelCentralCardLayout(), "panelPerfil");
		});
		
		JButton btnNewButton_1 = new JButton("A+");
		btnNewButton_1.addActionListener(e -> {
			
			AlbumWindow pw = new AlbumWindow(user);
			pw.showWindow(frame);
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 6;
		panelPerfil.add(btnNewButton_1, gbc_btnNewButton_1);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 6;
		panelPerfil.add(btnNewButton, gbc_btnNewButton);
		
		
		
		editButton.addActionListener(e -> {
			CardLayout cl = (CardLayout) VentanaInicio.getPanelCentralCardLayout().getLayout();
			cl.show(VentanaInicio.getPanelCentralCardLayout(), "panelEdit");
		});
		

		JButton logout = new JButton("Logout");
		GridBagConstraints gbc_logout = new GridBagConstraints();
		gbc_logout.gridwidth = 2;
		gbc_logout.insets = new Insets(0, 0, 5, 5);
		gbc_logout.gridx = 1;
		gbc_logout.gridy = 4;
		
		logout.addActionListener(e -> {
			if(!Controller.getInstancia().logout(user)) System.out.println("Cagaste en LOGOUT");
			VentanaInicio.hideWindow();
			VentanaLogin.getInstancia().showWindow();
		});

		
		
		
		if(isUserLogged())
			panelPerfil.add(logout, gbc_logout);
		else {
			JButton follow = new JButton("");
			if(Controller.getInstancia().checkFollower(userLogged, user))
				follow.setText("Unfollow");
			else 
				follow.setText("Follow");
			
			
			follow.addActionListener(e -> {
				Controller.getInstancia().follow(userLogged, user);
				// update
			});
			panelPerfil.add(follow, gbc_logout);
		}
		

//		JButton createPdf = new JButton("PDF");
//		createPdf.addActionListener(e -> {
//			
//			JFileChooser chooser = new JFileChooser();
//			chooser.showSaveDialog(null);
//
//			if (chooser.getSelectedFile() != null) {
//
//				String fichero = chooser.getSelectedFile().getAbsolutePath();
//				Controller.getInstancia().createPdf(user, fichero);
//
//			}
//		});
//		panelPerfil.add(createPdf);
		
		
		
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
				if(renderer instanceof JLabel) {
					if(isSelected) {
						JLabel l = (JLabel) value;
						
					}
					
					((JLabel)renderer).setBackground(index % 2 == 0 ? background : defaultBackground);
				}


				return renderer;
			}
		};
	}
	
	private boolean isUserLogged() {
		return user.equals(userLogged);
	}

}
