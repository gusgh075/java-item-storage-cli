package com.kth.game.inventory.service;

import com.kth.game.inventory.comparator.AscendingPower;
import com.kth.game.inventory.dto.ItemDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryService {

  private List<ItemDTO> inventory;

  public InventoryService() {
    inventory = new ArrayList<>();

    /* 초기 아이템 데이터 추가 */
    inventory.add(new ItemDTO(1, "초심자의 검", "노말", "하급", 100));
    inventory.add(new ItemDTO(2, "바람의 활", "레어", "상급", 500));
    inventory.add(new ItemDTO(3, "드래곤 슬레이어", "에픽", "최상급", 2500));
    inventory.add(new ItemDTO(4, "절대반지", "유니크", "중급", 4000));
    inventory.add(new ItemDTO(5, "고대신의 지팡이", "에인션트", "하급", 9999));
  }

  public List<ItemDTO> getInventory() {
    return inventory;
  }

  /**
   * 번호로 아이템 찾기
   */
  public ItemDTO selectItemByNumber(int itemNumber) {
    for (ItemDTO item : inventory) {
      if (item.getNumber() == itemNumber) return item;
    }
    return null;
  }

  /**
   * 아이템 추가
   * 1. 이름 중복 불가
   */
  public int addItem(ItemDTO newItem) {

    // 1. 중복 체크
    for (ItemDTO item : inventory) {
      if (item.getName().equals(newItem.getName())) return 0; // 중복
    }

    // 2. 다음 번호 생성: (요청하신 대로 비어있음 체크를 제거했습니다.)
    int nextNumber = inventory.isEmpty() ? 1 : inventory.get(inventory.size() - 1).getNumber() + 1;

    newItem.setNumber(nextNumber);

    inventory.add(newItem);
    return newItem.getNumber();
  }

  /**
   * 아이템 삭제
   */
  public ItemDTO deleteItem(int itemNumber) {
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).getNumber() == itemNumber) {
        return inventory.remove(i);
      }
    }
    return null;
  }

  /**
   * 아이템 검색 (이름 포함)
   */
  public List<ItemDTO> searchItem(String keyword) {
    List<ItemDTO> searchList = new ArrayList<>();
    for (ItemDTO item : inventory) {
      if (item.getName().contains(keyword)) {
        searchList.add(item);
      }
    }
    return searchList;
  }

  /**
   * 정렬 (1: 이름순, 2: 전투력순)
   */
  public List<ItemDTO> sortInventory(int sortingNumber) {
    // 스트림을 이용한 깊은 복사
    List<ItemDTO> sortedList = inventory.stream()
        .map(ItemDTO::new)
        .collect(Collectors.toList());

    if (sortingNumber == 1) {
      Collections.sort(sortedList); // 이름 오름차순 (Comparable)
    } else {
      Collections.sort(sortedList, new AscendingPower()); // 전투력 오름차순 (Comparator)
    }

    return sortedList;
  }
}
