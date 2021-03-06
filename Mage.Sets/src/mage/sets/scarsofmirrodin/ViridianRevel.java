/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */

package mage.sets.scarsofmirrodin;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.ZoneChangeEvent;

/**
 *
 * @author Loki
 */
public class ViridianRevel extends CardImpl {

    public ViridianRevel (UUID ownerId) {
        super(ownerId, 132, "Viridian Revel", Rarity.UNCOMMON, new CardType[]{CardType.ENCHANTMENT}, "{1}{G}{G}");
        this.expansionSetCode = "SOM";
        this.color.setGreen(true);
        this.addAbility(new ViridianRevelTriggeredAbility());
    }

    public ViridianRevel (final ViridianRevel card) {
        super(card);
    }

    @Override
    public ViridianRevel copy() {
        return new ViridianRevel(this);
    }
}

class ViridianRevelTriggeredAbility extends TriggeredAbilityImpl {
    ViridianRevelTriggeredAbility() {
        super(Zone.BATTLEFIELD, new DrawCardSourceControllerEffect(1), true);
    }

    ViridianRevelTriggeredAbility(final ViridianRevelTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public ViridianRevelTriggeredAbility copy() {
        return new ViridianRevelTriggeredAbility(this);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.ZONE_CHANGE && ((ZoneChangeEvent)event).isDiesEvent()) {
            Card card = game.getCard(event.getTargetId());
            if (card != null && card.getCardType().contains(CardType.ARTIFACT)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRule() {
        return "Whenever an artifact is put into an opponent's graveyard from the battlefield, you may draw a card.";
    }
}