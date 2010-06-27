package com.gerixsoft.utils;

import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class DelegatingXMLReader implements XMLReader {
	protected XMLReader reader;

	public DelegatingXMLReader(XMLReader reader) {
		this.reader = reader;
	}

	public ContentHandler getContentHandler() {
		return reader.getContentHandler();
	}

	public DTDHandler getDTDHandler() {
		return reader.getDTDHandler();
	}

	public EntityResolver getEntityResolver() {
		return reader.getEntityResolver();
	}

	public ErrorHandler getErrorHandler() {
		return reader.getErrorHandler();
	}

	public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		return reader.getFeature(name);
	}

	public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
		return reader.getProperty(name);
	}

	public void parse(InputSource input) throws IOException, SAXException {
		reader.parse(input);
	}

	public void parse(String systemId) throws IOException, SAXException {
		reader.parse(systemId);
	}

	public void setContentHandler(ContentHandler handler) {
		reader.setContentHandler(handler);
	}

	public void setDTDHandler(DTDHandler handler) {
		reader.setDTDHandler(handler);
	}

	public void setEntityResolver(EntityResolver resolver) {
		reader.setEntityResolver(resolver);
	}

	public void setErrorHandler(ErrorHandler handler) {
		reader.setErrorHandler(handler);
	}

	public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
		reader.setFeature(name, value);
	}

	public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
		reader.setProperty(name, value);
	}
}