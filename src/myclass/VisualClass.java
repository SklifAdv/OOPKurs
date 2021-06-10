package myclass;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import threads.PC;
import threads.Lecturer;
import threads.Table;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import rnd.*;

public class VisualClass {

	private JFrame frmOliynykVI;
	private JTextField queuePC;
	private JTextField queueLec;
	private JTextField queueTable;
	private JTextField servedTable;
	private JTextField servedLec;
	private JTextField servedPC;

	private JSlider arival;
	private JSlider speedPC;
	private JSlider speedTable;
	private JSlider speedLec;
	private JLabel queueLabelPC;
	private JLabel queueLabelLect;
	private JLabel queueLabelTable;

	private JButton btnStop;
	private JButton btnStart;
	private Sound play;
	private Thread tc1;

	private Thread th1;
	private Thread th2;
	private Thread th3;

	private long startTime;

	CounterOfQueue qToTable;
	CounterOfQueue qToPC;
	CounterOfQueue qToLec;

	ModifyLabel lab;
	ModifyLabel ext;
	JLabel tableBoxlbl;
	JLabel lectlbl;
	JLabel pclbl;
	JLabel enter;
	private JLabel lblNewLabel_1;

	private InfoFrame infoFrame = new InfoFrame();
	private DialogOfSettings settingsdialog;
	private JLabel lblNewLabel_3;

	private JTextField counterTable;
	private JTextField counterPC;
	private JTextField counterLec;
	private JLabel label_6;
	private JSlider slider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VisualClass window = new VisualClass();
					window.frmOliynykVI.setVisible(true);
				} catch (Exception var2) {
					var2.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualClass() {
		initialize();
		qToTable = new CounterOfQueue(this.queueTable, this.queueLabelTable);
		qToPC = new CounterOfQueue(this.queuePC, this.queueLabelPC);
		qToLec = new CounterOfQueue(this.queueLec, this.queueLabelLect);

		JLabel lblNewLabel_2 = new JLabel("Volume");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(202, 130, 91, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("At the table");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(409, 270, 113, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_3);

		counterTable = new JTextField();
		counterTable.setHorizontalAlignment(SwingConstants.CENTER);
		counterTable.setEditable(false);
		counterTable.setText("0");
		counterTable.setBounds(460, 301, 59, 26);
		frmOliynykVI.getContentPane().add(counterTable);
		counterTable.setColumns(10);

		counterPC = new JTextField();
		counterPC.setHorizontalAlignment(SwingConstants.CENTER);
		counterPC.setEditable(false);
		counterPC.setText("0");
		counterPC.setBounds(62, 411, 59, 26);
		frmOliynykVI.getContentPane().add(counterPC);
		counterPC.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Working with PC");
		lblNewLabel_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBounds(62, 364, 179, 29);
		frmOliynykVI.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Working on Score");
		lblNewLabel_5.setForeground(Color.DARK_GRAY);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_5.setBounds(1106, 222, 159, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_5);

		counterLec = new JTextField();
		counterLec.setHorizontalAlignment(SwingConstants.CENTER);
		counterLec.setEditable(false);
		counterLec.setText("0");
		counterLec.setBounds(1140, 253, 59, 26);
		frmOliynykVI.getContentPane().add(counterLec);
		counterLec.setColumns(10);

		slider = new JSlider();
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setEnabled(false);
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				onVolumechanged();
			}
		});

		slider.setPaintLabels(true);
		slider.setValue(5);
		slider.setMaximum(10);
		slider.setBounds(151, 165, 162, 49);
		frmOliynykVI.getContentPane().add(slider);
		
				label_6 = new JLabel("");
				label_6.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/cabinet.png")));
				label_6.setBounds(10, 10, 1289, 650);
				frmOliynykVI.getContentPane().add(label_6);
	}

	/**
	 * @return the queuetoTicket
	 */
	// Метод задання розташування інформації про розробника
	protected void onInfoClick() {
		infoFrame.setLocation(frmOliynykVI.getLocation().x, frmOliynykVI.getLocation().y);
		infoFrame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOliynykVI = new JFrame();
		frmOliynykVI.setFont(new Font("Dialog", Font.PLAIN, 20));
		frmOliynykVI.setTitle("Oliynyk V. I. PI-191");
		frmOliynykVI.getContentPane().setBackground(Color.WHITE);
		frmOliynykVI.setBounds(100, 100, 1305, 722);
		Container pane = frmOliynykVI.getContentPane();
		frmOliynykVI.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmOliynykVI.setJMenuBar(menuBar);
		frmOliynykVI.setFocusable(true);

		JMenu infomore = new JMenu("More inf");
		infomore.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(infomore);

		JMenuItem iteminfo = new JMenuItem("About Creator");
		infomore.add(iteminfo);
		iteminfo.addActionListener(e -> {
			try {
				onInfoClick();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Помилка виводу інформаціїї", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
				JLabel lblNewLabel_12 = new JLabel("Queue");
				lblNewLabel_12.setForeground(Color.WHITE);
				lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				lblNewLabel_12.setBounds(691, 368, 64, 20);
				frmOliynykVI.getContentPane().add(lblNewLabel_12);

		enter = new JLabel("");
		enter.setBounds(563, 553, 64, 91);
		enter.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/door.png")));
		frmOliynykVI.getContentPane().add(enter);

		JLabel lblNewLabel_20 = new JLabel("Entry speed");
		lblNewLabel_20.setForeground(Color.BLACK);
		lblNewLabel_20.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_20.setBounds(770, 553, 157, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_20);

		arival = new JSlider();
		arival.setBounds(727, 584, 200, 61);
		arival.setMajorTickSpacing(1);
		arival.setPaintLabels(true);
		arival.setPaintTicks(true);
		arival.setValue(6);
		arival.setMinorTickSpacing(1);
		arival.setMinimum(1);
		arival.setMaximum(10);
		frmOliynykVI.getContentPane().add(arival);

		queueLabelPC = new JLabel("");
		queueLabelPC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		queueLabelPC.setBounds(329, 429, 42, 105);
		queueLabelPC.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/stud/studentstatic.png")));
		frmOliynykVI.getContentPane().add(queueLabelPC);

		pclbl = new JLabel("");
		pclbl.setBounds(142, 475, 100, 68);
		pclbl.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/PC.png")));
		frmOliynykVI.getContentPane().add(pclbl);

		lblNewLabel_1 = new JLabel("Entrance/Exit");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(527, 533, 137, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_1);

		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnStart.setBounds(946, 549, 159, 29);

		this.btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualClass.this.doRun();
			}
		});
		frmOliynykVI.getContentPane().add(btnStart);

		JLabel lblNewLabel_13 = new JLabel("Queue");
		lblNewLabel_13.setForeground(Color.WHITE);
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_13.setBounds(318, 518, 91, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_13);

		JLabel lblNewLabel_11 = new JLabel("PC speed");
		lblNewLabel_11.setForeground(Color.BLACK);
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_11.setBounds(177, 558, 100, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_11);

		btnStop = new JButton("End");
		btnStop.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnStop.setBounds(946, 593, 159, 29);

		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualClass.this.doStopPlay();
			}
		});

		JLabel lblDoneJob = new JLabel("Done job");
		lblDoneJob.setForeground(Color.DARK_GRAY);
		lblDoneJob.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblDoneJob.setBounds(62, 518, 91, 20);
		frmOliynykVI.getContentPane().add(lblDoneJob);
		frmOliynykVI.getContentPane().add(btnStop);

		queuePC = new JTextField();
		queuePC.setEditable(false);
		queuePC.setBounds(318, 545, 64, 26);
		frmOliynykVI.getContentPane().add(queuePC);
		queuePC.setColumns(10);

		speedPC = new JSlider();
		speedPC.setBounds(151, 590, 162, 49);
		speedPC.setPaintLabels(true);
		speedPC.setMajorTickSpacing(1);
		speedPC.setPaintTicks(true);
		speedPC.setValue(7);
		speedPC.setMinorTickSpacing(1);
		speedPC.setMinimum(1);
		speedPC.setMaximum(10);
		frmOliynykVI.getContentPane().add(speedPC);

		servedPC = new JTextField();
		servedPC.setHorizontalAlignment(SwingConstants.CENTER);
		servedPC.setEditable(false);
		servedPC.setBounds(62, 549, 59, 26);
		frmOliynykVI.getContentPane().add(servedPC);
		servedPC.setColumns(10);

		tableBoxlbl = new JLabel("");
		tableBoxlbl.setBounds(505, 244, 201, 193);
		tableBoxlbl.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/table.png")));
		frmOliynykVI.getContentPane().add(tableBoxlbl);

		queueLabelTable = new JLabel("");
		queueLabelTable.setBounds(703, 283, 41, 85);
		queueLabelTable.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/stud/studentstatic.png")));
		frmOliynykVI.getContentPane().add(queueLabelTable);

		queueLabelLect = new JLabel("");
		queueLabelLect.setBounds(890, 240, 46, 105);
		queueLabelLect.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/stud/studentstatic.png")));
		frmOliynykVI.getContentPane().add(queueLabelLect);

		lectlbl = new JLabel("");
		lectlbl.setBounds(946, 217, 179, 220);
		lectlbl.setIcon(new ImageIcon(VisualClass.class.getResource("/resource/lecturer.png")));
		frmOliynykVI.getContentPane().add(lectlbl);

		JLabel lblNewLabel_16 = new JLabel("Thought");
		lblNewLabel_16.setForeground(Color.WHITE);
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_16.setBounds(438, 348, 81, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_16);

		JLabel lblNewLabel_8 = new JLabel("Speed of thinking");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_8.setBounds(539, 124, 163, 33);
		frmOliynykVI.getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_14 = new JLabel("Queue");
		lblNewLabel_14.setForeground(Color.WHITE);
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_14.setBounds(868, 348, 59, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_14);

		JLabel lblNewLabel_9 = new JLabel("Speed of Giving Score");
		lblNewLabel_9.setForeground(Color.BLACK);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_9.setBounds(928, 137, 226, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_17 = new JLabel("Take score");
		lblNewLabel_17.setForeground(Color.DARK_GRAY);
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_17.setBounds(1135, 348, 114, 20);
		frmOliynykVI.getContentPane().add(lblNewLabel_17);

		servedTable = new JTextField();
		servedTable.setEditable(false);
		servedTable.setBounds(460, 380, 59, 26);
		frmOliynykVI.getContentPane().add(servedTable);
		servedTable.setColumns(10);

		speedTable = new JSlider();
		speedTable.setBounds(517, 159, 200, 61);
		speedTable.setToolTipText("");
		speedTable.setMajorTickSpacing(1);
		speedTable.setPaintLabels(true);
		speedTable.setPaintTicks(true);
		speedTable.setValue(5);
		speedTable.setMinorTickSpacing(1);
		speedTable.setMinimum(1);
		speedTable.setMaximum(10);
		frmOliynykVI.getContentPane().add(speedTable);

		queueTable = new JTextField();
		queueTable.setEditable(false);
		queueTable.setBounds(698, 398, 46, 26);
		frmOliynykVI.getContentPane().add(queueTable);
		queueTable.setColumns(10);

		queueLec = new JTextField();
		queueLec.setEditable(false);
		queueLec.setBounds(868, 375, 64, 26);
		frmOliynykVI.getContentPane().add(queueLec);
		queueLec.setColumns(10);

		speedLec = new JSlider();
		speedLec.setBounds(946, 168, 162, 49);
		speedLec.setPaintLabels(true);
		speedLec.setMajorTickSpacing(1);
		speedLec.setMinorTickSpacing(1);
		speedLec.setMinimum(1);
		speedLec.setMaximum(10);
		speedLec.setValue(4);
		speedLec.setPaintTicks(true);
		frmOliynykVI.getContentPane().add(speedLec);

		servedLec = new JTextField();
		servedLec.setEditable(false);
		servedLec.setBounds(1151, 375, 59, 26);
		frmOliynykVI.getContentPane().add(servedLec);
		servedLec.setColumns(10);

		frmOliynykVI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	protected void doRun() {

		this.btnStart.setEnabled(false);
		VisualClass.this.slider.setEnabled(true);
		Counter counterTable = new Counter(this.servedTable);
		Counter counterPC = new Counter(this.servedPC);
		Counter counterLec = new Counter(this.servedLec);

		try {
			this.settingsdialog = new DialogOfSettings();
			this.settingsdialog.setVisible(true);
			int minLec = this.settingsdialog.getMinLect();
			int minTable = this.settingsdialog.getMinTable();
			int minPC = this.settingsdialog.getMinPC();

			int maxLec = this.settingsdialog.getMaxLect();
			int maxTable = this.settingsdialog.getMaxTable();
			int maxPC = this.settingsdialog.getMaxPC();
			

			if (maxPC != 0) {
				Lecturer handler1 = new Lecturer(this, this.lectlbl, this.qToLec, this.speedLec, minLec, maxLec,
						counterLec, this.counterLec);
				PC handler2 = new PC(this, this.pclbl, this.qToPC, this.speedPC, minPC, maxPC, counterPC,
						this.counterPC);
				Table handler3 = new Table(this, this.tableBoxlbl, this.qToTable, this.speedTable, counterTable,
						minTable, maxTable, this.counterTable);

				SdntCreator creator1 = new SdntCreator(this, this.arival, this.enter);

				this.startTime = System.currentTimeMillis();

				(this.tc1 = new Thread(creator1)).start();
				(this.th3 = new Thread(handler3)).start();
				(this.th2 = new Thread(handler2)).start();
				(this.th1 = new Thread(handler1)).start();
				URL u = this.getClass().getResource("/resource/op.wav");
				this.play = Sound.playSound(u);
				playMusic();
				this.play.setVolume((float) slider.getValue() / 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playMusic() {
		if (this.play.isPlaying() == false) {
			this.play.play();
		}
		onEndOfPlay();
	}

	private void doStopPlay() {
		this.play.stop();
		VisualClass.this.btnStart.setEnabled(true);
		VisualClass.this.slider.setEnabled(true);
	}

	private void onEndOfPlay() {
		(new Thread() {
			public void run() {
				try {
					VisualClass.this.th1.join();
					VisualClass.this.th2.join();
					VisualClass.this.th3.join();
					VisualClass.this.tc1.join();
					VisualClass.this.btnStart.setEnabled(true);
					VisualClass.this.slider.setEnabled(true);
				} catch (InterruptedException var2) {
					var2.printStackTrace();
				}
			}
		}).start();
	}

	public void onVolumechanged() {
		this.play.setVolume((float) slider.getValue() / 10);
	}

	public Component getPane() {
		return this.frmOliynykVI.getContentPane();
	}

	public boolean isPlaying() {
		return this.play.isPlaying();
	}

	public boolean isCreatorWorking() {
		return this.tc1.isAlive();
	}

	public JFrame getFrame() {
		return this.frmOliynykVI;
	}

	public void println(String s) {
		long time = System.currentTimeMillis() - this.startTime;
	}

	public synchronized CounterOfQueue getQueuetoTicket() {
		return qToTable;
	}

	public synchronized CounterOfQueue getQueuetoEye() {
		return qToPC;
	}

	public synchronized CounterOfQueue getQueuetoShip() {
		return qToLec;
	}

	public synchronized void setQueuetoTicket(CounterOfQueue queuetoTicket) {
		this.qToTable = queuetoTicket;
	}

	public synchronized void setQueuetoEye(CounterOfQueue queuetoEye) {
		this.qToPC = queuetoEye;
	}

	public synchronized void setQueuetoShip(CounterOfQueue queuetoShip) {
		this.qToLec = queuetoShip;
	}

	public synchronized JLabel getQueueLabelEye() {
		return queueLabelPC;
	}

	public synchronized JLabel getQueueLabelShip() {
		return queueLabelLect;
	}

	public synchronized JLabel getQueueLabelTicket() {
		return queueLabelTable;
	}

	public synchronized void setQueueLabelEye(JLabel queueLabelEye) {
		this.queueLabelPC = queueLabelEye;
	}

	public synchronized void setQueueLabelShip(JLabel queueLabelShip) {
		this.queueLabelLect = queueLabelShip;
	}

	public synchronized void setQueueLabelTicket(JLabel queueLabelTicket) {
		this.queueLabelTable = queueLabelTicket;
	}
}
