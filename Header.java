import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public class Header extends JPanel
{
//	private JEditorPane header;
	private JLabel header;
	
	private String welcome = "<html><pre>"
			+ "  _  _     _        ___ _    _     _   _ <br/>"
			+ " | || |___| |_  _  / __| |_ (_)_ _| |_| |<br/>"
			+ " | __ / _ \\ | || | \\__ \\ ' \\| | '_|  _|_|<br/>"
			+ " |_||_\\___/_|\\_, | |___/_||_|_|_|  \\__(_)<br/>"
			+ "             |__/                        	<br/>"
			+ "-----------------------------------------<br/>"
			+ "It's what you'll be saying when you see our selection!<br/>"
			+ "(minus the 'r')\n\n</pre></html>";
	
	private String welcome2 = "<html><pre>		      ___         ___         ___  ___                   ___         ___                   ___      ___     <br>"
			+ "		      /\\__\\       /\\  \\       /\\__\\|\\__\\                 /\\  \\       /\\__\\        ___      /\\  \\    /\\  \\    <br>"
			+ "		     /:/  /      /::\\  \\     /:/  /|:|  |               /::\\  \\     /:/  /       /\\  \\    /::\\  \\   \\:\\  \\   <br>"
			+ "		    /:/__/      /:/\\:\\  \\   /:/  / |:|  |              /:/\\ \\  \\   /:/__/        \\:\\  \\  /:/\\:\\  \\   \\:\\  \\  <br>"
			+ "		   /::\\  \\ ___ /:/  \\:\\  \\ /:/  /  |:|__|__           _\\:\\~\\ \\  \\ /::\\  \\ ___    /::\\__\\/::\\~\\:\\  \\  /::\\  \\ <br>"
			+ "		  /:/\\:\\  /\\__/:/__/ \\:\\__/:/__/   /::::\\__\\         /\\ \\:\\ \\ \\__/:/\\:\\  /\\__\\__/:/\\/__/:/\\:\\ \\:\\__\\/:/\\:\\__\\<br>"
			+ "		  \\/__\\:\\/:/  \\:\\  \\ /:/  \\:\\  \\  /:/~~/~            \\:\\ \\:\\ \\/__\\/__\\:\\/:/  /\\/:/  /  \\/_|::\\/:/  /:/  \\/__/<br>"
			+ "		       \\::/  / \\:\\  /:/  / \\:\\  \\/:/  /               \\:\\ \\:\\__\\      \\::/  /\\::/__/      |:|::/  /:/  /     <br>"
			+ "		       /:/  /   \\:\\/:/  /   \\:\\  \\/__/                 \\:\\/:/  /      /:/  /  \\:\\__\\      |:|\\/__/\\/__/      <br>"
			+ "		      /:/  /     \\::/  /     \\:\\__\\                     \\::/  /      /:/  /    \\/__/      |:|  |             <br>"
			+ "		      \\/__/       \\/__/       \\/__/                      \\/__/       \\/__/                 \\|__|          </pre><br></html>";
	
			
			
	public Header()
	{
//		welcome.replaceAll(" ", "&#160;");
		Border innerBorder = BorderFactory.createTitledBorder("Welcome");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		header = new JLabel(welcome);
//		header = new JEditorPane();
		header.setFont(header.getFont().deriveFont(14f));
//		header.setContentType("text/html");
//		header.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
//		header.setFont(new Font("Segoe Script", Font.PLAIN, 50));
//		header.setText("<h1 style=\"text-align: center; font-size: 50px; \">Holy Shirt!</h1>/n" +
//				"<h3 style=\"text-align: center; font-size: 30px;\">It's what you'll be saying when you see our selection!</h3>/n" +
//				"<h4 style=\"text-align: center; font-size: 20px;\">(minus the 'r')</h4>");
		
//		header.setHorizontalAlignment(JLabel.LEFT);
		setBackground(Color.WHITE);
		add(header);

//		Dimension dim = new Dimension();
//		dim.width = 100;
//		setPreferredSize(dim);
//
	}
}
