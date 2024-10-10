package com.crimsonlogic.realestate.dto;


public class BrokerWithUserDetailsDTO {
	private Long brokerId;
	 private String userdetailsId;
	    private String userdetailsFirstName;
	    private String userLastName;
	    private String userPhoneNo;
	    private Boolean isSubscribed;
	    private String userRole;
	    private Double brokerageRate;
	    private String status;
	    private String brokerageLicenseId;
	    
	    
		public BrokerWithUserDetailsDTO() {
			super();
			// TODO Auto-generated constructor stub
		}


		public BrokerWithUserDetailsDTO(String userdetailsId, String userdetailsFirstName, String userLastName,
				String userPhoneNo, Boolean isSubscribed, String userRole, Double brokerageRate, String status,
				String brokerageLicenseId,Long brokerId ) {
			super();
			this.userdetailsId = userdetailsId;
			this.userdetailsFirstName = userdetailsFirstName;
			this.userLastName = userLastName;
			this.userPhoneNo = userPhoneNo;
			this.isSubscribed = isSubscribed;
			this.userRole = userRole;
			this.brokerageRate = brokerageRate;
			this.status = status;
			this.brokerageLicenseId = brokerageLicenseId;
			this.brokerId=brokerId;
		}


		public Long getBrokerId() {
			return brokerId;
		}


		public void setBrokerId(Long brokerId) {
			this.brokerId = brokerId;
		}


		public String getUserdetailsId() {
			return userdetailsId;
		}


		public void setUserdetailsId(String userdetailsId) {
			this.userdetailsId = userdetailsId;
		}


		public String getUserdetailsFirstName() {
			return userdetailsFirstName;
		}


		public void setUserdetailsFirstName(String userdetailsFirstName) {
			this.userdetailsFirstName = userdetailsFirstName;
		}


		public String getUserLastName() {
			return userLastName;
		}


		public void setUserLastName(String userLastName) {
			this.userLastName = userLastName;
		}


		public String getUserPhoneNo() {
			return userPhoneNo;
		}


		public void setUserPhoneNo(String userPhoneNo) {
			this.userPhoneNo = userPhoneNo;
		}


		public Boolean getIsSubscribed() {
			return isSubscribed;
		}


		public void setIsSubscribed(Boolean isSubscribed) {
			this.isSubscribed = isSubscribed;
		}


		public String getUserRole() {
			return userRole;
		}


		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}


		public Double getBrokerageRate() {
			return brokerageRate;
		}


		public void setBrokerageRate(Double brokerageRate) {
			this.brokerageRate = brokerageRate;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getBrokerageLicenseId() {
			return brokerageLicenseId;
		}


		public void setBrokerageLicenseId(String brokerageLicenseId) {
			this.brokerageLicenseId = brokerageLicenseId;
		}
	    
	    
}
