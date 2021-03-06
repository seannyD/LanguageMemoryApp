/*
 * Copyright (C) 2014 Language In Interaction
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package nl.ru.languageininteraction.language.client.model;

import java.util.HashMap;
import java.util.Set;

/**
 * @since Oct 14, 2014 1:11:22 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class UserResults {

    private final HashMap<String, String> metadataValues = new HashMap<>();
    private GameData gameData = new GameData();
    private StimuliGroup pendingStimuliGroup = null;
    private String scoreLog = "";

    public void clearResults() {
        gameData = new GameData();
    }

    public void clearMetadata() {
        metadataValues.clear();
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setMetadataValue(String key, String value) {
        metadataValues.put(key, value);
    }

    public String getMetadataValue(String key) {
        return metadataValues.get(key);
    }

    public Set<String> getMetadataKeys() {
        return metadataValues.keySet();
    }

    public StimuliGroup getPendingStimuliGroup() {
        return pendingStimuliGroup;
    }

    public void setPendingStimuliGroup(StimuliGroup pendingStimuliGroup) {
        this.pendingStimuliGroup = pendingStimuliGroup;
    }

    public void updateBestScore(int bestScore) {
        gameData.setBestScore((gameData.getBestScore() < bestScore) ? bestScore : gameData.getBestScore());
    }

    public String getScoreLog() {
        return scoreLog;
    }

    public void setScoreLog(String scoreLog) {
        this.scoreLog = scoreLog;
    }
}
