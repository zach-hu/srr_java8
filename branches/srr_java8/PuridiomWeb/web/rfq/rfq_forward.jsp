<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.*" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	String RfqHeader_vendorAwarded = (String)request.getAttribute("RfqHeader_vendorAwarded");
	List rfqHeaderList = (List) request.getAttribute("rfqHeaderList");
	List approvalLogList = (List) request.getAttribute("routingList");
	List rfqApprovalLogList = (List) request.getAttribute("rfqApprovalLogList");
	List vendorList = (List)request.getAttribute("rfqVendorList");
	List poList = (List) request.getAttribute("poList");
	String routTo = (String) request.getAttribute("routTo");
	String fromPage = (String) request.getAttribute("fromPage");
	String itemList = "Y";
	if (rfqHeaderList == null || rfqHeaderList.isEmpty()) {
		itemList = "N";
	}

	BigDecimal bd_ic_rfq_header = rfqHeader.getIcRfqHeader();
	String icRfqHeader = bd_ic_rfq_header.toString();
	String	s_rfq_number = rfqHeader.getRfqNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_rfq_type = rfqHeader.getRfqType();
	String	s_status = rfqHeader.getStatus();

	if (s_rfq_type == null)
	{
		s_rfq_type = "Solicitation ";
	}
	else
	{
		s_rfq_type = RfqType.toString(s_rfq_type, oid);
	}

	Map approvalNotes = new HashMap();
	String backgroundClass = "basic";
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_ic_rfq_header%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align=center>
		<%=s_rfq_type%>&nbsp;
		<font class=hdr12><%=s_rfq_number%></font>&nbsp;has been
		<%	if (fromPage.equalsIgnoreCase("rfq_award_options.jsp")) { %>
				forwarded
		<%
				if (approvalLogList!=null) {
					ApprovalLog approvalLog = (ApprovalLog) approvalLogList.get(0);
		%>
					to: <br><b><%=approvalLog.getApproverName()%></b>
		<%
				}
		 	}
		 	else if (fromPage.equalsIgnoreCase("rfq_approval.jsp")) {
		 		UserProfile userCurrent = UserManager.getInstance().getUser(oid, uid);
		 		if (rfqHeader.getStatus().equalsIgnoreCase("2020") || rfqHeader.getStatus().equalsIgnoreCase("2005")) {
		%>
			 		approved
		<%
				}
				else if (rfqHeader.getStatus().equalsIgnoreCase("2010")) {
		%>
					rejected
		<%
				}
				else if (rfqHeader.getStatus().equalsIgnoreCase("2017") && !HiltonUtility.isEmpty(routTo)) {
					UserProfile userRoutTo = UserManager.getInstance().getUser(oid, routTo);
		%>
					rerouted to <%=userRoutTo.getDisplayName()%>
		<%
				}
				else if (rfqHeader.getStatus().equalsIgnoreCase("2017")) {
					String sUserCode = "";
					for (int i=0; i<approvalLogList.size(); i++) {
						ApprovalLog approvalLog = (ApprovalLog) approvalLogList.get(i);
						if (approvalLog.getApproved().equalsIgnoreCase("A")) {
							sUserCode = approvalLog.getUserId();
						}
					}
					UserProfile userCode = UserManager.getInstance().getUser(oid, sUserCode);
		%>
					approved, <br>
					and forwarded for approval to <%=userCode.getDisplayName()%>
		<%
				}
			}
		%>
	</td>
</tr>
</table>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td align="center">
		<%@include file="/rfq/rfq_approval_routing_list_amt.jsp" %>
	</td>
</tr>
</table>

<% if (itemList.equalsIgnoreCase("Y")) { %>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="<%=formWidth%>">
  <tr>
  	<td width="<%=formWidth%>" valign="top" align="center">
	  <table align="center" width="673px" border="0" cellpadding="0" cellspacing="0">
	    <tr>
		  	<td class=browseHdr height=18px nowrap align="center">
		  		Solicitations Waiting my Approval
		  	</td>
		</tr>
	  </table>
	</td>
  </tr>
  <tr>
	<td width="<%=formWidth%>" valign="top">
	  <table align="center" width="680px" border="0" cellpadding="0" cellspacing="0">
	    <tr><td valign="top" align="center" width="680px">
	      <table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
			<tr>
			  <td width=5px>&nbsp;</td>
			  <td width=670px class=browseHdrDk align=center valign=top>
				<!--In this part we show some parameters of asset entity like Item Number, Tag Number, Status, Serial, etc  -->
				<table border=0 cellspacing=0 cellpadding=3 width=665px>
				  <tr>
					<td nowrap height=18px class=browseHdrDk width=20% align=left> Type </td>
					<td nowrap height=18px class=browseHdrDk width=15% align=left> Solicitation # </td>
					<td nowrap height=18px class=browseHdrDk width=08% align=center> Amendment </td>
					<td nowrap height=18px class=browseHdrDk width=17% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%> </td>
					<td nowrap height=18px class=browseHdrDk width=12% align=center> Date Created </td>
					<td nowrap height=18px class=browseHdrDk width=12% align=left> Due Date </td>
					<td nowrap height=18px class=browseHdrDk width=16% align=left> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "buyer", "Buyer")%> </td>
				  </tr>
				</table>
				<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
			<%
				for (int i=0; i<rfqHeaderList.size(); i++) {
				  RfqHeader rfqHeaderTemp = (RfqHeader) rfqHeaderList.get(i);
				  ApprovalLog approvalLog = (ApprovalLog) rfqApprovalLogList.get(i);
				  String classtd = "";
				  if (i%2==0)
				  { classtd = "browseRow"; }
				  else
				  { classtd = "summary"; }
			%>
				<table id=browseRows border=0 cellspacing=0 cellpadding=0 width=665px class=browseRow>
				  <tr>
					<td class=<%=classtd%>>
					  <!--In this part we show a list with their respective tagNumber-->
					  <table border=0 cellspacing=0 cellpadding=2 width=665px class=<%=classtd%>>
						<tr class="<%=classtd%>">
						  <!--In this part we get the Solicitation Type -->
						  <td class="<%=classtd%>" align=left width=20% valign=top>
							<%=BrowseUtility.formatBrowseColumnValue(rfqHeaderTemp.getRfqType(),"RFQ-TYPE",oid,userDateFormat)%>
						  </td>
						  <!--In this part we get the Solicitation Number -->
						  <td class="<%=classtd%>" align=left width=15% valign=top>
							<a href="javascript: approveRfq('<%=rfqHeaderTemp.getIcRfqHeader()%>','<%=rfqHeaderTemp.getRfqType()%>','<%=approvalLog.getComp_id().getSequence()%>'); void(0);" onMouseOver="showDetails(<%=i%>);" onMouseOut="hideDetails(<%=i%>);"><%=rfqHeaderTemp.getRfqNumber()%></a>
						  </td>
						  <!--In this part we get the Amendment number -->
						  <td class="<%=classtd%>" align=left width=8% valign=top>
							<%=rfqHeaderTemp.getRfqAmendment()%>
						  </td>
						  <!--In this part we get the Status -->
						  <td class="<%=classtd%>" align=left width=17% valign=top>
							<%=BrowseUtility.formatBrowseColumnValue(rfqHeaderTemp.getStatus(),"STATUS",oid,userDateFormat)%>
						  </td>
						  <!--In this part we get the Date of the Solicitation -->
						  <td class="<%=classtd%>" align=center width=12% valign=top>
							<%=BrowseUtility.formatBrowseColumnValue(rfqHeaderTemp.getRfqDate(),"DATE",oid,userDateFormat)%>
						  </td>
						  <!--In this part we get the Due Date of the Solicitation -->
						  <td class="<%=classtd%>" align=center width=12% valign=top>
							<%=BrowseUtility.formatBrowseColumnValue(rfqHeaderTemp.getDueDate(),"DATE",oid,userDateFormat)%>
						  </td>
						  <!--In this part we get the Buyer name -->
						  <td class="<%=classtd%>" align=center width=16% valign=top>
							<%=BrowseUtility.formatBrowseColumnValue(rfqHeaderTemp.getBuyer(),"USER-ID",oid,userDateFormat)%>
						  </td>
						</tr>
						<tr>
						  <td colspan=7>
							<div  id="details<%=i%>" style="visibility:hidden;display:none;" class=<%=classtd%>>
							  <table id="detailRows" border=0 cellspacing=0 cellpadding=0 class=<%=classtd%> width=95% align=right>
								<tr class=<%=classtd%>>
								  <td class=<%=classtd%>>
									<!--In this part we show information and details-->
									<table border=0 cellspacing=0 cellpadding=0 class=<%=classtd%> width=100%>
									  <tr>
										<td height=18px class=<%=classtd%> width=10% valign=top>
										  <b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "purpose", "Purpose")%>: </b><%=rfqHeaderTemp.getRfqDescription()%>
										</td>
									  </tr>
									</table>
								  </td>
								</tr>
							  </table>
							</div>
						  </td>
						</tr>
					  </table>
					</td>
				  </tr>
				</table>
			<%
				}
			%>
				</div>
			  </td>
			  <td width=5px>&nbsp;</td>
			</tr>
		  </table>
		</td>
	  </tr>
	</table>
  </td></tr>
</table>
<% } %>

<tsa:hidden name="viewNow" value="Y" />
<tsa:hidden name="PdfRfq_vendorId" value="<%=RfqHeader_vendorAwarded%>" />
<!--In this part we have two function the first button validate the correct fields and the second return to the last page-->
<%	if (s_status.compareTo(DocumentStatus.RFQ_PURCHASING) == 0) { %>
<table border=0 cellspacing=0 cellpadding=0 width=680px><td><table width="680px" border="0" align="center"><tr><td><table border=0 cellspacing=0 cellpadding=0 width=680px>
  <tr><td><table width="146" border="0" align="center">
 	<tr>
    	<td colspan="2"><select multiple="multiple" name="suppliers" id="listSuppliers" onChange="checkNone(this);">
      	<option value="none" selected="selected">None</option>
      	<%	if (vendorList != null) {
      			for(int vendorIndex = 0; vendorIndex < vendorList.size(); vendorIndex++) {
      				RfqVendor rfqVendor = (RfqVendor)vendorList.get(vendorIndex); %>
      				<option value="<%=rfqVendor.getComp_id().getVendorId() %>"><%=VendorManager.getInstance().getVendorName(oid, rfqVendor.getComp_id().getVendorId()) %></option>
      	<%		}
      		} %>
    	</select></td>
  </tr>

  <tr>
    <td width="35"><div align="right">
      <input name="printoptions" id="printRFQ" value="P" type="radio" />
    </div></td>
    <td width="101">RFQ</td>
  </tr>

  <tr>
    <td><div align="right">
      <input name="printoptions" id="bidWorkSheet" value="B" type="radio" />
      </div></td>
      <td>Bid WorkSheet</td>
  </tr>
  </table>
  <table width=146 border="0" align="center">
	<tr>
		<td colspan="2" align=center>
			<div id="pxbutton"><a href="javascript: printOption(); void(0);">Print</a></div>
		</td>
		<td colspan="2" align=center>
			<div id="pxbutton"><a href="javascript: createOrders(); void(0);">Award</a></div>
		</td>
		<td colspan="2" align=center>
			<div id="pxbutton"><a href="javascript: viewSolicitation(); void(0);">Return</a></div>
		</td>
  	</tr>
  </table>

  	<a href="javascript: printOption();"></a></td>
    </tr>
  </table></td>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

	function viewSolicitation() {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve');
<%	} else {%>
		doSubmit('/rfq/rfq_review.jsp', 'RfqRetrieve');
<%	}%>
	}

	function createOrders() {
		doSubmit('/rfq/rfq_intent_to_award.jsp', 'RfqRetrieve');
	}

	function approveRfq(ic, type, seq) {
		frm.viewType.value = "WIZARD";
		frm.RfqHeader_icRfqHeader.value = ic;
		var newInputField = "<input type='hidden' name='ApprovalLog_icHeader' value='" + ic + "'>";
		newInputField = newInputField + "<input type='hidden' name='ApprovalLog_icLine' value='0'>";
		newInputField = newInputField + "<input type='hidden' name='ApprovalLog_sequence' value='" + seq + "'>";
		if (frm.ApprovalLog_userId) {
			frm.ApprovalLog_userId.value = '${esapi:encodeForJavaScript(userId)}';
		} else {
			newInputField = newInputField + "<input type='hidden' name='ApprovalLog_userId' value='${esapi:encodeForJavaScript(userId)}'>";
		}
		setHiddenFields(newInputField);
		doSubmit('/rfq/rfq_approval.jsp','ApprovalLogRetrieveById;RfqRetrieve');
	}
	function printOption()
	{
		if(frm.printoptions[1].checked){
			bidWorkNow();
		}
		else if(frm.printoptions[0].checked){
			viewNow();
		}
		else{
			alert("Select a Print Option!");
		}
	}
	function viewNow()
	{
		if(selectedSupplier())
		{
			if(countSuppliers() > 1)
			{
				alert("When viewing Solicitations, only one Supplier can be selected.");
			}
			else
			{
				getSuppliers();
				popup();
				popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
				doSubmitToPopup('', 'PrintRfqPdf', 'width=775px', 'height=850px');
			}
		}
	}

	function bidWorkNow()
	{
		if(selectedSupplier("bid"))
		{
			if(countSuppliers() < 1)
			{
				alert("Select at leat one supplier to view.");
			}
			else
			{
				selectedSuppliers = new Array();
				for (var i = 0; i < frm.suppliers.options.length; i++) {
					if (frm.suppliers.options[i].selected)
						selectedSuppliers.push(frm.suppliers.options[i].value);
				}
				getSuppliersSep(",");
				popup();
				popupParameters = popupParameters + ";format=pdf";
				popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
				popupParameters = popupParameters + ";selectedSuppliers=" + selectedSuppliers;
				doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintWrkPdf', 'width=775px', 'height=850px');
			}
		}
	}
	function checkNone(mySelect)
	{
		if(mySelect.length==1)
		{
			return;
		}

		for(i=1; i<mySelect.length; i++)
   		{
   			if(mySelect[i].selected)
   			{
   				mySelect[0].selected=false;
   				return;
   			}
		}
	}

	function getSuppliers()
	{
		var supplierIds = '';
		var element = frm.suppliers;

		for(i = 0; i < element.length; i++)
  		{
  			if(element.options[i].selected == true)
    		{
  				supplierIds = supplierIds + frm.suppliers[i].value + ";";
  			}
  		}
  		frm.PdfRfq_vendorId.value = supplierIds;
	}

	function selectedSupplier(bid)
	{
		var element = frm.suppliers;
		selectedUser = false;
		for(i = element.options.length; i > 0 ; i--)
  		{
    			if(element.options[i - 1].selected == true)
    			{
    				if(bid == "bid")
    				{
    					if(element.options[i - 1].value == "none")
    					{
    						selectedUser = false;
    					}
					else
					{
						selectedUser = true;
					}
    				}
    				else
    				{
    					selectedUser = true;
    				}
    			}
  		}
  		if(!selectedUser)
  		{
  			alert("You must first select a Supplier.");
  			return false;
  		}
		return true;
	}

	function getSuppliersSep(sep)
	{
		var supplierIds = '';
		var element = frm.suppliers;

		for(i = 0; i < element.length; i++)
  		{
  			if(element.options[i].selected == true)
    		{
  				supplierIds = supplierIds + frm.suppliers[i].value + sep;
  			}
  		}
  		frm.PdfRfq_vendorId.value = supplierIds;
	}


	function popup()
	{
		popupParameters = "RfqHeader_icRfqHeader=<%=icRfqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";PdfRfq_vendorId=" + frm.PdfRfq_vendorId.value;
	}

	function countSuppliers()
	{
		var element = frm.suppliers;
		var count = 0;
		for(i = element.options.length; i > 0 ; i--)
  		{
    		if(element.options[i - 1].selected == true)
    		{
    			count = count + 1;
    		}
  		}

  		return count;
	}

	function showDetails(row) {
		displayArea("details" + row);
	}
	function hideDetails(row) {
		hideArea("details" + row);
	}

// End Hide script -->
</SCRIPT>