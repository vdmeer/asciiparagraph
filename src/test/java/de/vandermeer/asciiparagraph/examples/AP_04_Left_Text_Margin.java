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
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating left text behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_04_Left_Text_Margin implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "behavior of left margin";
	}

	@Override
	public Object getLongDescription() {
		return
				"The implementation allows to change the left text margin (padding before - to the left of - each line, independent to any padding for alignment).\n" + 
				"The number of characters as well as the used character can be changed.\n" + 
				"The default values are 0 and blank ` `.\n" + 
				"<br /><br />" + 
				"This example creates a paragraph context and then a paragraph with some demo text.\n" + 
				"It then prints the default settings (no left margin).\n" + 
				"This is followed by setting the left text margin to 5, printing the output.\n" + 
				"This is followed by setting the left text margin to 10 and the character to `>`, printing the output."
		;
	}

	@Override
	public String getName() {
		return "margin-left";
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context();\n" + 
				"ctx.setAlignment(TextAlignment.LEFT);\n" + 
				"ctx.setWidth(29);\n" + 
				"\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(new LoremIpsum().getWords(29));\n" + 
				"\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setTextLeftMargin(5);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setTextLeftMargin(10);\n" + 
				"ctx.setTextLeftChar('>');\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		ctx.setAlignment(TextAlignment.LEFT);
		ctx.setWidth(29);

		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render());

		ctx.setTextLeftMargin(5);
		System.out.println(ap.render());

		ctx.setTextLeftMargin(10);
		ctx.setTextLeftChar('>');
		System.out.println(ap.render());
		// end::example[]
	}
}
