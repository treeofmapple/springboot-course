package com.tom.sample.example.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends CustomGlobalException {

	private final String msg;
	
}
