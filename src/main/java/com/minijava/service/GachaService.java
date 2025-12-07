package com.minijava.service;

import com.minijava.common.RankGachaProbability;
import com.minijava.dto.ItemDTO;
import com.minijava.repository.ItemRepositoryInterface;

import java.util.List;
import java.util.Random;

public class GachaService {
    private Random rand = new Random();
    private ItemRepositoryInterface itemRepository;

    public GachaService(ItemRepositoryInterface itemRepository){
        this.itemRepository=itemRepository;
    }

    /**
     * 확률에따라 랜덤으로 등급을 반환해준다
     * @return 일반/희귀/레전더리 중 하나의 문자열을 return함. 오류가 발생시 null을 return한다.
     */
    private String getGachaRank() {
        int rankIndex = rand.nextInt(1, 101);
        if (rankIndex <= RankGachaProbability.NORMAL.getProbability()) // Normal 80 => rankIndex <= 80
            return RankGachaProbability.NORMAL.getRank();
        else if (rankIndex <= RankGachaProbability.NORMAL.getProbability() +        // Normal 80 + Rare 19 =>  80 < rankIndex <= 99
                RankGachaProbability.RARE.getProbability())
            return RankGachaProbability.RARE.getRank();
        else if (rankIndex <= RankGachaProbability.NORMAL.getProbability() +    // Normal 80 + Rare 19 + Legendary 1 =>  rankIndex == 100
                RankGachaProbability.RARE.getProbability() +
                RankGachaProbability.LEGENDARY.getProbability())
            return RankGachaProbability.LEGENDARY.getRank();
        else{
            return null;
        }
    }

    /**
     * 랜덤으로 아이템을 반환한다.
     * @return 정상적으로 동작시 ItemDTO객체를 반환한다. null을 반환할 시 동작오류
     */
    public ItemDTO getRandomItem(){
        String rank = getGachaRank();
        if(rank == null){
            return null;
        }
        List<ItemDTO> rankItems = itemRepository.findAll().stream().filter(itemDTO -> itemDTO.getRank().equals(rank)).toList(); // 정해진 랭크에 속하는 아이템 리스트를 추철
        int randomTarget = rand.nextInt(0, rankItems.size()); // rankItems중 랜덤으로 하나의 index를 지정한다
        return rankItems.get(randomTarget);
    }
}
