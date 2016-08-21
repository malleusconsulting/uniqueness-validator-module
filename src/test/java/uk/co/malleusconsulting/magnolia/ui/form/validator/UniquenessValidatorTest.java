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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import info.magnolia.test.mock.jcr.MockNode;
import info.magnolia.ui.vaadin.integration.jcr.JcrNodeAdapter;

import java.util.List;

import javax.jcr.RepositoryException;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import com.vaadin.data.util.PropertysetItem;

public class UniquenessValidatorTest {

	@Test
	public void validatorAcceptsNoClashWithAnotherFieldONode()
			throws RepositoryException {
		final String clashingValue = "clashes";
		final String clashingProperty = "clashingProperty";

		Property<String> property = mock(Property.class);
		when(property.getValue()).thenReturn("unqiueValue");

		List<String> validateAgainst = Lists.newArrayList(clashingProperty);

		JcrNodeAdapter item = mock(JcrNodeAdapter.class);
		when(item.getItemProperty(eq(clashingProperty))).thenReturn(property);

		UniquenessValidator validator = new UniquenessValidator(item,
				validateAgainst, "");

		assertThat(validator.isValid(clashingValue), is(true));
	}

	@Test
	public void validatorAcceptsNoClashWithUnnamedPropertyOnNode()
			throws RepositoryException {
		final String clashingValue = "clashes";
		final String clashingProperty = "clashingProperty";

		Property<String> propertyToValidateAgainst = mock(Property.class);
		when(propertyToValidateAgainst.getValue()).thenReturn("unqiueValue");

		Property<String> propertyToIgnore = mock(Property.class);
		when(propertyToIgnore.getValue()).thenReturn(clashingValue);

		List<String> validateAgainst = Lists.newArrayList(clashingProperty);

		JcrNodeAdapter item = mock(JcrNodeAdapter.class);

		when(item.getItemProperty(eq(clashingProperty))).thenReturn(
				propertyToValidateAgainst);
		when(item.getItemProperty(eq("propertyToIgnore"))).thenReturn(
				propertyToIgnore);

		UniquenessValidator validator = new UniquenessValidator(item,
				validateAgainst, "");

		assertThat(validator.isValid(clashingValue), is(true));
	}

	@Test
	public void validatorCatchesClashWithAnotherFieldOnNewNode()
			throws RepositoryException {
		final String clashingValue = "clashes";
		final String clashingProperty = "clashingProperty";

		Property<String> propertyToValidateAgainst = mock(Property.class);
		when(propertyToValidateAgainst.getValue()).thenReturn(clashingValue);

		List<String> validateAgainst = Lists.newArrayList(clashingProperty);

		JcrNodeAdapter item = mock(JcrNodeAdapter.class);

		when(item.getItemProperty(eq(clashingProperty))).thenReturn(
				propertyToValidateAgainst);

		UniquenessValidator validator = new UniquenessValidator(item,
				validateAgainst, "");

		assertThat(validator.isValid(clashingValue), is(false));
	}

	@Test
	public void validatorDoesNotDetectClashWhenValuesAreNull() {
		final String clashingProperty = "clashingProperty";

		Property<String> propertyToValidateAgainst = mock(Property.class);
		when(propertyToValidateAgainst.getValue()).thenReturn(null);

		List<String> validateAgainst = Lists.newArrayList(clashingProperty);

		JcrNodeAdapter item = mock(JcrNodeAdapter.class);

		when(item.getItemProperty(eq(clashingProperty))).thenReturn(
				propertyToValidateAgainst);

		UniquenessValidator validator = new UniquenessValidator(item,
				validateAgainst, "");

		assertThat(validator.isValid(null), is(true));
	}

	@Test
	public void validatorDoesNotDetectClashWhenIncorrectlyConfigured() {
		final String clashingProperty = "clashingProperty";

		List<String> validateAgainst = Lists.newArrayList(clashingProperty);

		JcrNodeAdapter item = mock(JcrNodeAdapter.class);

		when(item.getItemProperty(eq(clashingProperty))).thenReturn(null);

		// Logging will retrieve node to log path
		when(item.getJcrItem()).thenReturn(new MockNode());

		UniquenessValidator validator = new UniquenessValidator(item,
				validateAgainst, "");

		assertThat(validator.isValid("any value"), is(true));
	}

	@Test
	public void validatorWillFailOnAnythingOtherThanAJcrNodeAdaptor() {
		final String clashingProperty = "clashingProperty";

		List<String> validateAgainst = Lists.newArrayList(clashingProperty);

		PropertysetItem vaadinItem = mock(PropertysetItem.class);

		UniquenessValidator validator = new UniquenessValidator(vaadinItem,
				validateAgainst, "");

		assertThat(validator.isValid("any value"), is(false));
	}
}
