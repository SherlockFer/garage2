package com.apress.validation;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.apress.constants.Constants;
import com.apress.dto.UserDTO;

@Component
public class UserValidator {

	public void validate(UserDTO userDTO) {
		validateEmail(userDTO);
		validateName(userDTO);
		validateMobile(userDTO);
		validatePassword(userDTO);
		validateRole(userDTO);
	}

	private void validateEmail(UserDTO userDTO) {
		if (StringUtils.isBlank(userDTO.getEmail())) {
			userDTO.addError("Email can't be empty");
		}
	}

	private void validateName(UserDTO userDTO) {
		if (StringUtils.isBlank(userDTO.getFullName())) {
			userDTO.addError("Name can't be empty");
		}
	}

	private void validateMobile(UserDTO userDTO) {
		if (StringUtils.isBlank(userDTO.getMobile())) {
			userDTO.addError("Mobile can't be empty");
		}
	}

	private void validatePassword(UserDTO userDTO) {
		if (StringUtils.isBlank(userDTO.getPassword()) && userDTO.getId()==null) {
			userDTO.addError("Password can't be empty");
		}
	}

	private void validateRole(UserDTO userDTO) {
		if (!EnumUtils.isValidEnum(Constants.UserRole.class, userDTO.getRole())) {
			userDTO.addError("Role value is not valid");
		}
	}

}
