package com.tsa.puridiom.jasperreports;


import java.math.BigDecimal;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.JRFillVariable;


public class Scriptlet extends JRDefaultScriptlet
{
    public BigDecimal total = new BigDecimal(0);

	/**
	 *
	 */
	public void beforeReportInit() throws JRScriptletException
	{
		//System.out.println("call beforeReportInit");
	}


	/**
	 *
	 */
	public void afterReportInit() throws JRScriptletException
	{
		//System.out.println("call afterReportInit");
	    this.setVariableValue("reportTotal", this.total);
	}


	/**
	 *
	 */
	public void beforePageInit() throws JRScriptletException
	{
		//System.out.println("call   beforePageInit : PAGE_NUMBER = " + this.getVariableValue("PAGE_NUMBER"));
	}


	/**
	 *
	 */
	public void afterPageInit() throws JRScriptletException
	{
		//System.out.println("call   afterPageInit  : PAGE_NUMBER = " + this.getVariableValue("PAGE_NUMBER"));
	}


	/**
	 *
	 */
	public void beforeColumnInit() throws JRScriptletException
	{
		//System.out.println("call     beforeColumnInit");
	}


	/**
	 *
	 */
	public void afterColumnInit() throws JRScriptletException
	{
		//System.out.println("call     afterColumnInit");
	}


	/**
	 *
	 */
	public void beforeGroupInit(String groupName) throws JRScriptletException
	{
		if (groupName.equals("CityGroup"))
		{
			//System.out.println("call       beforeGroupInit : City = " + this.getFieldValue("PoHeader_buyer"));
		}
	}


	/**
	 *
	 */
	public void afterGroupInit(String groupName) throws JRScriptletException
	{
		if (groupName.equals("buyer"))
		{
			System.out.println("call       afterGroupInit  : Buyer = " + this.getFieldValue("PoHeader_buyer"));
		
			Map variables = this.variablesMap;
			JRFillVariable var = (JRFillVariable)variables.get("grandBaseTotal");
			BigDecimal sum = (BigDecimal)var.getEstimatedValue();
			
			if(sum == null)
			{
			    sum = new BigDecimal(0);
			}
			this.total = this.total.add(sum);
			this.setVariableValue("reportTotal", this.total);
		}
	}


	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException
	{
		//System.out.println("        detail");
	}


	/**
	 *
	 */
	public void afterDetailEval() throws JRScriptletException
	{
	}


	/**
	 *
	 */
	public String hello() throws JRScriptletException
	{
		return "Hello! I'm the report's scriptlet object.";
	}


}
