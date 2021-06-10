package myclass;

import java.awt.Component;

import javax.swing.JLabel;

public class ModifyLabel implements IGoFromTo {
	private JLabel label;

	public ModifyLabel(JLabel label) {
		this.label = label;

	}

	@Override
	public void onOut(Student var1) {

	}

	@Override
	public void onIn(Student var1) {

	}

	@Override
	public Component getComponent() {
		return this.label;
	}

}
