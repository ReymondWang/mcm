package com.purplelight.mcm.api.bean;

import java.util.List;

public class BindUser {
	private String UserId;

    private String Name;

    private String Email;

    private String Gender;

    private String Token;
    
    private List<String> Rights;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

	public List<String> getRights() {
		return Rights;
	}

	public void setRights(List<String> rights) {
		Rights = rights;
	}
}
