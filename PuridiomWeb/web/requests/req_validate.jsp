<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>

<%
  ValidationRules rules = (ValidationRules)request.getAttribute("rules");
  RequisitionHeader reqHeader = (RequisitionHeader)request.getAttribute("header");
  String s_req_number = reqHeader.getRequisitionNumber();
  boolean bForward = false;
  String reqAction = (String)request.getAttribute("reqaction");
  String	pricingAction = "";
  if (reqAction == null){ reqAction = "VALIDATE";	}

  if (reqAction.equalsIgnoreCase("REPRICING")) {
    pricingAction = reqAction;
    reqAction = "FORWARD";
  }
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=reqHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=reqHeader.getRequisitionType()%>"/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="pricingAction" value="<%=pricingAction%>"/>

<table border="0" cellpadding="0" cellspacing="0" width=655px>
<tr><td><br></td></tr>
<tr>
  <td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tr>
          <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
        </tr>
        <tr>
          <td nowrap class="hdr12" valign="middle">
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="validationResults" defaultString="Validation Results" /></div>
          </td>
        </tr>
        </table>
      </td>
      <td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
      <td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
          <td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
  <td align="center">
    <span class="formType"><tsa:label labelName="requisition" defaultString="Requisition" /> </span><span class="hdr12">#<%=s_req_number%></span>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/base/validation-rules.jsp" %>
  </td>
</tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" width="400px">
<TR>
<%	if ( rules.getResult() != 1 )
    {
      if (rules.getResult() > -1 && reqAction.equalsIgnoreCase("FORWARD"))
      { %>
  <TD align=center><a href="javascript: reqForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></TD>
<%		}
    } %>
  <td align=center><a href="javascript: closeMe(); void(0);"><img class="button" src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close"></a></td>
</tr>
<%	if (rules.getResult() == 1 && reqAction.equalsIgnoreCase("FORWARD"))
    { %>
       <tr>
        <td>
            <TABLE>
              <TD VALIGN="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></TD>
              <TD VALIGN="MIDDLE" CLASS="basic"><B><tsa:label labelName="windowsWillCloseAutomatically" defaultString="This window will close automatically when the forward process is complete" />.</B></TD>
            </TABLE>
        </td>
      </tr>
<%	} %>
</TR>
</TABLE>
</FORM>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  self.focus();
<%	if (rules.getResult() == 1 && reqAction.equalsIgnoreCase("FORWARD")) { %>
   reqForward();
<%	} %>

  function reqForward()
  {
    var reqaction = '<%=reqAction%>';
    frm.reqaction.value = reqaction;
    <%if(pricingAction.equalsIgnoreCase("repricing") || (reqHeader.getRequisitionType().equals("N") && reqHeader.getStatus().equals("1000")))
  {%>
    forward();
  <%}
  else
  {%>
      doSubmit('/requests/req_routinglist.jsp', 'RequisitionGetRoutingList');
    <%}%>
  }

  function forward()
  {
    var reqaction = '<%=reqAction%>';

    setTimeout('window.top.hidePopWin();', 500);

    //set cursor to hourglass while the system is processing
    window.parent.document.body.style.cursor = "wait";
    window.parent.frm.reqaction.value = "FORWARD";
    window.parent.frm.pricingAction.value = frm.pricingAction.value;
    window.parent.reqForward();

    window.top.hidePopWin();
  }

  function closeMe()
  {
      window.parent.showForwardButton();
      window.top.hidePopWin();
  }

  function thisUnLoadPopup()
  {
      window.parent.showForwardButton();
  }

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>