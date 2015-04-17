// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tsa.puridiom.graphs.tasks.tests;

import java.awt.Color;
import java.io.PrintStream;
import java.sql.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class JDBCCategoryChartDemo extends ApplicationFrame
{

    public JDBCCategoryChartDemo(String s)
    {
        super(s);
        CategoryDataset categorydataset = readData();
        JFreeChart jfreechart = ChartFactory.createBarChart3D("JDBC Category Chart Demo", "Category", "Value", categorydataset, PlotOrientation.VERTICAL, true, true, false);
        jfreechart.setBackgroundPaint(Color.yellow);
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        setContentPane(chartpanel);
    }

    private CategoryDataset readData()
    {
        JDBCCategoryDataset jdbccategorydataset = null;
        String s = "jdbc:oracle:thin:@216.37.169.84:1521:hilton1p";
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            System.err.print("ClassNotFoundException: ");
            System.err.println(classnotfoundexception.getMessage());
        }
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(s, "hiltonuser", "hILTONUSER");
            jdbccategorydataset = new JDBCCategoryDataset(connection);
            //String s1 = "SELECT * FROM CATEGORYDATA1;";
            String s1 = "SELECT status, count(*) FROM Po_Header where buyer_Code = 'RRAMOS' GROUP BY status";
            jdbccategorydataset.executeQuery(s1);
            
        }
        catch(SQLException sqlexception)
        {
            System.err.print("SQLException: ");
            System.err.println(sqlexception.getMessage());
        }
        catch(Exception exception)
        {
            System.err.print("Exception: ");
            System.err.println(exception.getMessage());
        }
        finally
        {
            
            if(connection != null)
            {
	            try
	            {
	                connection.close();
	            }
	            catch (SQLException e)
	            {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
            }
        }
        return jdbccategorydataset;
    }

    public static void main(String args[])
    {
        JDBCCategoryChartDemo jdbccategorychartdemo = new JDBCCategoryChartDemo("JDBC Category Chart Demo");
        jdbccategorychartdemo.pack();
        RefineryUtilities.centerFrameOnScreen(jdbccategorychartdemo);
        jdbccategorychartdemo.setVisible(true);
    }
}
