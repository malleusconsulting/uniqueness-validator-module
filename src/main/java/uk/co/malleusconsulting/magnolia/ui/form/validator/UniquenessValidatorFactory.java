package uk.co.malleusconsulting.magnolia.ui.form.validator;

import info.magnolia.ui.form.validator.factory.AbstractFieldValidatorFactory;

import com.vaadin.data.Item;
import com.vaadin.data.Validator;

public class UniquenessValidatorFactory extends
		AbstractFieldValidatorFactory<UniquenessValidatorDefinition> {

	private Item item;

	public UniquenessValidatorFactory(UniquenessValidatorDefinition definition,
			Item item) {
		super(definition);
		this.item = item;
	}

	@Override
	public Validator createValidator() {
		return new UniquenessValidator(item, definition.getValidateAgainst(),
				getI18nErrorMessage());
	}
}
