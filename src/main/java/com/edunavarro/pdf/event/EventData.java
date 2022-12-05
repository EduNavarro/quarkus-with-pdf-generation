package com.edunavarro.pdf.event;

import java.time.Instant;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class EventData {
	private String documentId;
	private Instant creationDate;
	private List<EventDatum> dataList;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public List<EventDatum> getDataList() {
		return dataList;
	}

	public void setDataList(List<EventDatum> dataList) {
		this.dataList = dataList;
	}

}
