<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsa.puridiom.organization.OrganizationManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>
<%@ page import="java.math.BigDecimal" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/filter.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	formField = (String) request.getAttribute("formField");
	String	fromPage = (String) request.getAttribute("fromPage");
	String	row = (String) request.getAttribute("index");
	if (fromPage == null)	{	fromPage = "";	}
	if (row == null)			{	row = "0";			}
	
	boolean allowSelectionAllCommodityLevel = propertiesManager.getProperty("MISC", "AllowSelectionLastCommodityLevelOnly", "N").equalsIgnoreCase("N");
	String	commodityType = HiltonUtility.ckNull(propertiesManager.getProperty("MISC", "CommodityType", "")).toUpperCase();
	String	CommoditylinkInSecondLevel = HiltonUtility.ckNull(propertiesManager.getProperty("BROWSE", "CommoditylinkInSecondLevel", "N")).toUpperCase();
	int categoryCount = 0;

	String defaultCategoryNames[] = {"Segment", "Family", "Group", "Commodity"};
	String categoryNames[];
	String	defaultCategory[];
	int	categoryStartPos[] = {0,2,4,6};
	int	categoryEndPos[] = {2,4,6,8};

	if (commodityType.equals("UNSPSC")) {
		categoryCount = 4;

		categoryNames = new String[categoryCount];
		categoryNames[0] = "Segment";
		categoryNames[1] = "Family";
		categoryNames[2] = "Group";
		categoryNames[3] = "Commodity";
	} else if (commodityType.equals("NIGP")) {
		categoryCount = 2;

		categoryNames = new String[categoryCount];
		categoryNames[0] = "Segment";
		categoryNames[1] = "Family";

		categoryStartPos[0] = 0;
		categoryStartPos[1] = 3;
		categoryEndPos[0] = 3;
		categoryEndPos[1] = 5;
	} else if (commodityType.equals("NIGP-11")) {
		categoryCount = 4;

		categoryNames = new String[categoryCount];
		categoryNames[0] = defaultCategoryNames[0];
		categoryNames[1] = defaultCategoryNames[1];
		categoryNames[2] = defaultCategoryNames[2];
		categoryNames[3] = defaultCategoryNames[3];

		categoryStartPos[0] = 0;
		categoryStartPos[1] = 3;
		categoryStartPos[2] = 5;
		categoryStartPos[3] = 7;
		categoryEndPos[0] = 3;
		categoryEndPos[1] = 5;
		categoryEndPos[2] = 7;
		categoryEndPos[3] = 11;

		commodityType = "NIGP";
	} else if (commodityType.equals("NIGP-6")) {
		categoryCount = 3;

		categoryNames = new String[categoryCount];
		categoryNames[0] = defaultCategoryNames[0];
		categoryNames[1] = defaultCategoryNames[1];
		categoryNames[2] = defaultCategoryNames[2];

		categoryStartPos[0] = 0;
		categoryStartPos[1] = 2;
		categoryStartPos[2] = 4;
		categoryEndPos[0] = 2;
		categoryEndPos[1] = 4;
		categoryEndPos[2] = 6;

		commodityType = "";
	} else {
		categoryCount = (new Integer(propertiesManager.getProperty("MISC", "CommoditySections", "0"))).intValue();

		categoryNames = new String[categoryCount];
		for (int i=0; i < categoryCount && i < defaultCategoryNames.length; i++) {
			categoryNames[i] = defaultCategoryNames[i];
		}

       for (int cnt = 0; cnt < categoryCount; cnt++ ) {
			String posTemp = propertiesManager.getProperty("MISC","CommodityLevel" + Integer.toString(cnt + 1) + "Pos","") ;

			// Remove Quotes
			posTemp = posTemp.replaceAll("\"","");

			if (!HiltonUtility.isEmpty(posTemp)) {
		        //Pick out the parameters
		        StringTokenizer st = new StringTokenizer(posTemp,",");
		        for (int ix = 1;st.hasMoreElements();ix++) {
		        	categoryStartPos[cnt] = Integer.valueOf(st.nextToken()).intValue();
		        	categoryEndPos[cnt] = Integer.valueOf(st.nextToken()).intValue();
		        	if (ix == 2) { break ; }
		        }
		    } else {
		    	categoryStartPos[cnt] = 0;
		    	categoryEndPos[cnt] = 0;
		    }
		}
		commodityType = OrganizationManager.getInstance().getOrganizationName(oid);
	}

	defaultCategory = new String[categoryCount];

	for (int icc=0; icc < categoryCount; icc++) {
		StringBuffer	sb = new StringBuffer("");
		int	categoryChars = categoryEndPos[icc] - categoryStartPos[icc];

		for (int ix = 0; ix < categoryChars; ix++) {
			sb.append("0");
		}
		defaultCategory[icc] = sb.toString();
	}
	List list = (List) request.getAttribute("commodityList");
	int	irows = 0;

	if (list != null) {
		irows = list.size();
	}
	

	
	
	String	allowBrowse = (String) request.getAttribute("allowBrowse");
	if (allowBrowse == null) {
		allowBrowse = "true";
	}

	String	keywords = HiltonUtility.ckNull((String) request.getAttribute("commodityKeywords"));
	String	commodityCode = HiltonUtility.ckNull((String) request.getAttribute("commodityCode"));

	Object obj = request.getAttribute("Commodity_commodity");
	String	filteredCommodities[];
	if (obj instanceof String) {
	    filteredCommodities = new String[1];
	    filteredCommodities[0] = (String) obj;
	} else if (obj instanceof String[]) {
	    filteredCommodities = (String[]) obj;
	} else {
	    filteredCommodities = new String[1];
	    filteredCommodities[0] = "";
	}

	String	selectedCode = HiltonUtility.ckNull((String) request.getAttribute("selectedCode"));
	String	scrollToX = HiltonUtility.ckNull((String) request.getAttribute("scrollToX"));
	String	scrollToY = HiltonUtility.ckNull((String) request.getAttribute("scrollToY"));
	
	int subcommodity=0;
	BigDecimal commodity1;
	BigDecimal commodity2;
	String subSelected;
	
	if (!HiltonUtility.isEmpty(selectedCode)) {
		if (selectedCode.length() >= 4) {
			subSelected = selectedCode.substring(0,4);	
		} else {
			subSelected = selectedCode;
		}
		if (HiltonUtility.isInteger(selectedCode)) {
			commodity2 = new BigDecimal(selectedCode);
		} else {
			commodity2 = new BigDecimal(0);
		}
	}else{
		commodity2= new BigDecimal(0);
		subSelected ="";
	}	
	if (commodity2.intValue() > 0){
		for (int il = 0; il < irows; il++) {
			Commodity commodity = (Commodity) list.get(il);
			String	code = commodity.getCommodity();
			commodity1 = new BigDecimal(code);
			if (subSelected.contentEquals(code.substring(0,4))){
				if(commodity1.intValue() > commodity2.intValue()){
					subcommodity++;
				}				
			}
		}	
	}	
%>
<tsa:hidden name="formField" value="<%=formField%>"/>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="selectedCode" value="<%=selectedCode%>"/>
<tsa:hidden name="scrollToX" value="<%=scrollToX%>"/>
<tsa:hidden name="scrollToY" value="<%=scrollToY%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=commodityType%> Commodity Browse</div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right>
		<div id="filterTextDisplay"></div>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=2px>&nbsp;</td>
	<td width=670px class=browseRows align=center valign=top>
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
		<div id="commodityFilterOptions" style="visibility: visible; align:center;">
		<table border=0 cellspacing=0 cellpadding=2 class=browseRow>
		<tr>
			<td align=right><b>Keyword(s)</b></td>
			<td colspan=2><input type=text name="commodityKeywords" value="<%=keywords%>" size=60>
			<!--td><input type=button name="request" value="Filter" onClick="filterMe();"></td-->
		</tr>
		<tr>
			<td align=right><b>Commodity Code</b></td>
			<td><input type=text name="commodityCode" value="<%=commodityCode%>" size=20>
			<td><input type=button name="request" value="Filter" onClick="filterMe();"></td>
		</tr>
		</table>
		<br>
		</div>
		<div id="page1" style="visibility: visible; align:center;width:680px">
		<table id=browseRows border=0 cellspacing=0 cellpadding=1 class=browseRow>
		<tr>
			<td align=center width=100%>
			
<%
			String previousCategories[] = new String[categoryCount];

			for (int il = 0; il < irows; il++) {
				Commodity commodity = (Commodity) list.get(il);
				StringBuffer	displayCode = new StringBuffer("");
				boolean	isLastLevelCodeForSelect = false;
				String	code = commodity.getCommodity();
				String categories[] = new String[categoryCount];

				for (int icc=0; icc < categoryCount; icc++) {
					if (code.length() >= categoryEndPos[icc]) {
						categories[icc] = code.substring(categoryStartPos[icc],categoryEndPos[icc]);
					} else {
						categories[icc] = code.substring(categoryStartPos[icc]);
					}
					if (!categories[icc].equals(defaultCategory[icc])) {
						displayCode.append(categories[icc]);
					}
				}
				String img = "plus.gif";
				String	imgClass = "processOnImg";
				String jsFunction = "openGroup";
				int	tabCount = 0;

				for (int ics = 0; ics < categoryCount; ics++) {
					if ((!categories[ics].equals(previousCategories[ics]) || ics == (categoryCount - 1)) && !categories[ics].equals(defaultCategory[ics])) {
						if (ics == (categoryCount - 1)) {
							img = "none.gif";
							imgClass = "";
							isLastLevelCodeForSelect = true;
						}
						else if ( (il+1) < irows) {
							Commodity nextCommodity = (Commodity) list.get(il + 1);
							String	nextCategory = nextCommodity.getCommodity().substring(categoryStartPos[ics],categoryEndPos[ics]);

							if (nextCategory.equals(categories[ics])) {
								img = "minus.gif";
								jsFunction = "close" + categoryNames[ics];
							}
						}
						if (il >= 0) {
							for (int ip=categoryCount - 1; ip >= 0; ip--) {%>
<%
								if (!HiltonUtility.isEmpty(previousCategories[ip]) && !categories[ip].equals(previousCategories[ip])) {
%>			</div> <!-- new category -->
<%							}
								if (categories[ip].equals(defaultCategory[ip])) {
									previousCategories[ip] = null;
								}
							}
						}%>
				<div name="<%=categoryNames[ics].toLowerCase()%><%=categories[ics]%>" style="visibility: visible; align:right;">
<%					if (ics > 0) {
							tabCount = ics;
						}
					}
				}
%>
				
				<table border=0 cellpadding=2 cellspacing=0 class=browseRow>
				<tr>
<%	for (int itc=0; itc < tabCount; itc++) {%>
					<td valign=top height=18px><img src="<%=contextPath%>/images/none.gif" border=0 width=11px height=1px></td>
<%	}%>
					<td valign=top height=18px><a href="javascript: <%=jsFunction%>('<%=code%>'); void(0);" id="link<%=code%>" onclick="setCurrentPosition();"><img src="<%=contextPath%>/images/<%=img%>" border=0 class="<%=imgClass%>" <% if (img.equals("none.gif")) {%>height=1px width=11px<%}%>></a></td>
					<td height=18px class=browseRow width=100% valign=top>
<%				if (allowBrowse.equalsIgnoreCase("true") && (isLastLevelCodeForSelect || allowSelectionAllCommodityLevel)) {%>
						<a href="javascript: returnMe('<%=code%>'); void(0);"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);"><% if(!oid.equalsIgnoreCase("BLY07P")) { %><%=displayCode.toString()%> - <% } %><%=commodity.getDescription()%></a>
<%				} else {%>
						<%=displayCode.toString()%> - <%=commodity.getDescription()%>
<%				}%>
						<tsa:hidden name="asCommodity_description" value="<%=commodity.getDescription()%>"/>
						<tsa:hidden name="asCommodity_type" value="<%=commodity.getCommodityType()%>"/>
					</td>
				</tr>
				</table>

<% 
   if (CommoditylinkInSecondLevel.equals("Y")) {
	if (tabCount == 1 && selectedCode.equals(code) && subcommodity == 0){	 %>
				<table border=0 cellpadding=2 cellspacing=0 class=browseRow>
				<tr>
<%	for (int itc=0; itc < tabCount + 1; itc++) {%>
					<td valign=top height=18px><img src="<%=contextPath%>/images/none.gif" border=0 width=11px height=1px></td>
<%	}%>
					<td valign=top height=18px>&nbsp;&nbsp;&nbsp;</td>
					<td height=18px class=browseRow width=100% valign=top>
						<a href="javascript: returnMe('<%=code%>'); void(0);"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);"><% if(!oid.equalsIgnoreCase("BLY07P")) { %><%=displayCode.toString()%> - <% } %><%=commodity.getDescription()%></a>
						<tsa:hidden name="asCommodity_description" value="<%=commodity.getDescription()%>"/>
						<tsa:hidden name="asCommodity_type" value="<%=commodity.getCommodityType()%>"/>
					</td>
				</tr>
				</table>
<%	}
   } %>				
				
<%
				for (int ic = 0; ic < categoryCount; ic++) {
					if (!categories[ic].equals(defaultCategory[ic])) {
						previousCategories[ic] = categories[ic];
						tabCount--;
					}
				}
			}%>
			</td>
		</tr>
		</table>
		</div>
	</td>
</tr>
<tr>
	<td id="originalFilterFields"></td>
	<td id="currentFilterFields"></td>
</tr>
</table>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var browsePage = frm.browsePage.value;
	var totalRows = <%=irows%>;
	var filteredCommodities = new Array();
	var selectedCode = "<%=selectedCode%>";

<%	for (int i=0; i < filteredCommodities.length; i++) {
			String code = filteredCommodities[i];
%>	filteredCommodities[<%=i%>] = "<%=code%>";
<%	}%>

	scrollToLastSelected();

	function scrollToLastSelected() {
		var x = frm.scrollToX.value;
		var y = frm.scrollToY.value;

		if (!isEmpty(x) && !isEmpty(y)) {
			window.scroll(x,y);
		} else {
			if (totalRows > 0) {
				var focusLink = document.getElementById("link" + selectedCode);
				if (focusLink != undefined) {
					focusLink.focus();
					window.scrollBy(0, 100);
				}
			}
		}
	}

	function highlightRow(row) {
	}

	function removeHighlight(row) {
	}

	function openGroup(code) {
		
		var hiddenFields = "<input type=hidden name=\"Commodity_commodity\" value=\"" + code + "\">";

		
		for (var i=0; i < filteredCommodities.length; i++) {
			hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			//alert(hiddenFields);			
		}

		frm.selectedCode.value = code;

		setHiddenFields(hiddenFields);
		doSubmit(browsePage, 'CommodityRetrieveTree');
	}

	function closeSegment(code) {
		var hiddenFields = "";
		var	segment = code.substring(<%=categoryStartPos[0]%>,<%=categoryEndPos[0]%>);

		for (var i=0; i < filteredCommodities.length; i++) {
			if ( !isEmpty(filteredCommodities[i]) && filteredCommodities[i].substring(<%=categoryStartPos[0]%>,<%=categoryEndPos[0]%>) != segment) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			}
		}
		setHiddenFields(hiddenFields);
		doSubmit(browsePage, 'CommodityRetrieveTree');
	}

	function closeFamily(code) {
		var hiddenFields = "";
		var	family = code.substring(<%=categoryStartPos[0]%>,<%=categoryEndPos[1]%>);

		for (var i=0; i < filteredCommodities.length; i++) {
			if ( !isEmpty(filteredCommodities[i]) && filteredCommodities[i].substring(<%=categoryStartPos[0]%>,<%=categoryEndPos[1]%>) != family) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			}
		}
		setHiddenFields(hiddenFields);
		doSubmit(browsePage, 'CommodityRetrieveTree');
	}

	function closeGroup(code) {
		var hiddenFields = "";
		var group = code.substring(<%=categoryStartPos[0]%>,<%=categoryEndPos[2]%>);

		for (var i=0; i < filteredCommodities.length; i++) {
			if ( !isEmpty(filteredCommodities[i]) && filteredCommodities[i].substring(<%=categoryStartPos[0]%>,<%=categoryEndPos[2]%>) != group) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			}
		}
		setHiddenFields(hiddenFields);
		doSubmit(browsePage, 'CommodityRetrieveTree');
	}

	function validateForm() {
//		var keywords = frm.commodityKeywords.value;
//		if (!isEmpty(keywords)) {
//			var hiddenFields = "<input type=hidden name=\"Commodity_description\" value=\"" + keywords + "\">";
//
//			setHiddenFields(getHiddenFields() + hiddenFields);
//		}
		return true;
	}

	function resetMe() {
		frm.commodityKeywords.value = "";

		doSubmit(browsePage, 'CommodityRetrieveTree');
	}

	function setCurrentPosition() {
		var xPos = document.body.scrollLeft;
		var yPos = document.body.scrollTop;

		if (xPos < 0) {
			xPos = 0;
		}
		if (yPos < 0) {
			yPos = 0;
		}
		frm.scrollToX.value = xPos;
		frm.scrollToY.value = yPos;
	}
	


// End Hide script -->
</SCRIPT>