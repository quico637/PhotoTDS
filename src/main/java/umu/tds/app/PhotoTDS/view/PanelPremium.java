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
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.persistence.jpa.jpql.parser.DatabaseTypeFactory;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.Foto;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class PanelPremium {

	/**
	 * 
	 */
	
	private JFrame frame;
		
	private String user;
	private JPanel panel;
	
	private boolean logged;
	private static List<Publication> l;

	
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
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JPanel getPanel() {
		return panel;
	}



	public PanelPremium(String user) {
		super();
		this.user = user;
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
		
		panel = new JPanel();
		
		Optional<User> us = Controller.getInstancia().getUser(user);
		
		if(us.isEmpty())
			return;
					
		User u = us.get();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{40, 213, 0, 40, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 27, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel prem = new JLabel("Get premium! Just for " + u.getTotalPremiumPrice() + "$");
		
		prem.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_prem = new GridBagConstraints();
		gbc_prem.fill = GridBagConstraints.HORIZONTAL;
		gbc_prem.anchor = GridBagConstraints.NORTH;
		gbc_prem.insets = new Insets(0, 0, 5, 5);
		gbc_prem.gridx = 1;
		gbc_prem.gridy = 1;
		panel.add(prem, gbc_prem);
		
		JButton suscribe = new JButton("Suscribe!");
		GridBagConstraints gbc_suscribe = new GridBagConstraints();
		gbc_suscribe.fill = GridBagConstraints.HORIZONTAL;
		gbc_suscribe.insets = new Insets(0, 0, 5, 5);
		gbc_suscribe.gridx = 2;
		gbc_suscribe.gridy = 1;
		panel.add(suscribe, gbc_suscribe);
		
		JLabel lblNewLabel = new JLabel("Access a trial of what you can get with premium");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		panel.add(scrollPane, gbc_scrollPane);
		
		List<JLabel> labels = new LinkedList<>();
		l = Controller.getInstancia().lastLikedFotos(user);
		for (Publication p : l) {
			System.out.println("p -  -- -- - - --");
			if (p instanceof Foto) {
				JLabel etiqueta = new JLabel();
				Image imagen = createImageIcon(((Foto) p).getPath()).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
				ImageIcon icono = new ImageIcon(imagen);
				etiqueta.setIcon(icono);
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
		jList.setVisible(false);
		scrollPane.setViewportView(jList);
		
		JButton btnNewButton = new JButton("Last Liked Photo");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancia().lastLikedFotos(user).stream().forEach(p -> System.out.println(p));
				jList.setVisible(true);
			}
		});
		
		
		suscribe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controller.getInstancia().goPremium(user);
				CardLayout cl = (CardLayout) VentanaInicio.getPanelCentralCardLayout().getLayout();
				cl.show(VentanaInicio.getPanelCentralCardLayout(), "panelPremium");
			}
		});
		
		
		
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
						JPanel panelFoto = new PanelFoto(l.get(index), l.get(index).getCreator()).getPanel();
						JPanel panelCentralCardLayout = VentanaInicio.getPanelCentralCardLayout();
						panelCentralCardLayout.add(panelFoto, "panelFoto");
						CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
						cl.show(panelCentralCardLayout, "panelFoto");
						System.out.println("Fotico golfa");
					}
					
					((JLabel)renderer).setBackground(index % 2 == 0 ? background : defaultBackground);
				}


				return renderer;
			}
		};
	}

}
