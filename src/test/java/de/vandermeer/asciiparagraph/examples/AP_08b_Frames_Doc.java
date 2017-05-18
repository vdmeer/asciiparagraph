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
import de.vandermeer.asciithemes.a7.A7_Frames_Doc;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating frames resembling code documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_08b_Frames_Doc implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "frames ressembling several documentation formats";
	}

	@Override
	public Object getLongDescription() {
		return "Frames are more versatile than simply adding boxes.\n" + 
				"They can for instance also add any type of string.\n" + 
				"The following examples are using frames to realize code documentation blocks.\n" + 
				"<br /><br />" + 
				"<br /><br />" + 
				"The following code creates a paragraph and then uses different frames to generate code documentation blocks:\n" + 
				"<br /><br />" +
				"- TeX/LaTeX comments (line 8)<br />\n" + 
				"- HTML comment block (line 11)<br />\n" + 
				"- bash scripts (line 14)<br />\n" + 
				"- bash scripts with double hashmark start (line 17)<br />\n" + 
				"- classic single line comment block (as found for instance in C++ and Java, line 20)<br />\n" + 
				"- classic multi-line comment block (as found for instance in C++ and Java, line 23)<br />\n" + 
				"- Javadoc comment block (line 26)<br />";
	}

	@Override
	public String getName() {
		return "frames-doc";
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context();\n" + 
				"ctx.setAlignment(TextAlignment.LEFT);\n" + 
				"ctx.setFrameTopBottomMargin(1);\n" + 
				"ctx.setTextTopBottomMargin(0);\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(new LoremIpsum().getWords(9));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.latexTB());\n" + 
				"System.out.println(ap.render(20));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.htmlTB());\n" + 
				"System.out.println(ap.render(20));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.bashTB());\n" + 
				"System.out.println(ap.render(20));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.bashStart2HashTB());\n" + 
				"System.out.println(ap.render(20));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.singleLine());\n" + 
				"System.out.println(ap.render(20));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.multiLine());\n" + 
				"System.out.println(ap.render(20));\n" + 
				"\n" + 
				"ctx.setFrame(A7_Frames_Doc.multiLineJavaDoc());\n" + 
				"System.out.println(ap.render(20));"
		;
	}

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		ctx.setAlignment(TextAlignment.LEFT);
		ctx.setFrameTopBottomMargin(1);
		ctx.setTextTopBottomMargin(0);
		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText(new LoremIpsum().getWords(9));

		ctx.setFrame(A7_Frames_Doc.latexTB());
		System.out.println(ap.render(20));

		ctx.setFrame(A7_Frames_Doc.htmlTB());
		System.out.println(ap.render(20));

		ctx.setFrame(A7_Frames_Doc.bashTB());
		System.out.println(ap.render(20));

		ctx.setFrame(A7_Frames_Doc.bashStart2HashTB());
		System.out.println(ap.render(20));

		ctx.setFrame(A7_Frames_Doc.singleLine());
		System.out.println(ap.render(20));

		ctx.setFrame(A7_Frames_Doc.multiLine());
		System.out.println(ap.render(20));

		ctx.setFrame(A7_Frames_Doc.multiLineJavaDoc());
		System.out.println(ap.render(20));
		// end::example[]
	}
}
