package com.tsa.puridiom.alerts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

import com.tsagate.foundation.utility.Utility;

public class AlertMessage
{
	private List lines = new ArrayList();

	public AlertMessage(List lineList)
	{
		for (Iterator iter = lineList.iterator(); iter.hasNext();)
		{
			Element	 lineElement = (Element) iter.next();
			MessageLine msgLine = new MessageLine();
			msgLine.setRepeat(lineElement.getAttributeValue("repeat"));
			msgLine.setLine(lineElement.getText());
			msgLine.setCommentId(lineElement.getAttributeValue("commentId"));
			msgLine.setSizeObject(lineElement.getAttributeValue("sizeObject"));

			lines.add(msgLine);
		}
	}

	public class MessageLine
	{
		private String line = "";
		private boolean repeat = false;
		private String commentId = "";
		private String sizeObject = "";

		public String getLine()
		{
			return line;
		}

		public void setLine(String line)
		{
			this.line = line;
		}

		public String getCommentId()
		{
			return Utility.ckNull(commentId);
		}

		public void setCommentId(String commentId)
		{
			this.commentId = commentId;
		}

		public boolean isComment()
		{
			if(this.getCommentId().length() > 0)
			{
				return true;
			}
			return false;

		}

		public boolean isRepeat()
		{
			return repeat;
		}

		public void setRepeat(String repeat)
		{
			if(!Utility.isEmpty(repeat))
			{
				this.repeat = repeat.equalsIgnoreCase("Y");
			}
		}

		public String toString() {
				StringBuffer buffer = new StringBuffer();
				buffer.append("[MessageLine:");
				buffer.append(" line: ");
				buffer.append(line);
				buffer.append(" repeat: ");
				buffer.append(repeat);
				buffer.append(" commentId: ");
				buffer.append(commentId);
				buffer.append("]");
				return buffer.toString();
			}

		public String getSizeObject() {
			return sizeObject;
		}

		public void setSizeObject(String sizeObject) {
			this.sizeObject = sizeObject;
		}

	}

	public List getLines() {
		return lines;
	}

	public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[AlertMessage:");
			buffer.append(" lines: ");
			buffer.append(lines);
			buffer.append("]");
			return buffer.toString();
		}
}
