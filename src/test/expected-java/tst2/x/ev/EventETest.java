/**
 * Copyright (C) 2015 Michael Schnell. All rights reserved. 
 * http://www.fuin.org/
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see http://www.gnu.org/licenses/.
 */
package tst2.x.ev;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.eclipse.yasson.FieldAccessStrategy;
import org.fuin.ddd4j.ddd.EventIdConverter;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.fuin.utils4j.JaxbUtils.marshal;
import static org.fuin.utils4j.JaxbUtils.unmarshal;
import static org.fuin.utils4j.Utils4J.deserialize;
import static org.fuin.utils4j.Utils4J.serialize;

// CHECKSTYLE:OFF
public final class EventETest {

	@Test
	public final void testSerializeDeserialize() {

		// PREPARE
		final EventE original = createTestee();

		// TEST
		final EventE copy = deserialize(serialize(original));

		// VERIFY
		assertThat(original).isEqualTo(copy);
		assertThat(original.getA()).isEqualTo(copy.getA());
		assertThat(original.getB()).isEqualTo(copy.getB());

	}

	@Test
	public final void testMarshalUnmarshalXml() {

		// PREPARE
		final EventE original = createTestee();

		// TEST
		final String xml = marshal(original, createAdapter(), EventE.class);
		final EventE copy = unmarshal(xml, createAdapter(), EventE.class);

		// VERIFY
		assertThat(original).isEqualTo(copy);
		assertThat(original.getA()).isEqualTo(copy.getA());
		assertThat(original.getB()).isEqualTo(copy.getB());

	}

	@Test
	public final void testMarshalUnmarshalJson() {

		// PREPARE
		final EventE original = createTestee();

		final JsonbConfig config = new JsonbConfig()
				.withAdapters(new EventIdConverter())
				.withPropertyVisibilityStrategy(new FieldAccessStrategy());
		final Jsonb jsonb = JsonbBuilder.create(config);

		// TEST
		final String json = jsonb.toJson(original, EventE.class);
		final EventE copy = jsonb.fromJson(json, EventE.class);

		// VERIFY
		assertThat(original).isEqualTo(copy);
		assertThat(original.getA()).isEqualTo(copy.getA());
		assertThat(original.getB()).isEqualTo(copy.getB());

	}

	private EventE createTestee() {
		// TODO Set test values
		final MyString a = new MyString("abc");
		final MyString b = new MyString("123");
		return new EventE(a, b);
	}

	protected final XmlAdapter<?, ?>[] createAdapter() {
		return new XmlAdapter[] { };
	}

}
// CHECKSTYLE:ON
