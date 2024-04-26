package br.com.neurotech.challenge.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name="Clientes")
public class NeurotechClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1251015129966876026L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@NotBlank(message = "O nome não pode estar em branco")
	@Column(nullable = false)
	private String name;
	
	@Min(value = 0, message = "A idade não pode ser negativa")
	@Column(nullable = false)
	private Integer age;
	
	@NotNull(message = "O rendimento não pode ser nulo")
	@Column(nullable = false)
	private Double income;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

    public NeurotechClient() {
    }
	
	public NeurotechClient(@NotBlank(message = "O nome não pode estar em branco") String name,
			@Min(value = 0, message = "A idade não pode ser negativa") Integer age,
			@NotNull(message = "O rendimento não pode ser nulo") Double income) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.income = income;
	}

}