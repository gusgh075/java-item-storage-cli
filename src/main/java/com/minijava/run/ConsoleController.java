package com.minijava.run;

import com.minijava.dto.ItemDTO;
import com.minijava.io.InputHandler;
import com.minijava.io.OutputHandler;
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

import java.util.List;

public class ConsoleController {

    private final InputHandler input = new InputHandler();
    private final OutputHandler output = new OutputHandler();

    private final ItemRepositoryInterface itemRepository;
    private final ItemAddService addService;
    private final ItemDeleteService deleteService;
    private final ItemSearchService searchService;

    public ConsoleController() {
        this.itemRepository = new ItemFileIORepository();
        this.addService = new ItemAddServiceImpl(itemRepository);
        this.deleteService = new ItemDeleteServiceImpl(itemRepository);
        this.searchService = new ItemSearchServiceImpl(itemRepository);
    }

    public void run() {

        int menu;
        do {
            output.printMenu();
            menu = input.inputInt("메뉴 선택 >> ");

            switch (menu) {
                case 1 -> showAllItems();
                case 2 -> findByNumber();
                case 3 -> findByName();
                case 4 -> addItem();
                case 5 -> deleteItem();
                case 7 -> gachaDraw();
                case 8 -> showInventory();
                case 9 -> findByKeyword();
                case 0 -> exit();
                default -> output.print("잘못된 번호");
            }

            output.print("-----------------------------------");
            input.waitForEnter();

        } while (menu != 0);

    }

    private void showAllItems() {
        output.printItemList(searchService.findAllItems());
    }

    private void findByNumber() {
        int num = input.inputInt("번호 입력: ");
        ItemDTO item = searchService.findItemById(num);
        output.print(item == null ? "없음" : item.toString());
    }

    private void findByName() {
        String name = input.inputString("이름 입력: ");
        ItemDTO item = searchService.findItemsByName(name);
        output.print(item == null ? "없음" : item.toString());
    }

    private void addItem() {
        String name = input.inputString("이름: ");
        String rank = input.inputString("등급: ");
        String quality = input.inputString("품질: ");
        int power = input.inputInt("전투력: ");

        int result = addService.itemAdd(name, rank, quality, power);
        output.print(result == -1 ? "중복 - 등록 실패" : result + "번 아이템 등록 완료");
    }

    private void deleteItem() {
        int num = input.inputInt("삭제 번호: ");
        boolean ok = deleteService.deleteItem(num);
        output.print(ok ? "삭제 완료" : "삭제 실패");
    }

    private void gachaDraw() {
        GachaService gacha = new GachaService(itemRepository);
        ItemDTO item = gacha.getRandomItem();
        output.print(item.toString());
    }

    private void showInventory() {
        InventoryRepo repo = new InventoryRepo();
        ItemSearchService s = new ItemSearchServiceImpl(repo);
        output.printItemList(s.findAllItems());
    }

    private void findByKeyword() {
        String key = input.inputString("키워드: ");
        List<ItemDTO> list = searchService.searchItemsByKeyword(key);
        output.printItemList(list);
    }

    private void exit() {
        ((ItemFileIORepository) itemRepository).saveAll();
        output.print("저장 후 종료.");
    }
}
