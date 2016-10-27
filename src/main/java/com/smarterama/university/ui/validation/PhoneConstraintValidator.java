package com.smarterama.university.ui.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstraintValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String phoneField, ConstraintValidatorContext context) {
		if (phoneField == null) {
			return false;
		}
		// +XX (XXX) XXX-XX-XX
		return phoneField.matches("^[\\+{1}][0-9]{2}\\s\\([0-9]{3}\\)\\s([0-9]{3})-([0-9]{2})-([0-9]{2})$");
	}
}