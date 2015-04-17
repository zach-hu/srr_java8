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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Numbering</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_form.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Form</a> | </b></td>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_fastening_use.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Fastening/Use</a> | </b></td>
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

<br><br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
			<TR>
				<TD>
				<TABLE WIDTH="100%" ALIGN="CENTER" BORDER="0" CELLSPACING="0" CELLPADDING="0" CLASS="htable">
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Number From:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_numberFrom" size=10 maxlength=20  tabindex=10 value="<%=HiltonUtility.ckNull(invFormData.getNumberFrom())%>"></TD>
						<TD NOWRAP></TD>
						<TD COLSPAN="2">To:&nbsp;<INPUT TYPE="TEXT" NAME="InvFormData_numberTo" size=10 maxlength=20 tabindex=20 value="<%=HiltonUtility.ckNull(invFormData.getNumberTo())%>" ></TD>
						<TD></TD>
						<TD NOWRAP>Guarantee No Missing Numbers:</TD>
						<TD COLSPAN="2" NOWRAP><INPUT TYPE="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull( invFormData.getNoMissing())).equals("Y")) { %> CHECKED <% } %> value="Y"  ONCLICK="setCheckbox(frm.InvFormData_noMissing, 0)" tabindex=70>
							<tsa:hidden name="InvFormData_noMissing" value="<%=Utility.ckNull( invFormData.getNoMissing())%>"/></TD>
						<TD></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Prefix:&nbsp;</TD>
						<TD NOWRAP><input type=text name="InvFormData_numberPrefix" size=10 maxlength=10 tabindex=30 value="<%=HiltonUtility.ckNull(invFormData.getNumberPrefix())%>"></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>Sort and List Missing Numbers:</TD>
						<TD COLSPAN="2" NOWRAP><INPUT TYPE="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(invFormData.getListMissing())).equals("Y")) { %> CHECKED <% } %> value="Y"  ONCLICK="setCheckbox(frm.InvFormData_listMissing, 1)" tabindex=80>
							<tsa:hidden name="InvFormData_listMissing" value="<%=HiltonUtility.ckNull(invFormData.getListMissing())%>"/></TD>
						<TD></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Suffix:&nbsp;</TD>
						<TD><input type=text name="InvFormData_numberSuffix" size=10 maxlength=10 tabindex=40  value="<%=HiltonUtility.ckNull(invFormData.getNumberSuffix())%>"></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_micrCode', 'FFNT');">MICR:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_micrCode" SIZE="15" MAXLENGTH="15" tabindex=50 value="<%=HiltonUtility.ckNull(invFormData.getMicrCode())%>"></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2" ALIGN=RIGHT>Description:&nbsp;</TD>
						<TD COLSPAN="3"><INPUT TYPE="TEXT" NAME="InvFormData_micrDesc" SIZE="60" value="<%=HiltonUtility.ckNull(invFormData.getMicrDesc())%>" disabled></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT"><a href="javascript: browseStd('InvFormData_ocrCode', 'FFNT');">OCR:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_ocrCode" SIZE="15" MAXLENGTH="15" tabindex=60 value="<%=HiltonUtility.ckNull(invFormData.getOcrCode())%>"></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD COLSPAN="2" ALIGN=RIGHT>Description:&nbsp;</TD>
						<TD COLSPAN="3"><INPUT TYPE="TEXT" NAME="InvFormData_ocrDesc" SIZE="60" MAXLENGTH="15" value="<%=HiltonUtility.ckNull(invFormData.getOcrDesc())%>" disabled></TD>
						<TD COLSPAN="2">&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP ALIGN="RIGHT">UDF 1:&nbsp;</TD>
						<TD></TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_numUdf1" SIZE="15" MAXLENGTH="20" tabindex=90 value="<%=HiltonUtility.ckNull(invFormData.getNumUdf1())%>" ></TD>
						<TD COLSPAN="5">&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP ALIGN="RIGHT">UDF 2:&nbsp;</TD>
						<TD></TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_numUdf2" SIZE="15" MAXLENGTH="20" tabindex=100 value="<%=HiltonUtility.ckNull(invFormData.getNumUdf2())%>" ></TD>
						<TD COLSPAN="5">&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP ALIGN="RIGHT">UDF 3:&nbsp;</TD>
						<TD></TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_numUdf3" SIZE="15" MAXLENGTH="20" tabindex=110 value="<%=HiltonUtility.ckNull(invFormData.getNumUdf3())%>" ></TD>
						<TD COLSPAN="5">&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP ALIGN="RIGHT">UDF 4:&nbsp;</TD>
						<TD></TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_numUdf4" SIZE="15" MAXLENGTH="20" tabindex=120 value="<%=HiltonUtility.ckNull(invFormData.getNumUdf4())%>" ></TD>
						<TD COLSPAN="5">&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">&nbsp;</TD>
						<TD>&nbsp;</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP ALIGN="RIGHT">UDF 5:&nbsp;</TD>
						<TD></TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_numUdf5" SIZE="15" MAXLENGTH="20" tabindex=130 value="<%=HiltonUtility.ckNull(invFormData.getNumUdf5())%>" ></TD>
						<TD COLSPAN="5">&nbsp;</TD>
					</TR>

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
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/forms/inv_item_numbering.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/inv_item.jsp', 'InvItemRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

// End Hide script -->
</SCRIPT>