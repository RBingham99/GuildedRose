package com.gildedrose;

import org.junit.jupiter.api.*;


class GildedRoseTest {

    public static GildedRose app;
    @BeforeEach
    public void createGuildedRoseObject() {
    Item[] items = new Item[]{
        new Item("+5 Dexterity Vest", 10, 20),
        new Item("+5 Dexterity Vest", 0, 10),
        new Item("+5 Dexterity Vest", 0, 0),
        new Item("Aged Brie", -4, 0),
        new Item("Aged Brie", -4, 50),
        new Item("Elixir of the Mongoose", 5, 7),
        new Item("Sulfuras, Hand of Ragnaros", 0, 80),
        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 46),
        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 46),
        new Item("Backstage passes to a TAFKAL80ETC concert", 0, 46)};
    app = new GildedRose(items);
    }

    @Test
    void checkGuildedRoseConstructorBuildsProperly() {
        Assertions.assertEquals("+5 Dexterity Vest", app.getItems()[0].name);
    }

    @Test
    void checkDexterityVestWithPositiveSellInReducesSellInBy1() {
        app.updateQuality();
        Assertions.assertEquals(9, app.getItems()[0].sellIn);
    }

    @Test
    void checkDexterityVestWithPositiveSellInReducesQualityBy1() {
        app.updateQuality();
        Assertions.assertEquals(19, app.getItems()[0].quality);
    }

    @Test
    void checkDexterityVestPassedSellInDecreaseQualityBy2() {
        app.updateQuality();
        Assertions.assertEquals(8, app.getItems()[1].quality);
    }

    @Test
    void checkQualityDoesNotDecreaseBelow0() {
        app.updateQuality();
        Assertions.assertEquals(0, app.getItems()[2].quality);
    }

    @Test
    void checkAgedBrieWithNegativeSellInIncreasesQuality() {
        app.updateQuality();
        Assertions.assertEquals(2, app.getItems()[3].quality);
    }

    @Test
    void checkAgedBrieQualityNeverIncreasesPast50() {
        app.updateQuality();
        Assertions.assertEquals(50, app.getItems()[4].quality);
    }

    @Test
    void checkSulfurasNeverDecreasesQuality() {
        app.updateQuality();
        Assertions.assertEquals(80, app.getItems()[6].quality);
    }

    @Test
    void checkBackstagePassesIncreasesQualityBy1WhenSellInOver10() {
        app.updateQuality();
        Assertions.assertEquals(21, app.getItems()[8].quality);
    }

    @Test
    void checkBackstagePassesIncreasesQualityBy2WhenSellInOverBetween5And10() {
        app.updateQuality();
        Assertions.assertEquals(48, app.getItems()[9].quality);
    }

    @Test
    void checkBackstagePassesIncreasesQualityBy3WhenSellIn5OrUnder() {
        app.updateQuality();
        Assertions.assertEquals(49, app.getItems()[10].quality);
    }

    @Test
    void checkBackstagePassesDropTo0QualityWhenPastSellIn() {
        app.updateQuality();
        Assertions.assertEquals(0, app.getItems()[11].quality);
    }
}
