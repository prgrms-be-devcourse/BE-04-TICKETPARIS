package com.programmers.ticketparis.domain.performance;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hall {

	private Long hallId;
	private String name;
	private String address;
	private Integer seatsCount;
	private LocalDateTime createdDatetime;
	private LocalDateTime updatedDatetime;
}
