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
import de.vandermeer.skb.interfaces.render.DoesRenderToWidth;

/**
 * AsciiParagraph example demonstrating that {@link DoesRenderToWidth} objects are automatically added as text.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00g_AddText_DoesRenderToWidth implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "render to width";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example uses an `AsciiParagraph` itself as a DoesRenderToWidth object with some text, and adds it to a paragraph."
		;
	}

	@Override
	public String getName() {
		return "render-width";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph renderToWidth = new AsciiParagraph();\r\n" + 
				"renderToWidth.addText(new LoremIpsum().getWords(30));\r\n" + 
				"\r\n" + 
				"AsciiParagraph ap = new AsciiParagraph();\r\n" + 
				"ap.getContext().setWidth(40);\r\n" + 
				"ap.addText((DoesRenderToWidth)renderToWidth);\r\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph renderToWidth = new AsciiParagraph();
		renderToWidth.addText(new LoremIpsum().getWords(30));

		AsciiParagraph ap = new AsciiParagraph();
		ap.getContext().setWidth(40);
		ap.addText((DoesRenderToWidth)renderToWidth);
		System.out.println(ap.render());
		// end::example[]
	}
}