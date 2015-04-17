/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.awt.Paint;

import org.jfree.chart.renderer.category.BarRenderer3D;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomBarRenderer3D extends BarRenderer3D
{
    private int current = 0;
    public Paint getItemPaint(int i, int j)
    {
        Paint paint = null;
        if(current == this.colors.length)
        {
            current = 0;
        }
        paint = colors[current];
        current++;
        return paint;
    }
    private Paint colors[];

    public CustomBarRenderer3D(Paint apaint[])
    {
        colors = apaint;
    }
}
