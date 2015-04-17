<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
	<td width=100% valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=2 width=100%>
	<%	if (user.getSecurityQuestion().trim().length() > 0) {%>
		<tr>
			<td align=right width=50%><b>Current Security Question:</td>
			<td width=50%><%=user.getSecurityQuestion()%></td>
		</tr>
		<tr>
			<td align=right><b>Current Security Question Answer:</td>
			<td><input type=text name="securityAnswer" value="" size=25 maxLength=40 tabindex=4 onChange="upperCase(this)"></td>
		</tr>
		<tr><td colspan=2><br></td></tr>
	<%	}%>
		<tr>
			<td align=right width=50%><b>New Security Question:</td>
			<td width=50%>
				<select name="newSecurityQuestion" tabindex=5>
					<option value="" selected>-- Please select your security question. </option>
					<option value="What year were you born?">What year were you born?</option>
					<option value="Where were you born?">Where were you born?</option>
					<option value="What is your favorite color?">What is your favorite color?</option>
					<option value="What is your mother's maiden name?">What is your mother's maiden name?</option>
					<option value="What is your pet's name?">What is your pet's name?</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align=right><b>New Security Question Answer:</td>
			<td><input type=text name="newSecurityAnswer" value="" size=25 maxLength=40 tabindex=6 onChange="upperCase(this)"></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>