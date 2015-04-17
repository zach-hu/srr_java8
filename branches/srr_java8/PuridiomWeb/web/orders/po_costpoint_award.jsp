<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.xml.parsers.*,org.w3c.dom.*" %>

<%
	ArrayList errorList = new ArrayList() ;
	PoHeader poHeader = (PoHeader)request.getAttribute("poHeader");
	String s_notes = (String)request.getAttribute("ApprovalLog_approverNotes");
	
	String s_po_type = poHeader.getPoType();
	String s_form_number = poHeader.getPoNumber();
	String s_header_ic = poHeader.getIcPoHeader().toString() ;
	boolean bForward = false;
	String poAction = (String)request.getAttribute("poaction");
	if(poAction == null){ poAction = "VALIDATE";	}
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String		targetUrl = propertiesManager.getProperty("WEBVSE_CLIENT","SERVICEURL","http://localhost/WebVSE_Client/PO_Webservice") ;
	targetUrl = targetUrl + "?IC_PO_HEADER=" + s_header_ic ;

	HttpURLConnection connection = null;
	InputStream inputStream = null;
	URL 	url = null;

	try {
		url = new URL(targetUrl );
	}
	catch(Exception e) {
		e.printStackTrace();
		throw e;
	}


	try {
		connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST") ;
		connection.setUseCaches(false);
		connection.setAllowUserInteraction(false);
		connection.setRequestProperty("Content-Type","text/xml");

		OutputStream os = connection.getOutputStream();

		OutputStreamWriter osw = new OutputStreamWriter(os);
		osw.write("");
		osw.flush();
		osw.close();

		inputStream = connection.getInputStream();
		DataInputStream iData = new DataInputStream(inputStream);
		String iLine = null;
		StringBuffer buf = new StringBuffer("");

		do {
			try {
				iLine = iData.readLine();
			}
			catch(IOException e) {
				System.out.println("readLine Exception: " + e.toString());
			}
			if (iLine == null) {
				break;
			}
			if (iLine.trim().length() > 0) {
				buf.append(iLine);
			}
		} while(true);

        String s_input = buf.toString();
		inputStream.close() ;

		Document doc ;

		String severity = "0";

		try {
		    InputStream is = new ByteArrayInputStream(s_input.getBytes("UTF-8"));
		    DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		    DocumentBuilder db=dbf.newDocumentBuilder();
//		    doc=db.parse(xmlfile);
		    doc = db.parse(is) ;
		    NodeList nlist = doc.getFirstChild().getChildNodes() ;
		    int nlength = nlist.getLength() ;
		    for (int ix = 0; ix < nlength; ix++) {
		    	Node nnode = nlist.item(ix) ;
		    	String nname = nnode.getNodeName() ;
		    	if (nname.equalsIgnoreCase("severity")) {
		    		severity = nnode.getLastChild().getNodeValue() ;
		    	}
		    	if (nname.equalsIgnoreCase("message")) {
		    		NodeList mlist = nnode.getChildNodes() ;
		    		int mlength = mlist.getLength() ;
		    		String msgText = "" ;
		    		Hashtable ht = new Hashtable() ;
		    		for (int im = 0; im < mlength; im++) {
		    	    	Node mnode = mlist.item(im) ;
		    	    	String mname = mnode.getNodeName() ;
			    		String mvalue = mnode.getLastChild().getNodeValue() ;
			    		ht.put(mname, mvalue) ;
		    		}
		    		errorList.add(ht);
		    	}
		    }

		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	} catch (Exception e) {
	    e.printStackTrace();
	}
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%= poHeader.getIcPoHeader() %>"/>
<tsa:hidden name="Account_icHeader" value="<%= poHeader.getIcPoHeader() %>"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="PoHeader_status" value="<%=poHeader.getStatus()%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=poHeader.getPoType()%>"/>
<tsa:hidden name="poaction" value=""/>
<tsa:hidden name="poForwardOption" value=""/>
<tsa:hidden name="goforward" value=""/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="ApprovalLog_approverNotes" value="<%=s_notes%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class="hdr12">CostPoint Submission Results</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center">
		<font class="formType"><%=OrderType.toString(poHeader.getPoType(), oid)%> </font><font class="hdr12">#<%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
<div id="costpointresponse" style="display:none;">
<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 500px; height: 200px; align:center; overflow-y:visible; overflow-x:auto;">
<table border="0" cellpadding="0" cellspacing="0" width="445px">
	<tr class="mnav" height="18px">
		<td nowrap class="mnav" align="center">&nbsp;</td>
		<td width="15px" class="mnav">&nbsp;</td>
		<td nowrap class="mnav" align="center"><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "csp-title-message", "Message from Costpoint")%></td>
		<td width="15px">&nbsp;</td>
		<!--td nowrap class="mnav" align="center" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%></td>
		<td width="15px" class="mnav">&nbsp;</td-->
		<td nowrap class="mnav" align="center"><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "csp-title-severity", "Severity")%></td>
		<td width="15px">&nbsp;</td>
	</tr>
<%
		String classType = "summary";
		int counter = 1;
		int result = 0 ;
		for (int i = 0; i < errorList.size(); i++)
		{
			Hashtable resp = (Hashtable)errorList.get(i);
			String	  severity = (String) resp.get("MsgSeverity") ;
			String	  message = (String) resp.get("MsgText") ;
			if (severity.equals("1")) severity = "W" ;
			if (severity.compareTo("1") > 0) severity = "E" ;

			if (severity.equalsIgnoreCase("E"))
			{
				result = -1 ;
				severity = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rul-value-error", "Error");

			}
			else if (severity.equalsIgnoreCase("W"))
			{
				if (result > -1) result = 0 ;
				severity = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rul-value-warning", "Warning");
			}
%>
			<tr class="<%=classType%>" height="20px">
				<td nowrap class="<%=classType%>" align="right" valign="top"><%=counter%>.</td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
				<td class="<%=classType%>" align="left" valign="top"><%=message%></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
				<!--td class="<%=classType%>" align="center" ><img src="<%=contextPath%>/images/delete.gif" border="0" height="10" width="10"></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td-->
				<td class="<%=classType%>" nowrap align="center" valign="top"><%=severity%></td>
				<td class="<%=classType%>" width="15px">&nbsp;</td>
			</tr>
		<%if(classType.equalsIgnoreCase("browseRow"))
    		{
				classType = "summary";
    		}
			else if(classType.equalsIgnoreCase("summary"))
			{
				classType = "browseRow";
			}
			counter++;
		} %>
	<tr ><td nowrap colspan="7"><hr class=browseHR></td></tr>
	<tr height="20px">
		<td nowrap colspan="7" align="right">Costpoint Award Result&nbsp;:&nbsp;<b>
				<%if(result == 1)
			{
			 out.print("Passed");
			}
			else if (result == 0)
			{
			 out.print("Passed With Warnings");
			}
			else if (result == -1)
			{
			 out.print("Failed");
			}%>!</b>
		</td>
	</tr>
</table>
</div>
</div>	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
			<TR>

			</tr>
		<%if(result == 1 || result == 0)
		{%>
			 <tr>
    		<td>
    		<div id="nocostpointresponse" style="display:none;">
      		<TABLE align="center">
      			<tr>
      			<TD VALIGN="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></TD>
      			<TD VALIGN="MIDDLE" CLASS="basic"><B>Please wait while we validate your order.</B></TD>
      			</tr>
      		</TABLE>
      		</div>
    		</td>
			</tr>
		<%}
		if (result == -1 )
		{%>
			<TD align="CENTER"><a HREF="javascript: returnToOrder();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order"></a></TD>
		<%}%>
	</TR>
</TABLE>


<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	actionCd = "<%= headerEncoder.encodeForJavaScript(poAction) %>" ;
	self.focus();
	<%if(result == 1 || result == 0)
	{%>
		displayArea('nocostpointresponse');
        orderAward();
	<%}
  	else
  	{%>
  		displayArea('costpointresponse');
  	<%}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

  	function orderAward()
	{
		if (actionCd == "APPROVE") {
			doSubmit('orders/po_forward_options.jsp', 'PoApprove');
		} else {
	  		frm.poaction.value = "AWARD";
			doSubmit('orders/po_forward_options.jsp', 'PoAward');
		}
	}

	function returnToOrder() {
		if (actionCd == "APPROVE") {
			doSubmit('orders/po_approval.jsp', 'PoApprovalRetrieve') ;
		} else {
			if (frm.viewType.value == "CLASSIC") {
				doSubmit('orders/po_summary.jsp', 'PoRetrieve') ;
			} else {
				doSubmit('orders/po_review.jsp', 'PoRetrieve') ;
			}
		}
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/footer.jsp" %>
