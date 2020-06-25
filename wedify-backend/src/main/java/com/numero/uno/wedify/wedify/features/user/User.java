package com.numero.uno.wedify.wedify.features.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.numero.uno.wedify.wedify.features.usertype.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@NotNull(groups = MandatoryId.class)
	@Null(groups = {Create.class})
	private Long id;

	@Basic
	@Column(name = "email", nullable = false, length = 45, unique = true)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	@Email(groups = {Create.class, Update.class})
	private String email;

	@Basic
	@Column(name = "password", nullable = false, columnDefinition = "LONGTEXT")
	@NotBlank(groups = Create.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Basic
	@ManyToOne
	@JoinColumn(name = "user_type", nullable = false)
	private UserType userType;

	public interface Create {
	}

	public interface MandatoryId {
	}

	public interface Update {

	}

}
