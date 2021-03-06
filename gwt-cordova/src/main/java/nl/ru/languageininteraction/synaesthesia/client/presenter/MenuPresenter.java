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
package nl.ru.languageininteraction.synaesthesia.client.presenter;

import nl.ru.languageininteraction.language.client.presenter.AbstractPresenter;
import nl.ru.languageininteraction.language.client.presenter.Presenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import nl.ru.languageininteraction.language.client.listener.AppEventListner;
import nl.ru.languageininteraction.language.client.listener.PresenterEventListner;
import nl.ru.languageininteraction.language.client.model.UserResults;
import nl.ru.languageininteraction.language.client.view.MenuView;

/**
 * @since Nov 3, 2014 11:03:38 AM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class MenuPresenter extends AbstractPresenter implements Presenter {

    protected final UserResults userResults;

    public MenuPresenter(RootLayoutPanel widgetTag, UserResults userResults) {
        super(widgetTag, new MenuView());
        this.userResults = userResults;
    }

    @Override
    protected void setTitle(PresenterEventListner titleBarListner) {
        simpleView.addTitle(messages.menuScreenTitle(), null);
    }

    @Override
    protected void setContent(final AppEventListner appEventListner) {
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.start);
        setMenuOption(appEventListner, AppEventListner.ApplicationState.intro, true);
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.setuser);
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.stimulus);
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.adddummyresults, true);
        setMenuOption(appEventListner, AppEventListner.ApplicationState.report, !userResults.getStimuliGroups().isEmpty());
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.feedback);
          setMenuOption(appEventListner, AppEventListner.ApplicationState.metadata, userResults.canRegister());
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.metadata, true);
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.registration, true);
//        setMenuOption(appEventListner, AppEventListner.ApplicationState.moreinfo);
        setMenuOption(appEventListner, AppEventListner.ApplicationState.locale, true);
        setMenuOption(appEventListner, AppEventListner.ApplicationState.version, true);
    }

    protected void setMenuOption(final AppEventListner appEventListner, final AppEventListner.ApplicationState applicationState, final boolean menuEnabled) {
        ((MenuView) simpleView).addMenuItem(new PresenterEventListner() {

            @Override
            public void eventFired(Button button) {
                appEventListner.requestApplicationState(applicationState);
            }

            @Override
            public String getLabel() {
                return applicationState.label;
            }
        }, menuEnabled);
    }
}
