import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class YahooXmlReader {
	private YahooXmlReader() {
		// no instantiation
	}

	public static Address readConfig(InputStream in) {
		Address address = new Address();
		try {
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			// InputStream in = new FileInputStream(configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// Read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					if (event.asStartElement().getName().getLocalPart() == ("country")) {
						event = eventReader.nextEvent();
						address.setCountry(event.asCharacters().getData());
						continue;
					}
					if (event.asStartElement().getName().getLocalPart() == ("county")) {
						event = eventReader.nextEvent();
						address.setCity(event.asCharacters().getData());
						continue;
					}
					
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return address;
	}
}
