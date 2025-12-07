package com.minijava.repository;

import com.minijava.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class AbstractItemRepository implements ItemRepositoryInterface {

  protected List<ItemDTO> itemDTOList = new ArrayList<>();

  @Override
  public void save(ItemDTO item) {
    for (int i = 0; i < itemDTOList.size(); i++){
      if (itemDTOList.get(i))
    }

  }

  @Override
  public ItemDTO findById(Integer id) {
    return null;
  }

  @Override
  public List<ItemDTO> findByName(String name) {
    return List.of();
  }

  @Override
  public List<ItemDTO> findByKeyword(String keyword) {
    return List.of();
  }

  @Override
  public List<ItemDTO> findAll() {
    return List.of();
  }

  @Override
  public boolean delete(Integer id) {
    return false;
  }

  @Override
  public Integer getLastItemNumber() {
    return 0;
  }
}
