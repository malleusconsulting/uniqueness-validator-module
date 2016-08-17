package uk.co.malleusconsulting.magnolia.ui.form.validator;

import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.ui.vaadin.integration.jcr.JcrNodeAdapter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.thirdparty.guava.common.collect.ImmutableList;
import com.vaadin.data.Item;
import com.vaadin.data.validator.AbstractStringValidator;

public class UniquenessValidator extends AbstractStringValidator {

	private static final long serialVersionUID = -6263226949565478768L;

	private static final Logger LOG = LoggerFactory
			.getLogger(UniquenessValidator.class);

	private final Item item;
	private final List<String> validateAgainst;

	public UniquenessValidator(Item item, List<String> validateAgainst,
			String errorMessage) {
		super(errorMessage);
		this.item = item;
		this.validateAgainst = ImmutableList.copyOf(validateAgainst);
	}

	@Override
	protected boolean isValidValue(String value) {

		if (item instanceof JcrNodeAdapter) {

			// Null values are considered acceptable.
			// If fields are required, the built in validation will catch this.
			if (value == null) {
				LOG.debug("Will not check uniqueness of nulls");
				return true;
			}

			for (String potentialClashingProperty : validateAgainst) {

				// If this validator is not set up correctly, it should validate
				// as best it can. If left unchecked, a NullPointerException
				// here will require the entire parent app to be restarted.
				if (item.getItemProperty(potentialClashingProperty) == null) {
					LOG.warn(
							"Incorrectly setup for editing item {}. No such dialog field as {}",
							getNodePathIfPossible(item),
							potentialClashingProperty);
					continue;
				}

				if (value.equals(item
						.getItemProperty(potentialClashingProperty).getValue())) {
					LOG.debug("Value ({}) is equal to field {} ({})", value,
							potentialClashingProperty,
							item.getItemProperty(potentialClashingProperty)
									.getValue());
					return false;
				}
			}
			return true;
		}

		// Cannot validate against anything other than a JCR item
		return false;
	}

	private String getNodePathIfPossible(Item item) {
		return NodeUtil.getPathIfPossible(((JcrNodeAdapter) item).getJcrItem());
	}
}
