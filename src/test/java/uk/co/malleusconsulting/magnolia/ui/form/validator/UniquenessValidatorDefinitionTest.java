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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static com.google.gwt.thirdparty.guava.common.collect.Lists.newArrayList;

public class UniquenessValidatorDefinitionTest {

	@Test
	public void DefinitionGetsAndSetsListOfFields() {
		List<String> validateAgainst = newArrayList("a", "b", "c");
		UniquenessValidatorDefinition definition = new UniquenessValidatorDefinition();
		definition.setValidateAgainst(validateAgainst);

		assertThat(definition.getValidateAgainst(), is(validateAgainst));
	}

	@Test
	public void DefinitionDefaultsToEmptyListOfFields() {
		UniquenessValidatorDefinition definition = new UniquenessValidatorDefinition();

		assertThat(definition.getValidateAgainst(),
				is((List<String>) new ArrayList<String>()));
	}
}
