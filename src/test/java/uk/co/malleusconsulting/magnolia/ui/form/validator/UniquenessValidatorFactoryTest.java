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
