package login;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class UpdateProperty {
	

	public static void main(String args[]) throws Exception {   
        
		//addUser("1599737",0);
		//addUser("1599738",0);
		
		//removeUser("999999");
		
		//ChangePassword("1599734", "passwordTest");
		
		//validateUser("1599738","1599738");
		validateUser("1599734","passwordTest");
		
		
		System.out.println("Done");
    }
	
	private static void addUser(String userName, int count) {
		try {
			FileInputStream in = new FileInputStream("UserDetails.properties");
	        Properties props = new Properties();
	        props.load(in);
	        in.close();

	        FileOutputStream out = new FileOutputStream("UserDetails.properties");
	        props.setProperty(userName, String.valueOf(count));
	        props.store(out, null);
	        out.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void removeUser(String userName) {
		try {
			FileInputStream in = new FileInputStream("UserDetails.properties");
	        Properties props = new Properties();
	        props.load(in);
	        in.close();

	        FileOutputStream out = new FileOutputStream("UserDetails.properties");
	        props.remove(userName);
	        props.store(out, null);
	        out.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void ChangePassword(String userName, String password) {
		try {
			FileInputStream in = new FileInputStream("UserNamePassword.properties");
	        Properties props = new Properties();
	        props.load(in);
	        in.close();

	        FileOutputStream out = new FileOutputStream("UserNamePassword.properties");
	        props.setProperty(userName, password);
	        props.store(out, null);
	        out.close();
	        addUser(userName, 1);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static boolean validateUser(String userName, String password) {
		try {
			FileInputStream in = new FileInputStream("UserNamePassword.properties");
	        Properties userPwdProps = new Properties();
	        userPwdProps.load(in);
	        in.close();
	        
	        FileInputStream userIn = new FileInputStream("UserDetails.properties");
	        Properties usersProps = new Properties();
	        usersProps.load(userIn);
	        userIn.close();
	        
	        String s = usersProps.getProperty(userName);
	        if(s == null) {
	        	System.out.println("Invalid User");
	        } else {
	        	if(Integer.parseInt(s) == 0) {
	        		System.out.println("First Time Login");
	        		if(userName.equals(password)) {// Password to be decoded
	        			System.out.println("Login Success"); // Asked to change password, Userid to be stored in webserver session
	        		} else {
	        			System.out.println("Login Failure");
	        		}
	        	} else {
        			System.out.println("Existing User");
	        		if(password.equals(userPwdProps.getProperty(userName))) {
	        			System.out.println("Existing User Login Success"); // if userid available in session then should not allow to login 
	        			int count = Integer.parseInt(s)+1;
	        			addUser(userName, count);
	        		} else {
	        			System.out.println("Existing User Login Failure");
	        		}
	        		
	        	}
	        }
	        
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}
	

}
