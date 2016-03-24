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
import de.vandermeer.asciiparagraph.AP_Format;
import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.asciiparagraph.dropcaps.FigletOldBanner_6L;
import de.vandermeer.skb.interfaces.StandardExample;

/**
 * AsciiParagraph example demonstrating different paragraph formats.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class AP_Format_Behavior implements StandardExample {

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context pc = new AP_Context();
		AsciiParagraph ap = new AsciiParagraph(pc);
		ap.addText(new LoremIpsum().getWords(29));

		System.out.println(ap.render(35));

		pc.setFormat(AP_Format.FIRST_LINE);
		System.out.println(ap.render(35));

		pc.setFormat(AP_Format.HANGING);
		System.out.println(ap.render(35));

		pc.setFormat(AP_Format.DROPCAP);
		System.out.println(ap.render(35));

		pc.setDropCapLib(new FigletOldBanner_6L());
		System.out.println(ap.render(35));
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AP_Context pc = new AP_Context();",
				"AsciiParagraph ap = new AsciiParagraph(pc);",
				"ap.addText(new LoremIpsum().getWords(29));",
				"",
				"System.out.println(ap.render(35));",
				"",
				"pc.setFormat(AP_Format.FIRST_LINE);",
				"System.out.println(ap.render(35));",
				"",
				"pc.setFormat(AP_Format.HANGING);",
				"System.out.println(ap.render(35));",
				"",
				"pc.setFormat(AP_Format.DROPCAP);",
				"System.out.println(ap.render(35));",
				"",
				"pc.setDropCapLib(new FigletOldBanner_6L());",
				"System.out.println(ap.render(35));"
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}
