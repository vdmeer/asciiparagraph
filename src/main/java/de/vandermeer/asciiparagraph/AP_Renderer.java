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

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.interfaces.categories.is.IsCollectionStrategy;
import de.vandermeer.skb.interfaces.categories.is.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.categories.is.transformers.arrays2d.Object_To_ColumnContentArray;
import de.vandermeer.skb.interfaces.categories.is.transformers.stringformats.StringAr_To_Centered;
import de.vandermeer.skb.interfaces.categories.is.transformers.stringformats.StringAr_To_Justified;
import de.vandermeer.skb.interfaces.categories.is.transformers.stringformats.StringAr_To_LeftPadded;
import de.vandermeer.skb.interfaces.categories.is.transformers.stringformats.StringAr_To_RightPadded;

/**
 * Renders a paragraph.
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
	 * Renders an {@link AsciiParagraph}.
	 * @param ap the paragraph to render
	 * @return collection of lines, each as a {@link StrBuilder}
	 */
	default Collection<StrBuilder> render(AsciiParagraph ap) {
		Validate.notNull(ap);

		//remove all extra white spaces (more than one space, tabs, LF, CR, CR+LF
		String text = ap.getText().toString().replaceAll("\\s+", " ");

		AP_Context ctx = ap.getContext();

		int actualWidth = ctx.getActualWidth();

		String[] textAr = Object_To_ColumnContentArray.convert(text, actualWidth);

		Collection<StrBuilder> ret = null;
		IsCollectionStrategy<?, StrBuilder> collStrat = ArrayListStrategy.create();
		switch(ctx.getAlignment()){
			case CENTER:
				ret = StringAr_To_Centered.convert(textAr, actualWidth, TMP_PADING_LEFT, TMP_PADDING_RIGHT, collStrat);
				break;
			case LEFT:
				ret = StringAr_To_LeftPadded.convert(textAr, actualWidth, TMP_PADDING_RIGHT, collStrat);
				break;
			case RIGHT:
				ret = StringAr_To_RightPadded.convert(textAr, actualWidth, TMP_PADING_LEFT, collStrat);
				break;
			case JUSTIFIED:
				ret = StringAr_To_Justified.convert(textAr, actualWidth, collStrat);
				break;
			case JUSTIFIED_RIGHT:
				ret = StringAr_To_Justified.convertLastRight(textAr, actualWidth, TMP_PADING_LEFT, collStrat);
				break;
			case JUSTIFIED_LEFT:
				ret = StringAr_To_Justified.convertLastLeft(textAr, actualWidth, TMP_PADDING_RIGHT, collStrat);
				break;
		}

		for(StrBuilder sb : ret){
			// add padding left and right
			sb.insert(0, new StrBuilder().appendPadding(ctx.getPaddingLeft(), TMP_PADING_LEFT));
			sb.appendPadding(ctx.getPaddingRight(), TMP_PADDING_RIGHT);

			//now change all internal padding chars (blank) to the actual in-line char
			sb.replaceAll(' ', ctx.getInlineWS());

			//now change all temporary right padding characters to the actual right padding character
			int index = sb.size()-1;
			while(sb.lastIndexOf(TMP_PADDING_RIGHT)==index){
				sb.replace(index, sb.size(), new String() + ctx.getRightPaddingChar());
				index--;
			}

			//now adjust the length of the line to be width (i.e. show right padding)
			while(sb.size()<ctx.getWidth()){
				sb.append(ctx.getRightPaddingChar());
			}

			//now change all temporary left padding characters to the actual left padding character
			index = 0;
			while(sb.charAt(index)==TMP_PADING_LEFT){
				sb.replace(index, index+1, new String() + ctx.getLeftPaddingChar());
				index++;
			}

			//now add the indentation to the line
			int count = ctx.getIndentation();
			while(count>0){
				sb.insert(0, ctx.getIndentationChar());
				count--;
			}

			//now add a line start if set
			if(ctx.getLineStart()!=null){
				sb.insert(0, ctx.getLineStart());
			}

			//now add a line end if set
			if(ctx.getLineEnd()!=null){
				sb.append(ctx.getLineEnd());
			}
		}

		//finally add additional lines if required
		for(int i=0; i<ctx.getAddLines(); i++){
			ret.add(new StrBuilder().append(""));
		}

		return ret;
	}

	/**
	 * Returns a new default renderer for an {@link AsciiParagraph}.
	 * @return default renderer
	 */
	static AP_Renderer create(){
		return new AP_Renderer() {};
	}
}
