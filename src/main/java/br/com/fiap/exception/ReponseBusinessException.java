package br.com.fiap.exception;

public class ReponseBusinessException extends Exception {
	private String message;

	public ReponseBusinessException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
