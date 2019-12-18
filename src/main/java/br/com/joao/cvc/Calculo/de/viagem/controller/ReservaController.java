package br.com.joao.cvc.Calculo.de.viagem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.cvc.Calculo.de.viagem.controller.request.DadosViagemCidadeRequest;
import br.com.joao.cvc.Calculo.de.viagem.controller.request.DadosViagemHotelRequest;
import br.com.joao.cvc.Calculo.de.viagem.service.OrcamentoService;
import br.com.joao.cvc.Calculo.de.viagem.service.response.OrcamentoResponse;

@RestController
public class ReservaController {
	
	@Autowired
	private OrcamentoService orcamentoService;

	@GetMapping("/orcamento/cidade")
	public ResponseEntity<List<OrcamentoResponse>> retornarPropostaViagem(DadosViagemCidadeRequest dadosViagem) {
		List<OrcamentoResponse> orcamentoFinal = orcamentoService.calcularOrcamentoPorDatasEPessoas(dadosViagem);
		return ResponseEntity.ok(orcamentoFinal);
	}
	
	@GetMapping("/orcamento/hotel")
	public ResponseEntity<?> retornarPropostaViagemPorHotel(DadosViagemHotelRequest dadosViagem) {
		List<OrcamentoResponse> orcamentoFinal = orcamentoService.calcularOrcamentoPorDatasEHotel(dadosViagem);
		return ResponseEntity.ok(orcamentoFinal);
	}
	
}
