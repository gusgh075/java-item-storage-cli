package com.minijava.service.impl;

import com.minijava.dto.ItemDTO;
import com.minijava.repository.ItemRepository;
import com.minijava.repository.ItemRepositoryInterface;
import com.minijava.service.ItemUpdateService;

public class ItemUpdateServiceImpl implements ItemUpdateService {

  private final ItemRepositoryInterface repository;

  public ItemUpdateServiceImpl(ItemRepositoryInterface repository) {
    this.repository = repository;
  }

  @Override
  public boolean updateItem(int number, String name, String rank, String quality, Integer power) {

    // 1. 수정 대상 찾기
    ItemDTO target = repository.findById(number);

    // 2. 존재하지 않으면 실패
    if (target == null) {
      return false;
    }

    // 3. 입력값이 null 또는 빈 값이 아니면 업데이트
    if (name != null && !name.isBlank()) {
      target.setName(name);
    }

    if (rank != null && !rank.isBlank()) {
      target.setRank(rank);
    }

    if (quality != null && !quality.isBlank()) {
      target.setQuality(quality);
    }

    if (power != null) {
      if (power < 0) {
        throw new IllegalArgumentException("전투력은 0 이상이어야 합니다.");
      }
      target.setPower(power);
    }

    return true; // 리스트 기반이니까 setter로 이미 값 반영됨
  }
}



