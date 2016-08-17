package uk.co.malleusconsulting.magnolia.ui.form.validator;

import info.magnolia.ui.form.validator.definition.ConfiguredFieldValidatorDefinition;

import java.util.ArrayList;
import java.util.List;

public class UniquenessValidatorDefinition extends
		ConfiguredFieldValidatorDefinition {

	private List<String> validateAgainst = new ArrayList<String>();

	public UniquenessValidatorDefinition() {
		setFactoryClass(UniquenessValidatorFactory.class);
	}

	public List<String> getValidateAgainst() {
		return validateAgainst;
	}

	public void setValidateAgainst(List<String> validateAgainst) {
		this.validateAgainst = validateAgainst;
	}
}
