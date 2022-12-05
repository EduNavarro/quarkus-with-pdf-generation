package com.edunavarro.pdf.event;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public enum StatusDatum {
	OPEN,
	CLOSED,
	PHANTOM,
	IN_PROGRESS,
	AUDIT
}
