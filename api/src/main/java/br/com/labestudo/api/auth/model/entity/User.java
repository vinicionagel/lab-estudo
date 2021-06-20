package br.com.labestudo.api.auth.model.entity;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "\"user\"")
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String email;
	
	private String pass;
	
	private Boolean active = true;
	
	@CreationTimestamp
	private OffsetDateTime created;
	
	@UpdateTimestamp
	private OffsetDateTime updated;
	
}
