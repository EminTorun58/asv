package com.numero.uno.wedify.wedify.features.facility.validation;

import com.numero.uno.wedify.wedify.features.venueroom.validation.VenueRoomGuestsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = { VenueRoomGuestsValidator.class })
public @interface FacilityGuests {
	String message() default "{validation.constraints.Guests.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
