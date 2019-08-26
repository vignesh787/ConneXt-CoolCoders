package login;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import login.UserVO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class LoginController {
    
	/**
	 * TO Externalize for dynamic file location;
	 */
	static final String userNamePwdFile = "/users/vigneshr/gs-spring-boot/initial/src/main/resources/UserNamePassword.properties";
	static final String userIdFile ="/users/vigneshr/gs-spring-boot/initial/src/main/resources/UserDetails.properties";
	
    @RequestMapping("/login")    
    public @ResponseBody UserResponseVO index(@RequestBody UserVO userVO,HttpServletRequest request,HttpSession httpSession) throws Exception {
    
    	
    		System.out.println(userVO);

    		boolean isPasswordChangeReqd = false;
    		boolean responseStatus=false;
			try {
				responseStatus = validateUser(userVO.getUserid(),userVO.getPassword());
			} catch (Exception e) {
				System.out.println(" l*****"+e.getMessage());
				System.out.println("I:"+e);
				return new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"login","sessionId",e.toString());
			}
    		if(responseStatus) {
    			isPasswordChangeReqd = isPasswordChangeRequire(userVO,request,httpSession);
    		}
    		return new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"login","sessionId",null);
    	
    	
    	
       // return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/loginOM")    
    //public @ResponseBody UserResponseVO indexing(@RequestBody String userInfo,HttpServletRequest request,HttpSession httpSession) throws Exception {
    	public String indexing(@RequestBody String userInfo,HttpServletRequest request,HttpSession httpSession) throws Exception {
    		
    		System.out.println(" userInfo : "+userInfo);
    	
    		ObjectMapper mapper = new ObjectMapper();
    		UserVO userVO = mapper.readValue(userInfo, UserVO.class);
    		System.out.println(userVO);
    		
//    		System.out.println(userVO);

    		boolean isPasswordChangeReqd = false;
    		boolean responseStatus=false;
			try {
				responseStatus = validateUser(userVO.getUserid(),userVO.getPassword());
			} catch (Exception e) {
				System.out.println(" l*****"+e.getMessage());
				System.out.println("I:"+e);
				return mapper.writeValueAsString(new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"login","sessionId",e.toString()));
				//return new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"login","sessionId",e.toString());
				
			}
    		if(responseStatus) {
    			isPasswordChangeReqd = isPasswordChangeRequire(userVO,request,httpSession);
    		}
    		return mapper.writeValueAsString(new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"login","sessionId",null));
    		//return new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"login","sessionId",null);
    	
    	
    	
       // return "Greetings from Spring Boot!";
    }
    
    @RequestMapping("/checkpwdchgOM")  
    private static boolean isPasswordChangeRequire(@RequestBody UserVO userVO,HttpServletRequest request,HttpSession httpSession) throws Exception{
    	String userName = userVO.getUserid();
		FileInputStream in = new FileInputStream(userNamePwdFile);
        Properties userPwdProps = new Properties();
        userPwdProps.load(in);
        in.close();
        
        
        String pwdFromFile = userPwdProps.getProperty(userName);
        
        if(pwdFromFile == null) {
        	return true;
        } 
        return false;
	
}
    
    
    
    private static boolean validateUser(String userName, String password) throws Exception{
			FileInputStream in = new FileInputStream(userNamePwdFile);
	        Properties userPwdProps = new Properties();
	        userPwdProps.load(in);
	        in.close();
	        
	        FileInputStream userIn = new FileInputStream(userIdFile);
	        Properties usersProps = new Properties();
	        usersProps.load(userIn);
	        userIn.close();
	        
	        String s = usersProps.getProperty(userName);
	        String pwdFromFile = userPwdProps.getProperty(userName);
	        
	        if(s == null) {
	        	throw new ApplicationException("Invalid User");
	        } else {
	        	if(Integer.parseInt(s) == 0) {// todo to check based on websesssion
	        		
	        		if(pwdFromFile == null) {
	        			System.out.println("First Time Login");
		        		if(userName.equals(password)) {// Password to be decoded
		        			System.out.println("Login Success"); // Asked to change password, Userid to be stored in webserver session
		        			return true;
		        		} else {
		        			System.out.println("Login Failure");
		        			throw new ApplicationException("Invalid Username/Password");
		        		}
	        		} else {
	        			System.out.println("Existing User");
		        		if(password.equals(userPwdProps.getProperty(userName))) {
		        			System.out.println("Existing User Login Success"); // if userid available in session then should not allow to login 
		        			int count = Integer.parseInt(s)+1;
		        		//	addUser(userName, count);
		        			return true;
		        		} else {
		        			System.out.println("Existing User Login Failure");
		        			throw new ApplicationException("Invalid Username/Password");
		        		}
	        		}
	        		
	        		
	        	} else {
        			System.out.println("Already Logedin");
        			throw new ApplicationException("User Already Logged In");
	        		
	        	}
	        }
	        
		
	}
    @RequestMapping("/pwdchg")
    private static UserResponseVO ChangePassword(@RequestBody UserVO userVO,HttpServletRequest request,HttpSession httpSession) {
    	
    	String userName = userVO.getUserid();
    	String password = userVO.getPassword();
    	
		boolean isPasswordChangeReqd = false;
		boolean responseStatus=false;
    	
		try {
			FileInputStream in = new FileInputStream(userNamePwdFile);
	        Properties props = new Properties();
	        props.load(in);
	        in.close();

	        FileOutputStream out = new FileOutputStream(userNamePwdFile);
	        props.setProperty(userName, password);
	        props.store(out, null);
	        out.close();
	     //   addUser(userName, 1);
	        responseStatus = true;
		} catch(Exception e) {
			return new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"pwdchg","sessionId",e.toString());
		}
		return new UserResponseVO(userVO.getUserid(),responseStatus,isPasswordChangeReqd,"pwdchg","sessionId",null);
	}
	
    
}
