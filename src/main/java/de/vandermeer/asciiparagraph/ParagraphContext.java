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

/**
 * Context for an {@link AsciiParagraph} with settings such as indentation and alignment.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160304 (04-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class ParagraphContext {

	/** Paragraph alignment. */
	protected TextAlign alignment;

	/** Left padding, spaces for each line on the left side, changes width. */
	protected int paddingLeft;

	/** Right padding, spaces for each line on the right side, changes width. */
	protected int paddingRight;

	/** Paragraph indentation, does not change the paragraph width. */
	protected int indentation;

	/** Added empty lines at the end of the paragraph. */
	protected int addLines;

	/** The width of the paragraph, actual width depends on padding settings. */
	protected int width;

	/** The character to be used for left padding. */
	protected char leftPaddingChar;

	/** The character to be used for right padding. */
	protected char rightPaddingChar;

	/** The character to be used for indentation. */
	protected char IndentationChar;

	/** A string to be inserted at the start of each text line. */
	protected String lineStart;

	/** A string to be inserted at the end of each text line. */
	protected String lineEnd;

	/** The character to be used for in-line white spaces. */
	protected char inlineWS;

	/**
	 * Creates a new paragraph context with default settings.
	 * The default values are as follows:
	 * <ul>
	 * 		<li>Width: 80</li>
	 * 		<li>Alignment: justified</li>
	 * 		<li>Indentation: 0</li>
	 * 		<li>Indentation character: ' ' (space)</li>
	 * 		<li>Left padding: 0</li>
	 * 		<li>Left padding character: ' ' (space)</li>
	 * 		<li>Right padding: 0</li>
	 * 		<li>Right padding character: ' ' (space)</li>
	 * 		<li>Added empty lines: 1</li>
	 * 		<li>In-line whitespace character: ' ' (space)</li>
	 * 		<li>Line start: null</li>
	 * 		<li>Line end: null</li>
	 * </ul>
	 */
	public ParagraphContext(){
		this.alignment = TextAlign.JUSTIFIED;
		this.paddingLeft = 0;
		this.paddingRight = 0;
		this.addLines = 1;
		this.width = 80;
		this.leftPaddingChar = ' ';
		this.rightPaddingChar = ' ';
		this.inlineWS = ' ';
		this.indentation = 0;
		this.IndentationChar = ' ';
		this.lineStart = null;
		this.lineEnd = null;
	}

	/**
	 * Returns the actual paragraph width, 
	 * @return the actual paragraph width is the width minus left padding minus right padding
	 */
	public int getActualWidth(){
		return this.width-this.paddingLeft-this.paddingRight;
	}

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
	public TextAlign getAlignment(){
		return this.alignment;
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
	 * @return self to allow chaining
	 */
	public ParagraphContext setAddLines(int addLines) {
		if(addLines>=0){
			this.addLines = addLines;
		}
		return this;
	}

	/**
	 * Sets the paragraph alignment.
	 * @param alignment set alignment
	 * @throws NullPointerException if the argument was null
	 * @return self to allow chaining
	 */
	public ParagraphContext setAlignment(TextAlign alignment){
		Validate.notNull(alignment);
		this.alignment = alignment;
		return this;
	}

	/**
	 * Sets the indentation.
	 * @param indentation new indentation, must be 0 or positive
	 * @return self to allow chaining
	 */
	public ParagraphContext setIndentation(int indentation) {
		if(indentation>=0){
			this.indentation = indentation;
		}
		return this;
	}

	/**
	 * Sets the indentation character.
	 * @param indentationChar new indentation character
	 * @return self to allow chaining
	 */
	public ParagraphContext setIndentationChar(char indentationChar) {
		IndentationChar = indentationChar;
		return this;
	}

	/**
	 * Sets the in-line whitespace character.
	 * @param inlineWS new inline whitespace character
	 * @return self to allow chaining
	 */
	public ParagraphContext setInlineWS(char inlineWS) {
		this.inlineWS = inlineWS;
		return this;
	}

	/**
	 * Sets the padding character for left padding.
	 * @param leftPaddingChar left padding character
	 * @return self to allow chaining
	 */
	public ParagraphContext setLeftPaddingChar(char leftPaddingChar) {
		this.leftPaddingChar = leftPaddingChar;
		return this;
	}

	/**
	 * Sets the line end string.
	 * @param lineEnd new line end string, should not be blank
	 * @return self to allow chaining
	 * @throws NullPointerException if the argument was null
	 * @throws IllegalArgumentException if the argument was blank
	 */
	public ParagraphContext setLineEnd(String lineEnd) {
		Validate.notBlank(lineEnd);
		this.lineEnd = lineEnd;
		return this;
	}

	/**
	 * Sets the line start string.
	 * @param lineStart new line start string, should not be blank
	 * @return self to allow chaining
	 * @throws NullPointerException if the argument was null
	 * @throws IllegalArgumentException if the argument was blank
	 */
	public ParagraphContext setLineStart(String lineStart) {
		Validate.notBlank(lineStart);
		this.lineStart = lineStart;
		return this;
	}

	/**
	 * Sets the left and right padding for the paragraph.
	 * @param padding padding, only added if all paddings and width allow for at least 3 characters per line
	 * @return self to allow chaining
	 */
	public ParagraphContext setPadding(int padding){
		if((this.width-padding-padding)>=3){
			this.paddingLeft = padding;
			this.paddingRight = padding;
		}
		return this;
	}

	/**
	 * Sets the left and right padding character.
	 * @param paddingChar left and right padding character
	 * @return self to allow chaining
	 */
	public ParagraphContext setPaddingChar(char paddingChar) {
		this.leftPaddingChar = paddingChar;
		this.rightPaddingChar = paddingChar;
		return this;
	}

	/**
	 * Sets the left padding for the paragraph
	 * @param paddingLeft left padding, only added if all paddings and width allow for at least 3 characters per line
	 * @return self to allow chaining
	 */
	public ParagraphContext setPaddingLeft(int paddingLeft) {
		if((this.width-paddingLeft-this.paddingRight)>=3){
			this.paddingLeft = paddingLeft;
		}
		return this;
	}

	/**
	 * Sets the right padding for the paragraph
	 * @param paddingRight right padding, only added if all paddings and width allow for at least 3 characters per line
	 * @return self to allow chaining
	 */
	public ParagraphContext setPaddingRight(int paddingRight) {
		if((this.width-this.paddingLeft-paddingRight)>=3){
			this.paddingRight = paddingRight;
		}
		return this;
	}

	/**
	 * Sets the padding character for right padding.
	 * @param rightPaddingChar right padding character
	 * @return self to allow chaining
	 */
	public ParagraphContext setRightPaddingChar(char rightPaddingChar) {
		this.rightPaddingChar = rightPaddingChar;
		return this;
	}

	/**
	 * Sets the paragraph width
	 * @param width new width, only added if all paddings and width allow for at least 3 characters per line
	 * @return self to allow chaining
	 */
	public ParagraphContext setWidth(int width) {
		if((width-this.paddingLeft-this.paddingRight)>=3){
			this.width = width;
		}
		return this;
	}

	/**
	 * Sets the width of the paragraph using all possible impacting settings.
	 * Beside padding (which is already calculated against the width), this includes the indentation (if larger than 0) and the length of start/end strings (if set)
	 * @return self to allow chaining
	 * @throws IllegalArgumentException if the resulting width does not allow for at least 3 characters of text per line
	 */
	public ParagraphContext calculateWidthInclusive(){
		int minus = this.indentation;
		if(this.lineStart!=null){
			minus += this.lineStart.length();
		}
		if(this.lineEnd!=null){
			minus += this.lineEnd.length();
		}
		int width = this.getActualWidth() - minus;
		Validate.isTrue(width>=3, "calculating an inclusive width and the result is less than 3");
		this.width = width;

		return this;
	}
}
