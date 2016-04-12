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

/**
 * Margins object for an {@link AP_Context}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v0.0.3-SNAPSHOT build 160319 (19-Mar-16) for Java 1.7
 * @since      v0.1.0
 */
public class AP_CtxtMargins {

	/** Margin between text and start string. */
	protected int textLeft = 0;

	/** Margin between text and end string. */
	protected int textRight = 0;

	/** Margin between start string and frame border. */
	protected int stringLeft = 0;

	/** Margin between end string and frame border. */
	protected int stringRight = 0;

	/** Margin outside the left frame border. */
	protected int frameLeft = 0;

	/** Margin outside the right frame border. */
	protected int frameRight = 0;

	/** Margin between text and top frame. */
	protected int textTop = 0;

	/** Margin between text and bottom frame. */
	protected int textBottom = 1;

	/** Margin outside top frame. */
	protected int frameTop = 0;

	/** Margin outside bottom frame. */
	protected int frameBottom = 0;

	/**
	 * Creates a new margin object with all margins set to 0.
	 */
	public AP_CtxtMargins(){
		
	}

	/**
	 * Returns the bottom frame margin.
	 * @return bottom frame margin
	 */
	public int getFrameBottom() {
		return this.frameBottom;
	}

	/**
	 * Returns the left frame margin.
	 * @return left frame margin
	 */
	public int getFrameLeft() {
		return this.frameLeft;
	}

	/**
	 * Returns the right frame margin.
	 * @return right frame margin
	 */
	public int getFrameRight() {
		return this.frameRight;
	}

	/**
	 * Returns the top frame margin.
	 * @return top frame margin
	 */
	public int getFrameTop() {
		return this.frameTop;
	}

	/**
	 * Returns the left string margin.
	 * @return left string margin
	 */
	public int getStringLeft() {
		return this.stringLeft;
	}

	/**
	 * Returns the right string margin.
	 * @return right string margin
	 */
	public int getStringRight() {
		return this.stringRight;
	}

	/**
	 * Returns the bottom text margin.
	 * @return bottom text margin
	 */
	public int getTextBottom() {
		return this.textBottom;
	}

	/**
	 * Returns the left text margin.
	 * @return left text margin
	 */
	public int getTextLeft() {
		return this.textLeft;
	}

	/**
	 * Returns the right text margin.
	 * @return right text margin
	 */
	public int getTextRight() {
		return this.textRight;
	}

	/**
	 * Returns the top text margin.
	 * @return top text margin
	 */
	public int getTextTop() {
		return this.textTop;
	}

	/**
	 * Sets the bottom frame margin
	 * @param frameBottom margin
	 */
	public void setFrameBottom(int frameBottom) {
		this.frameBottom = frameBottom;
	}

	/**
	 * Sets the left frame margin
	 * @param frameLeft margin
	 */
	public void setFrameLeft(int frameLeft) {
		this.frameLeft = frameLeft;
	}

	/**
	 * Sets the right frame margin
	 * @param frameRight margin
	 */
	public void setFrameRight(int frameRight) {
		this.frameRight = frameRight;
	}

	/**
	 * Sets the top frame margin
	 * @param frameTop margin
	 */
	public void setFrameTop(int frameTop) {
		this.frameTop = frameTop;
	}

	/**
	 * Sets the left string margin
	 * @param stringLeft margin
	 */
	public void setStringLeft(int stringLeft) {
		this.stringLeft = stringLeft;
	}

	/**
	 * Sets the right string margin
	 * @param stringRight margin
	 */
	public void setStringRight(int stringRight) {
		this.stringRight = stringRight;
	}

	/**
	 * Sets the bottom text margin
	 * @param textBottom margin
	 */
	public void setTextBottom(int textBottom) {
		this.textBottom = textBottom;
	}

	/**
	 * Sets the left text margin
	 * @param textLeft margin
	 */
	public void setTextLeft(int textLeft) {
		this.textLeft = textLeft;
	}

	/**
	 * Sets the right text margin
	 * @param textRight margin
	 */
	public void setTextRight(int textRight) {
		this.textRight = textRight;
	}

	/**
	 * Sets the top text margin
	 * @param textTop margin
	 */
	public void setTextTop(int textTop) {
		this.textTop = textTop;
	}
}
