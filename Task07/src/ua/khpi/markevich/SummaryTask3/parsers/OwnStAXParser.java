package ua.khpi.markevich.SummaryTask3.parsers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import ua.khpi.markevich.SummaryTask3.model.Knife;
import ua.khpi.markevich.SummaryTask3.model.Visuals;

public class OwnStAXParser {

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

	public List<Knife> getKnives() {
		return knives;
	}

	/**
	 * Obtain objects from xml file and put them to knives list.
	 * 
	 * @param path
	 *            xml file to parse
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public void parse(String path) throws FileNotFoundException, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(path));
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			switch (event.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				startElement(event);
				break;

			case XMLStreamConstants.CHARACTERS:
				characters(event);
				break;

			case XMLStreamConstants.END_ELEMENT:
				endElement(event);
				break;

			default:
				break;
			}

		}
	}

	private void startElement(XMLEvent event) {

		StartElement startElement = event.asStartElement();
		String qName = startElement.getName().getLocalPart();
		if (knives == null) {
			knives = new ArrayList<>();
		}

		switch (qName) {
		case "knife":
			knife = new Knife();
			@SuppressWarnings("rawtypes")
			Iterator attributes = startElement.getAttributes();
			String handy = ((Attribute) attributes.next()).getValue();
			String value = ((Attribute) attributes.next()).getValue();
			knife.setHandy(handy);
			knife.setValue(Boolean.parseBoolean(value));
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
			@SuppressWarnings("rawtypes")
			Iterator iterator = startElement.getAttributes();
			String material = ((Attribute) iterator.next()).getValue();
			visuals.setWoodenHandler(true);
			visuals.setHandleMaterial(material);
			bWood = true;
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

	private void characters(XMLEvent event) {
		Characters characters = event.asCharacters();
		String currentTag = characters.getData();

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

	private void endElement(XMLEvent event) {
		EndElement endElement = event.asEndElement();
		if (endElement.getName().getLocalPart().equals("knife")) {
			knife.setVisuals(visuals);
			knives.add(knife);
			visuals = null;
			knife = null;
		}

	}
}
