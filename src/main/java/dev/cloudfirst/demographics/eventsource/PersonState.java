package dev.cloudfirst.demographics.eventsource;

import org.infinispan.protostream.annotations.ProtoField;

public class PersonState {
    public String firstName;
    public String lastName;
    public String emailAddress;
    @Override
    public String toString() {
        return "PersonState [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress
                + "]";
    }

    public PersonState() {}

    @ProtoField(number = 1)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

    @ProtoField(number = 2)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    @ProtoField(number = 3)
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

    
}
