CREATE TABLE bookings (
	id int8 NOT NULL,
	"comments" varchar(255) NULL,
	country_code varchar(255) NULL,
	created_at timestamp NULL,
	"date" date NOT NULL,
	ip_city varchar(255) NULL,
	ip_country varchar(255) NULL,
	modified_at timestamp NULL,
	reference varchar(255) NOT NULL,
	status varchar(255) NULL,
	total float8 NULL,
	vat_number varchar(255) NULL,
	vehicle_brand varchar(255) NULL,
	vehicle_engine varchar(255) NULL,
	vehicle_model varchar(255) NULL,
	vehicle_number_plate varchar(255) NOT NULL,
	vehicle_type varchar(255) NOT NULL,
	product_id int8 NOT NULL,
	customer_id int8 NULL,
	mechanic_id int8 NULL,
	CONSTRAINT bookings_pkey PRIMARY KEY (id),
	CONSTRAINT uk_pki3ddx1043pwavq8ijbqcxkw UNIQUE (reference)
);

ALTER TABLE bookings ADD CONSTRAINT fk624671ymniidpsqki9p6patf7 FOREIGN KEY (mechanic_id) REFERENCES users(id);
ALTER TABLE bookings ADD CONSTRAINT fkib6gjgj2e9binkktxmm175bmm FOREIGN KEY (customer_id) REFERENCES users(id);
ALTER TABLE bookings ADD CONSTRAINT fklmdmerb98p3rhxcmvc9iunj2d FOREIGN KEY (product_id) REFERENCES products(id);

CREATE SEQUENCE booking_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;