package br.com.labestudo.api.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "parameter")
@Data
public class Parameter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String key;

	private String value;

	@CreationTimestamp
	private LocalDateTime created;

	@UpdateTimestamp
	private LocalDateTime updated;
}
