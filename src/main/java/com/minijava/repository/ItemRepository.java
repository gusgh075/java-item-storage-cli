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
    public ItemDTO findByName(String name) {
      // 필터링 후 findFirst()와 orElse(null)을 사용해 단일 객체를 반환합니다.
      return itemDTOList.stream()
          .filter(item -> item.getName().equals(name))
          .findFirst() // 첫 번째 일치 요소 (이름 고유하므로 유일한 요소)를 찾습니다.
          .orElse(null); // 찾지 못하면 null을 반환합니다.
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
        if(itemDTOList.isEmpty()) return -1;
        return itemDTOList.get(itemDTOList.size()-1).getNumber();
    }


}
