package net.pferdimanzug.hearthstone.analyzer.game.spells.trigger;

import net.pferdimanzug.hearthstone.analyzer.game.entities.Actor;
import net.pferdimanzug.hearthstone.analyzer.game.events.DamageEvent;
import net.pferdimanzug.hearthstone.analyzer.game.events.GameEvent;
import net.pferdimanzug.hearthstone.analyzer.game.events.GameEventType;

public class HeroDamagedTrigger extends GameEventTrigger {

	@Override
	public GameEventType interestedIn() {
		return GameEventType.DAMAGE;
	}

	@Override
	public boolean fire(GameEvent event, Actor host) {
		DamageEvent damageEvent = (DamageEvent) event;
		return damageEvent.getVictim() == host;
	}

}