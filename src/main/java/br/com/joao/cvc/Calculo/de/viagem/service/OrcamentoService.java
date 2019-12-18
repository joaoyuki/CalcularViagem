package br.com.joao.cvc.Calculo.de.viagem.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.joao.cvc.Calculo.de.viagem.controller.request.DadosViagemCidadeRequest;
import br.com.joao.cvc.Calculo.de.viagem.controller.request.DadosViagemHotelRequest;
import br.com.joao.cvc.Calculo.de.viagem.controller.request.DadosViagemRequest;
import br.com.joao.cvc.Calculo.de.viagem.exceptions.GenericException;
import br.com.joao.cvc.Calculo.de.viagem.proxy.PesquisHotelProxy;
import br.com.joao.cvc.Calculo.de.viagem.proxy.response.CidadeResponse;
import br.com.joao.cvc.Calculo.de.viagem.service.response.OrcamentoResponse;

@Service
public class OrcamentoService {
	
	@Autowired
	private PesquisHotelProxy hotelProxy;

	public List<OrcamentoResponse> calcularOrcamentoPorDatasEPessoas(DadosViagemCidadeRequest dadosViagem) {
		
		validarRequest(dadosViagem);
		List<CidadeResponse> listaDeHoteisPorCidade = hotelProxy.pesquisarPorCidade(dadosViagem.getCityCode());
		long totalDiasViagem = calcularTotalDeDiasDaViagem(dadosViagem);
		List<OrcamentoResponse> orcamentoFinal = listaDeHoteisPorCidade.parallelStream().map(hoteis -> {
			return OrcamentoResponse.criarOrcamento(hoteis, totalDiasViagem, dadosViagem);
		}).collect(Collectors.toList());
		
		return orcamentoFinal;
		
	}

	public List<OrcamentoResponse> calcularOrcamentoPorDatasEHotel(@Valid DadosViagemHotelRequest dadosViagem) {

		validarRequest(dadosViagem);
		List<CidadeResponse> pesquisarPorHotel = hotelProxy.pesquisarPorHotel(dadosViagem.getHotelId());
		long totalDiasViagem = calcularTotalDeDiasDaViagem(dadosViagem);
		
		List<OrcamentoResponse> orcamentoFinal = pesquisarPorHotel.parallelStream().map(hoteis -> {
			return OrcamentoResponse.criarOrcamento(hoteis, totalDiasViagem, dadosViagem);
		}).collect(Collectors.toList());
		
		return orcamentoFinal;		
		
	}
	
	private long calcularTotalDeDiasDaViagem(DadosViagemRequest dadosViagem) {
		return ChronoUnit.DAYS.between(dadosViagem.getCheckin(), dadosViagem.getCheckout());
	}

	public void validarRequest(DadosViagemRequest dadosViagem) {
		
		if (ObjectUtils.isEmpty(dadosViagem.getCheckin()) || ObjectUtils.isEmpty(dadosViagem.getCheckout())) {
			throw new GenericException(HttpStatus.BAD_GATEWAY, "É obrigatório informar data de checkin e data de checkout", "");
		}
		
		if (dadosViagem.getCheckin().isAfter(dadosViagem.getCheckout())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, "A data de checkin não pode ser depois da data de checkout!", "A data de checkin não pode ser depois da data de checkout!");
		}
		
		if(ObjectUtils.isEmpty(dadosViagem.getTotalAdults()) || ObjectUtils.isEmpty(dadosViagem.getTotalChilds())) {
			throw new GenericException(HttpStatus.BAD_REQUEST, "É obrigatório informar o numero de adultos e crianças", "");
		}
		
		if (dadosViagem instanceof DadosViagemCidadeRequest) {
			DadosViagemCidadeRequest dadosCidade = (DadosViagemCidadeRequest) dadosViagem;
			if (ObjectUtils.isEmpty(dadosCidade.getCityCode())) {
				throw new GenericException(HttpStatus.BAD_REQUEST, "É obrigatório indicar código da cidade para pesquisa", "");
			}
		}
		
		if (dadosViagem instanceof DadosViagemHotelRequest) {
			DadosViagemHotelRequest dadosHotel = (DadosViagemHotelRequest) dadosViagem;
			if (ObjectUtils.isEmpty(dadosHotel.getHotelId())) {
				throw new GenericException(HttpStatus.BAD_REQUEST, "É obrigatório indicar o código do hotel para pesquisa", "");
			}
			 
		}
	}
	
}
