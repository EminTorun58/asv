package com.numero.uno.wedify.wedify.features.facility;

import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.facility.validation.FacilityGuests;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Facility")
@Inheritance(strategy = InheritanceType.JOINED)
@FacilityGuests
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@NotNull(groups = MandatoryId.class)
	@Null(groups = Create.class)
	private Long id;

	@Basic
	@Column(name = "name", nullable = false, length = 45, unique = true)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String name;

	@Basic
	@Column(name = "description", nullable = false, length = 65535, columnDefinition = "Text")
	@NotBlank(groups = {Create.class, Update.class})
	private String description;

	@Basic
	@Column(name = "pricing_information", nullable = true, length = 65535, columnDefinition = "Text")
	private String pricingInformation;

	@Basic
	@Column(name = "min_guests", nullable = true)
	private Integer minGuests;

	@Basic
	@Column(name = "max_guests", nullable = true)
	private Integer maxGuests;

	@Basic
	@ManyToOne
	@JoinColumn(name = "facility_type", nullable = false)
	private FacilityType facilityType;

	@Basic
	@Column(name = "image")
	private String image;


	@Basic
	@ManyToOne
	@JoinColumn(name = "company", nullable = false)
	@NotNull(groups = Create.class)
	@Valid
	private Company company;

	public interface Create {
	}

	public interface Update {
	}

	public interface MandatoryId {
	}

}
