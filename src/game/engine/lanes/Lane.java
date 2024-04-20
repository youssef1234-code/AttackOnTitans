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
	
	public void addTitan(Titan titan) {
		this.titans.add(titan);
	}
	
	public void addWeapon(Weapon weapon) {
		this.weapons.add(weapon);
	}
	
	public void moveLaneTitans() {
		PriorityQueue<Titan> temp = new PriorityQueue<Titan>();
		Titan currentTitan = null;
		while(!this.titans.isEmpty()) {
			currentTitan = this.titans.poll();
			if(!currentTitan.hasReachedTarget())
					currentTitan.move();
			temp.add(currentTitan);
		}
		while(!temp.isEmpty())
			this.titans.add(temp.poll());
	}
	
	 public int performLaneTitansAttacks() {
		 int resources = 0;
		 PriorityQueue<Titan> temp = new PriorityQueue<Titan>();
		 Titan currentTitan = null;
		 int size = this.getTitans().size();
		 for(int i=0;i<size;i++) {
			 currentTitan = this.titans.poll();
			 if(currentTitan.hasReachedTarget())
				 resources+=currentTitan.attack(this.laneWall);
			 temp.add(currentTitan);
		 }
		 while(!temp.isEmpty())
			 this.titans.add(temp.poll());
		 return resources;
	 }
	 
	 public int performLaneWeaponsAttacks() {
		 int resources = 0;
		 for(int i =0;i<this.weapons.size();i++) {
			 resources+= this.weapons.get(i).turnAttack(this.titans);
		 }
		 return resources;
	 }
	 
	 public boolean isLaneLost() {
		 return this.laneWall.isDefeated();
	 }
	 
	 public void updateLaneDangerLevel() {
		 int totalDangerLevel = 0;
		 PriorityQueue<Titan> temp = new PriorityQueue<Titan>();
		 Titan currentTitan = null;
		 int size = this.getTitans().size();
		 
		 for(int i=0;i<size;i++) {
			 currentTitan = this.titans.poll();
			 totalDangerLevel += currentTitan.getDangerLevel();
			 temp.add(currentTitan);
		 }
		 
		 while(!temp.isEmpty())
			 this.titans.add(temp.poll());
		 
		 this.dangerLevel = (size>0) ? totalDangerLevel :0;
	 }
	 
	 
}
