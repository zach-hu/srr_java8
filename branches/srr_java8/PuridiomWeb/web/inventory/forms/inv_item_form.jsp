<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.math.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	String s_itemNumber = HiltonUtility.ckNull((String) request.getAttribute("InvItem_itemNumber"));
	String s_description = HiltonUtility.ckNull((String) request.getAttribute("InvItem_description"));
	InvFormData invFormData = (InvFormData) request.getAttribute("invFormData");
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
	<td nowrap align=right colspan=2 class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-itemNumber", "Item/Part #")%>:&nbsp;</td>
	<td nowrap><b><%=headerEncoder.encodeForHTML(s_itemNumber)%></b></td>
</tr>
<tr>
	<td nowrap align=right valign=top colspan=2 class=formType><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-description", "Description")%>:&nbsp;</td>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Form</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=left NOWRAP><b><a href="javascript: doSubmit('/inventory/forms/inv_item_numbering.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);">Numbering</a> | </b></td>
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
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
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
						<TD NOWRAP CLASS="mtitleopen"><b>Catalog Information:</b></TD>
						<TD COLSPAN="6">&nbsp;</TD>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Form Number:&nbsp;</TD>
						<TD><input type=text value="<%=headerEncoder.encodeForHTMLAttribute(s_itemNumber)%>" size=15 disabled></TD>
						<TD NOWRAP><tsa:hidden name="InvFormData_formNumber" value="<%=HiltonUtility.ckNull(invFormData.getFormNumber())%>"/></TD>
						<td nowrap align=right COLSPAN="2"><a href="javascript: browseStd('InvFormData_primeUser','NAME');">Primary User:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_primeUser" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getPrimeUser())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2">Appointed:&nbsp;</TD>
						<TD ALIGN="LEFT"><INPUT TYPE="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(invFormData.getAppointedFlag())).equals("Y")) { %>CHECKED <% } %> value="Y"  ONCLICK="setCheckbox(frm.InvFormData_appointedFlag, 0)"  >
							<tsa:hidden name="InvFormData_appointedFlag" value="<%=HiltonUtility.ckNull(invFormData.getAppointedFlag()) %>"/>
						</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Form Revision:&nbsp;</TD>
						<TD NOWRAP><INPUT TYPE="TEXT" NAME="InvFormData_formRevision" size=15 maxlength=10 value="<%=HiltonUtility.ckNull(invFormData.getFormRevision())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "frm-department", "Department", true)%>:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_department" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getDepartment())%>"></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2"><a href="javascript: browseStd('InvFormData_lobCode','LOB');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "frm-lobCode", "LOB Code", true)%>:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_lobCode" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getLobCode())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Replaces:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formReplaces" size=15 maxlength=20 value="<%=HiltonUtility.ckNull(invFormData.getFormReplaces())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Office Loc:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_officeLoc" size=15 maxlength=40 value="<%=HiltonUtility.ckNull(invFormData.getOfficeLoc())%>"></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2><a href="javascript: browseStd('InvFormData_formUdf01', 'FUF1');">Form UDF 1:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formUdf01" size=15 maxlength=20 value="<%=HiltonUtility.ckNull(invFormData.getFormUdf01())%>"></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_compCode', 'CMPY');">Comp Code:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_compCode" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getCompCode())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2"><a href="javascript: browseStd('InvFormData_budgetCode','AC02');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "frm-budgetCode", "Budget Code", true)%>:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_budgetCode" size=15 maxlength=17 value="<%=HiltonUtility.ckNull(invFormData.getBudgetCode())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2"><a href="javascript: browseStd('InvFormData_formUdf02','FUF2');">Form UDF 2:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formUdf02" size=15 maxlength=20 value="<%=HiltonUtility.ckNull(invFormData.getFormUdf02())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD COLSPAN="7"><BR></TD>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD CLASS="mtitleopen"><b>Specifications:</b></TD>
						<TD COLSPAN="6">&nbsp;</TD>
						<TD WIDTH="1px" CLASS="bnav"></TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_formType','FTYP');">Type:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formType" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getFormType())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2">Color Separations:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_colorSeparation" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getColorSeparation())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2"><a href="javascript: browseStd('InvFormData_formLogo1','NAME');">Form Logo 1:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formLogo1" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getFormLogo1())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<TD ALIGN="RIGHT">Size Flat:</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_sizeFlat" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getSizeFlat())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp;</TD>
						<td nowrap align=right colspan=2>Use Type:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_useType" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getUseType())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2><a href="javascript: browseStd('InvFormData_formLogo2','NAME');">Form Logo 2:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formLogo2" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getFormLogo2())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_formFolding','FFLD');">Folding:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formFolding" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getFormFolding())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2">Mail Date:&nbsp;</TD>
						<TD NOWRAP><INPUT TYPE="TEXT" NAME="InvFormData_mailDate" SIZE="15" value="<%=HiltonUtility.getFormattedDate(invFormData.getMailDate(),oid, userDateFormat)%>" >
							<a href="javascript: show_calendar('InvFormData_mailDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
						</TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2">Form Sig 1:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formSig1" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getFormSig1())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Pages:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_pages" SIZE="15" value="<%=Utility.getBigDecimalFormatted(invFormData.getPages(),0)%>" style="text-align:right"></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Date Out:&nbsp;</TD>
						<TD NOWRAP><INPUT TYPE="TEXT" NAME="InvFormData_dateOut" SIZE="15" value="<%=HiltonUtility.getFormattedDate(invFormData.getDateOut(),oid, userDateFormat)%>" >
							<a href="javascript: show_calendar('InvFormData_dateOut', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" valign=bottom border=0></a>
						</TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Form Sig 2:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formSig2" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getFormSig2())%>" ></TD>
						<TD>&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_coverStock','FPPR');">Cover Stock:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_coverStock" size=15 maxlength=15 value="<%=HiltonUtility.ckNull(invFormData.getCoverStock())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2">Equivalent Stock:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_equivalentStock" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getEquivalentStock())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Form Address:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formAddress" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getFormAddress())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_coverInk','FINK');">Cover Ink:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_coverInk" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getCoverInk())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2">Auto Reprint:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_autoReprint" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getAutoReprint())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right COLSPAN="2"><a href="javascript: browseStd('InvFormData_specUdf01','FSP1');">Spec. UDF 1:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_specUdf01" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getSpecUdf01())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Cover Prints:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_coverPrints" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getCoverPrints())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Inprintable Space:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_inprintableSpace" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getInprintableSpace())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2><a href="javascript: browseStd('InvFormData_specUdf02','FSP2');">Spec. UDF 2:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_specUdf02" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getSpecUdf02())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Bleeds:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_bleeds" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getBleeds())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Traps:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formTraps" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getFormTraps())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2><a href="javascript: browseStd('InvFormData_specUdf03','FSP3');">Spec. UDF 3:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_specUdf03" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getSpecUdf03())%>" ></TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_pageStock','FPPR');">Page Stock:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_pageStock" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getPageStock())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<TD COLSPAN="2">Special Notes:</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<td nowrap align=right colspan=2>Legal Review:&nbsp;</TD>
						<TD ALIGN="LEFT"><INPUT TYPE="checkbox" NAME="c_checkbox" <%if ((HiltonUtility.ckNull(invFormData.getLegalReview())).equals("Y")) { %> CHECKED <% } %> value="Y" ONCLICK="setCheckbox(frm.InvFormData_legalReview, 1)"  >
							<tsa:hidden name="InvFormData_legalReview" value="<%=HiltonUtility.ckNull(invFormData.getLegalReview())%>"/>
						</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_pageInk','FINK');">Page Ink:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_pageInk" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getPageInk())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<TD COLSPAN="3" ROWSPAN="4"><textarea NAME="InvFormData_specNotes" cols=38 rows=6><%=HiltonUtility.ckNull(invFormData.getSpecNotes())%></textarea></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP colspan=2>Supply Instructions:</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Proofs:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_proofs" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getProofs())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP COLSPAN="3" ROWSPAN="3"><textarea NAME="InvFormData_supInstruction" cols=34 rows=4><%=HiltonUtility.ckNull(invFormData.getSupInstruction())%></textarea></TD>
						<TD NOWRAP></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right>Finishing:&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_finishing" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getFinishing())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP></TD>
						<TD NOWRAP></TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD>&nbsp;</TD>
					</TR>
					<TR>
						<TD WIDTH="1px" CLASS="bnav"></TD>
						<td nowrap align=right><a href="javascript: browseStd('InvFormData_formArt','FART');">Artwork:</a>&nbsp;</TD>
						<TD><INPUT TYPE="TEXT" NAME="InvFormData_formArt" SIZE="15" MAXLENGTH="12" value="<%=HiltonUtility.ckNull(invFormData.getFormArt())%>" ></TD>
						<TD NOWRAP>&nbsp;&nbsp</TD>
						<TD NOWRAP>&nbsp;</TD>
						<TD NOWRAP></TD>
						<TD NOWRAP></TD>
						<TD NOWRAP>&nbsp;</TD>
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
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/forms/inv_item_form.jsp', 'InvFormDataUpdate;InvFormDataRetrieveById'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
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