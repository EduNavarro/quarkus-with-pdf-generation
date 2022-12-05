package com.edunavarro.pdf.service;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class BaseService {

	@ConfigProperty(name = "edunavarro.folder.path")
	String path;

	static final PDType1Font TITLE_FONT = PDType1Font.COURIER;
    static final int TITLE_MARGIN = 500;
    static final int TITLE_SIZE = 16;
	
    static final PDType1Font TEXT_FONT = PDType1Font.HELVETICA;
    static final int TEXT_SIZE = 12;
    static final int TEXT_MARGIN = 50;
    
    private static final String SEPARATOR = "/";
    private static final String FILE_TYPE = ".pdf";

    protected String getFilePath(String documentId) {
    	return SEPARATOR+documentId+FILE_TYPE;
    }
}
