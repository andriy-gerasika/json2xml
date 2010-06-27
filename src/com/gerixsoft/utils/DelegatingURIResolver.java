package com.gerixsoft.utils;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;

public class DelegatingURIResolver implements URIResolver {

	protected URIResolver resolver;

	public DelegatingURIResolver(URIResolver resolver) {
		this.resolver = resolver;
	}

	public Source resolve(String href, String base) throws TransformerException {
		return resolver.resolve(href, base);
	}
}
