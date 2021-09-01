package unsw.dungeon.test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


class SwordTest {

	@Test
	void testPickUpItem() {
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		Sword sword1 = new Sword(0, 0);
		assertEquals(sword1.pickUpItem(player), true);
		assertEquals(player.getSword(), sword1);
		
		assertEquals(sword1.pickUpItem(player), false);
		
		Sword sword2 = new Sword(2, 2);
		assertEquals(sword2.pickUpItem(player), false);
		
		sword1.destroyItem();
		assertEquals(player.getSword(), null);
		assertEquals(sword2.pickUpItem(player), true);
		assertEquals(player.getSword(), sword2);
	}

	@Test
	void testUseItem() {
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		Sword sword = new Sword(0, 0);
		sword.pickUpItem(player);
		
		sword.useItem();
		assertEquals(player.getSword(), sword);
		sword.useItem();
		assertEquals(player.getSword(), sword);
		sword.useItem();
		assertEquals(player.getSword(), sword);
		sword.useItem();
		assertEquals(player.getSword(), sword);
		sword.useItem();
		assertEquals(player.getSword(), null);
	}

	@Test
	void testDestroyItem() {
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		Sword sword = new Sword(0, 0);
		player.pickUp(sword);
		
		sword.destroyItem();
		assertEquals(player.getSword(), null);
	}

}
