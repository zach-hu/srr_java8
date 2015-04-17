<%@ page language="java" errorPage="/system/error.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/context_path.jsp"%>
<%@ include file="/system/header.jsp"%>
<%@ page import="com.tsa.puridiom.property.PropertiesManager"%>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String s_commodity = HiltonUtility.ckNull((String) request.getAttribute("Commodity_commodity"));
	String s_commodity_description = HiltonUtility.ckNull((String) request.getAttribute("Commodity_description"));
	String s_commodity_buyerCode = HiltonUtility.ckNull((String) request.getAttribute("Commodity_buyerCode"));
	String s_buyerName = HiltonUtility.ckNull((String) request.getAttribute("as_buyerName"));

	String s_default_buyer = "";
	if(s_commodity_buyerCode.length()> 0){
		s_default_buyer = s_commodity_buyerCode + "  " + s_buyerName;
	}

	List commDeptBuyerList = (List) request.getAttribute("commodityDepartmentBuyer");
	if (commDeptBuyerList == null) {
		commDeptBuyerList = new ArrayList();
	}
%>

<tsa:hidden name="deleteall" value=""/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
	<tr>
		<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
			<tr>
				<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
			</tr>
			<tr>
				<td nowrap class=hdr12 valign=middle>
				<div style="margin-left: 10px; margin-right: 10px" class=hdr12>Commodity <%= s_commodity %>	- <%=s_commodity_description %></div>
				</td>
			</tr>
		</table>
		</td>
		<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
		<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr>
				<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
			</tr>
			<tr>
				<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
		<td valign=top>
		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH=500px>
			<tr>
				<TD WIDTH=20px>&nbsp;</TD>
				<TD <%=HtmlWriter.isVisible(oid, "allocationMethod")%> ><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defaultBuyer", "Default Buyer")%>: </B>&nbsp;&nbsp; <%=s_default_buyer%></TD>
			</tr>
		</TABLE>

		<BR>

		<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0">
			<TR>
				<TD VALIGN="TOP">
				<TABLE ID=commoditydeparment BORDER="0" CELLPADDING="2" WIDTH=100%>
					<TR VALIGN="MIDDLE">
						<TD NOWRAP><tsa:hidden name="as_rows" value="<%=commDeptBuyerList.size() %>"/></TD>
						<TD NOWRAP></TD>
						<TD NOWRAP width="20%"><A HREF="javascript: browseDepartment('CommodityDepartmentBuyer_departmentCode', 'department'); void(0);">Department</A></TD>
						<TD NOWRAP width="20%"><A HREF="javascript: browseBuyer('CommodityDepartmentBuyer_userId', 'buyer');">Buyer</A></TD>
						<TD NOWRAP><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyerName", "Buyer Name")%></TD>
						<TD WIDTH=10px><IMG SRC="<%=contextPath%>/images/none.gif" border=0></TD>
					</TR>

<% 		String	s_set_row = "";
		int i_rowcount = 0;
		for (int i = 0; i < commDeptBuyerList.size(); i++) {
			CommodityDepartmentBuyer commDeptBuyer = (CommodityDepartmentBuyer) commDeptBuyerList.get(i);
			String s_department=commDeptBuyer.getDepartmentCode();
			String s_buyer=commDeptBuyer.getUserId();
			UserProfile	userProfile = UserManager.getInstance().getUser(oid, s_buyer);
			String s_nameBuyer=userProfile.getDisplayName();
%>
					<TR>
						<TD WIDTH=15px></TD>
						<TD ID=acc_0 ALIGN="LEFT"><tsa:hidden name="CommodityDepartmentBuyer_commodity" SIZE="20" MAXLENGTH="20" value="<%= s_commodity%>" ONCHANGE="upperCase(this);" onfocus="setCurrentRow(0);"/></TD>
						<TD ID=acc_1 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="CommodityDepartmentBuyer_departmentCode" SIZE="20" MAXLENGTH="20" value="<%=s_department%>" ONCHANGE="upperCase(this);" onfocus="setCurrentRow(0);" ></TD>
						<TD ID=acc_2 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="CommodityDepartmentBuyer_userId" SIZE="20" MAXLENGTH="20" value="<%=s_buyer%>" ONCHANGE="upperCase(this);getNewInfo('buyer', this);" onfocus="setCurrentRow(0);"></TD>
						<TD ID=acc_3 ALIGN="LEFT"><INPUT TYPE="TEXT" NAME="as_buyerName" SIZE="40" MAXLENGTH="40" value="<%=s_nameBuyer %>" ONCHANGE="upperCase(this);" DISABLED></TD>
						<TD ID=acc_del_><A href="javascript: if (confirm('Are you sure you wish to delete this row?')) { deleteMe(<%=i%>); } void(0);" TABINDEX="999"><IMG SRC="<%=contextPath%>/images/delete.gif" ALT="Delete" BORDER="0"></A></TD>
					</TR>

<%			i_rowcount++;
		}%>
				</TABLE>
				<HR ALIGN="CENTER" WIDTH=95%>
				<TABLE BORDER="0" CELLPADDING="0" WIDTH=100%>
					<TR>
						<TD WIDTH=15px>&nbsp;</TD>
						<TD ALIGN="LEFT"><A HREF="javascript: addNew(); void(0);"><FONT CLASS="reset"><B>Add new</B></FONT></A></TD>
						<TD ALIGN="RIGHT"><A HREF="javascript: deleteAll(); void(0);"><FONT CLASS="reset"><B>Delete all</B></FONT></A></TD>
					</TR>
				</TABLE>
			</TR>
			<tsa:hidden name="as_maxrows" value="<%=i_rowcount%>"/></TD>
		</TABLE>
		</TD>
	</tr>
	<tr>
		<TD>

		</TD>
	</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 WIDTH=50%>
<tr>
	<TD WIDTH=15%>&nbsp;</TD>
	<td WIDTH=20%><a href="javascript: saveComDeptBuyer(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<!-- td WIDTH=20%><a href="javascript: doSubmit('/admin/systemtables/commodity.jsp', 'CommodityDepartmentBuyerUpdate;CommodityRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td-->
	<TD WIDTH=10%>&nbsp;</TD>
	<td WIDTH=20%><a href="javascript: returnCommodity(); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
	<!-- td WIDTH=20%><a href="javascript: doSubmit('/admin/systemtables/commodity_bydeparment.jsp', 'DoNothing'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td-->
	<TD WIDTH=10%>&nbsp;</TD>
</tr>
</table>


<%@ include file="/admin/systemtables/commoditydeparment_rows.jsp"%>
<%@ include file="/system/footer.jsp"%>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	//setNavCookie("/admin/systemtables/commodity.jsp", "DoNothing");

	var myTable = document.getElementById("commoditydeparment");
	var TotalRows = myTable.rows.length - 1;

	var accRows = frm.as_rows.value;
	var maxRows = frm.as_maxrows.value;

	var currentRow = 0;


	if (TotalRows == 0)
	{
		addNew();
	}


	function thisLoad()
	{
		f_StartIt();
	}

	function browseDepartment(formField, xml)
	{
  		var fldObject = formField;
  		popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";"; // ";allowBrowse=" + frm.allowBrowse.value;
  		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function browseBuyer(formField, xml)
	{
  		var fldObject = formField;
  		popupParameters = "formField=" + formField +";browseName=" + xml + ";index=" + currentRow + ";"; // ";allowBrowse=" + frm.allowBrowse.value;
  		doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
	}

	function saveComDeptBuyer()
   {
     var newInputField = "<input type='hidden' name='Commodity_commodity' value='" + "<%=s_commodity %>" + "'>";

     setHiddenFields(newInputField);
	 doSubmit('/admin/systemtables/commodity.jsp', 'CommodityDepartmentBuyerUpdateLines;CommodityRetrieveById');
	 }

	function returnCommodity()
   {
     var newInputField = "<input type='hidden' name='Commodity_commodity' value='" + "<%=s_commodity %>" + "'>";
     setHiddenFields(newInputField);
	 doSubmit('/admin/systemtables/commodity.jsp', 'CommodityRetrieveById');
	 }

// End Hide script -->
</SCRIPT>
