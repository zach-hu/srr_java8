/*
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsagate.foundation.utility;

/**
 * @author JEFF
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

import java.security.*;

public class HashGenerator {

	private String	hashString = "" ;
	private String  hashType = "SHA1" ;

	public HashGenerator()
	{
	}

	public HashGenerator(String hashType) {

		this.hashType = hashType ;
	}

	public String getHash(String hashString) {

        return this.getHash(this.hashType, hashString);
	}

	public String getHash(String hashType, String hashString) {
        StringBuffer hashBuff = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance(hashType);
            md.reset() ;
            byte[] hBytes = hashString.getBytes();
            md.update(hBytes);
            byte[] dBytes = md.digest() ;
            for (int ix = 0; ix < md.getDigestLength(); ix++) {
            	String bt = Integer.toHexString(dBytes[ix] & 0xff);
            	if (bt.length()< 2) {
            		hashBuff.append("0") ;
            	}
                hashBuff.append(bt);
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashBuff.toString();
	}

}
