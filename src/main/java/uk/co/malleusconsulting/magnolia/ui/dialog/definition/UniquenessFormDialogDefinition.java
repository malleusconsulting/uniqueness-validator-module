package uk.co.malleusconsulting.magnolia.ui.dialog.definition;

import info.magnolia.ui.dialog.definition.ConfiguredFormDialogDefinition;
import info.magnolia.ui.dialog.formdialog.FormDialogPresenter;

public class UniquenessFormDialogDefinition extends ConfiguredFormDialogDefinition {

    public UniquenessFormDialogDefinition() {
    	super();
        super.setPresenterClass(FormDialogPresenter.class);
    }
}
