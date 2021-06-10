package myclass;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Student implements Runnable {
	private VisualClass gui;
	Graphics2D g;
	private int hT = 10;
	private int wT = 10;
	public ArrayList<Integer> place = new ArrayList<>();
	private static String[] stdntPictures = new String[] { "/resource/stud/student1.png", "/resource/stud/student2.png",
			"/resource/stud/student3.png", "/resource/stud/student4.png", "/resource/stud/student5.png" };
	JLabel label = new JLabel();

	private int sizeOfQueue;

	SdntCreator cr;

	public Student(VisualClass gui, SdntCreator cr, int sizeOfQ) {
		this.gui = gui;

		this.g = (Graphics2D) gui.getPane().getGraphics();

		this.cr = cr;
		this.sizeOfQueue = sizeOfQ;
	}

	public Thread moveFromTo(final IGoFromTo from, final IGoFromTo to) {
		Thread t = new Thread() {
			public void run() {
				int xFrom = Student.this.pointFrom(from).x;
				int xTo = Student.this.pointTo(to).x;
				if (xFrom > xTo) {
					xFrom = Student.this.pointTo(from).x;
					xTo = Student.this.pointFrom(to).x;
				}
				int lenX = xTo - xFrom;
				int yFrom = Student.this.pointFrom(from).y;
				int yTo = Student.this.pointTo(to).y;
				int lenY = yTo - yFrom;
				int len = (int) Math.round(Math.sqrt((double) (lenX * lenX + lenY * lenY)));
				int lenT = (Student.this.hT + Student.this.wT) / 2;
				int n = len / lenT + 1;
				int dx = lenX / n;
				int dy = lenY / n;
				from.onOut(Student.this);
				Student.this.gui.getFrame().add(Student.this.label, 1);

				int x = xFrom;
				int y = yFrom;
				int counter = 0;

				for (int i = 0; i < n; ++i) {
					if (counter > stdntPictures.length - 1) {
						counter = 0;
					}
					URL u = this.getClass().getResource(stdntPictures[counter]);

					try {
						Image image = ImageIO.read(u);
						if (xFrom > xTo) {
							image = mirror(image);
						}
						ImageIcon im = new ImageIcon(image);
						Student.this.label.setIcon(im);

						Student.this.label.setBounds(x, y, im.getIconWidth(), im.getIconHeight());

						try {
							Thread.sleep(100);
						} catch (InterruptedException var16) {
							var16.printStackTrace();
						}

						Student.this.label.setBounds(x, y, im.getIconWidth(), im.getIconHeight());

						x += dx;
						y += dy;
						counter++;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				Student.this.label.setIcon(null);
				to.onIn(Student.this);

			}

		};
		t.start();

		return t;
	}

	public Point pointFrom(IGoFromTo ft) {
		Component c = ft.getComponent();
		int x = c.getX() + c.getWidth();
		int y = c.getY() + c.getHeight() / 2;
		return new Point(x, y);
	}

	public Point pointTo(IGoFromTo ft) {
		Component c = ft.getComponent();
		int x = c.getX();
		int y = c.getY() + c.getHeight() / 2;
		return new Point(x, y);
	}

	@Override
	public void run() {

		try {

				this.moveFromTo(this.cr, this.gui.qToTable).join();
				synchronized (this) {
					while (this.place.isEmpty()) {
						this.wait();
					}
				}

				this.gui.println(this.place.toString());
				while (!this.place.isEmpty()) {

					if (this.gui.qToPC.queue.size() <= this.sizeOfQueue) {
						this.moveFromTo(this.gui.qToTable, this.gui.qToPC).join();
						synchronized (this) {
							int currentsize = this.place.size();
							while (this.place.size() == currentsize) {
								this.wait();
							}
						}
					}

					if (this.gui.qToLec.queue.size() <= this.sizeOfQueue) {
						this.moveFromTo(this.gui.qToPC, this.gui.qToLec).join();
						synchronized (this) {
							int currentsize = this.place.size();
							while (this.place.size() == currentsize) {
								this.wait();
							}
						}
					}
					break;

				}

				this.moveFromTo(this.gui.qToLec, this.cr).join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized Image mirror(Image img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter((BufferedImage) img, null);
		return img;
	}

	public synchronized ArrayList<Integer> getPlace() {
		return place;
	}

	public synchronized void setPlace(ArrayList<Integer> place) {
		this.place = place;
	}

}
