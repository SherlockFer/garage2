CREATE TABLE bookings_parts (
	booking_id int8 NOT NULL,
	parts_id int8 NOT NULL,
	CONSTRAINT bookings_parts_pkey PRIMARY KEY (booking_id, parts_id)
);

ALTER TABLE bookings_parts ADD CONSTRAINT fk39k8n6b758fm0hx410ryeh699 FOREIGN KEY (parts_id) REFERENCES parts(id);
ALTER TABLE bookings_parts ADD CONSTRAINT fkdp1b30qn0958qhs36fg1noxid FOREIGN KEY (booking_id) REFERENCES bookings(id);