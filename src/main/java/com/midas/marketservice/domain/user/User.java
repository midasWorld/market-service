package com.midas.marketservice.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.midas.marketservice.domain.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotNull
	@Size(max = 50)
	private String email;

	@NotNull
	private String password;

	@NotNull
	@Size(max = 50)
	private String name;

	@Builder
	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	//== 비지니스 로직 ==//
	public void verifyPassword(String password) {
		if (!this.password.equals(password)) {
			throw new IllegalArgumentException("Invalid credentials with password=" + password);
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("id", id)
			.append("email", email)
			.append("password", password)
			.append("name", name)
			.append("createdAt", createdAt)
			.append("createdBy", createdBy)
			.append("modifiedAt", modifiedAt)
			.append("modifiedBy", modifiedBy)
			.toString();
	}
}
