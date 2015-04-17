<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
 <%@ page import="com.tsa.puridiom.steporder.*" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>

<tsa:hidden name="RequisitionHeader_requisitionType" value=""/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="invbrowse" value=""/>

<%
	int purchaseReqAccess = role.getAccessRights("REQP");
	int supplyReqAccess = role.getAccessRights("REQS");
	int transferReqAccess = role.getAccessRights("REQT");
	int replenishReqAccess = role.getAccessRights("REQR");
	int invReturnReqAccess = role.getAccessRights("REQI");
	int purReturnReqAccess = role.getAccessRights("REQU");
	int changeReqAccess = role.getAccessRights("REQC");
	int releaseReqAccess = role.getAccessRights("REQE");
	int pricingReqAccess = role.getAccessRights("REQN");
	int checkReqAccess = role.getAccessRights("REQK");
	int projectReqAccess = role.getAccessRights("REQO");
	int revisionReqAccess = role.getAccessRights("REQV");
	int itReqAccess = role.getAccessRights("REQH");
	int msrReqAccess = role.getAccessRights("REQM") ;
%>
<% String displayViewType = PropertiesManager.getInstance(oid).getProperty("REQ OPTIONS", "DISPLAYVIEWTYPE", "N"); %>
<table width="680px" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="150px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12">
				<tsa:label labelName="reqtypeselection" defaultString="Requisition Type Selection" />
				</div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" align="right">
<%
		if (displayViewType.equalsIgnoreCase("Y")) {
%>
		<table border="0" cellspacing="0" cellpadding="0" border="0">
		<tsa:tr>
			<tsa:td width="100px">&nbsp;</tsa:td>
			<tsa:td align="right">&nbsp;<tsa:input type="radio" name="as_view_type" value="CLASSIC" /></tsa:td>
			<tsa:td width="100px" noWrap="nowrap"><b><tsa:label labelName="classicview" defaultString="Classic View" /></b></tsa:td>
			<tsa:td align="right"><tsa:input type="radio" name="as_view_type" value="WIZARD" checked="CHECKED" /></tsa:td>
			<tsa:td noWrap="nowrap"><b><tsa:label labelName="wizardview" defaultString="Wizard View" /></b></tsa:td>
			<tsa:td width="10px">&nbsp;</tsa:td>
		</tsa:tr>
		</table>
<%	}	%>

		<table cellpadding="0" cellspacing="0" border="0">
			<tsa:tr>
				<tsa:td width="1000px" height="1px" align="center"><tsa:label labelName="req-type-selection" defaultString="" /></tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td width="1000px" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
			</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="680px" align="center">
	<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="650" border="0" ALIGN="CENTER">
<%	if (purchaseReqAccess > 0) { %>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('P'); void(0);" title="Clicking here will allow you to create a Purchase Request."; class="formType">
			<tsa:label labelName="reqtype-p" defaultString="Purchase Request" />
			</A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-p" defaultString="Request to Purchase Goods, Services or Printed Material from a Supplier." />
		</tsa:td>
	</tsa:tr>
<%	}
		if (projectReqAccess > 0) {%>
	<!--<TR>
		<TD ALIGN="RIGHT" HEIGHT="40" WIDTH="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</TD>
		<TD NOWRAP WIDTH="25%" class=formType>
			<A HREF="javascript: createReq('O'); void(0);" title="Clicking here will allow you to create a Procurement Request."; class=formType>Procurement Request</A>
		</TD>
		<TD><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reqtxt-o", "Request to engage Procurement's help to set-up a long-running Project or Contract for Services.", false)%></TD>
	</TR>-->
<%	}
		if (msrReqAccess > 0) {%>
		<tsa:tr>
			<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
			<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
				<A HREF="javascript: createReq('M'); void(0);" title="Clicking here will allow you to create a Materials and Services Request."; class="formType">
				<tsa:label labelName="reqtype-m" defaultString="MSR Request" /></A>
			</tsa:td>
			<tsa:td>
			<tsa:label labelName="reqtxt-m" defaultString="Request for Materials and Services." />
			</tsa:td>
		</tsa:tr>
	<%	}
	if (supplyReqAccess > 0) {%>
	<tsa:tr>
		<% if (oid.equalsIgnoreCase("VSE06P")) { %>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('S'); void(0);" title="Clicking here will allow you to create an Inventory Request."; class="formType">
			<tsa:label labelName="reqtype-s" defaultString="Inventory Request" />
			</A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-s" defaultString="Request Inventory items from a Facility location." />
		</tsa:td>
		<% } else { %>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('S'); void(0);" title="Clicking here will allow you to create a Supply Request."; class="formType">
			<tsa:label labelName="reqtype-s" defaultString="Supply Request" />
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-s" defaultString="Request Inventory items from one or more Inventory Warehouse locations." />
		</tsa:td>
		<% } %>
	</tsa:tr>
<%	}
	if (transferReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('T'); void(0);" title="Clicking here will allow you to create a Transfer Request."; class="formType">
			<tsa:label labelName="reqtype-t" defaultString="Transfer Request" />
			</A>
		</tsa:td>
		<tsa:td><tsa:label labelName="reqtxt-t" defaultString="To move items from one inventory location to another." /></tsa:td>
	</tsa:tr>
<%	}
	if (replenishReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<tsa:hidden name="inv_location" value=""/>
			<tsa:hidden name="as_action" value=""/>
			<A HREF="javascript: createReq('R'); void(0);" title="Clicking here will allow you to create a Replenish Request."; class="formType">
			<tsa:label labelName="reqtype-r" defaultString="Replenish Request" /></A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-r" defaultString="Request to order an item(s) to restock an inventory location." />
		</tsa:td>
	</tsa:tr>
<%	}
	if (invReturnReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('I'); void(0);" title="Clicking here will allow you to create a Inventory Return."; class="formType">
			<tsa:label labelName="reqtype-i" defaultString="Inventory Return" />
			</A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-i" defaultString="Request to return items to an inventory location." />
		</tsa:td>
	</tsa:tr>
<%	}
	if (purReturnReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('U'); void(0);" title="Clicking here will allow you to create a Purchase Return."; class="formType">
			<tsa:label labelName="reqtype-u" defaultString="Purchase Return" />
			</A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-u" defaultString="Request to return items to a supplier." />
		</tsa:td>
	</tsa:tr>
<%	}
	if (changeReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('C'); void(0);" title="Clicking here will allow you to create a Change Request."; class="formType">
			<tsa:label labelName="reqtype-c" defaultString="Change Request" />
			</A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-c" defaultString="Request to change an existing requisition." /></tsa:td>
	</tsa:tr>
<%	}
	if (releaseReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('E'); void(0);" title="Clicking here will allow you to create a Release Request."; class="formType">
			<tsa:label labelName="reqtype-e" defaultString="Release Request" /></A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-e" defaultString="Request for a release against a pre-negotiated Blanket Order." /></tsa:td>
	</tsa:tr>
<%	}
	if (pricingReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('N'); void(0);" title="Clicking here will allow you to create a Pricing Requisition."; class="formType">
			<tsa:label labelName="reqtype-n" defaultString="Pricing Requisition" /></A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-n" defaultString="Ask Purchasing to obtain prices only for Goods or Services in order to deliver cost savings or receive competitive pricing." /></tsa:td>
	</tsa:tr>
<%	}
	if (checkReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('K'); void(0);" title="Clicking here will allow you to create a Admin Check Request."; class="formType">
			<tsa:label labelName="reqtype-k" defaultString="Admin Check Request" /></A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-k" defaultString="Request for Accounts Payable to issue a check." />
		</tsa:td>
	</tsa:tr>
<%	}
	if (revisionReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('V'); void(0);" title="Clicking here will allow you to create a Revision Request."; class="formType">
			<tsa:label labelName="reqtype-v" defaultString="Revision Request" /></A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-v" defaultString="Request to add on to an existing Purchase Order." />
		</tsa:td>
	</tsa:tr>
<%	}
if (itReqAccess > 0) {%>
	<tsa:tr>
		<tsa:td align="RIGHT" height="40" width="5%"><IMG SRC="<%=contextPath%>/images/bullet.gif" BORDER="0">&nbsp;</tsa:td>
		<tsa:td noWrap="nowrap" width="25%" cssClass="formType">
			<A HREF="javascript: createReq('H'); void(0);" title="Clicking here will allow you to create a IT Requisition."; class="formType">
			<tsa:label labelName="reqtype-it" defaultString="IT Requisition." /></A>
		</tsa:td>
		<tsa:td>
		<tsa:label labelName="reqtxt-it" defaultString="Select this option to purchase hardware." />
		</tsa:td>
	</tsa:tr>
<%	} %>

	</TABLE>
	</tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var userId = '${esapi:encodeForJavaScript(userId)}';
	var department = '<%=user.getDepartment()%>';
	setNavCookie("/requests/req_select_type.jsp", "DoNothing", "Create New Requisition", true);

	function createReq(type)
	{
		setViewType();
		frm.RequisitionHeader_requisitionType.value = type;

		var newInputField;

		if (type == "C")	/*  Change Request  */
		{
			<% if (!user.getAdminBuyer().equalsIgnoreCase("Y")) { %>
				if (department.trim().length > 0)
				{
					newInputField = "<input type=\"hidden\" name=\"filter_txt\" value=\"" + department + "\">";
					newInputField = newInputField + "<input type=\"hidden\" name=\"colname\" value=\"RequisitionHeader_departmentCode\">";
					newInputField = newInputField + "<input type=\"hidden\" name=\"originalFilter\" value=\"Y\"><input type=\"hidden\" name=\"logicalOperator\"  value=\"\"><input type=\"hidden\" name=\"sort\"  value=\"ASC\"><input type=\"hidden\" name=\"operator\"  value=\"=\">";
					<% if (oid.equalsIgnoreCase("BLY07P")) { %>
					newInputField = newInputField + "<input type=\"hidden\" name=\"filter_txt\" value=\"" + userId + "\">";
					newInputField = newInputField + "<input type=\"hidden\" name=\"colname\" value=\"RequisitionHeader_buyer\">";
					newInputField = newInputField + "<input type=\"hidden\" name=\"originalFilter\" value=\"Y\"><input type=\"hidden\" name=\"logicalOperator\"  value=\"OR\"><input type=\"hidden\" name=\"sort\"  value=\"ASC\"><input type=\"hidden\" name=\"operator\"  value=\"=\">";
					//PoHeader.buyer = USER_ID
					newInputField = newInputField + "<input type=\"hidden\" name=\"filter_txt\" value=\"" + userId + "\">";
					newInputField = newInputField + "<input type=\"hidden\" name=\"colname\" value=\"PoHeader_buyerCode\">";
					newInputField = newInputField + "<input type=\"hidden\" name=\"originalFilter\" value=\"Y\"><input type=\"hidden\" name=\"logicalOperator\"  value=\"OR\"><input type=\"hidden\" name=\"sort\"  value=\"ASC\"><input type=\"hidden\" name=\"operator\"  value=\"=\">";
					<% } %>
					//newInputField = newInputField + "<input type=\"hidden\" name=\"filter_txt\" value=\"" + userId + "\">";
					//newInputField = newInputField + "<input type=\"hidden\" name=\"colname\" value=\"RequisitionHeader_owner\">";
					//newInputField = newInputField + "<input type=\"hidden\" name=\"originalFilter\" value=\"Y\"><input type=\"hidden\" name=\"logicalOperator\"  value=\"AND\"><input type=\"hidden\" name=\"sort\"  value=\"ASC\"><input type=\"hidden\" name=\"operator\"  value=\"=\">";
					setHiddenFields(newInputField);
				}
				else
				{
					//newInputField = "<input type=\"hidden\" name=\"filter_txt\" value=\"" + userId + "\">";
					//newInputField = newInputField + "<input type=\"hidden\" name=\"colname\" value=\"RequisitionHeader_owner\">";
					//newInputField = newInputField + "<input type=\"hidden\" name=\"originalFilter\" value=\"Y\"><input type=\"hidden\" name=\"logicalOperator\"  value=\"AND\"><input type=\"hidden\" name=\"sort\"  value=\"ASC\"><input type=\"hidden\" name=\"operator\"  value=\"=\">";
					setHiddenFields(newInputField);
				}
			<% }
			if ((user.getAdminBuyer().equalsIgnoreCase("Y") && role.getAccessRights("MUI")>=3) || user.isAFpe()) { %>
				browse('req_masbrowse');
			<% } else { %>
				browse('req_pobrowse');
			<% } %>
		}
		else if (type == "E")	/*  Release Request  */
		{
			var today = '<%=HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%>';

			if (department.trim().length > 0)
			{
				setAdvancedFilter("PoSecurity_id_accessId", "=", userId, "OR", "Y", "N");
				setAdvancedFilter("PoSecurity_id_accessId", "=", department, "", "Y", "N");
			}
			else
			{
				setOriginalFilter("PoSecurity_id_accessId", "=", userId);
			}

			setOriginalFilter("PoHeader_effectiveDate", "<=", today);
			setOriginalFilter("PoHeader_expirationDate", ">=", today);

			browse('req_blanketbrowse');
		}
		else if (type == 'R')	/*	 Replenish Request  */
		{
			browse('inventory_location');
		}
		else if (type == 'S')
		{
			frm.invbrowse.value = "TRUE";
			doSubmit('/requests/req_general_info.jsp', 'RequisitionCreate');
		}
		else if (type == 'T'){
			frm.invbrowse.value = "TRUE";
			doSubmit('/requests/req_general_info.jsp', 'RequisitionCreate');
		}
		else if(type == 'I'){
			frm.invbrowse.value = "TRUE";
			doSubmit('/requests/req_items.jsp', 'RequisitionCreate');
		}
		else if (type == 'K')	/**  Admin Check Request**/
		{
			newInputField = "<input type=\"hidden\" name=\"createAction\" value=\"SAVE\">";
			newInputField = newInputField + "<input type=\"hidden\" name=\"nonStandardItem\" value=\"N\">";
			setHiddenFields(newInputField);
			doSubmit('/requests/req_general_info.jsp', 'RequisitionCreate;RequisitionLineCreate');
		}
		else if (type == 'O')	/**  Procurement Request**/
		{
			newInputField = "<input type=\"hidden\" name=\"createAction\" value=\"SAVE\">";
			newInputField = newInputField + "<input type=\"hidden\" name=\"RequisitionLine_commodityCode\" value=\"CONTRACTS\">";
			setHiddenFields(newInputField);
			doSubmit('/requests/req_general_info_o.jsp', 'RequisitionCreate;RequisitionLineCreate');
		}
		else if (type == 'V')  /**  Revision Request**/
		{
			setOriginalFilter("PoHeader_status", ">=", "<%=DocumentStatus.PO_AWARDED%>");
			setOriginalFilter("PoHeader_status", "<", "<%=DocumentStatus.CLOSED%>");
			setOriginalFilter("PoHeader_status", "<>", "<%=DocumentStatus.PO_AMENDED%>");
			browse('req-rev-pobrowse');
		}
		else
		{
			//doSubmit('/requests/req_items.jsp', 'RequisitionCreate');
			<%
				String s_reqsteps = "reqsteps_p";
				ProcessSteps steps = new ProcessSteps(s_reqsteps, oid);
				String s_process_link = steps.getLink(0);
				String s_process_method = steps.getMethod(0);
			%>
			doSubmit('<%=s_process_link%>', 'RequisitionCreate;<%=s_process_method%>');
		}
	}

	function setViewType()
	{
		var displayViewType = "<%=displayViewType%>";
		if (displayViewType != "Y")
		{
			frm.viewType.value = "WIZARD";
		}
		else
		{
			var viewType = "";
			var types = frm.elements.item("as_view_type");

			for (var i = 0; i < types.length; i++)
			{
				if (frm.as_view_type[i].checked) {
					viewType = frm.as_view_type[i].value
				}
			}

			if (viewType == "CLASSIC")
			{
				frm.viewType.value = "CLASSIC";
			}
			else if (viewType == "WIZARD")
			{
				frm.viewType.value = "WIZARD";
			}
		}
	}

// End Hide script -->
</SCRIPT>
<%@ include file="/system/prevent_caching.jsp" %>