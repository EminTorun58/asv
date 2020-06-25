package com.numero.uno.wedify.wedify.features.venueroom.validation;

import com.numero.uno.wedify.wedify.features.venueroom.VenueRoom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VenueRoomGuestsValidator implements ConstraintValidator<VenueRoomGuests, VenueRoom> {

	@Override
	public void initialize(VenueRoomGuests constraintAnnotation) {
	}

	@Override
	public boolean isValid(VenueRoom venueRoom, ConstraintValidatorContext constraintValidatorContext) {
		if (venueRoom.getMinGuests() == null || venueRoom.getMaxGuests() == null) {
			return true;
		}

		return venueRoom.getMinGuests() <= venueRoom.getMaxGuests();
	}
}
