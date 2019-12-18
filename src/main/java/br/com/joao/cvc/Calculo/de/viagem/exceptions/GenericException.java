package br.com.joao.cvc.Calculo.de.viagem.exceptions;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException{

	private static final long serialVersionUID = 6937733370581079446L;

	private HttpStatus httpCode; 
	private String mensagemUsuario; 
	private String mensagemTecnica;
	
	public GenericException(HttpStatus httpCode, String mensagemUsuario, String mensagemTecnica) {
		this.httpCode = httpCode;
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemTecnica = mensagemTecnica;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpStatus httpCode) {
		this.httpCode = httpCode;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public String getMensagemTecnica() {
		return mensagemTecnica;
	}

	public void setMensagemTecnica(String mensagemTecnica) {
		this.mensagemTecnica = mensagemTecnica;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
