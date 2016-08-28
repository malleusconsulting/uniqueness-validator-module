package uk.co.malleusconsulting.magnolia.ui.vaadin.form;

import org.apache.commons.lang3.StringUtils;

import info.magnolia.ui.vaadin.form.Form;
import info.magnolia.ui.vaadin.form.FormSection;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

public class UniquenessForm extends Form {

	@Override
	public boolean isValid() {
		boolean res = true;
		getState().errorAmount = 0;

		boolean isFieldValid = true;

		for (final FormSection formSection : getFormSections()) {
			for (final Component fieldComponent : formSection) {

				// If this field has validators, use them to decide to decide on
				// error count by looking for error messages
				if (((Field<?>) fieldComponent).getValidators().size() > 0) {
					for (Validator validator : ((Field<?>) fieldComponent)
							.getValidators()) {
						if (validator instanceof AbstractValidator) {
							isFieldValid = StringUtils
									.isNotEmpty(((AbstractValidator<?>) validator)
											.getErrorMessage());
						}
					}
				} else {
					isFieldValid = ((Field<?>) fieldComponent).isValid();
				}
			}

			if (!isFieldValid) {
				++getState().errorAmount;
			}
			res &= isFieldValid;
		}
		return res;
	}
}
