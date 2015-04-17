package com.tsa.puridiom.encryptor;
/*
 * Created on Apr 9, 2003
 */

import java.util.Random;
import java.util.StringTokenizer;

/**
 * @author renzo
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Encryptor
{
	private StringBuffer key = new StringBuffer("{Gateway:Puridiom:TSAInc.}");
	private StringBuffer raw = new StringBuffer("");
	private StringBuffer encrypted = new StringBuffer("");
	private StringBuffer encoded = new StringBuffer("");

	private String vendorId = "";
	private String itemNumber = "";
	/**
	 * @return String
	 */
	public String getItemNumber()
	{
		return itemNumber;
	}

	/**
	 * @return String
	 */
	public String getVendorId()
	{
		return vendorId;
	}

	/**
	 * Sets the itemNumber.
	 * @param itemNumber The itemNumber to set
	 */
	public void setItemNumber(String itemNumber)
	{
		this.itemNumber = itemNumber;
	}

	/**
	 * Sets the vendorId.
	 * @param vendorId The vendorId to set
	 */
	public void setVendorId(String vendorId)
	{
		this.vendorId = vendorId;
	}


	public String of_encrypt(String thestr, int offset)
	{
		StringBuffer s_tempVal;
		char tStr;
		int keyPtr, keyLen, sourceLen, tempVal, tempKey;

		this.setRaw(thestr);

		keyPtr = offset;
		keyLen = this.key.length();
		sourceLen = this.raw.length();

		for(int sourcePtr = 0; sourcePtr < sourceLen; sourcePtr++)
		{
			tempVal = (int)this.raw.charAt(sourcePtr);

			tempKey = (int)this.key.charAt(keyPtr);

			tempVal += tempKey;

			// Added this section to ensure that ASCII Values stay within 0 to 255 range
			while (tempVal > 255)
			{
				if(tempVal > 255)
				{
					tempVal = tempVal - 255;
				}
			}
            //End of section

			tStr = (char)tempVal;

			this.encrypted = this.encrypted.append(tStr);

			s_tempVal = new StringBuffer(String.valueOf(tempVal));

			while(s_tempVal.length() < 3)
			{
				s_tempVal = s_tempVal.insert(0, 0);
			}

			this.encoded = encoded.append(s_tempVal);

			keyPtr++;

			if(keyPtr == key.length())
			{
				keyPtr = 0;
			}
		}

		//return encrypted;
		encoded = encoded.insert(0, offset);
		return encoded.toString();
	}

	public StringBuffer of_decrypt(StringBuffer thestr)
	{
		String temp = "";
		char tStr;
		char ch_holder;
		int keyPtr, keyLen, sourceLen, tempVal, tempKey;
		Integer i_holder;

		i_holder = Integer.valueOf((String.valueOf(thestr).substring(0,1)));

		keyPtr = i_holder.intValue();
		keyLen = this.key.length();

		//remove the first char
		thestr = new StringBuffer(thestr.substring(1));

		sourceLen = thestr.length();
		this.encrypted = thestr;
		this.raw.setLength(0);
		for(int sourcePtr = 0; sourcePtr < sourceLen; sourcePtr++)
		{
			tempVal = (int)this.encrypted.charAt(sourcePtr);
			tempKey = (int)this.key.charAt(keyPtr);
			tempVal -= tempKey;

			// Added this section to ensure that ASCII Values stay within 0 to 255 range
			while (tempVal < 0)
			{
				if(tempVal < 0)
				{
					tempVal = tempVal + 255;
				}
			}

			tStr = (char)tempVal;

			this.raw = this.raw.append(tStr);

			keyPtr++;

			if(keyPtr == key.length())
			{
				keyPtr = 0;
			}
		}

		return this.raw;
	}

	/**
	 * Method breaker
	 * <p>Will break any strings into an array of strings, using the tokenizer passed</p>
	 * @param theString
	 * @param separator
	 * @return String[]
	 */
	public String[] breaker(String theString, String separator)
	{
		StringTokenizer st = new StringTokenizer(theString, separator);
		String result[] = new String[st.countTokens()];
		int countTokens = 0;

		while (st.hasMoreTokens())
		{

			result[countTokens] = st.nextToken();
			countTokens++;
		}
		return result;
	}

	public String[] breaker(StringBuffer thestr)
	{
		//this array will hold the srn(0) and id(1)
		String[] result = new String[2];

		String s_theStr = "";

		s_theStr = String.valueOf(thestr);

		//hold the indexes of where the data starts
		int i_srn = s_theStr.indexOf("srn=");
		int i_id = s_theStr.indexOf("&id=");
		int length = s_theStr.length();

		result[0] = s_theStr.substring(i_srn + 4, i_id);
		this.setItemNumber(result[0]);
		result[1] = s_theStr.substring(i_id + 4, length);
		this.setVendorId(result[1]);
		return result;
	}
	/**
	 * @return StringBuffer
	 */
	public String getEncoded()
	{
		return encoded.toString();
	}

	/**
	 * @return StringBuffer
	 */
	public String getEncrypted()
	{
		return encrypted.toString();
	}

	/**
	 * @return StringBuffer
	 */
	public String getKey()
	{
		return key.toString();
	}

	/**
	 * @return StringBuffer
	 */
	public String getRaw()
	{
		return raw.toString();
	}

	/**
	 * Sets the encoded.
	 * @param encoded The encoded to set
	 */
	public void setEncoded(String encoded)
	{
		this.encoded.setLength(0);
		this.encoded.append(encoded);
	}

	/**
	 * Sets the encrypted.
	 * @param encrypted The encrypted to set
	 */
	public void setEncrypted(String encrypted)
	{
		this.encrypted.setLength(0);
		this.encrypted.append(encrypted);
	}

	/**
	 * Sets the key.
	 * @param key The key to set
	 */
	public void setKey(String key)
	{
		this.key.setLength(0);
		this.key.append(key);
	}

	/**
	 * Sets the raw.
	 * @param raw The raw to set
	 */
	public void setRaw(String raw)
	{
		this.raw.setLength(0);
		this.raw.append(raw);
	}

	/**
	 * Method hexToDec
	 * convert heaxdecimal 0-F into decimal.
	 * Converts integer from 1 - 16 into character hex representation
	 * @param asStr
	 * @return int
	 */
	private int hexToDec(char aChar)
	{
		int iHexChar;

		switch(aChar)
		{
			case 'A':
				iHexChar = 10;
				break;
			case 'B':
				iHexChar = 11;
				break;
			case 'C':
				iHexChar = 12;
				break;
			case 'D':
				iHexChar = 13;
				break;
			case 'E':
				iHexChar = 14;
				break;
			case 'F':
				iHexChar = 15;
				break;
			default:
				iHexChar = Integer.parseInt(String.valueOf(aChar));
				break;
		}
		return iHexChar;
	}

	/**
	 * Method hexToInt
	 * Recursive function to translate hex into number representation
	 * @param asStr
	 * @return int
	 */
	private int hexToInt(String asNumb)
	{
		if(asNumb.length() > 1)
		{
			int a = hexToInt(asNumb.substring(1));
			int b = hexToDec(asNumb.charAt(0));
			int c = (int)Math.pow(16, asNumb.length() -1) ;
			int d = a + (b * c);
			return d;
		}
		else
		{
			return hexToDec(asNumb.charAt(0));
		}
	}

	/**
	 * Method hexToChar
	 * Converts an input string into a hex character string
	 * @param asStr
	 * @return String
	 */
	private String hexToChar(String asStr)
	{
		StringBuffer sb = new StringBuffer();
		for(int li = 0; li < asStr.length(); li += 2)
		{
			String sHexChar = String.valueOf(hexToInt(asStr.substring(li, li + 2)));
			while (sHexChar.length()< 3) {
				sHexChar = "0" + sHexChar ;
			}
			sb.append(sHexChar);
		}
		return sb.toString();
	}

	public String ofDecode(String asStr)
	{
        this.setEncrypted("");
		String theStr = asStr.toUpperCase();
		//Fixed so that decryption is done on encrypted input string of proper length
		StringBuffer sbEncrypted = new StringBuffer();
		int iTemp = Integer.parseInt(asStr.substring(0, 1));
		sbEncrypted.append(iTemp);
		theStr = this.hexToChar(theStr.substring(1));
		int sourceLen = theStr.length();
		for(int sourcePtr = 0; sourcePtr < sourceLen; sourcePtr += 3)
		{
			int tempVal = Integer.valueOf(theStr.substring(sourcePtr, sourcePtr+3)).intValue();
			//Added this section to ensure that ASCII codes stay in 0 to 255 range
			while(tempVal > 255)
			{
				tempVal = tempVal - 255;
			}
			//end of section
			char tStr = (char)tempVal;

			sbEncrypted.append(tStr);
		}
		this.encrypted = sbEncrypted;
		return this.of_decrypt(sbEncrypted).toString();
		//return this.encrypted.toString();
	}

    public String of_set(String theStr)
    {
        Random offset = new Random();
        int offs = offset.nextInt(10);

        String set = this.of_set(theStr, offs);


        return set;
    }

    public String of_set(String thestr, int offset)
    {
        this.setEncoded("");
        StringBuffer s_tempVal;
        char tStr;
        int keyPtr, keyLen, sourceLen, tempVal, tempKey;
        this.setRaw(thestr);

        keyPtr = offset;
        keyLen = this.key.length();
        sourceLen = this.raw.length();

        for(int sourcePtr = 0; sourcePtr < sourceLen; sourcePtr++)
        {
            tempVal = (int)this.raw.charAt(sourcePtr);
            tempKey = (int)this.key.charAt(keyPtr);
            tempVal += tempKey;

            // Added this section to ensure that ASCII Values stay within 0 to 255 range
            while (tempVal > 255)
            {
                if(tempVal > 255)
                {
                    tempVal = tempVal - 255;
                }
            }
            //End of section

            tStr = (char)tempVal;
            this.encrypted = this.encrypted.append(tStr);
            String hexStr = Integer.toHexString((int)tStr) ;
            if(hexStr.length() == 1)
            {
                hexStr = "0"+ hexStr;
            }

            this.encoded.append(hexStr);

            keyPtr++;
            if(keyPtr == key.length())
            {
                keyPtr = 0;
            }
        }

        //return encrypted;
        encoded = encoded.insert(0, offset);
        return encoded.toString();

    }

    private String charToHex(String str)
    {
        String ret = "";
        //Converts an input string into a hex character string
        char chaArray[] = str.toCharArray();
        for (int i = 0; i < chaArray.length; i++)
        {
            String sHexChar = this.intToHex((int)chaArray[i]);
            if(sHexChar.length() == 1)
            {
                sHexChar = "0" + sHexChar;
            }
            ret = ret + sHexChar;
        }
        return ret;
    }

    private String intToHex(int aiNumber)
    {
//      Recursive function to translate number into hex representation
        if(aiNumber > 15)
        {
            return this.intToHex(aiNumber/16) + this.decToHex(aiNumber%16);
        }
        else
        {
            return this.decToHex(aiNumber);
        }
    }

    private String decToHex(int aiNumber)
    {
//      convert 0-15 decimal into hexadecimal.
        char sHexChar;
        switch(aiNumber)
        {
            case 10:
                sHexChar = 'A';
                break;
            case 11:
                sHexChar = 'B';
                break;
            case 12:
                sHexChar = 'C';
                break;
            case 13:
                sHexChar = 'D';
                break;
            case 14:
                sHexChar = 'E';
                break;
            case 15:
                sHexChar = 'F';
                break;
            default:
                sHexChar = (char)aiNumber;
                break;
        }
        return String.valueOf(sHexChar);
     }

    public static void main(String[] args)
    {
        Encryptor e = new Encryptor();
        String t = "link" ;

        String encrypted = e.of_set(t);
        System.out.println("encoded: " + encrypted);
        System.out.println("encrypted: " + e.getEncoded());
        System.out.println("decoded: " + e.ofDecode(encrypted) + "-");

    }
	public static void main1(String[] args)
	{
		Encryptor e = new Encryptor();
		String t = "101010101010;" ;
		System.out.println(t) ;
		while (t.endsWith(";")) {
			t = t.substring(0,t.length() - 1) ;
		}

		System.out.println(t);
		t = "0f0eeb99e9ecdecc3db9bc2d9dca9d8dcd0d49bc8b86facddd054e5e4aa9ea495a791be6b" ;
		System.out.println(e.ofDecode(t)) ;
		System.out.println(e.ofDecode("0eeedb59ea698a795a97076ded6a69499a4"));
	}
}
