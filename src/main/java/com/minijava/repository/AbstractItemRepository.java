package com.minijava.repository;

import com.minijava.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* 모든 저장소(메모리, 파일 등)에서 공통적으로 사용하는 CRUD 로직을 미리 구현해둔 클래스 */
public abstract class AbstractItemRepository implements ItemRepositoryInterface {

  /**
   * 데이터를 저장할 핵심 리스트로
   * protected를 쓴 이유는 '상속받은 자식'과 '같은 패키지'에서만 접근 가능하게 하려고 사용
   */
  protected List<ItemDTO> itemDTOList = new ArrayList<>();

  @Override
  public void addItem(ItemDTO item) {
    // 리스트를 처음부터 끝까지 돌면서 같은 ID가 있는지 확인
    for (int i = 0; i < itemDTOList.size(); i++){
      // 만약 리스트가 있는 아이템의 번호와, 저장하려는 아이템의 번호가 같다면
      if (itemDTOList.get(i).getNumber() == item.getNumber()){
        // set() : 해당 위치의 데이터를 새 것으로 교체한다.
        itemDTOList.set(i, item);
        return;
      }
    }
    // 반복문을 다 돌았는데도 return이 되지 않았으면 새 아이템이라는 뜻
    itemDTOList.add(item); // 리스트 맨 뒤에 추가 된다.
    saveAll();
  }

  @Override
  public ItemDTO findById(Integer id) {
    // List를 스트림으로 필터링
    return itemDTOList.stream()
        .filter(item -> item.getNumber() == id) // id가 같은 것만 남김
        .findFirst() // 그 중 첫번째 것
        .orElse(null); // 없으면 null 반환
  }

  @Override
  public ItemDTO findByName(String name) {
    // 이름 중복 검사에 사용
    return itemDTOList.stream()
        .filter(item -> item.getName().equals(name)) // 이름이 똑같은 것
        .findFirst()
        .orElse(null);
  }

  @Override
  public List<ItemDTO> findByKeyword(String keyword) {
    // 검색 기능
    return itemDTOList.stream()
        .filter(item -> item.getName().contains(keyword)) // 검색어를 포함하는지 확인
        .collect(Collectors.toList()); // 결과를 모아서 새로운 리스트로 반환
  }

  @Override
  public List<ItemDTO> findAll() {
    return new ArrayList<>(itemDTOList);
  }

  @Override
  public boolean delete(Integer id) {
    if (id == null) return false;

    // 1. 메모리 리스트에서 아이템 삭제
    // removeIf : 조건에 맞는 요소를 삭제하고, 삭제 성공 여부를 반환
    boolean wasRemoved = itemDTOList.removeIf(item -> item.getNumber() == id);

    // 2. 삭제에 성공했을 경우, 즉시 파일에 저장 (추가된 핵심 로직)
    if (wasRemoved) {
      saveAll(); // 변경된 메모리(itemDTOList) 상태를 파일에 덮어씁니다.
    }

    return wasRemoved;
  }

  @Override
  public Integer getLastItemNumber() {
    if (itemDTOList.isEmpty()) return -1; // 데이터 없으면 -1

    // 리스트 중간이 비어있더라도(삭제), 가장 큰 숫자를 찾아냄
    return itemDTOList.stream()
        .mapToInt(item -> item.getNumber()) // 아이템에서 숫자만 추출
        .max() // 최댓갑 찾기
        .orElse(-1);
  }
}
