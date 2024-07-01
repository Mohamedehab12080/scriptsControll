package com.scripts.controll.dashboard.scriptsController.model;

import java.time.LocalDateTime;
<<<<<<< HEAD
=======


>>>>>>> c93b7af8a34abd2b8dbc963eeeaed3081f391aac
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
<<<<<<< HEAD
import jakarta.persistence.Column;
=======
import org.springframework.format.annotation.DateTimeFormat;

>>>>>>> c93b7af8a34abd2b8dbc963eeeaed3081f391aac
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

<<<<<<< HEAD
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class baseEntity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(columnDefinition = "datetime")
    private LocalDateTime lastModifiedDate;

    // Getters and Setters
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
=======
@MappedSuperclass //not entity
@EntityListeners({AuditingEntityListener.class}) //to use auditing annotations
public class baseEntity <ID>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;
	@CreatedBy
	private String createdBy;
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss a")
	private LocalDateTime createdDate;
	@LastModifiedBy
	private String lastModifiedBy;
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss a")
	private LocalDateTime lastModifiedDate;
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
>>>>>>> c93b7af8a34abd2b8dbc963eeeaed3081f391aac
}
