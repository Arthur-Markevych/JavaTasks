package ua.khpi.markevich.SummaryTask3.creater;

import java.io.File;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ua.khpi.markevich.SummaryTask3.model.Knife;

public class XMLCreater {

	/**
	 * XML name spaces.
	 */
	private final static String XMLNS = "http://www.w3.org/2001/XMLSchema-instance";

	/**
	 * XSD schema location.
	 */
	private final static String SCHEMA_LOCATION = "input.xsd";

	/**
	 * Write new xml document out of knives list.
	 * 
	 * @param list
	 *            use to convert to xml
	 * @param output
	 *            location of resulting xml file
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static void create(List<Knife> list, String output)
			throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element rootElement = doc.createElement("knives");
		rootElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xsi", XMLNS);
		rootElement.setAttribute("xsi:noNamespaceSchemaLocation", SCHEMA_LOCATION);
		doc.appendChild(rootElement);
		for (Knife knife : list) {

			Element knifeEl = doc.createElement("knife");
			rootElement.appendChild(knifeEl);
			Attr handyAttr = doc.createAttribute("handy");
			Attr valueAttr = doc.createAttribute("value");
			handyAttr.setValue(knife.getHandy());
			valueAttr.setValue(String.valueOf(knife.isValue()));
			knifeEl.setAttributeNode(handyAttr);
			knifeEl.setAttributeNode(valueAttr);

			Element model = doc.createElement("model");
			model.appendChild(doc.createTextNode(knife.getModel()));
			knifeEl.appendChild(model);

			Element type = doc.createElement("type");
			type.appendChild(doc.createTextNode(knife.getType()));
			knifeEl.appendChild(type);

			Element origin = doc.createElement("origin");
			origin.appendChild(doc.createTextNode(knife.getOrigin()));
			knifeEl.appendChild(origin);

			// visuals
			Element visuals = doc.createElement("visuals");
			knifeEl.appendChild(visuals);

			Element blade = doc.createElement("blade");
			blade.appendChild(doc.createTextNode(knife.getVisuals().getBladeMaterial()));
			visuals.appendChild(blade);

			Element length = doc.createElement("length");
			length.appendChild(doc.createTextNode(String.valueOf(knife.getVisuals().getLength())));
			visuals.appendChild(length);

			Element width = doc.createElement("width");
			width.appendChild(doc.createTextNode(String.valueOf(knife.getVisuals().getWidth())));
			visuals.appendChild(width);

			// handle
			Element handle = doc.createElement("handle");
			visuals.appendChild(handle);

			Element handleMaterial = null;

			if (knife.getVisuals().isWoodenHandler()) {
				handleMaterial = doc.createElement("wood");
				Attr woodMaterial = doc.createAttribute("material");
				woodMaterial.setValue(knife.getVisuals().getHandleMaterial());
				handleMaterial.setAttributeNode(woodMaterial);
			} else {
				handleMaterial = doc.createElement(knife.getVisuals().getHandleMaterial());
			}
			handle.appendChild(handleMaterial);
			// -- --

			Element bloodsucker = doc.createElement("bloodsucker");
			bloodsucker.appendChild(doc.createTextNode(String.valueOf(knife.getVisuals().isBloodsucker())));
			visuals.appendChild(bloodsucker);
		}

		// Write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/ua/khpi/markevich/SummaryTask3/xml/output." + output));
		transformer.transform(source, result);

	}

}
