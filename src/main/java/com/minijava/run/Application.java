package com.minijava.run;

import com.minijava.dto.ItemDTO;
import com.minijava.repository.InventoryRepo;
import com.minijava.repository.ItemFileIORepository;
import com.minijava.repository.ItemRepositoryInterface;
import com.minijava.service.GachaService;
import com.minijava.service.ItemAddService;
import com.minijava.service.ItemDeleteService;
import com.minijava.service.ItemSearchService;
import com.minijava.service.impl.ItemAddServiceImpl;
import com.minijava.service.impl.ItemDeleteServiceImpl;
import com.minijava.service.impl.ItemSearchServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {

  private final Scanner sc = new Scanner(System.in);

  // 필요한 서비스 인터페이스 선언
  private final ItemAddService itemAddService;
  private final ItemDeleteService itemDeleteService;
  private final ItemSearchService itemSearchService;
  private final ItemRepositoryInterface itemRepository;

  public Application() {
    // 파일 I/O 기반 Repository 생성 및 주입
    itemRepository = new ItemFileIORepository();

    // 서비스 구현체 초기화 시, ItemRepositoryInterface 주입
    this.itemAddService = new ItemAddServiceImpl(itemRepository);
    this.itemDeleteService = new ItemDeleteServiceImpl(itemRepository);
    this.itemSearchService = new ItemSearchServiceImpl(itemRepository);
  }

  public static void main(String[] args) {
    new Application().run();
  }

  // 실행 메뉴

  public void run() {
    int input = 0;
    do {
      System.out.println("\n===== [게임 아이템 관리 시스템] =====\n");
      System.out.println("1. 전체 아이템 조회 (저장소)");
      System.out.println("2. 아이템 상세 조회(번호)");
      System.out.println("3. 아이템 검색(이름)");
      System.out.println("4. 아이템 추가 (저장소)");
      System.out.println("5. 아이템 삭제(번호, 저장소)");
      System.out.println("6. 사용자 이름 설정 (미구현)");
      System.out.println("7. 아이템 뽑기 ");
      System.out.println("8. 뽑기 내역 확인");
      System.out.println("9. 아이템 검색(키워드)");
      System.out.println("0. 시스템 종료");

      try {
        System.out.print("메뉴 선택 >> ");
        input = sc.nextInt();
        sc.nextLine();

        switch (input) {
          case 1:
            showAllItems();
            break;
          case 2:
            findItemByNumber();
            break;
          case 3:
            searchItemByName();
            break; // 구현부 위치 이동
          case 4:
            addItem();
            break;
          case 5:
            deleteItem();
            break;
          case 6:
            System.out.println("[6번 기능] 사용자 이름 설정 기능은 아직 구현되지 않았습니다.");
            break;
          // setUserName();
          case 7:
            GachaService gachaService = new GachaService(itemRepository);
            ItemDTO randomItem = gachaService.getRandomItem();
            while(randomItem == null) {
              randomItem = gachaService.getRandomItem();
            }
            InventoryRepo inventoryRepo = new InventoryRepo();
            ItemAddService itemAddService1 = new ItemAddServiceImpl(inventoryRepo);
            itemAddService1.itemAdd(randomItem.getName(), randomItem.getRank(), randomItem.getQuality(), randomItem.getPower());
            inventoryRepo.saveAll();
            System.out.println(randomItem);
            break;
          case 8:
            System.out.println("\n--- 전체 아이템 목록 ---");
            ItemSearchService itemSearchService1 = new ItemSearchServiceImpl(new InventoryRepo());
            List<ItemDTO> allItems = itemSearchService1.findAllItems();

            if (allItems.isEmpty()) {
              System.out.println("[알림] 등록된 아이템이 없습니다.");
              return;
            }

            for (ItemDTO item : allItems) {
              System.out.println(item);
            }
            break;
          case 9:
            System.out.print("검색할 아이템에 포함된 문자 입력 : ");
            String input2 = sc.nextLine();
            List<ItemDTO> itemDTOS = itemSearchService.searchItemsByKeyword(input2);
            System.out.println(input2+"이/가 포함된 아이템 목록");
            for (ItemDTO itemDTO : itemDTOS) {
              System.out.println(itemDTO);
            }
            break; // 구현부 위치 이동
          case 0:
            System.out.println("시스템을 종료합니다. 진행 상황이 저장되었습니다.");
            ((ItemFileIORepository) itemRepository).saveAll();
            break;
          default:
            System.out.println("@@@@@ 메뉴 목록에 존재하는 번호를 입력하세요. @@@@@");
        }

      } catch (InputMismatchException e) {
        System.out.println("##### 입력 형식이 잘못되었습니다. 숫자만 입력해주세요. ######");
        sc.nextLine();
        input = -1;
      } catch (Exception e) {
        System.out.println("##### 알 수 없는 예외 발생. 개발자에게 문의하세요. #####");
        e.printStackTrace();
      }

      System.out.println("---------------------------------");
      System.out.println("메뉴를 다시 보시려면 enter를 누르세요.");
      sc.nextLine();
    } while (input != 0);
  }

  // 3. 아이템 검색(이름) (ItemSearchService 활용)
  public void searchItemByName() {
    System.out.print("검색할 아이템 이름 입력: ");
    String name = sc.nextLine();

    ItemDTO found = itemSearchService.findItemsByName(name);

    if (found == null) {
      System.out.println("[결과] 일치하는 이름의 아이템을 찾을 수 없습니다.");
    } else {
      System.out.println("[결과] 조회된 아이템 정보: " + found);
    }
  }

  // 1. 아이템 전체 조회 (ItemSearchService 활용)
  public void showAllItems() {
    System.out.println("\n--- 전체 아이템 목록 ---");
    List<ItemDTO> allItems = itemSearchService.findAllItems();

    if (allItems.isEmpty()) {
      System.out.println("[알림] 등록된 아이템이 없습니다.");
      return;
    }

    for (ItemDTO item : allItems) {
      System.out.println(item);
    }
  }

  // 2. 아이템 상세 조회(번호) (ItemSearchService 활용)
  public void findItemByNumber() {
    System.out.print("조회할 아이템 번호 입력: ");
    int itemNumber = 0;
    try {
      itemNumber = sc.nextInt();
      sc.nextLine();
    } catch (InputMismatchException e) {
      System.out.println("아이템 번호는 숫자로만 입력해주세요.");
      sc.nextLine();
      return;
    }

    ItemDTO found = itemSearchService.findItemById(itemNumber);

    if (found == null) {
      System.out.println("[결과] 해당 번호의 아이템을 찾을 수 없습니다. (혹은 ID가 유효하지 않습니다)");
    } else {
      System.out.println("[결과] 조회된 아이템 정보: " + found);
    }
  }

  // 4. 아이템 추가 (ItemAddService 활용)
  public void addItem() {
    System.out.println("\n--- 새로운 아이템 등록 ---");
    try {
      System.out.print("이름: ");
      String name = sc.nextLine();

      // 1. 등급 유효성 검사 메서드 호출
      String rank = getValidRank();

      System.out.print("품질: ");
      String quality = sc.nextLine();

      System.out.print("공격력/방어력(Power): ");
      int power = sc.nextInt();
      sc.nextLine();

      int result = itemAddService.itemAdd(name, rank, quality, power);

      if (result == -1) {
        System.out.println("[등록 실패] 이름이 중복되어 아이템을 추가할 수 없습니다.");
      } else {
        System.out.println("[등록 성공] 아이템 고유번호 " + result + "번으로 등록되었습니다.");
      }
    } catch (InputMismatchException e) {
      System.out.println("[등록 오류] 파워(Power) 값은 숫자로만 입력해야 합니다.");
      sc.nextLine();
    }
  }

  // 등급(Rank) 무결성 검사 메서드 (새로 추가)

  /**
   * 유효한 등급(일반/희귀/레전더리)이 입력될 때까지 입력을 반복하여 받습니다.
   *
   * @return 유효한 등급 문자열
   */
  private String getValidRank() {
    // 허용되는 등급 목록 (대소문자 무시를 위해 소문자로 저장)
    final List<String> VALID_RANKS = List.of("일반", "희귀", "레전더리");
    String rankInput;

    while (true) {
      System.out.print("등급(일반/희귀/레전더리): ");
      rankInput = sc.nextLine().trim();

      if (VALID_RANKS.contains(rankInput)) {
        // 유효한 등급인 경우 반환
        return rankInput;
      } else {
        // 유효하지 않은 등급인 경우 오류 메시지 출력 후 반복
        System.out.println("❌ [오류] 등급은 '일반', '희귀', '레전더리' 중 하나만 입력해야 합니다. 다시 입력해주세요.");
      }
    }
  }

  // 5. 아이템 삭제(번호) (ItemDeleteService 활용)
  public void deleteItem() {
    System.out.print("삭제할 아이템 번호 입력: ");
    int itemNumber = 0;
    try {
      itemNumber = sc.nextInt();
      sc.nextLine();
    } catch (InputMismatchException e) {
      System.out.println("아이템 번호는 숫자로만 입력해주세요.");
      sc.nextLine();
      return;
    }

    boolean isDeleted = itemDeleteService.deleteItem(itemNumber);

    if (isDeleted) {
      System.out.println("[삭제 성공] 아이템 고유번호 " + itemNumber + "번이 목록에서 제거되었습니다.");
    } else {
      System.out.println("[삭제 실패] 해당 번호의 아이템을 찾을 수 없거나 삭제에 실패했습니다.");
    }
  }


}