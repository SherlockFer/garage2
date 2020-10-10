package com.apress.defaulter;

import org.springframework.stereotype.Component;

import com.apress.constants.Constants.UserRole;
import com.apress.dto.UserDTO;

@Component
public class UserDefaulter {

	public void populateDefaults(UserDTO userDTO) {
		populateRole(userDTO);
	}

	public void populateRole(UserDTO userDTO) {
		if (userDTO.getRole() == null) {
			userDTO.setRole(UserRole.customer.name());
		}
	}

}
