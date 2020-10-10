package com.apress.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
import lombok.Singular;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "BOOKINGS")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_seq")
	@SequenceGenerator(name = "booking_id_seq", sequenceName = "booking_id_seq", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@NaturalId(mutable = false)
	@EqualsAndHashCode.Include
	@Column(name = "REFERENCE", nullable = false, updatable = false, unique = true)
	private String reference;

	@Column(name = "VEHICLE_NUMBER_PLATE", nullable = false)
	private String vehicleNumberPlate;

	@Column(name = "VEHICLE_MODEL")
	private String vehicleModel;

	@Column(name = "VEHICLE_BRAND")
	private String vehicleBrand;

	@Column(name = "VEHICLE_ENGINE")
	private String vehicleEngine;

	@Column(name = "VEHICLE_TYPE", nullable = false)
	private String vehicleType;

	@Column(name = "DATE", nullable = false)
	private LocalDate date;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TOTAL")
	private Double total;

	@Singular(ignoreNullCollections = true)
	@ManyToMany
	@JoinTable(name = "BOOKING_PRODUCTS", joinColumns = @JoinColumn(name = "BOOKING_ID"), inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
	private Set<Product> extraProducts;

	@Singular(ignoreNullCollections = true)
	@ManyToMany
	@JoinColumn(name = "PART_ID")
	private Set<Part> parts;

	@OneToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	private Product baseProduct;

	@ManyToOne
	@JoinColumn(name = "MECHANIC_ID")
	private User mechanic;

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	private User customer;

	@Column(name = "CREATED_AT", insertable = true, updatable = false)
	private LocalDateTime created;

	@Column(name = "MODIFIED_AT")
	private LocalDateTime modified;

	@Column(name = "IP_COUNTRY")
	private String ipCountry;

	@Column(name = "IP_CITY")
	private String ipCity;

	@PrePersist
	void onCreate() {
		this.setCreated(LocalDateTime.now());
		this.setModified(LocalDateTime.now());
	}

	@PreUpdate
	void onUpdate() {
		this.setModified(LocalDateTime.now());
	}

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "VAT_NUMBER")
	private String vatNumber;

}