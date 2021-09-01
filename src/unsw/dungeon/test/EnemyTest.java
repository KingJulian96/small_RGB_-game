package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class EnemyTest {
	@Test
	void dirctionTest(){
		Dungeon it = new Dungeon(2, 2);
		Enemy n = new Enemy(it, 0,0);
		it.addEnemies(n);
		assertEquals((it.getEnemies()!= null),true);
		assertEquals((it.getEnemies().contains(n)),true);
		
		n.moveDown();
		assertEquals((it.getEnemies().contains(n)),true);
		assertEquals((n.getX() == 0), true);
		assertEquals((n.getY() == 1), true);
		
		n.moveUp();
		assertEquals((it.getEnemies().contains(n)),true);
		assertEquals((n.getX() == 0), true);
		assertEquals((n.getY() == 0), true);
		
		n.moveUp(); // will not move
		assertEquals((it.getEnemies().contains(n)),true);
		assertEquals((n.getX() == 0), true);
		assertEquals((n.getY() == 0), true);
		
		n.moveRight();
		assertEquals((it.getEnemies().contains(n)),true);
		assertEquals((n.getX() == 1), true);
		assertEquals((n.getY() == 0), true);
		
		n.moveLeft();
		assertEquals((it.getEnemies().contains(n)),true);
		assertEquals((n.getX() == 0), true);
		assertEquals((n.getY() == 0), true);
		
		n.moveLeft();// will not move
		assertEquals((it.getEnemies().contains(n)),true);
		assertEquals((n.getX() == 0), true);
		assertEquals((n.getY() == 0), true);
	}
	
	@Test
	void condtionTest(){
		Dungeon it = new Dungeon(3, 3);

		Enemy n = new Enemy(it, 2,2);
		it.addEnemies(n);
		assertEquals(it.getEnemies().contains(n),true);
		
		assertEquals((n.getX() == 2), true);
		assertEquals((n.getY() == 2), true);
		
		n.killed();
		assertEquals(n.state().live() ,false);
		assertEquals(it.getEnemies().contains(n),false);
		
		Enemy h = new Enemy(it, 2,2);
		it.addEnemies(h);
		assertEquals(it.getEnemies().contains(h),true);
		
		
	}
}
