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
import de.vandermeer.asciithemes.TA_FrameOptions;
import de.vandermeer.asciithemes.u8.U8_Frames;
import de.vandermeer.skb.interfaces.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating frames.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.0-SNAPSHOT build 170331 (31-Mar-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_08a_Frames implements StandardExampleAsCmd {

	@Override
	public void showOutput(){
		// tag::example[]
		AP_Context ctx = new AP_Context();
		ctx.setAlignment(TextAlignment.CENTER);
		ctx.setFrame(U8_Frames.borderLight());
		ctx.setFrameTopBottomMargin(1);
		ctx.setTextTopBottomMargin(1);
		ctx.setTextLeftRightMargin(1);

		AsciiParagraph ap = new AsciiParagraph(ctx);
		ap.addText(new LoremIpsum().getWords(9));
		System.out.println(ap.render(25));

		ctx.setFrameMode(TA_FrameOptions.THEME_CORNERS_ONLY);
		ctx.setFrameTopBottomMargin(0);
		ctx.setTextTopBottomMargin(0);
		ctx.setTextLeftRightMargin(0);
		System.out.println(ap.render(25));

		ctx.setFrameMode(TA_FrameOptions.THEME_LINE_TOPBOTTOM);
		System.out.println(ap.render(25));
		// end::example[]
	}

	@Override
	public StrBuilder getSource(){
		String[] source = new String[]{
				"AP_Context ctx = new AP_Context();",
				"ctx.setAlignment(AP_Alignment.CENTER);",
				"ctx.setFrame(UTF_Frames.borderLight());",
				"ctx.setFrameTopBottomMargin(1);",
				"ctx.setTextTopBottomMargin(1);",
				"ctx.setTextLeftRightMargin(1);",
				"",
				"AsciiParagraph ap = new AsciiParagraph(ctx);",
				"ap.addText(new LoremIpsum().getWords(9));",
				"System.out.println(ap.render(25));",
				"",
				"ctx.setFrameMode(TA_FrameOptions.THEME_CORNERS_ONLY);",
				"ctx.setFrameTopBottomMargin(0);",
				"ctx.setTextTopBottomMargin(0);",
				"ctx.setTextLeftRightMargin(0);",
				"System.out.println(ap.render(25));",
				"",
				"ctx.setFrameMode(TA_FrameOptions.THEME_LINE_TOPBOTTOM);",
				"System.out.println(ap.render(25));",
		};
		return new StrBuilder().appendWithSeparators(source, "\n");
	}

	@Override
	public String getDescription() {
		return "different paragraph frames";
	}

	@Override
	public String getID() {
		return "frames";
	}
}
