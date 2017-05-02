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
import de.vandermeer.asciiparagraph.AP_Context;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;

/**
 * AsciiParagraph example demonstrating inclusive width.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_07_InclusiveWidth implements StandardExampleAsCmd {

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

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AP_Context ctx = new AP_Context();",
				"AsciiParagraph ap = new AsciiParagraph(ctx);",
				"ap.addText(new LoremIpsum().getWords(29));",
				"",
				"System.out.println(ap.render(50));",
				"",
				"ctx.getStrings().setStartString(\"// \");",
				"System.out.println(ap.render(50));",
				"",
				"ctx.getStrings().setEndString(\" -->\");",
				"System.out.println(ap.render(50));",
				"",
				"ctx.getMargins().setTextLeftMargin(10);",
				"System.out.println(ap.render(50));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "setting paragraph width inclusive";
	}

	@Override
	public String getID() {
		return "width-inclusive";
	}
}
