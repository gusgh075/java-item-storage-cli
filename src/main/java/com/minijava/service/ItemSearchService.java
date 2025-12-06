package com.minijava.service;

import com.minijava.dto.ItemDTO;
import java.util.List;

public interface ItemSearchService {

  /**
   * 모든 상품 목록을 조회합니다.
   * @return 전체 ItemDTO 목록
   */
  List<ItemDTO> findAllItems();

  /**
   * 상품 번호(ID)로 상품 상세 정보를 조회합니다.
   * @param id 조회할 상품 번호
   * @return ItemDTO 또는 null
   */
  ItemDTO findItemById(Integer id);

  /**
   * 상품 이름으로 일치하는 모든 상품을 조회합니다.
   * @param name 조회할 상품 이름
   * @return 이름이 일치하는 ItemDTO 목록
   */
  List<ItemDTO> findItemsByName(String name);


  List<ItemDTO> searchItemsByKeyword(String keyword);

}