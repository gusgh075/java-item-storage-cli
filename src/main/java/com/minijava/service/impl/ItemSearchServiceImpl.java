package com.minijava.service.impl;

import com.minijava.dto.ItemDTO;
import com.minijava.repository.ItemRepositoryInterface;

import java.util.List;

public class ItemSearchServiceImpl implements com.minijava.service.ItemSearchService {


  // 의존성: Repository 인터페이스를 통해 데이터에 접근합니다.
  private final ItemRepositoryInterface itemRepository;

  // 생성자를 통해 ItemRepository 객체를 주입받습니다.
  public ItemSearchServiceImpl(ItemRepositoryInterface itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public List<ItemDTO> findAllItems() {
    // Repository의 findAll() 메서드를 호출하여 모든 상품 목록을 가져옵니다.
    return itemRepository.findAll();
  }

  @Override
  public ItemDTO findItemById(Integer id) {
    // 1. 유효성 검사: ID가 null이거나 0 이하인지 확인 (기존 로직)
    if (id == null || id <= 0) {
      return null;
    }

    // 2. Repository 호출 및 결과 저장
    ItemDTO foundItem = itemRepository.findById(id);

    // 3. 존재 여부 검사: ID가 양수이지만 결과가 null인지 확인 (추가 로직)
    if (foundItem == null) {
      return null;
    }

    // 4. 상품 정보가 존재하면 반환
    return foundItem;
  }

  @Override
  public ItemDTO findItemsByName(String name) {
    // 1. 유효성 검사: 이름이 null이거나 공백인지 확인
    if (name == null || name.trim().isEmpty()) {
      return null;
    }

    // 2. Repository 호출: (수정된 Repository의) findByName은 이제 ItemDTO를 반환합니다.
    ItemDTO foundItem = itemRepository.findByName(name);

    // 3. 찾은 객체 또는 null 반환
    return foundItem;
  }

  @Override
  public List<ItemDTO> searchItemsByKeyword(String keyword) {

    return itemRepository.findByKeyword(keyword);
  }
}
