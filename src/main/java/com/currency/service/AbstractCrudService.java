package com.currency.service;


import com.currency.model.BaseEntity;
import com.google.common.collect.Lists;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

@Service
@Transactional
public abstract class AbstractCrudService<T extends BaseEntity<I>, I extends Serializable> implements CrudService<T, I> {

	protected CrudRepository<T, I> crudRepository;

	protected AbstractCrudService(CrudRepository<T, I> crudRepository) {
		this.crudRepository = crudRepository;
	}

	@Override
	public T create(T entity) {
		return crudRepository.save(entity);
	}

	@Override
	public T update(T entity) {
		return crudRepository.save(entity);
	}

	@Override
	public void delete(T entity) {
		crudRepository.delete(entity);
	}

	@Override
	public void deleteById(I id) {
		crudRepository.delete(id);
	}

	@Override
	public void deleteAll() {
		crudRepository.deleteAll();
	}

	@Override
	@Transactional(readOnly = true)
	public T get(I id) {
		return crudRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<T> getAll() {
		return Lists.newArrayList(crudRepository.findAll());
	}
}
