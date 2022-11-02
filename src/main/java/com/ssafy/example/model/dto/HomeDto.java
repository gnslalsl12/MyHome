package com.ssafy.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HomeDto {
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String aptName;
	private String lat;
	private String lng;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int area;
	private int floor;
	
}
