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
	if (oid.equalsIgnoreCase("qri06p"))
	{
		String location = "ACCOUNT_" + reqHeader.getUdf1Code();
		tableType = propertiesManager.getProperty("BROWSE", location, "AC");
	}
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

  int varMessage=0;
  String message = "";
  String alreadyApprovedMsg = "";

  if(!(alreadyApproved == null && invalidApprover == null)){
	  varMessage = 1;
  }
  if(!(documentWasCancelled == null && requisitionWasRecalled == null)){
	  varMessage = 2;
  }
  pageContext.setAttribute("oid", oid);
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

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>
        <tsa:label labelName="requisitionInformation" defaultString="Requisition Information" /></div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
    <table border=0 cellspacing=0 cellpadding=1 width=100%>
    <tr>
      <td align=right><b><tsa:label labelName="requisitionNumber" defaultString="Requisition #" />:</b></td>
      <td width=100px><%=headerEncoder.encodeForHTML(reqHeader.getRequisitionNumber())%></td>
    </tr>
    <tr>
      <td align=right><b><tsa:label labelName="status" defaultString="Status" />:</b></td>
      <td width=100px><%=DocumentStatus.toString(reqHeader.getStatus())%></td>
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

<jsp:include page="/requests/req_approval_buttons.jsp" />

<br>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<%@ include file="/requests/req_approval_routing_list.jsp" %>
		<%@ include file="/requests/req_approval_notes_display.jsp" %>
  </td>
</tr>
<tr>
  <td align=center width=680px>
    <table border=0 cellpadding=0 cellspacing=2 align=center width=665px  class=summary>
    <tsa:tr field="approvalNotes">
      <td colspan=2><b><tsa:label labelName="approvalNotes" defaultString="Notes" />:</b></td>
    </tsa:tr>
    <tr>
      <tsa:td field="approvalNotes" >
      <tsa:input type="textarea" name="ApprovalLog_approverNotes" id="ApprovalLog_approverNotes" rows="8" cols="67" onkeypress="return limitChar(event, 255, this);">
      </tsa:input>
      </tsa:td>
      <td valign="top">
        <table border=0 cellpadding=0 cellspacing=2 align=center class=summary valign="top">
<%	if (propertiesManager.isModuleActive("SIGNATURE") && !oid.equalsIgnoreCase("DTN07P")) {%>
        <tsa:tr field="app-signature">
          <td valign=middle nowrap><b>
          <tsa:label labelName="app-signature" defaultString="Signature" />:&nbsp;</b>
          <tsa:input type="checkbox" name="c_app_signed" value="N" /></td>
        </tsa:tr>
        <tsa:tr field="app-signaturePassword">
          <td valign=middle nowrap><B>
          <tsa:label labelName="app-signaturePassword" defaultString="Approver" />:&nbsp;</b>
            &nbsp;<input type=password name="signature_password" autocomplete="off" value="" onchange="upperCase(this);"></td>
        </tsa:tr>
        <tr>
          <td width=100%><hr color="#9999CC"></td>
        </tr>
<%	}%>
           <tsa:tr field="reroute">
            <td valign=middle nowrap><b><A HREF="javascript: browseLookup('routTo', 'approver'); void(0);"><tsa:label labelName="reroute" defaultString="Reroute To" />:</b></a>&nbsp;
            <tsa:input type="text" name="routTo" onfocus="this.blur();" onchange="upperCase(this);" /></td>
          </tsa:tr>
          <tsa:tr field="reroute">
            <td valign=bottom nowrap>
              <input type=checkbox name="c_checkbox" value="Y" onclick="setReturn();" onfocus="checkAction('reroute');"><tsa:label labelName="returnmereroute" defaultString="Return to me after reroute" />.
              <tsa:hidden name="returnTo" value=""/>
            </td>
          </tsa:tr>
          <tsa:tr field="defer">
            <td valign=middle nowrap><b><A HREF="javascript: browseLookup('deferTo', 'approver'); void(0);"><tsa:label labelName="defer" defaultString="Defer To" />:</b></a>&nbsp;
            <tsa:input type="text" name="deferTo" onfocus="this.blur();" onchange="upperCase(this);" /></td>
          </tsa:tr>
        </table>
      </td>
    </tr>
    <tr>
      <td colspan=2><br><br></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<br>

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
              <td nowrap align=right><b><tsa:label labelName="req-requisitionerName" defaultString="Requisitioner" />:</b>&nbsp;</td>
              <td nowrap width=100%><%=requisitioner.getDisplayName()%></td>
            </tsa:tr>
            <tsa:tr field="req-department">
              <td nowrap align=right><b><tsa:label labelName="req-department" defaultString="Department" />:&nbsp;</b></td>
              <td nowrap><%=reqHeader.getDepartmentCode()%></td>
            </tsa:tr>
            <tsa:tr field="req-telephoneNumber">
              <td nowrap align=right><b><tsa:label labelName="req-telephoneNumber" defaultString="Telephone Number" />:</b>&nbsp;</td>
              <td nowrap><%=requisitioner.getPhoneNumber()%></td>
            </tsa:tr>
            <tsa:tr field="req-requestDate">
              <td nowrap align=right><b><tsa:label labelName="requestDate" defaultString="Request Date" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(reqHeader.getRequisitionDate(), oid, userDateFormat)%></td>
            </tsa:tr>
            <tsa:tr field="req-currency">
              <td nowrap align=right><b><tsa:label labelName="req-currency" defaultString="Currency" />:</b>&nbsp;</td>
              <td nowrap><%=reqHeader.getCurrencyCode()%></td>
            </tsa:tr>
<%	if (!HiltonUtility.isEmpty(reqHeader.getInternalComments())) { %>
            <tsa:tr field="req-purpose">
              <td nowrap align=right valign=top><b><tsa:label labelName="req-purpose" defaultString="Purpose" />:</b>&nbsp;</td>
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
            <tsa:tr field="req-addressLine1">
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
            <td class=browseRow height=12px nowrap><tsa:label labelName="req-shp-attention" defaultString="Attn" />:&nbsp;<%=reqHeader.getShipAttn()%></td>
            </tsa:tr>
<%		}
      if (reqHeader.getRequiredDate() != null) { %>
             <tsa:tr field="req-requiredDate">
            <td class=browseRow height=12px nowrap><tsa:label labelName="req-requiredDate" defaultString="Required By" />:&nbsp;<%=HiltonUtility.getFormattedDate(reqHeader.getRequiredDate(), oid, userDateFormat)%></td>
            </tsa:tr>
<%		} %>
            <!--tr><td class=browseRow height=12px nowrap>Priority:&nbsp<%//=HiltonUtility.ckNull(requisitionHeader.getPriorityCode())%></td></tr>
            <tr><td class=browseRow height=12px nowrap>Ship Via:&nbsp<%//=HiltonUtility.ckNull(requisitionHeader.getShippingCode())%></td></tr>
            <tr><td class=browseRow height=12px nowrap>Routing:&nbsp;<%//=HiltonUtility.ckNull(requisitionHeader.getRouting())%></td></tr-->
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
    if (vendor == null)
    {
      vendor = new Address();
    }
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
            <td class=browseRow height=12px nowrap><b><%=reqHeader.getVendorId()%></b></td>
            </tsa:tr>
            <tsa:tr field="req-vendorName">
            <td class=browseRow height=12px nowrap><%=reqHeader.getVendorName()%></td>
            </tsa:tr>
            <tsa:tr field="req-sup-addressLine1">
            <td class=browseRow height=12px nowrap><%=vendor.getAddressLine1()%></td>
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
            <td class=browseRow height=12px nowrap><%=vendor.getAddressLine4()%></td>
            </tsa:tr>
<%	}%>
            <tr><td class=browseRow height=12px nowrap><%=vendor.getCity()%>&nbsp;<%=vendor.getState()%>&nbsp;<%=vendor.getPostalCode()%></td></tr>
            <tsa:tr field="req-sup-country">
            <td class=browseRow height=12px nowrap><%=vendor.getCountry()%></td>
            </tsa:tr>
<%	if (!HiltonUtility.isEmpty(reqHeader.getVendorAttn())) { %>
            <tsa:tr field="req-sup-attention">
            <td class=browseRow height=12px nowrap>
            <tsa:label labelName="req-sup-attention" defaultString="Attn" />:&nbsp;<%=reqHeader.getVendorAttn()%>
            </td>
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
            <tsa:tr field="req-billToCode">
            <td class=browseRow height=12px nowrap><b><%=reqHeader.getBillToCode()%></b></td>
            </tsa:tr>
            <tsa:tr field="req-bil-addressLine1">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine1()%></td>
            </tsa:tr>
<%	if (!HiltonUtility.isEmpty(billTo.getAddressLine2()))
    { %>
            <tsa:tr field="req-bil-addressLine2">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine2()%></td>
            </tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(billTo.getAddressLine3()))
    { %>
            <tsa:tr field="req-bil-addressLine3">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine3()%></td>
            </tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(billTo.getAddressLine4()))
    { %>
            <tsa:tr field="req-bil-addressLine4">
            <td class=browseRow height=12px nowrap><%=billTo.getAddressLine4()%></td>
            </tsa:tr>
<%	} %>
            <tr><td class=browseRow height=12px nowrap><%=billTo.getCity()%>&nbsp;<%=billTo.getState()%>&nbsp;<%=billTo.getPostalCode()%></td></tr>
            <tsa:tr field="req-bil-country">
            <td class=browseRow height=12px nowrap><%=billTo.getCountry()%>
            </td></tsa:tr>
<%	if (!HiltonUtility.isEmpty(reqHeader.getBillToContact())) { %>
            <tsa:tr field="req-bil-attention">
            <td class=browseRow height=12px nowrap>
            <tsa:label labelName="req-bil-attention" defaultString="Attn" />:&nbsp;<%=reqHeader.getBillToContact()%></td>
            </tsa:tr>
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
          if (s_cmt_print.equals("N"))
          {
            continue;
          }
          iBefore++;
%>
            <tr>
              <td width=75% class=browseRow><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=docText.getStdText()%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
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
              <tsa:td field="req-lineNumber" width="7%" cssClass="browseHdrDk" noWrap="nowrap" >
              &nbsp;<tsa:label labelName="req-hdg-lineNumber" defaultString="Line #" />
              </tsa:td>
              <tsa:td field="req-itemNumber" width="25%" cssClass="browseHdrDk" noWrap="nowrap" >
              &nbsp;<tsa:label labelName="req-hdg-itemNumber" defaultString="Item/Part #" />
              </tsa:td>
<%		if ( reqHeader.getRequisitionType().equals("S") || reqHeader.getRequisitionType().equals("T") || reqHeader.getRequisitionType().equals("I") ) { %>
              <tsa:td field="req-inventoryLocation" width="10%" cssClass="browseHdrDk" noWrap="nowrap" >
              &nbsp;<tsa:label labelName="req-hdg-inventoryLocation" defaultString="Location" />
              </tsa:td>
<%		} else {%>
              <tsa:td field="req-catalog" width="10%" cssClass="browseHdrDk" noWrap="nowrap" >
              &nbsp;<tsa:label labelName="req-hdg-catalog" defaultString="Catalog" />
              </tsa:td>
<%		}%>
              <tsa:td field="req-commodity" width="13%" cssClass="browseHdrDk" noWrap="nowrap" >
              &nbsp;<tsa:label labelName="req-hdg-commodity" defaultString="Commodity" />
              </tsa:td>
              <tsa:td field="req-quantity" width="10%" align="right" noWrap="nowrap" cssClass="browseHdrDk">
              &nbsp;<tsa:label labelName="req-hdg-quantity" defaultString="Quantity" />
              </tsa:td>
              <tsa:td field="req-uom" width="7%" cssClass="browseHdrDk" noWrap="nowrap" >
              &nbsp;<tsa:label labelName="req-hdg-uom" defaultString="UOM" />
              </tsa:td>
              <% if (oid.equalsIgnoreCase("wpc08p")){%>
              		<tsa:td field="req-cost" width="7%" cssClass="browseHdrDk" noWrap="nowrap" >
              		&nbsp;<tsa:label labelName="req-hdg-unitCost" defaultString="Unit Cost" />
              		</tsa:td>
              <% } %>
              <tsa:td field="req-extendedCost" width="13%" align="right" noWrap="nowrap" cssClass="browseHdrDk">
              &nbsp;<tsa:label labelName="req-hdg-extendedCost" defaultString="Extended Cost" />
              </tsa:td>
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
    List lineList = (List) reqHeader.getRequisitionLineList();
    for (int i = 0; i < lineList.size(); i++)
    {
      RequisitionLine reqLine = (RequisitionLine) lineList.get(i);

      BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid);
      BigDecimal bd_unit_price = HiltonUtility.getFormattedPrice(reqLine.getUnitPrice(), oid);
      BigDecimal bd_um_factor = HiltonUtility.getFormattedDollar(reqLine.getUmFactor(), oid);
      BigDecimal bd_extended_cost = HiltonUtility.getFormattedDollar(bd_quantity.multiply(bd_unit_price).multiply(bd_um_factor), oid);
%>
            <tr>
              <tsa:td field="req-lineNumber" width="7%" align="right" cssClass="browseRow"  noWrap="nowrap" >
              <%=i+1%>
              </tsa:td>
              <tsa:td field="req-itemNumber" width="25%" cssClass="browseRow"  noWrap="nowrap" >
              <%=reqLine.getItemNumber()%>
              </tsa:td>
<%		if ( reqHeader.getRequisitionType().equals("S") || reqHeader.getRequisitionType().equals("T") || reqHeader.getRequisitionType().equals("I") ) { %>
              <tsa:td field="req-inventoryLocation" width="10%" cssClass="browseRow"  noWrap="nowrap" >
              <%=reqLine.getItemLocation()%>
              </tsa:td>
<%		} else { %>
              <tsa:td field="req-catalog" width="10%" cssClass="browseRow"  noWrap="nowrap" >
              <%=reqLine.getCatalogId()%>
              </tsa:td>
<%		} %>

              <tsa:td field="req-commodity" width="13%" cssClass="browseRow" noWrap="nowrap" >
              <%=reqLine.getCommodityCode()%>
              </tsa:td>
              <tsa:td field="req-quantity" width="10%" cssClass="browseRow"  noWrap="nowrap" align="right">
              <%=HiltonUtility.getFormattedQuantity(reqLine.getQuantity(), oid)%>
              </tsa:td>
              <tsa:td field="req-uom" width="7%" cssClass="browseRow"  noWrap="nowrap" >
              <%=reqLine.getUmCode()%>
              </tsa:td>
              <% if (oid.equalsIgnoreCase("wpc08p")){%>
              		<tsa:td field="req-cost" width="7%" cssClass="browseRow"  noWrap="nowrap" >
              		<%=HiltonUtility.getCurrency(bd_unit_price, currencyCode, oid)%>
              		</tsa:td>
              <% } %>
              <tsa:td field="req-extendedCost" width="13%" cssClass="browseRow"  noWrap="nowrap" align="right" >
              <%=HiltonUtility.getCurrency(bd_extended_cost, currencyCode, oid)%>
              </tsa:td>

            </tr>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
              <td colspan=7 class=browseRow><%=reqLine.getDescription()%></td>
            </tr>
<%
	AltText altText = reqLine.getAltText();
	if (altText != null && DictionaryManager.isVisible(oid, "req-description-alttext")) {
			DocText docText = altText.getDocText();
			if (docText != null) {%>
						<tr id=itemRows>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=9 class=browseRow><%=docText.getStdText()%></td>
						</tr>
<%		}
		}%>

		<% if (oid.equalsIgnoreCase("hoy08p")) { %>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<td colspan=7 class=browseRow>
					<b><tsa:label labelName="req-supplier" defaultString="Supplier" />:&nbsp;</b>
					<%=reqLine.getVendorId()%>
				</td>
			</tr>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<td colspan=7 class=browseRow>
					<b><tsa:label labelName="req-vendorName" defaultString="Vendor Name" />:&nbsp;</b>
					<%=reqLine.getVendorName()%>
				</td>
			</tr>
		<% } else { %>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<td <%=HtmlWriter.isVisible(oid, "req-line_vendorId")%>colspan=7 class=browseRow></td>
				<tsa:td field="req-line_vendorId" colspan="7" cssClass="browseRow">
				<b>Vendor ID:</b>&nbsp;<%=reqLine.getVendorId()%></tsa:td>
			</tr>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<tsa:td field="req-line_vendorName" colspan="7" cssClass="browseRow"></tsa:td>
				<tsa:td field="req-cost" width="7%" cssClass="browseRow"  noWrap="nowrap">
				<b>Vendor Name:</b>&nbsp;<%=reqLine.getVendorName()%></tsa:td>
			</tr>
		<% } %>

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
					<A HREF="javascript: showBudget('<%=s_account%>','<%=reqHeader.getFiscalYear()%>','<%=reqLine.getCommodityCode()%>'); void(0);"><%=s_account%></A>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, currencyCode, oid)%></td>
<%} else  {%>
					<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, currencyCode, oid)%></td>
<% } %>
				</tr>
<%if (!HiltonUtility.isEmpty(laccount.getAllocationDescription()) && oid.equalsIgnoreCase("hoy08p")) {	%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=7 class=browseRow><b>Description:</b>&nbsp;<%=laccount.getAllocationDescription()%></td>
				</tr>
<%					}
			if (oid.equalsIgnoreCase("qri06p"))
			{	%>
				<tr>
					<td colspan="8">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%							if ( !HiltonUtility.isEmpty(s_fld4))
							{	%>
								<tr>
									<td width="50px">&nbsp;</td>
									<td><b><tsa:label labelName="AC04" defaultString="UDF4" />:&nbsp;<%=s_fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", s_fld4)%></td>
								</tr>
<%							}
							if ( !HiltonUtility.isEmpty(s_fld5))
							{	%>
								<tr>
									<td width="50px">&nbsp;</td>
									<td><b><tsa:label labelName="AC05" defaultString="UDF5" />:&nbsp;<%=s_fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", s_fld5)%></td>
								</tr>
<%							}	%>
						</table>
					</td>
				</tr>
<%			}
		}
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
<%
    int	acci = 0;
    List accList = (List) reqHeader.getAccountList();

    if (accList != null && accList.size() > 0)
    {
%>
          <td valign=top>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tr>
              <td><b>Account:&nbsp;</b></td>
<%
      for (int i = 0; i < accList.size(); i++)
      {
        Account account = (Account) accList.get(i);

        String	s_account = "";
        String s_fld4 = "";
		String s_fld5 = "";
        String	s_accArray[] = new String[15];

        s_accArray[0] = account.getFld1();
        s_accArray[1] = account.getFld2();
        s_accArray[2] = account.getFld3();
        s_accArray[3] = account.getFld4();
        s_accArray[4] = account.getFld5();
        s_accArray[5] = account.getFld6();
        s_accArray[6] = account.getFld7();
        s_accArray[7] = account.getFld8();
        s_accArray[8] = account.getFld9();
        s_accArray[9] = account.getFld10();
        s_accArray[10] = account.getFld11();
        s_accArray[11] = account.getFld12();
        s_accArray[12] = account.getFld13();
        s_accArray[13] = account.getFld14();
        s_accArray[14] = account.getFld15();

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
        if ( !HiltonUtility.isEmpty(s_accArray[3]))
		{
			s_fld4 = s_accArray[3];
		}
		if ( !HiltonUtility.isEmpty(s_accArray[4]))
		{
			s_fld5 = s_accArray[4];
		}
        acci++;
%>
             <td nowrap><%=s_account%></td>
              <td width=15px>&nbsp;</td>
              <td nowrap>$<%=HiltonUtility.getFormattedDollar(account.getAllocAmount(), oid)%></td>
            </tr>
<%		if (oid.equalsIgnoreCase("qri06p")) {	%>
			<tr width="100%">
				<td colspan="4" width="100%">
					<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%				if ( !HiltonUtility.isEmpty(s_fld4)) {	%>
					<tr>
						<td width="50px">&nbsp;</td>
						<td nowrap><b><tsa:label labelName="AC04" defaultString="UDF4" />:&nbsp;<%=s_fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", s_fld4)%></td>
					</tr>
<%				}
					if ( !HiltonUtility.isEmpty(s_fld5)) {	%>
					<tr>
						<td width="50px">&nbsp;</td>
						<td nowrap><b><tsa:label labelName="AC05" defaultString="UDF5" />:&nbsp;<%=s_fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", s_fld5)%></td>
					</tr>
<%				}	%>
					</table>
				</td>
			</tr>
<%			}	%>
            <tr>
              <td>&nbsp;</td>
<%		} %>
            </tr>
            </table>
          </td>
<%	} %>
          <td width=100%>
            <table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
            <tsa:tr field="req-subtotal">
              <td width=72% class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-subtotal" defaultString="Subtotal" />:</td>
              <td width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getSubtotal(), currencyCode, oid)%></td>
              <td width=15% class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-discountAmount">
        <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-discountAmount" defaultString="Discount" />:</td>
        <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getDiscountAmount(), currencyCode, oid)%></td>
        <td class=browseRow nowrap align=right>&nbsp;</td>
      </tsa:tr>
            <tsa:tr field="req-taxAmount">
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-taxAmount" defaultString="Tax" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getTaxAmount(), currencyCode, oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-shippingCharges">
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingCharges" defaultString="Shipping" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getShippingCharges(), currencyCode, oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-shippingTaxAmount">
        <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
        <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getShippingTaxAmt(), currencyCode, oid)%></td>
        <td class=browseRow nowrap align=right>&nbsp;</td>
      </tsa:tr>
            <tsa:tr field="req-otherCharges">
              <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherCharges" defaultString="Other" />:</td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getOtherCharges(), currencyCode, oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="req-otherTaxAmount">
        <td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="req-otherTaxAmount" defaultString="Other Tax" />:</td>
        <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getOtherTaxAmount(), currencyCode, oid)%></td>
        <td class=browseRow nowrap align=right>&nbsp;</td>
      </tsa:tr>
            <tsa:tr field="req-Total">
              <td class=browseRow nowrap align=right><b><tsa:label labelName="req-total" defaultString="Total" />:</b></td>
              <td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(reqHeader.getTotal(), currencyCode, oid)%></td>
              <td class=browseRow nowrap align=right>&nbsp;</td>
            </tsa:tr>
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
<%
  if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
  List	bList	= (List) request.getAttribute("budgetReviewList") ;
  String	udfArray[] = (String[]) request.getAttribute("budgetUdfArray") ;
  String	labelArray[] = (String[]) request.getAttribute("budgetLabelArray") ;
  int	budgetColumns = Integer.parseInt((String) request.getAttribute("budgetColumns")) ;
%>
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
              <td class=browseHdrDk width= "38%" nowrap align=left>&nbsp;
				<tsa:label labelName="bgt-string" defaultString="Account" /></td>
              <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;Amount</td>
              <% if (oid.equalsIgnoreCase("wpc08p")){%>
	              <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;
					<tsa:label labelName="rec-balance" defaultString="Balance" /></td>
	              <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;
					<tsa:label labelName="req-budget" defaultString="Budgeted" /></td>
	          <%}
              else {%>
		          <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;Budgeted</td>
	              <td class=browseHdrDk width= "20%" nowrap align=right>&nbsp;Balance</td>
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
            <table id=budgetRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%
    for (int i = 0; i < bList.size(); i++)
    {
    	Object[] bTab = (Object[]) bList.get(i) ;
		BudgetTran bTran;
		BudgetAudit bAudit;
		BudgetCenter bCenter;
		BigDecimal	tran_amount;

		if (oid.equalsIgnoreCase("WPC08P"))
		{
			tran_amount = (BigDecimal) bTab[0];
			bCenter = (BudgetCenter) bTab[1];
		} else {
			bTran = (BudgetTran) bTab[0];
			bAudit = (BudgetAudit) bTab[1];
			bCenter = (BudgetCenter) bTab[2];
			tran_amount = bAudit.getTranAmt();
		}

		String bCenterData[] = new String[15];
		bCenterData[0] = bCenter.getBudget1() ;
		bCenterData[1] = bCenter.getBudget2() ;
		bCenterData[2] = bCenter.getBudget3() ;
		bCenterData[3] = bCenter.getBudget4() ;
		bCenterData[4] = bCenter.getBudget5() ;
		bCenterData[5] = bCenter.getBudget6() ;
		bCenterData[6] = bCenter.getBudget7() ;
		bCenterData[7] = bCenter.getBudget8() ;
		bCenterData[8] = bCenter.getBudget9() ;
		bCenterData[9] = bCenter.getBudget10() ;
		bCenterData[10] = bCenter.getBudget11() ;
		bCenterData[11] = bCenter.getBudget12() ;
		bCenterData[12] = bCenter.getBudget13() ;
		bCenterData[13] = bCenter.getBudget14() ;
		bCenterData[14] = bCenter.getBudget15() ;
		BigDecimal	bd_amount = HiltonUtility.getFormattedDollar(tran_amount, oid);
		BigDecimal	bd_budgeted = HiltonUtility.getFormattedDollar(bCenter.getBudgeted(), oid);
		BigDecimal	bd_preencumbered = HiltonUtility.getFormattedDollar(bCenter.getPreEncumbered(),oid) ;
		BigDecimal	bd_encumbered = HiltonUtility.getFormattedDollar(bCenter.getEncumbered(),oid) ;
		BigDecimal	bd_expensed = HiltonUtility.getFormattedDollar(bCenter.getExpensed(),oid) ;
		BigDecimal	bd_balance = HiltonUtility.getFormattedDollar(bd_budgeted.subtract(bd_preencumbered).subtract(bd_encumbered).subtract(bd_expensed),oid) ;
%>
        <tr>
<%
		String glAcctStr = "" ;
    	for (int ix = 0; ix < budgetColumns; ix++)
    	{
    		if (glAcctStr.length() > 0) glAcctStr = glAcctStr + s_account_separator ;
    		glAcctStr = glAcctStr + bCenterData[ix] ;
    } %>

	          <td class=browseRow width= "38%" nowrap><%=glAcctStr%></td>
	          <td class=browseRow width= "20%" nowrap align=right><%=bd_amount%></td>
	          <% if (oid.equalsIgnoreCase("wpc08p")){%>
	          	<td class=browseRow width= "20%" nowrap align=right><%=bd_balance%></td>
              	<td class=browseRow width= "20%" nowrap align=right><%=bd_budgeted%></td>
	          <%}
              else {%>
              	<td class=browseRow width= "20%" nowrap align=right><%=bd_budgeted%></td>
              	<td class=browseRow width= "20%" nowrap align=right><%=bd_balance%></td>
              <% } %>
            </tr>
            <%
            	String	cmtStr = bCenter.getComments() ;
            	if (cmtStr == null) cmtStr = "" ;
            	if (cmtStr.trim().length() > 0)
            	{ %>
            <tr>
              <td colspan=<%=budgetColumns + 3%> class=browseRow><%=cmtStr.trim()%></td>
            </tr>
            <% } %>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
    </table>
  </td>
</tr>
</table>

<br>

<% } %>
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
          if (s_cmt_print.equals("N"))
          {
            continue;
          }
          iAfter++;
%>
            <tr>
              <td width=75% class=browseRow><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=docText.getStdText()%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
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
<br>

<br>
<br>

<jsp:include page="/requests/req_approval_buttons.jsp" />

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
              <td align=center>You have already processed Requisition headerEncoder.encodeForHTML(<%=reqHeader.getRequisitionNumber())%>.</td>
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

//-->
</script>


      <%@ include file="/system/footer.jsp" %>

