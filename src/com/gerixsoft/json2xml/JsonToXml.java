package com.gerixsoft.json2xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.gerixsoft.utils.XsltHelper;

public class JsonToXml {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		document.appendChild(document.createElement("json"));
		String json = getContents(new File("files/input.json"));
		document.getDocumentElement().appendChild(document.createTextNode(json));

		Transformer transformer = XsltHelper.newTransformer(JsonToXml.class.getResource("main.xsl"));
		transformer.transform(new DOMSource(document), new StreamResult(new File("files/output.xml")));

		System.out.println("ok");
	}

	public static String getContents(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		try {
			String result = getContents(inputStream);
			return result;
		} finally {
			inputStream.close();
		}
	}

	public static String getContents(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			copyStream(inputStream, outputStream);
			String result = outputStream.toString();
			return result;
		} finally {
			outputStream.close();
		}
	}

	public static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
		byte[] buffer = new byte[65535];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, bytesRead);
		}
	}

}
