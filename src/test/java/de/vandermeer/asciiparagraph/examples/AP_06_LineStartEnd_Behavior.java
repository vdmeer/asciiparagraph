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
 * AsciiParagraph example demonstrating line start/end behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_06_LineStartEnd_Behavior implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "setting the line-end of a paragraph";
	}

	@Override
	public Object getLongDescription() {
		return
				"The implementation allows to add a start and/or end string to each text line.\n" + 
				"The start string is added before the text and any left text margin.\n" + 
				"The end string is added after the text and any right text margin.\n" + 
				"The default is no added text.\n" + 
				"<br /><br/>" + 
				"Start and end strings can be set any time, they are only considered when the paragraph is rendered.\n" + 
				"<br /><br/>" + 
				"This example creates a paragraph context and then a paragraph with some demo text.\n" + 
				"It then prints the default settings (no added string).\n" + 
				"This is followed by setting the start string to \"// \", printing the output.\n" + 
				"This is followed by setting the end string to \" -->\", printing the output.\n" + 
				"This is followed by setting the start string to `null`, which effectively removes the start string, printing the output."
		;
	}

	@Override
	public String getName() {
		return "line-end";
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context();\n" + 
				"ctx.setAlignment(TextAlignment.JUSTIFIED);\n" + 
				"ctx.setWidth(50);\n" + 
				"\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(new LoremIpsum().getWords(29));\n" + 
				"\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setStartString(\"// \");\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setEndString(\" -->\");\n" + 
				"System.out.println(ap.render());\n" + 
				"\n" + 
				"ctx.setStartString(null);\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		ctx.setAlignment(TextAlignment.JUSTIFIED);
		ctx.setWidth(50);

		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render());

		ctx.setStartString("// ");
		System.out.println(ap.render());

		ctx.setEndString(" -->");
		System.out.println(ap.render());

		ctx.setStartString(null);
		System.out.println(ap.render());
		// end::example[]
	}
}
