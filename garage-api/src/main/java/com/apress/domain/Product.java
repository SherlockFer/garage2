package com.apress.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
	@SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@NaturalId(mutable = false)
	@EqualsAndHashCode.Include
	@Column(name = "REFERENCE", nullable = false, updatable = false, unique = true)
	private String reference;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "PRICE")
	private Integer price;

	@Column(name = "CREATED_AT", insertable = true, updatable = false)
	private LocalDateTime created;

	@Column(name = "MODIFIED_AT")
	private LocalDateTime modified;

	@PrePersist
	void onCreate() {
		this.setCreated(LocalDateTime.now());
		this.setModified(LocalDateTime.now());
	}

	@PreUpdate
	void onUpdate() {
		this.setModified(LocalDateTime.now());
	}

}
