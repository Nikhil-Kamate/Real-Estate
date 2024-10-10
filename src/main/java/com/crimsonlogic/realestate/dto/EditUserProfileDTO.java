package com.crimsonlogic.realestate.dto;

public class EditUserProfileDTO {
	
	 private String userdetailsId;
	    private String firstName;
	    private String lastName;
	    private String phoneNo;
	    // UserAuthentication fields
	    private String email;
	    private String password;
		public String getUserdetailsId() {
			return userdetailsId;
		}
		public void setUserdetailsId(String userdetailsId) {
			this.userdetailsId = userdetailsId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    
	    

}
