package com.inditex.prices.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

	private Long id;

	private String name;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Brand brand = (Brand) o;
		return Objects.equals(id, brand.id) && Objects.equals(name, brand.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
