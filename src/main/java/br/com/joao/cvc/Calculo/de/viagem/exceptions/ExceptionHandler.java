package br.com.joao.cvc.Calculo.de.viagem.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@org.springframework.web.bind.annotation.ExceptionHandler( GenericException.class )
	public ResponseEntity<Erro> genericExceptionCatch(GenericException ex) {
		Erro erro = new Erro();
		erro.setData(new Date());
		erro.setMensagem(ex.getMensagemUsuario());
		erro.setMensagemDetalhada(ex.getMensagemTecnica());
		erro.setHttpStatus(ex.getHttpCode());
		return new ResponseEntity<Erro>(erro, ex.getHttpCode());
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler( Exception.class )
	public ResponseEntity<Erro> generalExceptionCatch(Exception ex) {
		Erro erro = new Erro();
		erro.setData(new Date());
		erro.setMensagem("Ocorreu um erro inexperado, caso persista, por favor entre em contato com o suporte.");
		erro.setMensagemDetalhada(ex.getLocalizedMessage());
		erro.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Erro>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
