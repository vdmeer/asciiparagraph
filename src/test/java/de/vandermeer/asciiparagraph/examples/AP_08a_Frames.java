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
import de.vandermeer.asciithemes.TA_FrameOptions;
import de.vandermeer.asciithemes.u8.U8_Frames;
import de.vandermeer.skb.interfaces.examples.StandardExampleAsCmd;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

/**
 * AsciiParagraph example demonstrating frames.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.3
 */
public class AP_08a_Frames implements StandardExampleAsCmd {

	@Override
	public String getDescription() {
		return "different paragraph frames";
	}

	@Override
	public Object getLongDescription() {
		return "The implementation allows to add frames to a paragraph.\n" + 
				"Frame borders will be added to the left and right of each line.\n" + 
				"Top and bottom lines will be added to the top and the bottom of the paragraph.\n" + 
				"Additional margins can be set to separate the frame from any text (string margins) and to add margins around the frame.\n" + 
				"<br /><br/>\n" + 
				"The example creates a paragraph and then adds frames to it.\n" + 
				"The first setting uses a frame constructed of UTF-8 light border characters, with frame and text margins set to `1`.\n" + 
				"The second setting removes all added margins and uses the same frame in a different mode.\n" + 
				"The third setting uses the frame in yet another mode.";
	}

	@Override
	public String getName() {
		return "frames";
	}

	@Override
	public String getSource(){
		return
				"AP_Context ctx = new AP_Context();\n" + 
				"ctx.setAlignment(TextAlignment.CENTER);\n" + 
				"ctx.setFrame(U8_Frames.borderLight());\n" + 
				"ctx.setFrameTopBottomMargin(1);\n" + 
				"ctx.setTextTopBottomMargin(1);\n" + 
				"ctx.setTextLeftRightMargin(1);\n" + 
				"\n" + 
				"AsciiParagraph ap = new AsciiParagraph(ctx);\n" + 
				"ap.addText(new LoremIpsum().getWords(9));\n" + 
				"System.out.println(ap.render(25));\n" + 
				"\n" + 
				"ctx.setFrameMode(TA_FrameOptions.THEME_CORNERS_ONLY);\n" + 
				"ctx.setFrameTopBottomMargin(0);\n" + 
				"ctx.setTextTopBottomMargin(0);\n" + 
				"ctx.setTextLeftRightMargin(0);\n" + 
				"System.out.println(ap.render(25));\n" + 
				"\n" + 
				"ctx.setFrameMode(TA_FrameOptions.THEME_LINE_TOPBOTTOM);\n" + 
				"System.out.println(ap.render(25));"
		;
	}

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
}
