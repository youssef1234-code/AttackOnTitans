package game.engine.lanes;

import java.util.PriorityQueue;
import java.util.ArrayList;
import game.engine.titans.*;
import game.engine.weapons.Weapon;
import game.engine.base.Wall;

public class Lane implements Comparable<Lane> {
	
	private final Wall laneWall;
	private int dangerLevel; 
	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons ;
	
	public Lane(Wall laneWall){
		this.laneWall = laneWall;
		this.dangerLevel = 0;
		this.titans = new PriorityQueue<Titan>();
		this.weapons = new ArrayList<Weapon>();
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

	public Wall getLaneWall() {
		return this.laneWall;
	}
	
	public PriorityQueue<Titan> getTitans() {
		return this.titans;
	}
	
	public ArrayList<Weapon> getWeapons() {
		return this.weapons;
	}
	

}
