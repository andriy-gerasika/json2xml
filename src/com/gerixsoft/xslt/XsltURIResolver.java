package com.gerixsoft.xslt;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.gerixsoft.utils.DelegatingURIResolver;

public class XsltURIResolver extends DelegatingURIResolver {

	private String base;

	public XsltURIResolver(URIResolver resolver, URL base) {
		super(resolver);
		this.base = base.toString();
	}

	public Source resolve(String href, String base) throws TransformerException {
		if ((base == null || base.length() == 0) && this.base != null) {
			base = this.base;
		}

		String args = null;
		int i = href.indexOf("?");
		if (i != -1) {
			args = href.substring(i + 1);
			href = href.substring(0, i);
		}

		if (resolver == null) {
			try {
				URI hrefUri = new URI(href);
				URI baseUri = new URI(base);
				URI resultUri = baseUri.resolve(hrefUri);
				return new XsltSAXSource(new InputSource(resultUri.toString()), args);
			} catch (URISyntaxException e) {
				throw new TransformerException(e);
			} catch (SAXException e) {
				throw new TransformerException(e);
			}
		} else {
			SAXSource result = (SAXSource) super.resolve(href, base);
			try {
				return new XsltSAXSource(result.getInputSource(), args);
			} catch (SAXException e) {
				throw new TransformerException(e);
			}
		}
	}

}
