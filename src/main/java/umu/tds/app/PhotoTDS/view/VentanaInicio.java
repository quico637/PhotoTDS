package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
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
import umu.tds.app.PhotoTDS.model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		lblNewLabel_1
				.setIcon(new ImageIcon(VentanaInicio.class.getResource("/umu/tds/app/PhotoTDS/images/buscar.png")));
		panel_1.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textField);
		textField.setColumns(10);

		JPanel panelPrincipal = new JPanel();
		panelCentralCardLayout.add(panelPrincipal, "panelPrincipal");

		//
		Controller.getInstancia().createFoto("Elquici", new Date(), "Madre mia el quico #marica", "/C");
		//
		JButton btnNewButton = new JButton("Elquecko");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controller.getInstancia().getAllPublications();
			}
		});
		panelPrincipal.add(btnNewButton);
		
		List<User> employees = Controller.getInstancia().getAllusers();
	    JList<User> jList = new JList<>(employees.toArray(new User[employees.size()]));
	    jList.setCellRenderer(createListRenderer());
	    jList.addListSelectionListener(createListSelectionListener(jList));
		panelPrincipal.add(jList);
		
//		Controller.getInstancia().getAllusers();

		JPanel panelPerfil = new JPanel();
		panelCentralCardLayout.add(panelPerfil, "panelPerfil");
		panelPerfil.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
	}
	
	private static ListSelectionListener createListSelectionListener(JList list) {
	      return e -> {
	          if (!e.getValueIsAdjusting()) {
	              System.out.println(list.getSelectedValue());
	          }
	      };
	  }

	  private static ListCellRenderer<? super User> createListRenderer() {
	      return new DefaultListCellRenderer() {
	          /**
			 * º
			 */
			private static final long serialVersionUID = 1L;
			private Color background = new Color(0, 100, 255, 15);
	          private Color defaultBackground = (Color) UIManager.get("List.background");

	          @Override
	          public Component getListCellRendererComponent(JList<?> list, Object value, int index,
	                                                        boolean isSelected, boolean cellHasFocus) {
	              Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	              if (c instanceof JLabel) {
	                  JLabel label = (JLabel) c;
	                  User u = (User) value;
	                  label.setText(String.format("%s [%s]", u.getUsername(), u.getEmail()));
	                  if (!isSelected) {
	                      label.setBackground(index % 2 == 0 ? background : defaultBackground);
	                  }
	                  label.setIcon(new ImageIcon(DescriptionWindow.class.getResource("/umu/tds/app/PhotoTDS/images/instagram.png")));
	           
	                  
	              }
	              return c;
	          }
	      };
	  }

	  private static JFrame createFrame() {
	      JFrame frame = new JFrame("JList Example");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setSize(new Dimension(600, 300));
	      return frame;
	  }

}
