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
    // Repository의 findById() 메서드를 호출하여 특정 ID의 상품을 조회합니다.
    if (id == null || id <= 0) {
      // 유효성 검사 (서비스 계층의 역할 중 하나)
      System.out.println("상품 ID는 양수여야 합니다.");
      return null;
    }
    return itemRepository.findById(id);
  }

  @Override
  public List<ItemDTO> findItemsByName(String name) {
    // Repository의 findByName() 메서드를 호출하여 이름이 일치하는 상품들을 조회합니다.(부분 검색 x)

    // name이 빈 문자열이거나 공백만으로 이루어진 문자열일 경우 빈 리스트 반환
    if (name == null || name.trim().isEmpty()) {
      System.out.println("검색할 상품 이름을 입력해야 합니다.");
      return List.of(); // 빈 리스트 반환
    }
    return itemRepository.findByName(name);
  }

  @Override
  public List<ItemDTO> searchItemsByKeyword(String keyword) {

    return itemRepository.findByKeyword(keyword);
  }
}
