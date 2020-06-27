import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class TextDisplay extends JPanel 
{
	private JEditorPane display;
	
	public TextDisplay()
	{
		display = new JEditorPane();
		JScrollPane scrollPane = new JScrollPane(display);
		Border innerBorder = BorderFactory.createTitledBorder("View"); // borderfactor has many options
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		setLayout(new GridBagLayout()); // very flexible layout
		
		GridBagConstraints gc = new GridBagConstraints(); // bunch of fields that must be initialized
		
		//FIRST ROW//
		
		gc.weightx = 2;
		gc.weighty = 1; // weight controls how much space it takes up relative to other sells
		
		gc.gridx = 0;
		gc.gridy = 0; // corresponding cells on (x,y) coordinate
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0,0,0,0); // adds 5 pixels to the right of nameLabel
//		display.setLineWrap(true);
//		display.setWrapStyleWord(true);
		add(scrollPane, gc);
		
		display.setEditable(false);
		display.setContentType("text/html");
		display.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		display.setFont(new Font("Segoe Script", Font.PLAIN, 16));
//		Dimension dim = new Dimension();
//		dim = getPreferredSize();
//		dim.width = 100;
//		setPreferredSize(dim);

	}
	
	public void setText(String text) 
	{
		display.setText("");
		display.setText(text);
//		display.append(text);
	}
}
