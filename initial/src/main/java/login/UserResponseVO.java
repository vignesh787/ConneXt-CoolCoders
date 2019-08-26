package login;

public class UserResponseVO {
	
	private String userId;
	private boolean requestStatus;
	private boolean changePassword;
	private String requestType;
	private String sessionId;
	private String errorMessage;
	
	
	public boolean isChangePassword() {
		return changePassword;
	}
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(boolean requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	@Override
	public String toString() {
		return "UserResponseVO [userId=" + userId + ", requestStatus=" + requestStatus + ", changePassword="
				+ changePassword + ", requestType=" + requestType + ", sessionId=" + sessionId + ", errorMessage="
				+ errorMessage + "]";
	}
	public UserResponseVO(String userId, boolean requestStatus, boolean changePassword, String requestType,
			String sessionId, String errorMessage) {
		super();
		this.userId = userId;
		this.requestStatus = requestStatus;
		this.changePassword = changePassword;
		this.requestType = requestType;
		this.sessionId = sessionId;
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public UserResponseVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
