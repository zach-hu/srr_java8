/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.data.general.PieDataset;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HiltonPieToolTipGenerator implements PieToolTipGenerator
{

    /* (non-Javadoc)
     * @see org.jfree.chart.labels.PieToolTipGenerator#generateToolTip(org.jfree.data.general.PieDataset, java.lang.Comparable)
     */
    /**
     * Generates a tool tip text item for the specified item in the dataset.  This method can
     * return <code>null</code> to indicate that no tool tip should be displayed for an item.
     *
     * @param dataset  the dataset (<code>null</code> not permitted).
     * @param key  the section key (<code>null</code> not permitted).
     *
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String generateToolTip(PieDataset dataset, Comparable key)
    {
       String keyword = key.toString();
       System.out.println("key: " + keyword);
        return key.toString();
    }

}
