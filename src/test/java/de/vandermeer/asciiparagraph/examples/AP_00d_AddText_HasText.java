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
import de.vandermeer.skb.interfaces.render.HasText;

/**
 * AsciiParagraph example demonstrating that {@link HasText} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00d_AddText_HasText implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "ad text from has text";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example creates a simple HasText object with some text, and adds it to a paragraph."
		;
	}

	@Override
	public String getName() {
		return "text-has-text";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\r\n" + 
				"class ObjectHasText implements HasText{\r\n" + 
				"	@Override\r\n" + 
				"	public String getText() {\r\n" + 
				"		return new LoremIpsum().getWords(10);\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"ap.addText(new ObjectHasText());\r\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		class ObjectHasText implements HasText{
			@Override
			public String getText() {
				return new LoremIpsum().getWords(10);
			}
		}

		ap.addText(new ObjectHasText());
		System.out.println(ap.render());
		// end::example[]
	}
}