package br.com.joao.cvc.Calculo.de.viagem.controller.request;


import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;


public class DadosViagemRequest {
	
	@ApiModelProperty(value = "Por favor utilizar o formato yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate checkin;
	
	@ApiModelProperty(value = "Por favor utilizar o formato yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate checkout;
	
	private Integer totalChilds;
	
	private Integer totalAdults;
	

	public LocalDate getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}
	public LocalDate getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	public Integer getTotalChilds() {
		return totalChilds;
	}
	public void setTotalChilds(Integer totalChilds) {
		this.totalChilds = totalChilds;
	}
	public Integer getTotalAdults() {
		return totalAdults;
	}
	public void setTotalAdults(Integer totalAdults) {
		this.totalAdults = totalAdults;
	}
	
}
