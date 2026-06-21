package com.fms.tickets.repository;

import com.fms.tickets.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    // Find all comments for a specific ticket, ordered by creation date
    List<Comment> findByTicketIdOrderByCreatedAtAsc(Long ticketId);
    
    // Find comments by author email
    List<Comment> findByAuthorEmail(String authorEmail);
    
    // Count comments for a specific ticket
    long countByTicketId(Long ticketId);
    
    // Delete all comments for a ticket (when ticket is deleted)
    void deleteByTicketId(Long ticketId);
    
    // Find comments by author role
    List<Comment> findByAuthorRole(String authorRole);
}
