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

import de.vandermeer.skb.interfaces.translators.CharacterTranslator;
import de.vandermeer.skb.interfaces.translators.HtmlElementTranslator;
import de.vandermeer.skb.interfaces.translators.TargetTranslator;

/**
 * Character converters object for an {@link AP_Context}.
 * The object's translators are either all null (no translator set) or one of them is set.
 * This means there will be not more than one translator set.
 * Setting a translator will unset (set to null) all other translators.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.1.0
 */
public class AP_CtxtConverters {

	/** A simple character translator. */
	protected CharacterTranslator charTranslator;

	/** A translator for HTML elements. */
	protected HtmlElementTranslator htmlElementTranslator;

	/** A translator for a particular target. */
	protected TargetTranslator targetTranslator;

	/**
	 * Creates a new object with all converters set to null.
	 */
	public AP_CtxtConverters(){}

	/**
	 * Returns the character translator
	 * @return character translator
	 */
	public CharacterTranslator getCharTranslator() {
		return this.charTranslator;
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
	 * Returns the HTML entity translator.
	 * @return HTML entity
	 */
	public HtmlElementTranslator getHtmlElementTranslator() {
		return this.htmlElementTranslator;
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
	 * Returns the target translator.
	 * @return target translator, null if not set
	 */
	public TargetTranslator getTargetTranslator() {
		return this.targetTranslator;
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

}
