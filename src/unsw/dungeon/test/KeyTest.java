package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class KeyTest {

	@Test
	void testPickUpItem() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		Key key1 = new Key(0, 0, 0);
		assertEquals(key1.pickUpItem(player), true);
		assertEquals(player.getKey(), key1);
		
		assertEquals(key1.pickUpItem(player), false);
		
		Key key2 = new Key(1, 2, 2);
		assertEquals(key2.pickUpItem(player), false);
		player.dropKey();
		assertEquals(player.getKey(), null);
		
		assertEquals(key2.pickUpItem(player), true);
		assertEquals(player.getKey(), key2);
	}

	@Test
	void testDropItem() {
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		Key key = new Key(0, 0, 0);
		player.pickUp(key);
		
		key.dropItem();
		assertEquals(player.getKey(), null);
	}

	@Test
	void testDestroyItem() {
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		Key key = new Key(0, 0, 0);
		player.pickUp(key);
		
		key.destroyItem();
		assertEquals(player.getKey(), null);
	}

}
