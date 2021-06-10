package myclass;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class InfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea Info;
	private java.net.URL url = InfoFrame.class.getResource("/resource/myphoto.jpg");
	private BufferedImage img = null;
	{
		try {
			img = ImageIO.read(url);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoFrame frame = new InfoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoFrame() {
		setTitle("Developer Information");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 466);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		JTextArea textArea = new JTextArea();
		textArea.setText(
				"Розробник програми:\r\nОлійник Володимир Ігорович\r\nСтудент группи ПІ-191\r\n\r\nE-mail:\r\sklifadventures@gmail.com\r\n\r\nТелефонний номер:\r\n+380964911222");
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		textArea.setEditable(false);
		contentPane.add(textArea);

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				Image scaleImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
				g2d.drawImage(scaleImg, 0, 0, this);
			}
		};
		contentPane.add(panel);
	}
}
