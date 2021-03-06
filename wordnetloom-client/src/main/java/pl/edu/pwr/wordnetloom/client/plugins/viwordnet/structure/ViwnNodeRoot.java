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
package pl.edu.pwr.wordnetloom.client.plugins.viwordnet.structure;

import pl.edu.pwr.wordnetloom.common.model.NodeDirection;

abstract public class ViwnNodeRoot extends ViwnNode {

    private final ViwnNodeSet[] sets_;

    public ViwnNodeSet getSynsetSet(NodeDirection dir) {
        return sets_[dir.ordinal()];
    }

    public ViwnNodeRoot() {
        sets_ = new ViwnNodeSet[NodeDirection.values().length];
        for(int i=0; i < sets_.length; i++){
            sets_[i] = new ViwnNodeSet();
        }
    }
}
