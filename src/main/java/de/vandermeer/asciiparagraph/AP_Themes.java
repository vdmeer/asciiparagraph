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

package de.vandermeer.asciiparagraph;

import de.vandermeer.asciithemes.a7.A7_Frames_Doc;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.skb.interfaces.translators.target.Text2Html;
import de.vandermeer.skb.interfaces.translators.target.Text2Latex;

/**
 * Collection of themes for an {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.1.0
 */
public interface AP_Themes {

	/**
	 * A theme for HTML target, setting the translator.
	 * @return the theme
	 */
	static AsciiParagraphTheme html(){
		return new AsciiParagraphTheme() {
			@Override
			public void apply(AP_Context ctx) {
				ctx.setTargetTranslator(Text2Html.create());
			}
		};
	}

	/**
	 * A theme formatting the paragraph like a JavaDoc documentation block.
	 * @return the theme
	 */
	static AsciiParagraphTheme javaDoc(){
		return new AsciiParagraphTheme() {
			@Override
			public void apply(AP_Context ctx) {
				ctx.setAlignment(TextAlignment.LEFT);
				ctx.setFrameTopBottomMargin(1);
				ctx.setTextTopBottomMargin(0);
				ctx.setFrame(A7_Frames_Doc.multiLineJavaDoc());
			}
		};
	}

	/**
	 * A theme for LaTeX target, setting the translator.
	 * @return the theme
	 */
	static AsciiParagraphTheme latex(){
		return new AsciiParagraphTheme() {
			@Override
			public void apply(AP_Context ctx) {
				ctx.setTargetTranslator(Text2Latex.create());
			}
		};
	}

	/**
	 * A theme formatting the paragraph like a multi line documentation block (as used for instance in Java and C++).
	 * @return the theme
	 */
	static AsciiParagraphTheme multiLineDoc(){
		return new AsciiParagraphTheme() {
			@Override
			public void apply(AP_Context ctx) {
				ctx.setAlignment(TextAlignment.LEFT);
				ctx.setFrameTopBottomMargin(1);
				ctx.setTextTopBottomMargin(0);
				ctx.setFrame(A7_Frames_Doc.multiLine());
			}
		};
	}

	/**
	 * A theme formatting the paragraph like a single line documentation block (as used for instance in Java and C++).
	 * @return the theme
	 */
	static AsciiParagraphTheme singleLineDoc(){
		return new AsciiParagraphTheme() {
			@Override
			public void apply(AP_Context ctx) {
				ctx.setAlignment(TextAlignment.LEFT);
				ctx.setFrameTopBottomMargin(1);
				ctx.setTextTopBottomMargin(0);
				ctx.setFrame(A7_Frames_Doc.singleLine());
			}
		};
	}
}
