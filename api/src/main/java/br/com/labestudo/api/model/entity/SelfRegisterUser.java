package br.com.labestudo.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "self_register_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelfRegisterUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private String email;

	private String pass;

	@CreationTimestamp
	private OffsetDateTime created;

	@UpdateTimestamp
	private OffsetDateTime updated;

	@Version
	private Integer version;
}
