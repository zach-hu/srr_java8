/*
 * ============================================================================
 *                   GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2005 JasperSoft Corporation http://www.jaspersoft.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * JasperSoft Corporation
 * 185, Berry Street, Suite 6200
 * San Francisco CA 94107
 * http://www.jaspersoft.com
 */

/*
 * Contributors:
 * Adrian Jackson - iapetus@users.sourceforge.net
 * David Taylor - exodussystems@users.sourceforge.net
 * Lars Kristensen - llk@users.sourceforge.net
 */
package net.sf.jasperreports.renderers;

import java.awt.*;
import java.awt.geom.*;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import net.sf.jasperreports.engine.JRAbstractSvgRenderer;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.output.OutputException;


/**
 * A wrapper for the Drawable interface in the JCommon library: you will need the
 * JCommon classes in your classpath to compile this class. In particular this can be
 * used to allow JFreeChart objects to be included in the output report in vector form.
 *
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: BarbecueRendererVSE.java,v 1.1 2009-02-27 22:48:20 jeff Exp $
 */
public class BarbecueRendererVSE extends JRAbstractSvgRenderer
{

	/**
	 *
	 */
	private Barcode barcode = null;
	private static final Font DEFAULT_FONT = new Font("monospace", Font.CENTER_BASELINE, 8);
    private static final int DEFAULT_BAR_WIDTH = 1;
    private static final int DEFAULT_BAR_HEIGHT = 20;

	/**
	 *
	 */
	public BarbecueRendererVSE(Barcode barcode)
	{
		this.barcode = barcode;
		this.barcode.setBarWidth(DEFAULT_BAR_WIDTH);
        this.barcode.setBarHeight(DEFAULT_BAR_HEIGHT);
        this.barcode.setFont(DEFAULT_FONT);
	}


	/**
	 *
	 */
	public void render(Graphics2D grx, Rectangle2D rectangle)
	{
		if (barcode == null)
		{
			return;
		}

		double scaleX;
        double scaleY;

        Rectangle r = this.barcode.getBounds();
        scaleX = rectangle.getWidth() / r.getWidth();
        scaleY = rectangle.getHeight() / r.getHeight();

        try {
        	this.barcode.draw(grx, (int)rectangle.getX(), (int)rectangle.getY());
        }
        catch (OutputException e)
        {
            e.printStackTrace();
        }

	}

    public Dimension2D getDimension() {
        return this.barcode.getBounds().getSize();
     }

}
