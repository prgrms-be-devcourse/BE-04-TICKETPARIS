package com.programmers.ticketparis.domain.reservation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReservationStatus {

	COMPLETED("예매완료"),
	CANCELED("예매취소");

	private final String status;
}
