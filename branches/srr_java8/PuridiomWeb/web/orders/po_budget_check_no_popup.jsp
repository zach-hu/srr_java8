<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%

 PoHeader poHeader = (PoHeader)request.getAttribute("header");
  List	errorList = (List) request.getAttribute("errorList");
  String s_po_number = poHeader.getPoNumber();
  boolean bForward = false;
  String poAction = (String)request.getAttribute("poaction");
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

  String	pricingAction = "";
  if (poAction == null){ poAction = "BUDGETCHECK";	}

  String	s_status = (String) request.getAttribute("budgetStatus");
  String	s_form_type = request.getParameter("budgetType");
  String	s_action_code = request.getParameter("budgetAction");
  String	s_action = request.getParameter("budgetActionText");
  String	errorMsg = "";

%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_status" value="<%=poHeader.getStatus()%>"/>
<tsa:hidden name="poaction" value=""/>
<tsa:hidden name="poForwardOption" value=""/>
<tsa:hidden name="goforward" value=""/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Budget Check  Results</div>
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
    <font class="formType"><%=OrderType.toString(poHeader.getPoType(), oid)%> </font><font class="hdr12">#<%=s_po_number%></font>
  </td>
</tr>

<tr><td><br></td></tr>
<tr>
  <td width="100%" align="center" valign="top">
  <div id="budgetrules" style="display:none;">
		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 600px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
		<table border="0" cellpadding="0" cellspacing="0" width="600px">
			<tr class="mnav" height="18px">
				<td nowrap class="mnav" align="center">Error Message</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Budget Id</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Budget Authority</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Initial Budget</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Budget Balance</td>
				<td width="15px">&nbsp;</td>
			</tr>
		<%
				String classType = "summary";
				String severity = "";
				int counter = 1;
				for (int i = 0; i < errorList.size(); i++)
				{
					Hashtable ht = (Hashtable) errorList.get(i) ;
					String		bError = (String) ht.get("budgetError");
					String		bId = (String) ht.get("budgetId");
					String		bAuth = (String) ht.get("budgetAuth");
					BigDecimal		bBalance = (BigDecimal) ht.get("budgetBalance");
					BigDecimal		bInitial = (BigDecimal) ht.get("budgetInitial");
					String		bSeverity = (String) ht.get("errorSeverity");
		%>
					<tr class="<%=classType%>" height="20px">
						<td nowrap class="<%=classType%>" align="left" valign="top"><%=bError%>.</td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="left" valign="top"><%=bId%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="left" valign="top"><%=bAuth%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="right" valign="top"><%=HiltonUtility.getFormattedDollar(bInitial,oid)%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="right" valign="top"><%=HiltonUtility.getFormattedDollar(bBalance,oid)%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
					</tr>
				<%if(classType.equalsIgnoreCase("browseRow"))
		    		{
						classType = "summary";
		    		}
					else if(classType.equalsIgnoreCase("summary"))
					{
						classType = "browseRow";
					}
					counter++;
				} %>
			<tr ><td nowrap colspan="10"><hr class=browseHR></td></tr>
		</table>
		<table>
			<% 	if (s_status.equalsIgnoreCase("FAILED")) {  %>
					<TR><TD ALIGN="CENTER"><BR><B>Budget Check Failed!:<br><br><br></B></TD></TR>
			<%	} else if (s_status.equalsIgnoreCase("IGNORE")) { %>
					<TR><TD ALIGN="CENTER"><BR><BR><BR><B>A Budget Check is not required for this Order!</B><BR><BR></TD></TR>
			<%	} else if (s_status.equalsIgnoreCase("WARNING")) { %>
					<TR><TD ALIGN="CENTER"><BR><BR><B>Budget Check Passed with warnings!</B><BR><BR></TD></TR>
			<%	} else { %>
					<TR><TD ALIGN="CENTER"><BR><BR><B>Budget Check Passed!</B><BR><BR></TD></TR>
			<%	}  %>
		</table>
		</div>
		</div>
  </td>
</tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="655px">
	<TR>
	<%if (s_status.equals("FAILED") || poAction.equals("BUDGETCHECK") ) {%>
		<TD align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order"></a></TD>
	<%} else if ( s_status.equals("WARNING")  ) {%>
		<TD align="center"><a HREF="javascript: orderForward();"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward Order"></a></TD>
		<TD align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order"></a></TD>
<%}%>

</TR>
</TABLE>

</FORM>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  self.focus();
<%	if ((s_status.equals("PASSED") || s_status.equalsIgnoreCase("IGNORE")) && poAction.equalsIgnoreCase("FORWARD"))
		{ %>
			displayArea('budgetrules');
	    	orderForward();
		<%}
  		else
  		{%>
  			displayArea('budgetrules');
  		<%}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function orderForward()
  {
  		frm.poaction.value = "FORWARD";

<%	if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") &&
		(!poHeader.getPoType().equals("CT") || propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y"))) { %>
      //doSubmitToPopup('/orders/po_forward_options.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
      doSubmit('/orders/po_award_options.jsp', 'PoAwardOptions');
<%	}
	else
	{ %>
      frm.poForwardOption.value = "F";	/*  award Order*/
     // doSubmit('/orders/po_forward_options.jsp', 'PoForward');
      doSubmit('/orders/po_forward_options.jsp', 'PoForward');
<%	} %>
  }


  function forward()
  {

      frm.poaction.value = "FORWARD";
      doSubmit('/orders/po_forward.jsp', 'PoForward');
  }

  function returnMe()
  {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	doSubmit('orders/po_summary.jsp', 'PoRetrieve');
<%	} else { %>
	doSubmit('orders/po_review.jsp', 'PoRetrieve');
<%	} %>
  }

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>