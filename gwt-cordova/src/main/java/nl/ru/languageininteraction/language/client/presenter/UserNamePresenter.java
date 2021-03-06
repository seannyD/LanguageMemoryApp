/*
 * Copyright (C) 2014 Language In Interaction
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.ru.languageininteraction.language.client.presenter;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.MetadataField;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.service.LocalStorage;
import nl.ru.languageininteraction.language.client.view.MetadataView;
import nl.ru.languageininteraction.language.client.exception.MetadataFieldException;

/**
 * @since Nov 14, 2014 3:28:10 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserNamePresenter extends MetadataPresenter {

    private boolean isNewUser = true;
    final MetadataField firstNameField = metadataFieldProvider.metadataFieldArray[0];

    public UserNamePresenter(RootLayoutPanel widgetTag, UserResults userResults) {
        super(widgetTag, userResults);
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.userNameScreenTitle(), titleBarListner);
    }

    @Override
    protected void setContent(AppEventListner appEventListner) {
        
        final String userNameValue = userResults.getMetadataValue(firstNameField.getPostName());
        if (userNameValue != null && !userNameValue.isEmpty()) {
            ((MetadataView) simpleView).addText(messages.userNameScreenExistingUserText(userNameValue));
            ((MetadataView) simpleView).addOptionButton(new PresenterEventListner() {

                @Override
                public String getLabel() {
                    return messages.userNameScreenExistingButton(userNameValue);
                }

                @Override
                public void eventFired(Button button) {
                    isNewUser = false;
                    ((MetadataView) simpleView).setFieldValue(firstNameField, userNameValue);
                    saveEventListner.eventFired(button);
                }
            });
        }
        ((MetadataView) simpleView).addPadding();
        ((MetadataView) simpleView).addPadding();
        ((MetadataView) simpleView).addText(messages.userNameScreenText());
//        for (MetadataField metadataField : new MetadataField[]{firstNameField/*, metadataFieldProvider.metadataFieldArray[1]*/}) {
        ((MetadataView) simpleView).addField(firstNameField, "");
//        }        
        ((MetadataView) simpleView).addPadding();
        ((MetadataView) simpleView).addOptionButton(new PresenterEventListner() {

            @Override
            public String getLabel() {
                return messages.userNameScreenCreateButton();
            }

            @Override
            public void eventFired(Button button) {
                isNewUser = true;
                saveEventListner.eventFired(button);
            }
        });
        ((MetadataView) simpleView).addPadding();
        ((MetadataView) simpleView).addPadding();
    }

    @Override
    protected void addNextButton() {
        // this screen should not have a next button
    }

    @Override
    protected void saveFields() throws MetadataFieldException {
        if (isNewUser) {
            userResults.clearResults();
            userResults.clearMetadata();
            new LocalStorage().clear();
            super.saveFields();
        }
    }

}
