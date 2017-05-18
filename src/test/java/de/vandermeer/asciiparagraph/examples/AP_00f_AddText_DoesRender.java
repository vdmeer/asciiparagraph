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
import de.vandermeer.skb.interfaces.render.DoesRender;

/**
 * AsciiParagraph example demonstrating that {@link DoesRender} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00f_AddText_DoesRender implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "render";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example creates a simple DoesRender object with some text, and adds it to a paragraph."
		;
	}

	@Override
	public String getName() {
		return "render";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\r\n" + 
				"class ObjectDoesRender implements DoesRender{\r\n" + 
				"	@Override\r\n" + 
				"	public String render() {\r\n" + 
				"		return new LoremIpsum().getWords(10);\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"ap.addText(new ObjectDoesRender());\r\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		class ObjectDoesRender implements DoesRender{
			@Override
			public String render() {
				return new LoremIpsum().getWords(10);
			}
		}

		ap.addText(new ObjectDoesRender());
		System.out.println(ap.render());
		// end::example[]
	}
}