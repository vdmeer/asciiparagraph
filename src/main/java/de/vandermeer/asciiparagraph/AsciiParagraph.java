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
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.StrBuilder;

import de.vandermeer.skb.interfaces.ClusterElementTransformer;
import de.vandermeer.skb.interfaces.categories.does.DoesRender;
import de.vandermeer.skb.interfaces.categories.does.RendersToCluster;
import de.vandermeer.skb.interfaces.categories.has.HasText;
import de.vandermeer.skb.interfaces.categories.has.HasTextCluster;
import de.vandermeer.skb.interfaces.categories.is.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.categories.is.transformers.StrBuilder_To_String;

/**
 * An ASCII paragraph with some formatting options.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AsciiParagraph implements DoesRender, RendersToCluster {

	/** The paragraph context with optional settings for the paragraph. */
	protected AP_Context ctx;

	/** The paragraph text. */
	protected StrBuilder text;

	/**
	 * Creates a new paragraph with a default context.
	 */
	public AsciiParagraph(){
		this(new AP_Context());
	}

	/**
	 * Creates a new paragraph.
	 * @param ctx context for the paragraph
	 * @throws NullPointerException if the context was null
	 */
	public AsciiParagraph(AP_Context ctx){
		Validate.notNull(ctx);
		this.setContext(ctx);
		this.text = new StrBuilder(100);
	}

	/**
	 * Adds text to the paragraph from a collection of objects.
	 * From each member of the collection, the toString method will be used to generate text.
	 * @param text collection of objects with text
	 * @return this to allow chaining
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
	 * Adds text to the paragraph provided by the input object.
	 * @param object an object providing text for the paragraph
	 * @return this to allow chaining
	 * throws NullPointerException if the argument was null or if the object did only provide null as text
	 * throws IllegalArgumentException if any text provided was blank
	 */
	public AsciiParagraph addText(HasText object){
		Validate.notNull(object);

		String text = object.getText();
		if(StringUtils.isNotBlank(text)){
			return this.addText(text);
		}

		throw new IllegalArgumentException("HasText provider did not provide any text");
	}

	/**
	 * Adds text to the paragraph provided by the input object.
	 * @param object an object providing text for the paragraph
	 * @return this to allow chaining
	 * throws NullPointerException if the argument was null or if the object did only provide null as text
	 * throws IllegalArgumentException if any text provided was blank
	 */
	public AsciiParagraph addText(HasTextCluster object){
		Validate.notNull(object);

		Collection<String> collection = object.getTextAsCollection();
		if(collection!=null){
			for(String s : collection){
				this.addText(s);
			}
			return this;
		}

		throw new IllegalArgumentException("HasText provider did not provide any text");
	}

	/**
	 * Adds text to the paragraph.
	 * The method uses reflection to look for a method render without any parameters.
	 * If found, the result of this method will be used as text.
	 * Otherwise the object's toString method is used. 
	 * @param text text to be added to the paragraph
	 * @return this to allow chaining
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
	 * Adds text to the paragraph.
	 * If the paragraph already has text a space character will be used to separate existing and added text.
	 * No further processing on the text is done, we let {@link #render()} deal with all extra whitespace handling.
	 * @param text text to be added to the paragraph
	 * @return this to allow chaining
	 * throws NullPointerException if the argument was null
	 * throws IllegalArgumentException if the argument was blank
	 */
	public AsciiParagraph addText(String text){
		Validate.notBlank(text);
		this.text.appendSeparator(' ').append(text);
		return this;
	}

	/**
	 * Returns the paragraph context.
	 * @return context, null if not set
	 */
	public AP_Context getContext(){
		return this.ctx;
	}

	/**
	 * Returns the text of the paragraph.
	 * @return paragraph text
	 */
	public StrBuilder getText(){
		return this.text;
	}

	/**
	 * Sets the paragraph context.
	 * @param ctx context, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiParagraph setContext(AP_Context ctx){
		if(ctx!=null){
			this.ctx = ctx;
		}
		return this;
	}

	@Override
	public Collection<String> renderAsCollection() {
		return ClusterElementTransformer.create().transform(
				this.ctx.getRenderer().render(this),
				StrBuilder_To_String.create(),
				ArrayListStrategy.create()
		);
	}

	@Override
	public String render() {
		return new StrBuilder().appendWithSeparators(this.ctx.getRenderer().render(this), "\n").toString();
	}
}
