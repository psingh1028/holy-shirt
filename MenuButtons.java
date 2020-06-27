import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuButtons extends JPanel implements ActionListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[] menuBtn;
	private MenuButtonListener menuButtonListener;
	
	private final int MAX_BUTTONS = 9;
	
	public MenuButtons() 
	{
		
		menuBtn = new JButton[9];
		for (int i = 0; i < 9; i++) 
		{
			menuBtn[i] = new JButton();
			menuBtn[i].addActionListener(this);
		}
		Border innerBorder = BorderFactory.createTitledBorder("MENU");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout()); // very flexible layout
		
		GridBagConstraints gc = new GridBagConstraints(); // bunch of fields that must be initialized
		
		gc.weightx = 2;
		gc.weighty = .7; // weight controls how much space it takes up relative to other sells
		
		for (int i = 0; i < 9; i++)
		{
			gc.gridx = 0;
			gc.gridy = i; // corresponding cells on (x,y) coordinate
			gc.fill = GridBagConstraints.NONE;
			gc.anchor = GridBagConstraints.CENTER;
			gc.insets = new Insets(0,0,0,0); // adds 5 pixels to the right of nameLabel
			add(menuBtn[i], gc);
			menuBtn[i].setSize(175, 50);
		}
		
		Dimension dim = new Dimension();
		dim = getPreferredSize();
		dim.width = 200;
		setPreferredSize(dim);
		
	}
	
	
	
	public void setButtonText(String[] menu)
	{
		
		for (int i = 0; i < MAX_BUTTONS;i++)
		{
			if (i < menu.length)
			{
				menuBtn[i].setText(menu[i]);
				menuBtn[i].setEnabled(true);
				menuBtn[i].setVisible(true);
			}
			else 
			{
				menuBtn[i].setEnabled(false);
				menuBtn[i].setVisible(false);
			}
		}
	}
	
	public void menuChoice(MenuButtonListener hlistener) {
		this.menuButtonListener = hlistener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		for (int i = 0; i < 9; i++)
		{
			if (clicked == menuBtn[i])
			{
				if (menuButtonListener != null)
				{
					try {
						menuButtonListener.menuChoice(i);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
	}
		
	
}
