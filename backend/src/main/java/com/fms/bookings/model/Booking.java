package com.fms.bookings.model;

import java.time.LocalDateTime;

import com.fms.bookings.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String resourceId;
	private String resourceName;
	private Integer capacity;
	private String requestedBy;
	private String purpose;

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;
	private String approvedBy;
	private String rejectionReason;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
