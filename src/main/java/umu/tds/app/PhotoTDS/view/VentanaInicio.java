package umu.tds.app.PhotoTDS.view;

import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import pulsador.IEncendidoListener;
import pulsador.Luz;
import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.Publication;
import umu.tds.app.PhotoTDS.model.User;
import umu.tds.app.PhotoTDS.model.Album;
import umu.tds.app.PhotoTDS.model.Foto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VentanaInicio implements IEncendidoListener {

	private static JFrame frame;

	private final static int X = 150;
	private final static int Y = 150;
	private final static int X_BORDER = 500;
	private final static int Y_BORDER = 700;
	
	private final static int PROFILE_PIC_SIZE = 40;
	
	private JTextField textField;
	private static JPanel panelBusquedaClicked;
	
	private static JPanel panelCentralCardLayout;
	
	private static String user;

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
		
	@Override
	public void enteradoCambioEncendido(EventObject arg0) {
		// TODO Auto-generated method stub
		
		JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);

		if (chooser.getSelectedFile() != null) {

			String fichero = chooser.getSelectedFile().getAbsolutePath();
			Controller.getInstancia().uploadPhotos(user, fichero);

		}
		
	}

	
	public static JPanel getPanelCentralCardLayout() {
		return panelCentralCardLayout;
	}
	
	/**
	 * Create the application.
	 */
	public VentanaInicio(String u) {
		user = u;
		initialize();
	}

	public void showWindow() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public static void hideWindow() {
		frame.setVisible(false);
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

		Luz luz = new Luz();
		luz.addEncendidoListener(this);
		panel_1.add(luz, BorderLayout.EAST);
		
		JLabel premium = new JLabel("");
		
		premium.setIcon(
				new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/calidad-premium.png")));
		premium.setFont(new Font("Segoe Script", Font.BOLD, 16));
		panel_1.add(premium, BorderLayout.EAST);
		
		
		JButton addPhoto = new JButton("+");
		addPhoto.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		panel_1.add(addPhoto);
		
		addPhoto.addActionListener(e -> {
			PublicationWindow pw = new PublicationWindow(user);
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
		
		JScrollPane panelPublications = new JScrollPane();
		panelCentralCardLayout.add(panelPublications, "panelPublications");
		JList<Component> jList = new JList<>(demoList);
		jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		jList.setVisibleRowCount(-1);
		jList.ensureIndexIsVisible(jList.getHeight());
		jList.setCellRenderer(createListRenderer());
		jList.setLayoutOrientation(JList.VERTICAL);
		jList.setVisibleRowCount(-1);
		panelPublications.setViewportView(jList);
			
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initializeProfilePanel();
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
				initializeBusquedaPanel(textField.getText());
				CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
				cl.show(panelCentralCardLayout, "panelBusqueda");
			}
		});
		
		premium.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initializePremiumPanel();
				CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
				cl.show(panelCentralCardLayout, "panelPremium");
			}
		});

	}
	
	
	
	private void initializeProfilePanel() {
		
		JPanel panelPerfil = new PanelPerfil(user, user).getPanelPerfil();
		panelCentralCardLayout.add(panelPerfil, "panelPerfil");

		JPanel panelEdit = new PanelEdit(user).getPanel();
		panelCentralCardLayout.add(panelEdit, "panelEdit");
				
	}
	
	private static void initializeBusquedaClickedPanel(String u) {
		
		JPanel panelBusquedaClicked = new PanelPerfil(u, user).getPanelPerfil();
		panelCentralCardLayout.add(panelBusquedaClicked, "panelBusquedaClicked");
		
	}
	
	private void initializePremiumPanel() {
		
		JPanel panelPremium = new PanelPremium(user).getPanel();
		panelCentralCardLayout.add(panelPremium, "panelPremium");
		
	}
	
	
	
	private void initializeBusquedaPanel(String b) {
		
		JPanel panelBusqueda = new JPanel();
		panelCentralCardLayout.add(panelBusqueda, "panelBusqueda");
		
		
		List<Component> labelsBusqueda = new LinkedList<>();
		List<Object> l = Controller.getInstancia().getBusqueda(user, b);
		if(l == null)
			return;
		
		for (Object o : l) {
			JLabel label = new JLabel("");
			label.setFont(new Font("Segoe Script", Font.BOLD, 16));
			label.setBounds(0, 0, PROFILE_PIC_SIZE, PROFILE_PIC_SIZE);
			if(o instanceof User) {
				User u = ((User)o);
				label.setText(u.getUsername());
				

				Image img = createImageIcon(u.getProfilePic()).getImage().getScaledInstance(label.getHeight(), label.getWidth(), Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				label.setIcon(icon);
				
				labelsBusqueda.add(label);
			}
				
			else if (o instanceof Foto) {
				Foto p = ((Foto)o);
				label.setText(p.getTitulo());
				

				Image img = createImageIcon(p.getPath()).getImage().getScaledInstance(label.getHeight(), label.getWidth(), Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				label.setIcon(icon);
				
				labelsBusqueda.add(label);
			}
			
			else if (o instanceof Album) {
				Album p = ((Album)o);
				label.setText("[Album] " + p.getTitulo());
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
						JLabel l = (JLabel) value;
						showBusquedaClicked(l.getText());
					}
					
					((JLabel)renderer).setBackground(index % 2 == 0 ? background : defaultBackground);
				}


				return renderer;
			}
		};
	}
	
	private static void showBusquedaClicked(String u) {
		initializeBusquedaClickedPanel(u);
		CardLayout cl = (CardLayout) panelCentralCardLayout.getLayout();
		cl.show(panelCentralCardLayout, "panelBusquedaClicked");
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

}
