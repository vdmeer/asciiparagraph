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

import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;

/**
 * AsciiParagraph example for a simple paragraph as getting started example.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_00_Getting_Started implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "a simple paragraph";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example shows how to create a paragraph, add text, and render the paragraph.\n" + 
				"It also demonstrates that all excessive white spaces (extra blanks, tabulators, new lines) will be removed automatically.\n" + 
				"Furthermore, the paragraph will break lines automatically to the set width (in the example the default width of 80 characters).\n" + 
				"This automatic line break uses words (not characters)."
		;
	}

	@Override
	public String getName() {
		return "simple";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\n" + 
				"ap.addText(\"line	1\");\n" + 
				"ap.addText(\"2  2\");\n" + 
				"ap.addText(\"more text with	tab and \\n newline\");\n" + 
				"ap.addText(\"some more text to get it over the 80 character default width\");\n" + 
				"String rend = ap.render();\n" + 
				"System.out.println(rend);"
		;
	}

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
}
