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
import org.dizitart.no2.IndexType;
import org.dizitart.no2.mapper.Mappable;
import org.dizitart.no2.mapper.NitriteMapper;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.io.Serializable;
import java.util.Objects;


@Indices({
    @Index(value = "titel", type = IndexType.NonUnique),
    @Index(value = "id", type = IndexType.Unique)
})
public class Rezept implements Serializable, Mappable {

    @Id
    protected long id;

    protected String titel;

    protected String tag;

    public Rezept() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitel(final String titel) {
        this.titel = titel;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public Document write(NitriteMapper mapper) {
        Document document = new Document();
        document.put("id", getId());
        document.put("titel", getTitel());
        document.put("tag", getTag());
        return document;
    }

    @Override
    public void read(NitriteMapper mapper, Document document) {
        if (document != null) {
            setId((long) document.get("id"));
            setTitel((String) document.get("titel"));
            setTag((String) document.get("tag"));
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rezept)) return false;
        Rezept rezept = (Rezept) o;
        return id == rezept.id &&
            titel.equals(rezept.titel) &&
            Objects.equals(tag, rezept.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titel, tag);
    }


}
