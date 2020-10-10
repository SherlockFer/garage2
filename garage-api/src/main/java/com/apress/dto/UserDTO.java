package com.apress.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

public class UserDTO extends BaseDTO {

	private Long id;

	@JsonProperty("full_name")
	private String fullName;

	@JsonProperty("mobile")
	private String mobile;

	@JsonProperty("email")
	private String email;

	@JsonProperty("role")
	private String role;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	public UserDTO(long id) {
	    this.id = id;
	}

}
