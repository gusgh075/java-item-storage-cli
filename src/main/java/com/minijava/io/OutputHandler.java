package com.minijava.io;

import com.minijava.dto.ItemDTO;

import java.util.List;

public class OutputHandler {

    public void print(String msg) {
        System.out.println(msg);
    }

    public void printItemList(List<ItemDTO> items) {
        if (items.isEmpty()) {
            System.out.println("아이템이 없습니다.");
            return;
        }
        for (ItemDTO item : items) {
            System.out.println(item);
        }
    }

    public void printMenu() {
        System.out.println("\n===== [게임 아이템 관리 시스템] =====\n");
        System.out.println("1. 전체 아이템 조회");
        System.out.println("2. 아이템 상세 조회(번호)");
        System.out.println("3. 아이템 검색(이름)");
        System.out.println("4. 아이템 추가");
        System.out.println("5. 아이템 삭제");
        System.out.println("6. 아이템 수정(미구현)");
        System.out.println("7. 아이템 뽑기");
        System.out.println("8. 인벤토리 조회");
        System.out.println("9. 아이템 검색(키워드)");
        System.out.println("0. 종료");
    }
}
