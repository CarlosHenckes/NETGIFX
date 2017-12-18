package br.com.netgifx.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class GetFrameFromAnimatedGif {
	
	public static byte[] getImageStream(ImageInputStream imageInputStream) throws IOException{
		BufferedImage bi = null;
		ImageReader reader = (ImageReader)ImageIO.getImageReadersByFormatName("gif").next();
		ImageInputStream iis = ImageIO.createImageInputStream(imageInputStream);
		reader.setInput(iis);
		bi = reader.read(1);
		byte[] imageBytes = ((DataBufferByte) bi.getData().getDataBuffer()).getData();
		return imageBytes;
	}
}
