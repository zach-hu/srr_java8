/*
 * Created on Dec 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs.design;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;

import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.plot.CategoryPlot;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomCategoryPlot extends CategoryPlot
{

    public LegendItemCollection getLegendItems()
    {
        LegendItemCollection oldLegends = super.getLegendItems();
        LegendItemCollection newLegends = new LegendItemCollection();

        /*int count = oldLegends.getItemCount();
        for (int i=0; i < count; i++)
        {
            LegendItem legendItem = oldLegends.get(i);
            String label = "hello";
            String description = label;

            LegendItem item = new LegendItem(label, description, legendItem.getShape(), legendItem.getPaint(), legendItem.getOutlinePaint(), legendItem.getStroke());
            newLegends.add(item);
        }
        */
        return newLegends;
    }
}
