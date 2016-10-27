package com.smarterama.university.ui.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<CorrectDate, String> {

	@Override
	public void initialize(CorrectDate constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isValid(String dateField, ConstraintValidatorContext context) {
		try {
			new SimpleDateFormat("dd-MM-yyyy").parse(dateField);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}