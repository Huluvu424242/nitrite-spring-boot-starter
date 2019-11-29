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

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
class NitriteTemplateProvider {

    @ConditionalOnMissingBean
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("singleton") // Trotz default, hier soll sichergestellt werden, dass beim upgrade alles so bleibt
    public NitriteTemplate nitriteTemplate() {
        return new NitriteTemplate();
    }
}
