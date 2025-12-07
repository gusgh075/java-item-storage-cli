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
      // removeIf : 리스트를 순회하면서 조건이 true인 요소를 삭제한다
      // 삭제된 요소가 하나라도 있으면 true를 반환
      // intValue() : Integer 객체를 int 기본형으로 안전하게 비교하기 위해 변환
      return itemDTOList.removeIf(item -> item.getNumber() == id.intValue());
    }

    @Override
    public Integer getLastItemNumber() {
        // 데이터가 하나도 없다면 -1을 반환, 다음 번호가 0 또는 1이 되도록 함
        if(itemDTOList.isEmpty()) {
          return -1;
        }

        // 스트림을 사용해 가장 큰 번호를 찾는 코드
        // 전체를 훑어서 '가장 큰 값'을 찾는 것이 ID 중복 방지에 안전하다고 생각하여 코드를 짬
        return itemDTOList.stream()
            .mapToInt(item -> item.getNumber()) // ItemDTO에서 number만 추출
            .max()                        //최댓값 찾고
            .orElse(-1);            // 없으면 -1

    }

}
