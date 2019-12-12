package com.github.funthomas424242.sbstarter.nitrite;

/*-
 * #%L
 * rezeptsammlung
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

import org.dizitart.no2.Document;
import org.dizitart.no2.FindOptions;
import org.dizitart.no2.Index;
import org.dizitart.no2.IndexOptions;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.RemoveOptions;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.event.ChangeListener;
import org.dizitart.no2.meta.Attributes;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class NitriteTemplate {

    protected static final Logger LOG = LoggerFactory.getLogger(NitriteTemplate.class);

    @Autowired
    protected NitriteInstanz nitriteInstanz;

    public <ET> NitriteRepository<ET> getRepository(Class<ET> type) {
        LOG.debug("Erzeuge neues Nitrite Repository für: {}", type.getName());
        final ObjectRepository<ET> tmpRepository = this.nitriteInstanz.getNitrite().getRepository(type);
        return new NitriteRepository<ET>() {

            protected ObjectRepository<ET> objectRepository = tmpRepository;

            @Override
            public WriteResult insert(ET et, ET... ets) {
                return objectRepository.insert(et, ets);
            }


            @Override
            public WriteResult update(ObjectFilter objectFilter, ET et) {
                return objectRepository.update(objectFilter, et);
            }

            @Override
            public WriteResult update(ObjectFilter objectFilter, ET et, boolean b) {
                return objectRepository.update(objectFilter, et, b);
            }

            @Override
            public WriteResult update(ObjectFilter objectFilter, Document document) {
                return objectRepository.update(objectFilter, document);
            }

            @Override
            public WriteResult update(ObjectFilter objectFilter, Document document, boolean b) {
                return objectRepository.update(objectFilter, document, b);
            }

            @Override
            public WriteResult remove(ObjectFilter objectFilter) {
                return objectRepository.remove(objectFilter);
            }

            @Override
            public WriteResult remove(ObjectFilter objectFilter, RemoveOptions removeOptions) {
                return objectRepository.remove(objectFilter, removeOptions);
            }

            @Override
            public Cursor<ET> find() {
                return objectRepository.find();
            }

            @Override
            public Cursor<ET> find(ObjectFilter objectFilter) {
                return objectRepository.find(objectFilter);
            }

            @Override
            public Cursor<ET> find(FindOptions findOptions) {
                return objectRepository.find(findOptions);
            }

            @Override
            public Cursor<ET> find(ObjectFilter objectFilter, FindOptions findOptions) {
                return objectRepository.find(objectFilter, findOptions);
            }

            @Override
            public Class<ET> getType() {
                return objectRepository.getType();
            }

            @Override
            public NitriteCollection getDocumentCollection() {
                return objectRepository.getDocumentCollection();
            }

            @Override
            public void createIndex(String s, IndexOptions indexOptions) {
                objectRepository.createIndex(s, indexOptions);
            }

            @Override
            public void rebuildIndex(String s, boolean b) {
                objectRepository.rebuildIndex(s, b);
            }

            @Override
            public Collection<Index> listIndices() {
                return objectRepository.listIndices();
            }

            @Override
            public boolean hasIndex(String s) {
                return objectRepository.hasIndex(s);
            }

            @Override
            public boolean isIndexing(String s) {
                return objectRepository.isIndexing(s);
            }

            @Override
            public void dropIndex(String s) {
                objectRepository.dropIndex(s);
            }

            @Override
            public void dropAllIndices() {
                objectRepository.dropAllIndices();
            }

            @Override
            public WriteResult insert(ET[] ets) {
                return objectRepository.insert(ets);
            }

            @Override
            public WriteResult update(ET et) {
                return objectRepository.update(et);
            }

            @Override
            public WriteResult update(ET et, boolean b) {
                return objectRepository.update(et, b);
            }

            @Override
            public WriteResult remove(ET et) {
                return objectRepository.remove(et);
            }

            @Override
            public ET getById(NitriteId nitriteId) {
                return objectRepository.getById(nitriteId);
            }

            @Override
            public void drop() {
                objectRepository.drop();
            }

            @Override
            public boolean isDropped() {
                return objectRepository.isDropped();
            }

            @Override
            public boolean isClosed() {
                return objectRepository.isClosed();
            }

            @Override
            public void close() {
                objectRepository.close();
            }

            @Override
            public String getName() {
                return objectRepository.getName();
            }

            @Override
            public long size() {
                return objectRepository.size();
            }

            @Override
            public void register(ChangeListener changeListener) {
                objectRepository.register(changeListener);
            }

            @Override
            public void deregister(ChangeListener changeListener) {
                objectRepository.deregister(changeListener);
            }

            @Override
            public Attributes getAttributes() {
                return objectRepository.getAttributes();
            }

            @Override
            public void setAttributes(Attributes attributes) {
                objectRepository.setAttributes(attributes);
            }
        };
    }

}

