package com.minijava.service.impl;

import com.minijava.repository.ItemRepositoryInterface;
import com.minijava.service.ItemDeleteService;

public class ItemDeleteServiceImpl implements ItemDeleteService {

  // repo 인터페이스 선언
  private final ItemRepositoryInterface itemRepository;

  // 생성자 - 외부에서 레포를 받음
  public ItemDeleteServiceImpl(ItemRepositoryInterface itemRepository) {
    this.itemRepository = itemRepository;
  }

  @Override
  public boolean deleteItem(int itemNumber) {
    return itemRepository.delete(itemNumber);
  }
}
