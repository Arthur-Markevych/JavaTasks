package ua.khpi.markevich.SummaryTask3;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import ua.khpi.markevich.SummaryTask3.creater.XMLCreater;
import ua.khpi.markevich.SummaryTask3.model.Knife;
import ua.khpi.markevich.SummaryTask3.parsers.OwnDOMParser;
import ua.khpi.markevich.SummaryTask3.parsers.OwnSAXParser;
import ua.khpi.markevich.SummaryTask3.parsers.OwnStAXParser;

public class Main {

	public static void main(String[] args)
			throws SAXException, IOException, ParserConfigurationException, XMLStreamException, TransformerException {
		if (args.length < 2) {
			throw new IllegalArgumentException("argument is required!");
		}
		final String xmlFile = args[0];
		final String xsdFile = args[1];

		System.out.println(" ~~ Validation ~~");
		validateXMLFile(xmlFile, xsdFile);
		System.out.println();

		System.out.println(" ~~ DOM Parser start (sorting: model) ~~");
		domRun(xmlFile);
		System.out.println();
		System.out.println(" ~~ SAX Parser start (sorting: length) ~~");
		saxRun(xmlFile);
		System.out.println();
		System.out.println(" ~~ StAX Parser start (sorting: width) ~~");
		staxTest(xmlFile);

	}

	/**
	 * The method validates xml file accordingly to xsd.
	 * 
	 * @param xml
	 *            path to file to validate
	 * @param xsd
	 *            path to validation schema
	 * @throws IOException
	 *             in case of reading files problem
	 */
	public static void validateXMLFile(String xml, String xsd) throws IOException {
		File schemaFile = new File(xsd);
		Source xmlFile = new StreamSource(new File(xml));
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println(xmlFile.getSystemId() + " is valid");
		} catch (SAXException e) {
			System.out.println(xmlFile.getSystemId() + " is NOT valid reson: " + e);
		}
	}

	/**
	 * DOM parser do work. Parse xml file make sorting by model and write it to new
	 * xml file.
	 * 
	 * @param path
	 *            file to parse
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	private static void domRun(String path)
			throws SAXException, IOException, TransformerException, ParserConfigurationException {
		OwnDOMParser dom = new OwnDOMParser();
		dom.parse(path);
		List<Knife> domKnives = dom.getKnives();

		Collections.sort(domKnives, new Comparator<Knife>() {

			@Override
			public int compare(Knife o1, Knife o2) {
				return o1.getModel().compareToIgnoreCase(o2.getModel());
			}
		});
		XMLCreater.create(domKnives, "dom.xml");
		print(domKnives);
	}

	/**
	 * SAX parser do work. Parse xml file make sorting by length and write it to new
	 * xml file.
	 * 
	 * @param path
	 *            file to parse
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	private static void saxRun(String path)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {
		OwnSAXParser sax = new OwnSAXParser();
		sax.parse(path);
		List<Knife> saxKnives = sax.getKnives();

		Collections.sort(saxKnives, new Comparator<Knife>() {

			@Override
			public int compare(Knife o1, Knife o2) {
				return o1.getVisuals().getLength() - o2.getVisuals().getLength();
			}
		});
		XMLCreater.create(saxKnives, "sax.xml");
		print(saxKnives);
	}

	/**
	 * StAX parser do work. Parse xml file make sorting by width and write it to new
	 * xml file.
	 * 
	 * @param path
	 *            file to parse
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XMLStreamException
	 * @throws TransformerException
	 */
	private static void staxTest(String path)
			throws IOException, ParserConfigurationException, XMLStreamException, TransformerException {
		OwnStAXParser stax = new OwnStAXParser();
		stax.parse(path);
		List<Knife> staxKnives = stax.getKnives();

		Collections.sort(staxKnives, new Comparator<Knife>() {

			@Override
			public int compare(Knife o1, Knife o2) {
				return o1.getVisuals().getWidth() - o2.getVisuals().getWidth();
			}
		});
		XMLCreater.create(staxKnives, "stax.xml");
		print(staxKnives);
	}

	/**
	 * Print list of knives to console.
	 * 
	 * @param list
	 *            knife list
	 */
	private static void print(List<Knife> list) {
		for (Knife knife : list) {
			System.out.println(knife);
		}
	}

}
