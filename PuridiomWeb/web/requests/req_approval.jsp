<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>

<%
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
  RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
  UserProfile requisitioner = UserManager.getInstance().getUser(oid, reqHeader.getRequisitionerCode());
  String	currencyCode = reqHeader.getCurrencyCode() ;

  String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
  String tableType = "AC";

  List lineList = reqHeader.getRequisitionLineList();
  int	i_line_count = 0;

  String emailAction = (String)request.getAttribute("emailAction");
  String override = (String)request.getAttribute("override");
  String backgroundClass = "summary";
  Map approvalNotes = new HashMap();
  String	fYear = reqHeader.getFiscalYear() ;
  if(emailAction == null)
  {
    emailAction = "NONE";
  }
  if (override == null) {
  	override = "N";
  }
  //DO NOT change to use the isEmpty methods. This fields should be null if they are not set
  String alreadyApproved = (String)request.getAttribute("alreadyApproved");
  String invalidApprover = (String)request.getAttribute("invalidApprover");
  String documentWasCancelled = (String)request.getAttribute("documentWasCancelled");
  String requisitionWasRecalled = (String)request.getAttribute("requisitionWasRecalled");
	pageContext.setAttribute("oid", oid);
	pageContext.setAttribute("language", language);
  int varMessage=0;
  String message = "";
  String alreadyApprovedMsg = "";

  if(!(alreadyApproved == null && invalidApprover == null)){
	  varMessage = 1;
  }
  if(!(documentWasCancelled == null && requisitionWasRecalled == null)){
	  varMessage = 2;
  }
  if(alreadyApproved == null && invalidApprover == null && documentWasCancelled == null && requisitionWasRecalled == null)
  {%>

<tsa:hidden name="emailAction" value="<%=emailAction%>"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="ApprovalLog_recommendation" value=""/>
<tsa:hidden name="as_return" value="TRUE"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="reqaction" value="APPROVE"/>
<tsa:hidden name="override" value="<%=override%>"/>
<tsa:hidden name="approveForAll" value="N"/>
<tsa:hidden name="updateAppBy" value="Y"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
		<td nowrap class=hdr12 valign=middle>
			<div style="margin-left:10px; margin-right:10px" class=hdr12><%=RequisitionType.toString(reqHeader.getRequisitionType(), oid)%> Information</div>
        </td>
    </tr>
    </table>
  </td>
  <td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px width=*>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" checkRequired="false"/>:</b></td>
      <td width=150px><%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></td>
    </tr>
    <tr>
      <td align=right><b><tsa:label labelName="req-status" defaultString="Status" />:</b></td>
      <td width=150px><%=DocumentStatus.toString(reqHeader.getStatus())%></td>
    </tr>
    </table>
    <table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<jsp:include page="/requests/req_approval_buttons.jsp"/>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td align=center width=680px>
		<%@ include file="/requests/req_approval_routing_list.jsp" %>
		<%@ include file="/requests/req_approval_notes_display.jsp" %>
  </td>
</tr>
<tr>
  <td align=center width=680px>
    <table border=0 cellpadding=0 cellspacing=2 align=center width=665px  class=summary>
    <tr field="approvalNotes">
      <td colspan=2><b><tsa:label labelName="approvalNotes" defaultString="Notes" />:</b></td>
    </tr>
    <tr>
	  <tsa:td field="approvalNotes" >
	  <tsa:input type="textarea" name="ApprovalLog_approverNotes" id="ApprovalLog_approverNotes" rows="8" cols="67" onkeypress="return limitChar(event, 255, this);"></tsa:input>
      </tsa:td>
      <td valign="top">
        <table border=0 cellpadding=0 cellspacing=2 align=center class=summary valign="top">
          <tsa:tr field="reroute">
            <td valign=middle nowrap><b><A HREF="javascript: browseLookup('routTo', 'approver'); void(0);">
            <tsa:label labelName="reroute" defaultString="Reroute To" />:</a></b>&nbsp;
            <input type=text name="routTo" onfocus="this.blur();" onchange="upperCase(this);"></td>
          </tsa:tr>
          <tsa:tr field="reroute">
            <td valign=bottom nowrap>
              <input type=checkbox name="c_checkbox" value="Y" onclick="setReturn();" onfocus="checkAction('reroute');">
              <tsa:label labelName="returnmereroute" defaultString="Return to me after reroute" checkRequired="false"/>
              <tsa:hidden name="returnTo" value=""/>
            </td>
          </tsa:tr>
          <tsa:tr field="defer">
            <td valign=middle nowrap><b><A HREF="javascript: browseLookup('deferTo', 'approver'); void(0);">
            <tsa:label labelName="defer" defaultString="Defer To" />:</a></b>&nbsp;
            <input type=text name="deferTo" onfocus="this.blur();" onchange="upperCase(this);"></td>
          </tsa:tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
		<td align=center nowrap width=100%><b><%=RequisitionType.toString(reqHeader.getRequisitionType(), oid)%>:&nbsp;</b>
			<a href="javascript: editRequisition(); void(0);"><%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></a>
		</td>
	</tr>
</table>
<br>
<br>
<% if(!reqHeader.getRequisitionType().equalsIgnoreCase(RequisitionType.ADMIN_CHECK_REQUEST)){%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
	<tr>
		<td width="20">&nbsp;</td>
	   	<td align=left nowrap><b><%=RequisitionType.toString(reqHeader.getRequisitionType(), oid)%>:&nbsp;</b><%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></td>
	    <td align=center nowrap><b>MSR #:&nbsp;</b><%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></td>
	</tr>
</table>
<%} %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td width=50% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
            <tr>
              <td class=browseHdrDk height=18px nowrap>&nbsp;General Information</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100%>
            <tsa:tr field="req-requisitionerName">
              <td nowrap align=right><b>
              <tsa:label labelName="req-requisitionerName" defaultString="Requisitioner" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap width=100%><%=requisitioner.getDisplayName()%></td>
			</tsa:tr>
            <tsa:tr field="req-department">
              <td nowrap align=right><b>
              <tsa:label labelName="req-department" defaultString="Department" checkRequired="false"/>:&nbsp;</b></td>
              <td nowrap><%=reqHeader.getDepartmentCode()%></td>
            </tsa:tr>
            <tsa:tr field="req-telephoneNumber">
              <td nowrap align=right><b>
              <tsa:label labelName="telephoneNumber" defaultString="Telephone Number" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap><%=requisitioner.getPhoneNumber()%></td>
            </tsa:tr>
            <tsa:tr field="requestDate">
              <td nowrap align=right><b>
              <tsa:label labelName="requestDate" defaultString="Request Date" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(reqHeader.getRequisitionDate(), oid, userDateFormat)%></td>
            </tsa:tr>
            <tsa:tr field="req-plannedDate">
            	<tsa:td field="req-plannedDate" noWrap="nowrap" align="right"><b>
            	<tsa:label labelName="req-plannedDate" defaultString="Planned Date" checkRequired="false"/>:</b>&nbsp;</tsa:td>
            	<tsa:td field="req-plannedDate" noWrap="nowrap"><%=HiltonUtility.getFormattedDate(reqHeader.getPlannedDate(), oid, userDateFormat) %></tsa:td>
            </tsa:tr>
            <tsa:tr field="req-currency">
              <td nowrap align=right><b>
              <tsa:label labelName="req-currency" defaultString="Currency" checkRequired="false"/>:</b>&nbsp;</td>
              <td nowrap><%=reqHeader.getCurrencyCode()%></td>
            </tsa:tr>
<%	if (!HiltonUtility.isEmpty(reqHeader.getInternalComments())) { %>
            <tsa:tr field="req-purpose">
              <td nowrap align=right valign=top><b>
              <tsa:label labelName="req-purpose" defaultString="Purpose" checkRequired="false"/>:</b>&nbsp;</td>
              <td width=100%><%=reqHeader.getInternalComments()%></td>
            </tsa:tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
      <td width=50% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
            <tr>
              <td class=browseHdrDk height=18px nowrap>&nbsp;Shipping Information</td>
            </tr>
            </table>
          </td>
        </tr>
<%
    Address shipTo = (Address) reqHeader.getShipToAddress();
    if (shipTo == null)
    {
      shipTo = new Address();
    }
%>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tsa:tr field="req-shipToCode">
            <td class=browseRow height=12px nowrap><b><%=reqHeader.getShipToCode()%></b></td>
            </tsa:tr>
            <tsa:tr field="req-shp-addressLine1">
            <td class=browseRow height=12px nowrap><%=shipTo.getAddressLine1()%></td>
            </tsa:tr>
<%		if (!HiltonUtility.isEmpty(shipTo.getAddressLine2()))
      { %>
            <tsa:tr field="req-shp-addressLine2">
            <td class=browseRow height=12px nowrap><%=shipTo.getAddressLine2()%></td>
            </tsa:tr>
<%		}
      if (!HiltonUtility.isEmpty(shipTo.getAddressLine3()))
      { %>
            <tsa:tr field="req-shp-addressLine3">
            <td class=browseRow height=12px nowrap><%=shipTo.getAddressLine3()%></td>
            </tsa:tr>
<%		}
      if (!HiltonUtility.isEmpty(shipTo.getAddressLine4()))
      { %>
            <tsa:tr field="req-shp-addressLine4">
            <td class=browseRow height=12px nowrap><%=shipTo.getAddressLine4()%></td>
            </tsa:tr>
<%		} %>
            <tr><td class=browseRow height=12px nowrap><%=shipTo.getCity()%>&nbsp;<%=shipTo.getState()%>&nbsp;<%=shipTo.getPostalCode()%></td></tr>
            <tsa:tr field="req-shp-country">
            <td class=browseRow height=12px nowrap><%=shipTo.getCountry()%></td>
            </tsa:tr>
<%		if (!HiltonUtility.isEmpty(reqHeader.getShipAttn())) { %>
            <tsa:tr field="req-shp-attention">
            <td class=browseRow height=12px nowrap>
            <tsa:label labelName="req-shp-attention" defaultString="Attn" checkRequired="false"/>:&nbsp;<%=reqHeader.getShipAttn()%></td>
            </tsa:tr>
<%		}
      if (reqHeader.getRequiredDate() != null) { %>
            <tsa:tr field="req-requiredDate">
            <td class=browseRow height=12px nowrap>
            <tsa:label labelName="req-requiredDate" defaultString="Required By" checkRequired="false"/>:&nbsp;<%=HiltonUtility.getFormattedDate(reqHeader.getRequiredDate(), oid, userDateFormat)%></td>
            </tsa:tr>
<%		} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<br>
<br>

<% if(!reqHeader.getRequisitionType().equalsIgnoreCase(RequisitionType.ADMIN_CHECK_REQUEST)){%>
<jsp:include page="/requests/req_approval_header_info.jsp" />
<%} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td width=50% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
            <tr>
              <td class=browseHdrDk height=18px nowrap>&nbsp;Supplier Information</td>
            </tr>
            </table>
          </td>
        </tr>
<%
    Address vendor = (Address) reqHeader.getVendorAddress();
    if (vendor == null)   vendor = new Address();
%>
<%
	boolean multipleSupplier = false;
	if (reqHeader.getRequisitionLineList().size() > 1)
		multipleSupplier = true;
%>
        <tr>
          <td class=browseRow id=supplierRows>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <% if (oid.equalsIgnoreCase("hoy08p") && multipleSupplier) { %>
            <tr><td class=browseRow height=12px nowrap><b>See Suppliers Listed Below</b></td></tr>
            <% } else { %>
            <tsa:tr field="req-supplier">
            <td class=browseRow height=12px nowrap><b><%=HiltonUtility.ckNull(reqHeader.getVendorId())%></b></td>
            </tsa:tr>
            <tsa:tr field="req-vendorName">
            <td class=browseRow height=12px nowrap><%=HiltonUtility.ckNull(reqHeader.getVendorName())%></td>
            </tsa:tr>
            <tsa:tr field="req-sup-addressLine1">
            <td class=browseRow height=12px nowrap><%=HiltonUtility.ckNull(vendor.getAddressLine1())%></td>
            </tsa:tr>
<%	if (!HiltonUtility.isEmpty(vendor.getAddressLine2()))
    { %>
            <tsa:tr field="req-sup-addressLine2">
            <td class=browseRow height=12px nowrap><%=vendor.getAddressLine2()%></td>
            </tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(vendor.getAddressLine3()))
    { %>
            <tsa:tr field="req-sup-addressLine3">
            <td class=browseRow height=12px nowrap><%=vendor.getAddressLine3()%></td>
            </tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(vendor.getAddressLine4()))
    { %>
            <tsa:tr field="req-sup-addressLine4">
            <td class=browseRow height=12px nowrap><%=HiltonUtility.ckNull(vendor.getAddressLine4())%></td>
            </tsa:tr>
<%	}%>
            <tr><td class=browseRow height=12px nowrap><%=HiltonUtility.ckNull(vendor.getCity())%>&nbsp;<%=HiltonUtility.ckNull(vendor.getState())%>&nbsp;<%=HiltonUtility.ckNull(vendor.getPostalCode())%></td></tr>

            <tsa:tr field="req-sup-country">
            <td class=browseRow height=12px nowrap><%=vendor.getCountry()%></td>
            </tsa:tr>
<%	if (!HiltonUtility.isEmpty(reqHeader.getVendorAttn())) { %>
            <tsa:tr field="req-sup-attention">
            <td class=browseRow height=12px nowrap>
            <tsa:label labelName="req-sup-attention" defaultString="Attn" checkRequired="false"/>:&nbsp;<%=reqHeader.getVendorAttn()%></td>
            </tsa:tr>
<%	} %>
			<% } %>
            </table>
          </td>
        </tr>
        </table>
      </td>
      <td width=50% align=center valign=top>
        <table id=billingTable border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
        <tr>
          <td>
            <table id=billingTable border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
            <tr>
              <td class=browseHdrDk height=18px nowrap>&nbsp;Billing Information</td>
            </tr>
            </table>
          </td>
        </tr>
<%
    Address billTo = (Address) reqHeader.getBillToAddress();
    if (billTo == null)
    {
      billTo = new Address();
    }
%>
        <tr>
          <td class=browseRow id=billingRows>
            <table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
            <tr field="req-billToCode">
            <td class=browseRow height=12px nowrap><b><%=reqHeader.getBillToCode()%></b></td>
			</tr>
            <tr field="req-bil-addressLine1">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine1()%></td>
            </tr>
<%	if (!HiltonUtility.isEmpty(billTo.getAddressLine2()))
    { %>
            <tr field="req-bil-addressLine2">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine2()%></td>
            </tr>
<%	}
    if (!HiltonUtility.isEmpty(billTo.getAddressLine3()))
    { %>
            <tr field="req-bil-addressLine3">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine3()%></td>
            </tr>
<%	}
    if (!HiltonUtility.isEmpty(billTo.getAddressLine4()))
    { %>
            <tr field="req-bil-addressLine4">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine4()%></td>
            </tr>
<%	} %>
            <tr><td class=browseRow height=12px nowrap><%=billTo.getCity()%>&nbsp;<%=billTo.getState()%>&nbsp;<%=billTo.getPostalCode()%></td></tr>

            <tr field="req-bil-country">
            <td class=browseRow height=12px nowrap><%=billTo.getCountry()%></td>
            </tr>
<%	if (!HiltonUtility.isEmpty(reqHeader.getBillToContact())) { %>
            <tr field="req-bil-attention">
            <td class=browseRow height=12px nowrap>
            <tsa:label labelName="req-bil-attention" defaultString="Attn" checkRequired="false"/>:&nbsp;<%=reqHeader.getBillToContact()%></td>
            </tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<br>

<div id="commentBefore" style="display:none;">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td width=50% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
            <tr>
              <td class=browseHdrDk height=18px nowrap>&nbsp;Comment</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100%>
<%
      int iBefore = 0;
      List cmtList = (List) reqHeader.getDocCommentList();
	  String s_cmt_textb = "";
	  String s_cmt_texta = "";
      if (cmtList != null)
      {
        for(int i = 0; i < cmtList.size(); i++)
        {
          DocComment docComment = (DocComment) cmtList.get(i);
          DocText docText = docComment.getDocText();

          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("A"))
          {
            continue;
          }
          //if (s_cmt_print.equals("N"))
          //{
          //  continue;
          //}
          iBefore++;
%>
            <tr>
              <td width=100% class=browseRow colspan=2><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
            </tr>
            <tr>
              <td width=25px>&nbsp;</td>
              <td width=100% class=browseRow><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=docText.getStdText()%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
            </tr>
<%			}
    } %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
</div>

<br>
<% String nowrap = "nowrap"; %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td align=center valign=top>
        <table border=1  cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
        <tr>
          <td class=browseHdrDk>
            <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
            <tr height=18px>
              <td <%=HtmlWriter.isVisible(oid, "req-lineNumber")%> width=7% class=browseHdrDk <%=nowrap%>>
              &nbsp;<tsa:label labelName="req-hdg-lineNumber" defaultString="Line #" />
              </td>
              <td <%=HtmlWriter.isVisible(oid, "req-itemNumber")%> width=18% class=browseHdrDk <%=nowrap%>>
              &nbsp;<tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" />
              </td>
			  <% if ( reqHeader.getRequisitionType().equals("S") || reqHeader.getRequisitionType().equals("T") || reqHeader.getRequisitionType().equals("I") ) { %>
              <td <%=HtmlWriter.isVisible(oid, "req-inventoryLocation")%> width=10% class=browseHdrDk <%=nowrap%>>
              &nbsp;<tsa:label labelName="req-hdg-inventoryLocation" defaultString="Location" />
              </td>
			  <% } else {%>
              <td <%=HtmlWriter.isVisible(oid, "req-catalog")%> width=8% class=browseHdrDk <%=nowrap%>>
              &nbsp;<tsa:label labelName="req-hdg-catalog" defaultString="Catalog" />
              </td>
			  <% }%>
              <td <%=HtmlWriter.isVisible(oid, "req-commodity")%> width=10% class=browseHdrDk <%=nowrap%>>
              &nbsp;<tsa:label labelName="req-hdg-commodity" defaultString="Commodity" checkRequired="false"/>
              </td>
              <td <%=HtmlWriter.isVisible(oid, "req-quantity")%> width=10% class=browseHdrDk <%=nowrap%> >
              &nbsp;<tsa:label labelName="req-hdg-quantity" defaultString="Quantity" />
              </td>
              <td <%=HtmlWriter.isVisible(oid, "req-uom")%> width=5% class=browseHdrDk <%=nowrap%>>
              &nbsp;<tsa:label labelName="req-hdg-uom" defaultString="UOM" checkRequired="false"/>
              </td>
              <td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=13% class=browseHdrDk <%=nowrap%> align=right>
              &nbsp;<tsa:label labelName="req-hdg-extendedCost" defaultString="Extended Cost" />
              </td>
              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <td <%=HtmlWriter.isVisible(oid, "req-extendedCost")%> width=13% class=browseHdrDk <%=nowrap%> align=right>
              &nbsp;
              </td>
              <% } %>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width=100% align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>
          <td>
            <table id=itemRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
	int iRow = 0;
    for (int i = 0; i < lineList.size(); i++)
    {
      RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

      BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
      BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
      BigDecimal bd_discount_percent = HiltonUtility.getFormattedPrice(reqLine.getDiscountPercent(), oid);
      BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
      BigDecimal bd_discount = HiltonUtility.getFormattedDollar(reqLine.getDiscountAmount(), oid);
      BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
            <tr>
              <tsa:td field="req-lineNumber" width="7%" align="right" noWrap="nowrap" cssClass="browseRow" >
              <%=i+1%>
              </tsa:td>
              <tsa:td field="req-itemNumber" width="25%" noWrap="nowrap" cssClass="browseRow" >
              <%=reqLine.getItemNumber()%>
              </tsa:td>
<%		if ( reqHeader.getRequisitionType().equals("S") || reqHeader.getRequisitionType().equals("T") || reqHeader.getRequisitionType().equals("I") ) { %>
              <tsa:td field="req-inventoryLocation" width="10%" noWrap="nowrap" cssClass="browseRow" >
              <%=reqLine.getItemLocation()%>
              </tsa:td>
<%		} else { %>
              <tsa:td field="req-catalog" width="10%" noWrap="nowrap" cssClass="browseRow" >
              <%=reqLine.getCatalogId()%>
              </tsa:td>
<%		} %>
              <tsa:td field="req-commodity" width="13%" noWrap="nowrap" cssClass="browseRow" >
              <%=reqLine.getCommodityCode()%>
              </tsa:td>
              <tsa:td field="req-quantity" width="10%" noWrap="nowrap" cssClass="browseRow" align="right" >
              <%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>
              </tsa:td>
              <tsa:td field="req-uom" width="7%" noWrap="nowrap" cssClass="browseRow" >
              <%=reqLine.getUmCode()%>
              </tsa:td>
              <tsa:td field="req-extendedCost" width="13%" noWrap="nowrap" cssClass="browseRow" align="right">
              <%=HiltonUtility.getCurrency(bd_extended_cost, currencyCode, oid)%>
              </tsa:td>

              <% if (!currencyCode.equalsIgnoreCase("USD")) { %>
              <tsa:td field="req-extendedCost" width="13%" noWrap="nowrap" cssClass="browseRow" align="right">
              (<%=HiltonUtility.getCurrencyConvert(bd_extended_cost, currencyCode, oid)%>)
              </tsa:td>
              <% } %>
            </tr>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
              <td colspan=7 class=browseRow><%=reqLine.getDescription()%></td>
            </tr>
            <%
            List cmtLineList = (List) reqLine.getDocCommentList();
			if(cmtLineList != null){
				for (int l = 0; l < cmtLineList.size(); l++){
					DocComment docComment = (DocComment) cmtLineList.get(l);
					DocText docText = docComment.getDocText();
					String docLineTitle = docComment.getCommentTitle();
					String docCommentText = docComment.getCommentText();
					String s_cmt_place = docComment.getCommentPlace();
					String s_cmt_text = docText.getStdText();
					if (s_cmt_place.equalsIgnoreCase("A")){
						//s_cmt_texta = s_cmt_text;
						continue;
					} else if (s_cmt_place.equalsIgnoreCase("B")){
						s_cmt_textb = docCommentText;
					} %>
				<tr>
            	<td class=browseRow nowrap>&nbsp;</td><td colspan=7 class=browseRow>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
            		<%if(docComment.getCommentBold().equals("Y")){%><b><%}%><%=s_cmt_textb%><%if(docComment.getCommentBold().equals("Y")){%></b><%}%>
            	</td>
            	</tr>
			<%	}
			}%>
            <%	if (reqLine.getStatus().compareTo(DocumentStatus.CANCELLED) == 0) { %>
            <tr><td class=browseRow nowrap>&nbsp;</td><td colspan=7 class=browseRow align="center" style="color: red; font-size: 11px;"><b>** THIS LINE HAS BEEN CANCELLED **</b></td></tr>
            <%	} %>
<%
	AltText altText = reqLine.getAltText();
	if (altText != null && DictionaryManager.isVisible(oid, "req-description-alttext")) {
			DocText docText = altText.getDocText();
			if (docText != null) {%>
						<tr id=itemRows>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=docText.getStdText()%></td>
						</tr>
<%		}
		}%>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="reqline-vendorId" width="10%" colspan="7" cssClass="browseRow" ><b><tsa:label labelName="reqline-vendorId" defaultString="Vendor ID" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getVendorId()%></tsa:td>
			</tr>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="reqline-vendorName" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="reqline-vendorName" defaultString="Vendor Name" checkRequired="false"/>:</b>&nbsp;</tsa:td>
				<tsa:td field="reqline-vendorName" width="5%" colspan="2" cssClass="browseRow" ><%=reqLine.getVendorName()%></tsa:td>
			</tr>


		<% if(!reqHeader.getRequisitionType().equalsIgnoreCase(RequisitionType.ADMIN_CHECK_REQUEST)){%>
			<tr>
				<td class=broswRow nowrap>&nbsp;</td>
				<tsa:td field="req-LN08" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="req-LN08" defaultString="Line UDF 8" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf8Code()%></tsa:td>
				<tsa:td field="req-LN01" width="5%" colspan="2" cssClass="browseRow" noWrap='0' align='left' ><b><tsa:label labelName="req-LN01" defaultString="Line UDF 1" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf1Code()%></tsa:td>
			</tr>
			<tr>
	        	<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="req-modelNumber" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="req-modelNumber" defaultString="Model Number" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getModelNumber()%></tsa:td>
				<tsa:td field="req-LN06" width="5%" colspan="2" cssClass="browseRow" noWrap='0' align='left' ><b><tsa:label labelName="req-LN06" defaultString="Line UDF 6" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf6Code()%></tsa:td>
			</tr>
			<tr>
	        	<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="req-manufacturer" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="req-manufacturer" defaultString="Manufacturer" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getMfgName()%></tsa:td>
				<tsa:td field="req-LN07" width="5%" colspan="2" cssClass="browseRow" noWrap='0' align='left' ><b><tsa:label labelName="req-LN07" defaultString="Line UDF 7" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf7Code()%></tsa:td>
			</tr>
			<tr>
	        	<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="req-LN02" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="req-LN02" defaultString="Line UDF 2" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf2Code()%></tsa:td>
				<tsa:td field="req-asset" width="5%" colspan="2" cssClass="browseRow" noWrap='0' align='left' ><b><tsa:label labelName="req-asset" defaultString="Requisition Asset" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getAsset()%></tsa:td>
			</tr>
			<tr>
	        	<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="req-LN03" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="req-LN03" defaultString="Line UDF 3" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf3Code()%></tsa:td>
				<tsa:td field="req-LN10" width="5%" colspan="2" cssClass="browseRow" noWrap='0' align='left' ><b><tsa:label labelName="req-LN10" defaultString="Line UDF 10" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf10Code()%></tsa:td>
			</tr>
	        <tr>
	        	<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="req-LN05" width="5%" colspan="2" cssClass="browseRow" ><b><tsa:label labelName="req-LN05" defaultString="Line UDF 5" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getUdf5Code()%></tsa:td>
				<tsa:td field="req-shelfLife" width="5%" colspan="2" cssClass="browseRow" noWrap='0' align='left' ><b><tsa:label labelName="req-shelfLife" defaultString="Shelf Life Req" checkRequired="false"/>:</b>&nbsp;<%=reqLine.getShelfLifeRqd()%></tsa:td>
			</tr>
	      <%}

		if (reqLine != null) {
	     List inspList = reqLine.getInspectionList();
			if (inspList != null && !inspList.isEmpty()) {
			%>
			<tr>
					<tsa:td colspan="7" height="18px">
						<table cellpadding="0" cellspacing="0" border="0"  width="100%">
						<tsa:tr>
				          <tsa:td align="center"><a href="javascript: inspection('<%=iRow%>')" title="Inspection"><img id='inspectionImg<%=iRow%>' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Inspections"></a>
								<a href="javascript: inspection('<%=iRow%>')" title="Inspection">&nbsp;Receipt Inspection Details</a></tsa:td>
				        </tsa:tr>
						<td>
						<table cellspacing="0">
						<div id="inspectionDiv" value="<%=iRow%>" style="display: none">
						<ol>
					<%
						for (int ix = 0; ix < inspList.size(); ix++) {
						InspectionHeader insp = (InspectionHeader) inspList.get(ix);
						InspectionStd inspStd = insp.getInspectionStd();
						if(inspStd == null){inspStd = new InspectionStd();}
						%>
						    <% String inspType = insp.getInspectType() ;
						    		String inspDesc = "Receipt Inspection" ;
						    		if (inspType == null) inspType = "RI" ;
						    		if (inspType.equals("FI"))   {
						    			inspDesc = "Field Inspection" ;
						    		} else if (inspType.equals("GI"))   {
						    			inspDesc = "General Inspection" ;
						    		} else if (inspType.equals("CG"))   {
						    			inspDesc = "CGD Inspection" ;
						    		}
						    %>
							<li>
								<b><%=HiltonUtility.ckNull(inspDesc) %></b>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>&nbsp;&nbsp;:&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspStd.getDescription())%>
								<br>
								&nbsp;&nbsp;&nbsp;CGD No : <%=HiltonUtility.ckNull(insp.getCgdNo())%>&nbsp;&nbsp;CGD Rev : <%=HiltonUtility.ckNull(insp.getCgdRev())%>
							</li>
								<ul>
							<%	if (insp.getInspectionLineList() != null)
								{
									List inspLineLi = (List) insp.getInspectionLineList();
										if (inspLineLi != null) {
											for (int j = 0; j < inspLineLi.size(); j++)
											{
													InspectionLine inspLine = (InspectionLine) inspLineLi.get(j) ;
								%>
													<li>&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritNo())%>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getInspectCode())%>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritDescription())%></li>
													<br></br>
													<br>
								<%
											}
										}
									}
								if (insp.getInspectionLineList() != null && insp.getInspectionLineList().size() > 0) { %>
							<%} else {%>
											<li><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></li>
											<br></br>
											<br>
							<%}%>
								</ul>
					<%}%>
						</ol>
						</div>
						<br></br>
						</table>
						</td>
						</table>
				</tsa:td>
			</tr>
			<% 		}
			}
			iRow++; %>
<%
            List attachmentItemList = (List) reqLine.getDocAttachmentList();
            boolean flagAttachmentItem = false;
            if (attachmentItemList != null && attachmentItemList.size() > 0)
			{	%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan="4">
						<table>
				<%	for (int ix = 0; ix < attachmentItemList.size(); ix++)
					{
						DocAttachment docAttachmentItem = (DocAttachment) attachmentItemList.get(ix);
						String	filename = docAttachmentItem.getDocFilename();
						String	ext = filename.substring(filename.lastIndexOf(".") + 1);
						%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow align="right">
							<%if(!flagAttachmentItem){%>
								<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-catalogItemAttachment", "Attachments")%>:&nbsp;</b>
								<%flagAttachmentItem=true;
							}%>

<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: oopenAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td nowrap>
								<a href="javascript: openAttachment('<%=docAttachmentItem.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachmentItem.getDocTitle()%></a>
							</td>
						</tr>
<%
					} %>
						</table>
					</td>
				</tr>
<%			} %>

	    <%List laccountList = (List) reqLine.getAccountList();
	   	if ( oid.equalsIgnoreCase("dtn07p") && laccountList.size() == 0 )
		{
			laccountList = (List) reqHeader.getAccountList();
		}
	    if (laccountList != null)
	    {
		BigDecimal bd_total_perc = new BigDecimal(0.00);
		BigDecimal bd_total_amt = new BigDecimal(0.00);

		for (int ix = 0; ix < laccountList.size(); ix++)
		{
			Account laccount = (Account) laccountList.get(ix);
			BigDecimal bd_alloc_perc = laccount.getAllocPercent();
			BigDecimal bd_alloc_amt = laccount.getAllocAmount();
			if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
			if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}
			bd_total_perc = bd_total_perc.add(bd_alloc_perc);
			bd_total_amt = bd_total_amt.add(bd_alloc_amt);
			String	s_account = "";
			String s_fld4 = "";
			String s_fld5 = "";
			String	s_accArray[] = new String[15];

			s_accArray[0] = laccount.getFld1();
			s_accArray[1] = laccount.getFld2();
			s_accArray[2] = laccount.getFld3();
			s_accArray[3] = laccount.getFld4();
			s_accArray[4] = laccount.getFld5();
			s_accArray[5] = laccount.getFld6();
			s_accArray[6] = laccount.getFld7();
			s_accArray[7] = laccount.getFld8();
			s_accArray[8] = laccount.getFld9();
			s_accArray[9] = laccount.getFld10();
			s_accArray[10] = laccount.getFld11();
			s_accArray[11] = laccount.getFld12();
			s_accArray[12] = laccount.getFld13();
			s_accArray[13] = laccount.getFld14();
			s_accArray[14] = laccount.getFld15();

			for (int j = 0; j <15; j++)
			{
				if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
				{
					if (s_account.length() > 0)
					{
						s_account = s_account + s_account_separator + s_accArray[j];
					}
					else
					{
						s_account = s_accArray[j];
					}
				}
			}
			if ( !HiltonUtility.isEmpty(s_accArray[3])){	s_fld4 = s_accArray[3];	}
			if ( !HiltonUtility.isEmpty(s_accArray[4])){	s_fld5 = s_accArray[4];	}%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
<%
  if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
%>
					<td colspan=7 class=browseRow><b>Account:</b>&nbsp;
					<A HREF="javascript: showBudget('<%=s_account%>','<%=reqHeader.getFiscalYear()%>','<%=reqLine.getCommodityCode()%>'); void(0);"><%=s_account%></A>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, currencyCode, oid)%>
					<% if (!currencyCode.equalsIgnoreCase("USD")) { %>&nbsp;&nbsp;&nbsp;(<%=HiltonUtility.getCurrencyConvert(bd_alloc_amt, currencyCode, oid)%>)<% } %>					</td>
<%} else  {%>
					<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, currencyCode, oid)%>
					<% if (!currencyCode.equalsIgnoreCase("USD")) { %>&nbsp;&nbsp;&nbsp;(<%=HiltonUtility.getCurrencyConvert(bd_alloc_amt, currencyCode, oid)%>)<% } %>					</td>
<% } %>
				</tr>


<%if (!HiltonUtility.isEmpty(laccount.getAllocationDescription()) && oid.equalsIgnoreCase("hoy08p")) {	%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=7 class=browseRow><b>Description:</b>&nbsp;<%=laccount.getAllocationDescription()%></td>
				</tr>

<%					}
		}
	    }

		if(cmtLineList != null){
			for (int l = 0; l < cmtLineList.size(); l++){
				DocComment docComment = (DocComment) cmtLineList.get(l);
				DocText docText = docComment.getDocText();
				String docLineTitle = docComment.getCommentTitle();
				String docCommentText = docComment.getCommentText();
				String s_cmt_place = docComment.getCommentPlace();
				String s_cmt_text = docText.getStdText();
				if (s_cmt_place.equalsIgnoreCase("B")){
					//s_cmt_textb = s_cmt_text;
					continue;
				} else if (s_cmt_place.equalsIgnoreCase("A")){
					s_cmt_texta = docCommentText;
				} %>
			<tr>
        	<td class=browseRow nowrap>&nbsp;</td><td colspan=7 class=browseRow>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
        		<%if(docComment.getCommentBold().equals("Y")){%><b><%}%><%=s_cmt_texta%><%if(docComment.getCommentBold().equals("Y")){%></b><%}%>
        	</td>
        	</tr>
		<%	}
		}
	} %>
            </table>
          </td>
        </tr>
        </table>

        <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
        <tr><td class=browseRow width=100%><hr size=0></td></tr>
        </table>

        <table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
        <tr>

<jsp:include page="/requests/req_approval_srr.jsp" />

<div id="commentAfter" style="display:none;">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td width=680px align=center>
    <table border=0 cellpadding=0 cellspacing=0 width=665px>
    <tr>
      <td align=center valign=top>
        <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
        <tr>
          <td>
            <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
            <tr>
              <td class=browseHdrDk height=18px nowrap>&nbsp;Comment</td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class=browseRow>
            <table border=0 cellspacing=0 cellpadding=2 width=100%>
<%
      int iAfter = 0;
      if (cmtList != null)
      {
        for (int i = 0; i < cmtList.size(); i++)
        {
          DocComment docComment = (DocComment) cmtList.get(i);
          DocText docText = docComment.getDocText();

          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("B"))
          {
            continue;
          }
          //if (s_cmt_print.equals("N"))
          //{
            //continue;
          //}
          iAfter++;
%>
            <tr>
              <td width=100% class=browseRow colspan=2><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
            </tr>
            <tr>
              <td width=25px>&nbsp;</td>
              <td width=100% class=browseRow><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=docText.getStdText()%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
            </tr>
<%			}
      }%>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
</div>

<div id="attachments" style="display:none;">
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=665px>
		<tr>
			<td align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
						<tr>
							<td class=browseHdrDk height=18px nowrap>&nbsp;Attachment</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
<%
		List attachmentList = (List) reqHeader.getDocAttachmentList();
		int ai = 0;
		if (attachmentList != null) {
			for (int i = 0; i < attachmentList.size(); i++) {
				DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
				if (docAttachment == null) {
					continue;
				}
				String	filename = docAttachment.getDocFilename();
				String	ext = filename.substring(filename.lastIndexOf(".") + 1);
				ai++;
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=80% class=browseRow>
								<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
								<tr>
									<td width=25px align=center valign=middle>
<%			if (ext.equalsIgnoreCase("DOC")) {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%			} else if (ext.equalsIgnoreCase("PDF")) {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%			} else if (ext.equalsIgnoreCase("XLS")) {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%			} else if (ext.equalsIgnoreCase("MPP")) {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%			} else if (ext.equalsIgnoreCase("PPT")) {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%			} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%			} else {%>
										<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%			}%>
									</td>
									<td>
										<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
										<tsa:hidden name="docFilename" value="<%=filename%>"/>
									</td>
								</tr>
								</table>
							</td>
							<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
							<td width=10% class=browseRow align=center valign=top></td>
						</tr>
						</table>
  <% 		}
      }
      if (ai == 0) {%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr><td class=browseRow>There are no attachments associated with this requisition.</td></tr>
						</table>
<%		}%>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<br>

<jsp:include page="/requests/req_approval_buttons.jsp"/>

<br>

<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;

<%	if (HiltonUtility.ckNull(override).equals("Y") && user.isAnOverrider()) {%>
			displayArea('adminApproverButtons');
<%	} else if (dollarApprover) {%>
			displayArea('approverButtons');
<%	} else if (advisor) {%>
			displayArea('advisorButtons');
<%	}
		if (iBefore > 0) { %>
    	  displayArea('commentBefore');
<%	}
	    if (iAfter > 0) { %>
      	displayArea('commentAfter');
<%	}
		if (ai > 0) { %>
			displayArea('attachments');
<%	}%>

  function setFirstFocus()
  {
    // please do not take this out!  its here because otherwise when the page loads it will focus on the first text box which is at the BOTTOM of the page
    // we do NOT want to focus there!!!
  }
  function checkHiddenMenu()
  {
  	hideArea("navTable");
  	displayArea("menuBarSpacer");
  }

  function setReturn()
  {
    var rtn = "N";

    if (frm.c_checkbox.checked)
    {
      rtn = "Y";
    }

    frm.returnTo.value = rtn;
  }

  function checkAction(s) {
    //frm.rq_action.value = s;

    if ((s == 'approve') || (s == 'reject')) {
      frm.routTo.value = '';
      frm.c_checkbox.checked = false;
      frm.returnTo.value = "N";
    }
    else if (s == 'reroute') {
      //frm.rq_action[2].checked = true;
    }
  }

  function rejectMe()
  {
	frm.routTo.value="";
	frm.deferTo.value="";
    frm.returnTo.value="N";
	frm.reqaction.value = "REJECT";

	var newInputField = "<input type='hidden' name='RequisitionHeader_icReqHeader' value='<%= reqHeader.getIcReqHeader() %>'>";
	newInputField = newInputField + "<input type='hidden' name='RequisitionLine_icReqHeader' value='<%= reqHeader.getIcReqHeader() %>'>";
	newInputField = newInputField + "<input type='hidden' name='formtype' value='REQ'>";

	setHiddenFields(newInputField);

	if (frm.ApprovalLog_approverNotes.value.length <= 0)
	{
		alert("Please first enter notes to the requisitioner explaining why you are rejecting this requisition.");
	}
	else
	{
      doSubmit('/requests/req_forward.jsp', 'RequisitionReject');
	}
  }

  function recommendApproval() {
  	frm.ApprovalLog_recommendation.value = "Y";
  	doSubmitApprove('N');
  }

  function opposeApproval()
  {
	frm.ApprovalLog_recommendation.value = "R";
	if (frm.ApprovalLog_approverNotes.value.length <= 0)
	{
		alert("Please first enter notes to the requisitioner explaining why you oppose approval of this requisition.");
	}
	else
    {
		// use RequisitionApprove process so approval continues, but recommendation will be set to R (Recommending the Req. be Rejected)
		doSubmit('/requests/req_forward.jsp', 'RequisitionApprove');
	}
  }

  function deferMe()
  {
	frm.routTo.value="";
    if (frm.deferTo.value.length <= 0)
    {
      alert("You must first select an approver to defer this Requisition!");
      browseLookup('deferTo', 'approver');
    }
    else
    {
		if (isEmpty(frm.ApprovalLog_approverNotes.value)) {
			alert("You must enter approval notes to defer this Requisition!");
			return false;
		}
      frm.reqaction.value = "DEFER";
      frm.emailAction.value = "Approve";
      doSubmit('/requests/req_forward.jsp', 'RequisitionDefer');
    }
  }  

  function rerouteMe()
  {
	frm.deferTo.value="";
    if (frm.routTo.value.length <= 0)
    {
      alert("You must first select an approver to reroute this Requisition!");
      browseLookup('routTo', 'approver');
    }
    else
    {
		if (isEmpty(frm.ApprovalLog_approverNotes.value)) {
			alert("You must enter approval notes to reroute this Requisition!");
			return false;
		}
      frm.reqaction.value = "REROUTE";
      frm.emailAction.value = "Approve";
      doSubmit('/requests/req_forward.jsp', 'RequisitionReroute');
    }
  }

  function doSubmitApprove(overrideAll)
  {
  	  frm.routTo.value="";
  	  frm.deferTo.value="";
  	  frm.returnTo.value="N";
  	  if (frm.override.value == "Y" &&isEmpty(frm.ApprovalLog_approverNotes.value)) {
 		alert("You must enter approval notes to approve on behalf of another approver.");
  		return false;
  	  }
  	  frm.approveForAll.value = overrideAll;
      hideAreaWithBlock('approve_link');
      doSubmit('/requests/req_forward.jsp', 'RequisitionApprove');
  }

	function showBudget(accs, fy, commodity) {
		var burl = "/budget/budget_view.jsp" ;
		popupParameters = "accountString=" + accs + ";fiscalYear=" + fy + ";currencyCode=<%=currencyCode%>;";
		popupParameters = popupParameters + "commodity=" + commodity + ";";
		doSubmitToPopup(burl, "BudgetCenterRetrieveView", "WIDTH=500", "HEIGHT=300") ;
	}

  function openDocument(row)
  {
    var filename = "";
    if (document.all("docFilename").length > 1)
    {
      filename = frm.docFilename[row].value;
    }
    else
    {
      filename = frm.docFilename.value;
    }
    openAttachment(filename);
  }

	function editRequisition()
	{
		var newInputField = "";

		if (frm.RequisitionHeader_icReqHeader) {
			frm.RequisitionHeader_icReqHeader.value = "<%=reqHeader.getIcReqHeader()%>";
		} else {
			newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_icReqHeader' value='<%=reqHeader.getIcReqHeader()%>'>";
		}

		if (frm.viewType) {
			frm.viewType.value = "WIZARD";
		} else {
			newInputField = newInputField + "<input type='hidden' name='viewType' value='WIZARD'>";
		}

		setHiddenFields(newInputField);

		doSubmit('/requests/req_review.jsp', 'RequisitionRetrieve');
	}

// End Hide script -->
</SCRIPT>

 <%}
 if (varMessage == 1)
  {
  //Already approved Requisition!
    alreadyApprovedMsg = (String)request.getAttribute("Message");
    %>

    <table border=0 cellpadding=0 cellspacing=0 width=680px>
       <tr>
        <td align=center width=680px>
          <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseRow>
            <tr>
              <td colspan=3><br><br></td>
            </tr>
            <tr class = 'error'>
              <td align=center>You have already processed Requisition <%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%>.</td>
			</tr>
          </table>
        </td>
      </tr>
    </table>
<%@ include file="/requests/req_waiting_approval_others.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;
  function retrieveReqApproval(ic)
  {
		var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='DocAttachment_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='emailAction' value='Approve'>";
		setHiddenFields(newInputField);
		doSubmit('/requests/req_approval.jsp', 'RequisitionApprovalRetrieve');
  }
// End Hide script -->
</SCRIPT>

<%}
 if (varMessage == 2)
  {
	  message = (String)request.getAttribute("Message");
	  %>

       <table border=0 cellpadding=0 cellspacing=0 width=680px>
       <tr>
        <td align=center width=680px>
          <table border=0 cellspacing=0 cellpadding=0 width=665px class=browseRow>
            <tr>
              <td colspan=3><br><br></td>
            </tr>
            <tr class = 'error'>
              <td align=center><%=message%></td>
			</tr>
          </table>
        </td>
       </tr>
      </table>
      <%@ include file="/requests/req_waiting_approval_others.jsp" %>
<SCRIPT value=JavaScript>
<!-- Hide script

  frm = document.purchaseform;
  function retrieveReqApproval(ic)
  {
		var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_icReqHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='DocAttachment_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='emailAction' value='Approve'>";
		setHiddenFields(newInputField);
		doSubmit('/requests/req_approval.jsp', 'RequisitionApprovalRetrieve');
  }
// End Hide script -->
</SCRIPT>
<%}%>
<script type="text/javascript">
<!--
function limitChar(elEvento, maxChar, field ) {
  var elemento = field;
  var evento = elEvento || window.event;
  var code = evento.charCode || evento.keyCode;
  if(code == 37 || code == 39) {
    return true;
  }
  if(code == 8 || code == 46) {
    return true;
  }
  else if(elemento.value.length >= maxChar ) {
    return false;
  }
  else {
    return true;
  }
}

function inspection(iRow)
{
	var myImg = document.getElementById("InspectionImg" + iRow);
	if ($('div[value*="'+ iRow + '"]').is(":hidden")) {
		$('div[value*="' + iRow + '"]').slideDown("slow");
		myImg.src = "<%=contextPath%>/images/arrows_up.gif";
		myImg.alt = "Hide Inspections";
	} else {
		$('div[value*="' + iRow + '"]').hide();
		myImg.src = "<%=contextPath%>/images/arrows_down.gif";
		myImg.alt = "View Inspections";
	}
}

//-->
</script>


<%@ include file="/system/footer.jsp" %>
