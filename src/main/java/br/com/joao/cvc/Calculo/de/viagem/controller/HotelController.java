package br.com.joao.cvc.Calculo.de.viagem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.cvc.Calculo.de.viagem.proxy.PesquisHotelProxy;
import br.com.joao.cvc.Calculo.de.viagem.proxy.response.CidadeResponse;

//@RestController
public class HotelController {
	
	@Autowired
	private PesquisHotelProxy cidadesProxy;


	@GetMapping("/cidades/{idCidade}")
	public ResponseEntity<?> pesquisarHoteisPorCidade(@PathVariable int idCidade) {
		List<CidadeResponse> pesquisarCidadePorId = cidadesProxy.pesquisarPorCidade(idCidade);
		return ResponseEntity.ok(pesquisarCidadePorId);
	}

	@GetMapping("/detalhe/{idHotel}")
	public ResponseEntity<?> pesquisarPorHotel(@PathVariable int idHotel) {
		List<CidadeResponse> pesquisarPorHotel = cidadesProxy.pesquisarPorHotel(idHotel);
		return ResponseEntity.ok(pesquisarPorHotel);
	}
	
}
