package br.com.joao.cvc.Calculo.de.viagem.proxy;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.joao.cvc.Calculo.de.viagem.exceptions.GenericException;
import br.com.joao.cvc.Calculo.de.viagem.proxy.response.CidadeResponse;

@Service
public class PesquisHotelProxy {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${cvcbackend.hoteis.cidade}")
	private String hoteisCidadeURL;
	
	@Value("${cvcbackend.hoteis.detalhe}")
	private String hotelDetalheURL;
	
	public List<CidadeResponse> pesquisarPorCidade(int idCidade) {
		try {
			ResponseEntity<CidadeResponse[]> cidades = restTemplate.getForEntity(hoteisCidadeURL + idCidade, CidadeResponse[].class);
			return Arrays.asList(cidades.getBody());			
		}catch(Exception e) {
			throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inexperado, por favor, tente novamente.", e.getLocalizedMessage());
		}

	}
	
	public List<CidadeResponse> pesquisarPorHotel(int idHotel) {
		try {
			ResponseEntity<CidadeResponse[]> cidades = restTemplate.getForEntity(hotelDetalheURL + idHotel, CidadeResponse[].class);
			return Arrays.asList(cidades.getBody());
		}catch(Exception e) {
			throw new GenericException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inexperado, por favor, tente novamente.", e.getLocalizedMessage());
		}
	}

}
