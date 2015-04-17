package com.tsa.puridiom.emails.tasks;

import java.io.File;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class OfficeMaxParser extends Task
{
	private final String REGEX_PO_NUMBER = "\\d+";

	private final String REGEX_PO_RELEASE_NUMBER = "(/\\d+)";

	private final String REGEX_LINE_PREFIX = "(.)*(your)\\s+(reference)\\s*(:)\\s+";

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map) object;
		try
		{
			File inputFile = (File) incomingRequest.get("officeMaxEmail");

			String organizationId = (String) incomingRequest.get("organizationId");

			if (organizationId == null)
			{
				organizationId = "bsc04p";
			}

			List contents = this.getContentsStream(inputFile);

			PoHeader poHeader = this.getPoHeader((String) contents.get(0), (String) contents.get(1), organizationId);

			incomingRequest.put("poHeader", poHeader);

			ret = this.buildEmailMessages((StringBuffer) contents.get(2), (Map) contents.get(3), poHeader, organizationId);

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			e.printStackTrace();
			this.setStatus(Status.FAILED);
		}
		// TODO Auto-generated method stub
		return ret;
	}

	private String[] getPoNumber(String line)
	{
		String number[] = { "0", "0" };
		String tmpArray[] = line.split(":");
		String comp;

		for (int i = 0; i < tmpArray.length; i++)
		{
			comp = tmpArray[i].trim();

			if (comp.matches(REGEX_PO_NUMBER + REGEX_PO_RELEASE_NUMBER))
			{
				number = comp.split("/");
			} else if (comp.matches(REGEX_PO_NUMBER))
			{
				number[0] = comp;
			}
		}

		return number;
	}

	private String getRequisitionerEmail(String organizationId, String requisitioner)
	{
		String email;
		try
		{
			email = UserManager.getInstance().getUser(organizationId, requisitioner).getMailId();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			email = "";
			e.printStackTrace();
		}
		return email;
	}

	private List getContentsStream(File aFile) throws Exception
	{
		StreamTokenizer tokenizer = new StreamTokenizer(new FileReader(aFile));
		int type;

		List returnList = new ArrayList();
		Map contentsMap = new HashMap();
		StringBuffer sbHeader = new StringBuffer();
		StringBuffer sbBody = new StringBuffer();
		Pattern pattern = Pattern.compile(REGEX_LINE_PREFIX + REGEX_PO_NUMBER + REGEX_PO_RELEASE_NUMBER + "?", Pattern.CASE_INSENSITIVE);
		Matcher matcher;

		String[] poNumber;
		boolean hasKey = false;
		int counter = 0;
		String key = "";
		String value = "";
		String tmp = "";

		returnList.add("");
		returnList.add("");
		tokenizer.ordinaryChar(32);
		tokenizer.wordChars(32, 126);
		tokenizer.eolIsSignificant(true);

		while ((type = tokenizer.nextToken()) != StreamTokenizer.TT_EOF)
		{
			value = "";

			switch (type)
			{
				case StreamTokenizer.TT_EOL:

					value = (value.length() == 0) ? "\r\n" : value;

				case StreamTokenizer.TT_NUMBER:

					value = (value.length() == 0) ? String.valueOf(tokenizer.nval) : value;

				case StreamTokenizer.TT_WORD:

					value = (value.length() == 0) ? tokenizer.sval : value;

					tmp = (tokenizer.sval != null) ? tokenizer.sval.trim() : "";

					if (!tmp.matches("\\*{6,}"))
					{
						if (counter > 1)
						{
							if (tmp.matches(";\\d+") && !hasKey)
							{
								key = tmp.substring(1);
								hasKey = true;
							}
							sbBody.append(value);
						} else
						{
							matcher = pattern.matcher(tmp);

							if (matcher.matches())
							{
								poNumber = this.getPoNumber(tmp);
								returnList.set(0, poNumber[0]);
								returnList.set(1, poNumber[1]);
							}

							sbHeader.append(value);
						}

					} else
					{
						if (sbBody.length() > 0)
						{
							contentsMap.put(key, sbBody);
							hasKey = false;
						}

						sbBody = new StringBuffer();
						sbBody.append(value);
						counter++;
					}

					break;
			}
		}

		returnList.add(sbHeader);
		returnList.add(contentsMap);

		return returnList;
	}

	private PoHeader getPoHeader(String poNumber, String releaseNumber, String organizationId) throws Exception
	{
		PoHeader poHeader = null;
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("po-retrieve-by-number.xml");
		Map incomingRequest = new HashMap();

		if (!HiltonUtility.isEmpty(poNumber))
		{
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("PoHeader_poNumber", poNumber);
			incomingRequest.put("PoHeader_releaseNumber", releaseNumber);

			process.executeProcess(incomingRequest);

			poHeader = (PoHeader) incomingRequest.get("poHeader");
			
			poHeader.setPoLineList((List) incomingRequest.get("poLineList"));
		}
		

		return poHeader;
	}

	private Map buildEmailMessages(StringBuffer messageHeader, Map bodyMessages, PoHeader poHeader, String organizationId)
	{
		Map requisitionerEmails = new HashMap();
		List poLineList;
		PoLine poLine;
		String requisitionerEmail;
		StringBuffer messageBody;

		if (poHeader != null)
		{
			poLineList = poHeader.getPoLineList();
			
			for (Iterator iter = poLineList.iterator(); iter.hasNext();)
			{
				poLine = (PoLine) iter.next();

				if (bodyMessages.containsKey(poLine.getLineNumber().toString()))
				{
					requisitionerEmail = this.getRequisitionerEmail(organizationId, poLine.getRequisitionerCode());

					messageBody = (StringBuffer) bodyMessages.get(poLine.getLineNumber().toString());

					if (requisitionerEmails.containsKey(requisitionerEmail))
					{
						((StringBuffer) requisitionerEmails.get(requisitionerEmail)).append(messageBody);
					} else
					{
						messageBody.insert(0, messageHeader);
						requisitionerEmails.put(requisitionerEmail, messageBody);
					}
				}
			}
		}

		return requisitionerEmails;
	}

}
