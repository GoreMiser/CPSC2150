/**
 * Authors:    William Newberry V
 *             Trevor Overfelt
 *             Jacob Collins
 *             
 * Date Last Modified:  4/27/14
 * 
 * The Configuration class is used to hold the email address of the current
 * user and his or her IP address as well as their password to login to
 * said email.  However, the password is not saved and must always be
 * reentered upon program startup.
 */

package edu.clemson.cpsc215.assignment3;

import java.io.Serializable;

/**
 * 
 * @author Trevor Overfelt, Jacob Collins, William Newberry V
 *
 */
public class Configuration extends EProfile implements Serializable {

	private static final long serialVersionUID = 4686650900750229649L;
	private String eAddr = "";
	private String ipAddr = "";
	//ipAddr is the IPAddress of the hosting email
	private transient String pWrd = "";
	private String port;
	
	//These are all just getters and setters, nbd
	public String getEAddr() {
		return this.eAddr;
	}
	
	public void setEAddr(String eAddr) {
		this.eAddr = eAddr;
	}
	
	public String getIPAddr() {
		return this.ipAddr;
	}
	
	public void setIPAddr(String iPAddr) {
		this.ipAddr = iPAddr;
	}

	public String getpWrd() {
		return pWrd;
	}

	public void setpWrd(String pWrd) {
		this.pWrd = pWrd;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
}
