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

/**
 * Defines a validator that ensures members of a set of fields contain unique values. 
 */
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

	/**
	 * Sets the names of the other fields to test this validator's parent
	 * field's value against.
	 * 
	 * @param validateAgainst
	 *            A list of field names
	 */
	public void setValidateAgainst(List<String> validateAgainst) {
		this.validateAgainst = validateAgainst;
	}
}
