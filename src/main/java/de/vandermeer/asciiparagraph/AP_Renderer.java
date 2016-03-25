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

import java.util.Collection;

import org.apache.commons.lang3.text.StrBuilder;

/**
 * Paragraph renderer interface.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public interface AP_Renderer {

	/** Temporary character for left paddings, might cause problems if found in text, set to 'Ļ'. */
	static char TMP_PADING_LEFT = 'Ļ';

	/** Temporary character for right paddings, might cause problems if found in text, set to 'Ɍ'. */
	static char TMP_PADDING_RIGHT = 'Ɍ';

	/**
	 * Renders an {@link AsciiParagraph} using the width set in the context.
	 * Each line will have text according to the width from the context.
	 * Any indentation, padding (left or right), and start/end strings will add to the line width.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToTextWidth(AsciiParagraph ap){
		return this.render(ap, ap.getContext().getWidth());
	}

	/**
	 * Renders an {@link AsciiParagraph} using the given width.
	 * Any indentation, padding (left or right), and start/end strings will add to the line width.
	 * @param width the width of text in the paragraph
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToTextWidth(AsciiParagraph ap, int width){
		return this.render(ap, width);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text).
	 * Any start/end strings will add to the line width.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToPaddingWidth(AsciiParagraph ap){
		int width = ap.getContext().getWidth() - ap.getContext().getIndentation();
		width -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		return this.render(ap, width);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text).
	 * Any start/end strings will add to the line width.
	 * @param ap the paragraph to render
	 * @param width the width of text and padding in the paragraph
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToPaddingWidth(AsciiParagraph ap, int width){
		int w = width - ap.getContext().getIndentation();
		w -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		return this.render(ap, w);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text) minus the start string length.
	 * Any end strings will add to the line width.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToStartStringWidth(AsciiParagraph ap){
		int width = ap.getContext().getWidth() - ap.getContext().getIndentation();
		width -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		width -= (ap.getContext().getLineStart()==null)?0:ap.getContext().getLineStart().length();
		return this.render(ap, width);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text) minus the start string length.
	 * Any end strings will add to the line width.
	 * @param width the width of text and padding in the paragraph and start string
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToStartStringWidth(AsciiParagraph ap, int width){
		int w = width - ap.getContext().getIndentation();
		w -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		w -= (ap.getContext().getLineStart()==null)?0:ap.getContext().getLineStart().length();
		return this.render(ap, w);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text) minus the end string length.
	 * Any start strings will add to the line width.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToEndStringWidth(AsciiParagraph ap){
		int width = ap.getContext().getWidth() - ap.getContext().getIndentation();
		width -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		width -= (ap.getContext().getLineEnd()==null)?0:ap.getContext().getLineEnd().length();
		return this.render(ap, width);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text) minus the end string length.
	 * Any start strings will add to the line width.
	 * @param width the width of text and padding in the paragraph and end string
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToEndStringWidth(AsciiParagraph ap, int width){
		int w = width - ap.getContext().getIndentation();
		w -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		w -= (ap.getContext().getLineEnd()==null)?0:ap.getContext().getLineEnd().length();
		return this.render(ap, w);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text) minus the start string length minus the end string length.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToAllInclusiveWidth(AsciiParagraph ap){
		int width = ap.getContext().getWidth() - ap.getContext().getIndentation();
		width -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		width -= (ap.getContext().getLineStart()==null)?0:ap.getContext().getLineStart().length();
		width -= (ap.getContext().getLineEnd()==null)?0:ap.getContext().getLineEnd().length();
		return this.render(ap, width);
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * Each line will have text to the width minus any indentation minus any padding character (left and/or right of the text) minus the start string length minus the end string length.
	 * @param width all inclusive width
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToAllInclusiveWidth(AsciiParagraph ap, int width){
		int w = width - ap.getContext().getIndentation();
		w -= ap.getContext().getPaddingLeft() - ap.getContext().getPaddingRight();
		w -= (ap.getContext().getLineStart()==null)?0:ap.getContext().getLineStart().length();
		w -= (ap.getContext().getLineEnd()==null)?0:ap.getContext().getLineEnd().length();
		return this.render(ap, w);
	}

	/**
	 * Renders an {@link AsciiParagraph}.
	 * Each line will have text according to width.
	 * Any padding (left or right) and start/end strings will add to the line width.
	 * To use a calculated width for rendering use one of the other render methods.
	 * @param ap the paragraph to render
	 * @param width maximum line width, excluding any extra strings and paddings
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	Collection<StrBuilder> render(AsciiParagraph ap, int width);
}
