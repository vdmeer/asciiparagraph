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

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.interfaces.strategies.IsCollectionStrategy;
import de.vandermeer.skb.interfaces.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.transformers.Object_To_ColumnContentArray;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_Centered;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_Justified;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_LeftPadded;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_ParaDroppedCap;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_ParaFirstline;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_ParaHanging;
import de.vandermeer.skb.interfaces.transformers.stringformats.StringAr_To_RightPadded;

/**
 * Paragraph renderer interface.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.1.0
 */
public interface AP_Renderer {

	/** Temporary padding character required for some special formats, might cause problems if found in text, set to 'Ļ'. */
	static char TMP_PADING = 'Ļ';

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
	 * The width used is calculated by {@link AP_Context#getWidthIncTextMargins()}.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToTextMarginWidth(AsciiParagraph ap){
		return this.render(ap, ap.getContext().getWidthIncTextMargins());
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * The width used is calculated by {@link AP_Context#getWidthIncTextMargins(int)}.
	 * @param ap the paragraph to render
	 * @param width the width of text and padding in the paragraph
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToTextMarginWidth(AsciiParagraph ap, int width){
		return this.render(ap, ap.getContext().getWidthIncTextMargins(width));
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * The width used is calculated by {@link AP_Context#getWidthIncStringMargins()}.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToStartStringMarginWidth(AsciiParagraph ap){
		return this.render(ap, ap.getContext().getWidthIncStringMargins());
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * The width used is calculated by {@link AP_Context#getWidthIncStringMargins(int)}.
	 * @param width the width of text and padding in the paragraph and start string
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToStartStringMarginWidth(AsciiParagraph ap, int width){
		return this.render(ap, ap.getContext().getWidthIncStringMargins(width));
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * The width used is calculated by {@link AP_Context#getWidthAllInclusive()}.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToAllInclusiveWidth(AsciiParagraph ap){
		return this.render(ap, ap.getContext().getWidthAllInclusive());
	}

	/**
	 * Renders an {@link AsciiParagraph} using a calculated width based on context settings.
	 * The width used is calculated by {@link AP_Context#getWidthAllInclusive(int)}.
	 * @param width all inclusive width
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> renderToAllInclusiveWidth(AsciiParagraph ap, int width){
		return this.render(ap, ap.getContext().getWidthAllInclusive(width));
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
	default Collection<StrBuilder> render(AsciiParagraph ap, int width){
		Validate.notNull(ap);
		AP_Context ctx = ap.getContext();

		//remove all extra white spaces (more than one space, tabs, LF, CR, CR+LF
		String text = ap.getText().toString().replaceAll("\\s+", " ");
		//check for translators, use what is available
		if(ctx.getTargetTranslator()!=null){
			if(ctx.getTargetTranslator().getCombinedTranslator()!=null){
				text = ctx.getTargetTranslator().getCombinedTranslator().translate(text);
			}
		}
		else if(ctx.getHtmlElementTranslator()!=null){
			text = ctx.getHtmlElementTranslator().translateHtmlElements(text);
		}
		else if(ctx.getCharTranslator()!=null){
			text = ctx.getCharTranslator().translateCharacters(text);
		}

		//create the text array with wrapped lines
		String[] textAr = Object_To_ColumnContentArray.convert(text, width);

		switch(ctx.getFormat()){
			case DROPCAP:
				textAr = StringAr_To_ParaDroppedCap.convert(
						textAr, width,
						ctx.getDropCapLib().getDropCap(text.charAt(0)),
						false, true, TMP_PADING
				);
				break;
			case DROPCAP_WITH_PADDING:
				textAr = StringAr_To_ParaDroppedCap.convert(
						textAr, width,
						ctx.getDropCapLib().getDropCap(text.charAt(0)),
						true, true, TMP_PADING
				);
				break;
			case FIRST_LINE:
				textAr = StringAr_To_ParaFirstline.convert(textAr, width, ctx.getFirstLineIndent(), TMP_PADING);
				break;
			case HANGING:
				textAr = StringAr_To_ParaHanging.convert(textAr, width, ctx.getHangingParaIndent(), TMP_PADING);
				break;
			case NONE:
				break;
		}

		Collection<StrBuilder> ret = null;
		IsCollectionStrategy<?, StrBuilder> collStrat = ArrayListStrategy.create();
		switch(ctx.getAlignment()){
			case CENTER:
				ret = StringAr_To_Centered.convert(textAr, width, ctx.getPaddingLeftChar(), ctx.getPaddingRightChar(), ctx.getInnerWsChar(), collStrat);
				break;
			case LEFT:
				ret = StringAr_To_LeftPadded.convert(textAr, width, ctx.getPaddingRightChar(), ctx.getInnerWsChar(), collStrat);
				break;
			case RIGHT:
				ret = StringAr_To_RightPadded.convert(textAr, width, ctx.getPaddingLeftChar(), ctx.getInnerWsChar(), collStrat);
				break;
			case JUSTIFIED:
				ret = StringAr_To_Justified.convert(textAr, width, ctx.getInnerWsChar(), collStrat);
				break;
			case JUSTIFIED_RIGHT:
				ret = StringAr_To_Justified.convertLastRight(textAr, width, ctx.getPaddingLeftChar(), ctx.getInnerWsChar(), collStrat);
				break;
			case JUSTIFIED_LEFT:
				ret = StringAr_To_Justified.convertLastLeft(textAr, width, ctx.getPaddingRightChar(), ctx.getInnerWsChar(), collStrat);
				break;
		}

		int calcW = 0;
		for(StrBuilder sb : ret){
			//add text margins left and right
			sb.insert(0, new StrBuilder().appendPadding(ctx.getTextLeftMargin(), ctx.getTextLeftChar()));
			sb.appendPadding(ctx.getTextRightMargin(), ctx.getTextRightChar());

			// we changed drop cap blanks, so remove the temporary ws here as well
			if(ctx.getFormat()!=AP_Format.NONE){
				sb.replaceAll(TMP_PADING, ctx.getPaddingLeftChar());
			}

			//now add a line start if set and margins
			if(ctx.getStartString()!=null){
				sb.insert(0, ctx.getStartString());
			}
			sb.insert(0, new StrBuilder().appendPadding(ctx.getStringLeftMargin(), ctx.getStringLeftChar()));

			//now add a line end if set and margins
			if(ctx.getEndString()!=null){
				sb.append(ctx.getEndString());
			}
			sb.appendPadding(ctx.getStringRightMargin(), ctx.getStringRightChar());
			calcW = sb.length();
		}

		//add text margins margin
		for(int i=0; i<ctx.getTextTopMargin(); i++){
			((ArrayList<StrBuilder>)ret).add(0, new StrBuilder().appendPadding(calcW, ' '));
		}
		for(int i=0; i<ctx.getTextBottomMargin(); i++){
			ret.add(new StrBuilder().appendPadding(calcW, ' '));
		}

		//add frame
		if(ctx.getFrame()!=null){
			ret = ctx.getFrame().addFrame(ret, ctx.getFrameMode());
		}

		//add frame margins
		for(int i=0; i<ctx.getFrameTopMargin(); i++){
			((ArrayList<StrBuilder>)ret).add(0, new StrBuilder().append(""));
		}
		for(int i=0; i<ctx.getFrameBottomMargin(); i++){
			ret.add(new StrBuilder().append(""));
		}

		//finally, add horizontal frame margins
		for(StrBuilder sb : ret){
			sb.insert(0, new StrBuilder().appendPadding(ctx.getFrameLeftMargin(), ctx.getFrameLeftChar()));
			sb.appendPadding(ctx.getFrameRightMargin(), ctx.getFrameRightChar());
		}

		return ret;
	}

	/**
	 * Creates a new renderer.
	 * @return new renderer
	 */
	static AP_Renderer create(){
		return new AP_Renderer() {};
	}
}
