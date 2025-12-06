package com.minijava.repository;

import com.minijava.dto.ItemDTO;

import java.util.List;

public interface ItemRepositoryInterface {

    void save(ItemDTO item);

    ItemDTO findById(Integer id);

    List<ItemDTO> findByName(String name);

  List<ItemDTO> findByKeyword(String keyword);

    List<ItemDTO> findAll();

    boolean delete(Integer id);

    /**
     * 마지막 요소의 순번을 반환한다.
     * @return
     */
    Integer getLastItemNumber();

}
