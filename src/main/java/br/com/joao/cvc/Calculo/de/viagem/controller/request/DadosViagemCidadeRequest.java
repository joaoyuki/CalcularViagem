package br.com.joao.cvc.Calculo.de.viagem.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class DadosViagemCidadeRequest extends DadosViagemRequest{
	
	private Integer cityCode;
	
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}	
	
}
