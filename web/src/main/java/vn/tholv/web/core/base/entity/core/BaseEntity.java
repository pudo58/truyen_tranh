package vn.tholv.web.core.base.entity.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import vn.tholv.web.core.override.util.CloneObject;
import vn.tholv.web.core.override.util.CryptoUtils;

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
    @JsonIgnore
    protected ID id;

    @JsonIgnore
    protected Timestamp createdDate;
    @JsonIgnore
    protected Timestamp modifiedDate;

    @JsonProperty(_id)
    protected String uid;

    public ID getId() {
        return id;
    }

    public String getUid() {
        if (this.id != null) {
            this.uid = CryptoUtils.encode(String.valueOf(this.id));
        }
        return this.uid;
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
