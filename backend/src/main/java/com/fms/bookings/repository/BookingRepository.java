package com.fms.bookings.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fms.bookings.enums.BookingStatus;
import com.fms.bookings.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByRequestedByOrderByCreatedAtDesc(String requestedBy);

	List<Booking> findByRequestedByIgnoreCaseOrderByCreatedAtDesc(String requestedBy);

	@Query("""
		select b from Booking b
		where lower(b.requestedBy) like lower(concat('%', :requestedByPattern, '%'))
		order by b.createdAt desc
		""")
	List<Booking> findByRequestedByCaseInsensitive(@Param("requestedByPattern") String requestedByPattern);

	List<Booking> findByStatusOrderByCreatedAtDesc(BookingStatus status);

	List<Booking> findByResourceIdOrderByStartDateTimeAsc(String resourceId);

	@Query("""
		select b from Booking b
		where b.resourceId = :resourceId
		  and b.status in :statuses
		  and b.startDateTime < :requestedEnd
		  and b.endDateTime > :requestedStart
		""")
	List<Booking> findConflictingBookings(@Param("resourceId") String resourceId,
		@Param("statuses") List<BookingStatus> statuses,
		@Param("requestedStart") LocalDateTime requestedStart,
		@Param("requestedEnd") LocalDateTime requestedEnd);

	@Query("""
		select b from Booking b
		where b.resourceId = :resourceId
		  and b.status in :statuses
		  and b.startDateTime < :requestedEnd
		  and b.endDateTime > :requestedStart
		  and b.id <> :excludedId
		""")
	List<Booking> findConflictingBookingsExcludingId(@Param("resourceId") String resourceId,
		@Param("statuses") List<BookingStatus> statuses,
		@Param("requestedStart") LocalDateTime requestedStart,
		@Param("requestedEnd") LocalDateTime requestedEnd,
		@Param("excludedId") Long excludedId);
}
