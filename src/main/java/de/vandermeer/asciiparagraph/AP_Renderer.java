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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.interfaces.document.IsParagraphRenderer;
import de.vandermeer.skb.interfaces.transformers.textformat.Text_To_FormattedText;

/**
 * Standard renderer for {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.0 build 170404 (04-Apr-17) for Java 1.8
 * @since      v0.1.0
 */
public interface AP_Renderer extends IsParagraphRenderer {

	/**
	 * Sets a new line separator for the renderer, overwriting any separator a paragraph defines.
	 * @param separator the new separator, ignored if blank
	 * @return self to allow chaining
	 */
	AP_Renderer setLineSeparator(String separator);

	/**
	 * Returns the current set line separator.
	 * @return the line separator, null if none set
	 */
	String getLineSeparator();

	/**
	 * Renders an {@link AsciiParagraph}.
	 * Each line will have text according to width.
	 * Any padding (left or right) and start/end strings will add to the line width.
	 * To use a calculated width for rendering use {@link AP_Renderer#renderAsCollection(String, AP_Context, int)}.
	 * @param text the text to render, cannot be null
	 * @param ctx context of the original paragraph with relevant settings, cannot be null
	 * @return a single string with the rendered paragraph
	 * @throws {@link NullPointerException} if text or context where null
	 */
	default String render(String text, AP_Context ctx){
		Validate.notNull(text);
		Validate.notNull(ctx);
		return this.render(text, ctx, ctx.getWidth());
	}

	/**
	 * Renders an {@link AsciiParagraph}.
	 * Each line will have text according to width.
	 * Any padding (left or right) and start/end strings will add to the line width.
	 * @param text the text to render, cannot be null
	 * @param ctx context of the original paragraph with relevant settings, cannot be null
	 * @param width maximum line width, excluding any extra strings and padding
	 * @return a single string with the rendered paragraph
	 * @throws {@link NullPointerException} if text or context where null
	 */
	default String render(String text, AP_Context ctx, int width){
		Validate.notNull(text);
		Validate.notNull(ctx);

		Collection<StrBuilder> coll = this.renderAsCollection(text, ctx, width);
		String fileSeparator = this.getLineSeparator();
		if(fileSeparator==null){
			fileSeparator = ctx.getLineSeparator();
		}
		if(fileSeparator==null){
			fileSeparator = System.lineSeparator();
		}
		return new StrBuilder().appendWithSeparators(coll, fileSeparator).build();
	}

	/**
	 * Renders an {@link AsciiParagraph}.
	 * Each line will have text according to width.
	 * Any padding (left or right) and start/end strings will add to the line width.
	 * To use a calculated width for rendering use {@link AP_Renderer#renderAsCollection(String, AP_Context, int)}.
	 * @param text the text to render, cannot be null
	 * @param ctx context of the original paragraph with relevant settings, cannot be null
	 * @return collection of lines, each as a {@link StrBuilder}
	 * @throws {@link NullPointerException} if text or context where null
	 */
	default Collection<StrBuilder> renderAsCollection(String text, AP_Context ctx){
		Validate.notNull(text);
		Validate.notNull(ctx);
		return this.renderAsCollection(text, ctx, ctx.getWidth());
	}

	/**
	 * Renders an {@link AsciiParagraph}.
	 * Each line will have text according to width.
	 * Any padding (left or right) and start/end strings will add to the line width.
	 * @param text the text to render, cannot be null
	 * @param ctx context of the original paragraph with relevant settings, cannot be null
	 * @param width maximum line width, excluding any extra strings and padding
	 * @return collection of lines, each as a {@link StrBuilder}
	 * @throws {@link NullPointerException} if text or context where null
	 */
	default Collection<StrBuilder> renderAsCollection(String text, AP_Context ctx, int width){
		Validate.notNull(text);
		Validate.notNull(ctx);

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

		Collection<StrBuilder> ret = Text_To_FormattedText.create(
				width,
				ctx.getAlignment().getMapping(),
				ctx.getFormat().getMapping(),
				ctx.getPaddingLeftChar(),
				ctx.getPaddingRightChar(),
				ctx.getInnerWsChar(),
				ctx.getHangingParaIndent(),
				ctx.getFirstLineIndent(),
				ctx.getDropCapLib().getDropCap(text.charAt(0)),
				3,
				1,
				null)
		.transform(text);

		int calcW = 0;
		for(StrBuilder sb : ret){
			//add text margins left and right
			sb.insert(0, new StrBuilder().appendPadding(ctx.getTextLeftMargin(), ctx.getTextLeftChar()));
			sb.appendPadding(ctx.getTextRightMargin(), ctx.getTextRightChar());

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
		return new AP_Renderer() {
			String lineSeparator = null;

			@Override
			public AP_Renderer setLineSeparator(String separator) {
				if(!StringUtils.isBlank(separator)){
					this.lineSeparator = separator;
				}
				return this;
			}

			@Override
			public String getLineSeparator() {
				return this.lineSeparator;
			}
		};
	}
}
