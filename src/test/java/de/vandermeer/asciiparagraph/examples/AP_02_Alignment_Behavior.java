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
 * AsciiParagraph example demonstrating alignment behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_02_Alignment_Behavior implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "text alignment behavior";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example creates a paragraph context and then a paragraph with some demo text.\n" + 
				"To demonstrate the text alignment, we then set the alignment in the context to justified, justified left, justified right, centered, left, and right\n" + 
				"and print the rendered text."
		;
	}

	@Override
	public String getName() {
		return "alignment";
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context().setWidth(39);\n" + 
				"\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(new LoremIpsum().getWords(29));\n" + 
				"\n" + 
				"ctx.setAlignment(TextAlignment.JUSTIFIED);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setAlignment(TextAlignment.JUSTIFIED_LEFT);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setAlignment(TextAlignment.JUSTIFIED_RIGHT);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setAlignment(TextAlignment.CENTER);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setAlignment(TextAlignment.LEFT);\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setAlignment(TextAlignment.RIGHT);\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context().setWidth(39);

		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText(new LoremIpsum().getWords(29));

		ctx.setAlignment(TextAlignment.JUSTIFIED);
		System.out.println(ap.render());

		ctx.setAlignment(TextAlignment.JUSTIFIED_LEFT);
		System.out.println(ap.render());

		ctx.setAlignment(TextAlignment.JUSTIFIED_RIGHT);
		System.out.println(ap.render());

		ctx.setAlignment(TextAlignment.CENTER);
		System.out.println(ap.render());

		ctx.setAlignment(TextAlignment.LEFT);
		System.out.println(ap.render());

		ctx.setAlignment(TextAlignment.RIGHT);
		System.out.println(ap.render());
		// end::example[]
	}
}
