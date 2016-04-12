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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.text.StrBuilder;

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.StandardExample;
import de.vandermeer.skb.interfaces.render.HasTextCluster;

/**
 * AsciiParagraph example demonstrating that {@link HasTextCluster} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class AP_00e_AddText_HasTextCluster implements StandardExample {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		class ObjectHasTextCluster implements HasTextCluster{
			@Override
			public Collection<String> getTextAsCollection() {
				ArrayList<String> text = new ArrayList<>();
				text.add(new LoremIpsum().getWords(10));
				text.add(new LoremIpsum().getWords(10));
				text.add(new LoremIpsum().getWords(10));
				return text;
			}
		}

		ap.addText(new ObjectHasTextCluster());
		System.out.println(ap.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiParagraph ap = new AsciiParagraph();",
				"class ObjectHasTextCluster implements HasTextCluster{",
				"	@Override",
				"	public Collection<String> getTextAsCollection() {",
						"ArrayList<String> text = new ArrayList<>();",
						"text.add(new LoremIpsum().getWords(10));",
						"text.add(new LoremIpsum().getWords(10));",
						"text.add(new LoremIpsum().getWords(10));",
						"return text;",
				"	}",
				"}",
				"",
				"ap.addText(new ObjectHasTextCluster());",
				"System.out.println(ap.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}