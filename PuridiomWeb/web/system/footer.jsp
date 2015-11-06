<%
	String date = "-datetime-";
%>
<br>
<br>

<table border="0" cellspacing="0" cellpadding="0" width=<%=formWidth%> vAlign="bottom" id="copyright">
<tr>
	<td width="18%">
	<table border=0 cellpadding=2 cellspacing=2 width="100%">
		<tr>
			<td>
				<hr size="0" color="black" align="left">
			</td>

		</tr>
	</table>
	</td>
	<td width="82%" colspan=2>
		<hr size="0" color="black" align="left">
	</td>
</tr>
<tr>
	<td class="copyright" width="18%" align="left" nowrap>&nbsp;Copyright &copy; <%=HiltonUtility.getFormattedDate(d_today, oid, "yyyy")%>&nbsp;&nbsp;<a href="<%=PropertiesManager.getInstance(oid).getProperty("MISC", "PRODUCTURL", "http://www.puridiom.com") %>" target="_blank"><%=PropertiesManager.getInstance(oid).getProperty("MISC", "PRODUCTURL", "http://www.puridiom.com").substring(11) %></a>
		<br>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "release", "Release")%> 4.0.20151022-jdk7
	</td>
	<td width="59%" align="right" style="font-size: 9px;"><%=PropertiesManager.getInstance(oid).getProperty("MISC", "FOOTERTEXT", "")%></td>
	<td class="copyright" width="23%" align="right">
		<table border=0 cellpadding=2 cellspacing=2>
		<tr>
			<td class="copyright"><a href="<%=PropertiesManager.getInstance(oid).getProperty("MISC", "CONTACTURL", "http://www.puridiom.com/contact/index.asp") %>" target="_blank">Contact Us</a></td>
			<td class="copyright"><a href="<%=PropertiesManager.getInstance(oid).getProperty("MISC", "PRIVACYURL", "http://www.puridiom.com/privacy.asp") %>" target="_blank">Privacy Policy</a></td>
			<td class="copyright"><a href="<%=PropertiesManager.getInstance(oid).getProperty("MISC", "PRODUCTURL", "http://www.puridiom.com") %>/terms.asp" target="_blank">Terms of Use</a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<!--div id="footer" style="width:<%=formWidth%>">
	<div class="footer_copyright">
		<a href="http://www.puridiom.com" target="_blank">&nbsp;Copyright &copy; <%=HiltonUtility.getFormattedDate(d_today, oid, "yyyy")%>&nbsp;Puridiom4.com</a>
	</div>
	<div class="footer_links">
		<a href="http://www.puridiom.com/contact/index.asp" target="_blank">Contact Us</a>
		<a href="http://www.puridiom.com/privacy.asp" target="_blank">Privacy Policy</a>
		<a href="http://www.puridiom.com/terms.asp" target="_blank">Terms of Use</a>
	</div>
</div-->
</div><!--  end pageForm div  -->

</form>
</body>
</html>
