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

import de.vandermeer.asciithemes.TA_DropCaps;
import de.vandermeer.asciithemes.TA_Frame;
import de.vandermeer.asciithemes.TA_FrameOptions;
import de.vandermeer.asciithemes.a7.dropcaps.FigletRoman;
import de.vandermeer.skb.interfaces.document.IsParagraphContext;
import de.vandermeer.skb.interfaces.translators.CharacterTranslator;
import de.vandermeer.skb.interfaces.translators.HtmlElementTranslator;
import de.vandermeer.skb.interfaces.translators.TargetTranslator;

/**
 * Context for an {@link AsciiParagraph}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AP_Context implements IsParagraphContext {

	/** A simple character translator. */
	protected CharacterTranslator charTranslator;

	/** A translator for HTML elements. */
	protected HtmlElementTranslator htmlElementTranslator;

	/** A translator for a particular target. */
	protected TargetTranslator targetTranslator;

	/** The padding character for left side of a text line if not justified. */
	protected Character paddingLeftChar = ' ';

	/** The padding character for right side of a text line if not justified. */
	protected Character paddingRightChar = ' ';

	/** The character to be used for white spaces in text. */
	protected Character innerWsChar = ' ';

	/** The character for left text margin. */
	protected Character textLeftChar = ' ';

	/** The character for right text margin. */
	protected Character textRightChar = ' ';

	/** The character for left string margin. */
	protected Character stringLeftChar = ' ';

	/** The character for right string margin. */
	protected Character stringRightChar = ' ';

	/** The character for left frame margin. */
	protected Character frameLeftChar = ' ';

	/** The character for right frame margin. */
	protected Character frameRightChar = ' ';

	/** Margin between text and start string. */
	protected int textLeftMargin = 0;

	/** Margin between text and end string. */
	protected int textRightMargin = 0;

	/** Margin between start string and frame border. */
	protected int stringLeftMargin = 0;

	/** Margin between end string and frame border. */
	protected int stringRightMargin = 0;

	/** Margin outside the left frame border. */
	protected int frameLeftMargin = 0;

	/** Margin outside the right frame border. */
	protected int frameRightMargin = 0;

	/** Margin between text and top frame. */
	protected int textTopMargin = 0;

	/** Margin between text and bottom frame. */
	protected int textBottomMargin = 1;

	/** Margin outside top frame. */
	protected int frameTopMargin = 0;

	/** Margin outside bottom frame. */
	protected int frameBottomMargin = 0;

	/** Indentation for a first line. */
	protected int firstLineIndent = 4;

	/** Indentation for a hanging paragraph. */
	protected int hangingParaIndent = 4;

	/** Start string for each line of a paragraph. */
	protected String startString = null;

	/** End string for each line of a paragraph. */
	protected String endString = null;

	/** Paragraph alignment, default is {@link AP_Alignment#JUSTIFIED_LEFT}. */
	protected AP_Alignment alignment = AP_Alignment.JUSTIFIED_LEFT;

	/** Paragraph format, default is {@link AP_Format#NONE}. */
	protected AP_Format format = AP_Format.NONE;

	/** The width of the paragraph, actual width depends on padding settings, default is `80`. */
	protected int width = 80;

	/** A library of dropped capital letters, default is {@link FigletRoman}. */
	protected TA_DropCaps dropCapLib = new FigletRoman();

	/** The theme for a frame. */
	protected TA_Frame frame = null;

	/** The mode for a frame. */
	protected int frameMode = TA_FrameOptions.THEME_FULL_FRAME;

	/**
	 * Creates a new paragraph context with default settings.
	 * The default values are as follows:
	 * 
	 * - Width: 80
	 * - Alignment: justified, last line left aligned
	 * - Format: none
	 * - Renderer: {@link AP_Renderer}
	 * - DropCap library: {@link FigletRoman}
	 * - Margins: all margins are set to 0, except text bottom is 1
	 * - Characters: all characters set to `' '`
	 * - Indentations: all indentations set to 4
	 * - Strings: all strings set to null
	 * - Converters: all converters set to null
	 * - Frame: null
	 * - Frame mode: {@link TA_FrameOptions#THEME_FULL_FRAME}
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
	 * Returns the character translator
	 * @return character translator
	 */
	public CharacterTranslator getCharTranslator() {
		return this.charTranslator;
	}

	/**
	 * Returns the set dropped capital letter library.
	 * @return dropped capital letter library, null if none set
	 */
	public TA_DropCaps getDropCapLib() {
		return this.dropCapLib;
	}

	/**
	 * Returns the end string.
	 * @return end string
	 */
	public final String getEndString() {
		return this.endString;
	}

	/**
	 * Returns the first line indentation.
	 * @return first line indentation
	 */
	public int getFirstLineIndent() {
		return this.firstLineIndent;
	}

	/**
	 * Returns the paragraph format.
	 * @return paragraph format
	 */
	public AP_Format getFormat() {
		return this.format;
	}

	/**
	 * Returns the paragraph frame.
	 * @return frame, null if not set
	 */
	public TA_Frame getFrame() {
		return this.frame;
	}

	/**
	 * Returns the bottom frame margin.
	 * @return bottom frame margin
	 */
	public int getFrameBottomMargin() {
		return this.frameBottomMargin;
	}

	/**
	 * Returns the left frame margin character.
	 * @return left frame margin character
	 */
	public Character getFrameLeftChar() {
		return this.frameLeftChar;
	}

	/**
	 * Returns the left frame margin.
	 * @return left frame margin
	 */
	public int getFrameLeftMargin() {
		return this.frameLeftMargin;
	}

	/**
	 * Returns the set frame mode.
	 * @return frame mode
	 */
	public int getFrameMode() {
		return frameMode;
	}

	/**
	 * Returns the right frame margin character.
	 * @return right frame margin character
	 */
	public Character getFrameRightChar() {
		return this.frameRightChar;
	}

	/**
	 * Returns the right frame margin.
	 * @return right frame margin
	 */
	public int getFrameRightMargin() {
		return this.frameRightMargin;
	}

	/**
	 * Returns the top frame margin.
	 * @return top frame margin
	 */
	public int getFrameTopMargin() {
		return this.frameTopMargin;
	}

	/**
	 * Returns the hanging paragraph indentation
	 * @return hanging paragraph indentation
	 */
	public int getHangingParaIndent() {
		return this.hangingParaIndent;
	}

	/**
	 * Returns the HTML entity translator.
	 * @return HTML entity
	 */
	public HtmlElementTranslator getHtmlElementTranslator() {
		return this.htmlElementTranslator;
	}

	/**
	 * Returns the in-text white space character.
	 * @return in-text white space character
	 */
	public Character getInnerWsChar() {
		return this.innerWsChar;
	}

	/**
	 * Returns the left padding character.
	 * @return left padding character
	 */
	public Character getPaddingLeftChar() {
		return this.paddingLeftChar;
	}

	/**
	 * Returns the right padding character.
	 * @return right padding character
	 */
	public Character getPaddingRightChar() {
		return this.paddingRightChar;
	}

	/**
	 * Returns the start string.
	 * @return start string
	 */
	public final String getStartString() {
		return this.startString;
	}

	/**
	 * Returns the left string margin character.
	 * @return left string margin character
	 */
	public Character getStringLeftChar() {
		return this.stringLeftChar;
	}

	/**
	 * Returns the left string margin.
	 * @return left string margin
	 */
	public int getStringLeftMargin() {
		return this.stringLeftMargin;
	}

	/**
	 * Returns the right string margin character.
	 * @return right string margin character
	 */
	public Character getStringRightChar() {
		return this.stringRightChar;
	}

	/**
	 * Returns the right string margin.
	 * @return right string margin
	 */
	public int getStringRightMargin() {
		return this.stringRightMargin;
	}

	/**
	 * Returns the target translator.
	 * @return target translator, null if not set
	 */
	public TargetTranslator getTargetTranslator() {
		return this.targetTranslator;
	}

	/**
	 * Returns the bottom text margin.
	 * @return bottom text margin
	 */
	public int getTextBottomMargin() {
		return this.textBottomMargin;
	}

	/**
	 * Returns the left text margin character.
	 * @return left text margin character
	 */
	public Character getTextLeftChar() {
		return this.textLeftChar;
	}

	/**
	 * Returns the left text margin.
	 * @return left text margin
	 */
	public int getTextLeftMargin() {
		return this.textLeftMargin;
	}

	/**
	 * Returns the right text margin character.
	 * @return right text margin character
	 */
	public Character getTextRightChar() {
		return this.textRightChar;
	}

	/**
	 * Returns the right text margin.
	 * @return right text margin
	 */
	public int getTextRightMargin() {
		return this.textRightMargin;
	}

	/**
	 * Returns the top text margin.
	 * @return top text margin
	 */
	public int getTextTopMargin() {
		return this.textTopMargin;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getTextWidth(int width) {
		int ret = width - this.getTextLeftMargin() - this.getTextRightMargin();
		ret = ret - this.getStringLeftMargin() - this.getStringRightMargin();
		if(this.getStartString()!=null){
			ret -= this.getStartString().length();
		}
		if(this.getEndString()!=null){
			ret -= this.getEndString().length();
		}
		ret = ret - this.getFrameLeftMargin() - this.getFrameRightMargin();
		return ret;
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
	 * Sets the character translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param charTranslator translator
	 */
	public void setCharTranslator(CharacterTranslator charTranslator) {
		if(charTranslator!=null){
			this.charTranslator = charTranslator;
			this.htmlElementTranslator = null;
			this.targetTranslator = null;
		}
	}

	/**
	 * Sets the set dropped capital letter library.
	 * @param dropCapLib capital letter library, only used if not null
	 * @return this to allow chaining
	 */
	public AP_Context setDropCapLib(TA_DropCaps dropCapLib) {
		Validate.notNull(dropCapLib);
		this.dropCapLib = dropCapLib;
		return this;
	}

	/**
	 * Sets the end string.
	 * @param end string, can be null
	 * @return this to allow chaining
	 */
	public final AP_Context setEndString(String end) {
		this.endString = end;
		return this;
	}

	/**
	 * Sets the first line indentation
	 * @param firstLine indentation
	 * @return this to allow chaining
	 */
	public AP_Context setFirstLineIndent(int firstLine) {
		if(firstLine>-1){
			this.firstLineIndent = firstLine;
		}
		return this;
	}

	/**
	 * Sets the paragraph format.
	 * @param format paragraph format, ignored if null
	 * @return this to allow chaining
	 */
	public AP_Context setFormat(AP_Format format) {
		Validate.notNull(format);
		this.format = format;
		return this;
	}

	/**
	 * Sets the paragraph frame.
	 * @param frame new frame, null to reset
	 * @return this to allow chaining
	 */
	public AP_Context setFrame(TA_Frame frame) {
		this.frame = frame;
		return this;
	}

	/**
	 * Sets the bottom frame margin
	 * @param frameBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameBottomMargin(int frameBottom) {
		if(frameBottom>-1){
			this.frameBottomMargin = frameBottom;
		}
		return this;
	}

	/**
	 * Sets the left frame margin character.
	 * @param frameLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftChar(Character frameLeft) {
		if(frameLeft!=null){
			this.frameLeftChar = frameLeft;
		}
		return this;
	}

	/**
	 * Sets the left frame margin
	 * @param frameLeft margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftMargin(int frameLeft) {
		if(frameLeft>-1){
			this.frameLeftMargin = frameLeft;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin character.
	 * @param frameChar character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightChar(Character frameChar){
		if(frameChar!=null){
			this.frameLeftChar = frameChar;
			this.frameRightChar = frameChar;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin character.
	 * @param frameLeft character
	 * @param frameRight character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightChar(Character frameLeft, Character frameRight){
		if(frameLeft!=null && frameRight!=null){
			this.frameLeftChar = frameLeft;
			this.frameRightChar = frameRight;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin.
	 * @param frameMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightMargin(int frameMargin){
		if(frameMargin>-1){
			this.frameLeftMargin = frameMargin;
			this.frameRightMargin = frameMargin;
		}
		return this;
	}

	/**
	 * Sets the left and right frame margin.
	 * @param frameLeft margin
	 * @param frameRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightMargin(int frameLeft, int frameRight){
		if(frameRight>-1 && frameLeft>-1){
			this.frameLeftMargin = frameLeft;
			this.frameRightMargin = frameRight;
		}
		return this;
	}

	/**
	 * Sets the frame mode.
	 * @param frameMode new frame mode, only used if 0 or positive integer
	 * @return this to allow chaining
	 */
	public AP_Context setFrameMode(int frameMode) {
		if(frameMode>=0){
			this.frameMode = frameMode;
		}
		return this;
	}

	/**
	 * Sets the right frame margin character.
	 * @param frameRight character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameRightChar(Character frameRight) {
		if(frameRight!=null){
			this.frameRightChar = frameRight;
		}
		return this;
	}

	/**
	 * Sets the right frame margin
	 * @param frameRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameRightMargin(int frameRight) {
		if(frameRight>-1){
			this.frameRightMargin = frameRight;
		}
		return this;
	}

	/**
	 * Sets the top and bottom frame margin.
	 * @param frameMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameTopBottomMargin(int frameMargin){
		if(frameMargin>-1){
			this.frameTopMargin = frameMargin;
			this.frameBottomMargin = frameMargin;
		}
		return this;
	}

	/**
	 * Sets the top and bottom frame margin.
	 * @param frameTop margin
	 * @param frameBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameTopBottomMargin(int frameTop, int frameBottom){
		if(frameTop>-1 && frameBottom>-1){
			this.frameTopMargin = frameTop;
			this.frameBottomMargin = frameBottom;
		}
		return this;
	}

	/**
	 * Sets the top frame margin
	 * @param frameTop margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameTopMargin(int frameTop) {
		if(frameTop>-1){
			this.frameTopMargin = frameTop;
		}
		return this;
	}

	/**
	 * Sets the hanging paragraph indentation
	 * @param hangingPara indentation
	 * @return this to allow chaining
	 */
	public AP_Context setHangingParaIndent(int hangingPara) {
		if(hangingPara>-1){
			this.hangingParaIndent = hangingPara;
		}
		return this;
	}

	/**
	 * Sets the HTML entity translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param htmlElementTranslator translator
	 */
	public void setHtmlElementTranslator(HtmlElementTranslator htmlElementTranslator) {
		if(htmlElementTranslator!=null){
			this.htmlElementTranslator = htmlElementTranslator;
			this.charTranslator = null;
			this.targetTranslator = null;
		}
	}

	/**
	 * Sets the in-text white space character
	 * @param innerWs in-text white space character
	 * @return this to allow chaining
	 */
	public AP_Context setInnerWsChar(Character innerWs) {
		if(innerWs!=null){
			this.innerWsChar = innerWs;
		}
		return this;
	}

	/**
	 * Sets the left padding character.
	 * @param paddingLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeftChar(Character paddingLeft) {
		if(paddingLeft!=null){
			this.paddingLeftChar = paddingLeft;
		}
		return this;
	}

	/**
	 * Sets the left and right padding character.
	 * @param paddingChar character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeftRightChar(Character paddingChar){
		if(paddingChar!=null){
			this.paddingLeftChar = paddingChar;
			this.paddingRightChar = paddingChar;
		}
		return this;
	}

	/**
	 * Sets the left and right padding character.
	 * @param paddingLeft character
	 * @param paddingRight character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeftRightChar(Character paddingLeft, Character paddingRight){
		if(paddingLeft!=null && paddingRight!=null){
			this.paddingLeftChar = paddingLeft;
			this.paddingRightChar = paddingRight;
		}
		return this;
	}

	/**
	 * Sets the right padding character.
	 * @param paddingRight character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingRightChar(Character paddingRight) {
		if(paddingRight!=null){
			this.paddingRightChar = paddingRight;
		}
		return this;
	}

	/**
	 * Sets the start and end string to the same string.
	 * @param start string, can be null
	 * @param end string, can be null
	 * @return this to allow chaining
	 */
	public final AP_Context setStartEndString(String start, String end) {
		this.startString = start;
		this.endString = end;
		return this;
	}

	/**
	 * Sets the start string.
	 * @param start string, can be null
	 * @return this to allow chaining
	 */
	public final AP_Context setStartString(String start) {
		this.startString = start;
		return this;
	}

	/**
	 * Sets the left string margin character.
	 * @param stringLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftChar(Character stringLeft) {
		if(stringLeft!=null){
			this.stringLeftChar = stringLeft;
		}
		return this;
	}

	/**
	 * Sets the left string margin
	 * @param stringLeft margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftMargin(int stringLeft) {
		if(stringLeft>-1){
			this.stringLeftMargin = stringLeft;
		}
		return this;
	}

	/**
	 * Sets the left and right string margin character.
	 * @param stringChar character
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightChar(Character stringChar){
		if(stringChar!=null){
			this.stringLeftChar = stringChar;
			this.stringRightChar = stringChar;
		}
		return this;
	}

	/**
	 * Sets the left and right string margin character.
	 * @param stringLeft character
	 * @param stringRight character
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightChar(Character stringLeft, Character stringRight){
		if(stringLeft!=null && stringRight!=null){
			this.stringLeftChar = stringLeft;
			this.stringRightChar = stringRight;
		}
		return this;
	}

	/**
	 * Sets the left and right string margin.
	 * @param stringMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightMargin(int stringMargin){
		if(stringMargin>-1){
			this.stringLeftMargin = stringMargin;
			this.stringRightMargin = stringMargin;
		}
		return this;
	}

	/**
	 * Sets the left and right string margin.
	 * @param stringLeft margin
	 * @param stringRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightMargin(int stringLeft, int stringRight){
		if(stringLeft>-1 && stringRight>-1){
			this.stringLeftMargin = stringLeft;
			this.stringRightMargin = stringRight;
		}
		return this;
	}

	/**
	 * Sets the right string margin character.
	 * @param stringRight character
	 * @return this to allow chaining
	 */
	public AP_Context setStringRightChar(Character stringRight) {
		if(stringRight!=null){
			this.stringRightChar = stringRight;
		}
		return this;
	}

	/**
	 * Sets the right string margin
	 * @param stringRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringRightMargin(int stringRight) {
		if(stringRight>-1){
			this.stringRightMargin = stringRight;
		}
		return this;
	}

	/**
	 * Sets the target translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param targetTranslator translator
	 */
	public void setTargetTranslator(TargetTranslator targetTranslator) {
		if(targetTranslator!=null){
			this.targetTranslator = targetTranslator;
			this.charTranslator = null;
			this.htmlElementTranslator = null;
		}
	}

	/**
	 * Sets the bottom text margin
	 * @param textBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextBottomMargin(int textBottom) {
		if(textBottom>-1){
			this.textBottomMargin = textBottom;
		}
		return this;
	}

	/**
	 * Sets the left text margin character.
	 * @param textLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftChar(Character textLeft) {
		if(textLeft!=null){
			this.textLeftChar = textLeft;
		}
		return this;
	}

	/**
	 * Sets the left text margin
	 * @param textLeft margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftMargin(int textLeft) {
		if(textLeft>-1){
			this.textLeftMargin = textLeft;
		}
		return this;
	}

	/**
	 * Sets the left and right text margin character.
	 * @param textChar character
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightChar(Character textChar){
		if(textChar!=null){
			this.textLeftChar = textChar;
			this.textRightChar = textChar;
		}
		return this;
	}

	/**
	 * Sets the left and right text margin character.
	 * @param textLeft character
	 * @param textRight character
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightChar(Character textLeft, Character textRight){
		if(textLeft!=null && textRight!=null){
			this.textLeftChar = textLeft;
			this.textRightChar = textRight;
		}
		return this;
	}

	/**
	 * Sets the left and right text margin.
	 * @param textMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightMargin(int textMargin){
		if(textMargin>-1){
			this.textLeftMargin = textMargin;
			this.textRightMargin = textMargin;
		}
		return this;
	}

	/**
	 * Sets the left and right text margin.
	 * @param textLeft margin
	 * @param textRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightMargin(int textLeft, int textRight){
		if(textLeft>-1 && textRight>-1){
			this.textLeftMargin = textLeft;
			this.textRightMargin = textRight;
		}
		return this;
	}

	/**
	 * Sets the right text margin character.
	 * @param textRight character
	 * @return this to allow chaining
	 */
	public AP_Context setTextRightChar(Character textRight) {
		if(textRight!=null){
			this.textRightChar = textRight;
		}
		return this;
	}

	/**
	 * Sets the right text margin
	 * @param textRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextRightMargin(int textRight) {
		if(textRight>-1){
			this.textRightMargin = textRight;
		}
		return this;
	}

	/**
	 * Sets the top and bottom text margin.
	 * @param textMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextTopBottomMargin(int textMargin){
		if(textMargin>-1){
			this.textTopMargin = textMargin;
			this.textBottomMargin = textMargin;
		}
		return this;
	}

	/**
	 * Sets the top and bottom text margin.
	 * @param textTop margin
	 * @param textBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextTopBottomMargin(int textTop, int textBottom){
		if(textTop>-1 && textBottom>-1){
			this.textTopMargin = textTop;
			this.textBottomMargin = textBottom;
		}
		return this;
	}

	/**
	 * Sets the top text margin
	 * @param textTop margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextTopMargin(int textTop) {
		if(textTop>-1){
			this.textTopMargin = textTop;
		}
		return this;
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

}
