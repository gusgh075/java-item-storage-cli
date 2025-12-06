package com.minijava.repository;

import com.minijava.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements ItemRepositoryInterface {

    private final List<ItemDTO> itemDTOList = new ArrayList<>();

    @Override
    public void save(ItemDTO item) {
        itemDTOList.add(item);
    }

    @Override
    public ItemDTO findById(Integer id) {
        return itemDTOList.stream()
                .filter(item -> item.getNumber() == id)
                .findFirst()
                .orElse(null); // 못 찾으면 null
    }

    @Override
    public List<ItemDTO> findByName(String name) {
        return itemDTOList.stream()
                .filter(item -> item.getName().equals(name))
                .toList(); // Java 16+
    }

  @Override
  public List<ItemDTO> findByKeyword(String keyword) {
    return itemDTOList.stream()
        // getName()에 keyword가 포함되어 있으면 true
        .filter(item -> item.getName().contains(keyword))
        .toList();
  }

    @Override
    public List<ItemDTO> findAll() {
        return List.copyOf(itemDTOList); // 방어적 복사
    }

    @Override
    public boolean delete(Integer id) {
        return itemDTOList.removeIf(item -> item.getNumber() == id.intValue());
    }

    @Override
    public Integer getLastItemNumber() {
        return itemDTOList.get(itemDTOList.size()-1).getNumber();
    }


}
