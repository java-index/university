package com.smarterama.university.domain.personal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smarterama.university.domain.Model;
import com.smarterama.university.ui.CustomDateSerializer;
import com.smarterama.university.ui.validation.Phone;

public abstract class Person extends Model {

	private String firstName;

	private String lastName;
	
	private java.util.Date birthDate;

	private Gender sex;
	
	private String tel;

	private String note;
	
	private byte[] photo;

	@NotEmpty(message = "{validation.notempty.message}")
	@Size(min = 2, max = 55, message = "{validation.firstname.size.message}")
	@Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "{validation.pattern.name.message}")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotEmpty(message = "{validation.notempty.message}")
	@Size(min = 2, max = 55, message = "{validation.lastname.size.message}")
	@Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$", message = "{validation.pattern.name.message}")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	@NotNull(message = "{validation.notempty.message}")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Past(message = "{validation.past.message}")
	public java.util.Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Gender getSex() {
		return sex;
	}
	
	public void setSex(Gender sex) {
		this.sex = sex;
	}

	@NotEmpty(message = "{validation.notempty.message}")
	@Phone(message = "{validation.phoneformat.message}")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Size(min = 0, max = 255, message = "{validation.note.size.message}")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@JsonIgnore
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}