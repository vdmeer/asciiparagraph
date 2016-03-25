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
 * Characters object for an {@link AP_Context}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.0.1
 */
public class AP_CtxtCharacters {

	/** The padding character for left side of a text line if not justified. */
	protected Character paddingLeft = ' ';

	/** The padding character for right side of a text line if not justified. */
	protected Character paddingRight = ' ';

	/** The character to be used for white spaces in text. */
	protected Character innerWs = ' ';

	/** The character for left text margin. */
	protected Character textLeft = ' ';

	/** The character for right text margin. */
	protected Character textRight = ' ';

	/** The character for left string margin. */
	protected Character stringLeft = ' ';

	/** The character for right string margin. */
	protected Character stringRight = ' ';

	/** The character for left frame margin. */
	protected Character frameLeft = ' ';

	/** The character for right frame margin. */
	protected Character frameRight = ' ';

	/**
	 * Creates a new object with all characters set to `' '`.
	 */
	public AP_CtxtCharacters(){}

	/**
	 * Returns the left padding character.
	 * @return left padding character
	 */
	public Character getPaddingLeft() {
		return this.paddingLeft;
	}

	/**
	 * Sets the left padding character.
	 * @param paddingLeft character
	 */
	public void setPaddingLeft(Character paddingLeft) {
		Validate.notNull(paddingLeft);
		this.paddingLeft = paddingLeft;
	}

	/**
	 * Returns the right padding character.
	 * @return right padding character
	 */
	public Character getPaddingRight() {
		return this.paddingRight;
	}

	/**
	 * Sets the right padding character.
	 * @param paddingRight character
	 */
	public void setPaddingRight(Character paddingRight) {
		Validate.notNull(paddingRight);
		this.paddingRight = paddingRight;
	}

	/**
	 * Returns the in-text white space character.
	 * @return in-text white space character
	 */
	public Character getInnerWs() {
		return this.innerWs;
	}

	/**
	 * Sets the in-text white space character
	 * @param innerWs in-text white space character
	 */
	public void setInnerWs(Character innerWs) {
		Validate.notNull(innerWs);
		this.innerWs = innerWs;
	}

	/**
	 * Returns the left text margin character.
	 * @return left text margin character
	 */
	public Character getTextLeft() {
		return this.textLeft;
	}

	/**
	 * Sets the left text margin character.
	 * @param textLeft character
	 */
	public void setTextLeft(Character textLeft) {
		Validate.notNull(textLeft);
		this.textLeft = textLeft;
	}

	/**
	 * Returns the right text margin character.
	 * @return right text margin character
	 */
	public Character getTextRight() {
		return this.textRight;
	}

	/**
	 * Sets the right text margin character.
	 * @param textRight character
	 */
	public void setTextRight(Character textRight) {
		Validate.notNull(textRight);
		this.textRight = textRight;
	}

	/**
	 * Returns the left string margin character.
	 * @return left string margin character
	 */
	public Character getStringLeft() {
		return this.stringLeft;
	}

	/**
	 * Sets the left string margin character.
	 * @param stringLeft character
	 */
	public void setStringLeft(Character stringLeft) {
		Validate.notNull(stringLeft);
		this.stringLeft = stringLeft;
	}

	/**
	 * Returns the right string margin character.
	 * @return right string margin character
	 */
	public Character getStringRight() {
		return this.stringRight;
	}

	/**
	 * Sets the right string margin character.
	 * @param stringRight character
	 */
	public void setStringRight(Character stringRight) {
		Validate.notNull(stringRight);
		this.stringRight = stringRight;
	}

	/**
	 * Returns the left frame margin character.
	 * @return left frame margin character
	 */
	public Character getFrameLeft() {
		return this.frameLeft;
	}

	/**
	 * Sets the left frame margin character.
	 * @param frameLeft character
	 */
	public void setFrameLeft(Character frameLeft) {
		Validate.notNull(frameLeft);
		this.frameLeft = frameLeft;
	}

	/**
	 * Returns the right frame margin character.
	 * @return right frame margin character
	 */
	public Character getFrameRight() {
		return this.frameRight;
	}

	/**
	 * Sets the right frame margin character.
	 * @param frameRight character
	 */
	public void setFrameRight(Character frameRight) {
		Validate.notNull(frameRight);
		this.frameRight = frameRight;
	}
}
