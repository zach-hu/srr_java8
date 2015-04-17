<%@ page import="com.tsa.puridiom.browse.*" %>
<%@ page import="com.tsagate.properties.DictionaryManager" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>
<%
			long start = System.currentTimeMillis();
			List list = (List) request.getAttribute("commodityList");
			int	irows = 0;
			
			if (list != null) {
				irows = list.size();
			}
			
			String	allowBrowse = (String) request.getAttribute("allowBrowse");
			if (allowBrowse == null) {
				allowBrowse = "true";
			}
			
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
			
			String	focusX = (String) request.getAttribute("focusX");
			String	focusY = (String) request.getAttribute("focusY");
			if (HiltonUtility.isEmpty(focusX)) { focusX = "0";	}
			if (HiltonUtility.isEmpty(focusY)) { focusY = "0";	}
%>
<tsa:hidden name="allowBrowse" value="<%=allowBrowse%>"/>
<tsa:hidden name="focusX" value="<%=focusX%>"/>
<tsa:hidden name="focusY" value="<%=focusY%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12>UNSPSC Commodity Browse</div></td></tr>
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

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr><td nowrap style="visibility: hidden; display: none;">&nbsp;&nbsp;<b>Showing <%=irows%>%>Records </b></td></tr>
</table>
<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=2px>&nbsp;</td>
	<td width=670px class=browseRows align=center valign=top>
		<div id="noRecords" style="visibility: hidden; display: none;">
		<table border=0 cellspacing=0 cellpadding=2 class=browseRow>
		<tr><td><b>There were no records found in the database matching your criteria.</b></td></tr>
		</table>
		</div>
		<div id="page1" style="visibility: visible; align:center;">
		<table id=browseRows border=0 cellspacing=0 cellpadding=1 class=browseRow>
		<tr>
			<td align=center>
<%
			String previousSegment = "";
			String previousFamily = "";
			String previousGroup = "";
			String previousSpecificCommodity = "";
			int	segments = 0;
			int	families = 0;
			int	groups = 0;
			int	specificCommodities = 0;
			
			for (int il = 0; il < irows; il++) {
				Commodity commodity = (Commodity) list.get(il);
				String	code = commodity.getCommodity();
				String	segment = code.substring(0,2);
				String	family = code.substring(2,4);
				String	group = code.substring(4,6);
				String	specificCommodity = code.substring(6,8);
				String img = "plus.gif";
				String	imgClass = "processOnImg";
				String jsFunction = "openGroup";

				if (!segment.equals(previousSegment)) {
					segments++;
					
					if ( (il+1) < irows) {
						Commodity nextCommodity = (Commodity) list.get(il + 1);
						String	nextSegment = nextCommodity.getCommodity().substring(0,2);
						if (nextSegment.equals(segment)) {
							img = "minus.gif";
							jsFunction = "closeSegment";
						}
					}

					if (!HiltonUtility.isEmpty(previousSpecificCommodity) && specificCommodities > 0) {
						specificCommodities--;%>
				</div>
			<%	}
					if (!HiltonUtility.isEmpty(previousGroup) && groups > 0) {
						groups--;%>
				</div>
			<%	}
					if (!HiltonUtility.isEmpty(previousFamily) && families > 0) {
						families--;%>
				</div>
			<%	}
					if (il > 0) {
						segments--;%>
				</div>
			<%	}%>
				<div name="segment<%=segment%>" style="visibility: visible; align:center;">
		<%	}
				if (!family.equals(previousFamily) && !family.equals("00")) {
					families++;
					
					if ( (il+1) < irows) {
						Commodity nextCommodity = (Commodity) list.get(il + 1);
						String	nextFamily = nextCommodity.getCommodity().substring(2,4);
						if (nextFamily.equals(family)) {
							img = "minus.gif";
							jsFunction = "closeFamily";
						}
					}
					
					if (!HiltonUtility.isEmpty(previousSpecificCommodity) && specificCommodities > 0) {
						specificCommodities--;%>
				</div>
			<%	}
					if (!HiltonUtility.isEmpty(previousGroup) && groups > 0) {
						groups--;%>
				</div>
			<%	}
					if (!HiltonUtility.isEmpty(previousFamily) && families > 1) {
						families--;%>
				</div>
			<%	} %>
				<div name="family<%=family%>" style="visibility: visible; align:center; width:90%;">
		<%	}
				if (!group.equals(previousGroup) && !group.equals("00")) {
					groups++;
					
					if ( (il+1) < irows) {
						Commodity nextCommodity = (Commodity) list.get(il + 1);
						String	nextGroup = nextCommodity.getCommodity().substring(4,6);
						if (nextGroup.equals(group)) {
							img = "minus.gif";
							jsFunction = "closeGroup";
						}
					}
					
					if (!HiltonUtility.isEmpty(previousSpecificCommodity) && specificCommodities > 0) {
						specificCommodities--;%>
				</div>
			<%	}
					if (!HiltonUtility.isEmpty(previousGroup) && groups > 1) {
						groups--;%>
				</div>
			<%	} %>
				<div name="group<%=group%>" style="visibility: visible; align:center; width:90%;">
		<%	}
				if (!specificCommodity.equals("00")) {
					specificCommodities++;
					img = "none.gif";
					imgClass = "";
					if (!HiltonUtility.isEmpty(previousSpecificCommodity) && specificCommodities > 1) {
						specificCommodities--;%>
				</div>
			<%	}%>
				<div name="commodity<%=specificCommodity%>" style="visibility: visible; align:center; width:90%;">
		<%	}%>
				<table border=0 cellpadding=2 cellspacing=0 class=browseRow>
				<tr>
					<td valign=top height=18px><a href="javascript: <%=jsFunction%>('<%=code%>'); void(0);" id="link<%=code%>" onclick="getWindowPosition();"><img src="<%=contextPath%>/images/<%=img%>" border=0 class="<%=imgClass%>" <% if (img.equals("none.gif")) {%>height=1px<%}%>></a></td>
					<td height=18px class=browseRow width=100% valign=top>
<%				if (allowBrowse.equalsIgnoreCase("true")) {%>
						<a href="javascript: returnMe('<%=code%>'); void(0);"  onclick="javascript: rowSelect='<%=il%>';" onMouseOver="highlightRow(<%=il%>);" onMouseOut="removeHighlight(<%=il%>);"><%=commodity.getDescription()%></a>
<%				} else {%>
						<%=commodity.getDescription()%>
<%				}%>
						<tsa:hidden name="Commodity_description" value="<%=commodity.getDescription()%>"/>
					</td>
				</tr>
				</table>
	<%		previousSegment = segment;
				previousFamily = family;
				previousGroup = group;
				previousSpecificCommodity = specificCommodity;
			}
			if (segments > 0) {%>
				</div>
	<%	}
			if (families > 0) {%>
				</div>
	<%	}
			if (groups > 0) {%>
				</div>
	<%	}
			if (specificCommodities > 0) {%>
				</div>
	<%	}%>
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
<%long end = System.currentTimeMillis();
//out.println("<b>Page took " + (end - start) +" Milliseconds to load.</b>");%>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var browser = browserCheck();
	var browseName = "${esapi:encodeForJavaScript(browseName)}";
	var totalRows = <%=irows%>;
	var filteredCommodities = new Array();
		
<%	for (int i=0; i < filteredCommodities.length; i++) {
			String code = filteredCommodities[i];
%>	filteredCommodities[<%=i%>] = "<%=code%>";
<%	}%>

	if (totalRows > 0) {
		window.scrollTo(frm.focusX.value, frm.focusY.value); 
	}

	function highlightRow(row) {
	}
	
	function removeHighlight(row) {
	}
	
	function openGroup(code) {
		var hiddenFields = "<input type=hidden name=\"Commodity_commodity\" value=\"" + code + "\">";
		
		for (var i=0; i < filteredCommodities.length; i++) {
			hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
		}
		
		setHiddenFields(hiddenFields);
		doSubmit('browse/browse_commodity_tree.jsp', 'CommodityRetrieveUNSPSCTree');
	}
	
	function closeSegment(code) {
		var hiddenFields = "";
		var segment = code.substring(0,2);

		for (var i=0; i < filteredCommodities.length; i++) {
			if ( !isEmpty(filteredCommodities[i]) && filteredCommodities[i].substring(0,2) != segment) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			}
		}
		setHiddenFields(hiddenFields);
		doSubmit('browse/browse_commodity_tree.jsp', 'CommodityRetrieveUNSPSCTree');
	}
	
	function closeFamily(code) {
		var hiddenFields = "";
		var	family = code.substring(2, 4);
		
		for (var i=0; i < filteredCommodities.length; i++) {
			if ( !isEmpty(filteredCommodities[i]) && filteredCommodities[i].substring(2,4) != family) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			}
		}
		setHiddenFields(hiddenFields);
		doSubmit('browse/browse_commodity_tree.jsp', 'CommodityRetrieveUNSPSCTree');
	}
	
	function closeGroup(code) {
		var hiddenFields = "";
		var group = code.substring(4, 6);

		for (var i=0; i < filteredCommodities.length; i++) {
			if ( !isEmpty(filteredCommodities[i]) && filteredCommodities[i].substring(4,6) != group) {
				hiddenFields = hiddenFields + "<input type=hidden name=\"Commodity_commodity\" value=\"" + filteredCommodities[i] + "\">";
			}
		}
		setHiddenFields(hiddenFields);
		doSubmit('browse/browse_commodity_tree.jsp', 'CommodityRetrieveUNSPSCTree');
	}
	
	function getWindowPosition() {
		frm.focusX.value = 0;
		frm.focusY.value = document.body.scrollTop;
	}
	
// End Hide script -->
</SCRIPT>