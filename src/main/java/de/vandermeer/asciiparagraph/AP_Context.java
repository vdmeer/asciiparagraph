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

/**
 * Context for an {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AP_Context implements IsObjectContext {

	/** Paragraph alignment, default is {@link AP_Alignment#JUSTIFIED_LEFT}. */
	protected AP_Alignment alignment = AP_Alignment.JUSTIFIED_LEFT;

	/** Paragraph format, default is {@link  = AP_Format#NONE}. */
	protected AP_Format format = AP_Format.NONE;

	/** Left padding, spaces for each line on the left side, changes width, default is `0`. */
	protected int paddingLeft = 0;

	/** Right padding, spaces for each line on the right side, changes width, default is `0`. */
	protected int paddingRight = 0;

	/** Paragraph indentation, does not change the paragraph width, default is `0`. */
	protected int indentation = 0;

	/** Added empty lines at the end of the paragraph, default is `1`. */
	protected int addLines = 1;

	/** The width of the paragraph, actual width depends on padding settings, default is `80`. */
	protected int width = 80;

	/** The character to be used for left padding, default is simple blank. */
	protected char leftPaddingChar = ' ';

	/** The character to be used for right padding, default is simple blank. */
	protected char rightPaddingChar = ' ';

	/** The character to be used for indentation, default is simple blank. */
	protected char IndentationChar = ' ';

	/** A string to be inserted at the start of each text line, default is `null`. */
	protected String lineStart = null;

	/** A string to be inserted at the end of each text line, default is `null`. */
	protected String lineEnd = null;

	/** The character to be used for in-line white spaces, default is simple blank. */
	protected char inlineWS = ' ';

	/** The renderer for this context, default is {@link AbstractRenderer}. */
	protected AP_Renderer renderer = new AbstractRenderer();

	/** Number of characters used for indentation of the first line, default is `4`. */
	protected int firstLineIndent = 4;

	/** Number of characters used for a hanging paragraph, default is `4`. */
	protected int hangingIndent = 4;

	/** A library of dropped capital letters, default is {@link FigletRoman}. */
	protected DropCaps dropCapLib = new FigletRoman();

	/**
	 * Creates a new paragraph context with default settings.
	 * The default values are as follows:
	 * 
	 * - Width: 80
	 * - Alignment: justified, last line left aligned
	 * - Format: none
	 * - Indentation: 0
	 * - Indentation character: ' ' (space)
	 * - Left padding: 0
	 * - Left padding character: ' ' (space)
	 * - Right padding: 0
	 * - Right padding character: ' ' (space)
	 * - Added empty lines: 1
	 * - In-line whitespace character: ' ' (space)
	 * - Line start: null
	 * - Line end: null
	 * - First line indentation: 4
	 * - Hanging paragraph indentation: 4
	 * - Renderer: {@link AbstractRenderer}
	 * - DropCap library: {@link FigletRoman}
	 * 
	 */
	public AP_Context(){}

	/**
	 * Returns the number of added empty lines.
	 * @return number of added empty lines
	 */
	public int getAddLines() {
		return addLines;
	}

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
		return dropCapLib;
	}

	/**
	 * Returns the number of characters used for first line indentation.
	 * @return number of characters
	 */
	public int getFirstLineIndent() {
		return firstLineIndent;
	}

	/**
	 * Returns the paragraph format.
	 * @return paragraph format
	 */
	public AP_Format getFormat() {
		return format;
	}

	/**
	 * Returns the number of characters used for a hanging paragraph.
	 * @return number of characters
	 */
	public int getHangingIndent() {
		return hangingIndent;
	}

	/**
	 * Returns the indentation.
	 * @return paragraph indentation
	 */
	public int getIndentation() {
		return indentation;
	}

	/**
	 * Returns the indentation character.
	 * @return indentation character
	 */
	public char getIndentationChar() {
		return IndentationChar;
	}

	/**
	 * Returns the in-line whitespace character.
	 * @return in-line whitespace character
	 */
	public char getInlineWS() {
		return inlineWS;
	}

	/**
	 * Returns the left padding character.
	 * @return left padding character
	 */
	public char getLeftPaddingChar() {
		return leftPaddingChar;
	}

	/**
	 * Returns the line end string.
	 * @return line end string, null if not set
	 */
	public String getLineEnd() {
		return lineEnd;
	}

	/**
	 * Returns the line start string.
	 * @return line start string, null if not set
	 */
	public String getLineStart() {
		return lineStart;
	}

	/**
	 * Returns the left padding.
	 * @return left padding
	 */
	public int getPaddingLeft() {
		return paddingLeft;
	}

	/**
	 * Returns the right padding.
	 * @return right padding
	 */
	public int getPaddingRight() {
		return paddingRight;
	}

	/**
	 * Returns the set renderer.
	 * @return renderer
	 */
	public AP_Renderer getRenderer(){
		return this.renderer;
	}

	/**
	 * Returns the right padding character.
	 * @return right padding character
	 */
	public char getRightPaddingChar() {
		return rightPaddingChar;
	}

	/**
	 * Returns the paragraph width.
	 * @return paragraph width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the number of empty lines that should be added to the paragraph.
	 * @param addLines number of added lines, only added if 0 or positive
	 * @return this to allow chaining
	 */
	public AP_Context setAddLines(int addLines) {
		if(addLines>=0){
			this.addLines = addLines;
		}
		return this;
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
		if(dropCapLib!=null){
			this.dropCapLib = dropCapLib;
		}
		return this;
	}

	/**
	 * Sets the number of characters used for first line indentation.
	 * @param number of characters, only used if positive integer
	 * @return this to allow chaining
	 */
	public AP_Context setFirstLineIndent(int firstLineIndent) {
		if(firstLineIndent>0){
			this.firstLineIndent = firstLineIndent;
		}
		return this;
	}

	/**
	 * Sets the paragraph format.
	 * @param paragraph format, ignored if null
	 * @return this to allow chaining
	 */
	public AP_Context setFormat(AP_Format format) {
		if(format!=null){
			this.format = format;
		}
		return this;
	}

	/**
	 * Sets the number of characters used for a hanging paragraph.
	 * @param number of characters, only used if positive integer
	 * @return this to allow chaining
	 */
	public AP_Context setHanging(int hanging) {
		if(hanging>0){
			this.hangingIndent = hanging;
		}
		return this;
	}

	/**
	 * Sets the indentation.
	 * @param indentation new indentation, must be 0 or positive
	 * @return this to allow chaining
	 */
	public AP_Context setIndentation(int indentation) {
		if(indentation>=0){
			this.indentation = indentation;
		}
		return this;
	}

	/**
	 * Sets the indentation character.
	 * @param indentationChar new indentation character
	 * @return this to allow chaining
	 */
	public AP_Context setIndentationChar(char indentationChar) {
		IndentationChar = indentationChar;
		return this;
	}

	/**
	 * Sets the in-line whitespace character.
	 * @param inlineWS new inline whitespace character
	 * @return this to allow chaining
	 */
	public AP_Context setInlineWS(char inlineWS) {
		this.inlineWS = inlineWS;
		return this;
	}

	/**
	 * Sets the padding character for left padding.
	 * @param leftPaddingChar left padding character
	 * @return this to allow chaining
	 */
	public AP_Context setLeftPaddingChar(char leftPaddingChar) {
		this.leftPaddingChar = leftPaddingChar;
		return this;
	}

	/**
	 * Sets the line end string.
	 * @param lineEnd new line end string, should not be blank
	 * @return this to allow chaining
	 * @throws NullPointerException if the argument was null
	 * @throws IllegalArgumentException if the argument was blank
	 */
	public AP_Context setLineEnd(String lineEnd) {
		Validate.notBlank(lineEnd);
		this.lineEnd = lineEnd;
		return this;
	}

	/**
	 * Sets the line start string.
	 * @param lineStart new line start string, should not be blank
	 * @return this to allow chaining
	 * @throws NullPointerException if the argument was null
	 * @throws IllegalArgumentException if the argument was blank
	 */
	public AP_Context setLineStart(String lineStart) {
		Validate.notBlank(lineStart);
		this.lineStart = lineStart;
		return this;
	}

	/**
	 * Sets the left and right padding for the paragraph.
	 * @param padding padding, only added if all paddings and width allow for at least 3 characters per line
	 * @return this to allow chaining
	 */
	public AP_Context setPadding(int padding){
		if((this.width-padding-padding)>=3){
			this.paddingLeft = padding;
			this.paddingRight = padding;
		}
		return this;
	}

	/**
	 * Sets the left and right padding character.
	 * @param paddingChar left and right padding character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingChar(char paddingChar) {
		this.leftPaddingChar = paddingChar;
		this.rightPaddingChar = paddingChar;
		return this;
	}

	/**
	 * Sets the left padding for the paragraph.
	 * @param paddingLeft left padding, only added if all paddings and width allow for at least 3 characters per line
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeft(int paddingLeft) {
		if((this.width-paddingLeft-this.paddingRight)>=3){
			this.paddingLeft = paddingLeft;
		}
		return this;
	}

	/**
	 * Sets the right padding for the paragraph.
	 * @param paddingRight right padding, only added if all paddings and width allow for at least 3 characters per line
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingRight(int paddingRight) {
		if((this.width-this.paddingLeft-paddingRight)>=3){
			this.paddingRight = paddingRight;
		}
		return this;
	}

	/**
	 * Sets the renderer for the context.
	 * @param renderer new renderer, only set if not null
	 */
	public void setRenderer(AP_Renderer renderer){
		if(renderer!=null){
			this.renderer = renderer;
		}
	}

	/**
	 * Sets the padding character for right padding.
	 * @param rightPaddingChar right padding character
	 * @return this to allow chaining
	 */
	public AP_Context setRightPaddingChar(char rightPaddingChar) {
		this.rightPaddingChar = rightPaddingChar;
		return this;
	}

	/**
	 * Sets the paragraph width.
	 * @param width new width, only added if all paddings and width allow for at least 3 characters per line
	 * @return this to allow chaining
	 */
	public AP_Context setWidth(int width) {
		if((width-this.paddingLeft-this.paddingRight)>=3){
			this.width = width;
		}
		return this;
	}
}
