# uniqueness-validator-module
## Description
This module provides an additional validator for Magnolia dialog fields that compares a given value to the value set for other fields in the same dialog and ensures all those compared are unique.

An example use case for this validator is a component on a category landing page of a news site. The component will provide links to the latest articles within that category but editors may override this behaviour to force one or more links into the results. If the editor accidentally linked to the same story twice, the user experience would be damaged so this validator ensures that is not possible.

## Installation
Once v1 is released this module will be available via [the central Maven repository](http://repo1.maven.org/maven2/uk/co/malleusconsulting/). Until then, it must be built from source code.

## Usage
The validator is configured like any other by adding a node pointing to `uk.co.malleusconsulting.magnolia.ui.form.validator.UniquenessValidatorDefinition`. The definition takes a list parameter, `validateAgainst`, that contains the names of the fields to check against.

In the example below, *link01* will be validated against *link02* and *link03*. Because validators are called in isolation, the configuration must be reciprocal. ie. *link02* must reference *link01* and *link03* in `validateAgainst`. The relationship is not inferred.

<img src="https://raw.githubusercontent.com/malleusconsulting/uniqueness-validator-module/gh_pages/validator_configuration.png" width="835" height="559" title="Dialog using a uniqueness validator" />

## Issues and Limitations
### Reporting total errors in a dialog
The process of displaying error messages and displaying the total number of errors in a dialog takes place in two phases.
1. Fields are validated when their value changes and the error message is set but not displayed.
2. On submitting the form, the save action validates every field to ensure it is not committing invalid data. Where errors are found, the fields are validated once again to establish the total number of errors for display at the top of the dialog, as seen below.

<img src="https://raw.githubusercontent.com/malleusconsulting/uniqueness-validator-module/gh_pages/total_errors_feedback.png" width="720" height="38" title="Feedback on total errors in a dialog" />

This validator does not handle this two step process well. Consider two fields, *a* and *b*, that are configured to validate against each other and ensure they are unique.
1. On first use of the dialog, *a* is set to the value **"abc"** and *b* remains empty. The validator on *a* runs and find that the value is unique.
2. *b* is set to **"abc"**. *b*'s validator runs and finds the field is not unique so sets an error message ready for future display.
3. The save button is clicked. Both validators are run via the save action and report *a* and *b* are in an invalid state.
4. The entire form is validated, via `info.magnolia.ui.vaadin.form.Form.isValid()` validating each field in turn to establish a total to display. Neither field is now considered valid so the total errors will be two.
5. The dialog will be updated to display a heading claiming there are two errors and the individual error messages on the fields are displayed. Because *a* was valid at the time of first validation, it has no error message. Therefore, while the total is correct, only one validation error is highlighted for the user.

An example of this outcome using three fields is displayed below. Link One was set first so has no error message, however the total reflects that all three are invalid.

<img src="https://raw.githubusercontent.com/malleusconsulting/uniqueness-validator-module/gh_pages/total_and_displayed_errors_mismatch.png" width="720" height="387" title="Total errors shown as three with only two error messages" />

I am seeking a solution to this problem. If the `isValid()` method of `Form` were to count error messages instead of re-running the validation routine, the total would match the number of error messages. This would be ideal for this validator because it may be argued that the first field set to a given value is valid and the only actual error lies in the field that duplicates it.

However, the type used is explicitly set by default in `info.magnolia.ui.dialog.formdialog.ItemFormView` so further investigation is required.
 
### Internationalisation
This validator does not support [single tree multi-language applications](https://documentation.magnolia-cms.com/display/DOCS/Multilanguage+structure#Multilanguagestructure-Singletree). Dialogs with internationalised fields will submit values with a language code appended to them. eg. *thisField* for the default language and *thisField_de*, *thisField_es*, etc. This validator will only look for the values it has been given via `validateAgainst` and will not attempt to append lanugage codes. This *may* be possible in a future version.
