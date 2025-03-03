package assessment.socialnetwork;
public class User {
    private String uId;
    private String name;
    private String eMail;

    public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String userId, String name, String email) {
        this.uId = userId;
        this.name = name;
        this.eMail = email;
    }

 
    public String getName() { 
    	return name; 
    }
    
}
