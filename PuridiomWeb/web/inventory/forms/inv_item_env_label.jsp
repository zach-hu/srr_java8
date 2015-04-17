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

	InvFormData invFormData = (InvFormData)request.getAttribute("invFormData");
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Env/Label</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_form.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Form</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_numbering.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Numbering</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_fastening_use.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Fastening/Use</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_packaging.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Packaging</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_size.jsp', 'InvFormDataUpdate;InvFormPartRetrieve'); void(0);">Size</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_copies.jsp', 'InvFormDataUpdate;InvFormPartRetrieve'); void(0);">Copies</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_paper_ink.jsp', 'InvFormDataUpdate;InvFormPartRetrieve'); void(0);">Paper/Ink</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_carbon.jsp', 'InvFormDataUpdate;InvFormPartRetrieve'); void(0);">Carbon</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_punch_perf.jsp', 'InvFormDataUpdate;InvFormPartRetrieve'); void(0);">Punch/Perf.</a></b></td>
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
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
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
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD NOWRAP CLASS="mtitleopen"><b>Envelope:</b></TD>
						<TD COLSPAN="6">&nbsp;</TD>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_envSize','FSIZ');">Envelope Size:</a></TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envSize" value="<%=Utility.ckNull(invFormData.getEnvSize())%>"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Window Size:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_winSize"  MAXLENGTH="12" value="<%=Utility.ckNull(invFormData.getWinSize()) %>" ></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_envType','FTYP');">Envelope Type:</a></TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envType" value="<%=Utility.ckNull(invFormData.getEnvType())%>" onchange="upperCase(this)"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Pos. From Left:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_posFromleft" MAXLENGTH="12" value="<%=Utility.getBigDecimalFormatted(invFormData.getPosFromleft(),2)%>" style="text-align:right"></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Envelope Color:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envColor" value="<%=Utility.ckNull(invFormData.getEnvColor())%>" onchange="upperCase(this)"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">From Bottom:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_posFrombot" MAXLENGTH="12" value="<%=Utility.getBigDecimalFormatted(invFormData.getPosFrombot(),2)%>" style="text-align:right"></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Envelope Weight:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envWeight" value="<%=Utility.ckNull(invFormData.getEnvWeight())%>" onchange="upperCase(this)"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Flap Position:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_flapPos" MAXLENGTH="12" value="<%=Utility.ckNull(invFormData.getFlapPos())%>" ></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Envelope Stock:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envStock" value="<%=Utility.ckNull(invFormData.getEnvStock())%>" onchange="upperCase(this)"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Gum Type:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_gumType" MAXLENGTH="12" value="<%=Utility.ckNull(invFormData.getGumType())%>" ></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Envelope Ink:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envInk" value="<%=Utility.ckNull(invFormData.getEnvInk())%>" onchange="upperCase(this)"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2"></TD>
						<TD></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Envelope Other:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envOther" value="<%=Utility.ckNull(invFormData.getEnvOther())%>" onchange="upperCase(this)"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2"></TD>
						<TD></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD COLSPAN="7"><HR></TD>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD CLASS="mtitleopen"><b>Labels:</b></TD>
						<TD COLSPAN="6">&nbsp;</TD>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Label Size:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_labelSize" value="<%=Utility.ckNull(invFormData.getLabelSize())%>"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Perf Location:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_perfLoc" MAXLENGTH="12" value="<%=Utility.getBigDecimalFormatted(invFormData.getPerfLoc(),2) %>" style="text-align:right"></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Label Type:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_envLabel" value="<%=Utility.ckNull(invFormData.getEnvLabel())%>"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Application Surface:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_appSurface" MAXLENGTH="12" value="<%=Utility.ckNull(invFormData.getAppSurface())%>" ></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_formColor','FPCO');">Label Color:</a></TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_formColor" value="<%=Utility.ckNull(invFormData.getFormColor())%>"></TD>
						<TD>&nbsp;</TD>
						<TD COLSPAN="2" NOWRAP></TD>
						<TD ALIGN="RIGHT">Adhesive:</TD>
						<TD NOWRAP>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_adhesive" MAXLENGTH="12" value="<%=Utility.ckNull(invFormData.getAdhesive())%>" ></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_formWeight','FWGT');">Label Weight:</a></TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_formWeight" value="<%=Utility.ckNull(invFormData.getFormWeight())%>"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2"></TD>
						<TD></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_formStock','FPPR');">Label Stock:</a></TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_formStock" value="<%=Utility.ckNull(invFormData.getFormStock())%>"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2"></TD>
						<TD></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_formInk','FINK');">Label Ink:</a></TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_formInk" value="<%=Utility.ckNull(invFormData.getFormInk())%>"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2"></TD>
						<TD></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Other:</TD>
						<TD>&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_otherType" value="<%=Utility.ckNull(invFormData.getOtherType())%>"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2"></TD>
						<TD></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD ALIGN="LEFT">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
				</TABLE>
				</TD>
			</TR>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/forms/inv_item_env_label.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
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