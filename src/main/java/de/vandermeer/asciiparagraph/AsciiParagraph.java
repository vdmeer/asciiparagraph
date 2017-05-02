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

import de.vandermeer.skb.interfaces.document.IsDocumentElement;
import de.vandermeer.skb.interfaces.document.IsParagraph;
import de.vandermeer.skb.interfaces.strategies.collections.list.ArrayListStrategy;
import de.vandermeer.skb.interfaces.transformers.ClusterElementTransformer;
import de.vandermeer.skb.interfaces.transformers.Object_To_StrBuilder;
import de.vandermeer.skb.interfaces.transformers.StrBuilder_To_String;

/**
 * An ASCII paragraph with some formatting options.
 * 
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.1.1 build 170502 (02-May-17) for Java 1.8
 * @since      v0.0.1
 */
public class AsciiParagraph implements IsParagraph {

	/** The paragraph context with optional settings for the paragraph. */
	protected AP_Context ctx;

	/** The paragraph text. */
	protected StrBuilder text;

	/** The renderer for the paragraph, default is {@link AP_Renderer}. */
	protected AP_Renderer renderer = AP_Renderer.create();

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
	 * Adds text to the paragraph using {@link Object_To_StrBuilder}.
	 * The method works as follows:
	 * 
	 * - test object for being a {@link IsDocumentElement} other than a paragraph, throw illegal argument exception if that's the case,
	 * - test if object is another {@link AsciiParagraph}, take the text if that's the case,
	 * - otherwise use {@link Object_To_StrBuilder}.
	 * 
	 * Null objects in clusters are silently ignored.
	 * Blank strings are processed like any other string (they do not impact the text anyway).
	 * 
	 * The method is recursive for iterators, iterables, and arrays.
	 * Care needs to be taken that the provided clusters do not lead to endless loops.
	 * 
	 * @param obj object with text for the paragraph
	 * @return this to allow chaining
	 * @throws NullPointerException if the argument was null
	 * @throws IllegalArgumentException if the argument was blank
	 */
	public AsciiParagraph addText(Object obj){
		Validate.notNull(obj);
		if((obj instanceof IsDocumentElement) && !(obj instanceof IsParagraph)){
			throw new IllegalArgumentException("cannot add " + obj.getClass().getSimpleName() + " as text to a paragraph");
		}

		if(obj instanceof AsciiParagraph){
			this.text.appendSeparator(' ').append(((AsciiParagraph)obj).text);
		}
		else {
			this.text.appendSeparator(' ').append(Object_To_StrBuilder.convert(obj));
		}

		return this;
	}

	/**
	 * Returns the paragraph context.
	 * @return context, null if not set
	 */
	public AP_Context getContext(){
		return this.ctx;
	}

	@Override
	public int getLongestLineLength() {
		return this.getRawContent().length();
	}

	@Override
	public String getRawContent(){
		return this.text.toString().replaceAll("\\s+", " ");
	}

	@Override
	public AP_Renderer getRenderer() {
		return this.renderer;
	}

	@Override
	public String render() {
		return this.renderer.render(this.getRawContent(), this.ctx);
//		return new StrBuilder().appendWithSeparators(this.renderer.renderAsCollection(this.getRawContent(), this.ctx), "\n").toString();
	}

	@Override
	public String render(int width) {
		return this.renderer.render(this.getRawContent(), this.ctx, this.ctx.getTextWidth(width));
//		return new StrBuilder().appendWithSeparators(this.renderer.renderAsCollection(this.getRawContent(), this.ctx, this.ctx.getTextWidth(width)), "\n").toString();
	}

	@Override
	public Collection<String> renderAsCollection() {
		return ClusterElementTransformer.create().transform(
				this.renderer.renderAsCollection(this.getRawContent(), this.ctx),
				StrBuilder_To_String.create(),
				ArrayListStrategy.create()
		);
	}

	@Override
	public Collection<String> renderAsCollection(int width) {
		return ClusterElementTransformer.create().transform(
				this.renderer.renderAsCollection(this.getRawContent(), this.ctx, this.ctx.getTextWidth(width)),
				StrBuilder_To_String.create(),
				ArrayListStrategy.create()
		);
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

	/**
	 * Sets the paragraph renderer.
	 * @param renderer new renderer, ignored if null
	 * @return this to allow chaining
	 */
	public AsciiParagraph setRenderer(AP_Renderer renderer){
		if(renderer!=null){
			this.renderer = renderer;
		}
		return this;
	}

	@Override
	public StrBuilder toLog() {
		StrBuilder ret = new StrBuilder();
		ret
			.append("AsciiParagraph: ")
			.append("l=").append(this.getLongestLineLength())
			.append(", w=").append(this.ctx.getWidth())
			.append(", tw=").append(this.ctx.getTextWidth())
			.appendNewLine()
		;
		return ret;
	}

//	/**
//	 * Applies the theme by setting parameters in the given context.
//	 * @param theme the theme to apply
//	 * @return this to allow chaining
//	 */
//	public AsciiParagraph applyTheme(AsciiParagraphTheme theme) {
//		if(theme!=null){
//			theme.apply(this.ctx);
//		}
//		return this;
//	}

}
