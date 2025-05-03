package com.edu.bits.cloud.bank.model;

import java.util.ArrayList;
import java.util.List;


public class CustomerModel {
 private Integer customerID;
 private String name;
 private Integer age;
 private String country;
 private String state;
 private String district;
 private String pincode;
 private String address;
 private String gender;
 private List<AccountModel> accounts;  // instead of full Account entity details
public Integer getCustomerID() {
	return customerID;
}
public void setCustomerID(Integer customerID) {
	this.customerID = customerID;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public List<AccountModel> getAccounts() {
	if(null!=accounts) {
		return accounts;
	}else {
		return new ArrayList<AccountModel>();
	}	
}
public void setAccounts(List<AccountModel> accounts) {
	this.accounts = accounts;
}

}


