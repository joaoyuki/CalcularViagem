package br.com.joao.cvc.Calculo.de.viagem.service.response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import br.com.joao.cvc.Calculo.de.viagem.controller.request.DadosViagemRequest;
import br.com.joao.cvc.Calculo.de.viagem.proxy.response.CidadeResponse;

public class OrcamentoResponse {
	
	public static final String COMISSAO = "0.7";

	private int id;
	private String cityName;
	private List<Room> rooms;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
	public static OrcamentoResponse criarOrcamento(CidadeResponse cidade, long totalDiasViagem, DadosViagemRequest dadosViagem) {
		OrcamentoResponse novoOrcamento = new OrcamentoResponse();
		novoOrcamento.setId(cidade.getCityCode());
		novoOrcamento.setCityName(cidade.getCityName());	
		List<Room> quartos = cidade.getRooms().parallelStream().map(quarto -> {
			Room novoQuarto = new Room();
			novoQuarto.setRoomId(quarto.getRoomId());
			novoQuarto.setCategoryName(quarto.getCategoryName());
			PriceDetail novoDetalhe = criarPriceDetail(totalDiasViagem, quarto);
			novoQuarto.setPriceDetail(novoDetalhe);
			novoQuarto.setTotalPrice(calcularTotalPrice(novoDetalhe, dadosViagem));
			return novoQuarto; 
		}).collect(Collectors.toList());
		novoOrcamento.setRooms(quartos);
		return novoOrcamento;
	}
	
	private static BigDecimal calcularTotalPrice(PriceDetail novoDetalhe, DadosViagemRequest dadosViagem) {
		BigDecimal totalAdultos = new BigDecimal(0);
		BigDecimal totalCriancas = new BigDecimal(0);
		if (dadosViagem.getTotalAdults() > 0) {
			totalAdultos = novoDetalhe.getPricePerDayAdult().multiply(new BigDecimal(dadosViagem.getTotalAdults()));
		}
		
		if (dadosViagem.getTotalChilds() > 0) {
			totalCriancas = novoDetalhe.getPricePerDayChild().multiply(new BigDecimal(dadosViagem.getTotalChilds()));
		}
		
		return totalAdultos.add(totalCriancas);
	}
	
	private static PriceDetail criarPriceDetail(long totalDiasViagem,
			br.com.joao.cvc.Calculo.de.viagem.proxy.response.CidadeResponse.Room quarto) {
		PriceDetail novoDetalhe = new PriceDetail();
		novoDetalhe.setPricePerDayAdult(retornarValorComComissao(new BigDecimal(quarto.getPrice().getAdult())).multiply(new BigDecimal(totalDiasViagem)));	
		novoDetalhe.setPricePerDayChild(retornarValorComComissao(new BigDecimal(quarto.getPrice().getChild())).multiply(new BigDecimal(totalDiasViagem)));
		return novoDetalhe;
	}
	
	public static BigDecimal retornarValorComComissao(BigDecimal valor) {
		return valor.divide(new BigDecimal(COMISSAO), 2, RoundingMode.HALF_UP);
	}

}

class Room {
	private int roomId;
	private String categoryName;
	private BigDecimal totalPrice;
	private PriceDetail priceDetail;
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public PriceDetail getPriceDetail() {
		return priceDetail;
	}
	public void setPriceDetail(PriceDetail priceDetail) {
		this.priceDetail = priceDetail;
	}
	
}

class PriceDetail {
	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChild;
	
	public BigDecimal getPricePerDayAdult() {
		return pricePerDayAdult;
	}
	public void setPricePerDayAdult(BigDecimal pricePerDayAdult) {
		this.pricePerDayAdult = pricePerDayAdult;
	}
	public BigDecimal getPricePerDayChild() {
		return pricePerDayChild;
	}
	public void setPricePerDayChild(BigDecimal pricePerDayChild) {
		this.pricePerDayChild = pricePerDayChild;
	}
	
}
