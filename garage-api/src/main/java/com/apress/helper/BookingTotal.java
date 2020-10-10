package com.apress.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.domain.Part;
import com.apress.domain.Product;
import com.apress.dto.BookingDTO;
import com.apress.dto.PartDTO;
import com.apress.dto.ProductDTO;
import com.apress.mappers.BookingMapper;
import com.apress.repository.BookingRepository;
import com.apress.repository.PartRepository;
import com.apress.repository.ProductRepository;

@Component
public class BookingTotal {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private BookingMapper bookingMapper;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PartRepository partRepository;

	public void updateTotal(final BookingDTO bookingDTO) {
		double total = 0;

		if (bookingDTO.getBaseProduct() != null) {
			Optional<Product> product = productRepository.findById(bookingDTO.getBaseProduct().getId());
			total += product.get().getPrice();
		}
		if (bookingDTO.getExtraProducts() != null) {
			for (ProductDTO productDTO : bookingDTO.getExtraProducts()) {
				Optional<Product> product = productRepository.findById(productDTO.getId());
				total += product.get().getPrice();
			}
		}
		if (bookingDTO.getParts() != null) {
			for (PartDTO partDTO : bookingDTO.getParts()) {
				Optional<Part> part = partRepository.findById(partDTO.getId());
				total += part.get().getPrice();
			}
		}
		bookingDTO.setTotal(total);
	}

}
