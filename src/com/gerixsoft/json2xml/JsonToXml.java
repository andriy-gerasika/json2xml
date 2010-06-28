package com.gerixsoft.json2xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class JsonToXml {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		document.appendChild(document.createElement("json"));
		String json;
		{
			InputStream inputStream = new FileInputStream(new File("files/input.json"));
			try {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				try {
					byte[] buffer = new byte[65535];
					int bytesRead;
					while ((bytesRead = inputStream.read(buffer)) > 0) {
						outputStream.write(buffer, 0, bytesRead);
					}
					json = outputStream.toString();
				} finally {
					outputStream.close();
				}
			} finally {
				inputStream.close();
			}
		}
		document.getDocumentElement().appendChild(document.createTextNode(json));

		Transformer transformer = TransformerFactory.newInstance().newTransformer(new StreamSource(JsonToXml.class.getResource("main.xsl").toString()));
		transformer.transform(new DOMSource(document), new StreamResult(new File("files/output.xml")));

		SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = factory.newSchema(new StreamSource(JsonToXml.class.getResourceAsStream("json.xsd")));

		Validator validator = schema.newValidator();
		validator.setErrorHandler(new ErrorHandler() {
			@Override
			public void warning(SAXParseException exception) throws SAXException {
				log("warning", exception);
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				log("fatal error", exception);
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				log("error", exception);
			}

			public void log(String type, SAXParseException exception) {
				System.err.println(type + " at line: " + exception.getLineNumber() + " col:" + exception.getColumnNumber() + " message: "
						+ exception.getMessage());
			}
		});
		validator.validate(new StreamSource(new File("files/output.xml")));

		System.out.println("ok");
	}

}
