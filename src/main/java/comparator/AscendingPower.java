package com.kth.game.inventory.comparator;

import com.kth.game.inventory.dto.ItemDTO;
import java.util.Comparator;

public class AscendingPower implements Comparator<ItemDTO> {
  @Override
  public int compare(ItemDTO o1, ItemDTO o2) {
    // 전투력 오름차순
    return o1.getPower() - o2.getPower();
  }
}