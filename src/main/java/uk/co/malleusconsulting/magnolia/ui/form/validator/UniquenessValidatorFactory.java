/**
 * This file Copyright 2016 Malleus Consulting Ltd.
 * All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
