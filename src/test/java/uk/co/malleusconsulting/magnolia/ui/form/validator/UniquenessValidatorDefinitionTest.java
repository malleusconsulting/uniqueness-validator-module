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
