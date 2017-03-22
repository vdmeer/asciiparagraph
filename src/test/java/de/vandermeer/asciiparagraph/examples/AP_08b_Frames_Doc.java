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
import de.vandermeer.asciithemes.a7.A7_Frames_Doc;
import de.vandermeer.skb.interfaces.StandardExample;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating frames resembling code documentation.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class AP_08b_Frames_Doc implements StandardExample {

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

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AP_Context ctx = new AP_Context();",
				"ctx.setAlignment(AP_Alignment.LEFT);",
				"ctx.setFrameTopBottomMargin(1);",
				"ctx.setTextTopBottomMargin(0);",
				"AsciiParagraph ap = new AsciiParagraph(ctx);",
				"ap.addText(new LoremIpsum().getWords(9));",
				"",
				"ctx.setFrame(A7_Frames_Doc.latexTB());",
				"System.out.println(ap.render(20));",
				"",
				"ctx.setFrame(A7_Frames_Doc.htmlTB());",
				"System.out.println(ap.render(20));",
				"",
				"ctx.setFrame(A7_Frames_Doc.bashTB());",
				"System.out.println(ap.render(20));",
				"",
				"ctx.setFrame(A7_Frames_Doc.bashStart2HashTB());",
				"System.out.println(ap.render(20));",
				"",
				"ctx.setFrame(A7_Frames_Doc.singleLine());",
				"System.out.println(ap.render(20));",
				"",
				"ctx.setFrame(A7_Frames_Doc.multiLine());",
				"System.out.println(ap.render(20));",
				"",
				"ctx.setFrame(A7_Frames_Doc.multiLineJavaDoc());",
				"System.out.println(ap.render(20));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}
}
