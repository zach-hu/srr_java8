package com.tsa.puridiom.currcode;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CurrCode;
import com.tsa.puridiom.entity.Country;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.*;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;

/**
 * Creation date: September 2006
 * @author: Kelli Knisely
 */
public class CurrencyManager {
	private static HashMap currencyManagers = new HashMap();
	private HashMap currencies;
	private HashMap countries;
	private String organizationId;
	private List locales;

	public static CurrencyManager getInstance(String oid) {
		if (CurrencyManager.currencyManagers.get(oid) == null) {
		    CurrencyManager.currencyManagers.put(oid, new CurrencyManager(oid));
		}
		return (CurrencyManager) CurrencyManager.currencyManagers.get(oid);
	}

	private CurrencyManager(String oid)
	{
	    this.organizationId = oid;
		this.loadCurrencies();
		this.loadCountries();
		this.loadLocales();
	}

	private void loadLocales()
	{
		Locale _locales[] = Locale.getAvailableLocales();
		this.locales = Arrays.asList(_locales);
	}

	public NumberFormat getCurrencyFormat(String currencyCode)
	{
		return this.getCurrencyFormat(currencyCode, true);
	}

	/**
	 * Returns the NumberFormat for this currency. If currency is empty or null will return
	 * base currency format.
	 * @param currencyCode
	 * @return
	 */
	public NumberFormat getCurrencyFormat(String currencyCode, boolean isCurrency)
	{
	    NumberFormat nf = NumberFormat.getCurrencyInstance();

	    try
	    {
			if (HiltonUtility.isEmpty(currencyCode))
			{
				currencyCode = this.getBaseCurrencyCode();
			}
			Currency currency = null;
			try
			{
				currency = Currency.getInstance(currencyCode);
			}
			catch (IllegalArgumentException	iA)
			{
				Log.error(this, "Currency not found: " + currencyCode);
				currency = Currency.getInstance(Locale.US);
			}

			int localeSize = this.locales.size();
			for (int i = 0; i < localeSize; i++)
			{
				Locale locale = (Locale)this.locales.get(i);
				if(isCurrency)
				{
					nf = NumberFormat.getCurrencyInstance(locale);
				}
				else
				{
					nf = NumberFormat.getNumberInstance(locale);
				}
				Currency localeCurrency = nf.getCurrency();
				if (currency.equals(localeCurrency) && i != 63)
				{
					return nf;
				}
			}
	    } catch (Exception e) {
	        Log.error(this, "An error occurred getting the currency format for " + currencyCode + " - " + e.getMessage());
	    }

		return nf;
	}

	public String getCurrencychars(String formatted,String currencyCode) {
        CurrCode currCode = null;
        String nf = "";
    	BigDecimal res;
    	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
    	String strange = "###,###,###";

        try {
        	if (HiltonUtility.isEmpty(currencyCode))
			{
				currencyCode = this.getBaseCurrencyCode();
			}

        	currCode = (CurrCode) this.getCurrencies().get(currencyCode);
        	if(currCode == null)
        	{
        		currencyCode = this.getBaseCurrencyCode();
        		currCode = (CurrCode) this.getCurrencies().get(currencyCode);
        	}

        	String symbol = currCode.getSymbol();

            String getdecimals = (String) currCode.getDecimalDigits().toString();
            String getseparator = currCode.getDecimalSeparator();
            String getthseparator = currCode.getThousandsSeprtr();

            int decimals = Integer.parseInt(getdecimals);
            //double Number = Double.parseDouble(formatted);

            res = new BigDecimal(formatted).setScale(decimals, BigDecimal.ROUND_UP);

            if (decimals > 0)
            {
            	unusualSymbols.setDecimalSeparator(getseparator.charAt(0));
                for(int x=0 ; x < decimals ; x++ )
                {
                	if(x == 0)
                	{
                		strange += ".";
                	}
                	strange += "0";
                }
            }
            unusualSymbols.setGroupingSeparator(getthseparator.charAt(0));

            DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
            weirdFormatter.setGroupingSize(3);

			String value = weirdFormatter.format(res);
			if (value.indexOf(".") == 0)
				value = "0" + value;

			nf = symbol + " " + value;

            }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return nf;
    }

	public String getCurrencycharsNS(String formatted,String currencyCode) {
        CurrCode currCode = null;
        String nf = "";
    	BigDecimal res;
    	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
    	String strange = "###,###,###";

        try {
        	if (HiltonUtility.isEmpty(currencyCode))
			{
				currencyCode = this.getBaseCurrencyCode();
			}

        	currCode = (CurrCode) this.getCurrencies().get(currencyCode);
        	if(currCode == null)
        	{
        		currencyCode = this.getBaseCurrencyCode();
        		currCode = (CurrCode) this.getCurrencies().get(currencyCode);
        	}

        	String symbol = currCode.getSymbol();

            String getdecimals = (String) currCode.getDecimalDigits().toString();
            String getseparator = currCode.getDecimalSeparator();
            String getthseparator = currCode.getThousandsSeprtr();

            int decimals = Integer.parseInt(getdecimals);
            //double Number = Double.parseDouble(formatted);

            res = new BigDecimal(formatted).setScale(decimals, BigDecimal.ROUND_UP);

            if (decimals > 0)
            {
            	unusualSymbols.setDecimalSeparator(getseparator.charAt(0));
                for(int x=0 ; x < decimals ; x++ )
                {
                	if(x == 0)
                	{
                		strange += ".";
                	}
                	strange += "0";
                }
            }
            unusualSymbols.setGroupingSeparator(getthseparator.charAt(0));

            DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
            weirdFormatter.setGroupingSize(3);

            nf = weirdFormatter.format(res);

			if (nf.indexOf(".") == 0)
				nf = "0" + nf;

            }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return nf;
    }

	public String getConvertCurrency(String formatted,String currencyCode) {
        CurrCode currCode = null;
        String nf = "";
    	BigDecimal res;
    	DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
    	String strange = "###,###,###";

        try {
        	currCode = (CurrCode) this.getCurrencies().get(currencyCode);
        	BigDecimal conversion = currCode.getConversionToBase();
            res = new BigDecimal(formatted).multiply(conversion);

            currencyCode = this.getBaseCurrencyCode();
        	currCode = (CurrCode) this.getCurrencies().get(currencyCode);

        	String symbol = currCode.getSymbol();
            String getdecimals = (String) currCode.getDecimalDigits().toString();
            String getseparator = currCode.getDecimalSeparator();
            String getthseparator = currCode.getThousandsSeprtr();

            int decimals = Integer.parseInt(getdecimals);
            //double Number = Double.parseDouble(formatted);

            res = res.setScale(decimals, BigDecimal.ROUND_UP);


            if (decimals > 0)
            {
            	unusualSymbols.setDecimalSeparator(getseparator.charAt(0));
                for(int x=0 ; x < decimals ; x++ )
                {
                	if(x == 0)
                	{
                		strange += ".";
                	}
                	strange += "0";
                }
            }
            unusualSymbols.setGroupingSeparator(getthseparator.charAt(0));

            DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
            weirdFormatter.setGroupingSize(3);

            nf = symbol + " " + weirdFormatter.format(res);

            }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return nf;
    }

	public BigDecimal getBaseTotalConvert(String s_formatted,String currencyCode) {
        CurrCode currCode = null;
        BigDecimal formatted = new BigDecimal(s_formatted);
        BigDecimal res = new BigDecimal(0);
        String oid = this.organizationId;

        try
        {
        	currCode = (CurrCode) this.getCurrencies().get(currencyCode);
        	BigDecimal conversion = currCode.getConversionToBase();

        	currencyCode = this.getBaseCurrencyCode();
        	currCode = (CurrCode) this.getCurrencies().get(currencyCode);

            String decimalDigits = currCode.getDecimalDigits().toString();
        	if(oid.equalsIgnoreCase("bly07p"))
        	{
        		res = formatted.multiply(conversion).setScale(Integer.valueOf(decimalDigits).intValue(), BigDecimal.ROUND_HALF_UP);
        	}
        	else
        	{
        		res = formatted.multiply(conversion).setScale(2, BigDecimal.ROUND_HALF_UP);
        	}
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

	private void loadCurrencies() {
		try {
			this.setCurrencies(new HashMap());

		    Map incomingRequest = new HashMap() ;
			incomingRequest.put("organizationId", this.organizationId);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("currcode-retrieve-all.xml");

			process.executeProcess(incomingRequest);
			List list = (List)incomingRequest.get("currCodeList");

			for (Iterator it = list.iterator(); it.hasNext(); ) {
				CurrCode currCode = (CurrCode) it.next();
		        if (currCode != null) {
		    		String	currencyCode = currCode.getCurrencyCode().toUpperCase().trim();

		    		this.getCurrencies().put(currencyCode, currCode);
		    	}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadCountries() {
		try {
			this.countries = new HashMap();

		    Map incomingRequest = new HashMap() ;
			incomingRequest.put("organizationId", this.organizationId);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("country-retrieve-all.xml");

			process.executeProcess(incomingRequest);
			List list = (List)incomingRequest.get("countryList");

			for (Iterator it = list.iterator(); it.hasNext(); ) {
				Country country = (Country) it.next();
		        if (country != null) {
		    		String	countryCode = country.getCountryCode().toUpperCase().trim();

		    		this.countries.put(countryCode, country);
		    	}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static  void reloadCurrencies (String oid) {
	    CurrencyManager.getInstance(oid).loadCurrencies();
	}

	public static  void reloadCountries (String oid) {
	    CurrencyManager.getInstance(oid).loadCountries();
	}

    /**
     * @return CurrCode
     * @param currencyCode java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public CurrCode getCurrCode(String currencyCode) {
        CurrCode currCode = null;

        try {
            if (Utility.isEmpty(currencyCode)) {
                Log.debug(this, "CurrencyCode is required.  An empty CurrCode object was returned. [organizationId: " + this.organizationId + "][currencyCode: " + currencyCode + "]");
                return new CurrCode();
            }
            else {
                if (this.getCurrencies().containsKey(currencyCode)) {
                    currCode = (CurrCode) this.getCurrencies().get(currencyCode);
                }
                else {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", this.organizationId);
                    processParameters.put("CurrCode_currencyCode", currencyCode);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.organizationId);
                    PuridiomProcess process = processLoader.loadProcess("currcode-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    currCode = (CurrCode) processParameters.get("currCode");

                    if (currCode != null) {
                        this.getCurrencies().put(currencyCode, currCode);
                    }
                }
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return currCode;
    }

    /**
     * @return Country
     * @param vendorId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public Country getCountry(String countryCode) {
        Country country = null;

        try {
            if (Utility.isEmpty(countryCode)) {
                Log.debug(this, "CountryCode is required.  An empty Country object was returned. [organizationId: " + this.organizationId + "][countryCode: " + countryCode + "]");
                return new Country();
            }
            else {
                if (this.countries.containsKey(countryCode)) {
                    country = (Country) this.countries.get(countryCode);
                }
                else {
                    HashMap processParameters = new HashMap();
                    processParameters.put("organizationId", this.organizationId);
                    processParameters.put("Country_countryCode", countryCode);

                    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.organizationId);
                    PuridiomProcess process = processLoader.loadProcess("country-retrieve-by-id.xml");

                    process.executeProcess(processParameters);

                    country = (Country) processParameters.get("country");

                    if (country != null) {
                        this.countries.put(countryCode, country);
                    }
                }
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        finally {
            return country;
        }
    }

    /**
     * @return java.math.BigDecimal currencyFactor
     * @param currencyCode java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public BigDecimal getCurrencyFactor(String currencyCode) {
        BigDecimal currencyFactor = new BigDecimal(1);

        try {
            if (!Utility.isEmpty(currencyCode)) {
                CurrCode currCode = this.getCurrCode(currencyCode);
                if (currCode != null) {
                    currencyFactor = currCode.getConversionToBase();
                }
                if (currencyFactor.compareTo(new BigDecimal(0)) == 0) {
                    // always default to 1
                    currencyFactor = new BigDecimal(1);
                }
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        finally {
            return currencyFactor;
        }
    }

    /**
     * @return java.lang.String currencyCode
     * @param countryCode java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public String getCurrencyCodeForCountry(String countryCode) {
        String	currencyCode = "";

        try {
            if (!Utility.isEmpty(countryCode)) {
                Country country = this.getCountry(countryCode);
                if (country != null) {
                    currencyCode = country.getCurrencyCode();
                }

                if (Utility.isEmpty(currencyCode)) {
                    currencyCode = this.getBaseCurrencyCode();
                }
            }
        }
        catch (Exception e) {
            Log.error(this, e.toString());
        }
        finally {
            return currencyCode;
        }
    }


    /**
     * @return java.math.BigDecimal currencyFactor
     * @param countryCode java.lang.String
     * @exception java.lang.Exception The exception description.
     */
    public BigDecimal getCurrencyFactorForCountry(String countryCode) {
        BigDecimal	currencyFactor = new BigDecimal(1);

        try {
            if (!Utility.isEmpty(countryCode)) {
                String currencyCode = this.getCurrencyCodeForCountry(countryCode);

                currencyFactor = this.getCurrencyFactor(currencyCode);
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        finally {
            return currencyFactor;
        }
    }

    /**
     * @param currCode CurrCode
     * @exception java.lang.Exception
     */
    public void setCurrCodeInCache(CurrCode currCode) {
        try {
            if (currCode == null){
                Log.error(this, "CurrCode was null.");
            }

            String	currencyCode = currCode.getCurrencyCode();

            if (!Utility.isEmpty(currencyCode)) {
                currencyCode = currencyCode.trim().toUpperCase();

                this.getCurrencies().put(currencyCode, currCode);
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
    }

    /**
     * @param String currencyCode
     * @exception java.lang.Exception
     */
    public void removeCurrCodeFromCache(String currencyCode) {
        try {
            if (!Utility.isEmpty(currencyCode)) {
                currencyCode = currencyCode.trim().toUpperCase();
                this.getCurrencies().remove(currencyCode);
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
    }

    /**
     * @param country Country
     * @exception java.lang.Exception
     */
    public void setCountryInCache(Country country) {
        try {
            if (country == null) throw new Exception ("Country was null.");

            String	countryCode = country.getCountryCode();

            if (!Utility.isEmpty(countryCode)) {
                countryCode = countryCode.trim().toUpperCase();

                this.countries.put(countryCode, country);
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
    }

    /**
     * @param String countryCode
     * @exception java.lang.Exception
     */
    public void removeCountryFromCache(String countryCode) {
        try {
            if (!Utility.isEmpty(countryCode)) {
                countryCode = countryCode.trim().toUpperCase();
                this.countries.remove(countryCode);
            }
        }
        catch (Exception e) {
            Log.error(this, e.getMessage());
        }
    }

    public String getBaseCurrencyCode() {
        return PropertiesManager.getInstance(this.organizationId).getProperty("MISC", "BASECURRENCY", "USD");
    }

    public BigDecimal convertPriceToBaseCurrency(BigDecimal originalPrice, String fromCurrencyCode) {
        BigDecimal convertedPrice = new BigDecimal(0);
        try {
            BigDecimal currencyFactor = this.getCurrencyFactor(fromCurrencyCode);
            convertedPrice = originalPrice.multiply(currencyFactor);
        } catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return convertedPrice;
    }

    public BigDecimal convertPriceFromBaseCurrency(BigDecimal basePrice, String toCurrencyCode) {
        BigDecimal convertedPrice = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);
        try {
            if (basePrice.compareTo(zero) == 0) {
                return basePrice;
            }
            BigDecimal currencyFactor = this.getCurrencyFactor(toCurrencyCode);
            if (currencyFactor.compareTo(zero) == 0) {
                currencyFactor = new BigDecimal(1);
            }
            convertedPrice = basePrice.divide(currencyFactor, 10, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return convertedPrice;
    }

    public BigDecimal convertPrice(BigDecimal originalPrice, String fromCurrencyCode, String toCurrencyCode) {
        BigDecimal convertedPrice = new BigDecimal(0);
        BigDecimal zero = new BigDecimal(0);
        try {
            if (originalPrice.compareTo(zero) == 0) {
                return originalPrice;
            }
            BigDecimal basePrice = this.convertPriceToBaseCurrency(originalPrice, fromCurrencyCode);
            convertedPrice = this.convertPriceFromBaseCurrency(basePrice, toCurrencyCode);

        } catch (Exception e) {
            Log.error(this, e.getMessage());
        }
        return convertedPrice;
    }

    /**
     * Returns a String that represents the value of this object.
     * @return a string representation of the receiver
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[ClassName=com.tsa.puridiom.currcode.CurrencyManager]");
        return sb.toString();
    }

	public void setCurrencies(HashMap currencies) {
		this.currencies = currencies;
	}

	public HashMap getCurrencies() {
		return currencies;
	}
}
