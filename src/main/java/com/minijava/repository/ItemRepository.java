package com.minijava.repository;

import com.minijava.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/*
* 프로그램 실행 중에만 데이터를 유지하는 클래스
* AbstractItemRepository의 기능을 그대로 받아 사용한다.
* */
public class ItemRepository extends AbstractItemRepository {


  /*
  * saveAll() (강제)
  * 이 저장소는 영구 저장을 지원하지 않으므로, 아무것도 핮 않게 비워둠
  * */
  @Override
  public void saveAll() {
    System.out.println("파일 저장 건너뛴다.");
  }
}