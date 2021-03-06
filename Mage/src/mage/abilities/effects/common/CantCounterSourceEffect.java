/*
* Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:
*
*    1. Redistributions of source code must retain the above copyright notice, this list of
*       conditions and the following disclaimer.
*
*    2. Redistributions in binary form must reproduce the above copyright notice, this list
*       of conditions and the following disclaimer in the documentation and/or other materials
*       provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
* ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those of the
* authors and should not be interpreted as representing official policies, either expressed
* or implied, of BetaSteward_at_googlemail.com.
*/

package mage.abilities.effects.common;

import mage.MageObject;
import mage.abilities.Ability;
import mage.abilities.effects.ContinuousRuleModifiyingEffectImpl;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.GameEvent.EventType;
import mage.game.stack.StackObject;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class CantCounterSourceEffect extends ContinuousRuleModifiyingEffectImpl {

    public CantCounterSourceEffect() {
        super(Duration.WhileOnStack, Outcome.Benefit, false, true);
        staticText = "{this} can't be countered";
    }

    public CantCounterSourceEffect(final CantCounterSourceEffect effect) {
        super(effect);
    }

    @Override
    public CantCounterSourceEffect copy() {
        return new CantCounterSourceEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public String getInfoMessage(Ability source, GameEvent event, Game game) {
        StackObject stackObject = game.getStack().getStackObject(event.getTargetId());
        MageObject sourceObject = game.getObject(source.getSourceId());
        if (stackObject != null && sourceObject != null) {
            return sourceObject.getLogName() + " can't be countered by " + stackObject.getName();
        }
        return staticText;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (event.getType() == EventType.COUNTER) {
            StackObject stackObject = game.getStack().getStackObject(event.getTargetId());
            if (stackObject != null) {
                if (stackObject.getSourceId().equals(source.getSourceId())) {
                    return true;
                }
            }
        }
        return false;
    }

}
