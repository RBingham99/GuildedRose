package com.gildedrose;

class GildedRose {
    private Item[] items;

    public Item[] getItems() {
        return items;
    }

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            Item currentItem = items[i];

            if (hasSpecialConditions(currentItem)) {
                if (isNot0Quality(currentItem)) {
                    decreaseItemQuality(currentItem);
                }
            } else {
                if (isNotMaxQuality(currentItem)
                    && !currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                    increaseItemQuality(currentItem, 1);
                    updateBackstagePassQuality(currentItem);
                }
            }
            if (!currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                decreaseSellInBy1(currentItem);
            }

            if (currentItem.sellIn < 0) {
                if (!currentItem.name.equals("Aged Brie")) {
                    if (!currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (isNot0Quality(currentItem)) {
                            if (!currentItem.name.equals("Sulfuras, Hand of Ragnaros")) {
                                decreaseItemQuality(currentItem);
                            }
                        }
                    } else {
                        currentItem.quality = 0;
                    }
                } else {
                    if (isNotMaxQuality(currentItem)) {
                        increaseItemQuality(currentItem, 1);
                    }
                }
            }
        }
    }

    private boolean hasSpecialConditions(Item currentItem) {
        return !currentItem.name.equals("Aged Brie")
            && !currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert")
            && !currentItem.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private void increaseItemQuality(Item currentItem, int amount) {
        currentItem.quality = currentItem.quality + amount;
    }

    private void decreaseItemQuality(Item currentItem) {
        currentItem.quality = currentItem.quality - 1;
    }

    private void updateBackstagePassQuality(Item currentItem) {
        if (currentItem.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (isNotMaxQuality(currentItem)) {
                if(currentItem.sellIn < 6) {
                    increaseItemQuality(currentItem, 2);
                } else if (currentItem.sellIn < 11) {
                    increaseItemQuality(currentItem, 1);
                }
            }
        }
    }

    private void decreaseSellInBy1(Item currentItem) {
            currentItem.sellIn = currentItem.sellIn - 1;
    }

    private boolean isNotMaxQuality(Item currentItem) {
        return currentItem.quality < 50;
    }

    private boolean isNot0Quality(Item currentItem) {
        return currentItem.quality > 0;
    }
}
