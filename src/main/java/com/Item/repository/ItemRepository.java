package com.Item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Item.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{



}

