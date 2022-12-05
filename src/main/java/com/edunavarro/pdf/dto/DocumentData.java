package com.edunavarro.pdf.dto;

import java.time.Instant;
import java.util.List;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class DocumentData {
	private String documentId;
	private Instant creationDate;
	private List<Datum> dataList;

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

	public List<Datum> getDataList() {
		return dataList;
	}

	public void setDataList(List<Datum> dataList) {
		this.dataList = dataList;
	}

}
