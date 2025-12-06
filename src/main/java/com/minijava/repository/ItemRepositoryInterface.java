package com.minijava.repository;

import com.minijava.dto.ItemDTO;
import java.util.List;

public interface ItemRepositoryInterface {

  /**
   * 아이템을 저장소에 저장함
   * @param item 저장할 아이템 객체
   */
  void save(ItemDTO item);

  /**
   * 고유 번호(ID)로 아이템을 찾음
   * @param id 찾을 아이템의 고유 번호
   * @return 발견된 ItemDTO, 없으면 null
   */
  ItemDTO findById(Integer id);

  /**
   * 이름이 정확히 일치하는 아이템들을 찾음
   * @param name 검색할 이름
   * @return 일치하는 아이템 리스트
   */
  List<ItemDTO> findByName(String name);

  /**
   * 이름에 특정 키워드가 포함된 아이템들을 찾음
   * @param keyword 검색할 키워드
   * @return 키워드가 포함된 아이템 리스트
   */
  List<ItemDTO> findByKeyword(String keyword);

  /**
   * 저장된 모든 아이템을 조회
   * @return 전체 아이템 리스트
   */
  List<ItemDTO> findAll();

  /**
   * 고유 번호(ID)로 아이템을 삭제
   * @param id 삭제할 아이템의 고유 번호
   * @return 삭제 성공 시 true, 실패 시 false
   */
  boolean delete(Integer id);

  /**
   * 현재 저장된 아이템 중 가장 큰 고유 번호를 반환합니다.
   * @return 가장 큰 ID 값, 저장소가 비어있으면 -1 반환
   */
  Integer getLastItemNumber();
}