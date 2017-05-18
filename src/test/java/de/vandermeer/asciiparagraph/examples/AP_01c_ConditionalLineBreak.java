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
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating conditional line breaks.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_01c_ConditionalLineBreak implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "behavior with conditional linebreaks";
	}

	@Override
	public Object getLongDescription() {
		return
				"This example shows the use of HTML entities for conditional line breaks."
		;
	}

	@Override
	public String getName() {
		return "cond-linebreak";
	}

	@Override
	public String getSource(){
		return
		"AsciiParagraph ap = new AsciiParagraph();\n" + 
		"ap.getContext().setAlignment(TextAlignment.LEFT).setWidth(35);\n" + 
		"\n" + 
		"ap.addText(\"line 1<br>\");\n" + 
		"ap.addText(\"line 2<br/>\");\n" + 
		"ap.addText(\"line three \\n still line three\");\n" + 
		"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();
		ap.getContext().setAlignment(TextAlignment.LEFT).setWidth(35);

		ap.addText("line 1<br>");
		ap.addText("line 2<br/>");
		ap.addText("line three \n still line three");
		System.out.println(ap.render());
		// end::example[]
	}
}
