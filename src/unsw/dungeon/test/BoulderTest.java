package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;
//import static org.junit.jupiter.api.Assertions.*;

public class BoulderTest {
	@Test
	void dirctionTest(){
		Dungeon it = new Dungeon(4, 4);
		//Wall wall = new Wall(0, 0);
		
		//it.get_GridSquare(0, 0).add(wall);
		Boulder one = new Boulder(it, 0, 1);
		it.get_GridSquare(0, 1).add(one);
		
		assertEquals(it.get_GridSquare(0, 1).hasBoulder(), true);
		assertEquals(one.moveToDirection(Direction.up), true);
		
		one.moveRight();
		assertEquals(it.get_GridSquare(0, 1).hasBoulder(), false);
		assertEquals(it.get_GridSquare(1, 1).hasBoulder(), true);
		assertEquals(one.moveToDirection(Direction.left), true);

		one.moveLeft();
		assertEquals(it.get_GridSquare(0, 1).hasBoulder(), true);
		assertEquals(it.get_GridSquare(1, 1).hasBoulder(), false);
		assertEquals(one.moveToDirection(Direction.up), true);
		
		
		one.moveUp();
		assertEquals(it.get_GridSquare(0, 0).hasBoulder(), true);
		assertEquals(it.get_GridSquare(0, 1).hasBoulder(), false);
		assertEquals(one.moveToDirection(Direction.down), true);
		
		one.moveDown();
		assertEquals(it.get_GridSquare(0, 1).hasBoulder(), true);
		assertEquals(it.get_GridSquare(0, 0).hasBoulder(), false);
		assertEquals(one.moveToDirection(Direction.down), true);
	}
	
	
	@Test
	void conditionTest(){
		Dungeon it = new Dungeon(4, 4);
		
		Boulder two = new Boulder(it, 0, 0);
		it.get_GridSquare(0, 0).add(two);
		
		Wall wall1 = new Wall(0, 1);
		it.get_GridSquare(0, 1).add(wall1);
		Wall wall2 = new Wall(1, 0);
		it.get_GridSquare(1, 0).add(wall2);
		
		
		assertEquals(it.get_GridSquare(0, 0).hasBoulder(), true);
		assertEquals(two.moveToDirection(Direction.right), false);
		assertEquals(two.moveToDirection(Direction.down), false);
		
		Boulder three = new Boulder(it, 1, 1);
		it.get_GridSquare(1, 1).add(three);
		
		assertEquals(it.get_GridSquare(1, 1).hasBoulder(), true);
		assertEquals(three.moveToDirection(Direction.up), false);
		assertEquals(three.moveToDirection(Direction.left), false);
		
		
		Boulder four = new Boulder(it, 1, 2);
		it.get_GridSquare(1, 2).add(four);
		
		assertEquals(it.get_GridSquare(1, 2).hasBoulder(), true);
		assertEquals(four.moveToDirection(Direction.up), false);
		
		Key key = new Key(1, 3, 0);
		it.get_GridSquare(1, 3).add(key);
		assertEquals(it.get_GridSquare(1, 3).hasKey(),true);
		assertEquals(four.moveToDirection(Direction.down), false);
		
		Sword s = new Sword(2,2);
		it.get_GridSquare(2, 2).add(s);
		assertEquals(it.get_GridSquare(2, 2).hasSword(),true);
		assertEquals(four.moveToDirection(Direction.right), false);
		
		
		Boulder f =  new Boulder(it, 3, 3);
		it.get_GridSquare(3, 3).add(f);
		assertEquals(it.get_GridSquare(3, 3).hasBoulder(),true);
		Bomb b = new Bomb(2, 3);
		it.get_GridSquare(2, 3).add(b);
		assertEquals(f.moveToDirection(Direction.left),false);
		
		
    	//if(temp.has_locked_door()) return false;
    	//if(temp.has_InvincibilityPotion()) return false;
    	//if(temp.has_Treasure()) return false;
	}
	
	@Test
	void tiggerTest() {
		Dungeon it = new Dungeon(2, 2);
		Boulder two = new Boulder(it, 0, 0);
		it.get_GridSquare(0, 0).add(two);
		
		FloorSwitch f = new FloorSwitch(0,1);
		it.get_GridSquare(0, 1).add(f);
		
		//assertEquals(two.moveToDirection(Direction.down),true);
		two.moveDown();
		assertEquals(it.get_GridSquare(0, 1).hasBoulder(),true);
		assertEquals((it.get_GridSquare(0, 1).hasFloorSwitch()!= null), true);
		assertEquals((it.get_GridSquare(0, 1).hasFloorSwitch().triggered()), true);
		
		two.moveUp();
		assertEquals((it.get_GridSquare(0, 1).hasFloorSwitch().triggered()), true);
	}
}
