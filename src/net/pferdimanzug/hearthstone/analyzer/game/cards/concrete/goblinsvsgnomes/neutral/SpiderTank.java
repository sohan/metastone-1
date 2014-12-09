package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.goblinsvsgnomes.neutral;

import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Race;

public class SpiderTank extends MinionCard {

	public SpiderTank() {
		super("Spider Tank", 3, 4, Rarity.COMMON, HeroClass.ANY, 3);
		setRace(Race.MECH);
	}

	@Override
	public int getTypeId() {
		return 545;
	}



	@Override
	public Minion summon() {
		return createMinion();
	}
}