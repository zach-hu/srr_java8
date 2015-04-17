<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility" %>
<%
	List timeZoneList = (List) request.getAttribute("timeZoneList");

    // Get all time zone ids
    String[] zoneIds = TimeZone.getAvailableIDs();
%>

<tsa:hidden name="StdTable_tableType" value="TMZN"/>
<tsa:hidden name="tableType" value="TMZN"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZNs", "Time Zones")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
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

<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td valign=bottom align="right" nowrap width=55px><input type=checkbox name="selectAll" onclick="flagAll(this.checked);">&nbsp;</td>
	<td valign=middle nowrap class="label" id="flagAllLabel"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-activateall", "Activate All")%></td>
</tr>
</table>
<table id=browseTable border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=5px>&nbsp;</td>
	<td width=670px class=browseHdrDk align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=670px>
		<tr>
			<td class="browseHdrDk" nowrap="" height="18" width=67px align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-activate", "Activate")%></td>
			<td class="browseHdrDk" nowrap="" height="18" width=215px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN", "Time Zone")%></td>
			<td class="browseHdrDk" nowrap="" height="18" width=80px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-abbreviation", "Abbreviation")%></td>
			<td class="browseHdrDk" nowrap="" height="18" width=220px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-fullname", "Full Name")%></td>
			<td class="browseHdrDk" nowrap="" height="18" width=88px><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-utcoffset", "UTC Offset")%></td>
		</tr>
		</table>
		<div id="browseBorder" class=browseHdrDk style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 675px; align:center; overflow-y:visible; overflow-x:auto;">
		<table id=browseRows border=0 cellspacing=0 cellpadding=1 width=665px class=browseRow>
<%
	String rowClass = "browseRow";

	Arrays.sort(zoneIds);

    // Loop through available Time Zones
    for (int i=0; i<zoneIds.length; i++) {
	 	String id = (String)  zoneIds[i];
        // Get time zone by time zone id
        TimeZone tz = TimeZone.getTimeZone(zoneIds[i]);

        // Get the display name
        String shortName = tz.getDisplayName(tz.inDaylightTime(d_today), TimeZone.SHORT);
        if (shortName.length() > 3 && shortName.indexOf("GMT") == 0) {
        	continue;
        }

        String longName = tz.getDisplayName(tz.inDaylightTime(d_today), TimeZone.LONG);

        // Get the number of hours from GMT
        int rawOffset = tz.getRawOffset();
        int hour = rawOffset / (60*60*1000);
        int min = Math.abs(rawOffset / (60*1000)) % 60;
%>
				<tr class="<%=rowClass%>">
					<td class="browseRow" width=65px align=center><input type=checkbox name="c_checkbox" value="Y" <% if (timeZoneList.contains(id)) {%>checked<%}%>></td>
					<td class="browseRow" width=215px nowrap><%=id%><tsa:hidden name="timeZoneId" value="<%=id%>"/></td>
					<td class="browseRow" width=80px nowrap><%=shortName%><tsa:hidden name="udf1Code" value="<%=shortName%>"/></td>
					<td class="browseRow" width=220px nowrap><%=longName%><tsa:hidden name="description" value="<%=longName%>"/></td>
					<td class="browseRow" width=85px nowrap><%=Dates.getUTCTimeZone(id)%><tsa:hidden name="udf2Code" value="<%=Dates.getUTCTimeZone(id)%>"/></td>
				</tr>
<%
			if (rowClass.equals("browseRow"))
			{
				rowClass = "summary";
			} else {
				rowClass = "browseRow";
			}
		}
%>
		</table>
		</div>
	</td>
	<td width=5px>&nbsp;</td>
</tr>
</table>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=50%><a href="javascript: doSubmit('/admin/systemtables/system_tables.jsp', 'StdTableUpdateByType'); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border=0 class=button alt="Save Active Time Zones"></a></td>
	<td align=center width=50%><a href="javascript: doSubmit('/admin/systemtables/system_tables.jsp', 'DoNothing'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to System Tables Menu"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function validateForm() {
	  	var handlers = frm.handler.value;
	  	if (handlers.indexOf('StdTableUpdateByType') >= 0) {
			var hiddenFields = "";
			var chkboxElements = frm.elements.item("c_checkbox");

			if (chkboxElements.length > 1) {
				for (var i=0; i < chkboxElements.length; i++) {
					var activate = chkboxElements(i).checked;

					if (activate) {
						var tzid = frm.elements.item("timeZoneId")(i).value;
						var description = frm.elements.item("description")(i).value;
						var udf1Code = frm.elements.item("udf1Code")(i).value;
						var udf2Code = frm.elements.item("udf2Code")(i).value;

						hiddenFields = hiddenFields + "<tsa:hidden name=\"StdTable_tableKey\" value=\"" + tzid + "\"/>";
						hiddenFields = hiddenFields + "<tsa:hidden name=\"StdTable_description\" value=\"" + description + "\"/>";
						hiddenFields = hiddenFields + "<tsa:hidden name=\"StdTable_udf1Code\" value=\"" + udf1Code + "\"/>";
						hiddenFields = hiddenFields + "<tsa:hidden name=\"StdTable_udf2Code\" value=\"" + udf2Code + "\"/>";
					}
				}
			}
			setHiddenFields(hiddenFields);
		}
		return true;
	}

	function flagAll(checkAll) {
		var checkboxes = document.all("c_checkbox");

		if (checkboxes != null) {
			if (checkboxes.length > 1) {
				for (i = 0; i < checkboxes.length; i++) {
					var cbox = checkboxes[i];
					cbox.checked = checkAll;
				}
			} else {
				var cbox = checkboxes;
				cbox.checked = checkAll;
			}
		}
		if (checkAll) {
			setInnerText("flagAllLabel","<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-deactivateall", "Deactivate All")%>");
		} else {
			setInnerText("flagAllLabel","<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "TMZN-activateall", "Activate All")%>");
		}
	}

// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>