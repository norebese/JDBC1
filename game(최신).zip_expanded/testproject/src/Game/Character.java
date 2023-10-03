package Game;

import java.util.ArrayList;
import java.util.List;

public class Character extends BattleSystem {
	
	public String name;
	private int courage;
	private int maxMana;
	private int mana;
	private int num;
	
	public List<String> AllCard=new ArrayList<>();
	
	public Character(int maxHP, int HP, int shield, int strength,int courage, int maxMana, int mana) {
		super(maxHP,HP,shield,strength);
		this.courage=courage;
		this.maxMana=maxMana;
		this.mana=mana;
		
	}

	public void setCourage(int courage) {
		this.courage=courage;
	}
	public int getCourage() {
		return this.courage;
	}
	
	public void setMaxMana(int maxMana) {
		this.maxMana=maxMana;
	}
	public int getMaxMana() {
		return this.maxMana;
	}
	
	public void setMana(int mana) {
		this.mana=mana;
	}
	public int getMana() {
		return this.mana;
	}
	
	public String toString() {
		return "체력:"+this.maxHP+"/"+this.HP+" | 쉴드:"+this.shield;
	}

	public List<String> getAllCard() {
		return AllCard;
	}

	public void setAllCard(String cardName) {
		AllCard.add(cardName);
	}
	
	
	
	
	
}
