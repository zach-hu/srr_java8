package com.tsa.puridiom.emails;

public class EmailsMisc
{
	private static EmailsMisc instance = null;
	private int found = 0;
	private int processed = 0;
	private String organizationId = "";

	public static EmailsMisc getInstance(String organizationId)
	{
		if(EmailsMisc.instance == null)
		{
			EmailsMisc.instance = new EmailsMisc(organizationId);
		}
		return EmailsMisc.instance;
	}

	private EmailsMisc(String organizationId)
	{
		this.setOrganizationId(organizationId);
	}
	private EmailsMisc()
	{
		this("test");
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public int getFound() {
		return found;
	}

	public void setFound(int found) {
		this.found =+ found;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed =+ processed;
	}

}
