package com.bookcatalog.bookcatalogv2.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter @Table(name = "publisher")
public class Publisher extends AbstracBaseEntity {

	private static final long serialVersionUID = -1938834457347387628L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_generator")
	@SequenceGenerator(name = "publisher_generator", sequenceName = "publisher_id_sequence")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "address", nullable = false)
	private String address;

	@OneToMany(mappedBy = "publisher")
	private List<Book> books;
}
