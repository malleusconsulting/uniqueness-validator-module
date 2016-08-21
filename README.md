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


  