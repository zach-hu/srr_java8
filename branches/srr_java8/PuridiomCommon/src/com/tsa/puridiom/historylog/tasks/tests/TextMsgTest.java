/*
 * Created on Mar 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.historylog.tasks.tests;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;

import com.tsagate.foundation.utility.Dates;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TextMsgTest
{
    public static String text()
    {
        String pattern = "Order {0} was automatically generated from Req {1} {2}.";
        Object args[] = new Object[5];
        //args[0] = "po#####";
        args[1] = "req###";
        args[2] = "and awarded to supplier XXX";
        args[3] = "po";
        args[4] = "ro";


        //1 if release order is > 0
        //0 if release order number < 1
        double[] xmlLimits = {0,1};
        String [] fileStrings = {"{3}",
                "{3}-{4}"
                };
        ChoiceFormat numberOptions = new ChoiceFormat(xmlLimits, fileStrings);
        MessageFormat msg = new MessageFormat("");
        msg.applyPattern(pattern);

        Format formats[] = {numberOptions, null, null};
        msg.setFormats(formats);

        args[0] = new Integer(0);
        String result = msg.format(args);
        return result;
    }

    /**
     *
     */
    public TextMsgTest()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args)
    {
        System.out.println(TextMsgTest.text());
        System.out.println("done");
    }
}
