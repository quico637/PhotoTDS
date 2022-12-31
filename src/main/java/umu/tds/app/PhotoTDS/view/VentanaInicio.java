package umu.tds.app.PhotoTDS.view;

import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.Foto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

public class VentanaInicio {

	private JFrame frame;

	private final static int X = 100;
	private final static int Y = 100;
	private final static int X_BORDER = 500;
	private final static int Y_BORDER = 700;
	private static VentanaInicio unicaInstancia = null;
	private JTextField textField;
	
	private static JPanel panelCentralCardLayout;
	
	private String user;
	

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the application.
	 */
	public VentanaInicio(String u) {
		this.user = u;
		initialize();
	}

	public void showWindow() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/instagram.png")));
		frame.setBounds(X, Y, X_BORDER, Y_BORDER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));

		panelCentralCardLayout = new JPanel();
		frame.getContentPane().add(panelCentralCardLayout, BorderLayout.CENTER);
		panelCentralCardLayout.setLayout(new CardLayout(0, 0));

				
		JLabel photoApp = new JLabel("PhotoApp");
		photoApp.setFont(new Font("Segoe Script", Font.PLAIN, 20));


		photoApp.setIcon(
				new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/iconoPequeno.png")));
		panelNorte.add(photoApp, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Profile");
		
		lblNewLabel.setIcon(
				new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/profileInicio.png")));
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 16));
		panelNorte.add(lblNewLabel, BorderLayout.EAST);

		JPanel panel_1 = new JPanel();
		panelNorte.add(panel_1, BorderLayout.CENTER);

		
		JButton addPhoto = new JButton("+");
		addPhoto.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		panel_1.add(addPhoto);
		
		addPhoto.addActionListener(e -> {
			PublicationWindow pw = new PublicationWindow(this.user);
			pw.showWindow(frame);
		});
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1
				.setIcon(new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/buscar.png")));
		panel_1.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textField);
		textField.setColumns(10);

		List<Component> paneles = new LinkedList<>();
		List<Publication> l = Controller.getInstancia().getPublicationsToShow(user);
		System.out.println("l2: " + l);
		for (Publication p : l) {
			User u = Controller.getInstancia().getUser(p.getCreator()).get();
			paneles.add(new PanelPublicacion(u.getUsername(), p.getDescripcion(), u.getProfilePic(), ((Foto) p).getPath(),
					50, 50, Math.round(VentanaInicio.X_BORDER / 2), Math.round(VentanaInicio.Y_BORDER / 2)).getFrame()
					.getContentPane());
			System.out.println("PERSON: " + u.getUsername());
		}
		
		DefaultListModel<Component> demoList = new DefaultListModel<>();
		demoList.addAll(paneles);
		
		
		JPanel panelPublications = new JPanel();
		panelCentralCardLayout.add(panelPublications, "panelPublications");
		JList<Component> jList = new JList<>(demoList);
		panelPublications.add(jList);
		jList.setCellRenderer(createListRenderer());
		jList.addListSelectionListener(createListSelectionListener(jList));
		
		JPanel panelPerfil = new JPanel();
		panelCentralCardLayout.add(panelPerfil, "panelPerfil");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panelPerfil.add(lblNewLabel_2);
		
		JButton logout = new JButton("Logout");
		panelPerfil.add(logout);
		
		JPanel panelBusqueda = new JPanel();
		panelCentralCardLayout.add(panelBusqueda, "panelBusqueda");
		
		JPanel panelBusquedaClicked = new JPanel();
		panelCentralCardLayout.add(panelBusquedaClicked, "panelBusquedaClicked");
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Etiqueta o Pub");
		panelBusquedaClicked.add(lblNewLabel_3_1_1);
		logout.addActionListener(e -> {
			Controller.getInstancia().logout(user);
			this.frame.setVisible(false);
			VentanaLogin.getInstancia().showWindow();
		});
		
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
				cl.show(panelCentralCardLayout, "panelPerfil");
			}
		});
		
		photoApp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
				cl.show(panelCentralCardLayout, "panelPublications");
			}
		});
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initializeBusquedaPanel(textField.getText(), panelBusqueda);
				CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
				cl.show(panelCentralCardLayout, "panelBusqueda");
			}
		});
		

	}
	
	private void initializeBusquedaPanel(String b, JPanel panelBusqueda) {
		
		List<Component> labelsBusqueda = new LinkedList<>();
		for (Object o : Controller.getInstancia().getBusqueda(this.user, b)) {
			JLabel label = new JLabel("");
			if(o instanceof User) {
				User u = ((User)o);
//				label = new JLabel("[User] " + u.getUsername());
				label.setText("[User] " + u.getUsername());
				labelsBusqueda.add(label);
			}
				
			else if (o instanceof Publication) {
				Publication p = ((Publication)o);
//				label = new JLabel("[Pub]" + p.getTitulo());
				label.setText("[Pub]" + p.getTitulo());
				labelsBusqueda.add(label);
			}
			
		}
		
		DefaultListModel<Component> demoBusqueda = new DefaultListModel<>();
		demoBusqueda.addAll(labelsBusqueda);
		
		JList<Component> jListBusqueda = new JList<>(demoBusqueda);
		panelBusqueda.add(jListBusqueda);
		jListBusqueda.setCellRenderer(createListRenderer());
		jListBusqueda.addListSelectionListener(createListSelectionListener(jListBusqueda));
		
	}

	private static ListSelectionListener createListSelectionListener(JList<Component> list) {
		return e -> {
			if (!e.getValueIsAdjusting()) {
				System.out.println(list.getSelectedValue());
			}
		};
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
						showBusquedaClicked();
					}
					
					((JLabel)renderer).setBackground(index % 2 == 0 ? background : defaultBackground);
				}


				return renderer;
			}
		};
	}
	
	private static void showBusquedaClicked() {
		CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
		cl.show(panelCentralCardLayout, "panelBusquedaClicked");
	}

//	private static JFrame createFrame() {
//		JFrame frame = new JFrame("JList Example");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(new Dimension(600, 300));
//		return frame;
//	}

}
