package game.engine.lanes;

import java.util.PriorityQueue;
import java.util.ArrayList;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;
import game.engine.base.Wall;

public class Lane implements Comparable<Lane> {
	
	private final Wall lanewall;
	private int dangerLevel; 
	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons ;
	
	public Lane(Wall laneWall){
		this.lanewall = laneWall;
		this.dangerLevel = 0;
	}
	
	@Override
	public int compareTo(Lane o) {
		return this.getDangerLevel() - o.getDangerLevel();
	}

	public int getDangerLevel() {
		return this.dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public Wall getLanewall() {
		return this.lanewall;
	}
	
	public PriorityQueue<Titan> getTitans() {
		return this.titans;
	}
	
	public ArrayList<Weapon> getWeapons() {
		return this.weapons;
	}
	
	
}
