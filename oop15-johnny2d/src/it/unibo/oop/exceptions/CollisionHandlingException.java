package it.unibo.oop.exceptions;

public class CollisionHandlingException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public CollisionHandlingException() {
		super();
	}
	public CollisionHandlingException (final String message) {
		  super(message); 
	}

}
