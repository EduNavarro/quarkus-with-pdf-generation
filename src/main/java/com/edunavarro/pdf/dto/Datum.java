package com.edunavarro.pdf.dto;

import com.edunavarro.pdf.event.DetailType;
import com.edunavarro.pdf.event.StatusDatum;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Datum {
	private String detailName;
	private Integer quantity;
	private DetailType type;
	private StatusDatum status;

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public DetailType getType() {
		return type;
	}

	public void setType(DetailType type) {
		this.type = type;
	}

	public StatusDatum getStatus() {
		return status;
	}

	public void setStatus(StatusDatum status) {
		this.status = status;
	}
}
