package com.project.microservices.userservice.model.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.microservices.userservice.utils.JsonTimestampSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_user_id_seq")
	@SequenceGenerator(name ="users_user_id_seq", sequenceName="users_user_id_seq", allocationSize=1)
	@Column(name="user_id")
	private Integer userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_notificationtype")
	private Integer userNotificationtype;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="user_createdon")
	private Timestamp userCreatedon;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="user_updatedon")
	private Timestamp userUpdatedon;
}
