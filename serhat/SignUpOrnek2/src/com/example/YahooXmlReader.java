package com.example;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class YahooXmlReader {
	private YahooXmlReader() {
		// no instantiation
	}

	public static myAddress readConfig(InputStream in) {
		myAddress address = new myAddress();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(in));
			doc.getDocumentElement().normalize();
			NodeList nodeList=doc.getElementsByTagName("Result");
			 for (int i = 0; i < nodeList.getLength(); i++) {
				 Node node = nodeList.item(i);


				 Element fstElmnt = (Element) node;
				 
				 NodeList nameList = fstElmnt.getElementsByTagName("country");
				 Element nameElement = (Element) nameList.item(0);
				 nameList =  nameElement.getChildNodes();
				 
				 if(((Node) nameList.item(0)) != null)
				 address.setCountry( ((Node) nameList.item(0)).getNodeValue());
				 else 
					 return null;
				 
				 NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("state");
			     Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
			     NodeList lstNm = lstNmElmnt.getChildNodes();
			     if(((Node) lstNm.item(0)).getNodeValue() != null)
					 address.setCity( ((Node) lstNm.item(0)).getNodeValue());
				 else{
					 NodeList countyLst = fstElmnt.getElementsByTagName("county");
				     Element countyElmnt = (Element) countyLst.item(0);
				     NodeList county = countyElmnt.getChildNodes();
				     if(((Node) county.item(0)).getNodeValue() != null)
						 address.setCity( ((Node) county.item(0)).getNodeValue());
				     else{
				    	 NodeList cityLst = fstElmnt.getElementsByTagName("city");
					     Element cityElmnt = (Element) cityLst.item(0);
					     NodeList city = cityElmnt.getChildNodes();
					     if(((Node) county.item(0)).getNodeValue() != null)
							 address.setCity( ((Node) city.item(0)).getNodeValue());
				     }
				 }
				 
				 

			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return address;
	}
}
