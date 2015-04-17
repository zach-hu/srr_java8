<%@ page import="com.tsa.puridiom.steporder.*" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>

<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0 ALIGN=RIGHT>
<tsa:tr><tsa:td colspan="3" height="1px" cssClass="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></tsa:td></tsa:tr>
<tsa:tr cssClass="sidebar"><tsa:td colspan="3" height="5px">&nbsp;</tsa:td></tsa:tr>

<%
  HashMap processMap = new HashMap();
  String	s_process_code = "";
  String	s_process_link = "";
  String	s_process_label = "";
  String	s_process_method = "";

  String	s_previous_link = "";
  String	s_previous_label = "";
  String	s_previous_method = "";
  String	s_next_link = "";
  String	s_next_label = "";
  String	s_next_method = "";
  String	s_reqsteps = "";
  String    accountPref = "";
  String	s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOREQ", "N");
  String	s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOREQ", "Y");
  int i_current_step=1;

  boolean	b_current_process = false;

  int	i_step = 1;

  ProcessSteps steps = null;

  if (!HiltonUtility.isEmpty(s_req_type)) {
    if (oid.equalsIgnoreCase("BSC04P") && s_req_subtype.equals("X")) {
      s_reqsteps = "reqsteps_" + s_req_subtype.toLowerCase();
    } else {
      s_reqsteps = "reqsteps_" + s_req_type.toLowerCase();
    }
  }

  steps = new ProcessSteps(s_reqsteps, oid);

  for (int ip = 0; ip < steps.getSize(); ip++)
  {
	s_process_code = steps.getProcess(ip);
    s_process_link = steps.getLink(ip);
    s_process_label = steps.getLabel(ip);
    s_process_method = steps.getMethod(ip);

	if (s_process_code.equals("HEADER_VALIDATION")) {
		if (s_req_type.equals("M") && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0) {
				// DO NOT DISPLAY VALIDATION STEP
				continue;
		} else
		{
			if  ((s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0) && (s_req_status.compareTo(DocumentStatus.RFQ_PRICED) != 0) && s_req_status.compareTo(DocumentStatus.INV_RETURNING) != 0) {
				// DO NOT DISPLAY VALIDATION STEP
				continue;
			}
		}
	}

	if (s_process_code.equals("SOURCING")) {
		if (s_req_type.equals("M")) {
			if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) < 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) >= 0) {
				// DO NOT DISPLAY SOURCING STEP IF NOT APPROVED
				// DO NOT DISPLAY SOURCING STEP IF SOURCED OR CLOSED
				continue;
			}
		}
	}

    processMap.put(s_process_code, "TRUE");

    if (s_current_process.equals(s_process_code))
    {
      b_current_process = true;
      s_current_process_method = s_process_method;
      i_current_step = i_step;

      if (ip > 0)
      {
        s_previous_link = steps.getLink(ip - 1);
        s_previous_label = steps.getLabel(ip - 1);
        s_previous_method = steps.getMethod(ip - 1);
      }
      if ( (ip + 1) < steps.getSize())
      {
    	  int incBy = 1 ;
          if (s_process_code.equals("HEADER_REVIEW") && s_req_type.equals("M")) {
        	  if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) >= 0) {
					incBy = 2 ;  // Skip Header Validation and goto sourcing
        	  }
          }
        s_next_link = steps.getLink(ip + incBy);
        s_next_label = steps.getLabel(ip + incBy);
        s_next_method = steps.getMethod(ip + incBy);
      }
    }
    else if (s_current_process.indexOf("LINE") == 0 && (s_process_code.equals("ITEMS") || s_process_code.equals("ITEM")))
    {
      b_current_process = false;

      if (ip > 0)
      {
        s_previous_link = steps.getLink(ip - 1);
        s_previous_label = steps.getLabel(ip - 1);
        s_previous_method = steps.getMethod (ip -1);
      }
      if ( (ip + 1) < steps.getSize())
      {
        s_next_link = steps.getLink(ip + 1);
        s_next_label = steps.getLabel(ip + 1);
        s_next_method = steps.getMethod(ip + 1);
      }
    }
    else
    {
      b_current_process = false;
    }

    if (b_current_process)
    {
        if (s_process_code.equals("HEADER_VALIDATION") && s_req_type.equals("M")) {
      	  if (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVING) < 0) {
      		s_next_link ="" ;
      	  }
    	}

%>
<tsa:hidden name="accountPref" value="<%=accountPref%>" />
<tsa:tr>
  <tsa:td width="5px" cssClass="processOn">&nbsp;</tsa:td>
  <tsa:td valign="center">
	<div id="pxsmallbutton"><%=i_step%></div>
  </tsa:td>
  <tsa:td cssClass="processOn" noWrap="nowrap">
    &nbsp;<tsa:label labelName="<%=s_process_label %>" defaultString="<%=s_process_label %>" checkRequired="false" />&nbsp;
  </tsa:td>
</tsa:tr>
<%
    }
    else
    {

%>
<tsa:tr>
  <tsa:td cssClass="processOff" width="5px">&nbsp;</tsa:td>
  <tsa:td cssClass="processOff" valign="middle">
    <div id="pxsmallbutton"><a href="javascript: doSubmitCheck('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');"><%=i_step%></a></div>
  </tsa:td>
  <tsa:td cssClass="processOff" noWrap="nowrap" valign="middle">
    &nbsp;<A HREF="javascript: doSubmitCheck('<%=s_process_link%>', '<%=s_current_method%>;<%=s_process_method%>');" class=processOff><tsa:label labelName="<%=s_process_label %>" defaultString="<%=s_process_label %>" checkRequired="false" /></A>&nbsp;
  </tsa:td>
</tsa:tr>
<%  }
    i_step++;
  }
%>
<tsa:tr cssClass="processOff"><tsa:td colspan="3" height="5px">&nbsp;</tsa:td></tsa:tr>
<tsa:tr><tsa:td colspan="3" height="1px" cssClass="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></tsa:td></tsa:tr>
<tsa:tr cssClass="processOff"><tsa:td colspan="3" height="5px">&nbsp;</tsa:td></tsa:tr>

<tsa:tr>
  <tsa:td colspan="3">
    <table border="0" width="100%">
    <tsa:tr>
      <tsa:td align="left">
<%	if (s_previous_link.length() > 0 && !"SOURCING".equals(s_current_process)) { %>
        <table border="0">
        <tsa:tr>
          <tsa:td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');"><<</a></div></tsa:td>
          <tsa:td cssClass="processOn"><a href="javascript: doSubmit('<%=s_previous_link%>', '<%=s_current_method%>;<%=s_previous_method%>');" class=processOn title="<%=s_previous_label%>"><tsa:label labelName="back" defaultString="Back" /></a></tsa:td>
        </tsa:tr>
        </table>
<%	} %>
      </tsa:td>
      <tsa:td align="right">
<%

	if(s_req_status.compareTo(DocumentStatus.REQ_APPROVED) >= 0)
	{
		if (s_next_link.length() > 0 && i_current_step!=(i_step-1) ) {%>
		<table border="0">
        <tsa:tr>
          <tsa:td cssClass="processOn"><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');" class=processOn title="<%=s_next_label%>"><tsa:label labelName="next" defaultString="Next" /></a></tsa:td>
          <tsa:td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');">>></a></div></tsa:td>
        </tsa:tr>
        </table>
<%		}
	}else{
		if (s_next_link.length() > 0 && i_current_step!=(i_step-1)) { %>
        <table border=0>
        <tsa:tr>
          <tsa:td cssClass="processOn"><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');" class=processOn title="<%=s_next_label%>"><tsa:label labelName="next" defaultString="Next" /></a></tsa:td>
          <tsa:td><div id="pxsmallbutton"><a href="javascript: doSubmit('<%=s_next_link%>', '<%=s_current_method%>;<%=s_next_method%>');">>></a></div></tsa:td>
        </tsa:tr>
        </table>
<%		}
	} %>
      </tsa:td>
    </tsa:tr>
    </table>
  </tsa:td>
</tsa:tr>

<tsa:tr><tsa:td colspan="3" cssClass="processOff"><br></tsa:td></tsa:tr>
<%
if (((s_current_page.equals("/requests/req_validation.jsp")) && (s_req_status.compareTo(DocumentStatus.REQ_PLANNING_APPROVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCING) == 0 ||
		s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_APPROVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_ORDERED) == 0 || 
		s_req_status.compareTo(DocumentStatus.REQ_RECEIVED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_KITTED) == 0 || s_req_status.compareTo(DocumentStatus.REQ_ISSUED) == 0 || 
		s_req_status.compareTo(DocumentStatus.REQ_CLOSED) == 0)) || (s_req_type.equals("M") && s_current_page.equals("/requests/req_review.jsp")  && s_req_status.compareTo(DocumentStatus.REQ_PLANNING_SOURCED) >= 0 ))
{%>
<tsa:tr>
	<tsa:td colspan="3" cssClass="processOff" align="center">
		<table border=0 cellpadding=1 cellspacing=0>
		<tsa:tr>
			<tsa:td align="center"><DIV id="sourcing_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: sourcing(); void(0);" onclick="this.blur();"><tsa:label labelName="ass-sourcing" defaultString="Sourcing" /></a></div></div></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
<% } else if (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) < 0 || s_req_status.compareTo(DocumentStatus.RFQ_PRICED) == 0 || s_req_status.compareTo(DocumentStatus.TEMPLATE) == 0 || s_req_status.compareTo(DocumentStatus.INV_RETURNING) == 0 
		|| (s_req_status.compareTo(DocumentStatus.REQ_FORWARDED) >= 0 && user.isAFpe()))
{ %>
<tsa:tr>
  <tsa:td colspan="3" cssClass="processOff" align="center">
    <table border=0 cellpadding=1 cellspacing=0>
    <tsa:tr>
<%		if ( (s_current_page.equals("/requests/req_validation.jsp")) && !HiltonUtility.isNA(s_req_number) && !s_req_number.equals("TEMPLATE") ) {
           	if (s_req_type.equals("N") && s_req_status.equals(DocumentStatus.RFQ_PRICED))	{
           /* Priced Pricing Requisition */ %>
      			<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: validateReq('REPRICING'); void(0);"><tsa:label labelName="ass-reprice" defaultString="Reprice" /></a></div></tsa:td>
<%			}
           	if ( (!((new BigDecimal( s_req_status )).intValue() >= 9020) && (!s_req_type.equals("P") || (s_req_type.equals("P") && user.isAFpe())))
           			|| (s_req_type.equals("I") && ((new BigDecimal( s_req_status )).intValue() == 5155 || (new BigDecimal( s_req_status )).intValue() == 5160))) { %>
      			<tsa:td align="center"><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: forwardReq(); void(0);" onclick="this.blur();"><tsa:label labelName="ass-forward" defaultString="Forward" /></a></div></div></tsa:td>
<%			}
      	} else if ((((s_current_page.equals("/requests/req_review.jsp")) && s_req_status.compareTo(DocumentStatus.REQ_INPROGRESS) > 0) || s_current_page.equals("/requests/req_validation.jsp")) && !HiltonUtility.isNA(s_req_number) && 
      			!s_req_number.equals("TEMPLATE") && user.isAFpe() && !s_req_type.equals("M")) { %>
      		<tsa:td align="center"><DIV id="forward_link" style="visibility: visible;"><div id="pxbutton"><a href="javascript: forwardReq(); void(0);" onclick="this.blur();"><tsa:label labelName="ass-forward" defaultString="Forward" /></a></div></div></tsa:td>
<%		} else {
         	if(oid.equalsIgnoreCase("vse06p")){%>
      		<tsa:td align="center"><div id="pxbutton"><a href="javascript: reqSaveCheckAccount(); void(0);"><tsa:label labelName="ass-save" defaultString="Save" /></a></div></tsa:td>
<%			} else if (!s_current_page.equals("/requests/req_review.jsp") || HiltonUtility.isNA(s_req_number)) { %>
      		<tsa:td align="center"><div id="pxbutton"><a href="javascript: reqSave(); void(0);"><tsa:label labelName="ass-save" defaultString="Save" /></a></div></tsa:td>
<%			}
        } %>
    </tsa:tr>
    </table>
  </tsa:td>
</tsa:tr>
<%		}%>
<%	if (s_req_status.compareTo(DocumentStatus.REQ_APPROVING) == 0 && s_req_appBy.equalsIgnoreCase(uid)) { %>
<tsa:tr>
	<tsa:td colspan="3" cssClass="processOff" align="center">
		<table border=0 cellpadding=1 cellspacing=0>
			<tsa:tr>
			<%	if (s_current_page.equals("/requests/req_review.jsp") && !HiltonUtility.isNA(s_req_number)) { %>
				<tsa:td align="center"><div id="pxbutton"><a href="javascript: reqGoToApprove(); void(0);"><tsa:label labelName="ass-gotoApprove" defaultString="Go to Approve" /></a></div></tsa:td>
			<%	} %>
			</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
<%	} %>

</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	var autoReqNumber = <%=s_autonumber.equals("Y")%>;
	var showAutoReqNumber = <%=s_showauto.equals("Y")%>;

	function doSubmitCheck(s_process_link, method)
	{
		<% if (propertiesManager.getProperty("REQ OPTIONS", "REQUIREUDF10IFUDF7", "N").equalsIgnoreCase("Y") &&
			   "/puridiom/requests/req_general_info.jsp".equals(requestURI)) { %>
			var udf7 = '';
			var udf10 = '';

			if(frm.RequisitionHeader_udf7Code){
				udf7 = frm.RequisitionHeader_udf7Code.value;
			} else {
				udf7 = '${requisitionHeader.udf7Code}';
			}

			if(frm.RequisitionHeader_udf10Code){
				udf10 = frm.RequisitionHeader_udf10Code.value;
			} else {
				udf10 = '${requisitionHeader.udf10Code}';
			}

			<% if(s_req_status.equals("1000")){%>
				if(udf7 != null && udf7 != ''){
					if(udf10 == null || udf10 == ''){
						alert("<%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "RQ10", "Request UDF 10", moduleType, false) %> is Required!");
					} else {
						doSubmit(s_process_link, method);
					}
				}else {
					doSubmit(s_process_link, method);
				}
			<% } else{%>
				doSubmit(s_process_link, method);
			<% }
		} else { %>
			doSubmit(s_process_link, method);
	<%	} %>
	}

// end hiding contents -->
</SCRIPT>