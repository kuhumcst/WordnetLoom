/*
    Copyright (C) 2011 Łukasz Jastrzębski, Paweł Koczan, Michał Marcińczuk,
                       Bartosz Broda, Maciej Piasecki, Adam Musiał,
                       Radosław Ramocki, Michał Stanek
    Part of the WordnetLoom

    This program is free software; you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by the Free
Software Foundation; either version 3 of the License, or (at your option)
any later version.

    This program is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
or FITNESS FOR A PARTICULAR PURPOSE.

    See the LICENSE and COPYING files for more details.
 */
package pl.edu.pwr.wordnetloom.client.systems.ui;

import com.alee.laf.splitpane.WebSplitPane;

import java.awt.*;

public class MSplitPane extends WebSplitPane {

    private int startDividerLocation = 0;


    public MSplitPane(int splitType, Component first, Component second) {
        super(splitType, true, first, second);
        setOneTouchExpandable(true);
        setContinuousLayout(true);
    }

    public MSplitPane withExpandable(boolean expandable) {
        setOneTouchExpandable(expandable);
        return this;
    }

    public void setStartDividerLocation(int location) {
        setDividerLocation(location);
        startDividerLocation = location;
    }

    public void resetDividerLocation() {
        setDividerLocation(startDividerLocation);
    }

    public void collapse(int number) {
        if (number == 0) {
            setDividerLocation(0);
        } else {
            setDividerLocation(99999);
        }
    }
}
