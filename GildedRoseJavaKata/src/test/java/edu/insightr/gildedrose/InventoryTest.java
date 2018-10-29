package edu.insightr.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void UpdateQuality() {
        // Déclaration des paramètres attendu pour le test classic d'update quality pour tous sauf Backstage passes (plus bas )et Conjured qui n'est pas défini
        int expectedQualityDexterity = 19; // Decrease 1
        int expectedQualityAged_Brie = 21; // Increase 1
        int expectedQualityElixir = 19; // Decrease 1
        int expectedQualitySulfuras = 80; // Legendary item 80 in quality so it don't change
        //INITIALISATION DES PARAMETRES
        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 10, 20),
                new Item("Elixir of the Mongoose", 10, 20),
                new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        Inventory test = new Inventory(list);
        //EXECUTION DU TEST
        test.updateQuality();
        //CONTROLE DU TEST

        assertEquals(expectedQualityDexterity, list[0].getQuality());
        assertEquals(expectedQualityAged_Brie, list[1].getQuality());
        assertEquals(expectedQualityElixir, list[2].getQuality());
        assertEquals(expectedQualitySulfuras, list[3].getQuality());
    } // Ok
    @Test
    void SellInDecrease(){
        // Déclaration des paramètres attendu pour le test classic d'update quality pour tous sauf Backstage passes et Conjured
        int expectedSellInDexterity = 9; // Decrease 1
        int expectedSellInAged_Brie = 9; // Decrease 1
        int expectedSellInElixir = 9; // Decrease 1
        int expectedSellInSulfuras = 0; // Legendary item sellin don't move
        int expectedSellInBackstage = 9; // Decrease 1

        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 10, 20),
                new Item("Elixir of the Mongoose", 10, 20),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                new Item("Conjured Mana Cake", 10, 20)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expectedSellInDexterity, list[0].getSellIn());
        assertEquals(expectedSellInAged_Brie, list[1].getSellIn());
        assertEquals(expectedSellInElixir, list[2].getSellIn());
        assertEquals(expectedSellInSulfuras, list[3].getSellIn());
        assertEquals(expectedSellInBackstage, list[4].getSellIn());
    } // Ok
    @Test
    void SellDatePassed(){
        // Déclaration des paramètres attendu pour le test classic d'update quality pour tous sauf Backstage passes et Conjured
        int expectedSellInDexterity = -1; // Decrease 1
        int expectedSellInAged_Brie = -1; // Decrease 1
        int expectedSellInElixir = -1; // Decrease 1
        int expectedSellInSulfuras = 0; // Legendary item sellin don't move
        int expectedSellInBackstage = -1; // Decrease 1

        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 0, 20),
                new Item("Aged Brie", 0, 20),
                new Item("Elixir of the Mongoose", 0, 20),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20),
                new Item("Conjured Mana Cake", 0, 20)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expectedSellInDexterity, list[0].getSellIn());
        assertEquals(expectedSellInAged_Brie, list[1].getSellIn());
        assertEquals(expectedSellInElixir, list[2].getSellIn());
        assertEquals(expectedSellInSulfuras, list[3].getSellIn());
        assertEquals(expectedSellInBackstage, list[4].getSellIn());
    } // Ok
    @Test
    void AfterSellInLessThan() {
        int expectedQualityDexterity = 18; // Decrease 1
        int expectedQualityAged_Brie = 22; // Increase 1
        int expectedQualityElixir = 18; // Decrease 1
        int expectedQualitySulfuras = 80; // Legendary item 80 in quality so it don't change
        int expectedQualityBackstage = 0;

        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 0, 20),
                new Item("Aged Brie", 0, 20),
                new Item("Elixir of the Mongoose", 0, 20),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20),
                new Item("Conjured Mana Cake", 0, 20)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expectedQualityDexterity, list[0].getQuality());
        assertEquals(expectedQualityAged_Brie, list[1].getQuality());
        assertEquals(expectedQualityElixir, list[2].getQuality());
        assertEquals(expectedQualitySulfuras, list[3].getQuality());
        assertEquals(expectedQualityBackstage, list[4].getQuality());
    } //Ok
    @Test
    void BackstagesPassesTest() {
        int expectedQualitySellMoreThan10 = 20; // Increase 1
        int expectedQualitySellEqual10 = 22; // Increase 2
        int expectedQualitySellEqual5 = 23; // Increase 3
        int expectedQualitySellAfterDate = 0; // Equals 0

        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 0, 20),
                new Item("Aged Brie", 0, 20),
                new Item("Elixir of the Mongoose", 0, 20),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 11, 19),
                new Item("Conjured Mana Cake", 0, 20)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expectedQualitySellMoreThan10, list[4].getQuality());

        test.updateQuality();

        assertEquals(expectedQualitySellEqual10, list[4].getQuality());

        list[4] = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        test.updateQuality();

        assertEquals(expectedQualitySellEqual5, list[4].getQuality());

        list[4] = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20);
        test.updateQuality();

        assertEquals(expectedQualitySellAfterDate, list[4].getQuality());

    } // Ok

    // Erreur car pas de remise a 0 modification de la définition de Item en conditionnant la déclaration de quality
    // pour éviter les déclarations négativent
    @Test
    void IfQualityIsNegative() {
        int expectedQualityDexterity = 0;
        int expectedQualityAged_Brie = 2; // Increase 2 because its Aged Brie
        int expectedQualityElixir = 0;
        int expectedQualitySulfuras = 0;
        int expectedQualityBackstage = 0;

        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 0, -10),
                new Item("Aged Brie", 0, -10),
                new Item("Elixir of the Mongoose", 0, -10),
                new Item("Sulfuras, Hand of Ragnaros", 0, -10),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, -10),
                new Item("Conjured Mana Cake", 0, -10)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expectedQualityDexterity, list[0].getQuality());
        assertEquals(expectedQualityAged_Brie, list[1].getQuality());
        assertEquals(expectedQualityElixir, list[2].getQuality());
        assertEquals(expectedQualitySulfuras, list[3].getQuality());
        assertEquals(expectedQualityBackstage, list[4].getQuality());

        test.updateQuality();

        assertEquals(expectedQualityDexterity, list[0].getQuality());
        assertEquals(expectedQualityAged_Brie + 2 , list[1].getQuality());
        assertEquals(expectedQualityElixir, list[2].getQuality());
        assertEquals(expectedQualitySulfuras, list[3].getQuality());
        assertEquals(expectedQualityBackstage, list[4].getQuality());


    } // Ok
    @Test
    void QualityLessThan50() {

        int expectedQualityDexterity = 49;
        int expectedQualityAged_Brie = 50; // Increase 2 because its Aged Brie
        int expectedQualityElixir = 49;
        int expectedQualitySulfuras = 80;
        int expectedQualityBackstage = 50;

        Item[] list = new Item[]{
                new Item("+5 Dexterity Vest", 10, 50),
                new Item("Aged Brie", 10, 50),
                new Item("Elixir of the Mongoose", 10, 50),
                new Item("Sulfuras, Hand of Ragnaros", 1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expectedQualityDexterity, list[0].getQuality());
        assertEquals(expectedQualityAged_Brie, list[1].getQuality());
        assertEquals(expectedQualityElixir, list[2].getQuality());
        assertEquals(expectedQualitySulfuras, list[3].getQuality());
        assertEquals(expectedQualityBackstage, list[4].getQuality());


    }

    // Now Conjured is set lets test it
    @Test
    void Conjured(){
        int expect = 18;
        Item[] list = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 10, 20),
                new Item("Elixir of the Mongoose", 10, 20),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                new Item("Conjured Mana Cake", 10, 20)};
        Inventory test = new Inventory(list);
        test.updateQuality();

        assertEquals(expect, list[5].getQuality());
    }

}