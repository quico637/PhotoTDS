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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelPremiumUser {

	/**
	 * 
	 */

	private JFrame frame;

	private String user;
	private JPanel panel;
	private static List<Publication> l;

	private boolean logged;

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

	public JFrame getFrame() {
		return this.frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public PanelPremiumUser(String user) {
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

		if (us.isEmpty())
			return;

		User u = us.get();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 40, 184, 60, 79, 0 };
		gbl_panel.rowHeights = new int[] { 40, 23, 0, 0, 0, 0, 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel excel = new JLabel("Generate Excel file with your followers");

		GridBagConstraints gbc_excel = new GridBagConstraints();
		gbc_excel.anchor = GridBagConstraints.EAST;
		gbc_excel.insets = new Insets(0, 0, 5, 5);
		gbc_excel.gridx = 1;
		gbc_excel.gridy = 1;
		panel.add(excel, gbc_excel);
		

		JButton excelButton = new JButton("Generate Excel");
		GridBagConstraints gbc_excelButton = new GridBagConstraints();
		gbc_excelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_excelButton.anchor = GridBagConstraints.NORTH;
		gbc_excelButton.insets = new Insets(0, 0, 5, 5);
		gbc_excelButton.gridx = 2;
		gbc_excelButton.gridy = 1;
		panel.add(excelButton, gbc_excelButton);

		excelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(null);

				if (chooser.getSelectedFile() != null) {

					String fichero = chooser.getSelectedFile().getAbsolutePath();
					Controller.getInstancia().createExcel(user, fichero);
				}
			}
		});

		JLabel pdf = new JLabel("Generate Excel file with your followers");
		GridBagConstraints gbc_pdf = new GridBagConstraints();
		gbc_pdf.anchor = GridBagConstraints.EAST;
		gbc_pdf.insets = new Insets(0, 0, 5, 5);
		gbc_pdf.gridx = 1;
		gbc_pdf.gridy = 2;
		panel.add(pdf, gbc_pdf);

		JButton pdfButton = new JButton("Generate PDF");
		GridBagConstraints gbc_pdfButton = new GridBagConstraints();
		gbc_pdfButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_pdfButton.insets = new Insets(0, 0, 5, 5);
		gbc_pdfButton.anchor = GridBagConstraints.NORTH;
		gbc_pdfButton.gridx = 2;
		gbc_pdfButton.gridy = 2;
		panel.add(pdfButton, gbc_pdfButton);

		pdfButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(null);

				if (chooser.getSelectedFile() != null) {

					String fichero = chooser.getSelectedFile().getAbsolutePath();
					Controller.getInstancia().createPdf(user, fichero);
				}
			}
		});

		JButton btnNewButton = new JButton("Most Liked");

		JLabel lblNewLabel = new JLabel("Show most like publications");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		panel.add(btnNewButton, gbc_btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		panel.add(scrollPane, gbc_scrollPane);

		List<JLabel> labels = new LinkedList<>();
		l = Controller.getInstancia().getMoreLikedFotos(user);
		for (Publication p : l) {
			System.out.println("p -  -- -- - - --");
			if (p instanceof Foto) {
				JLabel etiqueta = new JLabel();
				Image imagen = createImageIcon(((Foto) p).getPath()).getImage().getScaledInstance(150, 150,
						Image.SCALE_SMOOTH);
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

		btnNewButton.addActionListener(e -> {
			Controller.getInstancia().getMoreLikedFotos(user).stream().forEach(p -> System.out.println(p));
			jList.setVisible(true);
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
