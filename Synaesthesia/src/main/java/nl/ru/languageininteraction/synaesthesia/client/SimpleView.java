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
package nl.ru.languageininteraction.synaesthesia.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * @since Oct 7, 2014 2:06:28 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class SimpleView extends AbstractView {

    private final HorizontalPanel footerPanel;

    public SimpleView() {
        footerPanel = new HorizontalPanel();
        footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        resizeView();
    }

    public void clearAll() {
        clear();
        addSouth(footerPanel, 3);
    }

    protected void setDisplayText(String text) {
        add(new ScrollPanel(new HTML(text)));
    }

    protected void addTitle(String label) {
        addNorth(new ScrollPanel(new Label(label)), 3);
    }

    protected void addLink(String label, String target) {
        addSouth(new ScrollPanel(new Anchor(label, target)), 2);
    }

    protected void setButton(String buttonText, final AppEventListner presenterListerner) {
        final Button nextButton = new Button(buttonText);
        nextButton.addStyleName("nextButton");
        nextButton.setEnabled(true);
        footerPanel.add(nextButton);
        nextButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                presenterListerner.eventFired();
            }
        });
    }

//    DockLayoutPanel p = new DockLayoutPanel();
//p.addNorth(new HTML("header"), 2);
//p.addSouth(new HTML("footer"), 2);
//p.addWest(new HTML("navigation"), 10);
//p.add(new HTML(content));
    @Override
    protected void parentResized(int height, int width, String units) {
        footerPanel.setWidth(width + "px");
    }

}
