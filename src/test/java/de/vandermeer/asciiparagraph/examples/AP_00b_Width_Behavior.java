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

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating width behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00b_Width_Behavior implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "paragraph width behavior";
	}

	@Override
	public Object getLongDescription() {
		return
				"The width of a paragraph can be set via its context.\n" + 
				"This example creates a paragraph and then sets the width and shows the output.\n" + 
				"The first width is 50 (line 5), the second 40 (line 8), and the third 30 characters (line 11)."
		;
	}

	@Override
	public String getName() {
		return "width";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\n" + 
				"ap.addText(new LoremIpsum().getWords(20));\n" + 
				"ap.getContext().setAlignment(TextAlignment.LEFT);\n" + 
				"\n" + 
				"ap.getContext().setWidth(50);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ap.getContext().setWidth(40);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ap.getContext().setWidth(30);\n" + 
				"System.out.println(ap.render());"
		;
	}

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
}
