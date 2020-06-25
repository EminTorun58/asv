package com.numero.uno.wedify.wedify.features.image;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.numero.uno.wedify.wedify.features.facility.Facility;
import com.numero.uno.wedify.wedify.features.user.User;
import com.numero.uno.wedify.wedify.features.venue.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FacilityImage")
public class FacilityImage {



	@Id
	@Column(name = "id", nullable = false)
	@NotNull(groups = MandatoryId.class)
	private Long id;

	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "facility", nullable = false)
	@JsonBackReference
	private Facility facility;

	@Basic
	@Column(name = "title")
	@Length(max = 255)
	private String title;

	@Basic
	@Column(name = "path", nullable = false, length = 65535, columnDefinition = "Text")
	@NotBlank(groups = Create.class)
	@Length(max = 500)
	private String path;


	public interface Create {}

	public interface MandatoryId {}


}
