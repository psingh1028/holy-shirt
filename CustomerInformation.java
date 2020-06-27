/**
 * A collection of a customer's information
 * @author CalvinHolt
 */
public class CustomerInformation 
{
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private int zip;
	private String phoneNumber;
	private String email;
	private String password;
	private String username;
	
	/**
	 * Creates a CustomerInformation object
	 * @param _firstName Customer's first name
	 * @param _lastName Customer's last name
	 * @param _streetAddressCustomer's street address
	 * @param _city Customer's city
	 * @param _state Customer's state
	 * @param _zip Customer's zip code
	 * @param _phoneNumber Customer's phone number
	 * @param _email Customer's email address
	 * @param _password Customer's password
	 * @param _username Customer's username
	 */
	public CustomerInformation(String _firstName, String _lastName, String _streetAddress, 
			String _city, String _state, int _zip, String _phoneNumber, String _email, String _password, 
			String _username) {  }
	
	
	public String getFirstName() {
		return firstName;
	}
	public boolean setFirstName(String firstName) {
		return true;
	}
	public String getLastName() {
		return lastName;
	}
	public boolean setLastName(String lastName) {
		return true;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public boolean setStreetAddress(String streetAddress) {
		return true;
	}
	public String getCity() {
		return city;
	}
	public boolean setCity(String city) {
		return true;
	}
	public String getState() {
		return state;
	}
	public boolean setState(String state) {
		return true;
	}
	public int getZip() {
		return zip;
	}
	public boolean setZip(int zip) {
		return true;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public boolean setPhoneNumber(String phoneNumber) {
		return true;
	}
	public String getEmail() {
		return email;
	}
	public boolean setEmail(String email) {
		return true;
	}
	public String getPassword() {
		return password;
	}
	public boolean setPassword(String password) {
		return true;
	}
	public String getUsername() {
		return username;
	}
	public boolean setUsername(String username) {
		return true;
	}
	
	@Override
	public String toString() { return new String(); }
}
