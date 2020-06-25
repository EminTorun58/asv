package com.numero.uno.wedify.wedify.features.facility.validation;

import com.numero.uno.wedify.wedify.features.facility.Facility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FacilityGuestsValidator implements ConstraintValidator<FacilityGuests, Facility> {

	@Override
	public void initialize(FacilityGuests constraintAnnotation) {
		// Do nothing because this is a Singleton
	}

	@Override
	public boolean isValid(Facility facility, ConstraintValidatorContext constraintValidatorContext) {
		if (facility.getMinGuests() == null || facility.getMaxGuests() == null) {
			return true;
		}

		return facility.getMinGuests() <= facility.getMaxGuests();
	}
}
