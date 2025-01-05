package com.project.microservices.searchservice.theater.entity;

import java.util.List;

import com.project.microservices.searchservice.show.entity.ShowEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="theater")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int theaterId;
	@Column(name="name")
	private String name;
	private String address;
	private String city;
	
	@Column(name="total_seats")
	private int totalSeats;
	
	@OneToMany(mappedBy = "theater")
    private List<ShowEntity> showtimes;
}
