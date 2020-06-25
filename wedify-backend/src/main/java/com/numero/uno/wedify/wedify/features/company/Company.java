package com.numero.uno.wedify.wedify.features.company;

import com.numero.uno.wedify.wedify.features.user.User;
import com.numero.uno.wedify.wedify.features.usertype.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Company")
public class Company extends User {

	public Company(Long id, String email, String password, UserType userType, String name, String street,
				   String houseNumber, String postalCode, String city, String phoneNumber, String kvk,
				   String website) {
		super(id, email, password, userType);
		this.name = name;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.kvk = kvk;
		this.website = website;
	}

	@Basic
	@Column(name = "name", nullable = false, length = 45)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String name;

	@Basic
	@Column(name = "street", nullable = false, length = 45)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String street;

	@Basic
	@Column(name = "house_number", nullable = false, length = 45)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String houseNumber;

	@Basic
	@Column(name = "postal_code", nullable = false, length = 7)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 7, groups = {Create.class, Update.class})
	private String postalCode;

	@Basic
	@Column(name = "city", nullable = false, length = 45)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String city;

	@Basic
	@Column(name = "phone_number", nullable = false, length = 45)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String phoneNumber;

	@Basic
	@Column(name = "kvk", nullable = false, length = 45, unique = true)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String kvk;

	@Basic
	@Column(name = "website", nullable = true, length = 45)
	@Length(max = 45, groups = {Create.class, Update.class})
	private String website;

}
