package com.example.loadimagee;

public class Filename {
	String filename_Without_Ext = "";
	 String ext = "";

	 Filename(String file){
	     int dotposition= file.lastIndexOf(".");
	     filename_Without_Ext = file.substring(0,dotposition);
	     ext = file.substring(dotposition + 1, file.length());
	 }

	 String getFilename_Without_Ext(){
	  return filename_Without_Ext;
	 }

	 String getExt(){
	  return ext;
	 }
	
}
