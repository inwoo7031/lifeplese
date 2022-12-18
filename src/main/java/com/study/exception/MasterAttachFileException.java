package com.study.exception;
@SuppressWarnings("serial")
public class MasterAttachFileException extends RuntimeException{
	public MasterAttachFileException(String message) {
		super(message);
	}
	
	
	public MasterAttachFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
