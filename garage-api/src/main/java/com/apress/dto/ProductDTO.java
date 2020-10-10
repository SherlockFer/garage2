package com.apress.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO extends BaseDTO {

	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("category")
	private String category;

	@JsonIgnore
	private String reference;

	@JsonProperty("price")
	private Integer price;
	
	public ProductDTO(long id) {
	    this.id = id;
	}

}
