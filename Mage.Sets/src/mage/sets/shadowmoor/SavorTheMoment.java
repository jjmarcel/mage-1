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
package mage.sets.shadowmoor;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.turn.AddExtraTurnControllerEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.PhaseStep;
import mage.constants.Rarity;
import mage.game.Game;
import mage.game.turn.TurnMod;
import mage.players.Player;

/**
 *
 * @author LevelX2
 */
public class SavorTheMoment extends CardImpl {

    public SavorTheMoment(UUID ownerId) {
        super(ownerId, 50, "Savor the Moment", Rarity.RARE, new CardType[]{CardType.SORCERY}, "{1}{U}{U}");
        this.expansionSetCode = "SHM";

        this.color.setBlue(true);

        // Take an extra turn after this one. Skip the untap step of that turn.
        this.getSpellAbility().addEffect(new AddExtraTurnControllerEffect());
        this.getSpellAbility().addEffect(new SkipNextUntapStepSourceControllerEffect());
    }

    public SavorTheMoment(final SavorTheMoment card) {
        super(card);
    }

    @Override
    public SavorTheMoment copy() {
        return new SavorTheMoment(this);
    }
}

class SkipNextUntapStepSourceControllerEffect extends OneShotEffect {

    public SkipNextUntapStepSourceControllerEffect() {
        super(Outcome.Detriment);
        this.staticText = "Skip the untap step of that turn";
    }

    public SkipNextUntapStepSourceControllerEffect(SkipNextUntapStepSourceControllerEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            game.getState().getTurnMods().add(new TurnMod(controller.getId(), PhaseStep.UNTAP));
            return true;
        }
        return false;
    }

    @Override
    public SkipNextUntapStepSourceControllerEffect copy() {
        return new SkipNextUntapStepSourceControllerEffect(this);
    }

}
