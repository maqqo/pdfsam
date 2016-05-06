/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 11/set/2014
 * Copyright 2013-2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.splitbybookmarks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sejda.model.input.PdfFileSource;
import org.sejda.model.output.DirectoryTaskOutput;
import org.sejda.model.output.ExistingOutputPolicy;
import org.sejda.model.parameter.SplitByOutlineLevelParameters;
import org.sejda.model.pdf.PdfVersion;

/**
 * @author Andrea Vacondio
 *
 */
public class SplitByOutlineLevelParametersBuilderTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void build() throws IOException {
        SplitByOutlineLevelParametersBuilder victim = new SplitByOutlineLevelParametersBuilder();
        victim.compress(true);
        DirectoryTaskOutput output = mock(DirectoryTaskOutput.class);
        victim.output(output);
        victim.existingOutput(ExistingOutputPolicy.OVERWRITE);
        victim.level(2);
        victim.regexp("regExp");
        victim.prefix("prefix");
        File file = folder.newFile("my.pdf");
        PdfFileSource source = PdfFileSource.newInstanceNoPassword(file);
        victim.source(source);
        victim.version(PdfVersion.VERSION_1_7);
        SplitByOutlineLevelParameters params = victim.build();
        assertTrue(params.isCompress());
        assertFalse(params.discardOutline());
        assertEquals(ExistingOutputPolicy.OVERWRITE, params.getExistingOutputPolicy());
        assertEquals(PdfVersion.VERSION_1_7, params.getVersion());
        assertEquals(2, params.getLevelToSplitAt());
        assertEquals("regExp", params.getMatchingTitleRegEx());
        assertEquals("prefix", params.getOutputPrefix());
        assertEquals(output, params.getOutput());
        assertEquals(source, params.getSource());
    }
}
