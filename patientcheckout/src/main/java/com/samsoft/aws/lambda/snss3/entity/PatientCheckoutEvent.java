/**
 * 
 */
package com.samsoft.aws.lambda.snss3.entity;

/**
 * @author SAMARESH
 *
 */
public class PatientCheckoutEvent {

	private String firstName;
	private String lastName;
	private String ssn;

	public PatientCheckoutEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientCheckoutEvent(String firstName, String lastName, String ssn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "PatientCheckoutEvent [firstName=" + firstName + ", lastName=" + lastName + ", ssn=" + ssn + "]";
	}

}
