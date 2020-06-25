package com.numero.uno.wedify.wedify.features.venueroom;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.numero.uno.wedify.wedify.features.venue.Venue;
import com.numero.uno.wedify.wedify.features.venueroom.validation.VenueRoomGuests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data

@AllArgsConstructor
@ToString(exclude = {"venue"})
@NoArgsConstructor
@Entity
@Table(name = "VenueRoom")
@VenueRoomGuests(groups = {VenueRoom.Create.class, VenueRoom.Update.class})
public class VenueRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@Null(groups = {Create.class, NullId.class})
	private Long id;

	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venue", nullable = false)
	@JsonBackReference
	private Venue venue;

	@Basic
	@Column(name = "name", nullable = false, length = 45)
	@NotBlank(groups = {Create.class, Update.class})
	@Length(max = 45, groups = {Create.class, Update.class})
	private String name;

	@Basic
	@Column(name = "min_guests", nullable = true)
	private Integer minGuests;

	@Basic
	@Column(name = "max_guests", nullable = true)
	private Integer maxGuests;

	@Basic
	@Column(name = "square_meter", nullable = true)
	private Double squareMeter;

	public interface Create {
	}

	public interface Update {
	}

	public interface NullId {
	}

}
