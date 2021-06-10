package myclass;

import java.awt.Component;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CounterOfQueue implements IGoFromTo {

	Counter cnt;
	JLabel label;
	BlockingQueue<Student> queue = new ArrayBlockingQueue<>(40);

	public CounterOfQueue(JTextField textField, JLabel label) {
		this.cnt = new Counter(textField);
		this.label = label;
	}

	public void addtoQueue(Student visitor) {
		this.queue.add(visitor);
		this.cnt.setCount(this.cnt.getCount() + 1);
	}

	public Student deleteFromQueue() {
		this.cnt.setCount(this.cnt.getCount() - 1);

		try {
			return this.queue.take();
		} catch (InterruptedException e) {

			return null;
		}
	}

	public synchronized Queue<Student> getQue() {
		return queue;
	}

	public synchronized void setQue(BlockingQueue<Student> que) {
		this.queue = que;
	}

	@Override
	public void onOut(Student var1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onIn(Student var1) {
		synchronized (this) {
			if (this.getQue().size() < 40) {
				this.addtoQueue(var1);
				this.notify();
				return;
			}
		}
	}

	@Override
	public Component getComponent() {
		return this.label;
	}

}
