package com.edunavarro.pdf.service;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import com.edunavarro.pdf.event.EventData;
import com.edunavarro.pdf.event.EventDatum;

import io.quarkus.logging.Log;

@ApplicationScoped
public class PdfService extends BaseService {
	        
	private static final String HEADER_TEXT = "Issuing document ID - ";

	/**
	 * Creates a PDF document using the information of 
	 * an EventData payload.
	 * 
	 * It creates a header and a single page with the contents
	 * and saves it into the configured path.
	 * 
	 * @param payload
	 * @return
	 */
	public boolean createPdfDocument(final EventData payload) {

		boolean isOk = false;
		
		try (PDDocument document = new PDDocument()) {

			addHeaderPage(document, payload);

			addContentPages(document, payload);
			
			document.save(composeFullPath(payload));
			
			isOk=true;

		} catch (IOException e) {
			Log.errorf("Error writing pdf document %s", e);
		}

		return isOk;
	}

	private void addHeaderPage(PDDocument document, final EventData payload) throws IOException {
		PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);

		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		String headerString = new StringBuilder(HEADER_TEXT).append(payload.getDocumentId()).toString();

        PDRectangle mediaBox = page.getMediaBox();
		contentStream.setFont(TITLE_FONT, TITLE_SIZE);
		contentStream.beginText();
		contentStream.newLineAtOffset(getPositionX(headerString, TITLE_FONT, mediaBox),getPositionY(TITLE_FONT, mediaBox, TITLE_MARGIN, TITLE_SIZE));
	    contentStream.setCharacterSpacing(0);

		contentStream.showText(headerString);
		contentStream.endText();
		contentStream.close();
	}

	
	private void addContentPages(PDDocument document, final EventData payload) throws IOException {
		PDPage page = new PDPage(PDRectangle.A4);
		document.addPage(page);
		
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		
		contentStream.setFont(TEXT_FONT, TEXT_SIZE);
        PDRectangle mediaBox = page.getMediaBox();

        int lineNumber = 0;
        
		for (EventDatum datum : payload.getDataList()) {
			contentStream.beginText();
			String content = extractContent(datum);
			contentStream.newLineAtOffset(getPositionX(content, TEXT_FONT, mediaBox),getPositionY(TEXT_FONT, mediaBox, TEXT_MARGIN, TEXT_SIZE)+lineNumber);
			contentStream.showText(content);
			contentStream.endText();
			
			lineNumber+=TEXT_SIZE*2;
		}
		contentStream.close();
	}

	private float getPositionY(PDFont font, PDRectangle mediaBox, int marginTop, int fontSize) {
		float titleHeight = font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
        float startY = mediaBox.getHeight() - marginTop - titleHeight;
		return startY;
	}

	private float getPositionX(String headerString, PDFont font, PDRectangle mediaBox) throws IOException {
		float titleWidth = font.getStringWidth(headerString.toString()) / 1000 * 16;
        float startX = (mediaBox.getWidth() - titleWidth) / 2;
		return startX;
	}

	private String extractContent(EventDatum datum) {
		StringBuilder contentString = new StringBuilder("NAME: ").append(datum.getDetailName()).append("   |   ").append("STATUS: ").append(datum.getStatus()).append("   |   ").append("QUANTITY: ").append(datum.getQuantity());
		return contentString.toString();
	}
	
    private String composeFullPath(final EventData payload) {
        return path+getFilePath(payload.getDocumentId());
    }
}
