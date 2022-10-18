package com.bookcatalog.bookcatalogv2.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity @Table( name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = -137493267643246823L;

	@Id
	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = true)
	private String description;

	@ManyToMany(mappedBy = "categories")
	private List<Book> books;
}
