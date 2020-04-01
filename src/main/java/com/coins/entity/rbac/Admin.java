package com.coins.entity.rbac;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table admins
 */
public class Admin {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.id
	 * @mbg.generated
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.section_id
	 * @mbg.generated
	 */
	private Integer sectionId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.name
	 * @mbg.generated
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.realname
	 * @mbg.generated
	 */
	private String realname;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.email
	 * @mbg.generated
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.password
	 * @mbg.generated
	 */
	private String password;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.crypt
	 * @mbg.generated
	 */
	private String crypt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.phone
	 * @mbg.generated
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.lasttime
	 * @mbg.generated
	 */
	private Date lasttime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.lastip
	 * @mbg.generated
	 */
	private String lastip;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.status
	 * @mbg.generated
	 */
	private Byte status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.created_at
	 * @mbg.generated
	 */
	private Date createdAt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column admins.updated_at
	 * @mbg.generated
	 */
	private Date updatedAt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.id
	 * @return  the value of admins.id
	 * @mbg.generated
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.id
	 * @param id  the value for admins.id
	 * @mbg.generated
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.section_id
	 * @return  the value of admins.section_id
	 * @mbg.generated
	 */
	public Integer getSectionId() {
		return sectionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.section_id
	 * @param sectionId  the value for admins.section_id
	 * @mbg.generated
	 */
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.name
	 * @return  the value of admins.name
	 * @mbg.generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.name
	 * @param name  the value for admins.name
	 * @mbg.generated
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.realname
	 * @return  the value of admins.realname
	 * @mbg.generated
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.realname
	 * @param realname  the value for admins.realname
	 * @mbg.generated
	 */
	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.email
	 * @return  the value of admins.email
	 * @mbg.generated
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.email
	 * @param email  the value for admins.email
	 * @mbg.generated
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.password
	 * @return  the value of admins.password
	 * @mbg.generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.password
	 * @param password  the value for admins.password
	 * @mbg.generated
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.crypt
	 * @return  the value of admins.crypt
	 * @mbg.generated
	 */
	public String getCrypt() {
		return crypt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.crypt
	 * @param crypt  the value for admins.crypt
	 * @mbg.generated
	 */
	public void setCrypt(String crypt) {
		this.crypt = crypt == null ? null : crypt.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.phone
	 * @return  the value of admins.phone
	 * @mbg.generated
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.phone
	 * @param phone  the value for admins.phone
	 * @mbg.generated
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.lasttime
	 * @return  the value of admins.lasttime
	 * @mbg.generated
	 */
	public Date getLasttime() {
		return lasttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.lasttime
	 * @param lasttime  the value for admins.lasttime
	 * @mbg.generated
	 */
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.lastip
	 * @return  the value of admins.lastip
	 * @mbg.generated
	 */
	public String getLastip() {
		return lastip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.lastip
	 * @param lastip  the value for admins.lastip
	 * @mbg.generated
	 */
	public void setLastip(String lastip) {
		this.lastip = lastip == null ? null : lastip.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.status
	 * @return  the value of admins.status
	 * @mbg.generated
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.status
	 * @param status  the value for admins.status
	 * @mbg.generated
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.created_at
	 * @return  the value of admins.created_at
	 * @mbg.generated
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.created_at
	 * @param createdAt  the value for admins.created_at
	 * @mbg.generated
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column admins.updated_at
	 * @return  the value of admins.updated_at
	 * @mbg.generated
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column admins.updated_at
	 * @param updatedAt  the value for admins.updated_at
	 * @mbg.generated
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admins
	 * @mbg.generated
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		Admin other = (Admin) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getSectionId() == null ? other.getSectionId() == null
						: this.getSectionId().equals(other.getSectionId()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
				&& (this.getRealname() == null ? other.getRealname() == null
						: this.getRealname().equals(other.getRealname()))
				&& (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
				&& (this.getPassword() == null ? other.getPassword() == null
						: this.getPassword().equals(other.getPassword()))
				&& (this.getCrypt() == null ? other.getCrypt() == null : this.getCrypt().equals(other.getCrypt()))
				&& (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
				&& (this.getLasttime() == null ? other.getLasttime() == null
						: this.getLasttime().equals(other.getLasttime()))
				&& (this.getLastip() == null ? other.getLastip() == null : this.getLastip().equals(other.getLastip()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getCreatedAt() == null ? other.getCreatedAt() == null
						: this.getCreatedAt().equals(other.getCreatedAt()))
				&& (this.getUpdatedAt() == null ? other.getUpdatedAt() == null
						: this.getUpdatedAt().equals(other.getUpdatedAt()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table admins
	 * @mbg.generated
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getSectionId() == null) ? 0 : getSectionId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		result = prime * result + ((getCrypt() == null) ? 0 : getCrypt().hashCode());
		result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = prime * result + ((getLasttime() == null) ? 0 : getLasttime().hashCode());
		result = prime * result + ((getLastip() == null) ? 0 : getLastip().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
		result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
		return result;
	}
}