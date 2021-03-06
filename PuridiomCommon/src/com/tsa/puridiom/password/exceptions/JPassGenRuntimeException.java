/*
 * @(#)JPassGenRuntimeException.java  2002-07-26
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

package com.tsa.puridiom.password.exceptions;

/**
 * This class is for specific application runtime exceptions.
 *
 * @author hughes monget
 */
public class JPassGenRuntimeException extends RuntimeException {

    //////////////////
    // CONSTRUCTORS //
    //////////////////

    /**
    * Default Constructor.
    */
	public JPassGenRuntimeException() {

        super();
    }

    /**
     * Constructor with <code>String()</code> object.
     * @param s the description of the exception.
     */
    public JPassGenRuntimeException(String s) {

        super(s);
    }

} // End of JPassGenRuntimeException.java
