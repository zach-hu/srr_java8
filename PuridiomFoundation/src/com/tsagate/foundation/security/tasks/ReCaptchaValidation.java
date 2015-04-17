package com.tsagate.foundation.security.tasks;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

import java.util.Map;

/**
 * Creation date: August 2009
 * @author: Kelli Knisely
 */
public class ReCaptchaValidation extends Task
{
	/* (non-Javadoc)
	 * @see com.puridiom.hilton.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Boolean result = Boolean.FALSE;

        this.status = Status.READY;

		try
		{
            String ipAddress = (String) incomingRequest.get("ipAddress");
            String challengeValue = (String) incomingRequest.get("recaptcha_challenge_field");
            String responseValue = (String) incomingRequest.get("recaptcha_response_field");

            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();

            String pk = DictionaryManager.getInstance("host", "").getProperty("rpk", "");
            reCaptcha.setPrivateKey(pk);

            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(ipAddress, challengeValue, responseValue);

            if (!reCaptchaResponse.isValid()) {
                // Invalid - Please try again."
                incomingRequest.put("errorMsg", "The security code you entered did not match.  Please try again.");
                result = Boolean.FALSE;
                this.status = Status.FAILED;
            }
            else {
                // Else validation succeeds.
                result = Boolean.TRUE;
                this.status = Status.SUCCEEDED;
            }
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}