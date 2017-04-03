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

import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;

/**
 * AsciiParagraph example for a simple paragraph as getting started example.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00_Getting_Started implements StandardExampleAsCmd {

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		ap.addText("line	1");
		ap.addText("2  2");
		ap.addText("more text with	tab and \n newline");
		ap.addText("some more text to get it over the 80 character default width");
		String rend = ap.render();
		System.out.println(rend);
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AsciiParagraph ap = new AsciiParagraph();",
				"ap.addText(\"line\t1\");",
				"ap.addText(\"2  2\");",
				"ap.addText(\"more text with\ttab and \\n newline\");",
				"ap.addText(\"some more text to get it over the 80 character default width\");",
				"String rend = ap.render();",
				"System.out.println(rend);",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "a simple paragraph";
	}

	@Override
	public String getID() {
		return "simple";
	}
}
