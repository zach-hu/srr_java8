// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tsa.puridiom.graphs;

import java.awt.Color;
import java.io.PrintStream;
import java.sql.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class JDBCPieChartDemo extends ApplicationFrame
{

    public JDBCPieChartDemo(String s)
    {
        super(s);
        PieDataset piedataset = readData();
        JFreeChart jfreechart = ChartFactory.createPieChart("JDBC Pie Chart Demo", piedataset, true, true, false);
        jfreechart.setBackgroundPaint(Color.yellow);
        PiePlot pieplot = (PiePlot)jfreechart.getPlot();
        pieplot.setNoDataMessage("No data available");
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        setContentPane(chartpanel);
    }

    private PieDataset readData()
    {
        JDBCPieDataset jdbcpiedataset = null;
        String s = "jdbc:oracle:thin:@216.37.169.84:1521:hilton1p";
        Connection connection = null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            System.err.print("ClassNotFoundException: ");
            System.err.println(classnotfoundexception.getMessage());
        }
        try
        {
            connection = DriverManager.getConnection(s, "hiltonuser", "hiltonuser");
            jdbcpiedataset = new JDBCPieDataset(connection);
            String sql = "select header.status, count(*) from REQUISITION_HEADER header where header.ASSIGNED_BUYER = 'RRAMOS' group by header.STATUS";
            jdbcpiedataset.executeQuery(sql);
            
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
        return jdbcpiedataset;
    }

    public static void main(String args[])
    {
        JDBCPieChartDemo jdbcpiechartdemo = new JDBCPieChartDemo("JDBC Pie Chart Demo");
        jdbcpiechartdemo.pack();
        RefineryUtilities.centerFrameOnScreen(jdbcpiechartdemo);
        jdbcpiechartdemo.setVisible(true);
    }
}
