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
public class AP_Context implements IsObjectContext {

	/** Paragraph margins. */
	protected AP_CtxtMargins margins = new AP_CtxtMargins();

	/** Paragraph characters. */
	protected AP_CtxtCharacters characters = new AP_CtxtCharacters();

	/** Paragraph indentations. */
	protected AP_CtxtIndents indents = new AP_CtxtIndents();

	/** Paragraph strings. */
	protected AP_CtxtStrings strings = new AP_CtxtStrings();

	/** Paragraph converters. */
	protected AP_CtxtConverters converters = new AP_CtxtConverters();

	/** Paragraph alignment, default is {@link AP_Alignment#JUSTIFIED_LEFT}. */
	protected AP_Alignment alignment = AP_Alignment.JUSTIFIED_LEFT;

	/** Paragraph format, default is {@link AP_Format#NONE}. */
	protected AP_Format format = AP_Format.NONE;

	/** The width of the paragraph, actual width depends on padding settings, default is `80`. */
	protected int width = 80;

	/** The renderer for this context, default is {@link AP_Renderer}. */
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
	 * - Converters from {@link AP_CtxtConverters}
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
	 * Returns the end string.
	 * @return end string
	 */
	public final String getEndString() {
		return this.strings.end;
	}

	/**
	 * Returns the first line indentation.
	 * @return first line indentation
	 */
	public int getFirstLineIndent() {
		return this.indents.firstLine;
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
	public TA_FrameTheme getFrame() {
		return this.frame;
	}

	/**
	 * Returns the bottom frame margin.
	 * @return bottom frame margin
	 */
	public int getFrameBottomMargin() {
		return this.margins.frameBottom;
	}

	/**
	 * Returns the left frame margin character.
	 * @return left frame margin character
	 */
	public Character getFrameLeftChar() {
		return this.characters.frameLeft;
	}

	/**
	 * Returns the left frame margin.
	 * @return left frame margin
	 */
	public int getFrameLeftMargin() {
		return this.margins.frameLeft;
	}

	/**
	 * Returns the right frame margin character.
	 * @return right frame margin character
	 */
	public Character getFrameRightChar() {
		return this.characters.frameRight;
	}

	/**
	 * Returns the right frame margin.
	 * @return right frame margin
	 */
	public int getFrameRightMargin() {
		return this.margins.frameRight;
	}

	/**
	 * Returns the top frame margin.
	 * @return top frame margin
	 */
	public int getFrameTopMargin() {
		return this.margins.frameTop;
	}

	/**
	 * Returns the hanging paragraph indentation
	 * @return hanging paragraph indentation
	 */
	public int getHangingParaIndent() {
		return this.indents.hangingPara;
	}

	/**
	 * Returns the in-text white space character.
	 * @return in-text white space character
	 */
	public Character getInnerWsChar() {
		return this.characters.innerWs;
	}

	/**
	 * Returns the left padding character.
	 * @return left padding character
	 */
	public Character getPaddingLeftChar() {
		return this.characters.paddingLeft;
	}

	/**
	 * Returns the right padding character.
	 * @return right padding character
	 */
	public Character getPaddingRightChar() {
		return this.characters.paddingRight;
	}

	/**
	 * Returns the set renderer.
	 * @return renderer
	 */
	public AP_Renderer getRenderer(){
		return this.renderer;
	}

	/**
	 * Returns the start string.
	 * @return start string
	 */
	public final String getStartString() {
		return this.strings.start;
	}

	/**
	 * Returns the left string margin character.
	 * @return left string margin character
	 */
	public Character getStringLeftChar() {
		return this.characters.stringLeft;
	}

	/**
	 * Returns the left string margin.
	 * @return left string margin
	 */
	public int getStringLeftMargin() {
		return this.margins.stringLeft;
	}

	/**
	 * Returns the right string margin character.
	 * @return right string margin character
	 */
	public Character getStringRightChar() {
		return this.characters.stringRight;
	}

	/**
	 * Returns the right string margin.
	 * @return right string margin
	 */
	public int getStringRightMargin() {
		return this.margins.stringRight;
	}

	/**
	 * Returns the string settings. 
	 * @return string settings
	 */
//	public AP_CtxtStrings getStrings() {
//		return this.strings;
//	}

	/**
	 * Returns the bottom text margin.
	 * @return bottom text margin
	 */
	public int getTextBottomMargin() {
		return this.margins.textBottom;
	}

	/**
	 * Returns the left text margin character.
	 * @return left text margin character
	 */
	public Character getTextLeftChar() {
		return this.characters.textLeft;
	}

	/**
	 * Returns the left text margin.
	 * @return left text margin
	 */
	public int getTextLeftMargin() {
		return this.margins.textLeft;
	}

	/**
	 * Returns the right text margin character.
	 * @return right text margin character
	 */
	public Character getTextRightChar() {
		return this.characters.textRight;
	}

	/**
	 * Returns the right text margin.
	 * @return right text margin
	 */
	public int getTextRightMargin() {
		return this.margins.textRight;
	}

	/**
	 * Returns the top text margin.
	 * @return top text margin
	 */
	public int getTextTopMargin() {
		return this.margins.textTop;
	}

	/**
	 * Returns the paragraph width.
	 * @return paragraph width
	 */
	public int getWidth() {
		return this.width;
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

	/**
	 * Returns the width including text and string margins.
	 * @return width
	 */
	public int getWidthIncStringMargins(){
		int width = this.getWidthIncTextMargins()-this.margins.getStringLeft()-this.margins.getStringRight();
		if(this.strings.getStart()!=null){
			width -= this.strings.getStart().length();
		}
		if(this.strings.getEnd()!=null){
			width -= this.strings.getEnd().length();
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
		if(this.strings.getStart()!=null){
			w -= this.strings.getStart().length();
		}
		if(this.strings.getEnd()!=null){
			w -= this.strings.getEnd().length();
		}
		return w;
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
	 * Sets the end string.
	 * @param end string, can be null
	 * @return this to allow chaining
	 */
	public final AP_Context setEndString(String end) {
		this.strings.end = end;
		return this;
	}

	/**
	 * Sets the first line indentation
	 * @param firstLine indentation
	 * @return this to allow chaining
	 */
	public AP_Context setFirstLineIndent(int firstLine) {
		this.indents.firstLine = firstLine;
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
	public AP_Context setFrame(TA_FrameTheme frame) {
		this.frame = frame;
		return this;
	}

	/**
	 * Sets the bottom frame margin
	 * @param frameBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameBottomMargin(int frameBottom) {
		this.margins.frameBottom = frameBottom;
		return this;
	}

	/**
	 * Sets the left frame margin character.
	 * @param frameLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftChar(Character frameLeft) {
		Validate.notNull(frameLeft);
		this.characters.frameLeft = frameLeft;
		return this;
	}

	/**
	 * Sets the left frame margin
	 * @param frameLeft margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftMargin(int frameLeft) {
		this.margins.frameLeft = frameLeft;
		return this;
	}

	/**
	 * Sets the left and right frame margin character.
	 * @param frameChar character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightChar(Character frameChar){
		Validate.notNull(frameChar);
		this.characters.frameLeft = frameChar;
		this.characters.frameRight = frameChar;
		return this;
	}

	/**
	 * Sets the left and right frame margin character.
	 * @param frameLeft character
	 * @param frameRight character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightChar(Character frameLeft, Character frameRight){
		Validate.notNull(frameLeft);
		Validate.notNull(frameRight);
		this.characters.frameLeft = frameLeft;
		this.characters.frameRight = frameRight;
		return this;
	}

	/**
	 * Sets the left and right frame margin.
	 * @param frameMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightMargin(int frameMargin){
		this.margins.frameLeft = frameMargin;
		this.margins.frameRight = frameMargin;
		return this;
	}

	/**
	 * Sets the left and right frame margin.
	 * @param frameLeft margin
	 * @param frameRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameLeftRightMargin(int frameLeft, int frameRight){
		this.margins.frameLeft = frameLeft;
		this.margins.frameRight = frameRight;
		return this;
	}

	/**
	 * Sets the right frame margin character.
	 * @param frameRight character
	 * @return this to allow chaining
	 */
	public AP_Context setFrameRightChar(Character frameRight) {
		Validate.notNull(frameRight);
		this.characters.frameRight = frameRight;
		return this;
	}

	/**
	 * Sets the right frame margin
	 * @param frameRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameRightMargin(int frameRight) {
		this.margins.frameRight = frameRight;
		return this;
	}

	/**
	 * Sets the top and bottom frame margin.
	 * @param frameMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameTopBottomMargin(int frameMargin){
		this.margins.frameTop = frameMargin;
		this.margins.frameBottom = frameMargin;
		return this;
	}

	/**
	 * Sets the top and bottom frame margin.
	 * @param frameTop margin
	 * @param frameBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameTopBottomMargin(int frameTop, int frameBottom){
		this.margins.frameTop = frameTop;
		this.margins.frameBottom = frameBottom;
		return this;
	}

	/**
	 * Sets the top frame margin
	 * @param frameTop margin
	 * @return this to allow chaining
	 */
	public AP_Context setFrameTopMargin(int frameTop) {
		this.margins.frameTop = frameTop;
		return this;
	}

	/**
	 * Sets the hanging paragraph indentation
	 * @param hangingPara indentation
	 * @return this to allow chaining
	 */
	public AP_Context setHangingParaIndent(int hangingPara) {
		this.indents.hangingPara = hangingPara;
		return this;
	}

	/**
	 * Sets the in-text white space character
	 * @param innerWs in-text white space character
	 * @return this to allow chaining
	 */
	public AP_Context setInnerWsChar(Character innerWs) {
		Validate.notNull(innerWs);
		this.characters.innerWs = innerWs;
		return this;
	}

	/**
	 * Sets the left padding character.
	 * @param paddingLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeftChar(Character paddingLeft) {
		Validate.notNull(paddingLeft);
		this.characters.paddingLeft = paddingLeft;
		return this;
	}

	/**
	 * Sets the left and right padding character.
	 * @param paddingChar character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeftRightChar(Character paddingChar){
		Validate.notNull(paddingChar);
		this.characters.paddingLeft = paddingChar;
		this.characters.paddingRight = paddingChar;
		return this;
	}

	/**
	 * Sets the left and right padding character.
	 * @param paddingLeft character
	 * @param paddingRight character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingLeftRightChar(Character paddingLeft, Character paddingRight){
		Validate.notNull(paddingLeft);
		Validate.notNull(paddingRight);
		this.characters.paddingLeft = paddingLeft;
		this.characters.paddingRight = paddingRight;
		return this;
	}

	/**
	 * Sets the right padding character.
	 * @param paddingRight character
	 * @return this to allow chaining
	 */
	public AP_Context setPaddingRightChar(Character paddingRight) {
		Validate.notNull(paddingRight);
		this.characters.paddingRight = paddingRight;
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
	 * Sets the start and end string to the same string.
	 * @param start string, can be null
	 * @param end string, can be null
	 * @return this to allow chaining
	 */
	public final AP_Context setStartEndString(String start, String end) {
		this.strings.start = start;
		this.strings.end = end;
		return this;
	}

	/**
	 * Sets the start string.
	 * @param start string, can be null
	 * @return this to allow chaining
	 */
	public final AP_Context setStartString(String start) {
		this.strings.start = start;
		return this;
	}

	/**
	 * Sets the left string margin character.
	 * @param stringLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftChar(Character stringLeft) {
		Validate.notNull(stringLeft);
		this.characters.stringLeft = stringLeft;
		return this;
	}

	/**
	 * Sets the left string margin
	 * @param stringLeft margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftMargin(int stringLeft) {
		this.margins.stringLeft = stringLeft;
		return this;
	}

	/**
	 * Sets the left and right string margin character.
	 * @param stringChar character
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightChar(Character stringChar){
		Validate.notNull(stringChar);
		this.characters.stringLeft = stringChar;
		this.characters.stringRight = stringChar;
		return this;
	}

	/**
	 * Sets the left and right string margin character.
	 * @param stringLeft character
	 * @param stringRight character
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightChar(Character stringLeft, Character stringRight){
		Validate.notNull(stringLeft);
		Validate.notNull(stringRight);
		this.characters.stringLeft = stringLeft;
		this.characters.stringRight = stringRight;
		return this;
	}

	/**
	 * Sets the left and right string margin.
	 * @param stringMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightMargin(int stringMargin){
		this.margins.stringLeft = stringMargin;
		this.margins.stringRight = stringMargin;
		return this;
	}

	/**
	 * Sets the left and right string margin.
	 * @param stringLeft margin
	 * @param stringRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringLeftRightMargin(int stringLeft, int stringRight){
		this.margins.stringLeft = stringLeft;
		this.margins.stringRight = stringRight;
		return this;
	}

	/**
	 * Sets the right string margin character.
	 * @param stringRight character
	 * @return this to allow chaining
	 */
	public AP_Context setStringRightChar(Character stringRight) {
		Validate.notNull(stringRight);
		this.characters.stringRight = stringRight;
		return this;
	}

	/**
	 * Sets the right string margin
	 * @param stringRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setStringRightMargin(int stringRight) {
		this.margins.stringRight = stringRight;
		return this;
	}

	/**
	 * Sets the bottom text margin
	 * @param textBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextBottomMargin(int textBottom) {
		this.margins.textBottom = textBottom;
		return this;
	}

	/**
	 * Sets the left text margin character.
	 * @param textLeft character
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftChar(Character textLeft) {
		Validate.notNull(textLeft);
		this.characters.textLeft = textLeft;
		return this;
	}

	/**
	 * Sets the left text margin
	 * @param textLeft margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftMargin(int textLeft) {
		this.margins.textLeft = textLeft;
		return this;
	}

	/**
	 * Sets the left and right text margin character.
	 * @param textChar character
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightChar(Character textChar){
		Validate.notNull(textChar);
		this.characters.textLeft = textChar;
		this.characters.textRight = textChar;
		return this;
	}

	/**
	 * Sets the left and right text margin character.
	 * @param textLeft character
	 * @param textRight character
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightChar(Character textLeft, Character textRight){
		Validate.notNull(textLeft);
		Validate.notNull(textRight);
		this.characters.textLeft = textLeft;
		this.characters.textRight = textRight;
		return this;
	}

	/**
	 * Sets the left and right text margin.
	 * @param textMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightMargin(int textMargin){
		this.margins.textLeft = textMargin;
		this.margins.textRight = textMargin;
		return this;
	}

	/**
	 * Sets the left and right text margin.
	 * @param textLeft margin
	 * @param textRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextLeftRightMargin(int textLeft, int textRight){
		this.margins.textLeft = textLeft;
		this.margins.textRight = textRight;
		return this;
	}

	/**
	 * Sets the right text margin character.
	 * @param textRight character
	 * @return this to allow chaining
	 */
	public AP_Context setTextRightChar(Character textRight) {
		Validate.notNull(textRight);
		this.characters.textRight = textRight;
		return this;
	}

	/**
	 * Sets the right text margin
	 * @param textRight margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextRightMargin(int textRight) {
		this.margins.textRight = textRight;
		return this;
	}

	/**
	 * Sets the top and bottom text margin.
	 * @param textMargin margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextTopBottomMargin(int textMargin){
		this.margins.textTop = textMargin;
		this.margins.textBottom = textMargin;
		return this;
	}

	/**
	 * Sets the top and bottom text margin.
	 * @param textTop margin
	 * @param textBottom margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextTopBottomMargin(int textTop, int textBottom){
		this.margins.textTop = textTop;
		this.margins.textBottom = textBottom;
		return this;
	}

	/**
	 * Sets the top text margin
	 * @param textTop margin
	 * @return this to allow chaining
	 */
	public AP_Context setTextTopMargin(int textTop) {
		this.margins.textTop = textTop;
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

	/**
	 * Sets the character translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param charTranslator translator
	 */
	public void setCharTranslator(CharacterTranslator charTranslator) {
		this.converters.setCharTranslator(charTranslator);
	}

	/**
	 * Sets the HTML entity translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param htmlElementTranslator translator
	 */
	public void setHtmlElementTranslator(HtmlElementTranslator htmlElementTranslator) {
		this.converters.setHtmlElementTranslator(htmlElementTranslator);
	}

	/**
	 * Sets the target translator.
	 * It will also remove any other translator set.
	 * Nothing will happen if the argument is null.
	 * @param targetTranslator translator
	 */
	public void setTargetTranslator(TargetTranslator targetTranslator) {
		this.converters.setTargetTranslator(targetTranslator);
	}

	/**
	 * Returns the target translator.
	 * @return target translator, null if not set
	 */
	public TargetTranslator getTargetTranslator() {
		return this.converters.targetTranslator;
	}

	/**
	 * Returns the HTML entity translator.
	 * @return HTML entity
	 */
	public HtmlElementTranslator getHtmlElementTranslator() {
		return this.converters.htmlElementTranslator;
	}

	/**
	 * Returns the character translator
	 * @return character translator
	 */
	public CharacterTranslator getCharTranslator() {
		return this.converters.charTranslator;
	}
}
