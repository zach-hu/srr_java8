<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.NumberFormat" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	Map approvalNotes = new HashMap();
	RequisitionHeader reqHeader = (RequisitionHeader)request.getAttribute("requisitionHeader");
	String s_req_number = reqHeader.getRequisitionNumber();
	String	currencyCode = reqHeader.getCurrencyCode() ;
	
	boolean bForward = false;
	String reqAction = (String)request.getAttribute("reqaction");
	String pricingAction = (String)request.getAttribute("pricingAction");
	String previewOnly = HiltonUtility.ckNull((String)request.getAttribute("previewOnly"));
	String backgroundClass = "basic";

	if (reqAction == null) { reqAction = "FORWARD";	}

	pricingAction = HiltonUtility.ckNull(pricingAction);
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/dynamicTables.js' type='text/javascript'></script>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="browseName" value="approver"/>
<tsa:hidden name="formField" value="ApprovalLog_userId"/>
<tsa:hidden name="reqaction" value="<%=reqAction%>"/>
<tsa:hidden name="pricingAction" value="<%=pricingAction%>"/>
<tsa:hidden name="fromPage" value="routinglist"/>

<table border="0" cellpadding="0" cellspacing="0" width="665px">
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
  <tsa:td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tsa:tr>
      <tsa:td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tsa:tr>
          <tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
        </tsa:tr>
        <tsa:tr>
          <tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="req-routinglist" defaultString="Routing List"/></div>
          </tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
      <tsa:td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
      <tsa:td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tsa:tr>
          <tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
        </tsa:tr>
        <tsa:tr>
          <tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
    </tsa:tr>
    </table>
  </tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
  <tsa:td align="center">
    <font class="formType"><tsa:label labelName="requisition" defaultString="Requisition"/> </font><font class="hdr12">#<%=headerEncoder.encodeForHTML(s_req_number)%></font>
  </tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" width="640px">
<tsa:tr>
	<tsa:td>
<%
    String ruleStatus = HiltonUtility.ckNull((String) request.getAttribute("ruleStatus"));
    String ruleError = HiltonUtility.ckNull((String) request.getAttribute("ruleError"));
    String byPassRoutingListString = HiltonUtility.ckNull((String) request.getAttribute("byPassRoutingList"));
    boolean byPassRoutingList = byPassRoutingListString.equalsIgnoreCase("true");
    boolean bPassed = true;

    if (ruleStatus.equalsIgnoreCase("FAILED"))
    {
      bPassed = false;
%>
    <table border="0" cellspacing="0" cellpadding="0" width="600px" align="center">
    <tsa:tr>
      <tsa:td cssClass="label"><%=ruleError%></tsa:td>
    </tsa:tr>
    </table>
<%	}
		else if (propertiesManager.getProperty("APPROVALS", "RESORTBY", "").equals("RULE")
				|| (reqHeader.getStatus().compareTo(DocumentStatus.REQ_INPROGRESS) >= 0 && reqHeader.getStatus().compareTo(DocumentStatus.REQ_APPROVING) < 0)
				|| (reqHeader.getStatus().compareTo(DocumentStatus.REQ_PLANNING_APPROVING) < 0)) {%>
<%@include file="/requests/req_approval_routing_list.jsp" %>
<%	} else { %>
<%@include file="/requests/req_approval_routing_list_amt.jsp" %>
<%	}%>
<%@ include file="/requests/req_approval_notes_display.jsp" %>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td align="center"><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);"><tsa:label labelName="req-close" defaultString="Close"/></a></div></tsa:td></tsa:tr>
</table>

<br>
<br>

</FORM>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  self.focus();

   function thisUnLoadPopup()
  {
<%	if (!previewOnly.equalsIgnoreCase("Y")) { %>
      		window.parent.showForwardButton();
<%	} %>
  }

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>