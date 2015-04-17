<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	String s_itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String s_description = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	
	InvFormData invFormData = (InvFormData)request.getAttribute("invFormData");
%>

<tsa:hidden name="InvFormData_itemNumber" value="<%=headerEncoder.encodeForHTMLAttribute(s_itemNumber)%>"/>
<tsa:hidden name="InvFormPart_itemNumber" value="<%=headerEncoder.encodeForHTMLAttribute(s_itemNumber)%>"/>
<tsa:hidden name="InvItem_itemNumber" value="<%=headerEncoder.encodeForHTMLAttribute(s_itemNumber)%>"/>
<tsa:hidden name="InvItem_description" value="<%=headerEncoder.encodeForHTMLAttribute(s_description)%>"/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Fastening/Use</div>
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
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_env_label.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Env/Label</a> | </b></td>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width=680px>
			<TR>
				<TD>
				<TABLE WIDTH=680px ALIGN="CENTER" BORDER=0 CELLSPACING="0" CELLPADDING="0" CLASS="htable">
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD nowrap class="mtitleopen"><b>Fastening:</b></TD>
						<td colspan=7></td>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td align=right>Position:&nbsp;</TD>
						<td colspan=2><input type=text name="InvFormData_fastenPos" size=25 maxlength=20 tabindex=10 value="<%=HiltonUtility.ckNull(invFormData.getFastenPos())%>"></TD>
						<td colspan=5 align=right>Description:&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_fastenDesc" size=65 maxlength=60 tabindex=20 value="<%=HiltonUtility.ckNull(invFormData.getFastenDesc())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td align=right>Type:&nbsp;</TD>
						<td colspan=2><INPUT TYPE="TEXT" NAME="InvFormData_fastenType" size=25 maxlength=20 tabindex=30 value="<%=HiltonUtility.ckNull(invFormData.getFastenType())%>" ></TD>
						<td colspan=2>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_binding','FBND');">Binding:</a>&nbsp;</TD>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_binding" SIZE="15" MAXLENGTH="15" tabindex=40 value="<%=HiltonUtility.ckNull(invFormData.getBinding())%>"></TD>
						<td nowrap align=right>&nbsp;No. Per:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_bindingPer" SIZE="15" tabindex=50 value="<%=Utility.getBigDecimalFormatted(invFormData.getBindingPer(),0)%>" style="text-align:right"></TD>
						<td nowrap align=right>&nbsp;<a href="javascript: browseStd('InvFormData_bindingCover','FPPR');">Cover:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_bindingCover" SIZE="15" MAXLENGTH="15" tabindex=60 value="<%=Utility.ckNull(invFormData.getBindingCover())%>"></TD>
						<td nowrap align=right>&nbsp;<a href="javascript: browseStd('InvFormData_bindingBack','FPPR');">Backing:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_bindingBack" SIZE="15" MAXLENGTH="15" tabindex=70 value="<%=Utility.ckNull(invFormData.getBindingBack())%>"></TD>
						<TD></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_padding','FPAD');">Padding:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_padding" SIZE="15" MAXLENGTH="12" tabindex=80 value="<%=Utility.ckNull(invFormData.getPadding())%>"></TD>
						<td nowrap align=right>&nbsp;No. Per:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_paddingPer" SIZE="15" tabindex=90 value="<%=Utility.getBigDecimalFormatted(invFormData.getPaddingPer(),0)%>" style="text-align:right"></TD>
						<td nowrap align=right>&nbsp;<a href="javascript: browseStd('InvFormData_paddingCover','FPPR');">Cover:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_paddingCover" SIZE="15" MAXLENGTH="15" tabindex=100 value="<%=HiltonUtility.ckNull(invFormData.getPaddingCover())%>"></TD>
						<td nowrap align=right>&nbsp;<a href="javascript: browseStd('InvFormData_paddingBack','FPPR');">Backing:</a>&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_paddingBack" SIZE="15" MAXLENGTH="15" tabindex=110 value="<%=HiltonUtility.ckNull(invFormData.getPaddingBack())%>"></TD>
						<TD></TD>
					</TR>
					<TR>
						<TD COLSPAN="10" width=680px><HR></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD CLASS="mtitleopen"><b>Use:</b></TD>
						<td colspan=7></td>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Typewriter:&nbsp;</td>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_typewriter" SIZE="15" MAXLENGTH="10" tabindex=120 value="<%=HiltonUtility.ckNull(invFormData.getTypewriter())%>"></TD>
						<td nowrap align=right colspan=2>Vertical Spacing:&nbsp</TD>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_typewriterVsp" SIZE="15" tabindex=130 value="<%=Utility.getBigDecimalFormatted(invFormData.getTypewriterVsp(), 2)%>" style="text-align:right"></TD>
						<td nowrap align=right colspan=2>Horizontal Spacing:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_typewriterHsp" SIZE="15" tabindex=140 value="<%=Utility.getBigDecimalFormatted(invFormData.getTypewriterHsp(), 2)%>" style="text-align:right"></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Printer:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_printer" SIZE="15" MAXLENGTH="10" tabindex=150 value="<%=HiltonUtility.ckNull(invFormData.getPrinter())%>"></TD>
						<td nowrap align=right colspan=2>Vertical Spacing:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_printerVsp" SIZE="15" tabindex=160 value="<%=Utility.getBigDecimalFormatted(invFormData.getPrinterVsp(), 2)%>" style="text-align:right"></TD>
						<td nowrap align=right colspan=2>Horizontal Spacing:&nbsp;</td>
						<td><INPUT TYPE="TEXT" NAME="InvFormData_printerHsp" SIZE="15" tabindex=170 value="<%=Utility.getBigDecimalFormatted(invFormData.getPrinterHsp(),2)%>" style="text-align:right" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Decollator:&nbsp;</TD>
						<td colspan=5><INPUT TYPE="TEXT" NAME="InvFormData_decollator" SIZE="70" MAXLENGTH="60" tabindex=180 value="<%=HiltonUtility.ckNull(invFormData.getDecollator())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Burster:&nbsp;</TD>
						<td colspan=5><INPUT TYPE="TEXT" NAME="InvFormData_burster" SIZE="70" MAXLENGTH="60" tabindex=190 value="<%=HiltonUtility.ckNull(invFormData.getBurster())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Mail Equip:&nbsp;</TD>
						<td colspan=5><INPUT TYPE="TEXT" NAME="InvFormData_mailEquip" SIZE="70" MAXLENGTH="60" tabindex=200 value="<%=HiltonUtility.ckNull(invFormData.getMailEquip())%>" ></TD>
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
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/forms/inv_item_fastening_use.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
// End Hide script -->
</SCRIPT>