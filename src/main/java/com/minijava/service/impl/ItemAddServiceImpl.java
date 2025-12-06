package com.minijava.service.impl;

import com.minijava.dto.ItemDTO;
import com.minijava.repository.ItemRepository;
import com.minijava.repository.ItemRepositoryInterface;
import com.minijava.service.ItemAddService;

import java.util.List;

public class ItemAddServiceImpl implements ItemAddService {
    private final ItemRepositoryInterface itemRepository;

    public ItemAddServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    /**
     * item을 추가하는 메서드. return 값으로 추가한 아이템의 고유번호를 반환한다. -1을 반환할 시 중복 값이 있다는 것.
     *
     * @param name
     * @param rank
     * @param quality
     * @param power
     * @return
     */
    @Override
    public int itemAdd(String name, String rank, String quality, int power) {
        // 이름 중복 아이템 처리
        ItemDTO existingItem = itemRepository.findByName(name);
        if (existingItem != null) return -1;

        // 아이템 정상 등록
        int newItemNumber;
        int lastItemNumber = itemRepository.getLastItemNumber();
        if (lastItemNumber == -1) { //repository에 아이템이 없는 경우 getLastItemNumber는 -1을 반환
            newItemNumber = 1;
        } else { //마지막 아이템의 고유번호를 확인한 경우
            newItemNumber = lastItemNumber + 1;
        }
        itemRepository.save(new ItemDTO(newItemNumber, name, rank, quality, power));
        return newItemNumber;
    }
}
