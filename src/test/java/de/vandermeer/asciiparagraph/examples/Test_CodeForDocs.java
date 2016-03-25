/* Copyright 2016 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.asciiparagraph.examples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciiparagraph.AP_Context;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.render.HasTextCluster;

/**
 * Tests for ASCII Paragraph for code used in documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class Test_CodeForDocs {

	@Test
	public void test_HasTextWithMap(){
//		this.output("HasText with map behavior");

//		HasText textProvider = new HasText() {
//			Map<String, String> map = new HashMap<String, String>(){
//				private static final long serialVersionUID = 1L;{
//					put("k1", new LoremIpsum().getWords(30));
//					put("k2", new LoremIpsum().getWords(30));
//					put("k3", new LoremIpsum().getWords(30));
//			}};
//
//			@Override
//			public String getText() {
//				return Iterable_To_String.create(String.class).transform(map.values());
//			}
//		};

		HasTextCluster clusterProvider = new HasTextCluster() {
			Map<String, String> map = new HashMap<String, String>(){
				private static final long serialVersionUID = 1L;{
					put("c1", new LoremIpsum().getWords(30));
					put("c2", new LoremIpsum().getWords(30));
					put("c3", new LoremIpsum().getWords(30));
			}};

			@Override
			public Collection<String> getTextAsCollection() {
				return this.map.values();
			}

			@Override
			public Iterable<String> getTextAsIterable() {
				return this.map.values();
			}

			@Override
			public Iterator<String> getTextAsIterator() {
				return this.map.values().iterator();
			}

			@Override
			public String[] getTextAsArray() {
				return this.map.values().toArray(new String[]{});
			}
		};

		AP_Context pc = new AP_Context();
		pc.setWidth(50);

		AsciiParagraph ap = new AsciiParagraph(pc);
//		ap.addText(textProvider);
//		System.out.println(ap.render());
//		System.out.println();

		ap = new AsciiParagraph(pc);
		ap.addText(clusterProvider);
		System.out.println(ap.render());
	}
}
