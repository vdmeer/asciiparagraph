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
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Standard renderer for {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160306 (06-Mar-16) for Java 1.7
 * @since      v0.0.3
 */
public class Standard_AP_Renderer extends Abstract_AP_Renderer {

	@Override
	public List<String> renderToList(AsciiParagraph ap) {
		Validate.notNull(ap);

		List<String> ret = new ArrayList<>();

		//remove all extra white spaces (more than one space, tabs, LF, CR, CR+LF
		String text = ap.getText().toString().replaceAll("\\s+", " ");

		AP_Context ctx = ap.getContext();

		int actualWidth = ctx.getActualWidth();
		char tmpPaddingLeft = 'Ļ'; //arbitrary temporary left padding character, hopefully none really uses that in context
		char tmpPaddingRight = 'Ɍ'; //arbitrary temporary right padding character, hopefully none really uses that in context

		//split lines with the actual paragraph width
		String[] textAr = StringUtils.split(WordUtils.wrap(text, actualWidth, "\n", true), "\n");

		//process each line with alignment and padding
		for(int k=0; k<textAr.length; k++){
			StrBuilder line = new StrBuilder(50);
			line.appendPadding(ctx.getPaddingLeft(), tmpPaddingLeft);

			switch(ctx.getAlignment()){
				case CENTER:
					//get a char[] with a centered string, using paddingLedft in left and right side
					char[] car = StringUtils.center(textAr[k], actualWidth, tmpPaddingLeft).toCharArray();
					//change all right padding chars to the actual tmpPaddingRight char
					for(int i=car.length-1; i>0; i--){
						if(car[i]==tmpPaddingLeft){
							car[i]=tmpPaddingRight;
						}
						else{
							break;
						}
					}
					//add this new string to the render object
					line.append(String.copyValueOf(car));
					break;
				case LEFT:
					line.appendFixedWidthPadRight(textAr[k], actualWidth, tmpPaddingRight);
					break;
				case RIGHT:
					line.appendFixedWidthPadLeft(textAr[k], actualWidth, tmpPaddingLeft);
					break;
				case JUSTIFIED:
				case JUSTIFIED_RIGHT:
					if(k==textAr.length-1){
						//last line
						if(ctx.getAlignment()==AP_Alignment.JUSTIFIED){
							//justified
							line.appendFixedWidthPadRight(textAr[k], actualWidth, tmpPaddingRight);
						}
						else{
							//justified right
							line.appendFixedWidthPadLeft(textAr[k], actualWidth, tmpPaddingLeft);
						}
					}
					else{
						String[] ar = StringUtils.split(textAr[k]);
						int length = 0;
						for(String s : ar){
							length += s.length();
						}

						//first spaces to distributed (even)
						//do that until all firsts have been consumed
						int first = ((actualWidth - length) / (ar.length-1)) * (ar.length-1);
						while(first>0){
							for(int i=0; i<ar.length-1; i++){
								if(first!=0){
									ar[i] += ' ';
									first--;
								}
							}
						}

						//second space to distributed (leftovers, as many as there are)
						//do seconds from the back to front, until all seconds have been consumed
						//reverse means do not append to the last array element!
						int second = (actualWidth - length) % (ar.length-1);
						while(second>0){
							for(int i=ar.length-2; i>0; i--){
								if(second!=0){
									ar[i] += ' ';
									second--;
								}
							}
						}
						line.append(StringUtils.join(ar));
					}
					break;
			}
			line.appendPadding(ctx.getPaddingRight(), tmpPaddingRight);

			//now change all internal padding chars (blank) to the actual in-line char
			line.replaceAll(' ', ctx.getInlineWS());

			//now change all temporary right padding characters to the actual right padding character
			int index = line.size()-1;
			while(line.lastIndexOf(tmpPaddingRight)==index){
				line.replace(index, line.size(), new String() + ctx.getRightPaddingChar());
				index--;
			}

			//now adjust the length of the line to be width (i.e. show right padding)
			while(line.size()<ctx.getWidth()){
				line.append(ctx.getRightPaddingChar());
			}

			//now change all temporary left padding characters to the actual left padding character
			index = 0;
			while(line.charAt(index)==tmpPaddingLeft){
				line.replace(index, index+1, new String() + ctx.getLeftPaddingChar());
				index++;
			}

			//now add the indentation to the line
			int count = ctx.getIndentation();
			while(count>0){
				line.insert(0, ctx.getIndentationChar());
				count--;
			}

			//now add a line start if set
			if(ctx.getLineStart()!=null){
				line.insert(0, ctx.getLineStart());
			}

			//now add a line end if set
			if(ctx.getLineEnd()!=null){
				line.append(ctx.getLineEnd());
			}

			//finally add the new line to the list
			ret.add(line.toString());
		}

		//finally add additional lines if required
		for(int i=0; i<ctx.getAddLines(); i++){
			ret.add("");
		}

		return ret;
	}

}
