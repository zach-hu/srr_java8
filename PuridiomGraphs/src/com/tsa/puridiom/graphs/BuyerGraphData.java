package com.tsa.puridiom.graphs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.utility.Log;

public class BuyerGraphData implements Serializable
{
    private String organizationId = "PURIDIOM";
    private String userId = "";
    protected List data = null;
    private BuyerGetGraphData graphData = null;
    
    public BuyerGraphData()
    {
        Log.debug(this, "started");
    }
    public BuyerGraphData (String organizationId, String userId)
    {
        this.setOrganizationId(organizationId);
        this.setUserId(userId);
        this.getGraphData();
        this.setData(this.getDataList(this.graphData));
    }
    
	private void getGraphData()
	{
	    this.graphData = new BuyerGetGraphData(this.getOrganizationId(), this.getUserId());
	}
    
    
    /**
     * @return
     */
    protected List getDataList(BuyerGetGraphData graphData)
    {
        return null;
    }

    public String status(String status)
    {
        return DocumentStatus.toString(status);
    }
    
    public Object buildDataSet(List dataList)
    {
        return null;
    }

    public boolean hasExpired(Map params, Date since)
    {
        System.out.println("hasExpired()");
        return (System.currentTimeMillis() - since.getTime()) > 5000;
    }

    public String getProducerId()
    {
        return "PageViewCountData DatasetProducer";
    }
    public String getOrganizationId()
    {
        return organizationId;
    }
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public List getData()
    {
        return data;
    }
    public void setData(List data)
    {
        this.data = data;
    }
}