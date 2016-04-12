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

import org.apache.commons.lang3.text.StrBuilder;
import org.stringtemplate.v4.ST;

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.StandardExample;
import de.vandermeer.skb.interfaces.render.RendersToClusterWidth;

/**
 * AsciiParagraph example demonstrating that {@link ST} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class AP_00i_AddText_RendersToClusterWidth implements StandardExample {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph renderToClusterWidth = new AsciiParagraph();
		renderToClusterWidth.addText(new LoremIpsum().getWords(30));

		AsciiParagraph ap = new AsciiParagraph();
		ap.getContext().setWidth(40);
		ap.addText((RendersToClusterWidth)renderToClusterWidth);
		System.out.println(ap.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiParagraph ap = new AsciiParagraph();",
				"class ObjectWHasText implements HasText{",
				"	@Override",
				"	public String getText() {",
				"		return new LoremIpsum().getWords(10);",
				"	}",
				"}",
				"",
				"ap.addText(new ObjectWHasText());",
				"System.out.println(ap.render());",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}