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

import org.apache.commons.lang3.Validate;

import de.vandermeer.asciiparagraph.dropcaps.DropCaps;
import de.vandermeer.asciiparagraph.dropcaps.FigletRoman;
import de.vandermeer.skb.interfaces.objctxt.IsObjectContext;
import de.vandermeer.skb.interfaces.textart.TA_FrameTheme;

/**
 * Context for an {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AP_Context implements IsObjectContext {

	/** Paragraph margins. */
	protected AP_CtxtMargins margins = new AP_CtxtMargins();

	/** Paragraph characters. */
	protected AP_CtxtCharacters characters = new AP_CtxtCharacters();

	/** Paragraph indentations. */
	protected AP_CtxtIndents indents = new AP_CtxtIndents();

	/** Paragraph strings. */
	protected AP_CtxtStrings strings = new AP_CtxtStrings();

	/** Paragraph alignment, default is {@link AP_Alignment#JUSTIFIED_LEFT}. */
	protected AP_Alignment alignment = AP_Alignment.JUSTIFIED_LEFT;

	/** Paragraph format, default is {@link  = AP_Format#NONE}. */
	protected AP_Format format = AP_Format.NONE;

	/** The width of the paragraph, actual width depends on padding settings, default is `80`. */
	protected int width = 80;

	/** The renderer for this context, default is {@link AbstractRenderer}. */
	protected AP_Renderer renderer = AP_Renderer.create();

	/** A library of dropped capital letters, default is {@link FigletRoman}. */
	protected DropCaps dropCapLib = new FigletRoman();

	/** The theme for a frame. */
	protected TA_FrameTheme frame = null;

	/**
	 * Creates a new paragraph context with default settings.
	 * The default values are as follows:
	 * 
	 * - Width: 80
	 * - Alignment: justified, last line left aligned
	 * - Format: none
	 * - Renderer: {@link AP_Renderer}
	 * - DropCap library: {@link FigletRoman}
	 * - Margins from {@link AP_CtxtMargins}
	 * - Characters from {@link AP_CtxtCharacters}
	 * - Indentations from {@link AP_CtxtIndents}
	 * - Strings from {@link AP_CtxtStrings}
	 * 
	 */
	public AP_Context(){}

	/**
	 * Returns the set alignment.
	 * @return paragraph alignment
	 */
	public AP_Alignment getAlignment(){
		return this.alignment;
	}

	/**
	 * Returns the set dropped capital letter library.
	 * @return dropped capital letter library, null if none set
	 */
	public DropCaps getDropCapLib() {
		return this.dropCapLib;
	}

	/**
	 * Returns the paragraph format.
	 * @return paragraph format
	 */
	public AP_Format getFormat() {
		return this.format;
	}

	/**
	 * Returns the set renderer.
	 * @return renderer
	 */
	public AP_Renderer getRenderer(){
		return this.renderer;
	}

	/**
	 * Returns the paragraph width.
	 * @return paragraph width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Sets the paragraph alignment.
	 * @param alignment set alignment
	 * @throws NullPointerException if the argument was null
	 * @return this to allow chaining
	 */
	public AP_Context setAlignment(AP_Alignment alignment){
		Validate.notNull(alignment);
		this.alignment = alignment;
		return this;
	}

	/**
	 * Sets the set dropped capital letter library.
	 * @param dropCapLib capital letter library, only used if not null
	 * @return this to allow chaining
	 */
	public AP_Context setDropCapLib(DropCaps dropCapLib) {
		Validate.notNull(dropCapLib);
		this.dropCapLib = dropCapLib;
		return this;
	}

	/**
	 * Sets the paragraph format.
	 * @param paragraph format, ignored if null
	 * @return this to allow chaining
	 */
	public AP_Context setFormat(AP_Format format) {
		Validate.notNull(format);
		this.format = format;
		return this;
	}

	/**
	 * Sets the renderer for the context.
	 * @param renderer new renderer, only set if not null
	 */
	public void setRenderer(AP_Renderer renderer){
		Validate.notNull(renderer);
		this.renderer = renderer;
	}

	/**
	 * Sets the paragraph width.
	 * @param width new width
	 * @return this to allow chaining
	 */
	public AP_Context setWidth(int width) {
		this.width = width;
		return this;
	}

	/**
	 * Sets the paragraph frame.
	 * @param frame new frame, null to reset
	 * @return this to allow chaining
	 */
	public AP_Context setFrame(TA_FrameTheme frame) {
		this.frame = frame;
		return this;
	}

	/**
	 * Returns the paragraph frame.
	 * @return frame, null if not set
	 */
	public TA_FrameTheme getFrame() {
		return this.frame;
	}

	/**
	 * Returns the margin settings.
	 * @return margin settings
	 */
	public AP_CtxtMargins getMargins() {
		return margins;
	}

	/**
	 * Returns the character settings.
	 * @return character settings
	 */
	public AP_CtxtCharacters getCharacters() {
		return characters;
	}

	/**
	 * Returns the indentation settings.
	 * @return indentation settings
	 */
	public AP_CtxtIndents getIndents() {
		return indents;
	}

	/**
	 * Returns the string settings. 
	 * @return string settings
	 */
	public AP_CtxtStrings getStrings() {
		return strings;
	}

	/**
	 * Returns the width including text margins.
	 * @return width
	 */
	public int getWidthIncTextMargins(){
		return this.width-this.margins.getTextLeft()-this.margins.getTextRight();
	}

	/**
	 * Returns the width including text margins based on requested width.
	 * @param width the requested width
	 * @return width
	 */
	public int getWidthIncTextMargins(int width){
		return width-this.margins.getTextLeft()-this.margins.getTextRight();
	}

	/**
	 * Returns the width including text and string margins.
	 * @return width
	 */
	public int getWidthIncStringMargins(){
		int width = this.getWidthIncTextMargins()-this.margins.getStringLeft()-this.margins.getStringRight();
		if(this.getStrings().getStart()!=null){
			width -= this.getStrings().getStart().length();
		}
		if(this.getStrings().getEnd()!=null){
			width -= this.getStrings().getEnd().length();
		}
		return width;
	}

	/**
	 * Returns the width including text and string margins based on requested width.
	 * @param width requested width
	 * @return width
	 */
	public int getWidthIncStringMargins(int width){
		int w = this.getWidthIncTextMargins(width)-this.margins.getStringLeft()-this.margins.getStringRight();
		if(this.getStrings().getStart()!=null){
			w -= this.getStrings().getStart().length();
		}
		if(this.getStrings().getEnd()!=null){
			w -= this.getStrings().getEnd().length();
		}
		return w;
	}

	/**
	 * Returns the width including all margins.
	 * @return width
	 */
	public int getWidthAllInclusive(){
		return this.getWidthIncStringMargins()-this.margins.getFrameLeft()-this.margins.getFrameRight();
	}

	/**
	 * Returns the width including all margins based on requested width.
	 * @param width requested width
	 * @return width
	 */
	public int getWidthAllInclusive(int width){
		return this.getWidthIncStringMargins(width)-this.margins.getFrameLeft()-this.margins.getFrameRight();
	}
}
