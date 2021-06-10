package myclass;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JSlider;

import rnd.Randomable;
import rnd.Uniform;

public class SdntCreator implements Runnable, IGoFromTo {
	JSlider timeOfWaiting;
	VisualClass gui;
	Student stdnt;
	JLabel label;
	Thread th4;

	Randomable rnd = new Uniform(1, 15, true);

	public SdntCreator(VisualClass gui, JSlider timeOfWaiting, JLabel label) {
		this.gui = gui;
		this.timeOfWaiting = timeOfWaiting;
		this.label = label;

	};

	public synchronized JSlider getWaitingTime() {
		return timeOfWaiting;
	}

	public synchronized void setWaitingTime(JSlider timeOfWaiting) {
		this.timeOfWaiting = timeOfWaiting;
	}

	@Override
	public void run() {
		synchronized (gui) {
			do {

				int i = (int) rnd.next();
				stdnt = new Student(this.gui, this, i);
				(this.th4 = new Thread(this.stdnt)).start();

				try {
					Thread.sleep((long) (this.timeOfWaiting.getValue() * 1000) + (long) (rnd.next() * 100));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} while (this.gui.isPlaying());

		}

	}

	@Override
	public void onOut(Student var1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onIn(Student var1) {
		synchronized (this) {

			this.notify();
		}

	}

	@Override
	public Component getComponent() {

		return this.label;
	}

}
