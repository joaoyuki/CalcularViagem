package br.com.joao.cvc.Calculo.de.viagem.proxy.response;

import java.util.List;

public class CidadeResponse {

	private int id;
	private String name;
	private int cityCode;
	private String cityName;
	private List<Room> rooms;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
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
	@Override
	public String toString() {
		return "CidadeResponse [id=" + id + ", name=" + name + ", cityCode=" + cityCode + ", cityName=" + cityName
				+ ", rooms=" + rooms + "]";
	}
	
	
	public static class Room {
		private int roomID;
		private String categoryName;
		private Price price;
		public int getRoomId() {
			return roomID;
		}
		public void setRoomId(int roomId) {
			this.roomID = roomId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public Price getPrice() {
			return price;
		}
		public void setPrice(Price price) {
			this.price = price;
		}
	}

	public static class Price {
		private Double adult;
		private Double child;
		public Double getAdult() {
			return adult;
		}
		public void setAdult(Double adult) {
			this.adult = adult;
		}
		public Double getChild() {
			return child;
		}
		public void setChild(Double child) {
			this.child = child;
		}
		
	}	
}


