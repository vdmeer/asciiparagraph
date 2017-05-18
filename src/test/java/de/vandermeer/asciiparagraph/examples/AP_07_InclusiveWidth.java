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
import de.vandermeer.asciiparagraph.AP_Context;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiParagraph example demonstrating inclusive width.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_07_InclusiveWidth implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "setting paragraph width inclusive";
	}

	@Override
	public Object getLongDescription() {
		return
				"The standard render method `render()` renders the paragraph to the given width.\n" + 
				"When the width is changing, the width of the overall paragraph changes.\n" + 
				"If anything is added to a text line (left/right margins, start/end strings, frames), the width of the text line is reduced by the length of those additions.\n" + 
				"<br /><br />" + 
				"This example creates a paragraph with some demo text.\n" + 
				"Then it renders the output.\n" + 
				"Then, in three steps, it adds a start string, an end string, and a left text margin.\n" + 
				"The output is then printed for each step.\n" + 
				"<br /><br />" + 
				"Since the length to which the paragraph is rendered does not change (we are using 50 for each render call), the text width is continuously shrinking."
		;
	}

	@Override
	public String getName() {
		return "width-inclusive";
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context();\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(new LoremIpsum().getWords(29));\n" + 
				"\n" + 
				"System.out.println(ap.render(50));\n" + 
				"\n" + 
				"ctx.setStartString(\"// \");\n" + 
				"System.out.println(ap.render(50));\n" + 
				"\n" + 
				"ctx.setEndString(\" -->\");\n" + 
				"System.out.println(ap.render(50));\n" + 
				"\n" + 
				"ctx.setTextLeftMargin(10);\n" + 
				"System.out.println(ap.render(50));"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render(50));

		ctx.setStartString("// ");
		System.out.println(ap.render(50));

		ctx.setEndString(" -->");
		System.out.println(ap.render(50));

		ctx.setTextLeftMargin(10);
		System.out.println(ap.render(50));
		// end::example[]
	}
}
