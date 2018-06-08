package ua.khpi.markevich.SummaryTask3.parsers;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.khpi.markevich.SummaryTask3.model.Knife;
import ua.khpi.markevich.SummaryTask3.model.Visuals;

/**
 * DOM Parser to parse xml which response input.xsd.
 * 
 * @author Arthur Markevich
 *
 */
public class OwnDOMParser {

	/**
	 * Resulting list of knives.
	 */
	private LinkedList<Knife> knives;

	private Knife knife;

	private Visuals visuals;

	public OwnDOMParser() {
		knives = new LinkedList<>();
	}

	public LinkedList<Knife> getKnives() {
		return knives;
	}

	/**
	 * Obtain objects from xml file and put them to list.
	 * 
	 * @param path
	 *            xml file to parse
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public void parse(String path) throws SAXException, IOException, ParserConfigurationException {
		Document document = getDocument(path);
		NodeList nodeList = document.getElementsByTagName("knife");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			knife = new Knife();
			knife.setHandy(((Element) node).getAttribute("handy"));
			knife.setValue(Boolean.parseBoolean(((Element) node).getAttribute("value")));

			NodeList childNodes = node.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				Node child = childNodes.item(j);
				if (child.getNodeType() == Node.ELEMENT_NODE) {

					String tag = child.getNodeName();
					switch (tag) {
					case "model":
						knife.setModel(child.getTextContent());

					case "type":
						knife.setType(child.getTextContent());
						break;

					case "origin":
						knife.setOrigin(child.getTextContent());
						break;

					case "visuals":
						visuals = new Visuals();
						NodeList visualNodes = child.getChildNodes();
						for (int k = 0; k < visualNodes.getLength(); k++) {

							Node visualNode = visualNodes.item(k);
							if (visualNode.getNodeType() == Node.ELEMENT_NODE) {
								String visualTag = visualNode.getNodeName();
								switch (visualTag) {
								case "#text":
									System.out.println(visualTag + " " + k + " k");
									break;
								case "bloodsucker":
									visuals.setBloodsucker(Boolean.parseBoolean(visualNode.getTextContent()));
									break;

								case "blade":
									visuals.setBladeMaterial(visualNode.getTextContent());
									break;

								case "length":
									visuals.setLength(Integer.parseInt(visualNode.getTextContent()));
									break;

								case "width":
									visuals.setWidth(Integer.parseInt(visualNode.getTextContent()));
									break;

								case "handle":
									NodeList handleNodes = visualNode.getChildNodes();
									for (int l = 0; l < handleNodes.getLength(); l++) {
										Node handleChild = handleNodes.item(l);
										if (handleChild.getNodeType() == Node.ELEMENT_NODE) {
											if (handleChild.getNodeName().equals("wood")) {
												visuals.setWoodenHandler(true);
												String woodMaterial = ((Element) handleChild).getAttribute("material");
												visuals.setHandleMaterial(woodMaterial);
											} else {
												visuals.setWoodenHandler(false);
												visuals.setHandleMaterial(handleChild.getNodeName());
											}
										}
									}

									break;

								default:
									visuals = null;
									throw new IllegalArgumentException(visualTag);
								}
							}
						}
						break;

					default:
						knife = null;
						throw new IllegalArgumentException(tag);
					}
				}
			}
			knife.setVisuals(visuals);
			getKnives().add(knife);
		}
	}

	/**
	 * Convert xml file to Document.
	 * 
	 * @param path
	 *            xml file location
	 * @return converted document
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document getDocument(String path) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document document = builder.parse(new File(path));
		// document.getDocumentElement().normalize();
		return document;
	}

}
