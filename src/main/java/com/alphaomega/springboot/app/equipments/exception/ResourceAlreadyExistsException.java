package com.alphaomega.springboot.app.equipments.exception;

public class ResourceAlreadyExistsException extends Exception {

	 

    public ResourceAlreadyExistsException() {

    }

 

    public ResourceAlreadyExistsException(String msg) {

        super(msg);

    }

}