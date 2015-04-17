/*
 * @(#)RandomVector.java  2002-07-29
 *
 * Version 1.04
 *
 * Copyright (C) 26 juillet 2002 hughes monget
 * hmonget@users.sourceforge.net
 * http://jpassgen.sourceforge.net/
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
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

package com.tsa.puridiom.password;

import java.util.Vector;

/**
 * This class is used as a <code>Vector()</code> object.
 * But this vector can be "scrambled".
 *
 * @author hughes monget
 */
class RandomVector extends Vector {

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    /**
     * We scramble the components of the vector.
     */
    void scramble() {

        // (See "Applied Cryptography" by Bruce Schneier)
        int shuffle = size() - 1;
        for (int ii = shuffle ; ii > 0 ; ii--) {
            int iiPermut = Math.abs(RandomSingleton.getInstance().nextInt())
                    % (ii + 1);
            if (iiPermut != ii) {
                Object permutObject = elementAt(ii);
                setElementAt(elementAt(iiPermut), ii);
                setElementAt(permutObject, iiPermut);
            }
        }
    }

} // End of RandomVector.java
