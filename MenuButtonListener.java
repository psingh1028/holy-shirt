import java.sql.SQLException;
import java.util.EventListener;

public interface MenuButtonListener extends EventListener {

	public void menuChoice(int choice) throws SQLException;

	
}