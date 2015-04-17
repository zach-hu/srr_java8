<%@ page language="java" errorPage="/system/error_popup.jsp"%>
<%@ include file="/system/prevent_caching.jsp"%>
<%@ include file="/system/header_popup.jsp"%>
<%@ page import="java.util.*"%>

<%
	List poList = (List) request.getAttribute("poDataList");
	int poSize = poList.size();

	if (poSize > 0)
	{
%>

<br>
<br>
<table width="90%" border="0" align="center" style="border: 1px solid #667788">
	<caption class="hdr12" style="font-size: 1em; padding: 5px 5px 2px 5px; border: 1px solid #667788"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-awarding-prefixQuestion", "What prefix should be set the Order(s)?")%></caption>
	<tr>
		<th scope="col" width="50%" align="right" style="font-weight: normal;"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier", "Supplier")%></th>
		<th scope="col" width="50%" align="left" style="font-weight: normal;"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "prefix", "Prefix")%></th>
	</tr>
	<%
			for (int i = 0; i < poSize; i++)
			{
				List values = (List) poList.get(i);
				String po = (String) values.get(0) + ">";
				System.out.println(values);
				System.out.println(po);
				if(po.equals(">")) continue;
				String vendorName = po.split(">")[0];
				po += (String) values.get(1) + ">";
				List items = (List) values.get(2);
				for (int j = 0; j < items.size(); j++)
				{
					po += (String) items.get(j) + ":";
				}
				po = po.substring(0, po.length() - 1);
	%>
	<tr>
		<th scope="row" align="right" style="font-weight: normal;"><%=VendorManager.getInstance().getVendorName(oid, vendorName)%>:</th>
		<td align="left"><input type="text" name="prefix" value="" size="6" onChange="upperCase(this)" style="text-transform: uppercase"> <tsa:hidden name="poDataList" value="<%= po%>"/></td>
	</tr>
	<%
	}
	%>
</table>
<table width="100%" cellspacing="5">
	<tr>
		<td align="right" id="buttons"><a href="javascript: savePrefix(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 title="Save Prefix and Continue Award" alt="Save Prefix and Continue Award"></a></td>
		<td align="left" id="buttons"><a href="javascript: cancel(); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 title="Cancel" alt="Cancel"></a></td>
	</tr>
</table>

</form>
<%
	} else
	{
%>


<table width="100%" cellspacing="5">
	<tr>
		<td><span class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-awarding-noItemSelected", "Please, select an item to award")%></span><br>
		<br>
		</td>
	</tr>
	<tr>
		<td align="center" id="buttons"><a href="javascript: cancel(); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 title="Cancel" alt="Cancel"></a></td>
	</tr>
</table>
<%
}
%>
</body>
<script type="text/javascript">
<!--
	function thisLoadPopup() {
		parent.resizeTypeOptions(<%= poSize%>);
		if (document.getElementsByName("prefix").length > 0)
			document.getElementsByName("prefix")[0].focus();
	}

	function savePrefix(prefix) {
		var list = document.getElementsByName("poDataList");
		var valid = true;
    	for(var i = 0; i < list.length; i++) {
    		if(document.getElementsByName("prefix")[i].value == '')	 { valid = false; break; }
			document.getElementsByName("prefix")[i].value = document.getElementsByName("poDataList")[i].value + '>' + document.getElementsByName("prefix")[i].value;
    	}
    	if (valid) {
	    	parent.assignPoList(document.getElementsByName("prefix"));
    	}
    	else {
    		alert('You must specify a prefix');
    	}
	}

	function cancel() {
		parent.hidePopUpWindow();
	}
//-->
</script>
<%@page import="com.tsa.puridiom.usermanager.UserManager"%>
<%@page import="com.tsa.puridiom.vendor.VendorManager"%>
<%@page import="com.tsagate.properties.DictionaryManager"%>
</html>
