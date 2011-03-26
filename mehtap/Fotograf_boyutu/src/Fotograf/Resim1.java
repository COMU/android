package Fotograf;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resim1 {  
	  public static void main(String[]args) throws IOException{
		  File f=new File("/home/mehtap/Masaüstü/test.jpg");
	    	BufferedImage bimg=ImageIO.read(f);
	    	BufferedImage bi=resize(bimg,150,150);
	    	saveImage(bi,"/home/mehtap/Belgeler/test2.jpg");
	  }
	  public static BufferedImage resize(BufferedImage img,int newW,int newH){
			 int w=img.getWidth();
			 int h=img.getHeight();
			 BufferedImage dimg=new BufferedImage(newW,newH,img.getType());
			 Graphics2D g=dimg.createGraphics();
			 g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			 g.drawImage(img,0,0,newW,newH,0,0,w,h,null);
			 g.dispose();
			 
			 
			return dimg;
			 
		 }
	  public static void saveImage (BufferedImage img,String ref){
		  try{
			  String format=(ref.endsWith(".jpg"))? "png":"jpg";
			  ImageIO.write(img, format,new File(ref));
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  }
}
