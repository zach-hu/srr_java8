<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<!--In this part we get information about assets-->
<%	List assetStatusList	= (List)request.getAttribute("assetStatusList");
	List assetList			= (List)request.getAttribute("assetList");
	BigDecimal statusSum = (BigDecimal)request.getAttribute("statusSum");
	String Asset_tagNumber = (String)request.getAttribute("Asset_tagNumber");
	String Asset_itemNumber 	= (String)request.getAttribute("Asset_itemNumber");
	String Asset_description	= (String)request.getAttribute("Asset_description");

	String  action = "";
	if (HiltonUtility.isEmpty(Asset_tagNumber))
	{
		Asset asset = (Asset) assetList.get(0);
		Asset_tagNumber = asset.getTagNumber();
	}
	if (HiltonUtility.isEmpty(Asset_itemNumber))
	{
		Asset_itemNumber = (String)request.getAttribute("InvItem_itemNumber");
		action = "new";
	}
	if (HiltonUtility.isEmpty(Asset_description))
	{
		Asset_description = (String)request.getAttribute("InvItem_description");
	}

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_current_process = "ASSETTOTALS";
	String	s_current_page = "/asset/asset_totals.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	String s_zero = "";
	String s_tag_number = Asset_tagNumber;
	String s_item_number = Asset_itemNumber;
	String s_description = Asset_description;
	pageContext.setAttribute("oid", oid);
%>

<tsa:hidden name="Asset_tagNumber" value="<%=Asset_tagNumber%>"/>
<tsa:hidden name="Asset_itemNumber" value="<%=Asset_itemNumber%>"/>
<tsa:hidden name="Asset_description" value="<%=Asset_description%>"/>
<tsa:hidden name="AssetLocation_tagNumber" value=""/>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/asset.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
				<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="assetreview" defaultString="Asset Review" /></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td align=right valign=top nowrap><%@ include file="/asset/asset_display_number.jsp"%></td></tr>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tr>
      <td width="680px" valign="top">
      	<table align="center" width="680px" height="200px" border="0" cellpadding="0" cellspacing="0"><tr><td valign="top" align="center" width="680px">
      	<table border=0 cellspacing=0 cellpadding=2>
		<%
			for(int i=0; i<assetStatusList.size(); i++)
			{ Object temp[] = (Object[]) assetStatusList.get(i);
		%>
			<tr><td width="50%" align="right"><a href="javascript: view<%=temp[0]%>();"><%=temp[0]%></a>: </td><td width="50%" align="left"><%=temp[1]%></td></tr>
		<%
			}
		%>
			<tr><td colspan="2"><table cellspacing=0 cellpadding=0 width="100%"><tr><td height="1px" class="darkShadow"><IMG SRC="<%=contextPath%>/images/none.gif" HEIGHT="1px" WIDTH="100%"></TD></TR></table></td></tr>
			<tr><td width="50%" align="right">Total: </td><td width="50%" align="left"><%=statusSum%></td></tr>
		</table>
		<br>
		<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=500px>
		<tr>
			<!--In this part we show some parameters about asset-->
			<td width=5px>&nbsp;</td>
			<td width=490px class=browseHdrDk align=center valign=top>
			<table border=0 cellspacing=0 cellpadding=3 width=485px>
				<tr>
					<td nowrap height=18px class=browseHdrDk width=10% align=left> Tag Number </td>
					<td nowrap height=18px class=browseHdrDk width=10% align=left> Model </td>
					<td nowrap height=18px class=browseHdrDk width=10% align=left> Serial Number </td>
					<td nowrap height=18px class=browseHdrDk width=10% align=left> <tsa:label labelName="owner" defaultString="Owner" /></td>
					<td nowrap height=18px class=browseHdrDk width=10% align=left> <tsa:label labelName="status" defaultString="Status" /></td>
				</tr>
			</table>
			<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 495px; align:center; overflow-y:visible; overflow-x:auto;">
		<%
			for(int i=0; i<assetStatusList.size(); i++)
			{
			  Object temp[] = (Object[]) assetStatusList.get(i);
			  int k=0;
			  for(int j=0; j<assetList.size(); j++)
			  {
			  	Asset asset = (Asset) assetList.get(j);
			  	if (asset.getAssetStatus().equalsIgnoreCase((String) temp[0])) {
			  		String classtd = "";
			  		if (k%2==0)
			  			{ classtd = "browseRow"; }
			  		else
			  			{ classtd = "summary"; }
		%>
			<div id="<%=temp[0]%><%=k%>" <% if (i==0) { %>style="visibility:visible; display:block;"<% } else { %>style="visibility:hidden; display:none;"<% } %>>
			<table id=browseRows border=0 cellspacing=0 cellpadding=0 width=485px class=browseRow>
				<tr>
					<td class=<%=classtd%>>
					<!--In this part we can show the asset data according with the TagNumber value-->
					<table border=0 cellspacing=0 cellpadding=2 width=485px class=<%=classtd%>>
					<tr class="<%=classtd%>">
						<td class="<%=classtd%>" align=left width=10% valign=top>
							<a href="javascript: viewassetdata('<%=asset.getTagNumber()%>');"><%=asset.getTagNumber()%></a>
						</td>
						<!--In this part we can get the model of asset-->
						<td class="<%=classtd%>" align=left width=10% valign=top>
							<%=asset.getModel()%>
						</td>
						<!--In this part we can get the serial number of asset-->
						<td class="<%=classtd%>" align=left width=10% valign=top>
							<%=asset.getSerialNumber()%>
						</td>
						<!--In this part we can get the owner of asset-->
						<td class="<%=classtd%>" align=left width=10% valign=top>
							<%=asset.getOwner()%>
						</td>
						<!--In this part we can get the status of asset-->
						<td class="<%=classtd%>" align=left width=10% valign=top>
							<%=asset.getAssetStatus()%>
						</td>
					</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
		<%
				k=k+1;
				}
			  }
			}
		%>
			</div>
			</td>
			<td width=5px>&nbsp;</td>
		</tr>
		<tr><td colspan=3 height=5px></tr>
		</table>
		<% if (action.equalsIgnoreCase("new")) { %>
		<table align="center" border=0 cellspacing=0 cellpadding=2>
			<tr><td width="50%" align="right">Asset of <%=Asset_itemNumber%> Item: </td><td><input type="text" name="Asset_number" value="1" onChange="javascript: numbercontrol();"></td></tr>
		</table>
		<!--In this part we have a fucntion to create an asset-->
		</td></tr><tr><td valign="bottom">
		<table align="center" border=0 cellspacing=0 cellpadding=2>
			<tr><td align="center" valign="bottom"><a href="javascript: createasset(); void(0);"><img class=button src="<%=contextPath%>/images/button_create.gif" border=0 alt="Create Assets"></a></td></tr>
		</table>
		</td></tr></table>
		<% } %>
      </td>
      <td rowspan="5" align="right" valign="top">
      	<%@ include file="/asset/asset_sidebar.jsp"%>
      </td>
	</tr>
	</table>
  </td>
</tr>
</table>

<br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/asset/asset_totals.jsp', 'DoNothing', 'Asset Information');
	<!--In this function we show the asset data according with the tagnumber value-->
	function viewassetdata(tagNumber) {
		frm.Asset_tagNumber.value = tagNumber;
		doSubmit('/asset/asset_general.jsp','AssetRetrieveById');
	}

<%  for(int i=0; i<assetStatusList.size(); i++) {
	  Object temp1[] = (Object[]) assetStatusList.get(i); %>

	 <!--In this function we can show the assetStatusList-->
	function view<%=temp1[0]%>() {
<%	  for(int j=0; j<assetStatusList.size(); j++) {
		Object temp2[] = (Object[]) assetStatusList.get(j);
		if (i==j) { %>
		displayArea('<%=temp2[0]%>');
<%		} else { %>
		hideArea('<%=temp2[0]%>');
<%		}
	  } %>
	}
<%	} %>
//-->
</script>