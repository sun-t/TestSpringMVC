package com.sivalabs.entity;

import java.util.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="SAMPLECONTACTS")
public class Contact
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column private int id;
    @Column private String name;
    @Column private String password;
    @Column private String address;//caseNum
    @Column private Date dateBegin;
    @Column private Date dateEnd;
    @Column private Time time;
    @Column private String email;//multiRun
    @Column private String mobile;//resultRec;
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setGender(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile= mobile;
	}

	
}
