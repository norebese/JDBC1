package Game;

public class Monster extends BattleSystem {
	
	int damage;
	public String name;
	
	public Monster(int maxHP, int HP, int shield, int strength,String name) {
		super(maxHP,HP,shield,strength);
		this.name=name;
	}
	
	public void setDamage(int damage) {
		this.damage=damage;
	}
	public int getDamage() {
		return this.damage;
	}
	public void setShield(int shield) {
		this.shield=shield;
	}
	public int getShield() {
		return this.shield;
	}
	
	public String toString() {
		return "체력:"+this.maxHP+"/"+this.HP+" | 쉴드:"+this.shield;
	}

}
