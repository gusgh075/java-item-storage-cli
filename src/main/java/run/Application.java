package com.kth.game.inventory.run;

import com.kth.game.inventory.dto.ItemDTO;
import com.kth.game.inventory.service.InventoryService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

  private Scanner sc = new Scanner(System.in);
  private InventoryService invService = new InventoryService();

  public static void main(String[] args) {
    new Application().run();
  }

  public void run() {

    int input = 0;

    do {
      System.out.println("\n===== [게임 인벤토리 관리 시스템] =====\n");
      System.out.println("1. 전체 아이템 조회");
      System.out.println("2. 아이템 상세 조회(번호)");
      System.out.println("3. 아이템 획득(추가)");
      System.out.println("4. 아이템 정보 수정");
      System.out.println("5. 아이템 버리기(삭제)");
      System.out.println("6. 아이템 검색");
      System.out.println("7. 아이템 정렬(가방 정리)");
      System.out.println("0. 게임 종료");
      try {
        System.out.print("메뉴 선택 >> ");
        input = sc.nextInt();
        sc.nextLine(); // 입력 버퍼 개행 문자 제거

        switch (input) {
          case 1: selectAll(); break;
          case 2: selectItemNumber(); break;
          case 3: addItem(); break;
          case 4: updateItem(); break;
          case 5: deleteItem(); break;
          case 6: searchItem(); break;
          case 7: sortInventory(); break;
          case 0:
            System.out.println("게임을 종료합니다. 진행 상황이 저장되었습니다."); break;
          default:
            System.out.println("@@@@@ 메뉴 목록에 존재하는 번호를 입력하세요. @@@@@");
        }

      } catch (InputMismatchException e) {
        System.out.println("##### 입력 형식이 잘못되었습니다. 숫자만 입력해주세요. ######");
        sc.nextLine(); // 입력 버퍼의 남은 잘못된 문자열을 제거
        input = -1; // 첫 반복 입력 오류 시 비정상 종료 막기

      } catch (Exception e) { // 나머지 예외 발생 처리
        System.out.println("##### 알 수 없는 예외 발생. 개발자에게 문의하세요. #####");
        e.printStackTrace();
      }

    } while (input != 0);
  }


  /*
   * 1. 모든 아이템 목록 조회
   * */
  private void selectAll() {
    System.out.println("\n*** 전체 인벤토리 조회 ***\n");
    List<ItemDTO> inventory = invService.getInventory();

    if(inventory.isEmpty()) {
      System.out.println("가방이 비어있습니다.");
      return;
    }

    for (ItemDTO item : inventory) {
      System.out.println(item);
    }
  }

  /**
   * 2. 아이템 상세 조회(아이템 번호)
   */
  private void selectItemNumber() {
    System.out.print("\n*** 아이템 상세 조회 ***\n");
    System.out.print("상세 조회할 아이템 번호 입력 : ");
    int itemNumber = sc.nextInt();
    sc.nextLine();

    // 서비스 호출
    ItemDTO item = invService.selectItemByNumber(itemNumber);

    if (item == null) {
      System.out.println("@@@ 일치하는 번호의 아이템이 없습니다. @@@");
      return;
    }

    System.out.println("---------- ITEM INFO ----------");
    System.out.println("번호: " + item.getNumber());
    System.out.println("이름: " + item.getName());
    System.out.println("등급: " + item.getRank());
    System.out.println("품질: " + item.getQuality());
    System.out.println("전투력: " + item.getPower());
    System.out.println("-------------------------------");
  }

  /**
   * 3. 아이템 추가
   */
  private void addItem() {
    System.out.print("\n*** 아이템 획득(추가) ***\n");

    System.out.print("아이템 이름 입력 : ");
    String name = sc.nextLine();

    System.out.print("아이템 등급 입력(노말~에인션트) : ");
    String rank = sc.nextLine();

    System.out.print("아이템 품질 입력(하급~최상급) : ");
    String quality = sc.nextLine();

    System.out.print("아이템 전투력 입력 : ");
    int power = sc.nextInt();
    sc.nextLine();

    // ItemDTO 객체 생성 및 값 대입
    ItemDTO newItem = new ItemDTO();
    newItem.setName(name);
    newItem.setRank(rank);
    newItem.setQuality(quality);
    newItem.setPower(power);

    // 서비스 호출
    int itemNumber = invService.addItem(newItem);

    if (itemNumber == 0) {
      System.out.println("@@@ 이미 가방에 존재하는 아이템 이름입니다. @@@");
      return;
    }

    System.out.println("[" + itemNumber + "]번 슬롯에 아이템이 추가되었습니다.");
  }


  /**
   * 4. 아이템 수정
   */
  private void updateItem() {
    System.out.println("\n*** 아이템 정보 수정 ***\n");

    // 1. 번호로 수정할 아이템이 있는지 검색
    System.out.print("수정할 아이템 번호 입력 : ");
    int itemNumber = sc.nextInt();
    sc.nextLine();

    // 서비스 호출
    ItemDTO item = invService.selectItemByNumber(itemNumber);

    if (item == null) {
      System.out.println("@@@ 일치하는 번호의 아이템이 없습니다. @@@");
      return;
    }

    // 2. 해당 아이템의 정보 수정
    System.out.print("수정할 이름 : ");
    String updateName = sc.nextLine();

    System.out.print("수정할 등급 : ");
    String updateRank = sc.nextLine();

    System.out.print("수정할 품질 : ");
    String updateQuality = sc.nextLine();

    System.out.print("수정할 전투력 : ");
    int updatePower = sc.nextInt();
    sc.nextLine();

    // Setter를 통해 객체 정보 수정 (참조 주소를 공유하므로 리스트 내용도 바뀜)
    item.setName(updateName);
    item.setRank(updateRank);
    item.setQuality(updateQuality);
    item.setPower(updatePower);

    System.out.println("*** 아이템 정보가 수정되었습니다. ***");
  }

  /**
   * 5. 아이템 제거
   */
  private void deleteItem() {
    System.out.print("\n*** 아이템 버리기(삭제) ***\n");

    System.out.print("제거할 아이템 번호 입력 : ");
    int itemNumber = sc.nextInt();
    sc.nextLine();

    // 서비스 호출
    ItemDTO deletedItem = invService.deleteItem(itemNumber);

    if (deletedItem == null) {
      System.out.println("@@@ 일치하는 번호의 아이템이 없습니다. @@@");
      return;
    }

    System.out.println("'" + deletedItem.getName() + "' 아이템을 버렸습니다.");
  }

  /**
   * 6. 아이템 검색
   */
  private void searchItem() {
    System.out.print("\n*** 아이템 검색(이름 키워드 검색) ***\n");

    System.out.print("검색할 키워드 입력 : ");
    String keyword = sc.nextLine();

    // 서비스 호출
    List<ItemDTO> searchList = invService.searchItem(keyword);

    if (searchList == null || searchList.isEmpty()) {
      System.out.println("검색 결과가 없습니다.");
      return;
    }

    for (ItemDTO item : searchList) {
      System.out.println(item);
    }
  }

  /**
   * 7. 아이템 정렬
   */
  private void sortInventory() {
    System.out.print("\n*** 인벤토리 정렬 목록 조회 ***\n");

    System.out.println("1. 아이템명 오름차순");
    System.out.println("2. 전투력 오름차순");
    System.out.print("정렬 방식 선택 >> ");
    int sortingNumber = sc.nextInt();
    sc.nextLine();

    // 서비스 호출
    List<ItemDTO> sortedList = invService.sortInventory(sortingNumber);

    for (ItemDTO item : sortedList) {
      System.out.println(item);
    }
  }
}
