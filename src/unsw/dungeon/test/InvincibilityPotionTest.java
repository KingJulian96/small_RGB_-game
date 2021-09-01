package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


class InvincibilityPotionTest {

	@Test
	void testPickUpItem() {
		
		Dungeon dungeon = new Dungeon(3, 3);
		Player player = new Player(dungeon, 1, 1);
		InvincibilityPotion potion1 = new InvincibilityPotion(0, 0);
		dungeon.addEntity(potion1);
		assertEquals(potion1.pickUpItem(player), true);
		assertEquals(player.state().invincible(), false);
		
		InvincibilityPotion potion2 = new InvincibilityPotion(1, 1);
		dungeon.addEntity(potion2);
		assertEquals(potion2.pickUpItem(player), true);
		assertEquals(player.state().invincible(), true);
		
	}
	
}
