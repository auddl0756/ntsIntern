package com.nts.intern.reserve.dto.detail;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class DisplayInfoDto {
	private int categoryId;
	private int displayInfoId;
	private int productId;

	private String categoryName;
	private String email;
	private String homepage;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String telephone;

	private String productContent;
	private String productDescription;
	private String productEvent;

	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
