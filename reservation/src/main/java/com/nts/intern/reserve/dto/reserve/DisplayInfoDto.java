package com.nts.intern.reserve.dto.reserve;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class DisplayInfoDto {
	private int categoryId;
	private String categoryName;
	
	private int displayInfoId;
	private String email;
	private String homepage;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String createDate;
	private String modifyDate;
	private String telephone;
	
	private String productContent;
	private String productDescription;
	private String productEvent;
	private int productId;
}
