package com.bookcatalog.bookcatalogv2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Entity @Table(name = "book_detail")
public class BookDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "settings")
	private String settings;

	@Column(name = "tumbnail")
	private String tumbnail;

	@OneToOne
	@JoinColumn(nullable = false, name = "book_id")
	private Book book;
}
