package com.currency.service;

import com.currency.model.BaseEntity;

import java.io.Serializable;
import java.util.Collection;

public interface CrudService<T extends BaseEntity<I>, I extends Serializable> {

	T create(T entity);

	T update(T entity);

	void delete(T entity);

	void deleteById(I id);

	void deleteAll();

	T get(I id);

	Collection<T> getAll();


}
