package project_salesforce;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XMLWriteClass {

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	Element root;

	public XMLWriteClass() throws ParserConfigurationException {
		dbFactory = DocumentBuilderFactory.newInstance();
		dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();
	}

	public Element createRootNode(String rootnode) {
		root = doc.createElement(rootnode);
		doc.appendChild(root);

		return root;
	}

	public void createElementNode(Element parentNode, String ElementName,
			String textname) {
		Element tex = doc.createElement(ElementName);
		Text text = doc.createTextNode(textname);
		tex.appendChild(text);
		parentNode.appendChild(tex);
	}

	public Element createAttributeNode(Element parentNode, String ElementName,
			String attributeName, String attributeValue) {
		Element tc = doc.createElement(ElementName);
		tc.setAttribute(attributeName, attributeValue);
		parentNode.appendChild(tc);
		return tc;
	}

	public void createNode(String AttriElementName, String attritextname,
			String attributeValue, String ElementName, String textname) {
		Element Ele = createAttributeNode(root, AttriElementName,
				attritextname, attributeValue);
		createElementNode(Ele, ElementName, textname);
	}

	public void writeDoc(String SaveLoc) throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(SaveLoc));
		transformer.transform(source, result);
		// System.out.println("File Created");
	}

}
