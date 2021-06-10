package myclass;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DialogOfSettings extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JSpinner spinnerTableMin;
	private JSpinner spinnetPCMin;
	private JSpinner spinnerLecturerMin;

	private JSpinner spinnerTableMax;
	private JSpinner spinnerPCMax;
	private JSpinner spinnerLecturerMax;

	private Integer minTable;
	private Integer maxTable;
	private Integer minPC;
	private Integer maxPC;
	private Integer minLect;
	private Integer maxLect;

	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogOfSettings dialog = new DialogOfSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogOfSettings() {
		setModal(true);
		setBounds(200, 200, 300, 187);
		getRootPane().setDefaultButton(okButton);
		setTitle("Student's Amount");
		setBounds(100, 100, 300, 187);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 279, 0 };
		gridBagLayout.rowHeights = new int[] { 118, 41, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
		gbc_contentPanel.gridx = 0;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 119, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 26, 20, 20, 20, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_4 = new JLabel("Minimum");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 1;
			gbc_lblNewLabel_4.gridy = 0;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Maximum");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_5.gridx = 2;
			gbc_lblNewLabel_5.gridy = 0;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		}
		{
			JLabel lblNewLabel = new JLabel("Students at the table:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			spinnerTableMin = new JSpinner();
			spinnerTableMin.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			GridBagConstraints gbc_spinnerTBMin = new GridBagConstraints();
			gbc_spinnerTBMin.fill = GridBagConstraints.BOTH;
			gbc_spinnerTBMin.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerTBMin.gridx = 1;
			gbc_spinnerTBMin.gridy = 1;
			contentPanel.add(spinnerTableMin, gbc_spinnerTBMin);
		}
		{
			spinnerTableMax = new JSpinner();
			spinnerTableMax.setModel(new SpinnerNumberModel(4, 1, 100, 1));
			GridBagConstraints gbc_spinnerTBmax = new GridBagConstraints();
			gbc_spinnerTBmax.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerTBmax.insets = new Insets(0, 0, 5, 0);
			gbc_spinnerTBmax.gridx = 2;
			gbc_spinnerTBmax.gridy = 1;
			contentPanel.add(spinnerTableMax, gbc_spinnerTBmax);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Students at the PC:");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 2;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			spinnetPCMin = new JSpinner();
			spinnetPCMin.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			GridBagConstraints gbc_spinnerEyemin = new GridBagConstraints();
			gbc_spinnerEyemin.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerEyemin.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerEyemin.gridx = 1;
			gbc_spinnerEyemin.gridy = 2;
			contentPanel.add(spinnetPCMin, gbc_spinnerEyemin);
		}
		{
			spinnerPCMax = new JSpinner();
			spinnerPCMax.setModel(new SpinnerNumberModel(6, 1, 100, 1));
			GridBagConstraints gbc_spinnerEyemax = new GridBagConstraints();
			gbc_spinnerEyemax.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerEyemax.insets = new Insets(0, 0, 5, 0);
			gbc_spinnerEyemax.gridx = 2;
			gbc_spinnerEyemax.gridy = 2;
			contentPanel.add(spinnerPCMax, gbc_spinnerEyemax);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Students near the lecturer:");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 3;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			spinnerLecturerMin = new JSpinner();
			spinnerLecturerMin.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			GridBagConstraints gbc_spinnerShipmin = new GridBagConstraints();
			gbc_spinnerShipmin.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerShipmin.insets = new Insets(0, 0, 0, 5);
			gbc_spinnerShipmin.gridx = 1;
			gbc_spinnerShipmin.gridy = 3;
			contentPanel.add(spinnerLecturerMin, gbc_spinnerShipmin);
		}
		{
			spinnerLecturerMax = new JSpinner();
			spinnerLecturerMax.setModel(new SpinnerNumberModel(5, 1, 100, 1));
			GridBagConstraints gbc_spinnerShipmax = new GridBagConstraints();
			gbc_spinnerShipmax.fill = GridBagConstraints.HORIZONTAL;
			gbc_spinnerShipmax.gridx = 2;
			gbc_spinnerShipmax.gridy = 3;
			contentPanel.add(spinnerLecturerMax, gbc_spinnerShipmax);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.SOUTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);

			{
				okButton = new JButton("Oke");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		okButton.addActionListener(e -> {
			onOK();

		});

		cancelButton.addActionListener(e -> {
			onCancel();

		});

	}

	private void onOK() {
		boolean flag = true;
		try {
			this.minTable = (Integer) spinnerTableMin.getValue();
			this.minLect = (Integer) spinnerLecturerMin.getValue();
			this.minPC = (Integer) spinnetPCMin.getValue();

			this.maxTable = (Integer) spinnerTableMax.getValue();
			this.maxLect = (Integer) spinnerLecturerMax.getValue();
			this.maxPC = (Integer) spinnerPCMax.getValue();

			if (maxTable < minTable || maxLect < minLect || maxPC < minPC) {
				JOptionPane.showMessageDialog(null, "All values ​​of the maximum value may be more for the minimum \n",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				flag = false;
			}

		} catch (Exception e1) {

			JOptionPane.showMessageDialog(null, "You have entered incorrect data. Try again.\n", "ERROR",
					JOptionPane.ERROR_MESSAGE);

			flag = false;
		}
		if (flag) {
			DialogOfSettings.this.setVisible(false);
			dispose();
		}
	}

	private void onCancel() {
		DialogOfSettings.this.setVisible(false);
		dispose();
	}

	public synchronized int getMinTable() {
		return minTable;
	}

	public synchronized int getMaxTable() {
		return maxTable;
	}

	public synchronized int getMinPC() {
		return minPC;
	}

	public synchronized int getMaxPC() {
		return maxPC;
	}

	public synchronized int getMinLect() {
		return minLect;
	}

	public synchronized int getMaxLect() {
		return maxLect;
	}

}
