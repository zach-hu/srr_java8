package com.tsa.puridiom.reports.datasource;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.media.jai.NullOpImage;
import javax.media.jai.OpImage;
import javax.media.jai.PlanarImage;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
import com.sun.media.jai.codec.TIFFDecodeParam;
import com.tsagate.foundation.utility.Log;

public class TiffEntity
{
	private String tiffName;
	private int imageIndex;
	private ImageDecoder dec;

	private void getDecoder() throws IOException
	{
		if(this.getTiffName().toLowerCase().indexOf(".tif") > 0)
		{
			File file = new File(this.getTiffName());
	        SeekableStream s = new FileSeekableStream(file);

	        TIFFDecodeParam param = null;

	        this.dec = ImageCodec.createImageDecoder("tiff", s, param);
	     }
	}

	public TiffEntity(String _ImageName)
	{
		this.setTiffName(_ImageName);
		try
		{
			this.getDecoder();
		}
		catch (IOException ioe)
		{
			this.dec = null;
			Log.error(this, this.getTiffName() + " was not found");
		}
	}

	public TiffEntity(String _ImageName, int index)
	{
		this.setTiffName(_ImageName);
		try
		{
			this.getDecoder();
		}
		catch (IOException ioe)
		{
			this.dec = null;
			Log.error(this, this.getTiffName() + " was not found");
		}
		this.setImageIndex(index);
	}

	public Object getImage() throws IOException
	{
		return this.getImage(this.getImageIndex());
	}

	public Object getImage(int index) throws IOException
	{
		if(this.getTiffName().toLowerCase().indexOf(".tif") > 0)
		{
	        if(this.dec != null)
	        {
		        System.out.println("getting page " + index);

		        // Which of the multiple images in the TIFF file do we want to load
		        // 0 refers to the first, 1 to the second and so on.
		        if(this.getPages() > 1)
		        {
		        	index = 0;
		        }
		        BufferedImage bufim = null;
		        try
		        {
		        	PlanarImage im = new NullOpImage(dec.decodeAsRenderedImage(index), null, OpImage.OP_IO_BOUND, null);

		        	bufim = im.getAsBufferedImage();
		        }
		        catch (Exception e) {
					e.printStackTrace();
				}

		        return bufim;
	        }
	        else
	        {
	        	return null;
	        }
		}
		else
		{
			return this.getTiffName();
		}
    }

	public int getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public String getTiffName() {
		return tiffName;
	}

	public void setTiffName(String tiffName) {
		this.tiffName = tiffName;
	}

	public int getPages()
	{
		if(this.getTiffName().toLowerCase().indexOf(".tif") > 0)
		{
			try
			{
				return dec.getNumPages();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return 0;
			}
		}
		else
		{
			return 1;
		}
	}
}
