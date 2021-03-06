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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Punch / Perf.</div>
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
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_paper_ink.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);">Paper/Ink</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_carbon.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);">Carbon</a></b></td>
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
			<TR>
				<TD>
				<TABLE WIDTH="100%" ALIGN="CENTER" BORDER="0" CELLSPACING="0" CELLPADDING="0" CLASS="htable">
					<TR CLASS="bnav">
						<TD HEIGHT="1px" COLSPAN="9"></TD>
					</TR>
					<TR>
						<TD COLSPAN="6"></TD>
						<TD COLSPAN="7"><BR></TD>
						<TD WIDTH="1px" CLASS="bnav"><BR></TD>
					</TR>
				</TABLE>
				<TABLE WIDTH="100%" ALIGN="CENTER" BORDER="1" CELLSPACING="2" CELLPADDING="2" >
					<TR align="center" CLASS="hdr12">
						<TD ROWSPAN=2 VALIGN=bottom>Part</TD>
						<TD COLSPAN=4>Punching</TD>
						<TD>Perforation</TD>
						<TD COLSPAN=2>Marginal Perf.</TD>
						<TD COLSPAN=2>Other Perf.</TD>
					</TR>
					<TR align="center" CLASS="hdr12">
						<TD># Holes</TD>
						<TD>Size</TD>
						<TD>Ctr. to Ctr.</TD>
						<TD>Pos</TD>
						<TD>Type</TD>
						<TD>Left</TD>
						<TD>Right</TD>
						<TD>Top</TD>
						<TD>Bottom</TD>
					</TR>

<%
	List invFormList = (List)request.getAttribute("invFormPart");
	//int rows = 12;
	if (invFormList != null)
	{
		for (int i = 0 ;i < invFormList.size(); i++)
		{
			InvFormPart invFormPart = (InvFormPart) invFormList.get(i);
%>
					<TR align="center" >
						<TD><b><%=(i + 1)%><tsa:hidden name="InvFormPart_formPart" value="<%=(i + 1)%>"/></b></TD>
						<TD><input type="text" name="InvFormPart_punchHoles" size=5 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getPunchHoles(),0)%>" style="text-align:right"></TD>
						<TD><input type="text" name="InvFormPart_punchSize" size=8 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getPunchSize(),2)%>" style="text-align:right"></TD>
						<TD><input type="text" name="InvFormPart_punchCenter" size=8 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getPunchCenter(),2)%>" style="text-align:right"></TD>
						<TD><input type="text" name="InvFormPart_punchPos" size=12 value = "<%=Utility.ckNull(invFormPart.getPunchPos())%>"></TD>
						<TD><input type="text" name="InvFormPart_perfType" size=12 value = "<%=Utility.ckNull(invFormPart.getPerfType())%>"></TD>
						<TD><input type="text" name="InvFormPart_mperfLeft" size=8 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getMperfLeft(),2)%>" style="text-align:right"></TD>
						<TD><input type="text" name="InvFormPart_mperfRight" size=8 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getMperfRight(),2)%>" style="text-align:right"></TD>
						<TD><input type="text" name="InvFormPart_operfLeft" size=8 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getOperfLeft(),2)%>" style="text-align:right"></TD>
						<TD><input type="text" name="InvFormPart_operfRight" size=8 value = "<%=Utility.getBigDecimalFormatted(invFormPart.getOperfRight(),2)%>" style="text-align:right"></TD>
					</TR>
<% } }%>
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
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/forms/inv_item_punch_perf.jsp', 'InvFormPartUpdateMultiple;InvFormPartRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>