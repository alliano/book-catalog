package com.bookcatalog.bookcatalogv2.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "book")
@Setter @Getter
public class Book extends AbstracBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "title")
	private String title;

	@Column(name = "description", nullable = true, columnDefinition = "TEXT")
	private String descriptions;

	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;

	/**
	 * joinColumns => reference ke id tabelnya sendiri
	 * inverseJoinColumns => reference ke id tabel lain sesuai dengan relasinya
	 */
	@ManyToMany()
	@JoinTable(name = "book_author", joinColumns = {
		@JoinColumn(name = "book_id", referencedColumnName = "id")},
		inverseJoinColumns = { @JoinColumn(name = "author_id", referencedColumnName = "id")})
	private List<Author> authors;

	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = {
		@JoinColumn(name = "book_id", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name = "category_code", referencedColumnName = "code")})
	private List<Category> categories;
}
