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

import de.svenjacobs.loremipsum.LoremIpsum;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.render.RendersToCluster;

/**
 * AsciiParagraph example demonstrating that {@link RendersToCluster} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00h_AddText_RendersToCluster implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "render to a cluster";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example creates a simple RendersToCluster object with some text, and adds it to a paragraph."
		;
	}

	@Override
	public String getName() {
		return "render-cluster";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\r\n" + 
				"class ObjectRendersToCluster implements RendersToCluster{\r\n" + 
				"	@Override\r\n" + 
				"	public Collection<String> renderAsCollection() {\r\n" + 
				"		ArrayList<String> text = new ArrayList<>();\r\n" + 
				"		text.add(new LoremIpsum().getWords(10));\r\n" + 
				"		text.add(new LoremIpsum().getWords(10));\r\n" + 
				"		text.add(new LoremIpsum().getWords(10));\r\n" + 
				"		return text;\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"ap.addText(new ObjectRendersToCluster());\r\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		class ObjectRendersToCluster implements RendersToCluster{
			@Override
			public Collection<String> renderAsCollection() {
				ArrayList<String> text = new ArrayList<>();
				text.add(new LoremIpsum().getWords(10));
				text.add(new LoremIpsum().getWords(10));
				text.add(new LoremIpsum().getWords(10));
				return text;
			}
		}

		ap.addText(new ObjectRendersToCluster());
		System.out.println(ap.render());
		// end::example[]
	}
}