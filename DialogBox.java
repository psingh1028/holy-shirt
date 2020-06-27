import Validate.Validate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class DialogBox extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private static String title = "Select Option";

	public static void setDatabaseInfo()
	{
		JPanel panel = setPanelLayout("Enter your database information: ");

		ArrayList<JTextField> list = new ArrayList<JTextField>();

		list.add(addLabelAndTextField("Database Name:", 0, panel));
		list.add(addLabelAndTextField("Database Driver:", 1, panel));
		list.add(addLabelAndTextField("Username:", 2, panel));
		list.add(addLabelAndTextField("Password:", 3, panel));

		list.get(1).setText("com.mysql.jdbc.Driver");


		int result = JOptionPane.showConfirmDialog(null, panel, "Log In",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			DBInfo.DB_URL = "jdbc:mysql://localhost:3306/" + list.get(0).getText() + "?autoReconnect=true&useSSL=false";
			DBInfo.JDBC_DRIVER = list.get(1).getText();
			DBInfo.username = list.get(2).getText();
			DBInfo.password = list.get(3).getText();
		}
	}

	public static String[] logIn()
	{
		JPanel panel = setPanelLayout("Enter your information to log in");

		ArrayList<JTextField> list = new ArrayList<JTextField>();

		list.add(addLabelAndTextField("Username:", 0, panel));
		list.add(addLabelAndTextField("Password:", 1, panel));

		int result = JOptionPane.showConfirmDialog(null, panel, "Log In",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			return new String[] {list.get(0).getText(), list.get(1).getText()};
		}
		return null;
	}

	public static void plainMessage(String message, String title)
	{
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public static CustomerAccount createAccountPanel(CustomerAccount a)
	{
		String mesg = "";
		JPanel panel;
		if (a == null)
			panel = setPanelLayout("Enter your information to create an acount.\nPress enter to confirm each field.\nAccount will be" +
				" created if all fields have a checkmark.");
		else
			panel = setPanelLayout("Press enter to confirm each field.\nAccount will be" +
					" edited if all fields have a checkmark.");

		boolean[] isConfirmed = new boolean[] {false, false, false, false, false, false, false, false, false, false};
		String[] inputs = new String[10];
		JLabel[] labelLeft = new JLabel[10];
		JTextField[] textField = new JTextField[10];
		JLabel[] labelRight = new JLabel[10];

		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
		for (int i = 0; i < labelLeft.length; i++)
		{
			labelLeft[i] = new JLabel();
			textField[i] = new JTextField();
			labelRight[i] = new JLabel();
			gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
			gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
			gridBagConstraintForLabel.gridx = 0;
			gridBagConstraintForLabel.gridy = i+1;
			panel.add(labelLeft[i], gridBagConstraintForLabel);

			gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
			gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
			gridBagConstraintForTextField.gridx = 1;
			gridBagConstraintForTextField.gridy = i+1;
			panel.add(textField[i], gridBagConstraintForTextField);
			textField[i].setColumns(10);

			gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
			gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
			gridBagConstraintForLabel.gridx = 2;
			gridBagConstraintForLabel.gridy = i+1;
			labelRight[i].setText("X");
			panel.add(labelRight[i], gridBagConstraintForLabel);
		}

		labelLeft[0].setText("First Name:");
		labelLeft[1].setText("Last Name:");
		labelLeft[2].setText("Username (minimum of 8 characters):");
		labelLeft[3].setText("Password (minimum of 8 characters):");
		labelLeft[4].setText("Email:");
		labelLeft[5].setText("Address:");
		labelLeft[6].setText("City:");
		labelLeft[7].setText("State (2 characters):");
		labelLeft[8].setText("Zip (#####):");
		labelLeft[9].setText("CC# (################):");

		if (a != null)
		{
			inputs[0] = a.getCustomerFirstName();
			inputs[1] = a.getCustomerLastName();
			inputs[2] = a.getCustomerUsername();
			inputs[3] = a.getCustomerPassword();
			inputs[4] = a.getCustomerEmail();
			inputs[5] = a.getCustomerAddress();
			inputs[6] = a.getCustomerCity();
			inputs[7] = a.getCustomerState();
			inputs[8] = a.getCustomerZipCode();
			inputs[9] = a.getCustomerCCNum();
			for (int i = 0; i < inputs.length; i++)
			{
				textField[i].setText(inputs[i]);
			}
		}

		textField[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 0;
				if (Validate.gui.hasOnlyLetters(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 1;
				if (Validate.gui.hasOnlyLetters(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 2;
				if (Validate.gui.isLongEnough(textField[i].getText(), 8) &&
						Validate.gui.hasOnlyLettersNumbers(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 3;
				if (Validate.gui.isLongEnough(textField[i].getText(), 8))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 4;
				if (Validate.gui.isEmail(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 5;
				if (Validate.gui.hasOnlyLettersNumbersSpaces(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 6;
				if (Validate.gui.hasOnlyLettersSpaces(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 7;
				if (Validate.gui.isCorrectLength(textField[i].getText(), 2) &&
						Validate.gui.hasOnlyLetters(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 8;
				if (Validate.gui.isCorrectLength(textField[i].getText(), 5) &&
						Validate.gui.hasOnlyNumbers(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});
		textField[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int i = 9;
				if (Validate.gui.isCorrectLength(textField[i].getText(), 16) &&
						Validate.gui.hasOnlyNumbers(textField[i].getText()))
				{
					labelRight[i].setText("✓");
					isConfirmed[i] = true;
					inputs[i] = textField[i].getText();
				}
				else
				{
					labelRight[i].setText("X");
					isConfirmed[i] = false;
				}
			}
		});

		int result = JOptionPane.showConfirmDialog(null, panel, "Create Account",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) 
		{
			for (int i = 0; i < isConfirmed.length; i++)
			{
				if (isConfirmed[i] == false)
				{
					return null;
				}
			}
			if (a != null)
			{
				CustomerAccount account = new CustomerAccount(a.getID(), inputs[0], inputs[1],
						inputs[2], inputs[3], inputs[4], inputs[5],
						inputs[6], inputs[7], inputs[8], inputs[9]);
				return account;
			}
			else
				{
				CustomerAccount account = new CustomerAccount(-1, inputs[0], inputs[1],
						inputs[2], inputs[3], inputs[4], inputs[5],
						inputs[6], inputs[7], inputs[8], inputs[9]);

				return account;
			}
		}
		return null;
	}

	public static Shirt searchShirt()
	{
		JPanel panel = setPanelLayout("Search our inventory using the below criteria:");

		JLabel[] labelLeft = new JLabel[6];
		JTextField[] textField = new JTextField[6];

		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
		for (int i = 0; i < labelLeft.length; i++)
		{
			labelLeft[i] = new JLabel();
			textField[i] = new JTextField();
			gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
			gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
			gridBagConstraintForLabel.gridx = 0;
			gridBagConstraintForLabel.gridy = i+1;
			panel.add(labelLeft[i], gridBagConstraintForLabel);

			gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
			gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
			gridBagConstraintForTextField.gridx = 1;
			gridBagConstraintForTextField.gridy = i+1;
			panel.add(textField[i], gridBagConstraintForTextField);
			textField[i].setColumns(10);
		}

		labelLeft[0].setText("Name: ");
		labelLeft[1].setText("Size (Small, Medium, Large): ");
		labelLeft[2].setText("Gender: ");
		labelLeft[3].setText("Color: ");
		labelLeft[4].setText("Minimum Price: ");
		labelLeft[5].setText("Maximum Price: ");

		int result = JOptionPane.showConfirmDialog(null, panel, title,
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION)
		{
			int min = -1;
			int max = -1;
			if (textField[4].getText().length() > 0)
			{
				min = Integer.parseInt(textField[4].getText());
			}
			if (textField[5].getText().length() > 0)
			{
				max = Integer.parseInt(textField[5].getText());
			}
			return new Shirt(min, textField[0].getText(), null, -1,
					textField[2].getText(), textField[3].getText(), textField[1].getText(), max);
		}
		return null;
	}

	public static Shirt selectShirtPanel(String msg, ArrayList<Shirt> shirtList, int previous, int current)
	{
		JPanel panel = setPanelLayout(msg);
		ArrayList<JRadioButton> list = new ArrayList<JRadioButton>();
		ButtonGroup bg = new ButtonGroup();

		System.out.println("current: " + current + "\nprevious: " + previous);
		
		for (int i = previous; i < current; i++)
		{
			int counter = 0;
			Shirt s = shirtList.get(i);
			String str = (i+1) + ": " + s.getName() + ", " + s.getSize() + ", " + s.getGender();
			list.add(addLabelAndRadioButton(str, i, panel));
		}

		for (int i = 0; i < (current - previous); i++)
		{
			bg.add(list.get(i));
		}

		System.out.println("# of buttons in group: " + bg.getButtonCount());
		System.out.println("shirtList size: " + shirtList.size());
		int result = JOptionPane.showConfirmDialog(null, panel, title,
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) 
		{
			for (int i = 0; i < current - previous; i++)
			{
				if (list.get(i).isSelected())
				{
					return shirtList.get(previous + i);
				}
			}
		}
		return null;
	}

	public static int enterNumberPanel(String label, String query, int min, int max)
	{
		JPanel panel = setPanelLayout(label);

		ArrayList<JTextField> list = new ArrayList<JTextField>();

		JButton decrease = new JButton("-");
		JButton increase = new JButton("+");

		JTextPane num = new JTextPane();
		num.setEditable(false);
		num.setText(String.valueOf(min));

		decrease.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int currentNumber = Integer.valueOf(num.getText());
				if (currentNumber != min)
				{
					num.setText(String.valueOf(currentNumber - 1));
				}
			}
		});

		increase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int currentNumber = Integer.valueOf(num.getText());
				if (currentNumber != max)
				{
					num.setText(String.valueOf(currentNumber + 1));
				}
			}
		});

		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();

		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 0;
		gridBagConstraintForLabel.gridy = 1;
		panel.add(decrease, gridBagConstraintForLabel);

		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 1;
		gridBagConstraintForLabel.gridy = 1;
		panel.add(num, gridBagConstraintForLabel);

		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridx = 2;
		gridBagConstraintForLabel.gridy = 1;
		panel.add(increase, gridBagConstraintForLabel);

		int result = JOptionPane.showConfirmDialog(null, panel, title,
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION)
		{
			return Integer.valueOf(num.getText());
		}
		else
		{
			return -1;
		}


	}

	public static int logInOrCreateAccountPanel(String[] labels)
	{
		JPanel panel = setPanelLayout("Customers must be logged in to add shirts to their cart");
		ArrayList<JRadioButton> list = new ArrayList<JRadioButton>();
		ButtonGroup bg = new ButtonGroup();

		for (int i = 0; i < labels.length; i++)
		{
			list.add(addLabelAndRadioButton(labels[i], i, panel));
		}

		for (int i = 0; i < labels.length; i++)
		{
			bg.add(list.get(i));
		}

		int result = JOptionPane.showConfirmDialog(null, panel, title,
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION)
		{
			for (int i = 0; i < bg.getButtonCount(); i++)
			{
				if (list.get(i).isSelected())
				{
					return i;
				}
			}
		}
		return -1;
	}

	private static JPanel setPanelLayout(String label)
	{
		JPanel panel = new JPanel();
//		panel.add(new JLabel(label));
		panel.setBorder(new CompoundBorder(panel.getBorder(), new EmptyBorder(10, 10, 10, 10)));
		GridBagLayout panelGridBagLayout = new GridBagLayout();
		panelGridBagLayout.columnWidths = new int[] { 120, 86, 0 };
		panelGridBagLayout.rowHeights = new int[] { 20, 20, 20, 20, 20, 0 };
		panelGridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panelGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(panelGridBagLayout);
		JLabel jLabel = new JLabel(label);
		GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
		gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
		gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
		gridBagConstraintForLabel.gridwidth = 2;
		gridBagConstraintForLabel.gridy = 0;
		panel.add(jLabel, gridBagConstraintForLabel);
		return panel;
	}
	
	private static JTextField addLabelAndTextField(String labelText, int yPos, Container containingPanel) {

	    JLabel label = new JLabel(labelText);
	    GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
	    gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
	    gridBagConstraintForLabel.gridx = 0;
	    gridBagConstraintForLabel.gridy = yPos+1;
	    containingPanel.add(label, gridBagConstraintForLabel);

	    JTextField textField = new JTextField();
	    GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
	    gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
	    gridBagConstraintForTextField.gridx = 1;
	    gridBagConstraintForTextField.gridy = yPos+1;
	    containingPanel.add(textField, gridBagConstraintForTextField);
	    textField.setColumns(10);
	    return textField;
	}
	
	private static JRadioButton addLabelAndRadioButton(String labelText, int yPos, Container containingPanel) {

	    JLabel label = new JLabel(labelText);
	    GridBagConstraints gridBagConstraintForLabel = new GridBagConstraints();
	    gridBagConstraintForLabel.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForLabel.insets = new Insets(0, 0, 5, 5);
	    gridBagConstraintForLabel.gridx = 0;
	    gridBagConstraintForLabel.gridy = yPos+1;
	    containingPanel.add(label, gridBagConstraintForLabel);

	    JRadioButton radioButton = new JRadioButton();
	    GridBagConstraints gridBagConstraintForTextField = new GridBagConstraints();
	    gridBagConstraintForTextField.fill = GridBagConstraints.BOTH;
	    gridBagConstraintForTextField.insets = new Insets(0, 0, 5, 0);
	    gridBagConstraintForTextField.gridx = 1;
	    gridBagConstraintForTextField.gridy = yPos+1;
	    containingPanel.add(radioButton, gridBagConstraintForTextField);
//	    radioButton.setColumns(10);
	    return radioButton;
	}

}