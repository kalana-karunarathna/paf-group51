package com.fms.tickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ticket_id")
    private Long ticketId;
    
    @Column(name = "author_email")
    private String authorEmail;
    
    @Column(name = "author_name")
    private String authorName;
    
    private String content;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_edited")
    private Boolean isEdited;
    
    @Column(name = "author_role")
    private String authorRole; // USER, TECHNICIAN, ADMIN
    
    // Default constructor
    public Comment(Long ticketId, String authorEmail, String authorName, String content, String authorRole) {
        this.ticketId = ticketId;
        this.authorEmail = authorEmail;
        this.authorName = authorName;
        this.content = content;
        this.authorRole = authorRole;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isEdited = false;
    }
    
    // Update comment content
    public void updateContent(String newContent) {
        this.content = newContent;
        this.updatedAt = LocalDateTime.now();
        this.isEdited = true;
    }
    
    // Check if user can edit this comment
    public boolean canEdit(String userEmail, String userRole) {
        return this.authorEmail.equals(userEmail) || 
               "ADMIN".equals(userRole) || 
               ("TECHNICIAN".equals(userRole) && !("USER".equals(this.authorRole)));
    }
    
    // Check if user can delete this comment
    public boolean canDelete(String userEmail, String userRole) {
        return this.authorEmail.equals(userEmail) || 
               "ADMIN".equals(userRole) || 
               ("TECHNICIAN".equals(userRole) && !("USER".equals(this.authorRole)));
    }
}
