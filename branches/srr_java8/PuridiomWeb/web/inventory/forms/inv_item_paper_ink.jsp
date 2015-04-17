<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	String s_itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String s_description = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
%>

<tsa:hidden name="InvFormData_itemNumber" value="<%=s_itemNumber%>"/>
<tsa:hidden name="InvFormPart_itemNumber" value="<%=s_itemNumber%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=s_itemNumber%>"/>
<tsa:hidden name="InvItem_description" value="<%=s_description%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="stdTable_tableType" value=""/>
<tsa:hidden name="itemAction" value="UPDATE"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td nowrap align=right colspan=2 class=formType>Item Number:&nbsp;</td>
	<td nowrap><b><%=headerEncoder.encodeForHTML(s_itemNumber)%></b></td>
</tr>
<tr>
	<td nowrap align=right valign=top colspan=2 class=formType>Description:&nbsp;</td>
	<td><b><%=headerEncoder.encodeForHTML(s_description)%></b></td>
</tr>
<tr><td colspan=3><br><br></td></tr>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Paper / Ink</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_form.jsp', 'InvFormPartUpdateMultiple;InvFormDataRetrieveById'); void(0);">Form</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_numbering.jsp', 'InvFormPartUpdateMultiple;InvFormDataRetrieveById'); void(0);">Numbering</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_fastening_use.jsp', 'InvFormPartUpdateMultiple;InvFormDataRetrieveById'); void(0);">Fastening/Use</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_env_label.jsp', 'InvFormPartUpdateMultiple;InvFormDataRetrieveById'); void(0);">Env/Label</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_packaging.jsp', 'InvFormPartUpdateMultiple;InvFormDataRetrieveById'); void(0);">Packaging</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_size.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);">Size</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_copies.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);">Copies</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_carbon.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);">Carbon</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_punch_perf.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);">Punch/Perf.</a></b></td>
		</tr>
		</table>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 cellspacing=0 cellpadding=0 height=100% align=center>
		<tr>
			<td>
				<TABLE WIDTH=680px ALIGN="CENTER" BORDER="1" CELLSPACING="2" CELLPADDING="2" >
					<TR align="center" CLASS="hdr12">
						<TD ROWSPAN=2 VALIGN=bottom>Part</TD>
						<TD COLSPAN=3>Paper</TD>
						<TD COLSPAN=2>Ink</TD>
						<TD COLSPAN=2>Screening</TD>
						<TD ROWSPAN=2 VALIGN=top>Turn Type</TD>
					</TR>
					<TR align="center" CLASS="hdr12">
						<TD><a href="javascript: browseStd('InvFormPart_paperColor','FCOL');">Color</a></TD>
						<TD>Weight</TD>
						<TD><a href="javascript: browseStd('InvFormPart_paperGrade','FGRD');">Grade</a></TD>
						<TD><a href="javascript: browseStd('InvFormPart_inkFront','FINK');">Front</a></TD>
						<TD><a href="javascript: browseStd('InvFormPart_inkBack','FINK');">Back</a></TD>
						<TD>Paper UDF</TD>
						<TD>Ink UDF</TD>
					</TR>

<%
	List invFormList = (List)request.getAttribute("invFormPart");
	int i_rowcount = 0;
	if (invFormList != null)
	{
		for (int i = 0 ;i < invFormList.size(); i++)
		{
			String s_set_row = "ONFOCUS='setCurrentRow("+i_rowcount+");'";
			InvFormPart invFormPart = (InvFormPart) invFormList.get(i);
%>
					<TR align="center" >
						<TD><b><%=(i + 1)%><tsa:hidden name="InvFormPart_formPart" value="<%=(i + 1)%>"/></b></TD>
						<TD><input type="text" name="InvFormPart_paperColor" size=10 value="<%=Utility.ckNull(invFormPart.getPaperColor())%>" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_paperWeight" size=10 value="<%=Utility.getBigDecimalFormatted(invFormPart.getPaperWeight(),2)%>" style="text-align:right" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_paperGrade" size=10 value="<%=Utility.ckNull(invFormPart.getPaperGrade())%>" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_inkFront" size=10 value="<%=Utility.ckNull(invFormPart.getInkFront())%>" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_inkBack" size=10 value="<%=Utility.ckNull(invFormPart.getInkBack())%>" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_paperUdf" size=10 value="<%=Utility.ckNull(invFormPart.getPaperUdf())%>" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_inkUdf" size=10 value="<%=Utility.ckNull(invFormPart.getInkUdf())%>" <%=s_set_row%>></TD>
						<TD><input type="text" name="InvFormPart_turnType" size=10 value="<%=Utility.ckNull(invFormPart.getTurnType())%>" <%=s_set_row%>></TD>
					</TR>
<%		i_rowcount++;
		}
	}%>
				</TABLE>
				</TD>
			</TR>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/forms/inv_item_paper_ink.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var currentRow = 0;

	function setCurrentRow(row)
	{
		currentRow = row;
	}

// End Hide script -->
</SCRIPT>