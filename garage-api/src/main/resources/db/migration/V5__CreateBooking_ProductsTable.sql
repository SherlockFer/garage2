CREATE TABLE booking_products (
	booking_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT booking_products_pkey PRIMARY KEY (booking_id, product_id)
);

ALTER TABLE booking_products ADD CONSTRAINT fk61i0xq10qbqmdoopgop57xyd9 FOREIGN KEY (product_id) REFERENCES products(id);
ALTER TABLE booking_products ADD CONSTRAINT fkihcgeg3ds5wtdcgu3eqq446qo FOREIGN KEY (booking_id) REFERENCES bookings(id);
