package com.nts.intern.reserve.dto.reserve;

public enum PriceTypeMap {	
	Y("청소년"),
	A("성인"),
	B("유아"),
	D("장애인"),
	E("얼리버드"),
	V("VIP석"),
	R("R석"),
	S("S석");
	
	private String actualName;
	
	PriceTypeMap(String actualName){
		this.actualName = actualName;
	}	
	
	public String getActualName() {
		return actualName;
	}
}
