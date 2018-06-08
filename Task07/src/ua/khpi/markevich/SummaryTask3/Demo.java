package ua.khpi.markevich.SummaryTask3;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Demo {

	/**
	 * Path to xml file.
	 */
	private final static String XML_FILE = "src/ua/khpi/markevich/SummaryTask3/xml/input.xml";

	/**
	 * Path to xsd file.
	 */
	private final static String XSD_XSD = "src/ua/khpi/markevich/SummaryTask3/xml/input.xsd";

	public static void main(String[] args)
			throws SAXException, IOException, ParserConfigurationException, XMLStreamException, TransformerException {

		Main.main(new String[] { XML_FILE, XSD_XSD });

	}

}
