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
import org.dizitart.no2.NitriteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;

public class NitriteInstanz {

    protected static final Logger LOG = LoggerFactory.getLogger(NitriteInstanz.class);

    protected final Nitrite nitrite;

    protected NitriteInstanz(NitriteAutoConfiguration nitriteConfig) {

        LOG.debug("Starte die Nitrite Datenbank.");
        final NitriteBuilder builder = Nitrite.builder();

        LOG.debug("[Nitrite Config]\n dbFile: {} \n compressed {} \n disableautocommit {} \n username {} \n password {}\n"
            , nitriteConfig.dbfilePath
            , nitriteConfig.compressed
            , nitriteConfig.disableautocommit
            , nitriteConfig.username
            , nitriteConfig.password
        );


        if (nitriteConfig.dbfilePath != null && !nitriteConfig.dbfilePath.isEmpty()) {
            LOG.info("[Nitrite] used dbFile: {}", nitriteConfig.dbfilePath);
            builder.filePath(nitriteConfig.dbfilePath);
        } else {
            LOG.info("[Nitrite] used as in-memory database.");
        }

        if (nitriteConfig.compressed != null && nitriteConfig.compressed) {
            LOG.info("[Nitrite] will be compressed automatically.");
            builder.compressed();
        }

        if (nitriteConfig.disableautocommit != null && nitriteConfig.disableautocommit) {
            LOG.info("[Nitrite] has autocommit disabled.");
            builder.disableAutoCommit();
        }

        if (nitriteConfig.username == null || nitriteConfig.username.isEmpty() || nitriteConfig.password == null || nitriteConfig.password.isEmpty()) {
            LOG.info("[Nitrite] openOrCreate without Credentials.");
            this.nitrite = builder.openOrCreate();
        } else {
            LOG.info("[Nitrite] openOrCreate with Credentials.");
            this.nitrite = builder.openOrCreate(nitriteConfig.username, nitriteConfig.password);
        }
    }

    @PreDestroy
    protected void destroy() {
        LOG.debug("Zerstöre die Nitrite Datenbank.");
        /**
         * Shutdown Hook
         *
         * While opening the database, Nitrite registers itself to a JVM shutdown hook, which before exiting will close the
         * database without persisting any unsaved changes to the disk. This shutdown hook protects the data file from
         * corruption due to JVM shutdown before properly closing the database.
         */
        this.nitrite.close();
    }

    public Nitrite getNitrite() {
        return this.nitrite;
    }
}
