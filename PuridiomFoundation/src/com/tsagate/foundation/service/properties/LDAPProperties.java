package com.tsagate.foundation.service.properties;

import java.util.*;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.tsagate.properties.DictionaryManager;
import com.tsagate.foundation.utility.Log;

/**
 * Insert the type's description here.
 * Creation date: (8/19/2003 10:53:36 AM)
 * @author: Kelli Knisely
 */
public class LDAPProperties
{
    private static LDAPProperties INSTANCE = null;
    private String LDAPMode = null;
    private String LDAPHost = null;
    private String LDAPAlternateHost=null;
    private String LDAPSearchbase = null;
    private int LDAPPort;
    private    String    LDAPConnectUser=null ;
    private    String    LDAPConnectPW=null ;
    private String  LDAPLoginAttr=null ;
    private String    LDAPOrganization=null ;
    private Map         LDAPColumnMap= new HashMap() ;
    private Map         LDAPGroupMap= new HashMap() ;
    private Map         LDAPRoleMap= new HashMap() ;
    private String    LDAPAuthOnly=null ;
    private String    LDAPUserUpdate="N" ;
    private String    LDAPTimeOut=null ;
    private int        LDAPConnectTimeOut=0;
    private String     LDAPProxyUrl=null ;
    private String     LDAPVersion="2" ;
    private String     LDAPAutoReferrals="N" ;
    private String     LDAPMaxHops="5" ;
    private String    LDAPLogging="N" ;
    private String  LDAPAuthenticate="Y" ;
    private String    LDAPSelfRegistration="N" ;

    private String  LDAPProtocal="" ;
    private String  LDAPAuthentication="simple" ;
    private String  LDAPUrl="" ;
    private String  LDAPKeystore="" ;
    private String  LDAPFilter="" ;

    private String    SSOType="0" ;     // (Standard UserInfo)
    private String    SSOCertificate = "" ;
    private String    SSOSecret="" ;
    private String     SSOSecurityAccess="N" ;
    private String     SSODefaultAccess="NEWUSER" ;
    private String    SSODefaultApprover="N" ;
    private String    SSOHashType="MD5" ;
    private String  SSOIdpUrl="" ;

    private boolean useBlackBox = false;

    /**
     * LDAPProperties constructor comment.
     */
    private LDAPProperties()
    {
        super();
        try
        {
            PropertiesFileManager pfm = PropertiesFileManager.getInstance();
            String path = DictionaryManager.getInstance("host", "").getProperty("properties-path", "");
            pfm.setPathToPropertiesFile(path);
            Properties properties = pfm.getProperties("ldap.properties");
            if(properties != null)
            {
                Enumeration en = properties.keys() ;
                while (en.hasMoreElements()) {
                    String key = (String) en.nextElement() ;
                    if (key.equalsIgnoreCase("ldaphost")) {
                        this.LDAPHost = properties.getProperty("ldaphost","localhost");
                    } else if (key.equalsIgnoreCase("ldapmode")) {
                       this.LDAPMode = properties.getProperty("ldapmode");
                    } else if (key.equalsIgnoreCase("ldapalternatehost")) {
                        this.LDAPAlternateHost = properties.getProperty("ldapalternatehost");
                    } else if (key.equalsIgnoreCase("ldaptimeout")) {
                        this.LDAPTimeOut = properties.getProperty("ldaptimeout");
                        if (this.LDAPTimeOut == null) this.LDAPTimeOut = "0" ;
                        this.LDAPConnectTimeOut = Integer.parseInt(this.LDAPTimeOut) ;
                    } else if (key.equalsIgnoreCase("ldapauthonly")) {
                        this.LDAPAuthOnly = properties.getProperty("ldapauthonly");
                    } else if (key.equalsIgnoreCase("ldapsearchbase")) {
                        this.LDAPSearchbase = properties.getProperty("ldapsearchbase");
                    } else if (key.equalsIgnoreCase("ldapport")) {
                        this.LDAPPort = Integer.parseInt(properties.getProperty("ldapport","389"));
                    } else if (key.equalsIgnoreCase("ldapconnectuser")) {
                        this.LDAPConnectUser = properties.getProperty("ldapconnectuser");
                    } else if (key.equalsIgnoreCase("ldapconnectpw")) {
                        this.LDAPConnectPW = properties.getProperty("ldapconnectpw", "");

                        if (LDAPConnectPW.substring(0, 4).equalsIgnoreCase("enc(") && LDAPConnectPW.substring(LDAPConnectPW.length() - 1).equals(")")) {
                            String ciphertext = LDAPConnectPW.substring(4, LDAPConnectPW.length() - 1);

                            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
                            encryptor.setPassword("tsapuridiom");

                            LDAPConnectPW = encryptor.decrypt(ciphertext);
                            useBlackBox = false;
                        }
                    } else if (key.equalsIgnoreCase("ldaploginattr")) {
                        this.LDAPLoginAttr = properties.getProperty("ldaploginattr");
                    } else if (key.equalsIgnoreCase("ldaporganization")) {
                        this.LDAPOrganization = properties.getProperty("ldaporganization");
                    } else if (key.equalsIgnoreCase("ldapuserupdate")) {
                        this.LDAPUserUpdate = properties.getProperty("ldapuserupdate") ;
                    } else if (key.equalsIgnoreCase("ldapauthenticate")) {
                        this.LDAPAuthenticate = properties.getProperty("ldapauthenticate") ;
                    } else if (key.equalsIgnoreCase("ldapproxyurl")) {
                        this.LDAPProxyUrl = properties.getProperty("ldapproxyurl");
                    } else if (key.equalsIgnoreCase("ldapversion")) {
                        this.LDAPVersion = properties.getProperty("ldapversion");
                    } else if (key.equalsIgnoreCase("ldaplogging")) {
                        this.LDAPLogging = properties.getProperty("ldaplogging");
                    } else if (key.equalsIgnoreCase("ldapautoreferrals")) {
                        this.LDAPAutoReferrals = properties.getProperty("ldapautoreferrals");
                    } else if (key.equalsIgnoreCase("ldapmaxhops")) {
                        this.LDAPMaxHops = properties.getProperty("ldapmaxhops");
                    } else if (key.equalsIgnoreCase("ldaplastmodifieddate")) {
                        // Do nothing
                    } else if (key.equalsIgnoreCase("ldapselfregistration")) {
                        this.LDAPSelfRegistration = properties.getProperty("ldapselfregistration") ;
                    } else if (key.equalsIgnoreCase("ssocertificate")) {
                        this.SSOCertificate = properties.getProperty("ssocertificate") ;
                    } else if (key.equalsIgnoreCase("ssotype")) {
                        this.SSOType = properties.getProperty("ssotype") ;
                    } else if (key.equalsIgnoreCase("ssosecret")) {
                        this.SSOSecret = properties.getProperty("ssosecret") ;
                    } else if (key.equalsIgnoreCase("ssoidpurl")) {
                        this.SSOIdpUrl = properties.getProperty("ssoidpurl") ;
                    } else if (key.equalsIgnoreCase("ssohashtype")) {
                        this.SSOHashType = properties.getProperty("ssohashtype") ;
                    } else if (key.equalsIgnoreCase("ssosecurityaccess")) {
                        this.SSOSecurityAccess = properties.getProperty("ssosecurityaccess") ;
                    } else if (key.equalsIgnoreCase("ssodefaultaccess")) {
                        this.SSODefaultAccess = properties.getProperty("ssodefaultaccess") ;
                    } else if (key.equalsIgnoreCase("ssodefaultapprover")) {
                        this.SSODefaultApprover = properties.getProperty("ssodefaultapprover") ;
                    } else if (key.equalsIgnoreCase("ldapprotocal")) {
                        this.LDAPProtocal = properties.getProperty("ldapprotocal");
                    } else if (key.equalsIgnoreCase("ldapauthentication")) {
                        this.LDAPAuthentication = properties.getProperty("ldapauthentication");
                    } else if (key.equalsIgnoreCase("ldapkeystore")) {
                        this.LDAPKeystore = properties.getProperty("ldapkeystore");
                    } else if (key.equalsIgnoreCase("ldapfilter")) {
                        this.LDAPFilter = properties.getProperty("ldapfilter");
                    } else {
                        // Must be a mapping column
                        String    value = properties.getProperty(key,"") ;
                        if (value == null) value = "" ;
                        Log.debug(this,"Key=" + key + "  Value=" + value) ;
                        if (value.length() > 0) {
                            LDAPColumnMap.put(key,value) ;
                        }
                    }
                }
            }
        }
        catch (Throwable te)
        {
            //system.out.println("Throwable Exception: " + te.toString());
        }

        this.LDAPUrl = "ldap://" + this.LDAPHost + ":" + this.LDAPPort ;
    }
    /**
     * @return com.tsagate.common.service.properties.LDAPProperties
     */
    public static LDAPProperties getInstance()
    {
        if (INSTANCE == null)
        {
            // Two threads may get here at the same time,
            // they line up behind the lock...
            synchronized (LDAPProperties.class)
            {
                // The first Thread will set INSTANCE
                // and the others will find it already set.
                if (INSTANCE == null)
                {
                    INSTANCE = new LDAPProperties();
                }
            }
        }
        return INSTANCE;
    }

    public boolean isSSOMode() {
       boolean rv = false;

       if (getLDAPMode() != null && getLDAPMode().equalsIgnoreCase("sso")) {
          rv = true;
       }

       return rv;
    }

    /**
    * @return String
    */
   public String getLDAPMode()
   {
      return this.LDAPMode;
   }
    /**
     * @return String
     */
    public String getLDAPHost()
    {
        return this.LDAPHost;
    }
    /**
     * @return String
     */
    public String getLDAPSearchbase()
    {
        return this.LDAPSearchbase;
    }
    /**
     * @return int
     */
    public int getLDAPPort()
    {
        return this.LDAPPort;
    }
    /**
     * @return String representation of the LDAPProperties object
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsagate.common.service.properties.LDAPProperties");
        sb.append("; LDAPHost = " + LDAPHost);
        sb.append("; LDAPSearchbase = " + LDAPSearchbase);
        sb.append("; LDAPPort = " + LDAPPort);
        sb.append("; LDAPConnectUser = " + this.LDAPConnectUser) ;
        sb.append("]");
        return sb.toString();
    }

    /**
     * @return Returns the lDAPConnectUser.
     */
    public String getLDAPConnectUser() {
        return LDAPConnectUser;
    }
    /**
     * @return Returns the lDAPConnectPW.
     */
    public String getLDAPConnectPW() {
        return LDAPConnectPW;
    }
    /**
     * @return Returns the lDAPColumnMap.
     */
    public Map getLDAPColumnMap() {
        return LDAPColumnMap;
    }
    /**
     * @return Returns the lDAPOrganization.
     */
    public String getLDAPOrganization() {
        return LDAPOrganization;
    }
    /**
     * @param connectPW The lDAPConnectPW to set.
     */
    public void setLDAPConnectPW(String connectPW) {
        LDAPConnectPW = connectPW;
    }
    /**
     * @return Returns the lDAPAlternate.
     */
    public String getLDAPAlternateHost() {
        return LDAPAlternateHost;
    }
    /**
     * @return Returns the lDAPAuthOnly.
     */
    public String getLDAPAuthOnly() {
        return LDAPAuthOnly;
    }
    /**
     * @return Returns the lDAPTimeOut.
     */
    public String getLDAPTimeOut() {
        return LDAPTimeOut;
    }
    /**
     * @param timeOut The lDAPTimeOut to set.
     */
    public void setLDAPTimeOut(String timeOut) {
        LDAPTimeOut = timeOut;
    }
    /**
     * @return Returns the lDAPConnectTimeOut.
     */
    public int getLDAPConnectTimeOut() {
        return LDAPConnectTimeOut;
    }
    /**
     * @param connectTimeOut The lDAPConnectTimeOut to set.
     */
    public void setLDAPConnectTimeOut(int connectTimeOut) {
        LDAPConnectTimeOut = connectTimeOut;
    }
    public String getLDAPUserUpdate() {
        return LDAPUserUpdate;
    }
    public void setLDAPUserUpdate(String userUpdate) {
        LDAPUserUpdate = userUpdate;
    }

    public String getLDAPProxyUrl() {
        return LDAPProxyUrl;
    }
    public void setLDAPProxyUrl(String lDAPProxyUrl) {
        LDAPProxyUrl = lDAPProxyUrl;
    }

    public String getLDAPVersion() {
        return LDAPVersion;
    }
    public void setLDAPVersion(String lDAPVersion) {
        LDAPVersion = lDAPVersion;
    }
    public String getLDAPAutoReferrals() {
        return LDAPAutoReferrals;
    }
    public void setLDAPAutoReferrals(String lDAPAutoReferrals) {
        LDAPAutoReferrals = lDAPAutoReferrals;
    }
    public String getLDAPMaxHops() {
        return LDAPMaxHops;
    }
    public void setLDAPMaxHops(String lDAPMaxHops) {
        LDAPMaxHops = lDAPMaxHops;
    }
    public String getLDAPLogging() {
        return LDAPLogging;
    }
    public void setLDAPLogging(String lDAPLogging) {
        LDAPLogging = lDAPLogging;
    }

    public String getLDAPSelfRegistration() {
        return LDAPSelfRegistration;
    }
    public void setLDAPSelfRegistration(String lDAPSelfRegistration) {
        LDAPSelfRegistration = lDAPSelfRegistration;
    }
    public String getLDAPLoginAttr() {
        return LDAPLoginAttr;
    }
    public void setLDAPLoginAttr(String lDAPLoginAttr) {
        LDAPLoginAttr = lDAPLoginAttr;
    }

    public String getLDAPProtocal() {
        return LDAPProtocal;
    }
    public void setLDAPProtocal(String lDAPProtocal) {
        LDAPProtocal = lDAPProtocal;
    }
    public String getLDAPAuthentication() {
        return LDAPAuthentication;
    }
    public void setLDAPAuthentication(String lDAPAuthentication) {
        LDAPAuthentication = lDAPAuthentication;
    }

    public String getLDAPFilter() {
        return LDAPFilter;
    }
    public void setLDAPFilter(String lDAPFilter) {
        LDAPFilter = lDAPFilter;
    }
    public String getLDAPUrl() {
        return LDAPUrl;
    }
    public void setLDAPUrl(String lDAPUrl) {
        LDAPUrl = lDAPUrl;
    }
    public String getLDAPKeystore() {
        return LDAPKeystore;
    }
    public void setLDAPKeystore(String lDAPKeystore) {
        LDAPKeystore = lDAPKeystore;
    }
    public String getSSOType() {
        return SSOType;
    }
    public void setSSOType(String sSOType) {
        SSOType = sSOType;
    }
    public String getSSOCertificate() {
        return SSOCertificate;
    }
    public void setSSOCertificate(String sSOCertificate) {
        SSOCertificate = sSOCertificate;
    }

    public String getSSOSecret() {
        return SSOSecret;
    }
    public void setSSOSecret(String sSOSecret) {
        SSOSecret = sSOSecret;
    }

    public String getSSOHashType() {
        return SSOHashType;
    }
    public void setSSOHashType(String sSOHashType) {
        SSOHashType = sSOHashType;
    }

    public String getSSOSecurityAccess() {
        return SSOSecurityAccess;
    }

    public void setSSOSecurityAccess(String sSOSecurityAccess) {
        SSOSecurityAccess = sSOSecurityAccess;
    }

    public String getSSODefaultAccess() {
        return SSODefaultAccess;
    }
    public void setSSODefaultAccess(String sSODefaultAccess) {
        SSODefaultAccess = sSODefaultAccess;
    }

    public String getSSODefaultApprover() {
        return SSODefaultApprover;
    }
    public void setSSODefaultApprover(String sSODefaultApprover) {
        SSODefaultApprover = sSODefaultApprover;
    }

    public String getSSOIdpUrl() {
        return SSOIdpUrl;
    }
    public void setSSOIdpUrl(String sSORelayUrl) {
        SSOIdpUrl = sSORelayUrl;
    }
    public Map getLDAPPropertyMap() {

        Map ldapMap = new HashMap() ;
        ldapMap.put("columnMap", this.LDAPColumnMap) ;
        ldapMap.put("alternateHost", this.LDAPAlternateHost) ;
        ldapMap.put("authOnly", this.LDAPAuthOnly) ;
        ldapMap.put("connectPW", this.LDAPConnectPW) ;
        ldapMap.put("connectTimeOut", this.LDAPConnectTimeOut) ;
        ldapMap.put("connectUser", this.LDAPConnectUser) ;
        ldapMap.put("loginAttr", this.LDAPLoginAttr) ;
        ldapMap.put("host", this.LDAPHost) ;
        ldapMap.put("organization", this.LDAPOrganization) ;
        ldapMap.put("port", this.LDAPPort) ;
        ldapMap.put("proxyUrl", this.LDAPProxyUrl) ;
        ldapMap.put("searchBase", this.LDAPSearchbase) ;
        ldapMap.put("authenticate", this.LDAPAuthenticate) ;
        ldapMap.put("userUpdate", this.LDAPUserUpdate) ;
        ldapMap.put("timeOut", this.LDAPTimeOut) ;
        ldapMap.put("autoReferrals", this.LDAPAutoReferrals) ;
        ldapMap.put("maxHops",this.LDAPMaxHops) ;
        ldapMap.put("logging", this.LDAPLogging) ;
        ldapMap.put("version", this.LDAPVersion) ;
        ldapMap.put("selfRegistration", this.LDAPSelfRegistration) ;
        ldapMap.put("ssotype",this.getSSOType()) ;
        ldapMap.put("ssocertificate", this.getSSOCertificate()) ;
        ldapMap.put("ssoSecret", this.getSSOSecret()) ;
        ldapMap.put("ssoHashType", this.getSSOHashType()) ;
        ldapMap.put("ssoidpurl", this.getSSOIdpUrl()) ;

        return ldapMap ;
    }


    public boolean isUseBlackBox() {
        return useBlackBox;
    }
    public void setUseBlackBox(boolean useBlackBox) {
        this.useBlackBox = useBlackBox;
    }
    public static void main(String [] args) {
        LDAPProperties properties = new LDAPProperties();

        System.out.println("username: " + properties.getLDAPConnectUser());
        System.out.println("password: " + properties.getLDAPConnectPW());
    }
}