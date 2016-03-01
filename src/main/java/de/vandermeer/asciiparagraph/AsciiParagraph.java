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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Create and render a paragraph for text with ASCII and UTF-8 characters.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.2 build 160301 (01-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AsciiParagraph {

	/** The context with optional settings for the paragraph. */
	protected ParagraphContext ctx;

	/** The paragraph text. */
	protected StrBuilder text;

	/**
	 * Creates a new paragraph with a default context.
	 */
	public AsciiParagraph(){
		this(new ParagraphContext());
	}

	/**
	 * Creates a new paragraph.
	 * @param ctx context for the paragraph
	 * @throws NullPointerException if the argument was null
	 */
	public AsciiParagraph(ParagraphContext ctx){
		this.setContext(ctx);
		this.text = new StrBuilder(100);
	}

	/**
	 * Adds text to the paragraph.
	 * If the paragraph already has text a space character will be used to separate existing and added text.
	 * No further processing on the text is done, we let {@link #render()} deal with all extra whitespace handling.
	 * @param text text to be added to the paragraph
	 * @return self to allow chaining
	 * throws NullPointerException if the argument was null
	 * throws IllegalArgumentException if the argument was blank
	 */
	public AsciiParagraph addText(String text){
		Validate.notBlank(text);
		this.text.appendSeparator(' ').append(text);
		return this;
	}

	/**
	 * Adds text to the paragraph.
	 * The method uses reflection to look for a method render without any parameters.
	 * If found, the result of this method will be used as text.
	 * Otherwise the object's toString method is used. 
	 * @param text text to be added to the paragraph
	 * @return self to allow chaining
	 * throws NullPointerException if the argument was null
	 * throws IllegalArgumentException if the argument was blank
	 */
	public AsciiParagraph addText(Object text){
		Validate.notNull(text);

		if(text instanceof String){
			return this.addText((String)text);
		}

		try{
			Class<? extends Object> clazz = text.getClass();
			Method method = clazz.getMethod("render", new Class[0]);
			Class<?> returnType = method.getReturnType();
			if(returnType.isAssignableFrom(String.class)){
				String append = (String)method.invoke(text);
				return this.addText(append);
			}
		}
		catch(Exception ignore){
//			ignore.printStackTrace();
		}

		return this.addText(text.toString());
	}

	/**
	 * Adds text to the paragraph from a collection of object.
	 * From each member of the collection, the toString method will be used to generate text.
	 * Any newline in any text will be removed.
	 * @param text collection of objects with text
	 * @return self to allow chaining
	 * throws NullPointerException if the argument was null or had null elements
	 * throws IllegalArgumentException if any text to be added was blank
	 */
	public AsciiParagraph addText(Collection<Object> text){
		Validate.notNull(text);
		Validate.noNullElements(text);

		for(Object o : text){
			this.addText(o);
		}
		return this;
	}

	/**
	 * Adds text to the paragraph provided by the input object
	 * @param object an object providing text for the paragraph
	 * @return self to allow chaining
	 * throws NullPointerException if the argument was null or if the object did only provide null as text
	 * throws IllegalArgumentException if any text provided was blank
	 */
	public AsciiParagraph addText(HasText object){
		Validate.notNull(object);

		String text = object.getText();
		if(StringUtils.isNotBlank(text)){
			return this.addText(text);
		}

		Collection<String> collection = object.getTextCollection();
		if(collection!=null){
			for(String s : collection){
				this.addText(s);
			}
			return this;
		}

		throw new IllegalArgumentException("HasText provider did not provide any text");
	}

	/**
	 * Sets the paragraph context.
	 * @param ctx context
	 * @return self to allow chaining
	 * @throws NullPointerException if the argument was null
	 */
	public AsciiParagraph setContext(ParagraphContext ctx){
		Validate.notNull(ctx);
		this.ctx = ctx;
		return this;
	}

	/**
	 * Returns the paragraph context.
	 * @return context, null if not set
	 */
	public ParagraphContext getContext(){
		return this.ctx;
	}

	/**
	 * Returns the rendered paragraph as a list of lines.
	 * The method uses arbitrary characters for indentation, left padding, and right padding.
	 * Those characters should not be used as actual padding characters, since the result might not be as required.
	 * The characters are:
	 * <ul>
	 * 		<li>left padding - Ļ, Unicode U+013B</li>
	 * 		<li>right padding - Ɍ, Unicode U+024C</li>
	 * </ul>
	 * @return list of lines for the rendered paragraph
	 */
	public List<String> renderToList(){
		List<String> ret = new ArrayList<>();

		//remove all extra white spaces (more than one space, tabs, LF, CR, CR+LF
		String text = this.text.toString().replaceAll("\\s+", " ");

		int actualWidth = this.ctx.getActualWidth();
		char tmpPaddingLeft = 'Ļ'; //arbitrary temporary left padding character, hopefully none really uses that in context
		char tmpPaddingRight = 'Ɍ'; //arbitrary temporary right padding character, hopefully none really uses that in context

		//split lines with the actual paragraph width
		String[] textAr = StringUtils.split(WordUtils.wrap(text, actualWidth, "\n", true), "\n");

		//process each line with alignment and padding
		for(int k=0; k<textAr.length; k++){
			StrBuilder line = new StrBuilder(50);
			line.appendPadding(this.ctx.getPaddingLeft(), tmpPaddingLeft);

			switch(this.ctx.getAlignment()){
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
						if(this.ctx.getAlignment()==TextAlign.JUSTIFIED){
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
			line.appendPadding(this.ctx.getPaddingRight(), tmpPaddingRight);

			//now change all internal padding chars (blank) to the actual in-line char
			line.replaceAll(' ', this.getContext().getInlineWS());

			//now change all temporary right padding characters to the actual right padding character
			int index = line.size()-1;
			while(line.lastIndexOf(tmpPaddingRight)==index){
				line.replace(index, line.size(), new String() + this.getContext().getRightPaddingChar());
				index--;
			}

			//now adjust the length of the line to be width (i.e. show right padding)
			while(line.size()<this.getContext().getWidth()){
				line.append(this.getContext().getRightPaddingChar());
			}

			//now change all temporary left padding characters to the actual left padding character
			index = 0;
			while(line.charAt(index)==tmpPaddingLeft){
				line.replace(index, index+1, new String() + this.getContext().getLeftPaddingChar());
				index++;
			}

			//now add the indentation to the line
			int count = this.getContext().getIndentation();
			while(count>0){
				line.insert(0, this.getContext().getIndentationChar());
				count--;
			}

			//now add a line start if set
			if(this.getContext().getLineStart()!=null){
				line.insert(0, this.getContext().getLineStart());
			}

			//now add a line end if set
			if(this.getContext().getLineEnd()!=null){
				line.append(this.getContext().getLineEnd());
			}

			//finally add the new line to the list
			ret.add(line.toString());
		}

		//finally add additional lines if required
		for(int i=0; i<this.getContext().getAddLines(); i++){
			ret.add("");
		}

		return ret;
	}

	/**
	 * Returns the rendered paragraph as an array of lines
	 * @return array of lines for the rendered paragraph
	 */
	public String[] renderToArray(){
		return this.renderToList().toArray(new String[]{});
	}

	/**
	 * Renders the paragraph, generates a string representation of it.
	 * All extra white spaces (extra spaces, tabulators, line feed, carriage return, line feed with carriage return) will be removed before the paragraph is rendered.
	 * @return rendered paragraph
	 */
	public String render(){
		return new StrBuilder(100).appendWithSeparators(this.renderToList(), "\n").toString();
	}
}
