package com.ahbap;

public class UserDetail {
	private Education education;
	private Job job;
	private String gender;
	private String userDetailId;
	
	public UserDetail(String userDetailId) {
		this.userDetailId = userDetailId;
	}
	public String getUserDetailId() {
		return userDetailId;
	}
	public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	

}
