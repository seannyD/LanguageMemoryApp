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
package nl.ru.languageininteraction.synaesthesia.client.service;

import java.util.Date;
import nl.ru.languageininteraction.language.client.LanguageDataProvider;
import nl.ru.languageininteraction.language.client.model.RoundData;
import nl.ru.languageininteraction.language.client.model.UserResults;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @since Oct 31, 2014 4:33:24 PM (creation date)
 * @author Peter Withers <p.withers@psych.ru.nl>
 */
public class ResultsSerialiserTest {

    /**
     * Test of serialise method, of class ResultsSerialiser.
     */
    @Test
    public void testSerialise() {
        System.out.println("serialise");
        UserResults userResults = new UserResults();
        final String postName_email = "postName_email";
        userResults.setMetadataValue(postName_email, "postName@email");
        userResults.getGameData().addRoundData(new RoundData(LanguageDataProvider.LanguageSample.fij, LanguageDataProvider.LanguageSample.cmn, new LanguageDataProvider.LanguageSample[]{LanguageDataProvider.LanguageSample.arz, LanguageDataProvider.LanguageSample.spa, LanguageDataProvider.LanguageSample.deu}, new Date(9999999), 123456));
        userResults.getGameData().addRoundData(new RoundData(LanguageDataProvider.LanguageSample.cmn, LanguageDataProvider.LanguageSample.spa, new LanguageDataProvider.LanguageSample[]{LanguageDataProvider.LanguageSample.cmn, LanguageDataProvider.LanguageSample.deu, LanguageDataProvider.LanguageSample.fij}, new Date(9999999), 123456));
        ResultsSerialiser instance = new ResultsSerialiser() {
            @Override
            protected String formatDate(Date date) {
                return date.toString();
            }
        };
        String expResult = "postName@email	fij	cmn	arz,spa,deu,	Thu Jan 01 03:46:39 CET 1970	123456.0\n"
                + "postName@email	cmn	spa	cmn,deu,fij,	Thu Jan 01 03:46:39 CET 1970	123456.0\n";
        String result = instance.serialise(userResults, postName_email);
        System.out.println(result);
        assertEquals(expResult, result);
    }

}
