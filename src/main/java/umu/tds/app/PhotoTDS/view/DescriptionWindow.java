package umu.tds.app.PhotoTDS.view;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.Color;

public class DescriptionWindow {

	private JFrame frame;
	private VentanaLogin lw = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DescriptionWindow window = new DescriptionWindow(null);
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
	public DescriptionWindow(VentanaLogin lw) {
		this.lw = lw;
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
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Add your presentation card");
		panel.add(lblNewLabel);
		
		Panel panel_1 = new Panel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Send");
		panel_1.add(btnNewButton);
		
		TextArea textArea = new TextArea();
		textArea.setBackground(Color.ORANGE);
		textArea.setForeground(Color.BLACK);
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
	}

}
