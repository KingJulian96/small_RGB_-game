package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class FloorSwitchTest {
	@Test
	void testFloorSwitch() {
		Dungeon d = new Dungeon(3, 3);
		
		FloorSwitch one = new FloorSwitch(0, 0);
		FloorSwitch two = new FloorSwitch(0, 1);
		
		assertEquals(d.get_GridSquare(0, 1).hasFloorSwitch(), null);
		assertEquals(d.get_GridSquare(0, 0).hasFloorSwitch(), null);
		
		d.get_GridSquare(0, 1).add(two);
		d.get_GridSquare(0, 0).add(one);
		
		assertEquals(one.triggered(), false);
		assertEquals(two.triggered(), false);
		
		
		assertEquals(d.get_GridSquare(0, 1).hasFloorSwitch().triggered(), false);
		assertEquals(d.get_GridSquare(0, 0).hasFloorSwitch().triggered(), false);
		
		Boulder for_one = new Boulder(d, 0, 0);
		d.get_GridSquare(0, 0).add(for_one);
		
		//assertEquals(d.get_GridSquare(0, 1).has_FloorSwitch().triggered(), true);
	}
}
