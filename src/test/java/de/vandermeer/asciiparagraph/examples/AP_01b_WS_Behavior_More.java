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

import org.apache.commons.lang3.StringUtils;

import de.vandermeer.asciiparagraph.AsciiParagraph;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating white space behavior.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_01b_WS_Behavior_More implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "more complex whitespace behavior";
	}

	@Override
	public Object getLongDescription() {
		return
				"It does not matter how many excessive white spaces are added.\n" + 
				"This example uses more excessive white spaces for blanks and tabulators."
		;
	}

	@Override
	public String getName() {
		return "ws-more";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\n" + 
				"\n" + 
				"ap.addText(\"c2  c2\");\n" + 
				"ap.addText(\"c3   c3\");\n" + 
				"ap.addText(\"c4    c4\");\n" + 
				"ap.addText(\"c5     c5\");\n" + 
				"ap.addText(\"c6      c6\");\n" + 
				"ap.addText(\"c7       c7\");\n" + 
				"\n" + 
				"ap.addText(\"t2		t2\");\n" + 
				"ap.addText(\"t3			t3\");\n" + 
				"ap.addText(\"t4				t4\");\n" + 
				"ap.addText(\"t5					t5\");\n" + 
				"ap.addText(\"t6						t6\");\n" + 
				"ap.addText(\"t7							t7\");\n" + 
				"\n" + 
				"ap.addText(\"word followed by \" + StringUtils.CR + \" followed by\" + StringUtils.LF + \" followed by \\n\");\n" + 
				"\n" + 
				"ap.getContext().setWidth(60);\n" + 
				"ap.getContext().setAlignment(TextAlignment.LEFT);\n" + 
				"System.out.println(ap.render());"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AsciiParagraph ap = new AsciiParagraph();

		ap.addText("c2  c2");
		ap.addText("c3   c3");
		ap.addText("c4    c4");
		ap.addText("c5     c5");
		ap.addText("c6      c6");
		ap.addText("c7       c7");

		ap.addText("t2		t2");
		ap.addText("t3			t3");
		ap.addText("t4				t4");
		ap.addText("t5					t5");
		ap.addText("t6						t6");
		ap.addText("t7							t7");

		ap.addText("word followed by " + StringUtils.CR + " followed by" + StringUtils.LF + " followed by \n");

		ap.getContext().setWidth(60);
		ap.getContext().setAlignment(TextAlignment.LEFT);
		System.out.println(ap.render());
		// end::example[]
	}
}
