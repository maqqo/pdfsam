/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 13/ott/2014
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
package org.pdfsam.ui.selection.multiple;

import java.util.Comparator;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfsam.i18n.DefaultI18nContext;

import javafx.beans.value.ObservableValue;

/**
 * Definition of the {@link Integer} columns of the selection table
 * 
 * @author Andrea Vacondio
 *
 */
public enum IntColumn implements SelectionTableColumn<Integer> {
    PAGES {
        public String getColumnTitle() {
            return DefaultI18nContext.getInstance().i18n("Pages");
        }

        @Override
        public ObservableValue<Integer> getObservableValue(SelectionTableRowData data) {
            return data.pages();
        }

        @Override
        public String getTextValue(Integer item) {
            if (item != null && item.intValue() > 0) {
                return ObjectUtils.toString(item);
            }
            return StringUtils.EMPTY;
        }

        public Comparator<Integer> comparator() {
            return Comparator.naturalOrder();
        }
    };
}
