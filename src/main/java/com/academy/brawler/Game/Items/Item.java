package com.academy.brawler.Game.Items;

import com.academy.brawler.Game.Items.Types.ItemSlot;

public interface Item {
    String getName();
    String getDescription();
    long getWeight();
    ItemSlot[] getItemSlots();
    default boolean itemMatchesItemSlots(final ItemSlot itemSlot){
        ItemSlot[] itemSlots = getItemSlots();
        for (ItemSlot slot : itemSlots) {
            if (slot.equals(itemSlot)){
                return true;
            }
        }
        return false;
    }
}