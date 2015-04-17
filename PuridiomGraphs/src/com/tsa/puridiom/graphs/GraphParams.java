/*
 * Created on Dec 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.graphs;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.data.category.CategoryDataset;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GraphParams
{
    private int width = 200;
    private int heigth = 200;
    private HttpSession session = null;
    private PrintWriter writer = null;
    private String contextPath = "";
    private String XTitle = "";
    private String YTitle = "";
    private CategoryDataset dataset = null;
    private String title = "";
    private ChartRenderingInfo chartRenderingInfo = null;
    private String ImgName = "";
    private String oid = "PURIDIOMX";
    private boolean hasData = false;
    private String formType = "REQ";
    private boolean createImageMap = true;
    private int tickUnits = 0;
    private List dataCodeList = null;

    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public int getHeigth()
    {
        return heigth;
    }
    public void setHeigth(int heigth)
    {
        this.heigth = heigth;
    }
    public HttpSession getSession()
    {
        return session;
    }
    public void setSession(HttpSession session)
    {
        this.session = session;
    }
    public int getWidth()
    {
        return width;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }
    public PrintWriter getWriter()
    {
        return writer;
    }
    public void setWriter(PrintWriter writer)
    {
        this.writer = writer;
    }
    public String getContextPath()
    {
        return contextPath;
    }
    public void setContextPath(String contextPath)
    {
        this.contextPath = contextPath;
    }
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[GraphParams:");
        buffer.append(" width: ");
        buffer.append(width);
        buffer.append(" heigth: ");
        buffer.append(heigth);
        buffer.append(" session: ");
        buffer.append(session);
        buffer.append(" writer: ");
        buffer.append(writer);
        buffer.append(" contextPath: ");
        buffer.append(contextPath);
        buffer.append("]");
        return buffer.toString();
    }
    /**
     * @return
     */
    public String getXTitle()
    {
        // TODO Auto-generated method stub
        return XTitle;
    }
    public CategoryDataset getDataset()
    {
        return dataset;
    }
    public void setDataset(CategoryDataset categorydataset)
    {
        this.dataset = categorydataset;
    }
    public String getYTitle()
    {
        return YTitle;
    }
    public void setYTitle(String title)
    {
        YTitle = title;
    }
    public void setXTitle(String title)
    {
        XTitle = title;
    }
    public ChartRenderingInfo getChartRenderingInfo()
    {
        return chartRenderingInfo;
    }
    public void setChartRenderingInfo(ChartRenderingInfo chartRenderingInfo)
    {
        this.chartRenderingInfo = chartRenderingInfo;
    }
    public String getImgName()
    {
        return ImgName;
    }
    public void setImgName(String imgName)
    {
        ImgName = imgName;
    }
    public boolean getHasData()
    {
    	return hasData;
    }
    public void setHasData(boolean bHasData)
    {
    	this.hasData = bHasData;
    }
    public boolean getCreateImageMap()
    {
    	return createImageMap;
    }
    public void setCreateImageMap(boolean bImgMap)
    {
    	this.createImageMap = bImgMap;
    }
    /**
     * @return
     */
    public String getOid()
    {
        return this.oid;
    }
    public void setOid(String oid)
    {
        this.oid = oid;
    }
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public int getTickUnits() {
		return tickUnits;
	}
	public void setTickUnits(int tickUnits) {
		this.tickUnits = tickUnits;
	}
	public List getDataCodeList() {
		return dataCodeList;
	}
	public void setDataCodeList(List dataCodeList) {
		this.dataCodeList = dataCodeList;
	}
}
