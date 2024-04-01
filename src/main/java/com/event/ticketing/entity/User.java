package com.event.ticketing.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "event_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String roles;
    @Column(nullable = false)
    private Boolean isLocked;
    @Column(nullable = false)
    private Boolean isActive;
    
    @CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date creationDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_on", nullable = false, updatable = true)
	private Date updatedOn;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    
    public User(){
    	this.isLocked=false;
    	this.isActive=true;
    	this.roles="USER";
    }
}
