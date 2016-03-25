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
 * Abstract (but fully functional) implementation of a paragraph renderer.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AbstractRenderer implements AP_Renderer {

	@Override
	public Collection<StrBuilder> render(AsciiParagraph ap, int width) {
		Validate.notNull(ap);

		//remove all extra white spaces (more than one space, tabs, LF, CR, CR+LF
		String text = ap.getText().toString().replaceAll("\\s+", " ");

		AP_Context ctx = ap.getContext();
		String[] textAr = Object_To_ColumnContentArray.convert(text, width);

		switch(ctx.getFormat()){
			case DROPCAP:
				textAr = StringAr_To_ParaDroppedCap.convert(
						textAr, width,
						ctx.getDropCapLib().getDropCap(text.charAt(0)),
						false, true, TMP_PADING_LEFT
				);
				break;
			case DROPCAP_WITH_PADDING:
				textAr = StringAr_To_ParaDroppedCap.convert(
						textAr, width,
						ctx.getDropCapLib().getDropCap(text.charAt(0)),
						true, true, TMP_PADING_LEFT
				);
				break;
			case FIRST_LINE:
				textAr = StringAr_To_ParaFirstline.convert(textAr, width, ctx.getFirstLineIndent(), TMP_PADING_LEFT);
				break;
			case HANGING:
				textAr = StringAr_To_ParaHanging.convert(textAr, width, ctx.getHangingIndent(), TMP_PADING_LEFT);
				break;
			case NONE:
				break;
		}

		Collection<StrBuilder> ret = null;
		IsCollectionStrategy<?, StrBuilder> collStrat = ArrayListStrategy.create();
		switch(ctx.getAlignment()){
			case CENTER:
				ret = StringAr_To_Centered.convert(textAr, width, TMP_PADING_LEFT, TMP_PADDING_RIGHT, collStrat);
				break;
			case LEFT:
				ret = StringAr_To_LeftPadded.convert(textAr, width, TMP_PADDING_RIGHT, collStrat);
				break;
			case RIGHT:
				ret = StringAr_To_RightPadded.convert(textAr, width, TMP_PADING_LEFT, collStrat);
				break;
			case JUSTIFIED:
				ret = StringAr_To_Justified.convert(textAr, width, collStrat);
				break;
			case JUSTIFIED_RIGHT:
				ret = StringAr_To_Justified.convertLastRight(textAr, width, TMP_PADING_LEFT, collStrat);
				break;
			case JUSTIFIED_LEFT:
				ret = StringAr_To_Justified.convertLastLeft(textAr, width, TMP_PADDING_RIGHT, collStrat);
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
				sb.replace(index, index+1, new String() + ctx.getRightPaddingChar());
				index--;
			}

			//now change all temporary left padding characters to the actual left padding character
			index = 0;
			while(sb.charAt(index)==TMP_PADING_LEFT){
				sb.replace(index, index+1, new String() + ctx.getLeftPaddingChar());
				index++;
			}

			// we changed drop cap blanks, so remove the temporary ws here as well
			if(ctx.getFormat()==AP_Format.DROPCAP || ctx.getFormat()==AP_Format.DROPCAP_WITH_PADDING){
				sb.replaceAll(TMP_PADING_LEFT, ' ');
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
}
