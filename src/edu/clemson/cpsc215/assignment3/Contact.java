/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The Contact class is used to store information about contacts in the 
 * ContactTableModel.
 */

package edu.clemson.cpsc215.assignment3;

import java.io.Serializable;

public class Contact extends EProfile implements Serializable {
	private static final long serialVersionUID = 107198622462521477L;
	private String name;
	private String email;
	private String address;
	private String phone;
	
	//this constructor is for de-serializing purposes only.
	public Contact() {
		this.name = "";
		this.email = "";
		this.address = "";
		this.phone = "";
	}
	
	public Contact(String _name, String _email, String _address,
			String _phone) {
		this.name = _name;
		this.email = _email;
		this.address = _address;
		this.phone = _phone;
	}
	
	//Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
