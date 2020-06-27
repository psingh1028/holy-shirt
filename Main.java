import Validate.Validate;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
//				DialogBox.setDatabaseInfo();
				try {
					new AppWindow();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

	}
}
