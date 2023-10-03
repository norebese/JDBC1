package Game;

public class BattleSystem {
	
	protected int maxHP;
	protected int HP;
	protected int shield;
	protected int strength;

	public BattleSystem(int maxHP, int HP, int shield, int strength) {
		this.maxHP=maxHP;
		this.HP=HP;
		this.shield=shield;
		this.strength=strength;
	}
	
	public void setMaxHP(int maxHP) {
		this.maxHP=maxHP;
	}
	public int getMaxHP() {
		return this.maxHP;
	}
	
	public void setHP(int HP) {
		this.HP=HP;
	}
	public int getHP() {
		return this.HP;
	}
	
	public void setShield(int shield) {
		this.shield=shield;
	}
	public int getShield() {
		return this.shield;
	}
	
	public void setStrength(int strength) {
		this.strength=strength;
	}
	public int getStrength() {
		return this.strength;
	}
	

	
	
}
