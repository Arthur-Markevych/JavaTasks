package ua.khpi.markevich.SummaryTask3.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.khpi.markevich.SummaryTask3.model.Knife;
import ua.khpi.markevich.SummaryTask3.model.Visuals;

/**
 * SAX Parser to parse xml which response input.xsd.
 * 
 * @author Arthur Markevich
 *
 */
public class OwnSAXParser extends DefaultHandler {

	/**
	 * Resulting list of knives.
	 */
	private List<Knife> knives;

	private Knife knife;
	private Visuals visuals;

	private boolean bModel;
	private boolean bType;
	private boolean bOrigin;

	private boolean bBlade;
	private boolean bLength;
	private boolean bWidth;
	private boolean bBloodsucker;

	private boolean bWood;
	private boolean bSteel;
	private boolean bPlastic;

	/**
	 * Obtain objects from xml file and put them to knives list.
	 * 
	 * @param path
	 *            xml file to parse
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void parse(String path) throws ParserConfigurationException, SAXException, IOException {
		File inputFile = new File(path);
		knives = new ArrayList<>();
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = spf.newSAXParser();
		saxParser.parse(inputFile, this);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		String tag = qName;
		switch (tag) {
		case "knife":
			knife = new Knife();
			String handy = attributes.getValue("handy");
			boolean value = Boolean.parseBoolean(attributes.getValue("value"));
			knife.setHandy(handy);
			knife.setValue(value);
			break;

		case "visuals":
			visuals = new Visuals();
			break;

		case "model":
			bModel = true;
			break;

		case "type":
			bType = true;
			break;

		case "origin":
			bOrigin = true;
			break;

		case "blade":
			bBlade = true;
			break;

		case "length":
			bLength = true;
			break;

		case "width":
			bWidth = true;
			break;

		case "wood":
			bWood = true;
			String material = attributes.getValue("material");
			visuals.setHandleMaterial(material);
			break;

		case "steel":
			bSteel = true;
			break;
		case "plastic":
			bPlastic = true;
			break;

		case "bloodsucker":
			bBloodsucker = true;
			break;

		default:
			break;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("knife")) {
			knife.setVisuals(visuals);
			knives.add(knife);
			visuals = null;
			knife = null;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String currentTag = new String(ch, start, length);

		if (bModel) {
			knife.setModel(currentTag);
			bModel = false;
		} else if (bType) {
			knife.setType(currentTag);
			bType = false;
		} else if (bOrigin) {
			knife.setOrigin(currentTag);
			bOrigin = false;
		} else if (bBlade) {
			visuals.setBladeMaterial(currentTag);
			bBlade = false;
		} else if (bLength) {
			visuals.setLength(Integer.parseInt(currentTag));
			bLength = false;
		} else if (bWidth) {
			visuals.setWidth(Integer.parseInt(currentTag));
			bWidth = false;
		} else if (bWood) {
			visuals.setWoodenHandler(true);
			bWood = false;
		} else if (bSteel) {
			visuals.setHandleMaterial("steel");
			bSteel = false;
		} else if (bPlastic) {
			visuals.setHandleMaterial("plastic");
			bPlastic = false;
		} else if (bBloodsucker) {
			visuals.setBloodsucker(Boolean.parseBoolean(currentTag));
			bBloodsucker = false;
		}
	}

	public List<Knife> getKnives() {
		return knives;
	}

}
