package myclass;

import java.awt.Component;

import javax.swing.JTextField;

public class Counter implements IGoFromTo {
	private JTextField textField;
	private int cnt;

	public Counter(JTextField textField) {
		this.textField = textField;
		this.setCount(0);
	}

	public void setCount(int n) {
		this.cnt = n;
		this.textField.setText(String.valueOf(n));
	}

	public int getCount() {
		return this.cnt;

	}

	public void onOut(Student tr) {
	}

	public void onIn(Student tr) {
		this.setCount(++this.cnt);
		this.textField.setText(String.valueOf(cnt));
	}

	public Component getComponent() {
		return this.textField;

	}
}
