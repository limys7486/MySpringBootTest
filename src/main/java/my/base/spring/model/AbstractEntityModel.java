package my.base.spring.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractEntityModel implements Serializable
{
	@CreatedDate
	@Column(name = "reg_date", updatable = false)
	private LocalDateTime createdDateTime;

	//private LocalDateTime createdDateTime = LocalDateTime.now();

	@LastModifiedDate
	@Column(name = "update_date", updatable = true)
	private LocalDateTime lastModifiedDateTime;

	@Column(name = "created_by_user", nullable = false)
	@CreatedBy
	private String createdByUser;

	@Column(name = "modified_by_user", nullable = false)
	@LastModifiedBy
	private String modifiedByUser;
}
