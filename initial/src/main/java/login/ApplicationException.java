package login;

public class ApplicationException extends Exception{
	
	private String message;
	
	public ApplicationException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}
	
	

}
