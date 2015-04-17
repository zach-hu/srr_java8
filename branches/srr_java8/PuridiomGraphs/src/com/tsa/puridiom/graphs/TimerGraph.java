package com.tsa.puridiom.graphs;

import org.jdom.Element;

import com.tsa.puridiom.common.utility.HiltonUtility;

public class TimerGraph extends Graph
{
	private double startInterval ;
    private double normalInterval ;
    private double warningInterval ;
    private double criticalInterval ;
    private double endInterval ;


    public void setNormalInterval(double normalInterval) {
		this.normalInterval = normalInterval;
	}
	public void setNormalInterval(String normalInterval) {
		if(!HiltonUtility.isEmpty(normalInterval))
		{
			this.normalInterval = Double.parseDouble(normalInterval);
		}
	}

	public double getNormalInterval() {
		return normalInterval;
	}

	public void setWarningInterval(double warningInterval) {
		this.warningInterval = warningInterval;
	}
	public void setWarningInterval(String warningInterval) {
		if(!HiltonUtility.isEmpty(warningInterval))
		{
			this.warningInterval = Double.parseDouble(warningInterval);
		}
	}


	public double getWarningInterval() {
		return warningInterval;
	}


	public void setStartInterval(double startInterval) {
		this.startInterval = startInterval;
	}
	public void setStartInterval(String startInterval) {
		if(!HiltonUtility.isEmpty(startInterval))
		{
			this.startInterval = Double.parseDouble(startInterval);
		}
	}

	public double getStartInterval() {
		return startInterval;
	}

	public void setEndInterval(double endInterval) {
		this.endInterval = endInterval;
	}
	public void setEndInterval(String endInterval) {
		if(!HiltonUtility.isEmpty(endInterval))
		{
			this.endInterval = Double.parseDouble(endInterval);
		}
	}

	public double getEndInterval() {
		return endInterval;
	}

	public void setCriticalInterval(double criticalInterval) {
		this.criticalInterval = criticalInterval;
	}
	public void setCriticalInterval(String criticalInterval) {
		if(!HiltonUtility.isEmpty(criticalInterval))
		{
			this.criticalInterval = Double.parseDouble(criticalInterval);
		}
	}

	public double getCriticalInterval() {
		return criticalInterval;
	}

	public void buildMeForType(Element graphElement)
	{
		if( graphElement.getChildTextTrim("interval")!=null  )
		{
			this.setStartInterval( graphElement.getChild("interval").getChildTextTrim("start") );
			this.setNormalInterval( graphElement.getChild("interval").getChildTextTrim("normal") );
			this.setWarningInterval( graphElement.getChild("interval").getChildTextTrim("warning") );
			this.setCriticalInterval( graphElement.getChild("interval").getChildTextTrim("critical") );
			this.setEndInterval( graphElement.getChild("interval").getChildTextTrim("end") );
		}
	}

}
