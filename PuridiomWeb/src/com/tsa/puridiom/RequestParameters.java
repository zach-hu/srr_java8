/*
 * Created on Mar 9, 2005
 */
package com.tsa.puridiom;

import com.tsagate.foundation.utility.Utility;

import javax.servlet.http.HttpServletRequest;

/**
 * @author renzo
 */
public class RequestParameters
{
    public String process = "";
    public String url = "";
    public String link = "";
    public Object icHeader;
    public Object icLine;

    private HttpServletRequest request;
    private String oid = "PURIDIOM";
    private boolean deploy = false;

    public boolean isDeploy()
    {/*
        if(this.getOid() != null)
        {
            if(this.getOid().equalsIgnoreCase("PURIDIOM"))
            {
                this.deploy = true;
            }
            else
            {
                this.deploy = false;
            }
        }
        else
        {
            this.deploy = false;
        }

        return this.deploy;
        */
        if(this.getProcess().indexOf("browse") > -1)
        {
            return false;
        }
        return false;
    }

    private void setDeploy(boolean deploy)
    {
        this.deploy = deploy;
    }
    public void setRequestProcess(HttpServletRequest request)
    {
        String formType = (String)request.getAttribute("formType");
        if (Utility.isEmpty(formType))
        {
            formType = (String) request.getAttribute("viewPage");
        }
        formType = Utility.ckNull(formType).toUpperCase();

        this.setProcess(formType);

        if (process.indexOf("REQ") > -1)
        {
            Object icHeader = request.getAttribute("RequisitionHeader_icReqHeader");
            if (icHeader instanceof String)
            {
                this.setIcHeader((String) icHeader);
            }
            else if (icHeader instanceof String[])
            {
                this.setIcHeader((String[]) icHeader);
            }
            
            Object icLine = request.getAttribute("RequisitionHeader_icReqLine");
            if (icLine instanceof String)
            {
                this.setIcLine((String) icLine);
            }
            else if (icLine instanceof String[])
            {
                this.setIcLine((String[]) icLine);
            }

            if (this.getIcHeader() instanceof String && !Utility.isObjectEmpty(this.getIcHeader()))
            {
                this.setLink("openReq('" + this.getIcHeader() + "');");
            }
            else
            {
                this.setDefaultProcess();
            }
        }
        else if (process.indexOf("PO") > -1)
        {
            Object icHeader = request.getAttribute("PoHeader_icPoHeader");
            if (icHeader instanceof String)
            {
                this.setIcHeader((String) icHeader);
            }
            else if (icHeader instanceof String[])
            {
                this.setIcHeader((String[]) icHeader);
            }
            
            Object icLine = request.getAttribute("PoLine_icPoLine");
            if (icLine instanceof String)
            {
                this.setIcLine((String) icLine);
            }
            else if (icLine instanceof String[])
            {
                this.setIcLine((String[]) icLine);
            }
            
            if (this.getIcHeader() instanceof String && !Utility.isObjectEmpty(this.getIcHeader()))
            {
                this.setLink("openOrder('" + this.getIcHeader() + "');");
            }
            else
            {
                this.setDefaultProcess();
            }
        }
        else if (process.indexOf("DSB_") > -1)
        {
            Object icHeader = request.getAttribute("DisbHeader_icDsbHeader");
            if (icHeader instanceof String)
            {
                this.setIcHeader((String) icHeader);
            }
            else if (icHeader instanceof String[])
            {
                this.setIcHeader((String[]) icHeader);
            }
            
            Object icLine = request.getAttribute("DisbLine_icDsbLine");
            if (icLine instanceof String)
            {
                this.setIcLine((String) icLine);
            }
            else if (icLine instanceof String[])
            {
                this.setIcLine((String[]) icLine);
            }
            
            if (this.getIcHeader() instanceof String && !Utility.isObjectEmpty(this.getIcHeader()))
            {
                this.setLink("openDsb('" + this.getIcHeader() + "');");
            }
            else
            {
                this.setDefaultProcess();
            }
        }
        else if (process.indexOf("INV") > -1)
        {
            Object icHeader = request.getAttribute("InvItem_itemNumber");
            if (icHeader instanceof String)
            {
                this.setIcHeader((String) icHeader);
            }
            else if (icHeader instanceof String[])
            {
                this.setIcHeader((String[]) icHeader);
            }

            if (this.getIcHeader() instanceof String && !Utility.isObjectEmpty(this.getIcHeader()))
            {
                this.setLink("openInvItem('" + this.getIcHeader() + "');");
            }
            else
            {
                this.setDefaultProcess();
            }
        }
        else if (process.indexOf("CAT") > -1)
        {
            Object icHeader = request.getAttribute("Catalog_catalogId");
            if (icHeader instanceof String)
            {
                this.setIcHeader((String) icHeader);
            }
            else if (icHeader instanceof String[])
            {
                this.setIcHeader((String[]) icHeader);
            }
            
            if (this.getIcHeader() instanceof String && !Utility.isObjectEmpty(this.getIcHeader()))
            {
                this.setLink("openCatalog('" + this.getIcHeader() + "');");
            }
            else
            {
                this.setDefaultProcess();
            }
        }
        else
        {
            this.setDefaultProcess();
        }
        this.setRequest(request);
        this.setOid((String)request.getAttribute("oid"));
    }

    public void setDefaultProcess()
    {
        this.setProcess("");
        this.setLink("doSubmit(\'/menu/main_menu.jsp\', \'DoNothing\');");
    }

    public String getLink()
    {
        return link;
    }
    public void setLink(String link)
    {
        this.link = link;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public Object getIcHeader()
    {
        return icHeader;
    }
    public void setIcHeader(Object icHeader)
    {
        this.icHeader = icHeader;
    }
    public Object getIcLine()
    {
        return icLine;
    }
    public void setIcLine(Object icLine)
    {
        this.icLine = icLine;
    }

    public String getProcessMsg()
    {
        String str = "Click here to Return to your ";
        return str;
    }

    public String getProcess()
    {
        String str = "";
        if(process.indexOf("REQ") > -1)
        {
            str = "Requisition";
        }
        else if(process.indexOf("PO") > -1)
        {
            str = "Order";
        }
        else if(process.indexOf("DSB_") > -1)
        {
            str ="Disbursement";
        }
        else if(process.indexOf("INV") > -1)
        {
            str = "Inventory Item";
        }
        else if(process.indexOf("CAT") > -1)
        {
            str = "Catalog";
        }
        else
        {
            str = "Procurement Workload  Activity Center";
        }
        return str;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }
    public String getOid()
    {
        return oid;
    }
    private void setOid(String oid)
    {
        this.oid = oid;
    }
    public HttpServletRequest getRequest()
    {
        return request;
    }
    private void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }
}
