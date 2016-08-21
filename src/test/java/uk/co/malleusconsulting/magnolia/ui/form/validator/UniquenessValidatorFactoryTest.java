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
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import info.magnolia.ui.vaadin.integration.jcr.JcrNodeAdapter;

import java.util.List;

import org.junit.Test;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;

public class UniquenessValidatorFactoryTest {

	@Test
	public void factoryPassesGivenParametersToValidator() {
		final String errorMessage = "errorMessage";
		final List<String> validateAgainst = ImmutableList.of("a", "b");

		JcrNodeAdapter item = mock(JcrNodeAdapter.class);

		UniquenessValidatorDefinition definition = mock(UniquenessValidatorDefinition.class);
		when(definition.getValidateAgainst()).thenReturn(validateAgainst);
		when(definition.getErrorMessage()).thenReturn(errorMessage);

		UniquenessValidatorFactory factory = new UniquenessValidatorFactory(
				definition, item);

		assertThat(factory.createValidator(),
				is(instanceOf(UniquenessValidator.class)));
	}
}
