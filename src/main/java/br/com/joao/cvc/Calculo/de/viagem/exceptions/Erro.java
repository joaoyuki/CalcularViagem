package br.com.joao.cvc.Calculo.de.viagem.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class Erro {

	 private Date data;
	    private String mensagem;
	    private String mensagemDetalhada;
	    private HttpStatus httpStatus;    

	    public Date getData() {
	        return data;
	    }

	    public void setData(Date data) {
	        this.data = data;
	    }

	    public String getMensagem() {
	        return mensagem;
	    }

	    public void setMensagem(String mensagem) {
	        this.mensagem = mensagem;
	    }

	    public String getMensagemDetalhada() {
	        return mensagemDetalhada;
	    }

	    public void setMensagemDetalhada(String mensagemDetalhada) {
	        this.mensagemDetalhada = mensagemDetalhada;
	    }

	    public HttpStatus getHttpStatus() {
	        return httpStatus;
	    }

	    public void setHttpStatus(HttpStatus httpStatus) {
	        this.httpStatus = httpStatus;
	    }

	    public Erro() {
	        //Construtor padrão
	    }

	    public Erro(Date data, String mensagem, String mensagemDetalhada, HttpStatus httpStatus) {
	        this.data = data;
	        this.mensagem = mensagem;
	        this.mensagemDetalhada = mensagemDetalhada;
	        this.httpStatus = httpStatus;
	    }	
	
}
