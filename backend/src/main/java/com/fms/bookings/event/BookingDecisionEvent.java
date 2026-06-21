package com.fms.bookings.event;

import com.fms.bookings.enums.BookingStatus;

public record BookingDecisionEvent(Long bookingId, String requestedBy, BookingStatus status, String reason) {
}
