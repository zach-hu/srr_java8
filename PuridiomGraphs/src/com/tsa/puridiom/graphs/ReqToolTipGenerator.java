/*
 * Created on Mar 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.text.NumberFormat;

import org.jfree.chart.labels.StandardCategoryToolTipGenerator;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReqToolTipGenerator extends StandardCategoryToolTipGenerator
{
    /**
     * Creates a new generator with a default number formatter.
     */
    public ReqToolTipGenerator()
    {
        super("{1} = {2}", NumberFormat.getInstance());
    }

}
