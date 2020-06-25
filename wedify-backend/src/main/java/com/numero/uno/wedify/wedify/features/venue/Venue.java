package com.numero.uno.wedify.wedify.features.venue;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.numero.uno.wedify.wedify.features.company.Company;
import com.numero.uno.wedify.wedify.features.facilitytype.FacilityType;
import com.numero.uno.wedify.wedify.features.venueroom.VenueRoom;
import com.numero.uno.wedify.wedify.features.facility.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Venue")
public class Venue extends Facility {

	public Venue(@NotNull(groups = MandatoryId.class) @Null(groups = Create.class) Long id, @NotBlank(groups = Create.class) @Length(max = 45, groups = Create.class) String name, @NotBlank(groups = Create.class) String description, String pricingInformation, Integer minGuests, Integer maxGuests, FacilityType facilityType, String image, @NotNull(groups = Create.class) @Valid Company company, @NotBlank(groups = Create.class) @Length(max = 45, groups = Create.class) String street, @NotBlank(groups = Create.class) @Length(max = 45, groups = Create.class) String houseNumber, @NotBlank(groups = Create.class) @Length(max = 7, groups = Create.class) String postalCode, @NotBlank(groups = Create.class) @Length(max = 45, groups = Create.class) String city, @Valid List<VenueRoom> rooms) {
		super(id, name, description, pricingInformation, minGuests, maxGuests, facilityType, image, company);
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.rooms = rooms;
	}

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

	@OneToMany(mappedBy = "venue", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<VenueRoom> rooms;

}
