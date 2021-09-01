package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class DoorTest {

	@Test
	void testCheckKey() {
		Door door = new Door(0, 2, 2);
		Key keyRight = new Key(0, 1, 1);
		assertEquals(door.checkKey(keyRight), true);
		
		Key keyWrong = new Key(1, 1, 1);
		assertEquals(door.checkKey(keyWrong), false);
	}

	@Test
	void testUnlock() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 0, 0);
		Door door = new Door(0, 2, 2);
		
		assertEquals(door.unlock(null), false);
		assertEquals(door.getLocked(), true);
		
		Key keyWrong = new Key(1, 1, 1);
		player.pickUp(keyWrong);
		assertEquals(door.unlock(keyWrong), false);
		assertEquals(door.getLocked(), true);
		player.dropKey();
		
		Key keyRight = new Key(0, 1, 2);
		player.pickUp(keyRight);
		assertEquals(door.unlock(keyRight), true);
		assertEquals(door.getLocked(), false);
		
	}
	
	@Test
	void testMoveOnPlayer() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 0, 0);
		Door door = new Door(0, 2, 2);
		assertEquals(door.moveOn(player), false);
		
		Key keyWrong = new Key(1, 1, 1);
		player.pickUp(keyWrong);
		assertEquals(door.moveOn(player), false);
		player.dropKey();
		
		Key keyRight = new Key(0, 1, 1);
		player.pickUp(keyRight);
		assertEquals(door.moveOn(player), true);
		assertEquals(door.getLocked(), false);
		
		player.dropKey();
		assertEquals(door.moveOn(player), true);
		assertEquals(door.getLocked(), false);
		
	}

	@Test
	void testMoveOnEnemy() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 0, 0);
		Enemy enemy = new Enemy(dungeon, 0, 0);
		Door door = new Door(0, 2, 2);
		assertEquals(door.moveOn(enemy), false);
		
		Key keyRight = new Key(0, 1, 1);
		player.pickUp(keyRight);
		door.unlock(keyRight);
		assertEquals(door.getLocked(), false);
		assertEquals(door.moveOn(enemy), true);
	
	}

}
