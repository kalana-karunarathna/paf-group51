package com.fms.tickets.repository;

import com.fms.tickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(String status);
    List<Ticket> findByPriority(String priority);
    List<Ticket> findByCategory(String category);
    List<Ticket> findBySubmittedBy(String submittedBy);
    List<Ticket> findByAssignedTo(String assignedTo);
    List<Ticket> findByLocationContainingIgnoreCase(String location);
    List<Ticket> findByTitleContainingIgnoreCase(String title);
    Optional<Ticket> findByTicketId(String ticketId);
    
    // Find maximum numeric ticket ID
    default List<Ticket> findAllTicketIds() {
        return findAll();
    }
    
    // Custom query to find max numeric ticket ID
    @org.springframework.data.jpa.repository.Query(value = "select * from tickets where ticket_id ~ '^[0-9]+$'", nativeQuery = true)
    List<Ticket> findNumericTicketIds();
}
