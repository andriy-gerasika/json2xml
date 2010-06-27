package com.gerixsoft.utils;

import java.io.IOException;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;

import org.xml.sax.SAXException;

import com.gerixsoft.xslt.XsltSAXSource;
import com.gerixsoft.xslt.XsltURIResolver;

public final class XsltHelper {

	public static Transformer newTransformer(URL url) throws TransformerConfigurationException, SAXException, IOException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		// there could be not null resolver
		URIResolver resolver = transformerFactory.getURIResolver();
		transformerFactory.setURIResolver(new XsltURIResolver(resolver, url));

		// create a transformer
		Transformer result = transformerFactory.newTransformer(new XsltSAXSource(url.openStream(), null));

		// reset to old resolver for document() function
		result.setURIResolver(resolver);
		return result;
	}

}
