package com.tsa.puridiom.common.documents;

import java.math.BigDecimal;
import com.tsa.puridiom.property.PropertiesManager;

public class SupplierPerformanceRatings
{
	public static BigDecimal NONE = new BigDecimal(9);
	public static BigDecimal EXCELLENT = new BigDecimal(4);
	public static BigDecimal GOOD = new BigDecimal(3);
	public static BigDecimal MARGINAL = new BigDecimal(2);
	public static BigDecimal POOR = new BigDecimal(1);
	public static BigDecimal UNSATISFACTORY = new BigDecimal(0);

	public static BigDecimal NOTHING = new BigDecimal(0.00);
	public static BigDecimal TWENTYFIVEPERCENT = new BigDecimal(0.25);
	public static BigDecimal FIFTYPERCENT = new BigDecimal(0.50);
	public static BigDecimal SEVENTYFIVEPERCENT = new BigDecimal(0.75);
	public static BigDecimal ONEHUNDREDPERCENT = new BigDecimal(1.00);

	public static String getText(BigDecimal rating)
	{
		String ret = "NONE";
		if(rating.compareTo(SupplierPerformanceRatings.UNSATISFACTORY) == 0)
		{
			ret = "Unsatisfactory";
		}
		else if(rating.compareTo(SupplierPerformanceRatings.POOR) == 0)
		{
			ret = "Poor";
		}
		else if(rating.compareTo(SupplierPerformanceRatings.MARGINAL) == 0)
		{
			ret = "Marginal";
		}
		else if(rating.compareTo(SupplierPerformanceRatings.GOOD) == 0)
		{
			ret = "Good";
		}
		else if(rating.compareTo(SupplierPerformanceRatings.EXCELLENT) == 0)
		{
			ret = "Excellent";
		}
		else
		{
			ret = "NONE";
		}
		return ret;
	}


	public static BigDecimal calculateRating(BigDecimal rating)
	{
		BigDecimal percent  = new BigDecimal(0);
		if(rating.compareTo(SupplierPerformanceRatings.UNSATISFACTORY) == 0)
		{
			percent = SupplierPerformanceRatings.NOTHING;
		}

		else if(rating.compareTo(SupplierPerformanceRatings.POOR) == 0)
		{
			percent = SupplierPerformanceRatings.TWENTYFIVEPERCENT;
		}

		else if(rating.compareTo(SupplierPerformanceRatings.MARGINAL) == 0)
		{
			percent = SupplierPerformanceRatings.FIFTYPERCENT;
		}
		else if(rating.compareTo(SupplierPerformanceRatings.GOOD) == 0)
		{
			percent = SupplierPerformanceRatings.SEVENTYFIVEPERCENT;
		}

		else if(rating.compareTo(SupplierPerformanceRatings.EXCELLENT) == 0)
		{
			percent = SupplierPerformanceRatings.ONEHUNDREDPERCENT;
		}
		return percent;

	}

    public static String getRatingImage(String oid, BigDecimal rating, boolean displayRating)
    {
        StringBuffer img = new StringBuffer();
        StringBuffer ratingDisplay = new StringBuffer();
        BigDecimal ONESTAR = new BigDecimal(0.0);
        BigDecimal TWOSTARS = new BigDecimal(20.0);
        BigDecimal THREESTARS = new BigDecimal(40.0);
        BigDecimal FOURSTARS = new BigDecimal(60.0);
        BigDecimal FIVESTARS = new BigDecimal(80.0);
        String url = PropertiesManager.getInstance(oid).getProperty("APPLICATION", "URL", "/puridiom");

        if ((rating.compareTo(ONESTAR) == 0 || rating.compareTo(ONESTAR) == 1)) {
            img.append("<img height=15px src='" + url + "/images/star.gif' border=0 alt=" + rating.toString() + ">");
            ratingDisplay.append("&nbsp;&nbsp;");
        }
        if ((rating.compareTo(TWOSTARS) == 0 || rating.compareTo(TWOSTARS) == 1)) {
            img.append("<img height=15px src='" + url + "/images/star.gif' border=0 alt=" + rating.toString() + ">");
            ratingDisplay.append("&nbsp;&nbsp;");
        }
        if ((rating.compareTo(THREESTARS) == 0 || rating.compareTo(THREESTARS) == 1)) {
            img.append("<img height=15px src='" + url + "/images/star.gif' border=0 alt=" + rating.toString() + ">");
            ratingDisplay.append("&nbsp;&nbsp;");
        }
        if ((rating.compareTo(FOURSTARS) == 0 || rating.compareTo(FOURSTARS) == 1)) {
            img.append("<img height=15px src='" + url + "/images/star.gif' border=0 alt=" + rating.toString() + ">");
            ratingDisplay.append("&nbsp;&nbsp;");
        }
        if (rating.compareTo(FIVESTARS) == 0 || rating.compareTo(FIVESTARS) == 1) {
            img.append("<img height=15px src='" + url + "/images/star.gif' border=0 alt=" + rating.toString() + ">");
            ratingDisplay.append("&nbsp;&nbsp;");
        }

        if (img.length() > 0 && displayRating) {
            img.append("<br>&nbsp;" + ratingDisplay.toString() + rating);
        }

        return img.toString();
    }

}
