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
     * 마지막 요소의 아이템 고유번호를 반환한다. 만일 저장소가 비어있다면 -1을 반환한다.
     * @return
     */
    Integer getLastItemNumber();

}
