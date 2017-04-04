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

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating width behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00b_Width_Behavior implements StandardExampleAsCmd {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		ap.addText(new LoremIpsum().getWords(20));
		ap.getContext().setAlignment(TextAlignment.LEFT);

		ap.getContext().setWidth(50);
		System.out.println(ap.render());

		ap.getContext().setWidth(40);
		System.out.println(ap.render());

		ap.getContext().setWidth(30);
		System.out.println(ap.render());
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiParagraph ap = new AsciiParagraph();",
				"ap.addText(new LoremIpsum().getWords(20));",
				"ap.getContext().setAlignment(AP_Alignment.LEFT);",
				"",
				"ap.getContext().setWidth(50);",
				"System.out.println(ap.render());",
				"",
				"ap.getContext().setWidth(40);",
				"System.out.println(ap.render());",
				"",
				"ap.getContext().setWidth(30);",
				"System.out.println(ap.render());"
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "paragraph width behavior";
	}

	@Override
	public String getID() {
		return "width";
	}
}
