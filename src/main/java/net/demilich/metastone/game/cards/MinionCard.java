package net.demilich.metastone.game.cards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.demilich.metastone.game.Attribute;
import net.demilich.metastone.game.actions.BattlecryAction;
import net.demilich.metastone.game.actions.PlayCardAction;
import net.demilich.metastone.game.actions.PlayMinionCardAction;
import net.demilich.metastone.game.cards.desc.MinionCardDesc;
import net.demilich.metastone.game.entities.minions.Minion;
import net.demilich.metastone.game.entities.minions.Race;
import net.demilich.metastone.game.spells.desc.BattlecryDesc;

public class MinionCard extends Card {

	private static final Set<Attribute> inheritedAttributes = new HashSet<Attribute>(
			Arrays.asList(new Attribute[] { Attribute.STEALTH, Attribute.CANNOT_ATTACK, Attribute.TAUNT, Attribute.UNTARGETABLE_BY_SPELLS,
					Attribute.CHARGE, Attribute.DIVINE_SHIELD, Attribute.WINDFURY, Attribute.MEGA_WINDFURY, Attribute.SPELL_DAMAGE,
					Attribute.ATTACK_EQUALS_HP, Attribute.INVERT_HEALING, Attribute.SPELL_AMPLIFY_MULTIPLIER, Attribute.ENRAGABLE,
					Attribute.DOUBLE_DEATHRATTLES, Attribute.HERO_POWER_CAN_TARGET_MINIONS, Attribute.ATTACK_BONUS, Attribute.HERO_POWER_USAGES

	}));

	private final MinionCardDesc desc;

	public MinionCard(MinionCardDesc desc) {
		super(desc);
		setAttribute(Attribute.BASE_ATTACK, desc.baseAttack);
		setAttribute(Attribute.BASE_HP, desc.baseHp);
		if (desc.race != null) {
			setRace(desc.race);
		}
		this.desc = desc;
	}

	protected Minion createMinion(Attribute... tags) {
		Minion minion = new Minion(this);
		for (Attribute gameTag : getAttributes().keySet()) {
			if (inheritedAttributes.contains(gameTag)) {
				minion.setAttribute(gameTag, getAttribute(gameTag));
			}
		}
		minion.setBaseAttack(getBaseAttack());
		minion.setBaseHp(getBaseHp());
		BattlecryDesc battlecry = desc.battlecry;
		if (battlecry != null) {
			BattlecryAction battlecryAction = BattlecryAction.createBattlecry(battlecry.spell, battlecry.getTargetSelection());
			battlecryAction.setResolvedLate(battlecry.resolvedLate);
			if (battlecry.condition != null) {
				battlecryAction.setCondition(battlecry.condition.create());
			}

			minion.setBattlecry(battlecryAction);
		}

		if (desc.deathrattle != null) {
			minion.addDeathrattle(desc.deathrattle);
		}
		if (desc.trigger != null) {
			minion.setSpellTrigger(desc.trigger.create());
		} else if (desc.aura != null) {
			minion.setSpellTrigger(desc.aura.create());
		}
		if (desc.cardCostModifier != null) {
			minion.setCardCostModifier(desc.cardCostModifier.create());
		}
		return minion;
	}

	public int getAttack() {
		return getBaseAttack() + getAttributeValue(Attribute.ATTACK_BONUS);
	}

	public int getBaseAttack() {
		return getAttributeValue(Attribute.BASE_ATTACK);
	}

	public int getBaseHp() {
		return getAttributeValue(Attribute.BASE_HP);
	}

	@Override
	public PlayCardAction play() {
		return new PlayMinionCardAction(getCardReference());
	}

	public void setRace(Race race) {
		setAttribute(Attribute.RACE, race);
	}

	public Minion summon() {
		return createMinion();
	}

}