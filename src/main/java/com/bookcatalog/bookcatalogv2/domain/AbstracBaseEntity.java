package com.bookcatalog.bookcatalogv2.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Table(indexes = {
	@Index(name = "uk_secure_id", columnList = "secureId")
})@Setter @Getter
public abstract class AbstracBaseEntity implements Serializable {

	@Column(nullable = false, unique = true, name = "secureId")
	private String secureId = UUID.randomUUID().toString();

	@Column(name = "deleted", columnDefinition = "boolean default false")
	private boolean deleted = false;
}
