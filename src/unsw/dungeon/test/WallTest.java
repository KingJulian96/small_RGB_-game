package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


class WallTest {

	@Test
	void testMoveOnPlayer() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 0, 0);
		Wall wall = new Wall(2, 2);
		assertEquals(wall.moveOn(player), false);
		
	}

	@Test
	void testMoveOnEnemy() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Enemy enemy = new Enemy(dungeon, 0, 0);
		Wall wall = new Wall(2, 2);
		assertEquals(wall.moveOn(enemy), false);
		
	}

}
