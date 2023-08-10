package vn.tholv.web.core.base.entity.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import vn.tholv.web.core.override.util.CloneObject;

import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
@SuppressWarnings("all")
@Getter
public class BaseEntity<T, ID> extends CloneObject<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String _id = "id";
	public static final String _createdDate = "createdDate";
	public static final String _modifiedDate = "modifiedDate";


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected ID id;
	@JsonIgnore
	protected Timestamp createdDate;
	@JsonIgnore
	protected Timestamp modifiedDate;

	public ID getId() {
		return id;
	}

	@PreUpdate
	private void preUpdate() {
		modifiedDate = new Timestamp(System.currentTimeMillis());
	}

	@PrePersist
	private void prePersist() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		createdDate = now;
		modifiedDate = now;
	}
}
