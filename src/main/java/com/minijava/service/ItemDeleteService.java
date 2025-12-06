package com.minijava.service;

public interface ItemDeleteService {

  /**
   * 아이템 번호를 받아 해당 아이템을 삭제
   * @param itemNumber 삭제할 아이템의 고유 번호
   * @return 삭제 성공 시 true, 실패 시(번호 없음 등) false
   */
  boolean deleteItem(int itemNumber);
}
