package com.minijava.repository;

import com.minijava.dto.ItemDTO;

import java.util.List;

public interface ItemRepositoryInterface {

    void save(ItemDTO item);

    ItemDTO findById(Integer id);

    List<ItemDTO> findByName(String name);

    List<ItemDTO> findAll();

    boolean delete(Integer id);

}
