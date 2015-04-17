package com.tsa.puridiom.emails;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;

/**
 *
 * @author Dj
 */
public class RenderableMessage
{
    private String subject;
    private StringBuffer bodytext;

    /** Creates a new instance of RenderableMessage */
    public RenderableMessage(Message m) throws MessagingException,IOException
    {
    	this(m, null);
    }
    
    /** Creates a new instance of RenderableMessage */
    public RenderableMessage(Message m, String contentType) throws MessagingException,IOException
    {
        subject=m.getSubject().substring("MailPage:".length());
        this.bodytext = new StringBuffer();
        this.handleMessage(m, contentType);
    }
    /**
     * @param content
     * @param from
     * @param subject
     * @param organizationId
     */
    private void handleStringContent(String content)
      {
    	Log.debug(this, "handleStringContent");
    	  try
    	  {
    		  this.bodytext.append(content);
    		  Log.debug(this, content);
    	  }
    	  catch (Exception e)
    	  {
    		// TODO: handle exception
    	  }
      }

    /**
     * @param multipart
     */
    private void handleContent(Multipart multipart, String contentType)
	{
		Log.debug(this, "handleContent");

		try
		{
			// String contentType=multipart.getContentType();
			for (int j = 0; j < multipart.getCount(); j++)
			{
				Part part = multipart.getBodyPart(j);
				Log.debug(this, String.valueOf(part.getLineCount()));
				Log.debug(this, String.valueOf(part.getContent()));
				Log.debug(this, String.valueOf(part.getContentType()));

				if (HiltonUtility.isEmpty(contentType) || part.getContentType().indexOf(contentType) >= 0)
				{
					String disposition = part.getDisposition();
					Log.debug(this, "handleContent-disposition: " + disposition);
					// if (disposition != null)
					// {
					InputStream inputStream = part.getInputStream();
					byte[] buffer = new byte[inputStream.available()];
					int bytesRead;
					while ((bytesRead = inputStream.read(buffer)) > -1) // Read
					// bytes
					// until
					// EOF
					{
						Log.debug(this, "reading contents");
					}

					String tmp = new String(buffer);
					this.bodytext.append(tmp);
					this.bodytext.append("\r\n");
					Log.debug(this, "handleContent: " + tmp);

					if (inputStream != null)
					{
						try
						{
							inputStream.close();
						} catch (IOException io)
						{
							Log.error(this, " error Closing InputStream" + io.getMessage());
							io.printStackTrace();
						}
					}
				}
			}
			// }
		} catch (Exception e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		}
	}

    public void handleMessage(Message message, String contentType) throws IOException, MessagingException
    {
    	Log.debug(this, "handleMessage");
    	Object objContent = message.getContent();
		if (objContent instanceof Multipart)
        {
			Log.debug(this, "handleMessage-multipart");
      	  	Multipart multipart = (Multipart)message.getContent();
            this.handleContent(multipart, contentType);
        }
        else
        {
        	Log.debug(this, "handleMessage-string");
      	  this.handleStringContent((String)objContent);
        }
    }

    public String getSubject() {
        return subject;
    }

    public StringBuffer getBodytext() {
        return bodytext;
    }
}

