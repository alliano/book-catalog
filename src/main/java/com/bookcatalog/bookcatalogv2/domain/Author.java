package com.bookcatalog.bookcatalogv2.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

// import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @DynamicUpdate
 * dinamic update adalah annotasi sepesifik milik dari hibernate bukan dari JPA
 * => performaapplicasi akan mengigkat jika kolom pada entity sangat banyak karna dengan menambahkan annotasi @DynamicUpdate
 * hibernate akan meng update field yang berubah
 * => dengan mengaktifkan @DynamicUpdate juga berimpec kepada applikasi jika kolomnya sedikit karna ketika @DynamicUpdate ini diaktifkan
 * maka Hibernate kana mematikan Caching yang ada pada internalnya sehingga Hibernate harus melihat terlebih dahulu kedalam database
 * untuk melihat data Existing nya .
 *
 * Note : jikalu kolomnya dikit matikan saja @DynamicUpdate dan juga sebaliknya
 */
@Entity
@SQLDelete(sql = "UPDATE author SET deleted = true WHERE author.id = ?")
@Where(clause = "deleted = false")
@Table(name = "author") //@DynamicUpdate
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Author extends AbstracBaseEntity {

	/**
	 * strategy
	 * IDENTITY -> tidak ada fitur bach insert jadi jikalau ada ribuan daata yang akan di insert maka proses
	 * round treep nya ribuan juga ini karna yang memantanance si DAtabse nya bukan hobernate nya
	 * SEQUENCE -> bisa meng enable fitur bach issert milik hibernate jadi data relativ cepat karna
	 * jikalau ada ribuan data yg akan di insert hanya 1x round treep karna yang memantanace Id nya Hibernate
	 */
	@Id @SequenceGenerator(name = "author_generator", sequenceName = "author_id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
	private long id;

	@Column(nullable = false, unique = false, length = 30)
	private String name;

	@Column(nullable = false)
	private LocalDate birthDate;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL) // => ALL artinay DETECT, MERGE, PERSIST, REFRESH, REMOVE
	private java.util.List<Address> addresses;
}
