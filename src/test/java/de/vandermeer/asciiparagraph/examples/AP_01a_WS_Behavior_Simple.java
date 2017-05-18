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
 * AsciiParagraph example demonstrating white space behavior used in readme.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_01a_WS_Behavior_Simple implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "simple whitespace behavior";
	}

	@Override
	public Object getLongDescription() {
		return
				"Excessive white spaces in the paragraph text are removed.\n" + 
				"Those white spaces are additional blanks (two or more), tabulators, and any new line.\n" + 
				"<br /><br />" +
				"This example shows text being added to a paragraph with excessive white spaces.\n" + 
				"The first lines add strings with extra blanks (c2, c3, and c4).\n" + 
				"The next four lines add strings with tabulators (t1, t2, t3, and t4), using the normal tabulator character and the escaped tabulator `\\t`.\n" + 
				"<br /><br />" + 
				"Finally, a string with additional line breaks is added. The line breaks for carriage return, line feed, and escaped newline `\\n` are recognized."
		;
	}

	@Override
	public String getName() {
		return "ws-simple";
	}

	@Override
	public String getSource(){
		return
				"AsciiParagraph ap = new AsciiParagraph();\n" + 
				"\n" + 
				"ap.addText(\"c2  c2\");\n" + 
				"ap.addText(\"c3   c3\");\n" + 
				"ap.addText(\"c4    c4\");\n" + 
				"\n" + 
				"ap.addText(\"t1	t1\");\n" + 
				"ap.addText(\"t2		t2\");\n" + 
				"ap.addText(\"t3			t3\");\n" + 
				"ap.addText(\"t4\\t\\t\\t\\tt4\");\n" + 
				"\n" + 
				"ap.addText(\"word followed by \" + StringUtils.CR + \" followed by\" + StringUtils.LF + \" followed by \\n\");\n" + 
				"\n" + 
				"ap.getContext().setWidth(60).setAlignment(TextAlignment.LEFT);\n" + 
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

		ap.addText("t1	t1");
		ap.addText("t2		t2");
		ap.addText("t3			t3");
		ap.addText("t4\t\t\t\tt4");

		ap.addText("word followed by " + StringUtils.CR + " followed by" + StringUtils.LF + " followed by \n");

		ap.getContext().setWidth(60).setAlignment(TextAlignment.LEFT);
		System.out.println(ap.render());
		// end::example[]
	}
}
