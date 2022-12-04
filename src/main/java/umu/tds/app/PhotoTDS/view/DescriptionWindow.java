package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.TextArea;
import java.util.List;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import javax.swing.JList;

import umu.tds.app.PhotoTDS.controller.Controller;
import umu.tds.app.PhotoTDS.model.User;

public class DescriptionWindow {

	private JFrame frame;
	private static DescriptionWindow unicaInstancia = null;
	private String descripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescriptionWindow window = new DescriptionWindow();
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
	private DescriptionWindow() {
		initialize();
	}

	public void showWindow(Component c) {
		frame.setVisible(true);
		frame.setLocationRelativeTo(c);
	}

	public void hideWindow() {
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);
	}

	public static DescriptionWindow getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new DescriptionWindow();
		}

		return unicaInstancia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme");
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Add your presentation card");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lblNewLabel);

		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Send");
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 106, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

//		JList<String> list = new JList<>();
//		DefaultListModel<String> model = new DefaultListModel<>();
//		model.addElement("Alumno 1");
//		model.addElement("Alumno 2");
//		model.addElement("Alumno 3");
//		list.setModel(model);
		
		List<User> employees = Controller.getInstancia().getAllusers();
	    JList<User> jList = new JList<>(employees.toArray(new User[employees.size()]));
	    jList.setCellRenderer(createListRenderer());
	    jList.addListSelectionListener(createListSelectionListener(jList));
		
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 0;
//		panel_2.add(list, gbc_list);
		panel_2.add(jList);

		TextArea textArea = new TextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		panel_2.add(textArea, gbc_textArea);
		textArea.setBackground(new Color(20, 32, 49));
		textArea.setForeground(Color.WHITE);

		btnNewButton.addActionListener(e -> {
			this.hideWindow();
			this.descripcion = textArea.getText();
		});
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
