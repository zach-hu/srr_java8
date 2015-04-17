/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import org.jfree.data.general.PieDataset;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PieParams extends GraphParams
{
    private PieDataset pieDataset = null;
    

    public PieDataset getPieDataset()
    {
        return pieDataset;
    }
    public void setPieDataset(PieDataset pieDataset)
    {
        this.pieDataset = pieDataset;
    }
}
