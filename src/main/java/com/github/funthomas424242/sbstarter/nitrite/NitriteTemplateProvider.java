package com.github.funthomas424242.sbstarter.nitrite;

/*-
 * #%L
 * Nitrite Spring Boot Starter
 * %%
 * Copyright (C) 2019 PIUG
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.dizitart.no2.Nitrite;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
class NitriteTemplateProvider {

    @ConditionalOnClass({Nitrite.class})
    @ConditionalOnMissingBean
    @Bean
    public NitriteTemplate nitriteTemplate() {
        return new NitriteTemplate();
    }

    @ConditionalOnClass({Nitrite.class})
    @ConditionalOnMissingBean
    @Bean
    public NitriteInstanz nitriteInstanz(final NitriteAutoConfiguration config) {
        return new NitriteInstanz(config);
    }
}
